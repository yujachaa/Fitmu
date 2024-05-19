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
          <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp" v-model="userStore.user.nickname">
          <div class="invalid-feedback">필수 입력 항목입니다.</div>
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
          <input type="date" id="birth" class="form-control" placeholder="생년월일을 선택해주세요">
        </div>

        <div class="input">
          <label class="form-label">
            <h5 class="label">성별</h5>
          </label>
          <div class="radios">
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1">
              <label class="form-check-label" for="inlineRadio1">남성</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2">
              <label class="form-check-label" for="inlineRadio2">여성</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="0">
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
  nickname : "",
  birth:"",
  gender: 0
})

onMounted(()=>{
  userStore.getUser(sessionStorage.getItem("loginUser"))

})

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
</style>