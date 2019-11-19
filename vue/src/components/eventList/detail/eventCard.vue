<template>
    <div class="event-card">
        <div class="left-container">
            <div class="Date-info">
                <p class="startMonth">{{props.designDate}}</p>
                <p class="startDate">{{}}</p>
            </div>
        </div>
        <div class="right-container">
            <div class="info">
                <p class="info-dateTime">
                    <span class="weekDay">{{}}</span>
                    <span class="Time">{{}}</span>
                </p>
                <p class="address">{{props.address}}</p>
            </div>
            <div class="detail-container">
                <h2 class="event-title">
                    {{props.eventTitle}}
                </h2>
                <p class="description">
                    {{props.eventDetail}}
                </p>
                <div class="participant" v-for="user in props.joinedUserInfo" :key="user.id">
                    <img :src="user.imagePath" alt="user image" >
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "eventCard",
        props: {
            designDate: Date,
            address: String,
            eventTitle: String,
            eventDetail: String,
            joinedUserInfo: [{id: Number,imagePath:String}],
        },
        data() {
            return {}
        },
        computed: {
            // ...mapState({})
        },
        methods: {
            // ...mapMutations({}),
            // ...mapActions({})

        }
    }
</script>

<style scoped>

</style>
