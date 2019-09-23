<template>
    <div id="sign_up">
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
                    //todo: どこまでフロントでもバリデーションを書くか。
                },
            }
        },
        methods: {
            checkSignUpForm: function (e) {
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
                };

                if (!this.request.username) {
                    this.inputErrors.userNameIsRequired = true;
                }
                if (!this.request.email) {
                    this.inputErrors.mailAddressIsRequired = true;
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
                    this.createUser();
                    e.preventDefault();
                }
                alert("please retry");
                e.preventDefault();

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

                axiosResponse
                    .then(response => {
                            console.log(response);
                            window.location.href = '/events';
                        }
                    )
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
        }
    }

    export const USER_REGISTRATION_ERROR = {
        //todo: vue jsでenum使うの案外面倒だったので、TSにしたいなーーーーと思った。
        //ここのメッセージはこういう制約あったわ。って思い出せるように。ユーザーに見せてもよい想定で書きます。
        //response をstringで返すことにしたので、一旦細かいことはきにしなくてもOK
        USER_NAME_LENGTH_ERROR: {
            eVal: "1",
            text: "ユーザー名の長さは3文字以上50文字以下で入力してください。",
            name: "USER_NAME_LENGTH_ERROR"
        },
        USER_NAME_ALREADY_USED: {
            eVal: "2",
            text: "ユーザー名はすでに使われています。",
            name: "USER_NAME_ALREADY_USED"
        },
        PASSWORD_LENGTH_ERROR: {
            eVal: "3",
            text: "パスワードは6文字以上20文字以下で入力してください",
            name: "PASSWORD_LENGTH_ERROR"
        },
        INCORRECT_PASSWORD: {
            eVal: "5",
            text: "パスワードまたはユーザー名が違います。",
            name: "INCORRECT_PASSWORD"
        },
        MAIL_ADDRESS_ALREADY_USED: {
            eVal: "7",
            text: "メールアドレスはすでに使用されています。",
            name: "MAIL_ADDRESS_ALREADY_USED"
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
    @import "../assets/css/login-signup";
</style>
