// src/axios.js
import axios from 'axios';

// Create an Axios instance with default settings
const axiosInstance = axios.create({
  withCredentials: true,
  // Add any other custom settings here, for example:
  // baseURL: 'https://api.example.com',
});

export default axiosInstance;
