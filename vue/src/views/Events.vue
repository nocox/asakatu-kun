<template>
    <div id="events" class="event-list">
        <section>
            <div class="event-list-switch clearfix">
                    <div :class="{ active: isAll }" @click="all" class="event-list-switch-button">ホーム</div>
                    <div :class="{ active: !isAll }" @click="join" class="event-list-switch-button">参加イベント</div>
            </div>

            <div class="event-list-content" v-if="isAll">
                <EventList
                  :eventList="eventList"
                 />
            </div>
            <div v-else class="event-list-content">
                <EventList
                  :eventList="joinedEventList"
                 />
            </div>
        </section>
    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import EventList from '@/components/eventList/EventList.vue';

    export default {
        name: "Events",
        components: {
            EventList
        },
        data() {
            return {
                isAll: true
            }
        },
        created() {
            this.getEventList();
            this.getJoinedEventList();
        },

        methods: {
           ...mapActions('eventList',[
               'getEventList',
               'getJoinedEventList',
           ]),
           all: function () {
              this.isAll = true
           },
           join: function () {
              this.isAll = false
           }
        },
        computed: {
            ...mapState({
                eventList: state => state.eventList.eventList,
                joinedEventList: state => state.joinedEventList.eventList,
            }),
        }
    }
</script>

<style scoped>
    .event-list {
        margin: 0 0;
        height: inherit;
        background: #f5f5f7;

    }
    .event-list-switch {
        padding-top: 20px;
        display: flex;
        background-color: #ffffff;
    }

    .event-list-switch-button {
        margin: auto;
        color: black;
        font-size: 14px;
        font-weight: bold;
        width: 30%;
        min-width: 100px;
        text-align: center;
        border-bottom: white 4px solid;
        text-decoration: none;
    }

    .active {
        border-bottom: grey 4px solid;
    }

    .event-list-content {
        margin-top: 1rem;
        height: max-content;
        padding: 2em 1em 1em;
    }

</style>
