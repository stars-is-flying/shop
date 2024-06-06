<template>
  <div>
    <h1>Address Management</h1>
    <div v-if="loading">Loading...</div>
    <div v-else>
      <div v-if="address">
        <h2>Your Address:</h2>
        <p>{{ address }}</p>
        <button @click="showUpdateForm = true">Update Address</button>
      </div>
      <div v-else>
        <h2>No Address Found</h2>
        <button @click="showCreateForm = true">Create New Address</button>
      </div>

      <div v-if="showCreateForm">
        <h2>Create Address</h2>
        <form @submit.prevent="createAddress">
          <input v-model="newAddress" placeholder="Enter your address" required>
          <button type="submit">Submit</button>
        </form>
      </div>

      <div v-if="showUpdateForm">
        <h2>Update Address</h2>
        <form @submit.prevent="updateAddress">
          <input v-model="newAddress" placeholder="Enter new address" required>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      address: null,
      loading: true,
      newAddress: '',
      showCreateForm: false,
      showUpdateForm: false,
    };
  },
  created() {
    this.getAddress();
  },
  methods: {
    async getAddress() {
      try {
        const response = await axios.get('/user/addr/get');
        if (response.data.code === 1) {
          this.address = response.data.message;
        } else {
          this.address = null;
        }
      } catch (error) {
        console.error('Error fetching address:', error);
      } finally {
        this.loading = false;
      }
    },
    async createAddress() {
      try {
        const response = await axios.post('/user/addr/new', { address: this.newAddress });
        if (response.data.code === 1) {
          this.address = this.newAddress;
          this.showCreateForm = false;
        } else {
          alert('Error creating address');
        }
      } catch (error) {
        console.error('Error creating address:', error);
      }
    },
    async updateAddress() {
      try {
        const response = await axios.post('/user/addr/ch', { address: this.newAddress });
        if (response.data.code === 1) {
          this.address = this.newAddress;
          this.showUpdateForm = false;
        } else {
          alert('Error updating address');
        }
      } catch (error) {
        console.error('Error updating address:', error);
      }
    }
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
