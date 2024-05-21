<template>
  <div class="container">
    <div class="header">
      <div>
        <RouterLink class="logo" :to="{ name: 'commu' }">
          <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
        </RouterLink>
      </div>
      <button class="PC" @click="registStory">등록하기</button>
    </div>
  </div>
  <hr>
  <div class="container">
    <div class="inputBox">
      <div>
        <div v-if="imagePreview">
          <img class="preview" :src="imagePreview" alt="File Preview" ref="img" @mouseup="clickPosition">
        </div>
        <label v-else class="top" for="input">
          <i class="bi bi-camera-fill"></i>
          <h5>사진을 끌어다 놓으세요</h5>
          <span>아쉽게도.. 한 장만 올릴 수 있어요.</span>
          <div class="PC">PC에서 불러오기</div>
        </label>
        <input class="nonedisplay" id="input" type="file" ref="fileInput" @change="handleFileUpload">
        <div class="picturefont">
          <i v-if="imagePreview" @click="deleteAll" class="bi bi-arrow-clockwise"></i>
          <i v-if="imagePreview" @click="deletePicture" class="bi bi-trash"></i>
        </div>
      </div>
      <div class="info">
        <label for="category">카테고리</label>
        <select id="category" name="category" v-model="story.category">
          <option disabled selected hidden>카테고리 선택</option>
          <option value="1">홈트레이닝</option>
          <option value="2">오운완</option>
          <option value="3">3대몇</option>
          <option value="4">운동팁</option>
          <option value="5">러닝</option>
        </select>
        <label for="title">제목</label>
        <input id="title" type="text" v-model="story.title" placeholder="게시글 제목">
        <label for="content">내용</label>
        <textarea id="content" type="text" v-model="story.content" placeholder="어떤 게시글인지 짧은 소개로 시작해보세요."></textarea>

      </div>
    </div>
    <!-- 태그 생성 -->
    <div v-for="(tag, index) in tagList" :key="tag.tagId"
      :style="{ position: 'absolute', left: tag.left + imgleft + 'px', top: tag.top + imgtop + 'px' }">
      <Popper :hover=true interactive disableClickAway>
        <button id="btn" ref="autobtn">+</button>
        <label class="btn" for="btn">+</label>
        <template #content>
          <div v-if="tag.productId == 0" class="content">
            <div>
              <input v-model="search" class="input" type="text">
              <button class="deletebtn" @click="deleteTag(tag.tagId, index)">취소</button>
            </div>
            <div v-if="search == '' || productStore.searchProducts.length == 0" class="noproduct">
              <span>최근 구매한 상품이 없어요.</span><br>
              <span>구매한 상품이 있다면 여기에 표시됩니다.</span>
            </div>
            <div class="croll" v-else>
              <div v-for="product in productStore.searchProducts" class="productList">
                <div class="garo">
                  <img class="mini-img" :src="`/src/assets/image/product/${getProductImage(product.productId)}`"
                    alt="이미지">
                  <div class="productInfo">
                    <span class="productBrand">{{ product.brand }}</span>
                    <span class="productName">{{ product.name }}</span>
                  </div>
                </div>
                <button class="lastbutton" type="submit" @click="storeTag(index, product.productId, product)">선택</button>
              </div>
            </div>
          </div>
          <div v-else class = "content2">
            <div class="garo">
              <img class="mini-img" :src="`/src/assets/image/product/${getProductImage(tag.productId)}`" alt="이미지">
              <div class="productInfo">
                <span class="productBrand">{{ getSelectedProductInfo(tag.productId).brand}}</span>
                <span class="productName">{{ getSelectedProductInfo(tag.productId).name }}</span>
              </div>
            </div>
            <button class="deletebtn" @click="deleteTag(tag.tagId, index)">삭제</button>
          </div>
        </template>
      </Popper>
    </div>
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { ref, computed, onUpdated, nextTick, watch, onBeforeUnmount, onBeforeMount } from 'vue'
import axios from 'axios'
import { useMouseInElement, watchDebounced } from '@vueuse/core'
import { useStoryStore } from "@/stores/story"
import { useProductStore } from "@/stores/product"


// 파일 업로드 관련
const storyStore = useStoryStore()
const productStore = useProductStore()
onBeforeMount(() => {
  productStore.getProductALLImages()
})

const file = ref(null)
const filename = ref("")
const imagePreview = ref(null)
const fileInput = ref(null)
const preViewfile = ref(null)
const selectedProduct = ref([])

const search = ref("")
watchDebounced(search, () => {
  productStore.searchProductforTag(search.value)
}, { debounce: 1000, maxWait: 3000 })

const getProductImage = function (productId) {
  if (productStore.productAllImages.filter(image => image.productId == productId).length == 0) {
    return "1.jpg";
  }
  return productStore.productAllImages.filter(image => image.productId == productId)[0].fileName
}

const getSelectedProductInfo = function(id){
  return selectedProduct.value.find(product => product.productId == id)
}

const handleFileUpload = (event) => {
  deleteAll()
  file.value = null
  file.value = fileInput.value.files[0]

  preViewfile.value = fileInput.value.files[0]
  const reader = new FileReader()

  reader.onload = () => {
    imagePreview.value = reader.result;
  }

  if (preViewfile.value) {
    reader.readAsDataURL(preViewfile.value)
  }
}

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
  const index2 = selectedProduct.value.findIndex(product => product.productId == id)
  tagList.value.splice(index, 1)
  isDelete = false
}

const storeTag = function (id, productId, product) {
  isDelete = false
  tagList.value[id].productId = productId
  selectedProduct.value.push(product)
  autobtn.value[id].click()
}

const deleteAll = function () {
  tagList.value = []
  isDelete = false
}

const deletePicture = function () {
  deleteAll()
  file.value = null
  imagePreview.value = null
  if (fileInput.value) {
    fileInput.value.value = ""
  }
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
.lastbutton {
  width: 50px;
  height: 35px;
  border: 1px solid #DBDDE0;
  border-radius: 8px;
  background-color: white;
}

.garo {
  display: flex;
}

.productBrand {
  color: #DBDDE0;
  font-weight: bold;
}

.productName {
  font-size: 18px;
  font-weight: bold;
}

.productInfo {
  display: flex;
  flex-direction: column;
}

.productList {
  width: 100%;
  display: flex;
  flex-direction: row;
  margin-top: 10px;
  margin-bottom: 10px;
  justify-content: space-between;
  padding-right: 20px;
  align-items: center;

}

.mini-img {
  width: 80px;
  height: 80px;
  border-radius: 8px;
  margin-right: 15px;
}

.croll {
  overflow-y: auto;
}

.noproduct {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #DBDDE0;
  font-weight: bold;
}

.deletebtn {
  border: 1px solid white;
  background-color: white;
  margin-left: 7px
}

.input {
  width: 85%;
  border: 1px solid #DBDDE0;
  height: 35px;
  border-radius: 8px;
}

.content {
  display: flex;
  flex-direction: column;
  width: 400px;
  height: 430px;
  padding: 20px;
  background-color: white;
  border: 1px solid #DBDDE0;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.content2{
  display: flex;
  width: 400px;
  height: 120px;
  padding: 20px;
  background-color: white;
  border: 1px solid #DBDDE0;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

#btn {
  display: none;
}

.btn {
  display: flex;
  border: 1px solid #34C5F0;
  width: 1px;
  height: 1px;
  justify-content: center;
  align-items: center;
  padding-left: 5px;
  padding-right: 6px;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
}

#category {
  height: 40px;
  margin-bottom: 15px;
  border-radius: 5px;
  border: 2px solid #DBDDE0;
  padding-left: 16px;
}

#title {
  height: 40px;
  margin-bottom: 15px;
  border-radius: 5px;
  padding-left: 20px;
  border: 2px solid #DBDDE0;
}

#content {
  height: 317px;
  resize: none;
  padding: 20px;
  border-radius: 5px;
  border: 2px solid #DBDDE0;
}

.picturefont {
  display: flex;
  margin-left: 30px;
  font-size: 30px;
  gap: 30px;
}

.picturefont i {
  cursor: pointer;
}

.info {
  width: 100%;
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  font-weight: bold;
}

.inputBox {
  display: flex;
  padding-left: 50px;
  padding-right: 50px;
}

.PC {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 160px;
  height: 40px;
  border-radius: 5px;
  background-color: #34C5F0;
  color: white;
  margin-top: 10px;
  border-color: #34C5F0;
}

.PC:hover {
  background-color: #1e91b4;
  cursor: pointer;
  color: white;
}

.nonedisplay {
  display: none;
}

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
  display: flex;
  flex-direction: column;
  margin: 20px;
  width: 500px;
  height: 500px;
  justify-content: center;
  align-items: center;
  background-color: #F7F9FA;
  border-radius: 5px;
}

.preview {
  margin: 20px;
  width: 500px;
  height: 500px;
  border-radius: 5px;
}

img {
  width: 500px;
  height: 500px;
}

.container {
  width: 100%;
  padding-left: 80px;
  padding-right: 80px;
}

.header {
  display: flex;
  padding-top: 20px;
  justify-content: space-between;
}
</style>