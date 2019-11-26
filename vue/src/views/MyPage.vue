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

        <p>
            <input
                    v-model="request.displayName"
                    type="text"
                    name="displayName"
            />
        </p>
        <p>
            <input
                    type="file"
                    name="file"
                    ref="file"
            />
        </p>
        <input type="submit" @click="edit" value="完了">
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
               'editDisplayName',
               'editImage'
           ]),
           edit: function (){
              this.editDisplayName(this.request);
              this.editImage(this.$refs.file.files[0]);
              this.$router.push('/mypage');
           }
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
