import user from "../../../api/user"
import {USER_REGISTRATION_ERROR} from "../../../models/USER_REGISTRATION_ERROR";

const state = {
    isLogin: false,
    userName: "",
    userImagePath: "",
    displayName: "",
    email: ""
};

const mutations = {
    initLogin(state) {
        state.isLogin = false;
        state.userName = "";
        state.userImagePath = "";
        state.displayName = "";
        state.email = "";
    },
    getActive(state, activation) {
        state.isLogin = activation;
    },
    getUserName(state, username) {
        state.userName = username;
        state.isLogin = true;
    },
    setUserImage(state, imagePath) {
        state.userImagePath = imagePath;
    },
    whoAmI(state, user) {
        state.displayName = user.displayName;
        state.email = user.email;
    },
    getUserAllInfo(state, username, imagePath) {
        state.userName = username;
        state.userImagePath = imagePath;
    }
};
const actions = {
    setUserImage({commit}) {
        user.setUserImage()
            .then(response => {
                const userImagePath = response.data.data.imagePath;
                commit('setUserImage', userImagePath)
            })
            .catch(error => {
                alert('サーバからのデータ取得に失敗しました');
                // TODO nocox エラーハンドリングが必要かも (2019/10/02)
            })
    },
    whoAmI({commit}) {
        user.whoAmI()
            .then(response => {
                const user = response.data.data;
                commit('whoAmI', user)
            })
            .catch(error => {
                alert('サーバからのデータ取得に失敗しました');
                // TODO nocox エラーハンドリングが必要かも (2019/10/02)
            })
    },
    editDisplayName({commit}, request) {
        console.log(request)
        const params = new URLSearchParams();
        params.append('displayName', request.displayName);
        user.editDisplayName(params)
            .then(response => {
                const user = response.data.data;
                commit('whoAmI', user)
            })
            .catch(error => {
                console.log(error)
                alert('名前の更新処理に失敗しました');
                // TODO nocox エラーハンドリングが必要かも (2019/10/02)
            })
    },
    editImage({commit}) {
        user.editImage()
            .then(response => {
                const user = response.data.data;
                commit('whoAmI', user)
            })
            .catch(error => {
                alert('名前の更新処理に失敗しました');
                // TODO nocox エラーハンドリングが必要かも (2019/10/02)
            })
    },
    getLogin({commit}, request) {
        const params = new URLSearchParams();
        params.append('username', request.name);
        params.append('password', request.password);
        return user.getLogin(params)
            .then(() => {
                    commit('getActive', true);
                    return user.whoAmI()
                        .then((response) => {
                            commit('getUserName', response.data.data.username);
                            commit('setUserImage', response.data.data.imagePath);
                            return false;
                        })
                        .catch(error => {
                            return true;
                        });
                }
            )
            .catch(
                error => {
                    return true;
                    // todo: とりあえずエラー帰ってきたら間違ってますよ。と表示しているので、ステータスによって分岐する処理を書きたい。
                })


    },
    createUser(commit, request) {
        user.createUser(request)
            .then(() => {
                this.$router.push('/events');
            })
            .catch(error => {
                    if (error.response.data.data.message === USER_REGISTRATION_ERROR.USER_NAME_LENGTH_ERROR.name) {
                        this.responseError.userNameLengthError = true;
                        //本当はsetUserNameLengthErrorとかメソットでtrue,false切り替える感じの事ができればいいなーとおもってやってる
                    }
                    if (error.response.data.data.message === USER_REGISTRATION_ERROR.USER_NAME_ALREADY_USED.name) {
                        this.responseError.userNameAlreadyUsed = true;
                    }
                    if (error.response.data.data.message === USER_REGISTRATION_ERROR.PASSWORD_LENGTH_ERROR.name) {
                        this.responseError.passwordLengthError = true;
                    }
                    if (error.response.data.data.message === USER_REGISTRATION_ERROR.INCORRECT_PASSWORD.name) {
                        this.responseError.passwordConfirmError = true;
                    }
                    if (error.response.data.data.message === USER_REGISTRATION_ERROR.MAIL_ADDRESS_ALREADY_USED.name) {
                        this.responseError.mailAddressAlreadyUsed = true;
                    }
                    alert("please retry");
                }
            )
    },
};

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
