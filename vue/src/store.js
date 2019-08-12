import Vue from 'vue'
import Vuex from 'vuex'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

const loginState = new Vuex.Store({
  state: {
    isLogin: false,
    userName: "",
    userImagePath:""
  },
  mutations: {
    initLogin(state) {
      state.islogin = false;
      state.userName = ""
    },
    getActive(state, activation){
      state.islogin = activation;
      console.log("getActive")
    },
    getUserName(state, username){
      state.userName = username;
    },
    getUserImage(state,imagePath){
      state.userImagePath = imagePath;
    },
    getUserAllInfo(state,username,imagePath){
      state.userName = username;
      state.userImagePath = imagePath;
    }
  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'asakatsukun',
    paths: ['isLogin','userName','userImagePath'],
    storage: window.sessionStorage
  })]
});

export default loginState;
