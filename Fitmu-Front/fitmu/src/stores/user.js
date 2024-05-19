import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import { useRoute } from 'vue-router'

const REST_USER_API = `http://localhost:8080/user-api`

export const useUserStore = defineStore('user', () => {

  const route = useRoute();
  const login = function (user) {
    axios({
      url: REST_USER_API + "/login",
      method: 'POST',
      data: user
    })
      .then((res) => {
        sessionStorage.setItem("loginUser", res.data.userId)
        sessionStorage.setItem("role", res.data.role)
        // console.log(sessionStorage.getItem("loginUser"))

        router.push({ name: 'commu' })

      })
      .catch((err) => {
        console.log(err)
      })
  }

  const logout = function () {
    axios.get({
      url: REST_USER_API + "/logout"
    })
      .then((res) => {
        sessionStorage.clear();
        console.log(route.fullPath)
        if (route.fullPath == '/') {
          location.reload()
        } else {
          router.push({ name: 'commu' })
        }

        // router.go()
        // location.reload()
      })
  }

  const kakaoLogin = function(url){
    axios.post(url)
    .then((response)=>{
      console.log(response)
      // router.push({ name: 'commu' })
    })
  }

  const registUser = function (user) {
    return new Promise((resolve, reject) => {
      let result = false;
      axios({
        url: REST_USER_API + "/regist",
        method: 'POST',
        data: user
      })
        .then((res) => {
          if (res.status === 200) {
            console.log("회원가입성공");
            result = true;
          } else {
            console.log("회원가입실패");
            result = false;
          }
          resolve(result);
        })
        .catch((err) => {
          console.error(err);
          reject(err);
        });
    });
  }

  const emailCheck = function (email) {
    return new Promise((resolve, reject) => {
      const formData = new FormData();
      formData.append('email', email);

      axios({
        url: REST_USER_API + "/email",
        method: 'POST',
        data: formData
      })
        .then((res) => {
          if (res.status === 200) {
            // console.log("200맞아여");
            resolve(true);
          } else {
            // console.log("200아니에여");
            resolve(false);
          }
        })
        .catch((err) => {
          console.log(err);
          reject(err);
        });
    });
  }

  //유저 전체목록 가져오기
  const userList = ref([])
  const getUserList = function () {
    axios.get(REST_USER_API + "/")
    .then((res)=>{
      userList.value = res.data
    })
    .catch((err)=>{
      console.log(err)
    })
  }

  //유저 한명 가져오기
  const user = ref([])
  const getUser = function(){
    axios.get(REST_USER_API + "/" + sessionStorage.getItem("loginUser"))
    .then((res)=>{
      user.value = res.data
    })
    .catch((err)=>{
      console.log(err)
    })
  }

  //유저가 팔로우하는 사람들 가져오기 (팔로잉)
  const followingList = ref([])
  const getFollowing = function(userId){
    axios.get(REST_USER_API + "/user/" + userId + "/follower")
    .then((res)=>{
      followingList.value = res.data
    })
    .catch((err)=>{
      console.log(err)
    })
  }
  //유저의 팔로워들 가져오기 (팔로워)
  const followerList = ref([])
  const getFollower = function(userId){
    axios.get(REST_USER_API + "/user/" + userId + "/followee")
    .then((res)=>{
      followerList.value = res.data
    })
    .catch((err)=>{
      console.log(err)
    })
  }
  //유저의 상품 스크랩북 (찜목록) 가져오기
  const productScrapList = ref([])
  const getProductScrap = function(userId){
    axios.get(REST_USER_API + "/user/" + userId + "/product-scrap")
    .then((res)=>{
      productScrapList.value = res.data
    })
    .catch((err)=>{
      console.log(err)
    })
  }
  //유저의 게시글 스크랩북 가져오기
  const storyScrapList = ref([])
  const getStoryScrap = function(userId){
    axios.get(REST_USER_API + "/user/" + userId + "/story-scrap")
    .then((res)=>{
      storyScrapList.value = res.data
    })
    .catch((err)=>{
      console.log(err)
    })
  }

  return {
    login,
    logout,
    registUser,
    emailCheck,
    getUserList,
    userList,
    user,
    getUser,
    followingList,
    getFollowing,
    followerList,
    getFollower,
    productScrapList,
    getProductScrap,
    storyScrapList,
    getStoryScrap,
    kakaoLogin,

  }
}, { persist: true })
