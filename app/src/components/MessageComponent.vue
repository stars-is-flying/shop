<template>
    <div class="message-form-wrapper">
      <h2>Add Message</h2>
      <form @submit.prevent="handleSubmit">
        <div class="input-group">
          <input type="text" placeholder="Type your message here" v-model="message" required>
        </div>
        <button type="submit" class="submit-button">Submit</button>
      </form>
      <button @click="navigateToHome" class="home-button">Home</button>
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
      <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
import { useRouter } from 'vue-router';

export default {
  name: 'MessageFormComponent',
  data() {
    return {
      message: '',
      errorMessage: '',
      successMessage: ''
    };
  },
  methods: {
    async handleSubmit() {
      if (!this.message) {
        this.errorMessage = 'Please enter a message';
        this.successMessage = '';
        return;
      }

      try {
        const response = await axios.post(`${process.env.VUE_APP_API_URL}/user/message/add`, {
          message: this.message
        }, {
          withCredentials: true // Keep this line
        });

        if (response.data.status === 1) {
          this.message = '';
          this.errorMessage = '';
          this.successMessage = 'Message added successfully!';
        } else {
          this.errorMessage = 'Failed to add message';
          this.successMessage = '';
        }
      } catch (error) {
        console.error('Error adding message:', error);
        this.errorMessage = 'An error occurred while adding the message';
        this.successMessage = '';
      }
    },
    navigateToHome() {
      this.$router.push('/shop'); // Assuming '/' is the home route
    }
  },
  setup() {
    const router = useRouter();
    return { router };
  }
};
  </script>
  
  <style scoped>
  .message-form-wrapper {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f0f2f5;
  }
  
  .input-group {
    margin-bottom: 1rem;
  }
  
  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
    transition: border-color 0.3s;
  }
  
  input:focus {
    border-color: #42b983;
    outline: none;
  }
  
  .submit-button {
    width: 100%;
    padding: 10px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .submit-button:hover {
    background-color: #369f79;
  }
  
  .home-button {
    display: block;
    width: 100%;
    padding: 10px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-top: 1rem;
  }
  
  .home-button:hover {
    background-color: #369f79;
  }
  
  .error-message {
    color: red;
    margin-top: 1rem;
  }
  
  .success-message {
    color: green;
    margin-top: 1rem;
  }
  </style>