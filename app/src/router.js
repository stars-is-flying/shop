// src/router.js

import { createRouter, createWebHistory } from 'vue-router';
import LoginComponent from './components/LoginComponent.vue';
import RegisterComponent from './components/RegisterComponent.vue';
import ShopComponent from './components/ShopComponent.vue';
import AdminLoginComponent from './components/AdminLoginComponent.vue';
import MessageComponent from './components/MessageComponent.vue'
import BoxListComponent from './components/BoxListComponent.vue'
import AdminPanelComponent from './components/AdminPanelComponent.vue'
import AddProductComponent from './components/AddProductComponent.vue'

const routes = [
  { path: '/', component: LoginComponent },
  { path: '/login', component: LoginComponent },
  { path: '/register', component: RegisterComponent },
  { path: '/shop', component: ShopComponent },
  { path: '/admin', component: AdminLoginComponent },
  { path: '/message', component: MessageComponent },
  { path: '/user/box', component:  BoxListComponent},
  { path: '/admin/manage', component:  AdminPanelComponent},
  { path: '/admin/addproduct', component:  AddProductComponent},
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;