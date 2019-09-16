<template>
    <div id="login">
        <!--        <h2 class="login-title">login</h2>-->
        <!--        <div v-if="userName">-->
        <!--            <h3>{{this.userName}}</h3>-->
        <!--        </div>-->

        <main>
            <h2>ログイン</h2>

            <form
                    id="login-form"
                    @submit="checkLoginForm"
            >
                <!-- todo:fix validation-->

                <div class="form-part">
                    <label for="login__inputter--userName">ユーザーID</label>
                    <input
                            type="text"
                            name="name"
                            id="login__inputter--userName"
                            v-model="request.name"
                    >
                </div>

                <div class="form-part">
                    <label for="login__inputter--password">パスワード</label>
                    <input
                            type="password"
                            name="password"
                            v-model="request.password"
                            id="login__inputter--password"
                    >
                    <p hidden>パスワードをお忘れの場合はこちら</p>
                </div>

                <p v-if="hasError"><strong>メールアドレスまたはパスワードが違います</strong></p>

                <input
                        class="primaryButton"
                        type="submit"
                        id="login--submit"
                        value="ログイン">
            </form>
            <p class="secondaryButton"><a href="signUp.html">アカウントを新規作成</a></p>
        </main>
    </div>
</template>

<script>

    import axios from 'axios'

    export default {
        name: "login",

        data() {
            return {
                request: {
                    name: undefined,
                    password: undefined
                },
                hasError: false,
                userName: this.$store.state.userName,
                ERRORMESSAGE: USER_REGISTRATION_ERROR
            }
        },
        methods: {
            checkLoginForm: function (e) {
                this.errors = [];
                this.hasError = false;

                if (!this.request.name) {
                    this.errors.push("Name required.");
                }
                if (!this.request.password) {
                    this.errors.push('password required.');
                }
                if (!this.errors.length) {
                    this.getLogin();
                    e.preventDefault();
                    return true;
                }
                e.preventDefault();
            },
            getLogin: async function () {
                const params = new URLSearchParams();
                params.append('username', this.request.name);
                params.append('password', this.request.password);
                console.log(this.request);
                const loginResponse = axios.post('http://localhost:8080/login', params, {withCredentials: true});
                await loginResponse
                    .then(response => {
                        alert("get login");
                        console.log(response);
                        console.log(this.$store.state.isLogin);
                        this.$store.commit('getActive', true);
                        console.log(this.$store.state.isLogin);
                        alert("ok");
                        this.whoami();
                        this.$router.push('/mypage');
                        // window.location.href = '/'
                    })
                    .catch(error => {
                        console.log("login is failed");
                        console.log(error);
                        this.hasError = true;
                        //todo: とりあえずエラー帰ってきたら間違ってますよ。と表示しているので、ステータスによって分岐する処理を書きたい。
                    });

            },
            whoami: function () {
                const userNameResponse = axios.get('http://localhost:8080/login_user', {withCredentials: true});
                userNameResponse.then(response => {
                        this.$store.commit('getUserName', response.data.data.displayName);
                    }
                );
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
    @import "../assets/css/login-signup";

</style>

