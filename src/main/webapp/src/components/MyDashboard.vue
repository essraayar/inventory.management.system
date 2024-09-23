<template>
  <div>
    <!-- Dashboard Header -->
    <header class="dashboard-header">
      <!-- Başlığı tıklanabilir hale getirmek için router-link kullanın -->
      <router-link class="header-title" :to="{ name: 'Dashboard' }">
        Envanter Yönetim Sistemi
      </router-link>
      <nav class="nav-links">
        <router-link class="nav-link" :to="{ name: 'ProductManagement' }">Ürün Yönetimi</router-link>
        <router-link class="nav-link" :to="{ name: 'SaleManagement' }">Satış Yönetimi</router-link>
        <router-link class="nav-link" :to="{ name: 'FeedbackForm' }">Feedback Form</router-link>
      </nav>
    </header>

    <!-- Conditional Main Content -->
    <div v-if="isDashboardPage" class="main-content">
      <h1 class="welcome-message">Hoş Geldiniz!</h1>
      <p class="instructions">Lütfen yukarıdaki menüden yapmak istediğiniz işlemi seçiniz.</p>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'MyDashboard',
  computed: {
    isDashboardPage() {
      return this.$route.name === 'Dashboard';
    }
  }
};
</script>

<style scoped>
/* Container for the entire Dashboard */
.dashboard-header {
  background-color: black; /* Dark purple background */
  padding: 1rem;
  color: #fff; /* White text color */
  display: flex;
  justify-content: space-between; /* Space out the title and nav links */
  align-items: center; /* Center align items vertically */
}

.header-title {
  margin: 0; /* Remove default margin */
  font-size: 1.5rem; /* Adjust font size as needed */
  text-decoration: none; /* Remove underline from the link */
  color: #fff; /* White text color */
  cursor: pointer; /* Pointer cursor on hover */
}

.header-title:hover {
  text-decoration: underline; /* Underline text on hover */
}

.nav-links {
  display: flex;
  gap: 0.5rem; /* Space between links */
}

.nav-link {
  color: #fff;
  padding: 0.5rem 1rem;
  text-decoration: none;
  border: 2px solid #fff; /* White border */
  border-radius: 5px;
  font-weight: bold;
  transition: background-color 0.3s, color 0.3s;
}

.nav-link:hover {
  background-color: #fff; /* White background on hover */
  color: #333; /* Dark grey text on hover */
}

/* Full-screen container for the main content */
.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 60px); /* Adjust based on your header height */
  text-align: center;
  padding: 1rem; /* Add some padding if needed */
}

.welcome-message {
  font-size: 2rem; /* Adjust as needed */
  margin: 0.5rem 0;
}

.instructions {
  font-size: 1rem; /* Adjust as needed */
}
</style>
