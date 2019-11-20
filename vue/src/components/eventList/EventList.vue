<template>
    <div>
      <div v-for="event in eventList" :key="event.event.id" @click="showEvent(event.event.id)">
        <eventCard
            :designDate="event.designDate"
            :address="event.event.address"
            :eventTitle="event.event.eventTitle"
            :eventDetail="event.event.eventDetail"
            :joinedUserInfo="event.joinedUserInfo"
        />
        <p>-----------------------------</p>
      </div>
    </div>
</template>

<script>
    import eventCard from '@/components/eventList/detail/eventCard'
    export default {
        name: "EventList",
        components: {
            eventCard,
        },
        props: {
            eventList: Array
        },
        methods: {
            showEvent: function (id) {
                this.$router.push(`/event/detail/${id}`)
            }
        }
    }
</script>

<style scoped>

</style>
