import Vue from 'vue'
import Vuex from 'vuex'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

const loginState = new Vuex.Store({
  state: {
    isLogin: false,
    userName: ""
  },
  mutations: {
    initLogin(state) {
      state.loginState = false;
      state.userName = ""
    },
    getActive(state){
      state.loginState = true;
    },
    getUserName(state, username){
      state.userName = username;
    },
  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'asakatsukun',
    paths: ['isLogin','userName'],
    storage: window.sessionStorage
  })]
});

export default loginState;
