<template>
  <div>
    <!-- 로고 -->
    <div>
      <RouterLink class="logo" :to="{ name: 'commu' }">
        <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
      </RouterLink>
    </div>

    <!-- 파일 업로드 -->
    <div class="top">
      <input type="file" ref="fileInput" @change="handleFileUpload">
    </div>

    <!-- 파일 미리보기 -->
    <div v-if="imagePreview" class="preview">
      <img :src="imagePreview" alt="File Preview" ref="img" @mouseup="clickPosition">
    </div>

    <!-- 태그 생성 -->
    <div class="plus" v-for="(tag, index) in tagList" :key="tag.tagId"
      :style="{ position: 'absolute', left: tag.left + imgleft - 16 + 'px', top: tag.top + imgtop - 16 + 'px' }">
      <Popper :hover=true class="popper" interactive arrow disableClickAway>
        <button class="btn" ref="autobtn">+</button>
        <template #content>
          <input type="text">
          <p>asdasdasdasd</p>
          <button @click="deleteTag(tag.tagId, index)">삭제</button>
          <button type="submit" @click="storeTag(index)">저장</button>
        </template>
      </Popper>
    </div>

    <button @click="registStory">등록하기</button>
    <button @click="deleteAll">태그 전부 지우기</button>

    <div>
      <label for="title">제목</label>
      <input id="title" type="text" v-model="story.title">
      <label for="content">내용</label>
      <input id="content" type="text" v-model="story.content">
      <select name="category" v-model="story.category">
        <option value="null" selected>선택하세요</option>
        <option value="1">홈트레이닝</option>
        <option value="2">오운완</option>
        <option value="3">3대몇</option>
        <option value="4">운동팁</option>
        <option value="5">러닝</option>
      </select>

    </div>
  </div>

</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { ref, computed, onUpdated, nextTick, watch, onBeforeUnmount } from 'vue'
import axios from 'axios'
import { useMouseInElement } from '@vueuse/core'
import { useStoryStore } from "@/stores/story"

// 파일 업로드 관련
const storyStore = useStoryStore()

const file = ref(null)
const filename = ref("")
const imagePreview = ref(null)
const fileInput = ref(null)

const handleFileUpload = (event) => {
  deleteAll()
  file.value = null
  file.value = fileInput.value.files[0]

  const preViewfile = fileInput.value.files[0]
  const reader = new FileReader()

  reader.onload = () => {
    imagePreview.value = reader.result;
  }

  if (preViewfile) {
    reader.readAsDataURL(preViewfile)
  }
}

// axios.post("http://localhost:8080/story-api/upload", formData, {
//   headers: {
//     'Content-Type': 'multipart/form-data'
//   }
// })
//   .then((response) => {
//     filename.value = "src/assets/image/" + response.data
//   })


// 태그 관련
const img = ref(null)
const { elementPositionX, elementPositionY, elementX, elementY } = useMouseInElement(img)

const autobtn = ref(null)
let id = 1
let isDelete = false
const imgleft = ref(0)
const imgtop = ref(0)
const tagList = ref([])

const clickPosition = function () {
  if (isDelete) {
    return
  }
  imgleft.value = elementPositionX.value
  imgtop.value = elementPositionY.value
  const tag = {
    tagId: id++,
    imageId: 0,
    productId: 0,
    left: elementX.value,
    top: elementY.value,
    storyId: 0,
  }
  tagList.value.push(tag)
  isDelete = true
}
const deleteTag = function (id) {
  const index = tagList.value.findIndex(tag => tag.tagId == id)
  tagList.value.splice(index, 1)
  isDelete = false
}

const storeTag = function (id) {
  isDelete = false
  autobtn.value[id].click()
}

const deleteAll = function () {
  tagList.value = []
}

// 스토리 관련
const story = ref({
  storyId: 0,
  userId: sessionStorage.getItem("loginUser"),
  title: "",
  image: "",
  content: "",
  productLink: "",
  liked: 0,
  category: 0,
  viewCnt: 0,
})

const registStory = function () {
  // 스토리 등록
  story.value.userId = sessionStorage.getItem("loginUser")
  const formData = new FormData()
  formData.append('file', file.value)
  storyStore.registStory(story.value, formData)
}

</script>
<script>
import { defineComponent, ref } from "vue"
import Popper from "vue3-popper";

export default defineComponent({
  name: 'App',
  components: {
    Popper
  },
})
</script>

<style scoped>
.logo {
  margin-right: auto;
  text-decoration: none;
  color: black;
}

.fitmu {
  color: #34C5F0;
  font-size: smaller;
  margin-left: 5px;

}

.jua-regular {
  font-family: "Jua", sans-serif;
  font-weight: 100;
  font-style: normal;
}

.top {
  margin: 20px;
}

img {
  width: 500px;
  height: 500px;
}
</style>