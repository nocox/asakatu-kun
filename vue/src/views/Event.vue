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
                <button class="uk-button uk-button-default uk-button-small event__btn">参加</button>
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

        <section class="event-participant section-margin">
            <h3 class="participant-title">参加メンバー</h3>
            <div  class="participant-list">
                <img v-for="user in users" v-bind:key="user.id" :src="user.imagePath" v-bind:alt="user.displayName" class="participant-image image_circle">
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

        <div class="join-button uk-flex uk-flex-center">
            <button class="event__btn">参加</button>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "Event",
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
                    eventDetail: "社会人にとって、休日は貴重な自由時間。その休日の朝の時間を、...."
                },
                request: {
                    userId: 1,
                    content: "https://4.bp.blogspot.com/-m56DCo_VDbQ/UU--ubQ1vTI/AAAAAAAAO84/CWFZIAw-zxY/s1600/kaizoku_mark.png",
                    comment: "おしゃべりしたい！"
                },
                users: [],
                userStatusList: [],
                eventAPI: 'http://localhost:8080/event/'
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
                // console.log(this.eventInfo);
                // console.info(this.eventInfo);
                // const eventUsers = await axios.get(this.eventAPI + this.eventId + '/users');
                // this.users = eventUsers.data;
                // console.log(this.users);
            },
            joinEvent: async function () {
                await axios.post(this.eventAPI + this.eventId + '/user', this.request);
                await this.refresh();
            },
            getEventInfo: async function () {
                const getUserInfo = axios.get('http://localhost:8080/event/' + this.eventId, {withCredentials: true});
                getUserInfo.then(response => {
                    console.log("ok");
                    console.log(response.data);

                    this.eventInfo.title = response.data.event.eventTitle;
                    this.eventInfo.eventId = response.data.event.id;
                    this.eventInfo.date = response.data.designDate;
                    this.eventInfo.address = response.data.event.address;
                    this.eventInfo.seatInfo = response.data.event.seatInfo;
                    this.eventInfo.eventStatus = response.data.event.eventStatus;

                    this.userStatusList = response.data.event.userStatusList;
                }).catch(error => {
                    console.error("error in get user image path");
                    console.error(error);
                    this.$store.commit('initLogin');
                    this.$router.push('/login');
                });
            },
            getUsers: async function () {
                const getUserInfo = axios.get('http://localhost:8080/event/' + this.eventId + '/users' , {withCredentials: true});
                getUserInfo.then(response => {
                    console.log("ok");
                    console.log(response.data.data);
                    this.users = response.data.data.userList;

                }).catch(error => {
                    console.error("error in get user image path");
                    console.error(error);
                    this.$store.commit('initLogin');
                    this.$router.push('/login');
                });
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/eventCreate.js";
</style>