<template>
    <div id="sign_up">
        <div class="sign_up">sign up</div>
        <main>
            <h2>新規会員登録</h2>
            <p class="linkToLogin"><small><a href="login.html">ログインはこちら</a></small></p>
            <form
                    id="sign-up-form"
                    @submit="checkSignUpForm">
<!--                todo: バリデーションの処理を書き換える必要がある。-->
                <!--            <div v-if="errors.length">-->
                <!--                <p>Please correct the following error(s):</p>-->
                <!--                <ul>-->
                <!--                    <li v-for="error in errors" :key="error">{{ error }}</li>-->
                <!--                </ul>-->
                <!--            </div>-->

                <div class="form-part">
                    <label for="signUp__inputter--email">メールアドレス</label>
                    <input type="email" value="" id="signUp__inputter--email">
                    <p class="cautionMessage"><strong>そのメールアドレスはすでに使われています</strong></p>
                    <!--            <p>-->
                    <!--                <label for="email">Email</label>-->
                    <!--                <input-->
                    <!--                        id="email"-->
                    <!--                        v-model="request.email"-->
                    <!--                        type="email"-->
                    <!--                        name="email"-->
                    <!--                >-->
                    <!--            </p>-->
                </div>

                <div class="form-part">
                    <label for="signUp__inputter--userName">ユーザーID</label>
                    <p class="autionMessage--userIdMessage">
                        <small>
                            半角英字と半角数字の組み合わせで6文字以上、16字以内で登録してください。<br>
                            ユーザーIDは他の人が利用しているものは利用できません。
                        </small>
                    </p>
                    <input type="text" value="" id="signUp__inputter--userName">
                    <p class="cautionMessage"><strong>そのユーザーIDはすでに使われています</strong></p>

                   <!-- <p>-->
                        <!--                <label for="username">Name</label>-->
                        <!--                <input-->
                        <!--                        id="username"-->
                        <!--                        v-model="request.username"-->
                        <!--                        type="text"-->
                        <!--                        name="username"-->
                        <!--                >-->
                        <!--            </p>-->
                </div>
                <div class="form-part">
                    <label for="signUp__inputter--nickname">ニックネーム</label>
                    <p><small>※任意です。後からでも変更できます。</small></p>
                    <input type="text" value="" id="signUp__inputter--nickname">
                    <!--            <p>-->
                    <!--                <label for="displayName">displayName</label>-->
                    <!--                <input-->
                    <!--                        id="displayName"-->
                    <!--                        v-model="request.displayName"-->
                    <!--                        type="text"-->
                    <!--                        name="displayName"-->
                    <!--                >-->
                    <!--            </p>-->
                </div>
                <div class="form-part">
                    <label for="signUp__inputter--password-new">新しいパスワード</label>
                    <p class="autionMessage--passwordMessage"><small>半角英字と半角数字の組み合わせで6文字以上、16字以内で登録してください。</small></p>
                    <input type="password" value="" id="signUp__inputter--password-new">
                    <!--            <p>-->
                    <!--                <label for="password">Password</label>-->
                    <!--                <input-->
                    <!--                        id="password"-->
                    <!--                        v-model="request.password"-->
                    <!--                        type="password"-->
                    <!--                        name="password"-->
                    <!--                >-->
                    <!--            </p>-->
                </div>
                <div class="form-part">
                    <label for="signUp__inputter--password-confirm">パスワードの再入力</label>
                    <input type="password" value="" id="signUp__inputter--password-confirm">
                    <p class="cautionMessage cautionMessage--confirmPassword"><strong>新しいパスワードと一致しません。</strong></p>
                    <!--            <p>-->
                    <!--                <label for="passwordConfirm">passwordConfirm</label>-->
                    <!--                <input-->
                    <!--                        id="passwordConfirm"-->
                    <!--                        v-model="request.passwordConfirm"-->
                    <!--                        type="password"-->
                    <!--                        name="passwordConfirm"-->
                    <!--                >-->
                    <!--            </p>-->
                </div>
                <input class="primaryButton" type="submit" name="" value="登録する">
                <!--            <input-->
                <!--                    type="submit"-->
                <!--                    value="Submit"-->
                <!--                    id="sing_in&#45;&#45;submit"-->
                <!--            >-->
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
