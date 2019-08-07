<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/sign_up" class="sign_up">Sign Up</router-link> |
      <router-link to="/login" class="login">Login</router-link> |
      <router-link to="/events" class="events">Events</router-link> |
      <router-link to="/event" class="event">Event</router-link> |
      <router-link to="/event/create" class="create">create</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
  /*@import "assets/css/uikit.css";*/
  /*@import "assets/css/uikit-rtl.css";*/
  @import "assets/css/base";
</style>
