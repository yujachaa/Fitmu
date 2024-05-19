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
          <div id="nickHelp" class="form-text">
          </div>
          <div class="company-number d-flex justify-content-between row">
            <div class="col-8">
              <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp"
                v-model="userStore.user.nickname" placeholder="'-'를 제외하고 입력해주세요." @keyup="nickCheck">
            </div>
            <div class="col-3 px-0 mx-0">
              <button class="btn btn-primary">인증하기</button>
            </div>
          </div>
          <div class="form-text" id="nickHelp">{{ nickMsg }}</div>
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">회사명<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp"
            v-model="userStore.user.nickname" placeholder="2자~20자까지 입력할 수 있어요." @keyup="nickCheck">
          <div class="form-text" id="nickHelp">{{ nickMsg }}</div>
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">회사 주소지<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp"
            v-model="userStore.user.nickname" placeholder="2자~20자까지 입력할 수 있어요." @keyup="nickCheck">
          <div class="form-text" id="nickHelp">{{ nickMsg }}</div>
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">고객센터<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp"
            v-model="userStore.user.nickname" placeholder="2자~20자까지 입력할 수 있어요." @keyup="nickCheck">
          <div class="form-text" id="nickHelp">{{ nickMsg }}</div>
        </div>
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">회사 이메일<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="email" id="nickname" class="form-control" aria-describedby="nickHelp"
            v-model="userStore.user.nickname" placeholder="2자~20자까지 입력할 수 있어요." @keyup="nickCheck">
          <div class="form-text" id="nickHelp">{{ nickMsg }}</div>
        </div>
        <button class="btn btn-primary btn-regist" @click="registSeller" type="button">신청하기</button>
      </form>
    </div>
  </div>

</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { onMounted, ref } from 'vue';

const userStore = useUserStore()

const user = ref({
  nickname: "",
  birth: "",
  gender: "0"
})

onMounted(() => {
  userStore.getUser(sessionStorage.getItem("loginUser"))
  userStore.getUserList()
})

const registSeller = function () {
  if (nickMsg.value == "이미 존재하는 닉네임입니다." || nickMsg.value == "2자 이상 입력해주세요." || nickMsg.value == "20자 이하로 입력해주세요.") {
    window.alert("닉네임을 확인해주세요!")
    return
  }
  user.value = userStore.user
  console.log(user.value)

  userStore.updateUser(user.value);
}

//닉네임 체크
const nickMsg = ref("")
const nickCheck = function () {
  const nickExist = userStore.userList.some(existuser => existuser.nickname === userStore.user.nickname)

  if (nickExist) {
    nickMsg.value = "이미 존재하는 닉네임입니다."
  } else if (userStore.user.nickname < 2) {
    nickMsg.value = "2자 이상 입력해주세요."
  } else if (userStore.user.nickname > 20) {
    nickMsg.value = "20자 이하로 입력해주세요."
  } else {
    nickMsg.value = "사용가능한 닉네임입니다! ✔️"
  }
}

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

.btn-check {
  width: 100%;
  height: 40px;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
}
.btn-check:hover {
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
</style>