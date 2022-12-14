<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-3">
        <form @submit.prevent="register">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password">
          </div>
          <div class="mb-3">
            <label for="confirm_password" class="form-label">确认密码</label>
            <input v-model="confirmedPassword" type="password" class="form-control" id="confirm_password">
          </div>
          <button type="submit" class="btn btn-primary" @click="open">注册</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import {ref} from "vue"
import $ from 'jquery'
import router from "@/router/index"
import {ElMessage} from "element-plus";

export default {
  name: "RegisterView",
  components: {

  },
  setup() {
    let username = ref('');
    let password = ref('');
    let confirmedPassword = ref('');
    let error_message = ref('');
    const register = () => {
      error_message.value = '';
      $.ajax({
        url: 'http://localhost:3000/user/account/register/',
        type: 'post',
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
        },
        success(resp){
          if (resp.error_message === "success") {
            setTimeout(() => {
                  router.push({name: 'login'});
                },1000)
          } else {
            error_message.value = resp.error_message;
          }
        },
      })
    }
    const set_message = () => {
      if(error_message.value==="")
      {
        ElMessage({
          message: '注册成功',
          type: 'success',
        })
      }
      else{
        ElMessage.error(error_message.value)
      }
    }
    const open = () => {
      setTimeout(set_message,200);
    }
    return {
      username,
      password,
      confirmedPassword,
      error_message,
      register,
      open,
    }
  }
}
</script>

<style scoped>
button{
  display:block;
  margin:0 auto;
  width: 50%;
}
div.container{
  margin-top: 20px;
}
div.error_message{
  color: orangered;
}
</style>
