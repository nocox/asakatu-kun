<template>
    <div class="header">
        <a class="header__backBtn" @click="back" v-bind:class="{ hidden: typeof this.before === 'undefined' }"></a>
        <router-link to="/events" class="header__profileImg">
            <h1 class="header__logo"><img alt="BizSpot" src="../../static/logo.png"></h1>
        </router-link>
        <figure class="header__profileImg" v-bind:class="{hidden : !checkExistImagePath}">
            <!--router-linkに余計なスタイルがついているので、一旦同じclassつけてます。-->
            <router-link to="/mypage" class="header__profileImg">
                <img alt="user profile image"
                     v-bind:src="this.userImagePath">
            </router-link>
        </figure>

    </div>
</template>

<script>
    export default {
        name: 'Header',
        props: {
            msg: String,
            before: String,
        },
        computed: {
            checkExistImagePath() {
                return !!this.userImagePath;
            },
            userImagePath() {
                return this.$store.state.userImagePath;
            }
        },
        methods: {
          back: function () {
            this.$router.push(`${this.before}`)
          }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    //reset CSS fot UIkit
    figure {
        margin-right: 10vw;
    }

    .hidden {
        visibility: collapse;
    }

    .header {
        display: flex;
        width: 100%;
        height: 72px;
        background: #FFF;
        z-index: 100000;
        padding: 0 16px;
        align-items: center;
        justify-content: space-between;
        filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 0.4));

        &__backBtn {
            position: absolute;

            &::before {
                content: "";
                display: block;
                height: 3px;
                width: 16px;
                background: #707070;
                transform: rotate(-45deg);
                position: absolute;
                top: -5px;
                left: -25%;
            }

            &::after {
                content: "";
                display: block;
                height: 3px;
                width: 16px;
                background: #707070;
                transform: rotate(45deg);
                position: absolute;
                top: 5px;
                left: -25%;
            }
        }

        &__logo {
            height: 40px;

            > img {
                height: 100%;
                margin: 0px 34vw;
            }
        }

        &__profileImg {
            height: 34px;
            width: 34px;

            > img {
                height: 100%;
                width: 100%;
                object-fit: cover;
                border-radius: 50%;
            }
        }
    }

</style>
