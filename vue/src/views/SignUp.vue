<template>
    <div id="sign_up">
        <div class="sign_up">sign up</div>
        <form
                id="sign-up-form"
                @submit="checkSignUpForm"
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
                <label for="displayName">displayName</label>
                <input
                        id="displayName"
                        v-model="request.displayName"
                        type="text"
                        name="displayName"
                >
            </p>
            <p>
                <label for="email">Email</label>
                <input
                        id="email"
                        v-model="request.email"
                        type="email"
                        name="email"
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

            <p>
                <label for="passwordConfirm">passwordConfirm</label>
                <input
                        id="passwordConfirm"
                        v-model="request.passwordConfirm"
                        type="password"
                        name="passwordConfirm"
                >
            </p>
            <input
                    type="submit"
                    value="Submit"
                    id="sing_in--submit"
            >

        </form>
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

                if (!this.request.name) {
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
                const axiosResponse = await axios.post('http://localhost:8080/user_registration', this.request, {withCredentials: true});
                axiosResponse
                    .then((response) => {
                            console.log(response);
                            alert("ok");
                        }
                    )
                    .catch((error) => {
                            console.log(error);
                            alert("Error");
                        }
                    )
            },
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
</style>

