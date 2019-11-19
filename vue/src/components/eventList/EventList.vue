<template>
    <div class="eventList">
        <div v-for="event in eventList" :key="event.event.id" @click="showEvent(event.event.id)">
            <router-link to="/event/1">
                <eventCard/>
            </router-link>
        </div>
    </div>
</template>

<script>
    import eventCard from '@/components/eventList/detail/eventCard'

    export default {
        name: "AllEventList",
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
