<template>
    <section class="mypage">
        <div class="user-info">
            <h2 class="user-info__name">{{userName}}</h2>
            <img class="user-info__image" :src="userImage" alt="user image ">
        </div>
    </section>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        name: "MyPage",
        data() {
            return {
                apiURL:process.env.VUE_APP_API_URL_BASE
            }
        },
        created: function () {
            this.setUserImage();
        },
        methods: {
           ...mapActions([
               'setUserImage'
           ])
        },
        computed: {
            ...mapState({
                userName: state => state.userInfo.userName,
                userImage: state => state.userInfo.userImagePath
            }),
        }
    }
</script>

<style scoped lang="scss">

</style>
