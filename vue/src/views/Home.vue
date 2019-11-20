<template>
  <div class="home">
    <Header/>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from '@/components/common/HelloWorld.vue';
import Header from '@/components/common/Header.vue';

export default {
  name: 'home',
  components: {
    HelloWorld,
    Header
  }
}
</script>
