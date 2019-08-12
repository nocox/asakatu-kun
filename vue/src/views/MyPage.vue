<template>
    <section class="mypage">
        <div class="user-info">
            <img class="user-info__image" src="{{this.userInfo.userImage}}" alt="user image ">
            <h2 class="user-info__name">{{this.userInfo.userName}}</h2>
        </div>
    </section>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "MyPage",
        data(){
            return{
                userInfo:{
                    userName: this.$store.state.username,
                    userImage: this.$store.state.userImagePath
                }
            }
        },
        created: function () {
            this.getUserImage();
            console.log("get user image function is completed");
        },
        methods:{
            //todo ここloginに持たせればいらなくなるかも
            getUserImage:　function () {
                const getUserInfo = axios.get('http/:/localhost:8080/login_user');
                getUserInfo.then(response=>{
                    console.log("ok");
                    console.log(response);
                    //todo ここのデータの撮り方確認しないとあかん
                    const userImagePath = response.data.data.imagePath;
                    console.log("userImagePath");
                    console.log(userImagePath);
                    this.$store.commit('getUserImage',　userImagePath);
                })
                    .catch(error => {
                        console.error("error in get user image path");
                        console.error(error);
               })
            }
        },
        computed:{

        }
    }
</script>

<style scoped lang="scss">

</style>