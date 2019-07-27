import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const loginStore = new Vuex.Store({
  state: {
    loginState:false,
    token:'',
    userInfo:{},
  },
  mutations: {
    initLogin(state){
      state.loginState = false;
      state.token='';
      state.userInfo={}
    },
    create (state, token, userInfo) {
      state.loginState = true;
      state.token = token;
      state.userInfo = userInfo;
    },
    setToken(state,token){
      state.token = token;
    },
    getActive(state, userInfo){
      state.loginState =true;
      state.userInfo = userInfo;
    }

  },
  actions: {

  },
  plugins: [createPersistedState({
    key: 'facelook',
    paths: ['token','loginState','userinfo','testcount'],
    storage: window.sessionStorage
  })]
})

export default loginStore;
