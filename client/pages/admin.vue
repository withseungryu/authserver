<template>
<div>

  <h1>ADMIN페이지입니다.</h1>
  <AdminComponent></AdminComponent>
</div>
</template>

<script lang="ts">
import {  Vue, Component, Prop } from 'vue-property-decorator'

import AdminComponent from "~/components/Admin.vue";
@Component({
  components: {
    AdminComponent
  }

})
export default class Admin extends Vue {
}
</script>

<style scoped>

</style>
