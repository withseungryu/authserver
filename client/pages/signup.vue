<template>
<div>
  <SignUpComponent></SignUpComponent>
</div>
</template>

<script lang="ts">
import {  Vue, Component, Prop } from 'vue-property-decorator'

import SignUpComponent from "~/components/SingUp.vue";
@Component({
  components: {
    SignUpComponent
  }

})
export default class SignUp extends Vue {

}

</script>

<style scoped>

</style>
