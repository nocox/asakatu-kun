import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const loginState = new Vuex.Store({
  state: {
    isLogin: false,
    userInfo: {}
  },
  mutations: {
    initLogin(state){
      state.loginState = false;
      state.token='';
      state.userInfo={}
    },
    changeToLogin (state, token, userInfo) {
      state.loginState = true;
      state.userInfo = userInfo;
    },

  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'facelook',
    paths: ['token','loginState','userinfo','testcount'],
    storage: window.sessionStorage
  })]
});

export default loginState;