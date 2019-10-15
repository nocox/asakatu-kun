import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
import userInfo from "./modules/user/userInfo";
import eventList from "./modules/event/eventList";
import eventDetails from "./modules/event/eventDetail"

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        userInfo,
        eventList,
        eventDetails
    },
    plugins: [createPersistedState({
        key: 'asakatsukun',
        paths: [
            'userInfo.isLogin',
            'userInfo.userName',
            'userInfo.userImagePath'
        ]
    })]
})
