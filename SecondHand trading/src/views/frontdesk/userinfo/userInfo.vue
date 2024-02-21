<template>
  <div>
    <el-container class="userInfo" style="">
      <div style="height: 40px;float: left;"></div>
      <el-header style="width: 100%;position:fixed;z-index: 9;height: 40px;">
        <!--      顶部导航栏-->
        <div class="header">
          <div>
            <span>
              <img src="" alt="">
            </span>
          </div>
          <div class="topbar-user">
            <a href="javascript:;" @click="message()">我的消息</a>
            <a href="javascript:;" @click="backhome()">返回商城</a>
            <a href="javascript:;" @click="reg()">退出登陆</a>
          </div>
        </div>
        <!--  顶部导航栏end-->
      </el-header>
      <el-container>
        <div style="width: 200px;float: left;height: 100%;"></div>
        <el-aside width="200px" style="background-color:#e9eef3;position:fixed;height: 100%;z-index: 9;">
          <!--  侧边导航栏-->
          <el-menu style="" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" unique-opened router
            :default-active="activePath">
            <el-menu-item index="/baseInfo" @click="saveNavState('/baseInfo')"><i
                class="el-icon-document"></i>基本信息</el-menu-item>
            <el-menu-item index="/updatePass"><i class="el-icon-unlock"></i>安全设置</el-menu-item>

            <el-menu-item index="/order"><i class="el-icon-news"></i>我的订单</el-menu-item>
            <el-menu-item index="/releaseGoods"><i class="el-icon-sell"></i>我的发布</el-menu-item>
            <el-menu-item index="/footprint"><i class="el-icon-view"></i>浏览记录</el-menu-item>
            <el-menu-item index="/collect"><i class="el-icon-wallet"></i>我的收藏</el-menu-item>

          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "userInfo",
  data() {
    return {
      // 被激活的链接地址
      activePath: ""
    }
  },
  created() {
    // this.activePath=window.sessionStorage.getItem("activePath")
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    // 保存链接的激活状态
    saveNavState(val) {
      // window.sessionStorage.setItem("activePath",val);
      this.activePath = val
    },
    reg() {
      sessionStorage.clear()
      this.$message.success("账号退出成功")
      this.$router.push("/login")
    },
    backhome() {
      this.$router.push("/home")
    },
    message() {
      this.$router.push("/message")
    }
  },
  mounted() {

  }
}
</script>

<style scoped>


.el-header {
  background: #333333;
  height: 20vh;
}

.el-header .header {
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  padding-bottom: 10px;
  border-bottom: 1px solid rgba(0, 0, 0, .1);

}

.el-header .header .topbar-user a {
  display: inline-block;
  color: #B0B0B0;
  margin-right: 30px;
  font-weight: normal;
  text-decoration: none;
  margin-top: 10px;
}
</style>