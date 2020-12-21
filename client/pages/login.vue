<template>
  <div>
    <LoginComponent></LoginComponent>
  </div>
</template>

<script lang="ts">

import {  Vue, Component, Prop } from 'vue-property-decorator'

import LoginComponent from "~/components/Login.vue";
@Component({
  components: {
    LoginComponent
  }

})
export default class Login extends Vue{

}
</script>

<style scoped>

</style>
