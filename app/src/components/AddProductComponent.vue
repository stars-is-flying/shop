<template>
    <div class="add-product">
      <h1>Add Product</h1>
      <form @submit.prevent="handleSubmit">
        <div>
          <label for="name">Product Name:</label>
          <input type="text" id="name" v-model="name" required />
        </div>
        <div>
          <label for="description">Product Description:</label>
          <textarea id="description" v-model="description" required></textarea>
        </div>
        <div>
          <label for="price">Product Price:</label>
          <input type="number" id="price" v-model="price" required />
        </div>
        <div>
          <label for="image">Product Image:</label>
          <input type="file" id="image" @change="handleFileChange" accept=".jpg" required />
        </div>
        <button type="submit">Add Product</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        name: '',
        description: '',
        price: '',
        image: null,
      };
    },
    methods: {
      handleFileChange(event) {
        this.image = event.target.files[0];
      },
      async handleSubmit() {
        if (!this.image) {
          alert('Please select an image file');
          return;
        }
  
        // Upload the image
        const formData = new FormData();
        formData.append('file', this.image);
  
        try {
          const imageUploadResponse = await axios.post(`${process.env.VUE_APP_API_URL}/api/upload`, formData, {
            headers: {
              'Content-Type': 'multipart/form-data',
            },
          });
  
          const imageUrl = 'img/' + imageUploadResponse.data.url;

// 提交产品数据
const productData = new FormData();
productData.append('name', this.name);
productData.append('description', this.description);
productData.append('price', this.price);
productData.append('url', imageUrl);

const productResponse = await axios.post(`${process.env.VUE_APP_API_URL}/product/add`, productData, {
  headers: {
    'Content-Type': 'multipart/form-data',
  },
});

if (productResponse.data.status === 1) {
  alert('Product added successfully');
} else {
  alert('Failed to add product');
}
} catch (error) {
console.error('Error uploading image or adding product:', error);
alert('Error uploading image or adding product');
}
},
},
};
  </script>
  
  <style scoped>
  .add-product {
    max-width: 600px;
    margin: 0 auto;
    padding: 1rem;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  form div {
    margin-bottom: 1rem;
  }
  
  label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: bold;
  }
  
  input[type="text"],
  input[type="number"],
  textarea {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  button {
    padding: 0.5rem 1rem;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  