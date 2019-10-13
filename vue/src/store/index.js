import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
import userInfo from "./modules/user/userInfo";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        userInfo
    },
    plugins: [createPersistedState({
        key: 'asakatsukun',
        paths: ['isLogin', 'userName', 'userImagePath']
    })]
})
