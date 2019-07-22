var eventCreate = new Vue({
    el: '#createEvent',
    data: {
        request: {
            eventName: "",
            startDate: "",
            duration: 0,
            address: "",
            seatInfo: "",
            eventDetail: ""
        },
        errors: []
    },
    methods: {
        checkForm: function (e) {
            this.errors = [];
            if (!this.request.eventName) {
                this.errors.push("Name required.");
            }
            if (!this.request.startDate) {
                this.errors.push('startdate required.');
            }
            if (!this.errors.length) {
                this.createEvent();
                return true;
            }
            e.preventDefault();
        },
        createEvent: async function () {
            await axios.post('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/', this.request);
        }
    }
});