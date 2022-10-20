<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <el-row class="music-row">
    <el-col :md="24" class="music">
      <!-- <audio id="music" :src="musicInfo.url"></audio> -->
      <!-- 音乐图片 -->

      <div class="music-img">
        <el-avatar
          class="music-img music-rotate"
          :style="{'animation-name':isPlay?'music-rota':'none'}"
          :size="33"
          shape="square"
          :src="musicInfo.pic"
        >
          <i class="el-icon-loading"></i>
          <!-- <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/> -->
        </el-avatar>
        <span
          :class="{musicActive:isPlay}"
          @click="pause"
          class="iconfont music-toggle"
        >{{isPlay?'&#xe639;':'&#xe634;'}}</span>
      </div>
      <!-- 展开模块 -->
      <transition name="el-zoom-in-center">
        <div v-show="muIsShow" class="music-content">
          <transition name="el-zoom-in-bottom">
            <!-- 音乐列表模块 -->
            <div v-show="isList" class="music-list scrollbar">
              <el-scrollbar style="width: 100%; height: 100%;">
                <ul style="padding:0">
                  <li
                    :class="{active:index==ind}"
                    @click="index=ind"
                    v-for="(item,ind) of musics"
                    :key="item.musicId"
                  >
                    <span>
                      {{ind+1}}
                      <span>{{item.title}}</span>
                    </span>
                    <span>{{item.name}}</span>
                  </li>
                </ul>
              </el-scrollbar>
            </div>
          </transition>
          <!-- 标题 作者 -->
          <div class="cont-top">
            <span>{{musics[index].title}}</span>
            <span>---</span>
            <span>{{musics[index].name}}</span>
          </div>
          <!--暂停 快进   -->
          <div class="cont-cont">
            <span
              class="cont-itme iconfont"
              @click="index=index==0?musics.length-1:index-1"
            >&#xe636;</span>
            <span @click="pause" class="cont-itme iconfont">{{isPlay?'&#xe639;':'&#xe634;'}}</span>
            <span
              class="cont-itme iconfont"
              @click="index=index==musics.length-1?0:index+1"
            >&#xe638;</span>
            <span class="cont-itme iconfont" @click="isList=!isList">&#xe61f;</span>
          </div>
          <!-- 音乐拖动条 时间 -->
          <div class="cont-bottom">
            <div class="bottom-progress">
              <el-slider
                style="width:80%"
                tooltip-class="content-bg8"
                :format-tooltip="transTime"
                @change="getVal()"
                input-size="mini"
                :min="0"
                :max="max"
                v-model="numb"
              ></el-slider>
              <!-- <input  class="bottom-range" v-model="numb" type="range" min="0" :max="max" @input="getVal()"  :style="{background: '-webkit-linear-gradient(top, var(--main-5), var(--main-5)) 0% 0% / '+ numb*100/max +'% 100% no-repeat'}"/> -->
              <span class="time" style="padding-left:10px">{{newTime}}</span>
              <span class="time">/</span>
              <span class="time">{{time}}</span>
            </div>
            <!-- 循环播放  -->
            <div class="music-func">
              <span
                @click="cycle=cycle==2?0:cycle+1"
                class="cont-itme iconfont"
              >{{cycle==0?'&#xe647;':cycle==1?'&#xe66e;':'&#xe66d;'}}</span>
            </div>
          </div>
        </div>
      </transition>
      <!-- 扩展栏 -->
      <div class="music-btn" @click="MuBtnClick">
        <!-- <el-avatar v-if="isShow" :size="30" :src="musicInfo.pic" style="margin:15px 5px"></el-avatar> -->
        <i :class="muIsShow?'el-icon-arrow-left':'el-icon-arrow-right'"></i>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import Bus from "@/utils/bus.js";
export default {
  name: "Music",
  data() {
    return {
      index: 0, //播放列表
      muIsShow: false, //是否显示
      isPlay: false, //是否播放
      canplay: false, //是否能播放
      loading: false, //是否自动播放
      cycle: 0,
      numb: 0,
      time: "00:00",
      newTime: "00:00",
      max: 0,
      audio: "",
      musicInfo: {},
      isList: false,
      musics: [
        {
          music_id: "1436709403",
          title: "夏天的分",
          name: "默默",
          type: "netease",
        },
      ],
    };
  },
  methods: {
    // 打开和关闭音乐收缩栏
    MuBtnClick() {
      this.muIsShow = !this.muIsShow;
    },
    // 播放暂停
    pause() {
      if (this.audio !== null && this.canplay) {
        this.loading = true;
        if (this.audio.paused) {
          this.audio.play(); // 播放
          this.isPlay = true;
        } else {
          this.audio.pause(); // 暂停
          this.isPlay = false;
          console.log("暂停被调用了");
        }
      } else {
        this.$message({
          showClose: true,
          message: "音乐还没有加载成功呢！",
          type: "warning",
        });
      }
    },
    // 快进音乐
    getVal() {
      if (!this.audio.paused || this.audio.currentTime != 0) {
        this.audio.currentTime = this.numb;
        if (this.numb == Math.floor(this.max)) {
          this.audio.pause();
          this.isPlay = false;
        }
      }
    },
    // 获取音乐总时长
    getTime() {
      let time = this.audio.duration;
      this.max = time;
      //总共时长的秒数
      this.time = this.transTime(time);
    },
    // 时间格式化位00：00
    formatTime(value) {
      let time = "";
      let s = value.split(":");
      let i = 0;
      for (; i < s.length - 1; i++) {
        time += s[i].length == 1 ? "0" + s[i] : s[i];
        time += ":";
      }
      time += s[i].length == 1 ? "0" + s[i] : s[i];
      return time;
    },
    // 把毫秒变成时分秒
    transTime(value) {
      let time = "";
      let h = parseInt(value / 3600);
      value %= 3600;
      let m = parseInt(value / 60);
      let s = parseInt(value % 60);
      if (h > 0) {
        time = this.formatTime(h + ":" + m + ":" + s);
      } else {
        time = this.formatTime(m + ":" + s);
      }
      return time;
    },
    getMusic() {
      if (!this.musics[this.index].pause) {
        console.log("获取音乐");
        const qs = require("qs");
        let that = this;
        console.log(that.musics[that.index].type);
        this.$post(
          "/music",
          qs.stringify({
            input: that.musics[that.index].music_id,
            filter: "id",
            type: that.musics[that.index].type,
            page: 1,
          })
        )
          .then(function (res) {
            res.data[0].url = res.data[0].url.replace(
              /^http:\/\//i,
              "https://"
            );
            res.data[0].pic = res.data[0].pic.replace(
              /^http:\/\//i,
              "https://"
            );
            that.musics[that.index].musicInfo = res.data[0];
            that.musicInfo = res.data[0];
            that.audio.src = that.musicInfo.url;
          })
          .catch(function () {
            that.audio.pause(); // 暂停
            that.isPlay = false;
          });
      } else {
        console.log(this.index);
        this.musicInfo = {
          title: this.musics[this.index].title,
          author: this.musics[this.index].name,
          pic: this.musics[this.index].img,
          url: this.musics[this.index].url,
        };
        this.audio.src = this.musics[this.index].url;
      }
    },
    getList() {
      let that = this;
      this.$get("/apis/music/list")
        .then(function (res) {
          that.musics = res.data;
          let index = Math.floor(Math.random() * that.musics.length);
          if (index == 0) {
            that.index = 1;
          } else {
            that.index = index;
          }
          that.length = that.musics.length;
          // that.getMusic();
        })
        .catch(function () {});
    },
    mError() {
      if (this.loading) {
        console.log("出错");
        this.$message({
          showClose: true,
          message: "播放错误,自动播放下一首",
          type: "error",
        });
        if (this.musics[this.index].musicInfo) {
          delete this.musics[this.index].musicInfo;
        }
        this.index = this.index == this.musics.length - 1 ? 0 : this.index + 1;
      }
    },
    mCanplay() {
      this.canplay = true;
      if (this.loading) {
        this.audio.play(); // 播放
        this.isPlay = true;
      }
      this.getTime();
    },
    mTimeUpdate() {
      this.numb = this.audio.currentTime;
      this.newTime = this.transTime(this.audio.currentTime);
    },
    mEnded() {
      if (this.cycle == 0) {
        this.audio.pause(); // 暂停
        this.isPlay = false;
      } else if (this.cycle == 1) {
        this.audio.play(); // 播放
        this.isPlay = true;
      } else {
        this.index = this.index == this.musics.length - 1 ? 0 : this.index + 1;
      }
    },
  },
  mounted() {
    let that = this;
    Bus.$on("addMusic", function (info) {
      console.log(info);
      that.musics.push(info);
      // that.musicInfo={
      //     title:info.title,
      //     author:info.name,
      //     pic:info.img,
      //     url:info.url,
      // }
      // that.audio.src=info.url
      that.index = that.musics.length - 1;
      // setTimeout(200,function(){
      //     that.isAdd=true
      // })
    });
    Bus.$on("isPlay", that.pause);
  },
  created() {
    // this.audio=document.getElementById('music')
    this.audio = document.createElement("audio");
    this.getList();
    let that = this;
    this.audio.addEventListener("canplay", that.mCanplay, false),
    this.audio.addEventListener("timeupdate", that.mTimeUpdate, false);
    this.audio.addEventListener("ended", that.mEnded, false);
    this.audio.addEventListener("error", that.mError, false);
  },
  watch: {
    index() {
      if (this.loading) {
        this.audio.pause(); // 暂停
        this.isPlay = false;
      }
      this.canplay = false;
      if (this.musics[this.index].musicInfo) {
        this.musicInfo = this.musics[this.index].musicInfo;
        this.audio.src = this.musicInfo.url;
      } else {
        this.getMusic();
      }
    },
  },
  beforeUnmount() {
    let that=this
    this.audio.removeEventListener("canplay", that.mCanplay);
    this.audio.removeEventListener("ended", that.mEnded);
    this.audio.removeEventListener("error", that.mError);
    this.audio.removeEventListener("timeupdate", that.mTimeUpdate);
  },
};
</script>
<style lang="stylus" scoped>
>>>.el-avatar--square {
  border-bottom-right-radius: 0px;
  border-top-right-radius: 0px;
  border-bottom-left-radius: 0px;
}

>>>.el-slider__runway {
  &:hover {
    .el-slider__button {
      transform: scale(1.2);
    }
  }

  .el-slider__bar {
    background-color: var(--main-5);
  }

  .el-slider__button {
    width: 6px;
    height: 6px;
    border: 2px solid var(--main-6);
  }
}

.music-row {
  height: 33px;
  position: fixed;
  bottom: 0px;
  left: 0;
  z-index: 999;
  width: 33px;
  transition: all 0.5s;
  border-radius: 50%;
  overflow: hidden;
  margin: 10px;

  &:hover {
    height: 66px;
    border-radius: 0px !important;
    transform: scale(1) !important;
    overflow: visible !important;
    margin: 0px !important;
    width: auto !important;

    .music-img {
      height: 66px !important;
      width: 66px !important;
      animation-name: none !important;
    }
  }

  .music {
    transition: all 0.3s;
    display: flex;
    justify-content: left;
    box-shadow: -17px -20px 50px rgba(25, 24, 40, 0.25);

    .music-img {
      width: 33px;
      height: 33px;
      position: relative;
      cursor: pointer;
      transition: all 0.5s;

      &:hover {
        .music-toggle {
          color: var(--main-5);
          font-size: 25px;
        }
      }

      .music-rotate {
        animation-duration: 6s;
        animation-timing-function: linear;
        animation-iteration-count: infinite;
      }

      .music-toggle {
        width: 30px;
        height: 30px;
        font-size: 20px;
        color: var(--main-6);
        text-align: center;
        line-height: 30px;
        position: absolute;
        bottom: 50%;
        right: 50%;
        transform: translate(50%, 50%);
        transition: all 0.3s;

        &:hover {
          color: var(--main-5);
        }
      }

      .musicActive {
        bottom: 0px;
        right: 0px;
        transform: translate(0%, 0%);
      }
    }

    .music-content {
      width: 334px;
      height: 66px;
      border-top: 1px solid #e9e9e9;
      padding: 3px 5px;
      background-color: #fff;
      box-sizing: border-box;
      position: relative;
      display: flex;
      // align-items center
      flex-wrap: wrap;

      .music-list {
        width: 100%;
        height: 100px;
        background-color: #fff;
        position: absolute;
        top: -100px;
        left: 0;
        margin: 0;
        padding: 5px;
        overflow: auto;
        box-sizing: border-box;
        border-bottom: 1px solid #ccc;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        box-shadow: -17px -20px 50px rgba(25, 24, 40, 0.25);
      }

      .music-list li {
        height: 30px;
        line-height: 30px;
        font-size: 14px;
        padding: 0 4px;
        margin: 2px 0;
        color: var(--main-6);
        background-color: rgb(248, 249, 249);
        transition: all 0.3s;
        cursor: pointer;
        display: flex;
        justify-content: space-between;

        span {
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
        }
      }

      .active {
        color: rgb(245, 108, 108) !important;
        border-left: 3px solid rgb(245, 108, 108);
      }

      .cont-top {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        font-size: 15px;
        width: 65%;
        height: 40px;
        line-height: 40px;
      }

      .cont-cont {
        position: absolute;
        top: 3px;
        right: 0;

        .cont-itme {
          display: inline-block;
          width: 30px;
          height: 40px;
          text-align: center;
          line-height: 40px;
          font-size: 18px;
          transition: all 0.2s;
        }

        .cont-itme:hover {
          cursor: pointer;
          color: #ccc;
        }
      }

      .cont-bottom {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 28px;
        display: flex;
        justify-content: left;
        padding: 0 5px;
        box-sizing: border-box;

        .bottom-progress {
          width: 80%;
          display: flex;
          align-items: center;
        }

        .time {
          font-size: 12px;
        }

        .music-func {
          line-height: 28px;
        }

        .music-func span {
          margin: 0 3px;
          cursor: pointer;
        }
      }
    }

    .music-btn {
      height: 66px;
      width: 18px;
      background-color: #ccc;
      cursor: pointer;
      line-height: 66px;
      border-bottom-right-radius: 4px;
      border-top-right-radius: 4px;
      overflow: hidden;
      transition: all 0.3s;
    }
  }
}

@media (max-width: 418px) {
  .music-content {
    width: 260px !important;
  }
}

@media (max-width: 320px) {
  .music-content {
    width: 220px !important;
  }
}
</style>
<style lang="stylus">
@keyframes music-rota {
  0% {
    transform: rotate(-360deg);
  }

  100% {
    transform: rotate(0deg);
  }
}
</style>