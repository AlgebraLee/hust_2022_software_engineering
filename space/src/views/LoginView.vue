<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-3">
        <form @submit.prevent="login">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password">
          </div>
          <button type="submit" class="btn btn-primary" @click="open">登陆</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import {useStore} from 'vuex'
import {ref} from "vue";
import router from "@/router/index";
import {ElMessage} from "element-plus";

export default {
  name: "LoginView",
  components: {
  },
  setup(){
    let username = ref('');
    let password = ref('');
    let error_message = ref('');
    const store = useStore();
    const jwt_token = localStorage.getItem("jwt_token");
    if(jwt_token){
      store.commit("updateToken",jwt_token);
      store.dispatch("getInfo",{
        success(){
          router.push({name: 'home'});
        }
      })
    }
    const login = () => {
      error_message.value = '';
      store.dispatch("login",{
        username: username.value,
        password: password.value,
        success(){
          store.dispatch("getInfo",{
            success(){
              router.push({name: 'home'});
            }
          })
        },
        error(){
          error_message.value = '用户名或密码错误';
        },
      });
    }
    const set_message = () => {
      if(error_message.value==="")
      {
        ElMessage({
          message: '登陆成功',
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
      error_message,
      login,
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
div.error_message {
  color: orangered;
}
</style>
