import event from "../../../api/event";

const state = {
  eventList: [],
  joinedEventList: []
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
  },

  getJoinedEventList({commit}) {
      event.getJoinedEventList()
          .then(response => {
              commit('setJoinedEventList', response);
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
  },
  setJoinedEventList(state, eventList) {
      state.joinedEventList = eventList.data.data.eventsList;
  }

};

export default {
    namespaced: true,
    state,
    actions,
    mutations
}
