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
    const storyScrap = ref([])
    const productScrap = ref([])


    //유저의 게시글 스크랩 목록 가져오기
    const getStoryScrap = function(){
      if(sessionStorage.getItem("loginUser") == null){
        return
      }
      axios.get("http://localhost:8080/user-api/user/" + sessionStorage.getItem("loginUser") + "/story-scrap")
     .then((response)=>{
      storyScrap.value = response.data
     })
    }
    

    const YesBook = function(id){
      axios.post("http://localhost:8080/story-api/story/" + id + "/user/" + sessionStorage.getItem("loginUser"))
    }
    const NoBook = function(id){
      axios.delete("http://localhost:8080/story-api/story/" + id + "/user/" + sessionStorage.getItem("loginUser"))
    }

    const getTags = function(){
      axios.get("http://localhost:8080/tag-api/story/" + route.params.storyId)
      .then((response)=>{
        tags.value = response.data
        // console.log(tags.value)
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

    //스크랩수배열은 같이 사용
    const scrapCntList = ref([])
    //인기 리스트의 스크랩수 배열 가져오기
    const getPopularScrapCntList = function(){
      scrapCntList.value=[]
      axios
      .get(REST_STORY_API + "/story/latest")
      .then((res) => {
        popularList.value = res.data;
      })
      .then((res) => {
        popularList.value.forEach((story, index) =>{
          axios
          .get(REST_STORY_API + "/scrap/" + story.storyId)
          .then((res) => {
            scrapCntList.value[index] = res.data;
          })
          .catch((err) => {
            console.log(err);
          });
        })
      })
      .catch((err) => {
        console.log(err);
      });
    }
    //카테고리별 스토리의 스크랩 개수 배열 가져오기
    const getCategoryScrapCntList = function(categoryId){
      scrapCntList.value=[]
      axios({
        url:REST_STORY_API + "/search", 
        params : {key:"category", word: categoryId}})
      .then((res) => {
        categoryStoryList.value = res.data;
      })
      .then((res) => {
        categoryStoryList.value.forEach((story, index) =>{
          axios
          .get(REST_STORY_API + "/scrap/" + story.storyId)
          .then((res) => {
            scrapCntList.value[index] = res.data;
          })
          .catch((err) => {
            console.log(err);
          });
        })
      })
      .catch((err) => {
        console.log(err);
      });
    }
    //검색결과 스토리의 스크랩 개수 배열 가져오기
    const getSearchScrapCntList = function(){
      scrapCntList.value=[]
      if(searchList){
        searchList.value.forEach((story, index) =>{
          axios
          .get(REST_STORY_API + "/scrap/" + story.storyId)
          .then((res) => {
            scrapCntList.value[index] = res.data;
          })
          .catch((err) => {
            console.log(err);
          });
        })
      }
    }

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

    //카테고리별 게시글 가져오기
    const categoryStoryList = ref([])
    const getCategoryList = function(categoryId){
      axios({
        url:REST_STORY_API + "/search", 
        params : {key:"category", word: categoryId}})
      .then((res) => {
        categoryStoryList.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    }

      //게시글 검색 리스트 가져오기 -> 제목, 내용
  const searchList = ref([])
  const getSearchList = function(word){
    searchList.value = []
    axios({
      url:REST_STORY_API + "/search",
      method:"GET",
      params:{
        key: "title",
        word:word
      }
    })
    .then((res) =>{
      searchList.value = res.data
    })
    .then((res)=>{
      axios({
        url:REST_STORY_API + "/search",
        method:"GET",
        params:{
          key: "content",
          word:word
        }
    })
    .then((res)=>{
      searchList.value.push(...res.data);
    })
    .catch((err)=>{
      console.log(err)
    })
  })
    .catch((err)=>{
      console.log(err)
    })
  }

  const searchWord = ref('')

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
    tags,
    YesBook,
    getStoryScrap,
    storyScrap,
    NoBook,
    scrapCntList,
    categoryStoryList,
    getCategoryList,
    getCategoryScrapCntList,
    getPopularScrapCntList,
    searchList,
    getSearchList,
    getSearchScrapCntList,
    searchWord,


    };
  },
  { persist: true }
);
