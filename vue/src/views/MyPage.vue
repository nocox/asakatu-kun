<template>
    <section class="mypage">
        <Header
          before = "/events"
        />
        <div class="user-info">
            <img class="user-info__image" :src="userImage" alt="user image ">
            <p class="user-info__name">{{displayName}}</p>
            <p class="user-info__name">{{userName}}</p>
            <p class="user-info__name">{{email}}</p>
        </div>

        <form @submit="edit" enctype="multipart/form-data" method=GET>
            <p>
                <input
                        v-model="request.displayName"
                        type="text"
                        name="displayName"
                />
            </p>
            <p>
                <input
                        @change ="onFileChange"
                        type="file"
                        name="file"
                />
            </p>
            <input type="submit" value="完了">
        </form>

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
        data() {
          return {
              request: {
                  displayName: '',
                  file: '',
              }
          }
        },
        created: function () {
            this.setUserImage();
            this.whoAmI();
        },
        methods: {
           ...mapActions('userInfo',[
               'setUserImage',
               'whoAmI',
               'userInfo'
           ]),
           edit: function (){
              console.log(this.request)
              this.userInfo(this.request);
           },
           onFileChange(e) {
              let files = e.target.files;
              this.file = files[0];
              console.log(this.file)
           },
        },
        computed: {
            ...mapState({
                userName: state => state.userInfo.userName,
                userImage: state => state.userInfo.userImagePath,
                displayName: state => state.userInfo.displayName,
                email: state => state.userInfo.email
            }),
        }
    }
</script>

<style scoped lang="scss">

</style>
