import Vue from 'vue'
import Vuex from 'vuex'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

const loginState = new Vuex.Store({
  state: {
    isLogin: false,
    userInfo: {}
  },
  mutations: {
    initLogin(state){
      state.loginState = false;
      state.userInfo={}
    },
    getLogin (state) {
      state.loginState = true;
    }
  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'asakatsukun',
    paths: ['loginState','userInfo'],
    storage: window.sessionStorage
  })]
});

export default loginState;
