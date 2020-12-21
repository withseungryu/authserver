<template>
  <div>
    <div class="wrapper fadeInDown">
      <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
          <img src="https://static-cdn.onstove.com/0.0.14/img/gnb/bi.svg" id="icon" alt="User Icon" />
        </div>

        <!-- Login Form -->
<!--        <form v-on:submit="pushLogin()">-->
          <input type="text"  v-model="email" id="login" class="fadeIn second" name="login" placeholder="login">
          <input type="password"  v-model="password" id="password" class="fadeIn third" name="login" placeholder="password">
<!--          <input type="submit" class="fadeIn fourth"  v-on:click="pushLogin()">-->
          <button v-on:click="pushLogin()" class="fadeIn fourth">로그인</button>
          <button v-on:click="goSignUp()" class="fadeIn fourth">회원가입</button>
<!--        </form>-->

        <!-- Remind Passowrd -->
        <div id="formFooter">
          <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

      </div>
    </div>
  </div>
</template>

<script lang="ts">
import 'bootstrap/dist/css/bootstrap.min.css'
import '@/assets/css/main.css'
import {  Vue, Component, Prop } from 'vue-property-decorator'
import {LoginForm} from "~/store/loginStore";

//@ts-ignore
@Component
export default class Login extends Vue{


  email: string = ""
  password: string = ""
  loginForm: LoginForm = {email: "", password: ""}

  pushLogin(){
    console.log(this.email + this.password)
    this.loginForm.email = this.email;
    this.loginForm.password = this.password
    this.$store.dispatch('loginStore/postLogin', this.loginForm);
    // this.$router.push('admin')
  }
  goSignUp(){
    this.$router.push('signup')
  }


}
</script>

<style scoped>

</style>
