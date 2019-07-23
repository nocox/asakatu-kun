<template>
    <div class="event" id="event-detail">

        <section class="event-info">
            <h2 class="event-info-title">ちょっぴり遅めの朝活くんvol.２</h2>
            <h3 class="event-info-datetime">{{eventInfo.startDate}}</h3>
            <div class="event-info-place">{{eventInfo.address}}</div>
            <div class="event-info-function clearfix">
                <div class="event-info_google-api">
                    <div class="event-info-map"><i class="fas fa-map-marker-alt event-info__icon"></i> 東京都</div>
                    <div class="event-info-add-calender"><i class="far fa-calendar-alt event-info__icon"></i> カレンダーに追加</div>
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
            <div class="participant-list">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
                <img src="../assets/img/human-icon-big.png" alt="user-image" class="participant-image image_circle">
            </div>
        </section>

        <section class="event-comment section-margin">
            <h3 class="comment-title">やりたいこと</h3>
            <div class="comment-list">
                <div class="comment-user clearfix">
                    <img src="../assets/img/human-icon-big.png" alt="user-image" class="comment-user__image image_circle">
                    <p class="comment-user__content">がんばりやす</p>
                </div>
                <div class="comment-user clearfix">
                    <img src="../assets/img/human-icon-big.png" alt="user-image" class="comment-user__image image_circle">
                    <p class="comment-user__content">がんばりやす</p>
                </div>
                <div class="comment-user clearfix">
                    <img src="../assets/img/human-icon-big.png" alt="user-image" class="comment-user__image image_circle">
                    <p class="comment-user__content">がんばりやす</p>
                </div>
                <div class="comment-user clearfix">
                    <img src="../assets/img/human-icon-big.png" alt="user-image" class="comment-user__image image_circle">
                    <p class="comment-user__content">がんばりやす</p>
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
                    eventName: "ちょっぴり遅めの朝活くんvol.２",
                    startDate: "2019-07-28T09:00:00Z",
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
                users: []
            }
        },
        created: async function () {
            let url = location.href;
            this.eventId = url.substr(url.lastIndexOf('/') + 1);
            console.log(this.eventId);
            this.eventId = 1;
            await this.refresh();
        },
        methods: {
            refresh: async function () {
                const eventInfo = await axios.get('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId);
                this.eventInfo = eventInfo.data.data;
                console.info(this.eventInfo);
                const eventUsers = await axios.get('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId + '/users');
                this.users = eventUsers.data;
                console.log(this.users);
            },
            joinEvent: async function () {
                await axios.post('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId + '/user', this.request);
                await this.refresh();
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/eventCreate.js";

</style>