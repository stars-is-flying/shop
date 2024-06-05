<template>
  <div class="product-wrapper">
    <div class="product-card" v-for="product in products" :key="product.id">
      <img :src="product.url" alt="Product Image" class="product-image" />
      <h3>{{ product.name }}</h3>
      <p>{{ product.description }}</p>
      <p class="price">\${{ product.price }}</p>
      <button @click="addProduct(product.name)" class="add-button">Add</button>
    </div>
    <button @click="navigateToMessage" class="action-button message-button">留言</button>
    <button @click="navigateToCart" class="action-button cart-button">购物车</button>
    <p v-if="cartMessage" class="cart-message">{{ cartMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';
import { useRouter } from 'vue-router';

const instance = axios.create({
  withCredentials: true // 对于跨域请求，设置为true以发送cookie
});

export default {
  name: 'ProductList',
  data() {
    return {
      products: [],
      cartMessage: '' // New state variable for the cart message
    };
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await axios.get(`${process.env.VUE_APP_API_URL}/user/shop`);
        this.products = response.data;
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    },
    async addProduct(productName) {
      try {
        const response = await instance.get(`${process.env.VUE_APP_API_URL}/user/box/add?name=${productName}`);
        if (response.data.status == 1) {
          this.cartMessage = '已加入到购物车'; // Update the message
        } else {
          this.cartMessage = '添加到购物车失败'; // Handle other statuses if necessary
        }
        console.log('Product added:', response.data);
      } catch (error) {
        console.error('Error adding product:', error);
        this.cartMessage = '添加到购物车失败'; // Update the message on error
      }
    },
    navigateToMessage() {
      this.$router.push('/message');
    },
    navigateToCart() {
      this.$router.push('/user/box');
    }
  },
  created() {
    this.fetchProducts();
  },
  setup() {
    const router = useRouter();
    return { router };
  }
};

</script>

<style scoped>
.product-wrapper {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 2rem;
  background-color: #f0f2f5;
}

.product-card {
  background: white;
  padding: 1rem;
  margin: 1rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 250px;
  text-align: center;
}

.product-image {
  max-width: 100%;
  height: auto;
  border-radius: 4px;
}

.product-card h3 {
  margin: 0.5rem 0;
}

.product-card p {
  margin: 0.5rem 0;
}

.price {
  font-weight: bold;
  color: #42b983;
}

.add-button {
  padding: 0.5rem 1rem;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.add-button:hover {
  background-color: #369f79;
}

.action-button {
  display: block;
  margin: 1rem auto;
  padding: 0.5rem 1rem;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.action-button:hover {
  background-color: #369f79;
}

.cart-message {
  text-align: center;
  margin-top: 1rem;
  color: #dc3545;
}
</style>
