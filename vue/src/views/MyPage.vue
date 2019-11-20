<template>
    <section class="mypage">
        <Header
          before = "/events"
        />
        <div class="user-info">
            <h2 class="user-info__name">{{userName}}</h2>
            <img class="user-info__image" :src="userImage" alt="user image ">
        </div>
    </section>
</template>


<script>
    import {mapActions, mapState} from 'vuex'
    import Header from '@/components/common/Header.vue';

    export default {
        name: "MyPage",
        components: {
          Header
        },
        created: function () {
            this.setUserImage();
        },
        methods: {
           ...mapActions('userInfo',[
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
