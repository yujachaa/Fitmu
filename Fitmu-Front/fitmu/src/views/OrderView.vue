<template>
    <div class="main">
        <TheHeaderNav></TheHeaderNav>
        <div class="container">
            <div class="garo">
                <div class="orderInfo">
                    <div class="orderPerson">
                        <p class="middletitle">주문/결제</p>
                        <span class="smalltitle">주문자</span>
                        <hr>
                        <div class="inputgaro">
                            <label class="label" for="name">이름</label>
                            <input class="input" type="text" id="name">
                        </div>
                        <div class="inputgaro">
                            <label class="label" for="email">이메일</label>
                            <input class="input" type="email" id="email">
                            <span class="gol">@</span>
                            <select class="input" name="afteremail" id="afteremail">
                                <option value="" selected>선택해주세요.</option>
                                <option value="naver.com">naver.com</option>
                                <option value="daum.net">daum.net</option>
                                <option value="google.com">google.com</option>
                                <option value="">직접입력은 다음기회에</option>
                            </select>
                        </div>
                        <div class="inputgaro">
                            <label class="label" for="phonenumber">전화번호</label>
                            <input class="input" type="text" id="phonenumber" placeholder="-없이 작성해주세요">
                        </div>
                    </div>

                    <div v-if="defaultAddress == null" class="orderPerson">
                        <div class="delivery">
                            <span class="smalltitle">배송지</span>
                            <div>
                                <span class="selected" @click="registAddress">배송지 추가</span>
                                <button type="button" class = "hahaha" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">배송지 선택</button>
                            </div>
                        </div>
                        <hr>
                        <div class="inputgaro">
                            <label class="label" for="name">배송지명</label>
                            <input class="input" type="text" id="name" v-model="addressName">
                        </div>
                        <div class="inputgaro">
                            <label class="label" for="email">받는 사람</label>
                            <input class="input" type="text" id="email" v-model="receiver">
                        </div>
                        <div class="inputgaro">
                            <label class="label" for="phonenumber">전화번호</label>
                            <input class="input" type="text" id="phonenumber" placeholder="-없이 작성해주세요"
                                v-model="phoneNumber">
                        </div>
                        <div class="inputgaro">
                            <div class="sero">
                                <div class="inputgaro">
                                    <label class="label" for="phonenumber">주소</label>
                                    <button class="addressbtn" @click = "openaddress">주소찾기</button>
                                    <input class="addressinput1" type="text" v-model = "postcode">
                                </div>
                                <div class="sero">
                                    <div class="inputgaro">
                                        <div class="label"></div>
                                        <input class="addressinput2" type="text" id="address1" v-model="address1">
                                    </div>
                                    <div class="inputgaro">
                                        <div class="label"></div>
                                        <input class="addressinput3" type="text" id="address2" v-model="address2">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="Default">
                            <input id="isDefault" type="checkbox" v-model="isdefault">
                            <label for="isDefault">기본 배송지로 저장</label>
                        </div>
                        <select class="select" name="comment" id="comment">
                            <option value="배송시 요청사항을 선택해주세요">배송시 요청사항을 선택해주세요</option>
                            <option value="부재시 문앞에 놓아주세요">부재시 문앞에 놓아주세요</option>
                            <option value="배송전에 미리 연락주세요">배송전에 미리 연락주세요</option>
                            <option value="부재시 경비실에 맡겨 주세요">부재시 경비실에 맡겨 주세요</option>
                            <option value="분재시 전화주시거나 문자 남겨 주세요">분재시 전화주시거나 문자 남겨 주세요</option>
                        </select>
                    </div>
                    <div v-else class="orderPerson">
                        <div class="delivery">
                            <span class="smalltitle">배송지</span>
                            <div>
                                <span class="selected" @click="deleteAddress">배송지 비우기</span>
                                <button type="button" class = "hahaha" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">배송지 선택</button>
                            </div>
                        </div>
                        <hr>
                        <div class="inputgaro">
                            <label class="label" for="name">배송지명</label>
                            <input class="input" type="text" id="name" :value="productStore.defaultAddress.addressName"
                                disabled>
                        </div>
                        <div class="inputgaro">
                            <label class="label" for="email">받는 사람</label>
                            <input class="input" type="text" id="email" :value="productStore.defaultAddress.receiver"
                                disabled>
                        </div>
                        <div class="inputgaro">
                            <label class="label" for="phonenumber">전화번호</label>
                            <input class="input" type="text" id="phonenumber" placeholder="-없이 작성해주세요"
                                :value="productStore.defaultAddress.phoneNumber" disabled>
                        </div>
                        <div class="inputgaro">
                            <div class="sero">
                                <div class="inputgaro">
                                    <label class="label" for="phonenumber">주소</label>
                                    <button class="addressbtn" @click = "openaddress">주소찾기</button>
                                    <input class="addressinput1" type="text" :value="productStore.defaultAddress.postcode" disabled>
                                </div>
                                <div class="sero">
                                    <div class="inputgaro">
                                        <div class="label"></div>
                                        <input class="addressinput2" type="text" id="address1"
                                            :value="productStore.defaultAddress.address1" disabled>
                                    </div>
                                    <div class="inputgaro">
                                        <div class="label"></div>
                                        <input class="addressinput3" type="text" id="address2"
                                            :value="productStore.defaultAddress.address2" disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="Default">
                            <input id="isDefault" type="checkbox" :value="true" disabled>
                            <label for="isDefault">기본 배송지로 저장</label>
                        </div>
                        <select class="select" name="comment" id="comment" v-model = "memo">
                            <option value="배송시 요청사항을 선택해주세요">배송시 요청사항을 선택해주세요</option>
                            <option value="부재시 문앞에 놓아주세요">부재시 문앞에 놓아주세요</option>
                            <option value="배송전에 미리 연락주세요">배송전에 미리 연락주세요</option>
                            <option value="부재시 경비실에 맡겨 주세요">부재시 경비실에 맡겨 주세요</option>
                            <option value="분재시 전화주시거나 문자 남겨 주세요">분재시 전화주시거나 문자 남겨 주세요</option>
                        </select>
                    </div>
                    <span class="smalltitle">주문상품</span>
                    <hr>
                    <div class="product">
                        <div>
                            <span class="label">{{ productStore.product.brand }}</span>
                            <span class="productname">{{ productStore.product.name }}</span>
                        </div>
                        <div>
                            <span class="specialPrice">{{ productStore.product.specialPrice.toLocaleString('ko-KR') }}원</span>
                            <span>배송비</span>
                            <span class="deliveryFee">{{ productStore.product.deliveryFee.toLocaleString('ko-KR') }}원</span>
                        </div>
                    </div>
                    <span class="smalltitle">결제수단</span>
                    <hr>
                    <h3 class="buy">여러가지 결제수단이 예정되어 있습니다.</h3>
                </div>
                <div class="rightsero">
                    <div class="ordercheck">
                        <p class="smalltitle">결제금액</p>
                        <div class="garogaro">
                            <span>총 상품 금액</span>
                            <div>
                                <span>{{ productStore.product.specialPrice.toLocaleString('ko-KR') }}</span>
                                <span>원</span>
                            </div>
                        </div>
                        <div class="garogaro">
                            <span>배송비</span>
                            <div>
                                <span>{{ productStore.product.deliveryFee.toLocaleString('ko-KR') }}</span>
                                <span>원</span>
                            </div>
                        </div>
                        <div class="garogaro">
                            <span>쿠폰 사용</span>
                            <div>
                                <span>0</span>
                                <span>원</span>
                            </div>
                        </div>
                        <div class="garogaro">
                            <span>포인트 사용</span>
                            <div>
                                <span>0</span>
                                <span>원</span>
                            </div>
                        </div>
                        <hr>
                        <div class="garogaro2">
                            <p class="last">최종 결제 금액</p>
                            <p class="lastprice">{{ (productStore.product.specialPrice + productStore.product.deliveryFee).toLocaleString('ko-KR')
                                }}원</p>
                        </div>
                    </div>
                    <button @click = "finish" class="buybtn">{{ (productStore.product.specialPrice + productStore.product.deliveryFee).toLocaleString('ko-KR') }}원
                        결제하기</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">배송지 선택</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class = "sero">
                        <div class = "address" v-for = "address in productStore.addresses">
                            <div class = "addressgaro">
                                <p class = "addressName">{{address.addressName}}</p>
                                <p class = "defaultAddress" v-if = "address.isDefault == 1">기본배송지</p>
                            </div>
                            <div class ="addressgaro address-address">
                                <span>{{address.address1}}</span>
                                <span>,</span>
                                <span>{{address.address2}}</span>
                            </div>
                            <div class = "addressgaro address-receiver">
                                <span>{{address.receiver }}</span>
                                <span>{{ address.phoneNumber }}</span>
                            </div>
                            <div class = "btngaro">
                                <button @click = "deleteaddress(address.addressId)" class = "addressdelete">삭제</button>
                                <button @click = "selectaddress(address)" data-bs-dismiss="modal" class = "addressselect">선택</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import TheHeaderNav from '../components/common/TheHeaderNav.vue'

import { onMounted, ref, computed, onUpdated, } from 'vue';
import { useRoute, useRouter, onBeforeRouteLeave } from 'vue-router'
import { useProductStore } from '@/stores/product'

onBeforeRouteLeave((to, from) => {
    if(to.name == 'my-order'){
        return true
    }
    const answer = window.confirm('작성한 내용이 모두 사라져요. 나갈까요?.')
    return answer
})

const route = useRoute()
const router = useRouter()
const productStore = useProductStore()


onMounted(() => {
    productStore.getProduct(route.params.productId)
    productStore.getAddress(sessionStorage.getItem("loginUser"))
    productStore.getDefaultAddress(sessionStorage.getItem("loginUser"))
})

const addressName = ref("")
const receiver = ref("")
const phoneNumber = ref("")
const address1 = ref("")
const address2 = ref("")
const postcode = ref("")
const isdefault = ref(0)
const memo = ref("")

const finish = function(){
    productStore.finish(memo.value)
}

const deleteAddress = function () {
    productStore.deleteAddress()
}

const selectaddress = function (address) {
    productStore.deleteAddress()
    addressName.value = address.addressName
    receiver.value = address.receiver
    phoneNumber.value = address.phoneNumber
    address1.value = address.address1
    address2.value = address.address2
    isdefault.value = address.isDefault==1 ? true : false
    postcode.value = address.postcode
}

const deleteaddress = function(id){
    productStore.addressDelete(id)
}

const defaultAddress = computed(()=>{
    return productStore.defaultAddress
})

const registAddress = function () {
    if (addressName.value == "" || receiver.value == "" || phoneNumber.value == "" || address1.value == "" || phoneNumber.value == "" || address2.value == "" || postcode.value == "") {
        window.alert("빈칸을 채워주세요.")
        return
    }
    const address = {
        addressId: 0,
        userId: sessionStorage.getItem("loginUser"),
        addressName: addressName.value,
        receiver: receiver.value,
        phoneNumber: phoneNumber.value,
        address1: address1.value,
        address2: address2.value,
        isDefault: isdefault.value ? 1 : 0,
        postcode : postcode.value
    }
    productStore.registAddress(address)
}

const openaddress = function(){
    new window.daum.Postcode({
        oncomplete: function(data) {
            postcode.value = data.zonecode
            if(data.addressType == "R"){
                address1.value = data.roadAddress
            }else{
                address1.value = data.jibunAddress
            }
        }
    }).open()
}
    
</script>

<style scoped>
.defaultAddress{
    display : flex;
    color : #40C8F0;
    border : 1px solid #40C8F0;
    border-radius : 10px;
    font-weight : bold;
    width : 90px;
    height : 30px;
    justify-content: center;
    align-items: center;
}
.addressdelete{
    border : 1px solid #999999;
    background-color: white;
    border-radius : 5px;
    width : 60px;
    height : 40px;
}
.addressselect{
    border : 1px solid #40C8F0;
    background-color: #40C8F0;
    border-radius : 5px;
    color : white;
    width : 60px;
    height : 40px;
}
.addressselect:hover{
    border : 1px solid #3298b7;
    background-color: #3298b7;
}
.btngaro{
    display :flex;
    justify-content: space-between;
    margin-top : 10px;
}
.addressName{
    font-size : 20px;
    font-weight : bold;
}

.address-address{
    font-size : 18px;
}

.address-receiver{
    font-size : 18px;
    color : #999999;
}
.addressgaro{
    display : flex;
    gap : 10px;
}
.address{
    width : 100%;
    height : 190px;
    border : 2px solid rgba(4, 191, 248, 0.3);
    padding : 20px;
    border-radius: 5px;
    margin-bottom : 10px;
}
.hahaha{
    border : 1px solid white;
    background-color: white;
    color : #40C8F0;
    font-weight : bold;
    font-size : 16px;
}
.selected {
    color: #40C8F0;
    font-weight: bold;
    margin-right: 20px;
    cursor: pointer;
}

.delivery {
    display: flex;
    justify-content: space-between;
}

.productname {
    font-size: 20px;
    font-weight: bold;
    margin-left: 20px;
}

.specialPrice {
    margin-right: 10px;
    font-size: 20px;
    font-weight: bold;
}

.deliveryFee {
    margin-left: 5px;
}

.buybtn {
    margin-top: 25px;
    height: 55px;
    border: 1px solid #40C8F0;
    background-color: #40C8F0;
    border-radius: 8px;
    color: white;
    font-weight: bold;
}

.buybtn:hover {
    border: 1px solid #3596b3;
    background-color: #3596b3;
}

.rightsero {
    display: flex;
    flex-direction: column;
}

.last {
    font-size: 20px;
    font-weight: bold;
}

.lastprice {
    font-size: 20px;
    font-weight: bold;
    color: #40C8F0;
}

.garogaro {
    margin-top: 10px;
    margin-bottom: 7px;
    display: flex;
    justify-content: space-between;
}

.garogaro2 {
    margin-top: 20px;
    margin-bottom: 7px;
    display: flex;
    justify-content: space-between;
}

.ordercheck {
    border: 1px solid #999999;
    padding: 25px;
    height: 320px;
    border-radius: 8px;
}

.buy {
    margin-top: 30px;
}

.product {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
    margin-bottom: 50px;
}

.select {
    width: 680px;
    height: 40px;
    border-radius: 5px;
    border-color: #999999;
}

#isDefault {
    width: 20px;
    color: #EDEDED;
}

.Default {
    display: flex;
    padding-top: 20px;
    font-size: 20px;
    gap: 10px;
    margin-bottom: 15px;
}

.addressbtn {
    border: 1px solid #40C8F0;
    border-radius: 5px;
    height: 40px;
    margin-right: 10px;
    color: #40C8F0;
    background-color: white;
}

.addressbtn:hover {
    border: 1px solid #40C8F0;
    color: white;
    background-color: #40C8F0;
}

.addressinput1 {
    width: 162px;
    border-radius: 5px;
    border-color: #EDEDED;
    height: 40px;
}

.addressinput2 {
    width: 600px;
    border-radius: 5px;
    border-color: #EDEDED;
    height: 40px;
}

.addressinput3 {
    width: 600px;
    border-radius: 5px;
    border-color: #EDEDED;
    height: 40px;
}

.sero {
    display: flex;
    flex-direction: column;
}

.input {
    width: 250px;
    height: 40px;
    border-radius: 5px;
    border-color: #EDEDED;
}

.gol {
    margin-left: 10px;
    margin-right: 10px;
}

.main {
    width: 100%;
}

.label {
    width: 80px;
    color: #869097;
}

.smalltitle {
    font-weight: bold;
    font-size: 20px;
}

hr {
    margin-top: 8px;
    margin-bottom: 0px;
}

.middletitle {
    font-size: 25px;
    font-weight: bold;
    margin-bottom: 40px;
}

.orderPerson {
    margin-top: 20px;
    margin-bottom: 40px;
}

.garo {
    width: 100%;
    display: flex;
    gap: 26px;
    margin-top: 60px;
    justify-content: center;
}

.inputgaro {
    display: flex;
    margin-top: 15px;
    align-items: center;
}

.orderInfo {
    width: 750px;
    display: flex;
    flex-direction: column;
}

.ordercheck {
    width: 360px;
    display: flex;
    flex-direction: column;
}
</style>