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
                <label for="name">event Name</label>
                <input
                        id="name"
                        v-model="request.eventName"
                        type="text"
                        name="name"
                >
            </p>
            <p>
                <label for="date">startDate</label>
                <input
                        id="date"
                        v-model="request.startDate"
                        type="datetime-local"
                        name="date"
                >
            </p>
            <p>
                <label for="duration">duration</label>
                <input
                        id="duration"
                        v-model="request.duration"
                        type="number"
                        name="duration"
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
                <label for="eventDetail">eventDetail</label>
                <textarea
                        name="eventDetail"
                        id="eventDetail"
                        cols="30"
                        rows="10"
                        v-model="request.eventDetail"
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

    import axios from 'axios';

    export default {
        name: "EventCreate",
        data() {
            return {
                request: {
                    eventName: "",
                    startDate: "",
                    duration: 0,
                    address: "",
                    seatInfo: "",
                    eventDetail: ""
                },
                errors: [],
            }
        },
        methods: {
            checkEventCreateForm: function (e) {
                this.errors = [];
                if (!this.request.eventName) {
                    this.errors.push("Name required.");
                }
                if (!this.request.startDate) {
                    this.errors.push('start date required.');
                }
                if (!this.errors.length) {
                    this.createEvent();
                    e.preventDefault();
                    return true;
                }
                e.preventDefault();
            },
            createEvent: async function () {
                console.log(this.request);
                const axiosResponse = await axios.post('http://localhost:8080/event/new', this.request,{withCredentials:true});
                if (axiosResponse.status === 200 || axiosResponse.status === 201) {
                    console.log("ok");
                    console.log(axiosResponse);
                } else {
                    console.log("error");
                    console.log(axiosResponse);
                }
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
</style>