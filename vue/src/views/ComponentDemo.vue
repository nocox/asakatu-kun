<template>
  <div>
    <h2>Reaction</h2>
    <div>event id : {{eventId}}</div>
    <user-reaction v-bind:event-id="eventId" />
  </div>
</template>

<script>
import UserReaction from '../components/eventDetail/UserReaction.vue';
export default {
  name: 'demo',
  data() {
    return {
      eventId: 1
    }
  },
  components: {
    UserReaction
  }
}
</script>
