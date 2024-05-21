<template>
    <div class="pic-carousel">
        <carousel :autoplay="2000" :wrap-around="true">
            <!--<carousel :wrap-around="true">-->
            <slide v-for="slide in 7" :key="slide">
                <img class="pic_carousel__item row" :src="`src/assets/image/product/${slide}.jpg`" alt="이미지">
            </slide>
            <template #addons>
                <navigation />
                <pagination />
            </template>
        </carousel>
    </div>



    <div class="container">
        <div class="section">
            <div class="section-title">
                <div class="small-title">
                    <h4>오늘의 세일 상품을 확인해보세요!</h4>
                    <span>파격적인 할인 상품을 추천합니다</span>
                </div>
                <div>
                    <a @click="goSale()">더보기</a>
                </div>
            </div>

            <div class="today-deal ">
                <carousel v-bind="settings" :breakpoints="breakpoints" :mouseDrag="false" :touchDrag="false">
                    <slide v-for="product in sale8List" :key="product">
                        <div class="product_carousel__item">
                            <div class="sale-pic">
                                <img v-if="getProductMainImage(product.productId)" class="pic "
                                    :src="`src/assets/image/product/${getProductMainImage(product.productId)}`"
                                    alt="이미지" @click="productDetail(product.productId)">
                            </div>
                            <div class="product-info ">
                                <div class="brand">
                                    {{ product.brand }}
                                </div>
                                <div class="product-name">
                                    {{ product.name }}
                                </div>
                                <div class="price-box">
                                    <span class="discount">
                                        {{ product.discountRate }}%
                                    </span>
                                    <span class="price">
                                        {{ product.specialPrice.toLocaleString('ko-KR') }}
                                    </span>
                                </div>
                                <div>

                                    ⭐<span class="rating" v-if="product.ratingCnt > 0">
                                        {{ (product.ratingSum / product.ratingCnt).toFixed(1) }}
                                    </span>
                                    <span class="rating" v-else>0</span>
                                    리뷰 <span class="review">{{ product.ratingCnt }}</span>
                                </div>
                            </div>
                        </div>
                    </slide>
                    <template #addons>
                        <navigation />
                    </template>
                </carousel>
            </div>
            <hr>
        </div>
    </div>

    <div class="container">
        <div class="section">
            <div class="section-title">
                <div class="small-title">
                    <h4>인기 상품 모아보기💫</h4>
                    <span>지금 가장 인기있는 상품이에요</span>
                </div>
                <div>
                    <a @click="goPopular()">더보기</a>
                </div>
            </div>

            <div class="popular d-flex justify-content-between">
                <!-- v-for 넣기 -->
                <div v-for="product in popular6List" :key="product">
                    <div class="popular-pic">
                        <img v-if="getProductMainImage(product.productId)" class="pic"
                            :src="`src/assets/image/product/${getProductMainImage(product.productId)}`" alt="이미지" @click="productDetail(product.productId)">
                    </div>
                    <div class="product-info">
                        <div class="brand">
                            {{ product.brand }}
                        </div>
                        <div class="product-name">
                            {{ product.name }}
                        </div>
                        <div class="price-box">
                            <span class="discount">
                                {{product.discountRate}}%
                            </span>
                            <span class="price">
                                {{ product.specialPrice.toLocaleString('ko-KR') }}
                            </span>
                        </div>
                        <div>
                            ⭐<span class="rating" v-if="product.ratingCnt > 0">
                                        {{ (product.ratingSum / product.ratingCnt).toFixed(1) }}
                                    </span>
                                    <span class="rating" v-else>0</span>
                            <span class="review"> 리뷰 {{ product.ratingCnt }}</span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import 'vue3-carousel/dist/carousel.css'
import { defineComponent } from 'vue'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import { formToJSON } from 'axios'


export default defineComponent({
    name: 'Breakpoints',
    components: {
        Carousel,
        Slide,
        Navigation,
    },
    data: () => ({
        // carousel settings
        settings: {
            itemsToShow: 1,
            snapAlign: 'center',
        },
        // breakpoints are mobile first
        // any settings not specified will fallback to the carousel settings
        breakpoints: {

            // 700px and up
            600: {
                itemsToShow: 2,
                snapAlign: 'center',
            },

            990: {
                itemsToShow: 3,
                snapAlign: 'center',
            },


            // 1024 and up
            1100: {
                itemsToShow: 4,
                snapAlign: 'center',
            },
            // 1024 and up
            1300: {
                itemsToShow: 5,
                snapAlign: 'center',
            }
        },
    }),
})
</script>

<script setup>
import { ref, computed, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useProductStore } from '@/stores/product'


const router = useRouter()
const productStore = useProductStore()

productStore.getSaleList()
productStore.getProductALLImages()
productStore.getProducts()

const sale8List = computed(() => {
    return productStore.sale8List
})

const popular6List = computed(() => {
    return productStore.popular12ProductList
})


const getImageName = function (productId) {
    const files = productStore.productAllImages.filter((productImg) => productImg.productId == productId)
    if (files[0] == undefined)
        return ''
    return files[0].fileName
    // return (files != undefined) ? files[0].fileName : '';
}

const getProductMainImage = function (productId) {
    if (productStore.productAllImages.length > 0) {
        return getImageName(productId);
    }
    return '';
}

const productDetail = function (productId) {
    router.push({ name: 'productDetail', params: { 'productId': productId } })
}

const goPopular = function(){
    router.push({name: 'reviewPopular'})
}
const goSale = function(){
    router.push({name: 'sale'})
}

</script>

<style scoped>
hr {
    margin: 0;
}

.popular-pic {
    width: 270px;
    height: 270px;
    border-radius: 8px;
    object-fit: cover;
    object-fit: cover;
    overflow: hidden;
}

.popular-pic>.pic {
    border-radius: 8px;
    object-fit: cover;
    width: 100%;
    height: 100%;
    transition: all 0.2s linear;
}

.popular-pic:hover .pic {
    width: 100%;
    height: 100%;
    transform: scale(1.1);
    cursor: pointer;
}

.sale-pic {
    width: 220px;
    height: 220px;
    border-radius: 8px;
    /* height: 400px; */
    object-fit: cover;
    overflow: hidden;
    /* border-radius: 5px; */
}

.sale-pic>.pic {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 8px;
    transition: all 0.1s linear;
}

.sale-pic:hover .pic {
    width: 100%;
    height: 100%;
    transform: scale(1.1);
    cursor: pointer;
}

.product-info {
    margin-top: 5px;
    margin-bottom: 15px;
}

.price-box {
    font-size: larger;
    margin-top: 5px;
}

.brand {
    font-size: smaller;
    color: gray;
}

.discount {
    color: #34C5F0;
    font-weight: bold;
}

.price {
    margin-left: 5px;
    font-weight: bold;
}

.rating {
    margin-right: 5px;
    font-weight: bold;
}

.review {
    margin-left: 5px;
    color: gray;
}


.carousel {
    text-align: start;
}

.carousel__slide {
    padding: 0;
}

.product-info {
    justify-content: start;
}


.popular {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
}


.container {
    width: 100%;
    display: flex;
    padding-left: 80px;
    padding-right: 80px;
}

.section {
    margin-bottom: 20px;
    width: 100%;
}

.pic_carousel__item {
    object-fit: cover;
    max-height: 400px;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.product_carousel__item {
    /* width: 100%;
    display: flex;
    flex-direction: column; */
    justify-content: start;
    align-items: start;
}

/* .product_carousel_pic {
    width: 100%;
    height: 100%;
    width: 220px;
    height: 220px;
    object-fit: cover;
    border-radius: 8px;
} */

.section-title {
    width: 100%;
    height: 50px;
    display: flex;
    margin-top: 30px;
    margin-bottom: 30px;
    justify-content: space-between;
    align-items: center;
}

.section-title a {
    font-size: 18px;
    font-weight: bold;
    color: #34C5F0;
}

.small-title>h4 {
    font-weight: bold;
}
</style>