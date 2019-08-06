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
    getActive(state, userInfo){
      state.loginState =true;
      state.userInfo = userInfo;
    },
  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'asakatsukun',
    paths: ['isLogin','userInfo'],
    storage: window.sessionStorage
  })]
});

export default loginState;
