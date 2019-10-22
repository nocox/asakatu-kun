<template>
    <div class="event-create">
        <h2>create event</h2>
        <form
                id="event-create-form"
                @submit="checkEventCreateForm"
        >
            <div v-if="errors.length">
                <p>Please correct the following error(s):</p>
                <ul>
                    <li v-for="error in errors" :key="error">{{ error }}</li>
                </ul>
            </div>
            <p>
                <label for="name">event title</label>
                <input
                        id="name"
                        v-model="request.eventTitle"
                        type="text"
                        name="name"
                >
            </p>
            <p>
                <label for="startDate">start date</label>
                <input
                        id="startDate"
                        v-model="request.startDate"
                        type="date"
                        name="startDate"
                >
            </p>
            <p>
                <label for="startDate">start time</label>
                <input
                        id="startTime"
                        v-model="startTime"
                        type="time"
                        name="startTime"
                >
            </p>
            <p>
                <label for="endDate">end date</label>
                <input
                        id="endDate"
                        v-model="request.endDate"
                        type="date"
                        name="endDate"
                >
            </p>
            <p>
                <label for="endTime">end time</label>
                <input
                        id="endTime"
                        v-model="endTime"
                        type="time"
                        name="endTime"
                >
            </p>
            <p>
                <label for="address">address</label>
                <input
                        id="address"
                        v-model="request.address"
                        type="text"
                        name="address"
                >
            </p>
            <p>
                <label for="seatInfo">seat info</label>
                <input
                        id="seatInfo"
                        v-model="request.seatInfo"
                        type="text"
                        name="seatInfo"
                >
            </p>
            <p>
                <label for="eventDetail">eventDetail</label>
                <textarea
                        name="eventDetail"
                        id="eventDetail"
                        cols="30"
                        rows="10"
                        v-model="eventDetail"
                        placeholder="add event details"
                >
            </textarea>
            </p>
            <input
                    type="submit"
                    value="Submit"
                    id="event_create--submit"
            >
        </form>
    </div>
</template>

<script>
    import {mapActions} from "vuex";

    export default {
        name: "EventCreate",
        data() {
            return {
                request: {
                    eventTitle: "",
                    startDate: "",
                    endDate: "",
                    address: "",
                    seatInfo: "",
                },
                startTime: "",
                endTime: "",
                eventDetail: "",
                errors: [],
                apiURL: process.env.VUE_APP_API_URL_BASE
            }
        },
        methods: {
            checkEventCreateForm: function (e) {
                this.errors = [];
                if (!this.request.eventTitle) {
                    this.errors.push("Name required.");
                }
                if (!this.request.startDate) {
                    this.errors.push('start date required.');
                }
                console.log(this.startTime);

                this.request.startDate += "T" + this.startTime;
                this.request.endDate += "T" + this.endTime;

                if (!this.errors.length) {
                    this.createEvent(this.request);
                    e.preventDefault();
                }
                e.preventDefault();
            },
            ...mapActions([
                'createEvent'
            ])
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
</style>
