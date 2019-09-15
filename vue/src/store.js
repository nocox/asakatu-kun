import Vue from 'vue'
import Vuex from 'vuex'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

const loginState = new Vuex.Store({
  state: {
    isLogin: false,
    username: "",
    userImagePath: "",
    displayName: ""
  },
  mutations: {
    initLogin(state) {
      state.isLogin = false;
      state.username = "";
      state.userImagePath = "";
    },
    getActive(state, activation){
      state.isLogin = activation;
      console.log("getActive")
    },
    getUserName(state, username){
      state.username = username;
      state.isLogin = true;
    },
    getUserImage(state,imagePath){
      state.userImagePath = imagePath;
    },
    getDisplayName(state, displayName){
      state.displayName = displayName
    },
    getUserAllInfo(state,username,imagePath){
      state.username = username;
      state.userImagePath = imagePath;
    }
  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'asakatsukun',
    paths: ['isLogin','username','userImagePath','displayName'],
    storage: window.sessionStorage
  })]
});

export default loginState;
