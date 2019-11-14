<template>
    <div id="login">
        <main>
            <h2>ログイン</h2>

            <form
                    id="login-form"
                    @submit="checkLoginForm"
            >
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
                <p v-if="errors.length > 0"><strong>メールアドレス,パスワードは必須です。</strong></p>

                <input
                        class="primaryButton"
                        type="submit"
                        id="login--submit"
                        value="ログイン">
            </form>
            <p class="secondaryButton">
                <router-link to="/sign_up">アカウントを新規作成</router-link>
            </p>
        </main>
    </div>
</template>

<script>

    import {mapActions, mapState} from "vuex";

    export default {
        name: "login",

        data() {
            return {
                request: {
                    name: undefined,
                    password: undefined
                },
                hasError: false,
                errors: []
            }
        },
        computed: {
            ...mapState({
                userName: state => state.userInfo.userName
            })
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
                    this.hasError = this.getLogin(this.request);
                    e.preventDefault();
                    return true;
                }
                e.preventDefault();
            },
            ...mapActions('userInfo', [
                'getLogin'
            ])
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
    @import "../assets/css/login-signup";
    @import "../assets/css/button"; 
</style>
