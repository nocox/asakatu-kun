<template>
  <div class="home">
    <Header />
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue';
import Header from '@/components/Header.vue';

export default {
  name: 'home',
  components: {
    HelloWorld,
    Header
  }
}
</script>
