<template>
  <div class="header">
    <div class="header__backBtn"></div>
    <h1 class="header__logo"><img alt="BizSpot" src="asseets/logo.png"></h1>
    <figure class="header__profileImg"><img alt="user profile image" src=""></figure>
  </div>
</template>

<script>
export default {
  name: 'Header',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.header{
  display:flex;
  position: fixed;
  width: 100%;
  height: 72px;
  background: #FFF;
  z-index: 100000;
  padding: 0 16px;
  align-items: center;
  justify-content: space-between;
  filter: drop-shadow(2px 2px 2px rgba(0,0,0,0.4));
  &__backBtn{
    position:relative;
    &::before{
      content:"";
      display:block;
      height:3px;
      width:16px;
      background: #707070;
      transform: rotate(-45deg);
      position: absolute;
      top:-5px;
      left:-25%;
    }
    &::after{
      content:"";
      display:block;
      height:3px;
      width:16px;
      background: #707070;
      transform: rotate(45deg);
      position: absolute;
      top:5px;
      left:-25%;
    }
  }
  &__logo{
    height: 40px;
    >img{
      height:100%;
    }
  }
  &__profileImg{
    height:34px;
    width: 34px;
    >img{
      height:100%;
      width: 100%;
      object-fit: cover;
      border-radius: 50%;
    }
  }
}

</style>
