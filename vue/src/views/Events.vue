<template>
    <div id="events">
        <p>event list</p>
<!--    todo: 裏側の実装がまだなので後ほど。    -->
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Events",
        data() {
            return {
                eventList: {
                    joinedEvents:[],
                    yetEvents:[]
                }
            }
        },
        created: async function () {
            await this.refresh()
        },
        methods: {
            refresh: async function () {
                const res = await axios.get('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/events');
                this.eventList.joinedEvents = res.data.joined;
                this.eventList.yetEvents = res.data.yet;
                console.info(this.eventList);
            },
        }
    }
</script>

<style scoped lang="scss">

</style>