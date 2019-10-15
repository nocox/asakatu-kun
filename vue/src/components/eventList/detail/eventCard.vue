<template>
    <div class="event-card">
        <div class="left-container">
            <div class="Date-info">
                <p class="startMonth">{{}}</p>
                <p class="startDate">{{}}</p>
            </div>
        </div>
        <div class="right-container">
            <div class="info">
                <p class="info-dateTime">
                    <span class="weekDay">{{}}</span>
                    <span class="Time">{{}}</span>
                </p>
                <p class="address">{{}}</p>
            </div>
            <div class="detail-container">
                <h2 class="event-title">
                    {{}}
                </h2>
                <p class="description">
                    {{}}
                </p>
                <img src="" alt="" class="participant">
            </div>
            <router-link to="/event/1"> aa</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "eventCard",
        props: {
            eventId: Number
        },
        data() {
            return {}
        },
        computed: {
            ...mapState({})
        },
        methods: {
            ...mapMutations({}),
            ...mapActions({})

        }
    }
</script>

<style scoped>

</style>
