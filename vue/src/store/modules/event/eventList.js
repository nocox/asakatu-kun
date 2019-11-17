import event from "../../../api/event";

const state = {
  eventList: [{
    eventId: 0,
    title: "",
    startDate: "",
    date: "",
    duration: 0,
    address: "",
    seatInfo: "",
    eventStatus: "",
    eventDetail: "",
    hasJoin: false,
    users: [{
        imgePath:"",
        displayName:"",
        reaction:""
    }]
  }]

};

const actions = {
  getEventList({commit}) {
      event.getEventList()
          .then(response => {
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
      state.eventList = eventList.data.data.eventsList;
  }
};

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
