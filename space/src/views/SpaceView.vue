<template >
  <el-card shadow="never" style="margin-top: 20px;width: 50%;margin-left:25%">
    <el-input placeholder="请输入关键字" clearable style="width: 300px"></el-input>
    <el-button :icon="Search" style="margin-left: 10px" circle />
    <el-button type="warning" :icon="Share" style="margin-left: 10px" circle />
    <el-button @click="dialogFormVisible = true" type="primary" :icon="Edit" round plain style="float: right;" circle/>
    <el-dialog v-model="dialogFormVisible" title="发送动态">
      <el-form @submit.prevent="dialogFormVisible = false">
        <el-form-item label="动态内容" :label-width="formLabelWidth">
          <el-input v-model="content" type="textarea" rows="5" autocomplete="off" />
        </el-form-item>
        <el-form-item label="分享" :label-width="formLabelWidth">
          <el-select v-model="option" placeholder="Please select">
            <el-option label="所有人可见" value="public" />
            <el-option label="仅自己可见" value="private" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="write">
              提交
            </el-button>
          </span>
      </template>
    </el-dialog>
  </el-card>
  <el-card shadow="never" style="margin-top: 20px;width: 50%;margin-left:25%">
    <el-space direction="vertical" style="margin-top: 10px">
      <el-card v-for="(post,index) in posts" :key="index" class="box-card" style="width: 700px">
        <p>
          {{post.content}}
        </p>
        <img src="../assets/icon.png" v-if="!post.like" style="width:20px;height: 20px;"
             @click="add_agree(post.pid,parseInt($store.state.user.id),index)">
        <img src="../assets/icon-2.png" v-else style="width:20px;height: 20px;"
             @click="delete_agree(post.pid,parseInt($store.state.user.id),index)">
        <span style="font-size: small;color: darkgray;margin-left: 5px">({{post.cnt}}人点赞)</span>
        <img src="../assets/pinglun.png" style="width:20px;height: 20px;margin-left: 20px;" @click="open_box(index);get_comment(post.pid)">
        <span style="font-size: small;color: darkgray;margin-left: 5px">({{post.comments.length}}条评论)</span>
        <div>
          <div class="box" v-show="post.boxshow" style="margin-top: 10px;">
            <div class="row" style="margin-top: 10px">
              <div class="col-1">
                <img :src="$store.state.user.photo" class="img-fluid" style="border-radius: 50%;width: 50px">
              </div>
              <div class="col-10">
                <el-form-item>
                  <el-input v-model="comment" type="textarea" rows="2" autocomplete="off" placeholder="在这里写评论"/>
                </el-form-item>
                <el-button type:="info" link style="float: right" @click="write_comment(post.pid)">提交评论</el-button>
              </div>
              <el-divider />
              <div v-for="(comment,index) in post.comments" :key="index" style="margin-top: 10px;margin-left: 10px">
                <div class="row">
                  <div class="col-1">
                    <img :src="comment.photo" class="img-fluid" style="border-radius: 50%">
                  </div>
                  <span class="col-10">
                    <div style="font-size: small;color: lightskyblue">{{comment.username}}</div>
                    <div style="font-size: medium">{{comment.content}}</div>
                  </span>
                  <el-divider border-style="dotted" />
                </div>
              </div>
            </div>
          </div>

        </div>
        <template #header>
        <div class="card-header">
          <div class="row">
            <div class="col-1">
              <img :src="post.photo" class="img-fluid" style="border-radius: 50%;width: 50px">
            </div>
            <div class="col-5">
              <span style="margin-left: 20px;font-weight: bold">{{post.username}}</span>
            </div>
            <div class="col-6">
              <span style="text-align: center;font-size: small">更新于{{post.timer}}</span>
            </div>
          </div>
        </div>
      </template>
      </el-card>
    </el-space>
  </el-card>
</template>

<script>
import {
  Delete,
  Edit,
  Message,
  Search,
  Star,
  Share,
} from '@element-plus/icons-vue'
import {reactive,ref} from "vue"
import $ from 'jquery'
import $store from "@/store/user"
import {ElMessage} from "element-plus";

export default {
  name: "SpaceView",
  components: {

  },
  setup(){
    const dialogFormVisible = ref(false);
    const formLabelWidth = '140px';
    let posts = reactive([]);
    let content = ref('');
    let option = ref('');
    let comment = ref('');
    const write = ()=>{
      dialogFormVisible.value = false;
      let dataTime = new Date();
      let time_value = dataTime.getFullYear() + "-" + (dataTime.getMonth() + 1) + "-" + dataTime.getDate() + ' '+
          dataTime.getHours() + ':' + dataTime.getMinutes() + ':' + dataTime.getSeconds();
      $.ajax({
        url: 'http://localhost:3000/user/post/write/',
        type: 'post',
        headers: {
          Authorization: 'Bearer '+$store.state.token.toString()
        },
        data: {
          content: content.value,
          timer: time_value.toString(),
          id: $store.state.id.toString(),
        },
        success(resp){
          if(resp.error_message === 'success')
          {
            get_post();
            ElMessage.success('发表成功');
          }
          else ElMessage.error('内容不能为空');
        },
        error(){
          if($store.state.id === '') ElMessage.error('请先登陆');
        }
      })
      content.value='';
      option.value='';
    }
    const add_agree = (pid,id,index)=>{
      $.ajax({
        url: 'http://localhost:3000/user/agree/add/',
        type: 'get',
        headers: {
          Authorization: 'Bearer '+$store.state.token.toString(),
        },
        data: {
          pid: pid,
          id: id,
        },
        success(){
          posts[index].like = !posts[index].like;
          count_agree(pid);
        },
        error(){
          ElMessage.error('请先登陆');
        }
      })
    }
    const delete_agree = (pid,id,index)=>{
      $.ajax({
        url: 'http://localhost:3000/user/agree/delete/',
        type: 'get',
        headers: {
          Authorization: 'Bearer '+$store.state.token.toString(),
        },
        data: {
          pid: pid,
          id: id,
        },
        success(){
          posts[index].like = !posts[index].like;
          count_agree(pid);
        },
        error(){
          ElMessage.error('请先登陆');
        }
      })
    }
    const get_agree = (id)=>{
      $.ajax({
        url: 'http://localhost:3000/user/agree/get/',
        type: 'get',
        data: {
          id: id,
        },
        success(resp){
          for(let pid in resp){
            let index = posts.findIndex(value => value.pid === pid);
            posts[index].like=true;
          }
        },
      })
    }
    const count_agree = (pid)=>{
      $.ajax({
        url: 'http://localhost:3000/user/agree/count/',
        type: 'get',
        data: {
          pid: pid,
        },
        success(resp){
          let index = posts.findIndex(value => value.pid === pid);
          posts[index].cnt = resp.cnt;
        }
      })
    }
    const get_post = ()=>{
      $.ajax({
        url: 'http://localhost:3000/user/post/get/',
        type: 'get',
        success(resp) {
          for(let post in resp){
            resp[post].comments = reactive('');
            resp[post].boxshow = false;
            resp[post].like=false;
            resp[post].cnt = 0;
            posts.unshift(resp[post]);
            get_comment(resp[post].pid);
            count_agree(resp[post].pid);
          }
          get_agree(parseInt($store.state.id))
        },
      })
    }

    const write_comment = (pid)=>{
      $.ajax({
        url: 'http://localhost:3000/user/post/comment/',
        type: 'post',
        headers: {
          Authorization: 'Bearer '+$store.state.token.toString()
        },
        data: {
          content: comment.value,
          pid: pid,
          id: $store.state.id.toString(),
        },
        success(resp){
          get_comment(pid);
          if(resp.error_message === 'success'){
            comment.value = '';
          }else{
            ElMessage.error('评论不能为空');
          }
        },
      })
    }

    setTimeout(get_post,100);

    const open_box = (id)=>{
      posts[id].boxshow = !posts[id].boxshow;
    }

    const get_comment = (pid)=>{
      $.ajax({
        url: 'http://localhost:3000/user/post/getComment/',
        type: 'get',
        data: {
          pid: parseInt(pid),
        },
        success(resp){
          let index = posts.findIndex(value => value.pid === pid);
          posts[index].comments = resp;
        }
      })
    }

    return{
      Delete,
      Edit,
      Message,
      Search,
      Star,
      Share,
      posts,
      dialogFormVisible,
      formLabelWidth,
      content,
      option,
      write,
      get_post,
      open_box,
      comment,
      write_comment,
      get_comment,
      add_agree,
      delete_agree,
    }
  }
}
</script>

<style lang="scss" scoped>
p,span{
  word-break: break-all;
}
el-card{
  border-radius: 100px;
}
.box {
  background-color: rgb(248,248,248);
  overflow: hidden;
}
img:hover{
  cursor: pointer;
}
</style>

