<template class="header">
  <div class="hader__backBtn"></div>
  <h1 class="header__logo"><img alt="BizSpot" src="asseets/logo.png"></h1>
  <figure class="header__profileImg"><img alt="user profile image" src=""></figure>
</template>

<script>
export default {
  name: 'header',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.header{
  display:flex;
  height: 72px;
  padding: 0 16px;
  align-items: center;
  justify-content: space-between;
  &__backBtn{
    &::before{
      display:block;
      height:3px;
      width:16px;
      background: #707070;
      transform: rotate(-45deg);
      position: absolute;
      top:-100%;
      left:-25%;
    }
    &::after{
      display:block;
      height:1px;
      width:16px;
      background: #707070;
      transform: rotate(45deg);
      top:50%;
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
