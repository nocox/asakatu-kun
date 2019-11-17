import event from "../../../api/event";

const state = {
  eventList: [{
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
  }]

};

const actions = {
  getEventList() {
      event.getEventList()
          .then(response => {
              console.log(response);
              commit('setEventList', response);
          })
          .catch(error => {
              console.error(error);
              alert('サーバからのデータ取得に失敗しました');
          });
  }
};

const mutations = {
  setEventList(state, eventList) {
      console.log("aaaaaaaaaaaa")
      state.eventList = eventList;
  }
};

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
