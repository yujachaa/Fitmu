import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useStoryStore = defineStore('story', () => {

  const registStoryId = ref(1)
  const registFileName = ref("")
  
  const registStory = function(story, formData){
    axios.post("http://localhost:8080/story-api/regist", story)
    .then((response)=> {
      registStoryId.value = response.data
      return axios.post("http://localhost:8080/story-api/upload", formData)
    })
    .then((response)=>{
      const storyImage = {
        imageId : 0,
        storyId : registStoryId.value,
        fileName : response.data
      }
      registFileName.value = response.data
      return axios.post("http://localhost:8080/story-api/imageUpload", storyImage)
    })
    .then((response)=>{
      return axios.put("http://localhost:8080/story-api/updateFileName/" + registStoryId.value +"/" + registFileName.value)
    })
  }

  return {
    registStory,
   }
}, {persist : true})
