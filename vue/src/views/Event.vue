<template>
    <div class="event" id="event-detail">

        <section class="event-info">
            <h2 class="event-info-title">{{eventInfo.title}}</h2>
            <h3 class="event-info-datetime">{{eventInfo.date}}</h3>
            <div class="event-info-place">ここが足りない {{eventInfo.seatInfo}}</div>
            <div class="event-info-function clearfix">
                <div class="event-info_google-api">
                    <div class="event-info-map"><i class="fas fa-map-marker-alt event-info__icon"></i>{{eventInfo.address}}
                    </div>
                    <div class="event-info-add-calender"><i class="far fa-calendar-alt event-info__icon"></i>カレンダーに追加
                    </div>
                </div>
                <button v-if="!this.eventInfo.hasJoin" @click="showModal = 1"
                        class="uk-button uk-button-default uk-button-small event__btn">参加
                </button>
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
                    <button @click="showModal = 2"
                            class="uk-button uk-button-default uk-button-small participant-modal__btn">
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
                    <button @click="this.contract"
                            class="uk-button uk-button-default uk-button-small participant-modal__btn">
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
            <div class="participant-list">
                <div class="participant-element" v-for="user in users" v-bind:key="user.id">
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
    import ModalTemplate from "../components/eventDetail/ModalTemplate";
    import {mapActions, mapState} from "vuex";

    export default {
        name: "Event",
        components: {
            ModalTemplate
        },
        template: '<modal-template></modal-template>',
        data() {
            return {
                request: {
                    comment: ""
                },
                showModal: false,
            }
        },
        computed: {
            eventInfo: {
                ...mapState({
                    eventId: state => state.eventDetail.eventId,
                    title: state => state.eventDetail.title,
                    startDate: state => state.eventDetail.startDate,
                    date: state => state.eventDetail.date,
                    duration: state => state.eventDetail.duration,
                    address: state => state.eventDetail.address,
                    seatInfo: state => state.eventDetail.seatInfo,
                    eventStatus: state => state.eventDetail.eventStatus,
                    eventDetail: state => state.eventDetail.eventDetail,
                    hasJoin: state => state.eventDetail.hasJoin,
                }),
            },
            ...mapState({
                users:state => state.eventDetail.users,
            })

        },
        mounted: function () {
            this.eventId = Number(this.$route.params.eventId);
            console.log("event id : " + this.eventId);
            this.refresh();
        },
        methods: {
            refresh: function () {
                this.getEventInfo(this.eventId);
                this.getUsers(this.eventId);
            },
            contract() {
                const participationEvent = this.contract(this.eventId, this.request);
                participationEvent.then(() => {
                    this.showModal = false;
                    this.$router.push('/events/joined');
                }).catch(error => {
                    console.error(error);
                    alert('サーバからのデータ取得に失敗しました');
                    // TODO nocox エラーハンドリングが必要かも (2019/10/02)
                });
            },
            ...mapActions([
                'getEventInfo',
                'getUsers',
                'contract'
            ])
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/eventCreate.js";
</style>
