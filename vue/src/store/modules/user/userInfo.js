// todo: yukikoma ここあとでapiまとめる。
// import user from "../../../api/user"
// import login from "../../../api/login"


const state = {
    isLogin: false,
    userName: "",
    userImagePath: ""
};

const mutations = {
    initLogin(state) {
        state.isLogin = false;
        state.userName = "";
        state.userImagePath = "";
    },
    getActive(state, activation) {
        state.isLogin = activation;
        console.log("getActive")
    },
    getUserName(state, username) {
        state.userName = username;
        state.isLogin = true;
    },
    getUserImage(state, imagePath) {
        state.userImagePath = imagePath;
    },
    getUserAllInfo(state, username, imagePath) {
        state.userName = username;
        state.userImagePath = imagePath;
    }
};
const actions = {};

export default {
    namespaced: true,
    state,
    actions,
    mutations
}

