<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h4 class="text-center">Satış Yönetimi</h4>
        <button type="button" class="btn btn-primary mb-3" @click="openAddModal">
          <i class="fas fa-plus"></i> Satış Ekle
        </button>
        <table class="table table-bordered">
          <thead>
            <tr>
              <th scope="col">Müşteri E-posta</th>
              <th scope="col">Satış Tarihi</th>
              <th scope="col">Ürün Adı</th>
              <th scope="col">Adet</th>
              <th scope="col">Toplam Tutar (TL)</th>
              <th scope="col">İşlemler</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="sale in sales" :key="sale.id">
              <td>{{ getCustomerEmail(sale.customerEmail) }}</td>
              <td>{{ sale.saleDate }}</td>
              <td>{{ getProductName(sale.productId) }}</td>
              <td>{{ sale.quantity }}</td>
              <td>{{ sale.totalAmount }}</td>
              <td>
                <button type="button" class="btn btn-success mr-2" @click="openEditModal(sale.id)">
                  <i class="fas fa-edit"></i> Düzenle
                </button>
                <button type="button" class="btn btn-danger" @click="deleteSale(sale.id)">
                  <i class="far fa-trash-alt"></i> Sil
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Düzenleme ve Ekleme Modal -->
    <div v-if="isModalVisible" class="modal" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ isNewSale ? 'Satış Ekle' : 'Satış Düzenle' }}</h5>
            <button type="button" class="close" aria-label="Close" @click="closeEditModal">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="updateSale">
              <div class="form-group">
                <label for="productSelect">Ürün Adı:</label>
                <select class="form-control" id="productSelect" v-model="editedSale.productId">
                  <option v-for="product in products" :key="product.id" :value="product.id">{{ product.name }}</option>
                </select>
              </div>
              <div class="form-group">
                <label for="customerEmail">Müşteri E-posta:</label>
                <input type="email" class="form-control" id="customerEmail" v-model="editedSale.customerEmail" list="emailOptions">
                <datalist id="emailOptions">
                  <option v-for="email in customerEmails" :key="email" :value="email"></option>
                </datalist>
              </div>
              <div class="form-group">
                <label for="saleDate">Satış Tarihi:</label>
                <input type="date" class="form-control" id="saleDate" v-model="editedSale.saleDate">
              </div>
              <div class="form-group">
                <label for="saleQuantity">Adet:</label>
                <input type="number" class="form-control" id="saleQuantity" v-model.number="editedSale.quantity">
              </div>
              <div class="form-group">
                <label for="saleTotalAmount">Toplam Tutar (TL):</label>
                <input type="number" step="0.01" class="form-control" id="saleTotalAmount" v-model.number="editedSale.totalAmount">
              </div>
              <button type="submit" class="btn btn-primary">{{ isNewSale ? 'Ekle' : 'Güncelle' }}</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- /Düzenleme ve Ekleme Modal -->
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      sales: [],
      editedSale: { productId: null, customerEmail: '', saleDate: '', quantity: 0, totalAmount: 0 },
      editedSaleId: null,
      isNewSale: false,
      isModalVisible: false,
      products: [],
      customerEmails: ['customer1@example.com', 'customer2@example.com']
    };
  },
  created() {
    this.fetchSales();
    this.fetchProducts();
  },
   watch: {
      'editedSale.quantity': function(newQuantity) {
        const selectedProduct = this.products.find(p => p.id === this.editedSale.productId);
        if (selectedProduct) {
          this.editedSale.totalAmount = newQuantity * selectedProduct.price;
        }
      },
      'editedSale.productId': function(newProductId) {
        const selectedProduct = this.products.find(p => p.id === newProductId);
        if (selectedProduct) {
          this.editedSale.totalAmount = this.editedSale.quantity * selectedProduct.price;
        }
      }
    },
  methods: {
    async fetchSales() {
      try {
        const response = await axios.get('http://localhost:8080/api/sales/get-all-sales');
        console.log(response.data);
        this.sales = response.data;
      } catch (error) {
        console.error('Error fetching sales:', error);
      }
    },
    async fetchProducts() {
      try {
        const response = await axios.get('http://localhost:8080/api/products/get-all-products');
        this.products = response.data;
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    },
    getProductName(productId) {
      const product = this.products.find(p => p.id === productId);
      return product ? product.name : 'Bilgi Yok';
    },


    getCustomerEmail(customerEmail) {
      // E-posta adreslerini manuel olarak eşleştir
      const email = this.customerEmails.find(email => email === customerEmail);
      return email || customerEmail;
    },
    openAddModal() {
      this.isNewSale = true;
      this.editedSale = { productId: null, customerEmail: '', saleDate: '', quantity: 0, totalAmount: 0 };
      this.isModalVisible = true;
    },
    openEditModal(saleId) {
      this.isNewSale = false;
      const sale = this.sales.find(sale => sale.id === saleId);
      if (sale) {
        this.editedSale = { ...sale };
        this.editedSaleId = saleId;
        this.isModalVisible = true;
      }
    },
    closeEditModal() {
      this.editedSale = { productId: null, customerEmail: '', saleDate: '', quantity: 0, totalAmount: 0 };
      this.editedSaleId = null;
      this.isModalVisible = false;
    },
    async updateSale() {
      try {
        if (this.isNewSale) {
          await axios.post('http://localhost:8080/api/sales/create-sales', this.editedSale);
        } else {
          await axios.put(`http://localhost:8080/api/sales/update-sales/${this.editedSaleId}`, this.editedSale);
        }
        this.fetchSales();
        this.closeEditModal();
      } catch (error) {
        console.error('Error updating sale:', error);
      }
    },
    async deleteSale(saleId) {
      try {
        await axios.delete(`http://localhost:8080/api/sales/delete-sales/${saleId}`);
        this.fetchSales();
      } catch (error) {
        console.error('Error deleting sale:', error);
      }
    }
  }
};
</script>

<style scoped>
.container { padding: 2rem 0rem; }
h4 { margin: 2rem 0rem 1rem; }
.modal { display: flex; align-items: center; justify-content: center; background: rgba(0, 0, 0, 0.5); position: fixed; top: 0; right: 0; bottom: 0; left: 0; z-index: 1050; overflow: hidden; outline: 0; transition: opacity 0.15s; }
.modal[style*="display: none"] { display: none; }
.modal-dialog { position: relative; width: auto; margin: 0; pointer-events: none; }
.modal-content { position: relative; background-color: #fff; border: 1px solid rgba(0, 0, 0, 0.2); border-radius: 0.3rem; pointer-events: auto; transition: transform 0.3s ease-out; }
.modal-body { position: relative; flex: 1 1 auto; padding: 1rem; }
.modal-header, .modal-footer { padding: 0.5rem; }
.modal-title { margin-bottom: 0; line-height: 1.5; }
</style>
