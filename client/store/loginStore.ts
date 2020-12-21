// @ts-ignore
import {Module, VuexModule, Mutation, Action} from 'vuex-module-decorators';
import AxiosService from '@/service/axios.service';
import axios from "axios";
// axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
export interface LoginForm{
  email:string
  password: string

}

export interface showForm{
  id: number
  email: string
  password: string
  __proto__: Object
}

@Module
export default class FilterStore extends VuexModule {
  storage = window.sessionStorage
  loginForms: showForm[] = []
  email:string= ""
  @Mutation
  backPage(){
    alert("관리자 권한이 없습니다.")

    window.location.href = 'http://localhost:3000/login';


  }

  @Mutation
  saveStore(datas: showForm[]){
    this.loginForms = datas

  }

  @Mutation
  saveEmail(email: string){
    console.log(email)
    this.email = "alstmd"
    alert("보러가시겠습니까??")
    window.location.href = 'http://localhost:3000/detail'
  }




  @Action({rawError: true})
  postLogin(loginForm: LoginForm) {

    axios.post('http://localhost:8080/login/authenticate', {'email': loginForm.email, 'password': loginForm.password} ).then(res=>{

      localStorage.setItem("jwt-token", res.data)

      // localStorage.setItem("jwt-refresh-token", res.data.refresh)
      window.location.href = 'http://localhost:3000/admin';
      // this.context.commit('testMutation')

    }).catch(e => {
      console.log(e)
      alert("틀렸습니다. 다시 입력해주세요")
      window.location.href = 'http://localhost:3000/login';
    }
  )
  }

  @Action({rawError: true})
  refresh(loginForm: LoginForm) {

    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("jwt-refresh-token");
    axios.post('http://localhost:8080/refresh', {'email': loginForm.email, 'password': loginForm.password} ).then(res=>{

      localStorage.setItem("jwt-token", res.data.token)
      // localStorage.setItem("jwt-refresh-token", res.data.refresh)
      window.location.href = 'http://localhost:3000/admin';
      // this.context.commit('testMutation')

    }).catch(e => {
        console.log(e)
        window.location.href = 'http://localhost:3000/login';
      }
    )
  }

  @Action({rawError: true})
  postSignUp(loginForm: LoginForm) {
    console.log("??")
    axios.post('http://localhost:8080/login/signUp', {'email': loginForm.email, 'password': loginForm.password} ).then(res=>{
      console.log("success")
      window.location.href = 'http://localhost:3000/login';
    }).catch(e=>{
      alert("아이디가 중복됩니다")
    })
  }

  @Action({rawError: true})
  test(){
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("jwt-token");
    axios.get('http://localhost:8080/admin').then(res=>{
      console.log(res.data)
      this.context.commit('saveStore', res.data)
    }).catch(e=>{
      console.log(e)
      // this.context.dispatch('refresh')
      this.context.commit('backPage')
    })

  }

  @Action({rawError: true})
  getUser(id:number){
    console.log(id)
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("jwt-token");
    axios.get('http://localhost:8080/admin/'+id).then(res=>{
      console.log(res.data.email)
      this.context.commit('saveEmail', res.data.email)

    }).catch(e=>{
      console.log(e)
      this.context.commit('backPage')
    })

  }



}
