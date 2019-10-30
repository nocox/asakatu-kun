<template>
  <div class="hello">
    <header>
      <h1><a href=""><img src="" alt=""></a></h1>
    </header>
    <main>
      <h2>BizSpotへようこそ</h2>
      <p>ログインまたはサインアップを行なってください</p>
      <p><router-link class="primaryButton" to="/sign_up">サインアップ</router-link></p>
      <p class="secondaryButton"><router-link to="/login">ログイン</router-link></p>
    </main>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import "../assets/css/button";
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

@import "../../assets/css/login-signup";

</style>
