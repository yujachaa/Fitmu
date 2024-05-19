<template>
    <div class="pictures">
        <p class="small-title">상품사진</p>
        <img class="infoimg" v-for="(image) in productImages" :key="image.imageId"
            :src="`/src/assets/image/product/${image.fileName}`" alt="">
        <p class = "small-title">상품 설명</p>
        <p class = "content">{{ product.productDetail }}</p>
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


</script>

<style scoped>
.pictures {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.infoimg {
    width: 600px;
    height: 600px;
    margin: 30px;
    margin-top: 0px;
}

.small-title {
    width: 600px;
    font-size: 20px;
    font-weight: bold;
    margin-top: 30px;
}

.content{
    width : 600px;
}
</style>