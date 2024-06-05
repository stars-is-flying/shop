<template>
  <div class="cart-wrapper">
    <h2>购物车</h2>
    <div v-if="cartItems.length === 0">
      <p>购物车为空</p>
    </div>
    <div v-else>
      <div v-for="item in cartItems" :key="item.id">
        <div class="cart-item">
          <div>
            <span>商品名称: {{ item.name }}</span>
            <span>价格: ${{ item.price }}</span>
          </div>
          <button @click="deleteItem(item.id)">删除</button>
        </div>
      </div>
      <div class="total-price">
        <span>总价格: ${{ totalPrice }}</span>
      </div>
      <button @click="submitOrder">购买</button>
    </div>
    <button @click="navigateToHome">Home</button>
  </div>
</template>

<script>
import axios from 'axios';

const instance = axios.create({
  withCredentials: true // 对于跨域请求，设置为true以发送cookie
});

export default {
  name: 'cart-list',
  data() {
    return {
      cartItems: [],
      totalPrice: 0
    };
  },
  methods: {
    async fetchCartItems() {
      try {
        const response = await instance.get(`${process.env.VUE_APP_API_URL}/user/box/list`);
        this.cartItems = response.data;
        this.calculateTotalPrice();
      } catch (error) {
        console.error('Error fetching cart items:', error);
      }
    },
    calculateTotalPrice() {
      this.totalPrice = this.cartItems.reduce((total, item) => total + item.price, 0);
    },
    async deleteItem(id) {
      try {
        const response = await instance.get(`${process.env.VUE_APP_API_URL}/user/box/delete?id=${id}`);
        if (response.data.status === 1) {
          this.fetchCartItems(); // 刷新购物车
          alert('商品已删除');
        } else {
          alert('删除商品失败');
        }
      } catch (error) {
        console.error('Error deleting item:', error);
        alert('删除商品失败');
      }
    },
    async submitOrder() {
      try {
        const response = await instance.get(`${process.env.VUE_APP_API_URL}/user/order/submit?sum=${this.totalPrice}`);
        if (response.data.status === 1) {
          alert('购买成功');
          this.clearCart();
        } else {
          alert('购买失败');
        }
      } catch (error) {
        console.error('Error submitting order:', error);
        alert('购买失败');
      }
    },
    async clearCart() {
      try {
        const response = await instance.get(`${process.env.VUE_APP_API_URL}/user/box/deleteall`);
        if (response.data.status === 1) {
          this.fetchCartItems(); // 刷新购物车
        } else {
          alert('清空购物车成功');
          this.fetchCartItems();
        }
      } catch (error) {
        console.error('Error clearing cart:', error);
        alert('清空购物车失败');
      }
    },
    navigateToHome() {
      this.$router.push('/shop');
    }
  },
  created() {
    this.fetchCartItems();
  }
};
</script>

<style scoped>
.cart-wrapper {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}

.cart-item button {
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  background-color: #dc3545;
  color: #fff;
  cursor: pointer;
}

.total-price {
  margin-top: 20px;
  font-weight: bold;
  text-align: right;
}

button {
  margin-top: 20px;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
