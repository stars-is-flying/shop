<template>
  <div>
    <nav>
      <button @click="setActiveTab('users')">管理用户</button>
      <button @click="setActiveTab('orders')">管理订单</button>
      <button @click="setActiveTab('messages')">管理用户留言</button>
      <button @click="goToAddProduct">添加商品</button>
    </nav>

    <div v-if="activeTab === 'users'">
      <h2>用户管理</h2>
      <ul>
        <li v-for="user in users" :key="user.id">
          用户名: {{ user.username }} - 用户密码: {{ user.password }}
          <button @click="deleteUser(user.id)">删除</button>
        </li>
      </ul>
    </div>

    <div v-if="activeTab === 'orders'">
      <h2>订单管理</h2>
      <ul>
        <li v-for="order in orders" :key="order.id">
          用户名: {{ order.username }} - 订单价格: {{ order.sum }}
          <button @click="deleteOrder(order.id)">删除</button>
        </li>
      </ul>
    </div>

    <div v-if="activeTab === 'messages'">
      <h2>用户留言管理</h2>
      <ul>
        <li v-for="message in messages" :key="message.id">
          用户名: {{ message.username }} - 内容: {{ message.content }}
          <button @click="deleteMessage(message.id)">删除</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      activeTab: 'users',
      users: [],
      orders: [],
      messages: []
    };
  },
  created() {
    this.fetchUsers();
    this.fetchOrders();
    this.fetchMessages();
  },
  methods: {
    ...mapActions(['goToAddProduct']),
    setActiveTab(tab) {
      this.activeTab = tab;
    },
    fetchUsers() {
      axios.get(`${process.env.VUE_APP_API_URL}/user/list`)
        .then(response => {
          this.users = response.data;
        });
    },
    deleteUser(id) {
      axios.get(`${process.env.VUE_APP_API_URL}/user/delete?id=${id}`)
        .then(response => {
          if (response.data.status === 1) {
            this.users = this.users.filter(user => user.id !== id);
            alert('用户已删除');
          }
        });
    },
    fetchOrders() {
      axios.get(`${process.env.VUE_APP_API_URL}/user/order/list`)
        .then(response => {
          this.orders = response.data;
        });
    },
    deleteOrder(id) {
      axios.get(`${process.env.VUE_APP_API_URL}/user/order/delete?id=${id}`)
        .then(response => {
          if (response.data.status === 1) {
            this.orders = this.orders.filter(order => order.id !== id);
            alert('订单已删除');
          }
        });
    },
    fetchMessages() {
      axios.get(`${process.env.VUE_APP_API_URL}/user/message/list`)
        .then(response => {
          this.messages = response.data;
        });
    },
    deleteMessage(id) {
      axios.get(`${process.env.VUE_APP_API_URL}/user/message/delete?id=${id}`)
        .then(response => {
          if (response.data.status === 1) {
            this.messages = this.messages.filter(message => message.id !== id);
            alert('留言已删除');
          }
        });
    },
    goToAddProduct() {
      this.$router.push('/admin/addproduct');
    }
  }
};
</script>

<style scoped>
nav {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  border-radius: 5px;
}

button:hover {
  background-color: #0056b3;
}

h2 {
  color: #333;
  margin-bottom: 20px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  padding: 10px;
  background-color: #f8f9fa;
  border: 1px solid #dee2e6;
  border-radius: 5px;
}

li button {
  background-color: #dc3545;
}

li button:hover {
  background-color: #c82333;
}
</style>
