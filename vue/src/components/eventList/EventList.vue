<template>
    <div>
      <div v-for="event in eventList" :key="event.event.id" @click="showEvent(event.event.id)">
        <p>{{ event.designDate }}</p>
        <p>{{ event.event.address }}</p>
        <p>{{ event.event.eventTitle }}</p>
        <p>{{ event.event.eventDetail }}</p>
        <div v-for="user in event.joinedUserInfo" :key="user.id">
          <img :src="user.imagePath">
        </div>
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
