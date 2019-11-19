import event from "../../../api/event";

const state = {
    eventId: 1,
    title: "ちょっぴり遅めの朝活くんvol.２",
    startDate: "2019-07-28T09:00:00Z",
    date: "a",
    duration: 3,
    address: "BOOK LAB TOKYO",
    seatInfo: "ソファー席",
    eventStatus: "yet", // yet,progress,fin,canceled
    eventDetail: "社会人にとって、休日は貴重な自由時間。その休日の朝の時間を、....",
    hasJoin: true,
    users: [{
        imgePath:"",
        displayName:"",
        reaction:""
    }]
};

const actions = {
    createEvent(request) {
        event.createEvent(request)
            .then(() => {
                this.$router.push('/events');
            })
            .catch(error => {
                alert("some thing is error");
            })
    },
    getEventInfo(commit, eventId) {
        event.getEventInfo(eventId)
            .then(response => {
                commit('setEventInfo', response);
            })
            .catch(error => {
                alert('サーバからのデータ取得に失敗しました');
                // TODO nocox エラーハンドリングが必要かも (2019/10/02)
            });
        event.getUsers(eventId)
            .then(users => {
                commit('setEventUsers', users);
            })
            .catch(error =>{
                alert('サーバからのデータ取得に失敗しました');
            });
    },
    contract(eventId,request){
        return event.contract(eventId,request);
    }
};

const mutations = {
    setEventInfo(state, eventInfo) {
        state.eventId = eventInfo.eventId;
        state.title = eventInfo.title;
        state.startDate = eventInfo.startDate;
        state.date = eventInfo.date;
        state.duration = eventInfo.duration;
        state.address = eventInfo.address;
        state.seatInfo = eventInfo.seatInfo;
        state.eventStatus = eventInfo.eventStatus;
        state.eventDetail = eventInfo.eventDetail;
        state.hasJoin = eventInfo.hasJoin;
    },
    setEventUsers(state, users) {
        state.users = users;
    }
};

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
