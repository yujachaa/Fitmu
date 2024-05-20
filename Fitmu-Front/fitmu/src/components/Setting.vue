<template>
  <div class="box">
    <div class="container total">
      <div class="d-flex justify-content-center mt-3">
        <h4 class="title">회원정보수정</h4>
      </div>
      <hr>
      <form class="row g-3">
        <div class="input">
          <label for="nickname" class="form-label">
            <h5 class="label">닉네임<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp"
            v-model.trim="userStore.user.nickname" placeholder="2자~20자까지 입력할 수 있어요." @keyup="nickCheck">
          <div class="form-text" id="nickHelp">{{ nickMsg }}</div>
        </div>

        <div class="input">
          <label for="email" class="form-label">
            <h5 class="label">이메일<span class="star">*</span></h5>
          </label>
          <div id="nickHelp" class="form-text">
          </div>
          <input type="text" id="email" class="form-control" :value="userStore.user.email" disabled readonly>
        </div>

        <div class="input">
          <label for="birth" class="form-label">
            <h5 class="label">생년월일</h5>
          </label>
          <input type="date" id="birth" class="form-control" placeholder="생년월일을 선택해주세요" v-model="userStore.user.birth">
        </div>

        <div class="input">
          <label class="form-label">
            <h5 class="label">성별</h5>
          </label>
          <div class="radios">
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1"
                :checked="userStore.user.gender == 1" v-model="userStore.user.gender">
              <label class="form-check-label" for="inlineRadio1">남성</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2"
                :checked="userStore.user.gender == 2" v-model="userStore.user.gender">
              <label class="form-check-label" for="inlineRadio2">여성</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="0"
                :checked="userStore.user.gender == 0" v-model="userStore.user.gender">
              <label class="form-check-label" for="inlineRadio3">선택하지 않음</label>
            </div>
          </div>
        </div>

        <button class="btn btn-primary btn-update" @click="updateUser" type="button">완료</button>
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

const updateUser = function () {
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

.btn-update {
  margin-top: 20px;
  height: 40px;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
}

.btn-update:hover {
    background-color: #26768e;
}
</style>