import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_STORY_API = `http://localhost:8080/story-api`

export const useStoryStore = defineStore('story', () => {

  const registStoryId = ref(1)
  const registFileName = ref("")
  
  const registStory = function(story, formData){
    axios.post(REST_STORY_API + "/regist", story)
    .then((response)=> {
      registStoryId.value = response.data
      return axios.post(REST_STORY_API + "/upload", formData)
    })
    .then((response)=>{
      const storyImage = {
        imageId : 0,
        storyId : registStoryId.value,
        fileName : response.data
      }
      registFileName.value = response.data
      return axios.post(REST_STORY_API + "/imageUpload", storyImage)
    })
    .then((response)=>{
      return axios.put(REST_STORY_API + "/updateFileName/" + registStoryId.value +"/" + registFileName.value)
    })
  }

  //현재 사용자 전체 게시글 가져오기
  const userStoryList = ref([])
  const recent6List = ref([])
  const getUserStory = function(){
    axios.get(REST_STORY_API + "/user/" + sessionStorage.getItem("loginUser"))
    .then((res)=>{
      userStoryList.value = res.data
    })
    .then(()=>{
      const sortedStories = userStoryList.value.sort((a, b) => b.storyId - a.storyId);
      recent6List.value = sortedStories.slice(0, 6);
      console.log(recent6List.value)
    })


  }

  return {
    registStory,
    userStoryList,
    getUserStory,
    recent6List,

   }
}, {persist : true})
