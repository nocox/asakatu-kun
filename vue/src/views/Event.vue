<template>
    <div class="event" id="event-detail">

        <section class="event-info">
            <h2 class="event-info-title">{{eventInfo.title}}</h2>
            <h3 class="event-info-datetime">{{eventInfo.date}}</h3>
            <div class="event-info-place">ここが足りない {{eventInfo.seatInfo}}</div>
            <div class="event-info-function clearfix">
                <div class="event-info_google-api">
                    <div class="event-info-map"><i class="fas fa-map-marker-alt event-info__icon"></i>{{eventInfo.address}}</div>
                    <div class="event-info-add-calender"><i class="far fa-calendar-alt event-info__icon"></i>カレンダーに追加</div>
                </div>
                <button v-if="!this.eventInfo.hasJoin" @click="showModal = 1" class="uk-button uk-button-default uk-button-small event__btn">参加</button>
            </div>
            <div class="event-info_detail">
                社会人にとって、休日は貴重な自由時間。その休日の朝の時間を、
                もっと有意義に使いたい、もっと学びたい、
                会社外の人たちと交流を持ちたいという20代～30代の社会人が集まって、
                共に考えたり、学んだり。
                休日、ちょっと早起きをして、暮らし、
                人生をより豊かにするきっかけにしていきませんか？
            </div>
        </section>

        <modal-template v-if="showModal === 1" @close="showModal = false" :key="1">
            <div slot="header">
                やりたいことを記入
            </div>
            <div slot="body" class="participant-modal-body">
                <div class="participant-modal-detail">
                    参加する朝活をもっと有意義にするために決意表明をして，目的とゴールを明確にしよう
                </div>
                <label>
                    <textarea v-model="request.comment" class="participant-modal-textlines"></textarea>
                </label>
            </div>
            <div slot="footer">
                <div class="participant-modal-link">
                    <button @click="showModal = 2" class="uk-button uk-button-default uk-button-small participant-modal__btn">
                        確認する
                    </button>
                    <div class="participant-modal__btn-cancel" @click="showModal = false">
                        あとにする
                    </div>
                </div>
            </div>
        </modal-template>

        <modal-template v-if="showModal === 2" @close="showModal = false" :key="1">
            <div slot="header">
            </div>
            <div slot="body">
                <div class="participant-modal-contract">
                    明日朝絶対に起きることをここに誓います。
                </div>
            </div>
            <div class="" slot="footer">
                <div class="participant-modal-link">
                    <button @click="this.contract" class="uk-button uk-button-default uk-button-small participant-modal__btn">
                        決定する
                    </button>
                    <div class="participant-modal__btn-cancel" @click="showModal = 1">
                        変更する
                    </div>
                </div>
            </div>
        </modal-template>

        <section v-if="this.eventInfo.hasJoin" class="reaction-change">
            参加していたら、ここでリアクション変更
        </section>

        <section class="event-participant section-margin">
            <h3 class="participant-title">参加メンバー</h3>
            <div  class="participant-list">
                <div class="participant-element" v-for="user in users" v-bind:key="user.id" >
                    <div class="participant-reaction-balloon1">
                        <i v-bind:class="user.reaction" class="participant-reaction"></i>
                    </div>
                    <img :src="user.imagePath" v-bind:alt="user.displayName" class="participant-image image_circle">
                </div>
            </div>
        </section>

        <section class="event-comment section-margin">
            <h3 class="comment-title">やりたいこと</h3>
            <div class="comment-list">
                <div v-for="user in users" v-bind:key="user.id" class="comment-user clearfix">
                    <img :src="user.imagePath" alt="user-image"
                         class="comment-user__image image_circle">
                    <p class="comment-user__content">{{user.comment}}</p>
                </div>
            </div>
        </section>

        <div v-if="!this.eventInfo.hasJoin" class="join-button uk-flex uk-flex-center">
            <button @click="showModal = 1" class="event__btn">参加</button>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import ModalTemplate from "../components/ModalTemplate";

    export default {
        name: "Event",
        components: {
            ModalTemplate
        },
        template: '<modal-template></modal-template>',
        data() {
            return {
                eventInfo: {
                    eventId: 1,
                    title: "ちょっぴり遅めの朝活くんvol.２",
                    startDate: "2019-07-28T09:00:00Z",
                    date: "a",
                    duration: 3,
                    address: "BOOK LAB TOKYO",
                    seatInfo: "ソファー席",
                    eventStatus: "yet", // yet,progress,fin,canceled
                    eventDetail: "社会人にとって、休日は貴重な自由時間。その休日の朝の時間を、....",
                    hasJoin: true
                },
                request: {
                    comment: ""
                },
                users: [],
                eventAPI: 'http://localhost:8080/event/',
                showModal: false,
            }
        },
        mounted: function () {
            this.eventId = Number(this.$route.params.eventId);
            console.log("event id : " + this.eventId);
            this.refresh();
        },
        methods: {
            refresh: function () {
                this.getEventInfo();
                this.getUsers();
            },
            getEventInfo: async function () {
                const getEventInfo = axios.get('http://localhost:8080/event/' + this.eventId, {withCredentials: true});
                getEventInfo.then(response => {

                    this.eventInfo.title = response.data.event.eventTitle;
                    this.eventInfo.eventId = response.data.event.id;
                    this.eventInfo.date = response.data.designDate;
                    this.eventInfo.address = response.data.event.address;
                    this.eventInfo.seatInfo = response.data.event.seatInfo;
                    this.eventInfo.eventStatus = response.data.event.eventStatus;
                    this.eventInfo.hasJoin = response.data.hasJoin;

                }).catch(error => {
                    console.error(error);
                    alert('サーバからのデータ取得に失敗しました');
                    // TODO nocox エラーハンドリングが必要かも (2019/10/02)
                });
            },
            getUsers: async function () {
                const getUsers = axios.get('http://localhost:8080/event/' + this.eventId + '/users' , {withCredentials: true});
                getUsers.then(response => {
                    this.users = response.data.data.userList;
                }).catch(error => {
                    console.error(error);
                });
            },
            contract(){
                const participationEvent = axios.post('http://localhost:8080/event/' + this.eventId + '/user' ,this.request ,{withCredentials: true});
                participationEvent.then(() => {
                    this.showModal = false;
                    this.$router.push('/events/joined');
                }).catch(error => {
                    console.error(error);
                    alert('サーバからのデータ取得に失敗しました');
                    // TODO nocox エラーハンドリングが必要かも (2019/10/02)
                });
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/eventCreate.js";
</style>