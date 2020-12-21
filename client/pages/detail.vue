<template>
  <DetailComponent></DetailComponent>
</template>

<script>
import {  Vue, Component, Prop } from 'vue-property-decorator'

import DetailComponent from "~/components/Detail.vue";
@Component({
  components: {
    DetailComponent
  }

})
export default class Detail extends Vue {

}
</script>

<style scoped>

</style>
