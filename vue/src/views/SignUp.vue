<template>
    <div id="sign_up">
        <main>
            <h2>新規会員登録</h2>
            <p class="linkToLogin"><small><a href="login.html">ログインはこちら</a></small></p>
            <form
                    id="sign-up-form"
                    @submit="checkSignUpForm"
            >
                <!--                todo: バリデーションの処理を書き換える必要がある。-->
                <div class="form-part">
                    <label for="signUp__inputter--email">メールアドレス</label>
                    <input
                            type="email"
                            name="email"
                            v-model="request.email"
                            id="signUp__inputter--email"
                    >
                    <!--                    <p class="cautionMessage"><strong>そのメールアドレスはすでに使われています</strong></p>-->
                </div>

                <div class="form-part">
                    <label for="signUp__inputter--userName">ユーザーID</label>
                    <p class="autionMessage--userIdMessage">
                        <small>
                            半角英字と半角数字の組み合わせで6文字以上、16字以内で登録してください。<br>
                            ユーザーIDは他の人が利用しているものは利用できません。
                        </small>
                    </p>
                    <input
                            type="text"
                            name="username"
                            v-model="request.username"
                            id="signUp__inputter--userName"
                    >
                    <!--                    <p class="cautionMessage"><strong>そのユーザーIDはすでに使われています</strong></p>-->

                </div>
                <div class="form-part">
                    <label for="signUp__inputter--nickname">ニックネーム</label>
                    <p><small>※任意です。後からでも変更できます。</small></p>
                    <input
                            type="text"
                            name="displayName"
                            v-model="request.displayName"
                            id="signUp__inputter--nickname"
                    >
                </div>
                <div class="form-part">
                    <label for="signUp__inputter--password-new">新しいパスワード</label>
                    <p class="autionMessage--passwordMessage"><small>半角英字と半角数字の組み合わせで6文字以上、16字以内で登録してください。</small></p>
                    <input
                            type="password"
                            name="password"
                            v-model="request.password"
                            id="signUp__inputter--password-new">
                </div>
                <div class="form-part">
                    <label for="signUp__inputter--password-confirm">パスワードの再入力</label>
                    <input
                            type="password"
                            name="passwordConfirm"
                            v-model="request.passwordConfirm"
                            id="signUp__inputter--password-confirm">
                    <!--                    <p class="cautionMessage cautionMessage&#45;&#45;confirmPassword"><strong>新しいパスワードと一致しません。</strong></p>-->
                </div>
                <input
                        class="primaryButton"
                        type="submit"
                        name=""
                        value="登録する"
                        id="sing_in--submit"
                >
            </form>
        </main>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "signUp",

        data() {
            return {
                request: {
                    username: undefined,
                    email: undefined,
                    password: undefined,
                    displayName: undefined,
                    passwordConfirm: undefined
                },
                errors: []
            }
        },
        methods: {
            checkSignUpForm: function (e) {
                this.errors = [];

                if (!this.request.username) {
                    this.errors.push("Name required.");
                }
                if (!this.request.email) {
                    this.errors.push('Email required.');
                }
                if (!this.request.password) {
                    this.errors.push('password required.');
                } else if (this.request.password.length < 5) {
                    this.errors.push('password is too short. min 6');
                }

                if (!this.errors.length) {
                    this.createUser();
                    e.preventDefault();
                    return true;
                }
                e.preventDefault();
            },
            createUser: async function () {
                console.log("request");
                console.log(this.request);
                const axiosResponse = axios.post('http://localhost:8080/user_registration',
                    JSON.stringify(this.request),
                    {
                        withCredentials: true,
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });

                axiosResponse.then(response => {
                        console.log(response);
                        // alert("ok");
                        window.location.href = '/events';
                    }
                )
                    .catch(error => {
                            console.log(error);
                            alert("please retry");
                        }
                    )
            },
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
    @import "../assets/css/login-signup";
</style>
