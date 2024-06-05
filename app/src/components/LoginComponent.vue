<template>
  <div class="login-wrapper">
    <div class="login-card">
      <h2>Login</h2>
      <form @submit.prevent="handleLogin">
        <div class="input-group">
          <span class="input-icon">
            <i class="fas fa-user"></i>
          </span>
          <input type="text" placeholder="Username" v-model="username" required>
        </div>
        <div class="input-group">
          <span class="input-icon">
            <i class="fas fa-lock"></i>
          </span>
          <input type="password" placeholder="Password" v-model="password" required>
        </div>
        <button type="submit" class="login-button">Login</button>
      </form>
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
      <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
      <button @click="navigateToRegister" class="register-button">Register</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { useRouter } from 'vue-router';
import Cookies from 'js-cookie';

export default {
  name: 'LoginComponent',
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
      successMessage: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post(`${process.env.VUE_APP_API_URL}/user/login`, {
          username: this.username,
          password: this.password
        });
        if (response.data.status === 1) {
          // Login successful
          console.log('Login successful:', response.data);
          this.errorMessage = '';
          this.successMessage = 'Login successful!';
          // Set the cookie
          Cookies.set('username', this.username, { expires: 7 }); // 1 week
          // Redirect to the shop page
          this.$router.push('/shop');
        } else {
          // Login failed
          this.errorMessage = 'Invalid username or password';
          this.successMessage = '';
        }
      } catch (error) {
        console.error('Login error:', error);
        this.errorMessage = 'An error occurred during login';
        this.successMessage = '';
      }
    },
    navigateToRegister() {
      this.$router.push('/register');
    }
  },
  setup() {
    const router = useRouter();
    return { router };
  }
};
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f2f5;
}

.login-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
  text-align: center;
}

.login-card h2 {
  margin-bottom: 1.5rem;
}

.input-group {
  position: relative;
  margin-bottom: 1rem;
}

.input-icon {
  position: absolute;
  top: 50%;
  left: 10px;
  transform: translateY(-50%);
  color: #aaa;
}

input {
  width: 100%;
  padding: 10px 10px 10px 40px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.3s;
}

input:focus {
  border-color: #42b983;
  outline: none;
}

.login-button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover {
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

.register-button {
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

.register-button:hover {
  background-color: #369f79;
}
</style>
