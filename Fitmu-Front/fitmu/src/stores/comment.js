import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_COMMENT_API = `http://localhost:8080/comment-api`

export const useCommentStore = defineStore('comment', () => {

  const commentList = ref([])

  //댓글 등록하기
  const registComment = function (comment) {
    axios.post(REST_COMMENT_API + "/regist", comment)
      .then((res) => {
        location.reload()
      })
      .catch((err)=>{
        console.log(err)
      })
  }

  //게시글에 달린 댓글리스트 가져오기
  const getCommentList = function(storyId){
    axios.get(REST_COMMENT_API + "/story/" + storyId)
    .then((res) => {
      commentList.value = res.data
    })
  }



  return {
    registComment,
    commentList,
    getCommentList,




  }
}, { persist: true })
