<template>
  <div>
      <table class="table" >
        <tbody>
          <tr v-for="i in $store.state.loginStore.loginForms" >
            <th>
              {{i.id}}
            </th>
            <th>
              {{i.email}}
            </th>
            <th>
              <button type="button" class="btn btn-info" v-on:click="detailView(i.id)">보러가기</button>
            </th>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script lang="ts">
import {  Vue, Component, Prop } from 'vue-property-decorator'
import {showForm} from "~/store/loginStore";

//@ts-ignore
@Component
export default class Admin extends Vue {

  loginForms: showForm[] = []
  mounted(){
    this.test();
  }

  test(){
    this.$store.dispatch('loginStore/test')
  }
  detailView(id:number){
    this.$store.dispatch('loginStore/getUser', id)

  }
}

</script>

<style scoped>




</style>
