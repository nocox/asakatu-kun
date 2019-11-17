<template>
    <div>
      <div v-for="event in eventList">
        <p>{{ event.designDate }}</p>
        <p>{{ event.event.address }}</p>
        <p>{{ event.event.eventTitle }}</p>
        <p>{{ event.event.eventDetail }}</p>
        <div v-for="user in event.event.userStatusList">
          <p>{{user.id}}</p>
        </div>
        <p>-----------------------------</p>
      </div>
    </div>
</template>

<script>
    export default {
        name: "AllEventList",
        props: {
            eventList: Array
        },
    }
</script>

<style scoped>

</style>
