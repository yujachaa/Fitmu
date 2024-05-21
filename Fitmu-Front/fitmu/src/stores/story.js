import { ref, computed } from "vue";
import { defineStore } from "pinia";
import {useRouter, useRoute} from "vue-router";
import axios from "axios";

const REST_STORY_API = `http://localhost:8080/story-api`;

export const useStoryStore = defineStore(
  "story",
  () => {
    const router = useRouter()
    const route = useRoute()
    const registStoryId = ref(1)
    const registFileName = ref("")
    const imageId = ref(0)
    const tags = ref([])

    const getTags = function(){
      axios.get("http://localhost:8080/tag-api/story/" + route.params.storyId)
      .then((response)=>{
        tags.value = response.data
      })
    }

    const registStory = function (story, formData, tagList) {
      axios
        .post(REST_STORY_API + "/regist", story)
        .then((response) => {
          registStoryId.value = response.data;
          return axios.post(REST_STORY_API + "/upload", formData);
        })
        .then((response) => {
          const storyImage = {
            imageId: 0,
            storyId: registStoryId.value,
            fileName: response.data,
          };
          registFileName.value = response.data;
          return axios.post(REST_STORY_API + "/imageUpload", storyImage);
        })
        .then((response) => {
          imageId.value = response.data
          return axios.put(
            REST_STORY_API +
              "/updateFileName/" +
              registStoryId.value +
              "/" +
              registFileName.value
          );
        })
        .then((response) => {
          for(let i=0; i < tagList.length; i++){
            tagList[i].imageId = imageId.value
            tagList[i].storyId = registStoryId.value
          }
          const promises = tagList.map((tag) =>
            axios.post("http://localhost:8080/tag-api/regist", tag)
          )
          return Promise.all(promises);
        })
        .then((response)=>{
          router.push({name : "storyDetail", params : {storyId : registStoryId.value}})
        })
        .catch((err) => {
          console.log(err);
        });
    };

    //현재 사용자 전체 게시글 가져오기
    const userStoryList = ref([]);
    const recent6List = ref([]);
    const getUserStory = function () {
      userStoryList.value = [];
      recent6List.value = [];
      axios
        .get(REST_STORY_API + "/user/" + sessionStorage.getItem("loginUser"))
        .then((res) => {
          userStoryList.value = res.data;
        })
        .then(() => {
          const sortedStories = userStoryList.value.sort(
            (a, b) => b.storyId - a.storyId
          );
          recent6List.value = sortedStories.slice(0, 6);
        })
        .catch((err) => {
          console.log(err);
          // userStoryList.value = []
        });
    };


  //조회수 순 게시글 가져오기 (인기글)
  const popularList = ref([])
  const popular10List = ref([]) //인기글 6개
  const getPopularList = function(){
    popularList.value = []
    popular10List.value = []
    axios.get(REST_STORY_API + "/story/popular")
    .then((res)=>{
      popularList.value = res.data
    })
    .then((res)=>{
      popular10List.value = popularList.value.slice(0,10);
    })
    .catch((err)=>{
      console.log(err)
    })
  }

    //지금 조회하는 게시글 가져오기
    const story = ref({});
    const getStory = function (storyId) {
      axios
        .get(REST_STORY_API + "/" + storyId)
        .then((res) => {
          story.value = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    };

    //지금 조회하는 게시글의 스크랩수 가져오기
    const storyScrapCnt = ref(0);
    const getStoryScrapCount = function (storyId) {
      axios
        .get(REST_STORY_API + "/scrap/" + storyId)
        .then((res) => {
          storyScrapCnt.value = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    };

    //최신글 가져오기
    const recentList = ref([]);
    const recent4List = ref([]);
    const getRecentList = function () {
      recentList.value = [];
      recent4List.value = [];
      axios
        .get(REST_STORY_API + "/story/latest")
        .then((res) => {
          recentList.value = res.data;
        })
        .then((res) => {
          recent4List.value = recentList.value.slice(0, 4);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    //랜덤글 하나 가져오기
    const randomStory = ref({});
    const totalStoryList = ref([]);
    const randomStoryNick = ref("");
    const getRandom = function () {
      totalStoryList.value = [];
      randomStory.value = {};
      randomStoryNick.value = "";
      //전체글가져온다음에 랜덤
      axios
        .get(REST_STORY_API + "/story")
        .then((res) => {
          totalStoryList.value = res.data;
        })
        .then((res) => {
          randomStory.value =
            totalStoryList.value[
              Math.floor(Math.random() * totalStoryList.value.length)
            ];
        })
        .catch((err) => {
          console.log(err);
        });
    };

  return {
    registStory,
    userStoryList,
    getUserStory,
    recent6List,
    story,
    getStory,
    storyScrapCnt,
    getStoryScrapCount,
    popularList,
    popular10List,
    getPopularList,
    recentList,
    recent4List,
    getRecentList,
    randomStory,
    totalStoryList,
    getRandom,
    getTags,


    };
  },
  { persist: true }
);
