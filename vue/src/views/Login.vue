<template>
    <div id="login">
        <h2 class="login-title">login</h2>
        <form
                id="login-form"
                @submit="checkLoginForm"
        >
            <div v-if="errors.length">
                <p>Please correct the following error(s):</p>
                <ul>
                    <li v-for="error in errors" :key="error">{{ error }}</li>
                </ul>
            </div>
            <p>
                <label for="name">Name</label>
                <input
                        id="name"
                        v-model="request.name"
                        type="text"
                        name="name"
                >
            </p>
            <p>
                <label for="password">Password</label>
                <input
                        id="password"
                        v-model="request.password"
                        type="password"
                        name="password"
                >
            </p>
            <input
                    type="submit"
                    value="Submit"
                    id="login--submit"
            >
        </form>
        <div v-if="userName">
            <h3>{{this.userName}}</h3>
        </div>

        <main>
            <h2>ログイン</h2>
            <!-- TODO: actionの設定 -->
            <form action="" method="post">
                <div class="form-part">
                    <label for="login__inputter--userName">ユーザーID</label>
                    <input type="text" id="login__inputter--userName">
                </div>

                <div class="form-part">
                    <label for="login__inputter--password">パスワード</label>
                    <input type="password" id="login__inputter--password">
                    <p hidden>パスワードをお忘れの場合はこちら</p>
                </div>

                <p hidden><strong>メールアドレスまたはパスワードが違います</strong></p>

                <input class="primaryButton" type="submit" value="ログイン">
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
                errors: [],
                // userName: ""
                userName: this.$store.state.userName
            }
        },
        methods: {
            checkLoginForm: function (e) {
                this.errors = [];

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
                var params = new URLSearchParams();
                params.append('username', this.request.name);
                params.append('password', this.request.password);
                console.log(this.request);
                const loginResponse = axios.post('http://localhost:8080/login', params, {withCredentials: true});
                await loginResponse
                    .then(response => {
                        alert("get login");
                        console.log(response);
                        console.log(this.$store.state.isLogin);
                        this.$store.commit('getActive',true);
                        console.log(this.$store.state.isLogin);
                        alert("ok");
                        window.location.href='/mypage'
                    })
                    .catch(error => {
                        console.log("login is failed");
                        console.log(error);
                        alert("please retry");
                        // this.$route.router.go('/login');
                    });
                this.whoami();

            },
            whoami: function () {
                const userNameResponse = axios.get('http://localhost:8080/login_user', {withCredentials: true});
                console.log(userNameResponse);
                // const getUsername = userNameResponse.data.displayName;

                userNameResponse.then(response => {
                        console.log("in then");
                        console.log(response.data);
                        console.log(response.data.data);
                        console.log(response.data.data.displayName);
                        // this.userName = response.data.data.displayName;
                        // console.log("name--");
                        // console.log(this.userName);
                        this.$store.commit('getUserName', response.data.data.displayName);
                    }
                );
                // const getUsername = userNameResponse.data;
                //it also conclude name, imagepath  and so on.

                // this.userName = getUsername;
                // console.log("check username");
                // console.log(getUsername);
                // console.log(this.userName);
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
    @import "../assets/css/login-signup";

</style>

<!--curl -X POST -H "Content-Type: application/json" -d '{"username":"ito", "password":"aabbcc", "displayName":"itoFumiki", "email":"aaaaaa@bbbb.com", "passwordConfirm":"aabbcc"}' -i localhost:8080/user_registration-->