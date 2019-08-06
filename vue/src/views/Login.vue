<template>
    <div id="login">
        <h2 class="login-title">login</h2>
        <form
                id="login-form"
                @submit="checkLoginForm"
                method="post"
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
                errors: []
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
                    this.addUser();
                    return true;
                }
                e.preventDefault();
            },
            addUser: async function () {
                const loginResponse = axios.post('http://localhost:8080/login', this.request, {withCredentials: true});
                await loginResponse
                    .then(function (response) {
                        alert("get login");
                        console.log(response);
                        this.$store.commit('getLogin');
                    })
                    .catch(function (error) {
                        console.log("login is failed");
                        console.log(error);
                        alert("please retry");
                        this.$route.router.go('/login');
                    });
            },
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
</style>