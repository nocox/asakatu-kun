<template>
    <section class="mypage">
        <div class="user-info">
            <h2 class="user-info__name" >{{userName}}</h2>
            <img class="user-info__image" :src="userImage" alt="user image ">
        </div>
    </section>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "MyPage",
        data(){
            return{}
        },
        created: function () {
            this.getUserImage();
            console.log("get user image function is completed");
        },
        methods:{
            //todo ここloginに持たせればいらなくなるかも
            getUserImage: function () {
                const getUserInfo = axios.get('http://localhost:8080/login_user',{withCredentials:true});
                getUserInfo.then(response=>{
                    console.log("ok");
                    console.log(response);
                    //todo ここのデータの撮り方確認しないとあかん
                    const userImagePath = response.data.data.imagePath;
                    console.log("userImagePath");
                    console.log(userImagePath);
                    this.$store.commit('getUserImage', userImagePath);
                })
                    .catch(error => {
                        console.error("error in get user image path");
                        console.error(error);
                        this.$store.commit('initLogin');
                        this.$router.push('/login');
                    })
            }
        },
        computed:{
            userName() {
                return this.$store.state.userName;
            },
            userImage() {
                return this.$store.state.userImagePath;
            },

        }
    }
</script>

<style scoped lang="scss">

</style>