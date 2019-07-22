var eventDetail = new Vue({
    el: '#event-detail',
    data: {
        eventInfo: {
            eventId: 1,
            eventName:"ちょっぴり遅めの朝活くんvol.２",
            startDate: "2019-07-28T09:00:00Z",
            duration: 3,
            address: "BOOK LAB TOKYO",
            seatInfo: "ソファー席",
            eventStatus: "yet", // yet,progress,fin,canceled
            eventDetail:"社会人にとって、休日は貴重な自由時間。その休日の朝の時間を、...."
        },
        request: {
            userId: 1,
            content: "https://4.bp.blogspot.com/-m56DCo_VDbQ/UU--ubQ1vTI/AAAAAAAAO84/CWFZIAw-zxY/s1600/kaizoku_mark.png",
            comment: "おしゃべりしたい！"
        },
        users: []
    },
    created: async function () {
        let url = location.href;
        this.eventId = url.substr(url.lastIndexOf('/') + 1);
        console.log(this.eventId);
        this.eventId = 1;
        await this.refresh();
    },
    methods: {
        refresh: async function () {
            const eventInfo = await axios.get('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId);
            this.eventInfo = eventInfo.data.data;
            console.info(this.eventInfo);
            const eventUsers = await axios.get('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId + '/users');
            this.users = eventUsers.data;
            console.log(this.users);
        },
        joinEvent: async function () {
            await axios.post('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId + '/user', this.request);
            await this.refresh();
        }
    }
});