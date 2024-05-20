import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_COMMENT_API = `http://localhost:8080/comment-api`

export const useCommentStore = defineStore('comment', () => {

  const commentList = ref([])

  const registComment = function (comment) {
    axios.post({
      url: REST_COMMENT_API + "/regist",
      data: comment
    })
      .then((res) => {
        //댓글 등록한 다음에 이 게시글에 달린 리뷰 리스트 다시 전체 조회
        axios.get(REST_COMMENT_API + "/story/" + comment.storyId)
          .then((res) => {
            commentList.value = res.data
          })
      })
      .then((res) => {
        location.reload()
      })
  }

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
