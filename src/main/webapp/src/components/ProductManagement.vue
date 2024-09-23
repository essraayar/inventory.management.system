<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h4 class="text-center">Ürün Yönetimi</h4>

        <!-- Arama Çubuğu -->
        <div class="mb-3">
          <input
            type="text"
            class="form-control mb-2"
            placeholder="Ürün adı ile ara..."
            v-model="searchQuery.name"
          >
          <input
            type="text"
            class="form-control mb-2"
            placeholder="Kategori ile ara..."
            v-model="searchQuery.category"
          >
          <div class="form-group">
            <label for="priceMin">Fiyat Aralığı:</label>
            <div class="d-flex">
              <input
                type="number"
                class="form-control mr-2"
                id="priceMin"
                placeholder="Min"
                v-model.number="searchQuery.priceMin"
              >
              <input
                type="number"
                class="form-control"
                id="priceMax"
                placeholder="Max"
                v-model.number="searchQuery.priceMax"
              >
            </div>
          </div>
        </div>

        <!-- Ürün Ekle Button -->
        <button type="button" class="btn btn-primary mb-3" @click="openAddModal">
          <i class="fas fa-plus"></i> Ürün Ekle
        </button>

        <!-- Table -->
        <table class="table table-bordered">
          <thead>
            <tr>
              <th scope="col">Ürün Adı</th>
              <th scope="col">Kategori</th>
              <th scope="col">Fiyat (TL)</th>
              <th scope="col">İşlemler</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="product in filteredProducts" :key="product.id">
              <td>{{ product.name }}</td>
              <td>{{ product.category }}</td>
              <td>{{ product.price }}</td>
              <td>
                <button type="button" class="btn btn-success mr-2" @click="openEditModal(product.id)">
                  <i class="fas fa-edit"></i> Düzenle
                </button>
                <button type="button" class="btn btn-danger" @click="deleteProduct(product.id)">
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
            <h5 class="modal-title">{{ isNewProduct ? 'Ürün Ekle' : 'Ürün Düzenle' }}</h5>
            <button type="button" class="close" aria-label="Close" @click="closeEditModal">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="updateProduct">
              <div class="form-group">
                <label for="productName">Ürün Adı:</label>
                <input type="text" class="form-control" id="productName" v-model="editedProduct.name" required>
              </div>
              <div class="form-group">
                <label for="productCategory">Kategori:</label>
                <input type="text" class="form-control" id="productCategory" v-model="editedProduct.category" required>
              </div>
              <div class="form-group">
                <label for="productPrice">Fiyat (TL):</label>
                <input type="number" class="form-control" id="productPrice" v-model.number="editedProduct.price" required>
              </div>
              <button type="submit" class="btn btn-primary">{{ isNewProduct ? 'Ekle' : 'Güncelle' }}</button>
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
      products: [],
      editedProduct: {
        id: null,
        name: '',
        category: '',
        price: 0
      },
      isNewProduct: false,
      isModalVisible: false, // Modal'ın görünürlüğünü kontrol eder
      searchQuery: {
        name: '',
        category: '',
        priceMin: null,
        priceMax: null
      } // Arama kriterleri
    };
  },
  computed: {
    filteredProducts() {
      if (!Array.isArray(this.products)) {
        return [];
      }
      return this.products.filter(product => {
        const matchesName = product.name?.toLowerCase().includes(this.searchQuery.name.toLowerCase());
        const matchesCategory = product.category?.toLowerCase().includes(this.searchQuery.category.toLowerCase());
        const matchesPrice = (
            (this.searchQuery.priceMin == null || product.price >= this.searchQuery.priceMin) &&
            (this.searchQuery.priceMax == null || product.price <= this.searchQuery.priceMax)
        );
        return matchesName && matchesCategory && matchesPrice;
      });
    }
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await axios.get('http://localhost:8080/api/products/get-all-products');
        console.log(response.data);
        this.products = Array.isArray(response.data) ? response.data : [];
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    },
    openAddModal() {
      this.isNewProduct = true;
      this.editedProduct = { id: null, name: '', category: '', price: 0 };
      this.isModalVisible = true;
    },
    openEditModal(productId) {
      this.isNewProduct = false;
      const product = this.products.find(prod => prod.id === productId);
      if (product) {
        this.editedProduct = { ...product };
        this.isModalVisible = true;
      }
    },
    closeEditModal() {
      this.editedProduct = { id: null, name: '', category: '', price: 0 };
      this.isModalVisible = false;
    },
    async updateProduct() {
      try {
        if (this.isNewProduct) {
          const response = await axios.post('http://localhost:8080/api/products/create-products', this.editedProduct);
          this.products.push(response.data);
        } else {
          // Ürün güncelleme
          const response = await axios.put(`http://localhost:8080/api/products/update-products/${this.editedProduct.id}`, this.editedProduct);
          const index = this.products.findIndex(prod => prod.id === this.editedProduct.id);
          if (index !== -1) {
            this.products[index] = response.data;
          }
        }
        this.closeEditModal();
      } catch (error) {
        console.error('Error updating product:', error);
      }
    },
    async deleteProduct(productId) {
      try {
        await axios.delete(`http://localhost:8080/api/products/delete-products/${productId}`);
        this.products = this.products.filter(prod => prod.id !== productId);
      } catch (error) {
        console.error('Error deleting product:', error);
      }
    }
  }
};
</script>
<style scoped>
.container {
  padding: 2rem 0rem;
}
h4 {
  margin: 2rem 0rem 1rem;
}
.modal {
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 1050;
  overflow: hidden;
  outline: 0;
  transition: opacity 0.15s;
}
.modal[style*="display: none"] {
  display: none;
}
.modal-dialog {
  position: relative;
  width: auto;
  margin: 0;
  pointer-events: none;
}
.modal-content {
  position: relative;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 0.3rem;
  pointer-events: auto;
  transition: transform 0.3s ease-out;
}
.modal-body {
  position: relative;
  flex: 1 1 auto;
  padding: 1rem;
}
.modal-header,
.modal-footer {
  padding: 0.5rem;
}
.modal-title {
  margin-bottom: 0;
  line-height: 1.5;
}
.modal-open {
  overflow: hidden;
}
</style>
