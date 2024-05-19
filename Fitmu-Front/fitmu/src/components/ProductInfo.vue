<template>
    <div class = "pictures">
        <img v-for="(image) in productImages" :key= "image.imageId" :src="`/src/assets/image/product/${image.fileName}`" alt="">
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
.pictures{
    display : flex;
    flex-direction: column;
}
</style>