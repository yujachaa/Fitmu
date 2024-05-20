<template>
    <div class="container1">
        <RouterLink class="logo" :to="{ name: 'commu' }">
            <span class="jua-regular">핏뮤<span class="fitmu">Fitmu</span><span class = "for">for</span> <span class ="seller">Seller</span></span>
        </RouterLink>
    </div>
    <hr>
    <div class = "container2">
        <div class="input">
            <div class = "inputType">
                <label for="category">카테고리</label>
                <select class = "inputBox" id="category" v-model="product.categoryId">
                    <option value="null">선택해주세요</option>
                    <option value="1">프로틴분말</option>
                    <option value="2">프로틴음료</option>
                    <option value="3">보충제</option>
                    <option value="4">닭가슴살</option>
                    <option value="5">소시지</option>
                    <option value="6">도시락</option>
                    <option value="7">간식</option>
                    <option value="8">홈트용품</option>
                    <option value="9">상의</option>
                    <option value="10">하의</option>
                    <option value="11">악세사리</option>
                </select>
            </div>
            <div class = "inputType">
                <label for="brand">브랜드</label>
                <input class = "inputBox" id="brand" type="text" v-model="product.brand" placeholder="브랜드"
            </div>
            <div class = "inputType">
                <label for="name">상품명</label>
                <input class = "inputBox" id="name" type="text" v-model="product.name" placeholder="상품명">
            </div>
            <div class = "inputType">
                <label for="price">가격</label>
                <input class = "inputBox" id="price" type="text" v-model="product.price" placeholder="가격">
            </div>
            <div class = "inputType">
                <label for="discountRate">할인율</label>
                <input class = "inputBox" id="discountRate" type="text" v-model="product.discountRate" placeholder="할인률">
            </div>
            <div class = "inputType">
                <label for="deliveryFee">배송비</label>
                <input class = "inputBox" id="deliveryFee" type="text" v-model="product.deliveryFee" placeholder="배송비">
            </div>
            <div class = "inputType">
                <label for="specialPrice">특가</label>
                <input class = "inputBox" id="specialPrice" type="text" v-model="product.specialPrice" placeholder="특가">
            </div>
            <div class = "inputType">
                <label for="productDetail">제품 설명</label>
                <textarea id="productDetail" v-model="product.productDetail" placeholder="제품 설명"></textarea>
            </div>
            <label class = "filearea" for="file">
                <i class="bi bi-camera-fill"></i>
                <span>파일을 올려주세요.</span>
            </label>
            <input id = "file" type="file" ref="fileInput" @change="handleFileUpload" multiple>
            <button class = "btn2" @click="registProduct">상품 등록</button>
            <div v-if="imagePreviews.length">
                <img class="preview" v-for="(image, index) in imagePreviews" :key="index" :src="image">
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref, computed, watch } from 'vue'
import { useProductStore } from "@/stores/product"

const productStore = useProductStore()
const route = useRoute()
const router = useRouter()

const product = ref({
    productId: 0,
    sellerId: 0,
    categoryId: 0,
    brand: "",
    name: "",
    price: 0,
    discountRate: 0,
    deliveryFee: 0,
    specialPrice: 0,
    productDetail: "",
    productImage: "",
})
const files = ref([])
const imagePreviews = ref([])
const fileInput = ref(null)

const handleFileUpload = (event) => {
    files.value = []
    files.value = fileInput.value.files
    imagePreviews.value = []

    Array.from(files.value).forEach(file => {
        const reader = new FileReader()
        reader.onload = (e) => {
            imagePreviews.value.push(e.target.result)
        }
        reader.readAsDataURL(file)
    })
}

const registProduct = function () {
    productStore.registProduct(product.value, files.value)
}


</script>

<style scoped>
.for{
    font-size : 30px;
    margin-left : 10px;
}

.seller{
    font-size : 35px;
    color : pink;
}
.filearea{
    display : flex;
    flex-direction: column;
    width : 100%;
    height : 100px;
    border-radius: 8px;
    border : 1px solid #EDEDED;
    background-color: #EDEDED;
    justify-content: center;
    align-items: center;
}

.btn2{
    width : 100%;
    height : 45px;
    color : white;
    font-weight : bold;
    border : 1px solid #34C5F0;
    background-color: #34C5F0;
    border-radius: 8px;
    margin-top : 30px;
    margin-bottom : 100px;
}
.btn2:hover{
    border : 1px solid #479db8;
    background-color: #479db8;
}
#file{
    display : none;
}
#productDetail{
    resize : none;
    border : 2px solid #EDEDED;
    height : 300px;
    border-radius: 5px;
    padding-left : 15px;
    padding-top : 10px;
}
.inputBox{
    border : 2px solid #EDEDED;
    height : 40px;
    border-radius: 5px;
    padding-left : 15px;
}
.inputType{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width : 100%;
    margin-bottom : 20px;
}
.container1{
    width : 100%;
    padding-left : 100px;
    padding-top : 15px;
}

.container2{
    width : 100%;
    padding-left : 100px;
    padding-right : 100px;
}
.preview {
    width: 100px;
    height: 100px;
}

.input {
    display: flex;
    width : 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-left : 400px;
    padding-right : 400px;
    font-weight : bold;
    font-size  : 18px;
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
    font-size : 40px;
}
</style>