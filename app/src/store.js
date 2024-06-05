// src/store.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {
    goToAddProduct({ commit }, router) {
      router.push('/admin/addproduct');
    },
  },
});
