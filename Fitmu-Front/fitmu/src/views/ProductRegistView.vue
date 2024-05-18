<template>
    <div>
        <div>
            <RouterLink class="logo" :to="{ name: 'commu' }">
                <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
            </RouterLink>
        </div>
        <div class = "input">
            <label for="category">카테고리</label>
            <select id="category" v-model = "product.categoryId">
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
            <label for="brand">브랜드</label>
            <input id = "brand" type="text" v-model = "product.brand">
            <label for="name">상품 이름</label>
            <input id = "name" type="text" v-model = "product.name">
            <label for="price">가격</label>
            <input id = "price" type="text" v-model = "product.price">
            <label for="discountRate">할인율</label>
            <input id = "discountRate" type="text" v-model = "product.discountRate">
            <label for="deliveryFee">배송비</label>
            <input id = "deliveryFee" type="text" v-model = "product.deliveryFee">
            <label for="specialPrice">특가</label>
            <input id = "specialPrice" type="text" v-model = "product.specialPrice">
            <label for="productDetail">상세 설명</label>
            <textarea id="productDetail" v-model = "product.productDetail"></textarea>
            <input type="file" ref = "files" multiple>
            <button @click = "registProduct">우선은 상품만</button>
            <button @click = "uploadFile">이건 파일만</button>
        </div>
    </div>
</template>

<script setup>
import {useRoute, useRouter} from 'vue-router'
import {ref, computed, watch} from 'vue'
import {useProductStore} from "@/stores/product"

const productStore = useProductStore()
const route = useRoute()
const router =useRouter()

const product = ref({
    productId : 0,
    sellerId : 0,
    categoryId : 0,
    brand : "",
    name : "",
    price : 0,
    discountRate : 0,
    deliveryFee : 0,
    specialPrice : 0,
    productDetail : "",
    productImage : "",
})

const registProduct = function(){
    productStore.registProduct(product.value)
}

const files = ref([])

const uploadFile = function(){
    productStore.uploadFile(files.value)
}

</script>

<style scoped>
.input{
    display : flex;
    flex-direction: column;
    width : 500px;
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
</style>