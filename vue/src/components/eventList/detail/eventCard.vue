<template>
    <div class="event-card">
        <div class="left-container">
            <div class="Date-info">
                <p class="startMonth">{{designDate}}</p>
            </div>
        </div>
        <div class="right-container">
            <div class="info">
                <p class="info-dateTime">
                    <span class="weekDay">{{}}</span>
                    <span class="Time">{{}}</span>
                </p>
                <p class="address">{{address}}</p>
            </div>
            <div class="detail-container">
                <h2 class="event-title">
                    {{eventTitle}}
                </h2>
                <p class="description">
                    {{eventDetail}}
                </p>
                <div class="participant" v-for="user in joinedUserInfo" :key="user.id">
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
            designDate: String,
            address: String,
            eventTitle: String,
            eventDetail: String,
            joinedUserInfo: Array,
        }
    }
</script>

<style scoped>

</style>
