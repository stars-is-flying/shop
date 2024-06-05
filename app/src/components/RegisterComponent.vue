<template>
  <div class="register-wrapper">
    <div class="register-card">
      <h2>Register</h2>
      <form @submit.prevent="handleRegister">
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
        <div class="input-group">
          <span class="input-icon">
            <i class="fas fa-lock"></i>
          </span>
          <input type="password" placeholder="Confirm Password" v-model="confirmPassword" required>
        </div>
        <button type="submit" class="register-button">Register</button>
      </form>
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
      <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RegisterComponent',
  data() {
    return {
      username: '',
      password: '',
      confirmPassword: '',
      errorMessage: '',
      successMessage: ''
    };
  },
  methods: {
    async handleRegister() {
      if (this.password !== this.confirmPassword) {
        this.errorMessage = 'Passwords do not match';
        return;
      }

      try {
        const response = await axios.post(`${process.env.VUE_APP_API_URL}/user/register`, {
          username: this.username,
          password: this.password
        });
        if (response.data.status === 1) {
          // 注册成功，处理用户数据
          console.log('Registration successful:', response.data);
          this.errorMessage = '';
          this.successMessage = 'Registration successful';
          // 跳转到其他页面或执行其他逻辑
        } else {
          // 注册失败
          this.errorMessage = 'Registration failed';
        }
      } catch (error) {
        console.error('Registration error:', error);
        this.errorMessage = 'An error occurred during registration';
      }
    }
  }
};
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.register-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f2f5;
}

.register-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
  text-align: center;
}

.register-card h2 {
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

.register-button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.register-button:hover {
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