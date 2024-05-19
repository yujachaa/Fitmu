<template>
    <div class="container">
        <div class="pic-div">
            <img class="main-pic" :src="`/src/assets/image/product/${productImages[0].fileName}`" alt="이미지">
        </div>
        <div class="info">
            <p class="brand">{{ product.brand }}</p>
            <div class="namediv">
                <h3 class="name">{{ product.name }}</h3>
                <i class="heart bi bi-heart"></i>
            </div>
            ⭐<span class="rating" v-if="product.ratingCnt != 0">{{ product.ratingSum / product.ratingCnt }}</span>
            <span class="rating" v-else>0</span>
            <span>{{ product.ratingCnt }}개 리뷰</span>
            <br>
            <span class="discount">{{ product.discountRate }}%</span>
            <span class="price">{{ product.price }}원</span><br>
            <div class="price2">
                <p class="specialPrice">{{ product.specialPrice }}</p>
                <p>원</p>
                <p class="xmrrk">특가</p>
            </div>
            <div class="delivery">
                <span class="delivery2">배송</span>
                <span class="deliveryFee" v-if="product.deliveryFee != 0">{{ product.deliveryFee }}원</span>
                <span class="deliveryFee" v-else>무료배송</span>
            </div>
            <hr>
            <button>바로 구매</button>
        </div>
    </div>
    <div class="list">
        <div class="router">
            <RouterLink :to="{name : 'productinfo', params : {'productId' : productId}}">상품정보</RouterLink>
            <RouterLink :to="{name : 'productreview', params : {'productId' : productId}}">리뷰</RouterLink>
            <RouterLink :to="{name : 'productinquiry', params : {'productId' : productId}}">문의</RouterLink>
        </div>
    </div>
    <div>
        <RouterView/>
    </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useProductStore } from '@/stores/product'

const route = useRoute()
const router = useRouter()
const productStore = useProductStore()

const productId = ref(route.params.productId)
const product = ref(productStore.product)
const productImages = ref(productStore.productImages)

onMounted(() => {
    productStore.getProduct(productId.value)
    productStore.getProductImages(productId.value)

    console.log(productImages.value)
    // 여기 문의도 불러와야 함!!
})
</script>

<style scoped>
.router {
    display: flex;
    justify-content: center;
    align-items: center;
    height : 40px;
}

.router a {
    text-decoration: none;
    font-weight: bold;
    margin-left: 50px;
    margin-right: 50px;
    color : black;
}

.router a:hover {
    color : #34C5F0
}

hr {
    color: #EDEDED;
}

.list {
    background-color: #FAFAFA;
    border: 2px solid #EDEDED;
}

.heart {
    font-size: 30px;
    margin-right: 30px;
}

.rating {
    margin-right: 10px;
    margin-left: 5px;
}

.xmrrk {
    color: white;
    font-size: 12px;
    background-color: #FF7777;
    padding: 5px;
    border-radius: 10px;
    font-weight: bold;
    margin-left: 6px;
}

.price2 {
    display: flex;
    font-size: 25px;
    align-items: center;

}

.container {
    width: 100%;
    display: flex;
    padding-left: 80px;
    padding-right: 80px;
}

.pic-div {
    width: 600px;
    height: 600px;
    margin: 60px;
}

.main-pic {
    width: 100%;
    height: 100%;
    border-radius: 5px;
}

.info {
    width: 600px;
    margin-top: 90px;
    padding: 10px;
}

.brand {
    font-size: 15px;
    font-weight: bold;
}

.name {
    font-size: 25px;
    font-weight: bold;
    margin-bottom: 30px;
}

.namediv {
    display: flex;
    width: 100%;
    justify-content: space-between;
}

.discount {
    font-size: 20px;
    margin-right: 10px;
    font-weight: bold;
    color: #7B7977;
}

.price {
    font-size: 20px;
    text-decoration: line-through;
    text-decoration-color: #DEDEDD;
    /* 선의 색상 설정 */
    text-decoration-thickness: 2px;
    /* 선의 두께 설정 */
    color: #DEDEDD;
}

.specialPrice {
    font-size: 45px;
    font-weight: bold;
}

.delivery {
    display: flex;
    align-items: center;
}

.delivery2 {
    font-size: 17px;
    margin-left: 20px;
    margin-right: 10px;
    font-weight: bold;
    color: #7B7977;
}

.deliveryFee {
    font-size: 17px;
    margin-left: 10px;
    font-weight: bold;
}
</style>