var eventDetail = new Vue({
    el: '#event-detail',
    data: {
        eventInfo: {
            eventId: 1,
            startDate: "2019-07-28T09:00:00Z",
            duration: 3,
            address: "BOOK LAB TOKYO",
            seatInfo: "ソファー席",
            eventStatus: "yet"
        },
        request: {
            userId: 1,
            content: "https://4.bp.blogspot.com/-m56DCo_VDbQ/UU--ubQ1vTI/AAAAAAAAO84/CWFZIAw-zxY/s1600/kaizoku_mark.png",
            comment: "おしゃべりしたい！"
        },
        users: []
        //"userId": 1,
        //"displayName": "朝活くん",
        //"imagePath": "https://avatars0.githubusercontent.com/u/50159106",
        //"content":"https://4.bp.blogspot.com/-m56DCo_VDbQ/UU--ubQ1vTI/AAAAAAAAO84/CWFZIAw-zxY/s1600/kaizoku_mark.png"
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
        //    todo:ここデータうまく取れてない。取り方調べる。     
        },
        joinEvent: async function () {
            await axios.post('https://virtserver.swaggerhub.com/projectormato/asakatu-kun/1.0.0/event/' + this.eventId + '/user', this.request);
            await this.refresh();
        }
    }
});

//yet
// progress
// fin
// canceled