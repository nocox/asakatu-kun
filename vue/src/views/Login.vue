<template>
    <div id="login">
        <div class="login">login</div>
        <form
                id="login-form"
                @submit="checkForm"
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
            checkForm: function (e) {
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
                await axios.post('http://localhost:8080/', this.request);
            },
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/sign_up";
</style>