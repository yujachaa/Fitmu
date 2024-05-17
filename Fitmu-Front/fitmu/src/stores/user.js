import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import {useRoute} from 'vue-router'

const REST_USER_API = `http://localhost:8080/user-api`


export const useUserStore = defineStore('user', () => {

  const route = useRoute();
  const login = function(user){
    axios({
      url:REST_USER_API + "/login",
      method:'POST',
      data:user
    })
    .then((res)=>{
      sessionStorage.setItem("loginUser", res.data.userId)
      // console.log(sessionStorage.getItem("loginUser"))

      router.push({name:'commu'})

    })
    .catch((err)=>{
      console.log(err)
    })
  }

  const logout = function(){
    axios.get({
      url:REST_USER_API + "/logout"
    })
    .then((res)=>{
      sessionStorage.clear();
      console.log(route.fullPath)
      if(route.fullPath == '/'){
        location.reload()
      } else{
        router.push({name:'commu'})

      }
        
      // router.go()
      // location.reload()
    })

  }

  return {
    login,
    logout,

   }
}, {persist : true})
