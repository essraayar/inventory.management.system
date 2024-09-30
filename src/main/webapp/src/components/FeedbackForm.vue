<template>
  <div class="form-box">
    <h3>Feedback Form</h3>
    <form @submit.prevent="submitForm">
      <label>
        <i class="fa fa-solid fa-envelope"></i>
        Müşteri E-posta
      </label>
      <!-- Müşteri e-posta alanına tıklandığında e-postaları yükleyeceğiz -->
      <input
        type="email"
        v-model="formData.customerEmail"
        required
        class="form-control"
        @focus="loadCustomerEmails"
        list="emailList"
      >
      <!-- Dropdown menüsü -->
      <datalist id="emailList">
        <option v-for="email in customerEmails" :key="email" :value="email"></option>
      </datalist>

      <label>
        <i class="fa fa-solid fa-calendar"></i>
        Satış Tarihi
      </label>
      <input type="date" v-model="formData.saleDate" required class="form-control">

      <label>
        <i class="fa fa-solid fa-box"></i>
        Ürün Adı
      </label>
      <input type="text" v-model="formData.productName" required class="form-control">

      <label>
        <i class="fa-solid fa-star"></i>
        Hizmetimizden memnun musunuz? (1 ile 5 arası puan)
      </label>
      <select v-model="formData.rating" required class="form-control">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select>

      <label>
        <i class="fa-solid fa-comments"></i>
        Önerilerinizi Yazın:
      </label>
      <textarea v-model="formData.msg" rows="4" required class="form-control"></textarea>

      <button type="submit" class="btnContactSubmit">
        Gönder
      </button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formData: {
        customerEmail: '',
        saleDate: '',
        productName: '',
        rating: '5',
        msg: ''
      },
      customerEmails: [] // Müşteri e-postalarını tutacağımız dizi
    };
  },
  methods: {
    async loadCustomerEmails() {
      try {
        // API'den e-posta verilerini alıyoruz
        const response = await axios.get('http://10.20.4.88:8080/api/sales/get-all-customer-emails');
        this.customerEmails = response.data; // E-posta listesini dolduruyoruz
      } catch (error) {
        console.error('E-posta yükleme hatası:', error);
      }
    },
    async submitForm() {
      try {
        await axios.post('http://10.20.4.88:8080/api/feedbacks/add-feedback', this.formData);
        alert('Form başarıyla gönderildi!');
        this.formData = {
          customerEmail: '',
          saleDate: '',
          productName: '',
          rating: '5',
          msg: ''
        };
      } catch (error) {
        console.error('Form gönderme hatası:', error);
        alert('Form gönderilirken bir hata oluştu.');
      }
    }
  }
};
</script>

<style scoped>
.form-box {
  background: #fff;
  width: 40%;
  max-width: 600px;
  margin: 10% auto;
  padding: 1rem;
  border-radius: 1rem;
  box-shadow: 0 0 20px rgba(0, 0, 255, 0.2);
}

.form-box .form-control {
  border-radius: 1rem;
  margin-bottom: 1rem;
  padding: 0.75rem;
  border: 1px solid #ccc;
  width: calc(100% - 2 * 0.75rem);
}

.form-box h3 {
  margin-bottom: 1rem;
  text-align: center;
  color: #0062cc;
}

.btnContactSubmit {
  width: 50%;
  border-radius: 1rem;
  padding: 1.5%;
  color: #fff;
  background-color: #0062cc;
  border: none;
  cursor: pointer;
}
</style>
