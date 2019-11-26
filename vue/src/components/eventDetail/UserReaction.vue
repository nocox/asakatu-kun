<template>
    <div id="demo-user-reaction">
        <span>
            <img :src="userImage" v-bind:alt="userName" class="participant-image image_circle">
        </span>

        <span class="participant-myreaction-balloon">
            <i v-bind:class="myReaction.userStatusContent" class="participant-myreaction"></i>
        </span>

        <span class="reaction-change">
            <span v-for="reaction in reactionList" v-bind:key=reaction.id>
                <input type="radio" v-bind:id="reaction.id" v-model="checked" v-bind:value="reaction.id" style="display:none">
                <label v-bind:for="reaction.id" class="participant-reaction-balloon1">
                    <i v-bind:class="reaction.userStatusContent" class="participant-reaction"></i>
                </label>
            </span>
        </span>
    </div>
</template>

<script>
    import axios from 'axios'
    import {mapState} from 'vuex'

    export default {
        name: "demo-user-reaction",

        data() {
            return {
                reactionList: [],
                myReaction: "",
                checked: ""
            }
        },
        props: ['eventId'],
        methods: {
            getReactionList: function(){
                const reactionListResponse = axios.get('http://localhost:8080/reaction/list', {withCredentials: true});
                reactionListResponse.then(response => {
                        this.reactionList =  response.data;
                    }
                ).catch(function (error) {
                  alert('サーバとの通信に失敗しました');
                });
            },
            getMyReaction: function () {
                const reactionListResponse = axios.get('http://localhost:8080/reaction/myself/' + this.eventId, {withCredentials: true});
                reactionListResponse.then(response => {
                        this.myReaction =  response.data;
                    }
                ).catch(function (error) {
                    alert('サーバとの通信に失敗しました');
                });
            },
            changeMyReaction: function (reactionId) {
                const reactionListResponse = axios.put('http://localhost:8080/reaction/change/' + this.eventId + '/' + reactionId ,new FormData() ,{withCredentials: true});
                reactionListResponse.then(response => {
                        this.myReaction =  response.data;
                    }
                ).catch(function (error) {
                    alert('サーバとの通信に失敗しました');
                });
            }
        },
        watch: {
            checked: function() {
                this.changeMyReaction(this.checked);
            }
        },
        created(){
          this.getReactionList();
          this.getMyReaction();

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

.participant{
    &-list{
        margin: 0 auto;
        display: flex;
        &:first-child{
        }
    }
    &-image{
        width: 15vw;
        height: 15vw;
        margin-right: 1vw;
        margin-left: 1vw;
        margin-top: 2vh;
    }
    &-reaction {
        margin: auto;

        &-balloon1 {
            // position: relative;
            display: inline-block;
            margin: 0 2px;
            padding: 10px 5px;
            min-width: 10%;
            max-width: 100%;
            color: #555;
            background: #FFDE95;
            border-radius: 10px;
            text-align: center;
        }

        &-balloon1:before {
            content: "";
            position: absolute;
            top: 100%;
            left: 50%;
            margin-left: -5px;
            border: 5px solid transparent;
            border-top: 8px solid #FFDE95;
        }

        &-balloon1 p {
            margin: 0;
            padding: 0;
        }
    }

    &-myreaction {
        margin: auto;

        &-balloon {
            position: relative;
            display: inline-block;
            margin: auto;
            padding: 10px 5px;
            min-width: 10%;
            max-width: 100%;
            color: #555;
            background: #FFDE95;
            border-radius: 10px;
            text-align: center;
        }

        /* アイコンを左に表示 */
        &-balloon::before{
            content: '';
            position: absolute;
            display: block;
            width: 0;
            height: 0;
            left: -7px;
            top: 7px;
            border-right: 8px solid #FFDE95;
            border-top: 6px solid transparent;
            border-bottom: 6px solid transparent;
        }
    }

    &-label{
        width: auto;
    }

    &-modal{
        &__btn{
            background-color: #FFB100;
            border-radius: 22px;
            border-color: white;
            width: 30vw;
            height: 5vh;
            color: white;

            &-cancel{
                font-size: small;
                color: #08c;
                margin-top: 10px;
            }
        }

        &-body{
            height: inherit;
        }

        &-textlines{
            border: 1px solid #707070;  /* 枠線 */
            border-radius: 8px;         /* 角丸 */
            padding: 5%;              /* 内側の余白量 */
            width: 90%;           /* 横幅 */
            height: 100%;              /* 高さ */
            font-size: 1em;             /* 文字サイズ */
            line-height: 1.2;           /* 行の高さ */
            resize: none;
        }

        &-detail{
            color: #707070;
            font-size: 13px;
            text-align: left;
            margin-bottom: 20px;
        }

        &-contract{
            font-size: 23px;
            text-align: left;
        }

        &-link{
            padding-top: 20px;
        }

        &-space{
            height: 120px;
            visibility: hidden;
        }
    }

    &-element{
        display: grid;
    }
}

.reaction{
    &-change{
        margin-left: 10px;
        padding: 15px;
        border: 2px black solid;
    }
}
</style>
