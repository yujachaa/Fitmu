<template>
    <div class="container">
        <div class="reviewtop">
            <div class="reviewCnt">
                <p>리뷰</p>
                <p class="numColor">{{ productStore.reviews.length }}</p>
            </div>
            <button @click = "aaaa" class="reviewbtn" data-bs-toggle="modal" data-bs-target="#exampleModal"
                data-bs-whatever="@mdo">리뷰쓰기</button>
        </div>
        <div class="stardiv">
            <i class="star bi bi-star-fill"></i>
            <p class="rating" v-if="productStore.reviews.length == 0">0</p>
            <p class="rating" v-else>{{ rating }}</p>
            <div class="vertical-line"></div>
            <div class="ratinglist">
                <span>5점 : {{ five }}</span>
                <span>4점 : {{ four }}</span>
                <span>3점 : {{ three }}</span>
                <span>2점 : {{ two }}</span>
                <span>1점 : {{ one }}</span>
            </div>
        </div>
        <hr>
        <div class="category">
            <span class="sort" @click="help">도움순</span>
            <span class="sort" @click="late">최신순</span>
        </div>
        <hr>
        <div class="reviews">
            <div v-if = "productStore.reviews.length == 0">리뷰 좀 써주세요 ㅠㅠ</div>
            <div v-for="review in productStore.reviews" class="review">
                <div class="user-profile">
                    <img class="profile" src="@/assets/image/profile.png" alt="">
                    <p>{{ getUser(review.userId) }}</p> <br>
                </div>
                <div class="review-info">
                    <i v-for="_ in review.rating" class="mini-star bi bi-star-fill"></i>
                    <i v-for="_ in 5 - review.rating" class="mini-star bi bi-star"></i>
                    <span>{{ review.createdAt }}</span>
                </div>
                <p class="content">{{ review.content }}</p>
                <div class="gooddiv">
                    <p class="good" @click="reviewLike(review.reviewId)">도움이 돼요</p>
                    <div class="liked" v-if="review.liked != 0">
                        <span class="num">{{ review.liked }}</span>
                        <span>만큼 도움이 되었습니다.</span>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>

    <div @click = "outCheck2" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">리뷰 쓰기</h1>
                    <button @click = "outCheck" type="button" class="btn-close" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="productInfo">
                            <p class = "info">상품 정보</p>
                            <span class = "brand">{{ productStore.product.brand }}</span>
                            <span class = "name">{{ productStore.product.name }}</span>
                        </div>
                        <div class="rate">
                            <p class = "info">별점 평가</p>
                            <div>
                                <span class = "satis">만족도</span><br>
                                <input name="sat" class = "checkBox" id = "one" type="radio" value ="1" v-model = "satisfy">
                                <label for="one"><i v-for="_ in 1" class="mini-star2 bi bi-star-fill"></i></label>
                                <input name="sat" class = "checkBox" id = "two" type="radio" value ="2" v-model = "satisfy">
                                <label for="two"><i v-for="_ in 2" class="mini-star2 bi bi-star-fill"></i></label>
                                <input name="sat" class = "checkBox" id = "three" type="radio" value ="3" v-model = "satisfy">
                                <label for="three"><i v-for="_ in 3" class="mini-star2 bi bi-star-fill"></i></label>
                                <input name="sat" class = "checkBox" id = "four" type="radio" value ="4" v-model = "satisfy">
                                <label for="four"><i v-for="_ in 4" class="mini-star2 bi bi-star-fill"></i></label>
                                <input name="sat" class = "checkBox" id = "five" type="radio" value ="5" v-model = "satisfy">
                                <label for="five"><i v-for="_ in 5" class="mini-star2 bi bi-star-fill"></i></label>
                            </div>
                        </div>
                        <div class="reviewContent">
                            <p class = "info">리뷰 작성</p>
                            <textarea name="" id="reviewContent" placeholder="자세하고 솔직한 리뷰는 다른 고객에게 큰 도움이 됩니다." v-model = "reviewContent"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click = "registReview">등록</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useProductStore } from '@/stores/product'

const route = useRoute()
const router = useRouter()
const productStore = useProductStore()

const product = ref(productStore.product)
const reviews = computed(() => {
    return productStore.reviews
})
const users = ref(productStore.users)

const satisfy = ref(0)
const userId = sessionStorage.getItem("loginUser")
const reviewContent = ref("")

const registReview = function(){
    const review = {
        reviewId : 0,
        userId : userId,
        productId : product.value.productId,
        sellerId : product.value.sellerId,
        rating : satisfy.value,
        reviewImage : "",
        content : reviewContent.value,
        liked : 0,
        blockStatus : 0,
        createdAt : ""
    }
    productStore.registReview(review)
}

const outCheck = function(){
    const confirm = window.confirm("작성한 내용이 모두 사라져요. 나갈까요?")
    if(confirm){
        window.location.reload()
    }
}

const getUser = function (userId) {
    return users.value.filter(user => user.userId == userId)[0].nickname
}

const reviewLike = function (reviewId) {
    productStore.reviewLike(reviewId)
}

const one = computed(() => {
    return reviews.value.filter(reviews => reviews.rating === 1).length
})
const two = computed(() => {
    return reviews.value.filter(reviews => reviews.rating === 2).length
})
const three = computed(() => {
    return reviews.value.filter(reviews => reviews.rating === 3).length
})
const four = computed(() => {
    return reviews.value.filter(reviews => reviews.rating === 4).length
})
const five = computed(() => {
    return reviews.value.filter(reviews => reviews.rating === 5).length
})

const rating = computed(() => {
    let sum = 0
    for (let i = 0; i < productStore.reviews.length; i++) {
        sum += productStore.reviews[i].rating
    }
    return (sum / productStore.reviews.length).toFixed(1)
})

const help = function () {
    productStore.help()
}

const late = function () {
    productStore.late()
}
</script>

<style scoped>
#reviewContent{
    width: 100%;
    height : 100px;
    border-color : #DBDBDB;
    border-radius : 5px;
    resize : none;
    padding-left : 10px;
    padding-top : 5px;
}
.rate{
    margin-bottom : 40px;
}
.checkBox{
    margin-left : 10px;
}
.info{
    font-weight: bold;
}
.brand{
    font-size : 12px;
    margin-right : 10px;
    color : #989898;
}
.productInfo{
    margin-bottom : 40px;
}   
.sort {
    cursor: pointer;
}

.sort:hover {
    color: #34C5F0;
}

.liked {
    display: flex;
    margin-left: 15px;
}

.num {
    font-weight: bold;
}

.gooddiv {
    display: flex;
}

.good {
    display: flex;
    width: 100px;
    height: 35px;
    border-radius: 5px;
    color: #34C5F0;
    font-weight: bold;
    border: 1px solid #34C5F0;
    justify-content: center;
    align-items: center;
    margin-bottom: 30px;
}

.good:hover {
    cursor: pointer;
}

.content {
    display: flex;
    width: 100%;
    height: 80px;
    align-items: center;
}

.review-info span {
    margin-left: 10px;
}

.user-profile {
    display: flex;
    gap: 10px;
    font-weight: bold;
}

.mini-star {
    color: #34C5F0;
    font-size: 20px;
    margin-left: 3px;
}

.mini-star2 {
    color: #34C5F0;
    font-size: 20px;
    margin-left: 3px;
}

.profile {
    width: 20px;
    height: 20px;
}

.reviews {
    display: flex;
    flex-direction: column;
    width: 100%;
}

.review {
    padding-top: 20px;

}

.category {
    display: flex;
    gap: 30px;
    font-weight: bold;
}

hr {
    color: #B7B8B6
}

.ratinglist {
    width: 300px;
    display: flex;
    flex-direction: column;
    padding-left: 70px;
}

.ratinglist span {
    font-weight: bold;
    color: #B7B8B6;
}

.vertical-line {
    margin-left: 130px;
    width: 1px;
    height: 100px;
    /* 원하는 높이로 설정 */
    background-color: black;
    /* 선 색상 */
}

.rating {
    font-size: 70px;
    text-align: center;
    height: 100%;
    margin-left: 30px;
}

.star {
    font-size: 70px;
    color: #34C5F0;
}

.stardiv {
    display: flex;
    width: 100%;
    height: 150px;
    align-items: center;
    background-color: #F7F8FA;
    padding: 30px;
    padding-left: 100px;
    padding-right: 50px;
}

.reviewbtn {
    color: #34C5F0;
    background-color: white;
    border: none;
    font-weight: bold;
}

.reviewbtn :hover {
    color: #73848a;
}

.numColor {
    color: #34C5F0;
    font-size: 23px;
}

.reviewtop {
    width: 100%;
    display: flex;
    justify-content: space-between;
    font-weight: bold;
}

.reviewCnt {
    display: flex;
    gap: 10px;
    align-items: center;
}

.container {
    display: flex;
    flex-direction: column;
    width: 900px;
    margin-top: 50px;
}
</style>