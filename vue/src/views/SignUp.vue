<template>
    <div id="sign_up">
      <Header/>
        <main>
            <h2>新規会員登録</h2>
            <p class="linkToLogin"><small>
                <router-link to="/login">ログインはこちら</router-link>
            </small></p>

            <form
                    id="sign-up-form"
                    @submit="checkSignUpForm"
            >
                <div class="form-part">
                    <label for="signUp__inputter--email">メールアドレス</label>
                    <input
                            type="email"
                            name="email"
                            v-model="request.email"
                            id="signUp__inputter--email"
                    >
                    <p
                            class="cautionMessage"
                            v-if="responseError.mailAddressAlreadyUsed"
                    >
                        <strong>そのメールアドレスはすでに使われています</strong>

                    <p
                            class="cautionMessage"
                            v-if="inputErrors.mailAddressIsRequired"
                    >
                        <strong>メールアドレスの入力は必須です</strong>
                    </p>
                    <p
                            class="cautionMessage"
                            v-if="inputErrors.mailAddressFormatError"
                    >
                        <strong>メールアドレスの形式が不正です。</strong>
                    </p>
                </div>

                <div class="form-part">
                    <label for="signUp__inputter--userName">ユーザーID</label>
                    <p class="cautionMessage--userIdMessage">
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
                    <p
                            class="cautionMessage"
                            v-if="responseError.userNameAlreadyUsed"
                    >
                        <strong>そのユーザーIDはすでに使われています</strong>
                    </p>
                    <p
                            class="cautionMessage"
                            v-if="responseError.userNameLengthError"
                    >
                        <strong>そのユーザーIDの長さが違います</strong>
                    </p>
                    <p
                            class="cautionMessage"
                            v-if="inputErrors.userNameIsRequired"
                    >
                        <strong>ユーザーIDは必須です。</strong>
                    </p>

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
                    <p class="cautionMessage--passwordMessage"><small>半角英字と半角数字の組み合わせで6文字以上、16字以内で登録してください。</small></p>
                    <!--                    todo: ここの判定ないや-->
                    <input
                            type="password"
                            name="password"
                            v-model="request.password"
                            id="signUp__inputter--password-new">
                </div>
                <p
                        class="cautionMessage"
                        v-if="responseError.passwordLengthError || inputErrors.passwordLengthError"
                >
                    <strong>パスワードの長さが違います</strong>
                </p>
                <p
                        class="cautionMessage"
                        v-if="inputErrors.passwordIsRequired"
                >
                    <strong>パスワードは必須です。</strong>
                </p>
                <div class="form-part">
                    <label for="signUp__inputter--password-confirm">パスワードの再入力</label>
                    <input
                            type="password"
                            name="passwordConfirm"
                            v-model="request.passwordConfirm"
                            id="signUp__inputter--password-confirm">
                    <p
                            class="cautionMessage cautionMessage--confirmPassword"
                            v-if="responseError.passwordConfirmError || inputErrors.passwordIsNotSame"
                    >
                        <strong>新しいパスワードと一致しません。</strong>
                    </p>
                    <p
                            class="cautionMessage"
                            v-if="inputErrors.passwordConfIsRequired"
                    >
                        <strong>パスワードは必須です。</strong>
                    </p>
                </div>

                <input
                        class="primaryButton"
                        type="submit"
                        name="user_registration"
                        value="登録する"
                        id="sing_in--submit"
                >
            </form>
        </main>
    </div>
</template>

<script>
    import user from "../api/user";
    import {USER_REGISTRATION_ERROR} from "../models/USER_REGISTRATION_ERROR";
    import Header from '@/components/common/Header.vue';


    export default {
        name: "signUp",
        components: {
          Header
        },
        data() {
            return {
                request: {
                    username: undefined,
                    email: undefined,
                    password: undefined,
                    displayName: undefined,
                    passwordConfirm: undefined
                },
                responseError: {
                    mailAddressAlreadyUsed: false,
                    userNameLengthError: false,
                    userNameAlreadyUsed: false,
                    passwordLengthError: false,
                    passwordConfirmError: false
                },
                inputErrors: {
                    idIsRequired: false,
                    mailAddressIsRequired: false,
                    userNameIsRequired: false,
                    passwordIsRequired: false,
                    passwordConfIsRequired: false,
                    passwordIsNotSame: false,
                    passwordLengthError: false,
                    userNameLengthError: false,
                    mailAddressFormatError: false
                    //todo: どこまでフロントでもバリデーションを書くか。
                },
            }
        },
        methods: {
            checkSignUpForm: function (e) {
                e.preventDefault();
                //init errors
                this.responseError = {
                    mailAddressAlreadyUsed: false,
                    userNameLengthError: false,
                    userNameAlreadyUsed: false,
                    passwordLengthError: false,
                    passwordConfirmError: false
                };
                this.inputErrors = {
                    idIsRequired: false,
                    mailAddressIsRequired: false,
                    userNameIsRequired: false,
                    passwordIsRequired: false,
                    passwordConfIsRequired: false,
                    passwordIsNotSame: false,
                    passwordLengthError: false,
                    userNameLengthError: false,
                    mailAddressFormatError: false
                };

                if (!this.request.username) {
                    this.inputErrors.userNameIsRequired = true;
                }
                if (!this.request.email) {
                    this.inputErrors.mailAddressIsRequired = true;
                }

                if (this.request.email && !this.request.email.match(/^[\!\#\$\%\&\'\*\+\\\-\.\/\=\?\^\_\`\{\|\}\~\[\]0-9a-zA-Z]+@[a-z0-9-_]+(\.[a-z0-9-_]+)+$/)) {
                    this.inputErrors.mailAddressFormatError = true;
                }

                if (!this.request.password) {
                    this.inputErrors.passwordIsRequired = true;
                } else if (this.request.password.length < 5) {
                    this.inputErrors.passwordLengthError = true;
                }
                if (!this.request.passwordConfirm) {
                    this.inputErrors.passwordConfIsRequired = true;
                }
                if (this.request.password !== this.request.passwordConfirm) {
                    this.inputErrors.passwordIsNotSame = true;
                }
                if (!(convertToArray(this.inputErrors).indexOf(true) > 0)) {
                    user.createUser(this.request).then(() => {
                        this.$router.push('/events');
                    }).catch(error => {
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
                    });
                }else{
                    alert("please retry");
                }

                function convertToArray(hash) {
                    const hashObject = Object.assign({}, hash);
                    let convertArray = [];
                    for (let key in hashObject) {
                        if (hashObject.hasOwnProperty(key)) {
                            convertArray.push(hashObject[key]);
                        }
                    }
                    return convertArray;
                }
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
    @import "../assets/css/login-signup";
    @import "../assets/css/button";
</style>
