import { createRouter, createWebHistory } from 'vue-router';
import MyDashboard from '@/components/MyDashboard.vue'; // MyDashboard componentini import edin
import ProductManagement from '@/components/ProductManagement.vue'; // Örnek olarak diğer componentleri import edin
import SaleManagement from '@/components/SaleManagement.vue'; // Örnek olarak diğer componentleri import edin
import FeedbackForm from '@/components/FeedbackForm.vue'; // Örnek olarak diğer componentleri import edin


const routes = [
  {
    path: '/',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard,
    children: [
      {
        path: 'product-management',
        name: 'ProductManagement',
        component: ProductManagement
      },
      {
        path: 'sale-management',
        name: 'SaleManagement',
        component: SaleManagement
      },
      {
        path: 'feedback-form',
        name: 'FeedbackForm',
        component: FeedbackForm
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
