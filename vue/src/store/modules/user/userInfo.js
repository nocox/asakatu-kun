import Vuex from "vuex";
// todo: yukikoma ここあとでapiまとめる。
// import user from "../../../api/user"
// import login from "../../../api/login"

const userInfo = new Vuex.Store({
    state: {
        isLogin: false,
        userName: "",
        userImagePath:""
    },
    mutations: {
        initLogin(state) {
            state.isLogin = false;
            state.userName = "";
            state.userImagePath = "";
        },
        getActive(state, activation){
            state.isLogin = activation;
            console.log("getActive")
        },
        getUserName(state, username){
            state.userName = username;
            state.isLogin = true;
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
});

export default userInfo;
