<template>
  <div>
    <h2>Reaction</h2>
    <div>event id : {{eventId}}</div>
    <reaction-list v-bind:event-id="eventId" />
  </div>
</template>

<script>
import ReactionList from '../components/ReactionList.vue';
export default {
  name: 'demo',
  data() {
    return {
      eventId: 1
    }
  },
  components: {
    ReactionList
  }
}
</script>
