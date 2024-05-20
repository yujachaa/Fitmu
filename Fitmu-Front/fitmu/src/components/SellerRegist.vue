<template>
  <div class="d-flex justify-content-center mt-3">
    <h4 class="title">판매자 신청</h4>
  </div>
  <hr>
  <div class="box">
    <div class="container total">
      <form class="row g-3">
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">사업자 등록번호<span class="star">*</span></h5>
          </label>
          <div class="company-number d-flex justify-content-between ">
            <div class="regi-num">
              <input type="text" class="form-control" aria-describedby="reginumHelp"
                placeholder="'-'를 제외하고 입력해주세요." maxlength="10" @input="validateNumber" v-model.trime="seller.registrationNum" :disabled="checked">
            </div>
            <div class="regi-button-box">
              <button class="btn btn-primary btn-reginum-check" type="button" @click="regiNumCheck" :disabled="checked">인증하기</button>
            </div>
          </div>
          <div class="form-text" id="reginumHelp">{{ regiNumMsg }}</div>
        </div>

        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">대표자명<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="email"  class="form-control" placeholder="이메일을 입력해주세요." v-model.trim="seller.representative">
        </div>
        
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">회사명<span class="star">*</span></h5>
          </label>
          <input type="text" class="form-control" placeholder="2자~20자까지 입력할 수 있어요." v-model.trim="seller.companyName">
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">회사 주소지<span class="star">*</span></h5>
          </label>
          <input type="text"  class="form-control" placeholder="주소를 입력해주세요." v-model.trim="seller.address">
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">고객센터<span class="star">*</span></h5>
          </label>
          <input type="text"  class="form-control" placeholder="연락가능한 전화번호를 입력해주세요." maxlength="11" v-model.trim="seller.customerCenter">
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">회사 이메일<span class="star">*</span></h5>
          </label>
          <input type="email"  class="form-control" placeholder="이메일을 입력해주세요." v-model.trim="seller.email">
        </div>

        <button class=" btn-regist" @click="registSeller" type="button">신청하기</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import {useSellerStore} from '@/stores/seller'
import { onMounted, ref } from 'vue';

const userStore = useUserStore()
const sellerStore = useSellerStore()

const seller = ref({
  userId: sessionStorage.getItem("loginUser"),
  companyName: "",
  representative: "",
  address: "",
  customerCenter: "",
  email: "",
  registrationNum: ""
})

onMounted(() => {
  userStore.getUser(sessionStorage.getItem("loginUser"))
  userStore.getUserList()
  sellerStore.getSellerList()
})



const regiNumMsg = ref("")
const regiNumCheck = function ()  {
  const numExist = sellerStore.sellerList.some(existSeller => existSeller.registrationNum === seller.value.registrationNum)
  if(numExist){
    regiNumMsg.value="이미 등록된 번호입니다."
  } else if (seller.value.registrationNum < 10) {
    regiNumMsg.value="10자리 모두 입력해주세요."
  } else {
    checked.value = true
    regiNumMsg.value="신청가능한 번호입니다."
  }
}

const registSeller = function (){
  if(regiNumMsg.value != "신청가능한 번호입니다."){
    window.alert("사업자등록번호를 확인하세요!")
    return
  } else if(seller.value.companyName == ""){
    window.alert("필수 입력 사항을 모두 입력하세요!")
    return
  }else if(seller.value.representative == ""){
    window.alert("필수 입력 사항을 모두 입력하세요!")
    return
  }else if(seller.value.address == ""){
    window.alert("필수 입력 사항을 모두 입력하세요!")
    return
  }else if(seller.value.customerCenter == ""){
    window.alert("필수 입력 사항을 모두 입력하세요!")
    return
  }else if(seller.value.email == ""){
    window.alert("필수 입력 사항을 모두 입력하세요!")
    return
  }
  sellerStore.registSeller(seller.value)
}


const checked = ref(false)


</script>



<style scoped>
.box {
  width: 100%;
  display: flex;
  justify-content: center;
  color: rgb(59, 59, 59);
}

.total {
  margin: 10px;
  width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.title {
  font-weight: 600;
  font-size: 20px
}

.label {
  font-weight: 600;
  font-size: 16px;
  margin-bottom: 0;
  margin-top: 10px;
}

.star {
  color: rgb(237, 44, 44);
  vertical-align: text-top;
}

.btn-reginum-check {
  height: 40px;
  width: 100%;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
}

.btn-reginum-check:hover {
  background-color: #26768e;
}

.btn-regist {
  margin-top: 20px;
  height: 40px;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
}

.btn-regist:hover {
  background-color: #26768e;
}

.regi-num {
  width: 250px;
}

.regi-button-box {
  width: 120px;
}
</style>