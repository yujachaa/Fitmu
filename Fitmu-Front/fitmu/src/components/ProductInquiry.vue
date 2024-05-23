<template>
    <div class="container">
        <div class="reviewtop">
            <div class="reviewCnt">
                <p>문의</p>
                <p class="numColor">{{ productStore.inquirys.length }}</p>
            </div>
            <button class="reviewbtn" data-bs-toggle="modal" data-bs-target="#exampleModal"
                data-bs-whatever="@mdo">문의하기</button>
        </div>
        <div v-for="inquiry in productStore.inquirys" class="inquiry">
            <div class="inquiryInfo">
                <span v-if="inquiry.isBuy == 1">구매</span>
                <span v-else>비구매</span>
                <span>|</span>
                <span>{{ inquiry.category }}</span>
                <span>|</span>
                <span class="yesAnswer" v-if="inquiry.isAnswered == 1">답변완료</span>
                <span class="noAnswer" v-else>답변 대기중</span>
            </div>
            <div class="userInfo">
                <span>{{ getUser(inquiry.userId) }}</span>
                <span>|</span>
                <span>{{ inquiry.createdAt }}</span>
            </div>
            <div class="Question">
                <span class="Q">Q</span>
                <span v-if="isShow(inquiry)" class="context">{{ inquiry.context }}</span>
                <span v-else>비밀글입니다.</span>
            </div>
            <div v-if="inquiry.isPrivate == 0" class="Question">
                <span class="Q">A</span>
                <span v-if="inquiry.answer != ''" class="brand">{{ productStore.product.brand }}</span>
                <span v-else class="brand">아직 답변 전 입니다.</span>
            </div>
            <p v-if="inquiry.answer != ''" class="context2">{{ inquiry.answer }}</p>
            <div class="btnGroup">
                <button @click="deleteInquiry(inquiry.inquiryId)" class="delete"
                    v-if="inquiry.userId == userId || role != 'u'">삭제</button>
                <button @click = "saveId(inquiry.inquiryId)" data-bs-toggle="modal" data-bs-target="#exampleModal2" class="response"
                    v-if="role != 'u'">답변</button>
            </div>
            <hr>
        </div>
    </div>

    <div ref="exampleModal" class="modal fade" id="exampleModal" data-bs-backdrop="static" tabindex="-1"
        aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">문의 쓰기</h1>
                    <button ref="modal" @click.prevent="outCheck" type="button" class="btn-close"
                        aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="rate">
                            <p class="info">문의 유형</p>
                            <select name="category" id="category" v-model="category">
                                <option value="상품">상품</option>
                                <option value="배송">배송</option>
                                <option value="반품">반품</option>
                                <option value="교환">교환</option>
                                <option value="환불">환불</option>
                                <option value="기타">기타</option>
                            </select>
                        </div>
                        <div class="reviewContent">
                            <p class="info">문의 내용</p>
                            <textarea class="inquiryC" name="" id="reviewContent" placeholder="문의 내용을 입력하세요"
                                v-model="inquiryContent"></textarea>
                        </div>
                        <input id="secret" type="checkbox" v-model="isSecret">
                        <label for="secret">비밀글로 문의하기</label>
                        <div>
                            <input name="buy" id="buy1" type="radio" value="1" v-model="isBuy">
                            <label for="buy1">구매</label>
                            <input name="buy" id="buy2" type="radio" value="0" v-model="isBuy">
                            <label for="buy2">비구매</label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="registInquiry">등록</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal2" data-bs-backdrop="static" tabindex="-1"
        aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">문의 쓰기</h1>
                    <button ref="modal" @click.prevent="outCheck" type="button" class="btn-close"
                        aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="reviewContent">
                        <p class="info">문의 내용</p>
                        <textarea class="inquiryC" name="" id="reviewContent" placeholder="답변을 입력하세요"
                            v-model="answerContent"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="registAnswer()">등록</button>
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

const clickInquiryId = ref(0)
const saveId = function(id){
    clickInquiryId.value = id
}

const role = ref(sessionStorage.getItem("role"))
const product = ref(productStore.product)
const users = ref(productStore.users)

const userId = sessionStorage.getItem("loginUser")
const category = ref("")
const inquiryContent = ref("")
const answerContent = ref("")
const isSecret = ref(true)
const isPrivate = computed(() => {
    return isSecret.value ? 1 : 0
})
const isBuy = ref(0)

const registAnswer = function(){
    productStore.registAnswer(clickInquiryId.value, answerContent.value)
}

const deleteInquiry = function (id) {
    productStore.deleteInquiry(id, answerContent)
}

const outCheck = function () {
    const confirm = window.confirm("작성한 내용이 모두 사라져요. 나갈까요?")
    if (confirm) {
        window.location.reload()
    }
}

const isShow = function (requiry) {
    if (role.value == "u") {
        if (requiry.isPrivate == 0) {
            return true
        } else {
            return false
        }
    } else {
        return true
    }
}

const registInquiry = function () {
    const requiry = {
        inquiryId: 0,
        userId: userId,
        productId: product.value.productId,
        sellerId: product.value.sellerId,
        isBuy: isBuy.value,
        isPrivate: isPrivate.value,
        isAnswered: 0,
        createdAt: "",
        category: category.value,
        context: inquiryContent.value,
        answer: ""
    }
    console.log(requiry)
    productStore.registInquiry(requiry)
}


const getUser = function (userId) {
    return users.value.filter(user => user.userId == userId)[0].nickname
}
</script>

<style scoped>
.delete {
    background-color: rgb(52, 197, 240);
    color: white;
    border: 1px solid rgb(52, 197, 240);
    border-radius: 5px;
    width: 60px;
    height: 35px;
}

.delete:hover {
    background-color: rgb(51, 157, 189);
    border: 1px solid rgb(51, 157, 189);
}

.response {
    background-color: rgb(52, 197, 240);
    color: white;
    border: 1px solid rgb(52, 197, 240);
    border-radius: 5px;
    width: 60px;
    height: 35px;
}

.response:hover {
    background-color: rgb(51, 157, 189);
    border: 1px solid rgb(51, 157, 189);
}

.btnGroup {
    width: 100%;
    display: flex;
    justify-content: end;
    gap: 20px;
}

#buy1 {
    margin-right: 10px;
    margin-left: 10px;
}

#buy2 {
    margin-right: 10px;
    margin-left: 20px;
}


#secret {
    margin-right: 10px;
    margin-left: 10px;
}

.inquiryC {
    border: 2px solid rgb(52, 197, 240, 0.3);
}

#category {
    width: 100%;
    height: 35px;
    border-radius: 5px;
    border: 2px solid rgb(52, 197, 240, 0.3);
    margin-bottom: 40px;
    padding-left: 10px;
}

.brand {
    font-size: 15px;
    font-weight: bold;
}

.Question {
    display: flex;
    gap: 10px;
    font-size: 15px;
    padding-top: 10px;
    align-items: center;
}

.Q {
    color: #34C5F0;
    font-weight: bold;
    font-size: 20px;
}

.context {
    font-size: 16px;
}

.context2 {
    font-size: 16px;
    width: 100%;
    padding-left: 23px;
    padding-top: 10px;
    padding-bottom: 40px;
}

.userInfo {
    display: flex;
    gap: 10px;
    font-size: 15px;
    color: #DBDBDB
}

.inquiryInfo {
    font-size: 15px;
    display: flex;
    gap: 4px;
}

.yesAnswer {
    color: #34C5F0;
}

.noAnswer {
    color: red;
}

.container {
    display: flex;
    flex-direction: column;
    width: 900px;
    margin-top: 50px;
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

.numColor {
    color: #34C5F0;
    font-size: 23px;
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

.info {
    font-weight: bold;
}

.checkBox {
    margin-left: 10px;
}

hr {
    color: #EDEDED;
    border: 1px solid;
    font-weight: bold;
}

#reviewContent {
    width: 100%;
    height: 100px;
    border-color: #DBDBDB;
    border-radius: 5px;
    resize: none;
    padding-left: 10px;
    padding-top: 5px;
}
</style>