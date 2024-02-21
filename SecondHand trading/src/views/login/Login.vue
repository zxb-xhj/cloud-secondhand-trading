<template>
  <div>
    <div style="background-color: rgb(246, 246, 246);width: 100vw;height: 100vh;">
    <a  style="margin-left: 3.5vw;float: left;margin-top: 8vh;" class="el-dropdown-link" @click="fanhui">
      <i class="el-icon-arrow-left"></i>返回
    </a>
    <div class="floatBox">
        <div class="sss">
            <div class="sign-in">
                <div style="height: 20vh;"></div>
                <el-form  ref="loginFromRef" class="login-form" :model="form" :rules="rules">
                <div style="font-size: 2.8vh;text-align: center;height: 8vh;">Welcome back</div>
                    <div style="height: 4vh;font-size: 2.1vh;color: rgb(157, 176, 183);">name</div>
                    <el-form-item prop="username">
                    <el-input prefix-icon="el-icon-user" v-model="form.username"></el-input>
                    </el-form-item>
                    <div style="height: 4vh;font-size: 2.1vh;color: rgb(157, 176, 183);">password</div>
                    
                    <el-form-item prop="password">
                    <el-input @keyup.enter.native="login" prefix-icon="el-icon-lock" v-model="form.password" type="password" ></el-input>
                    </el-form-item>
                    <el-form-item class="btn" style="margin-top: 4vh;">
                    <el-button type="primary" round class="login-btn" @click="login">SIGN IN</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="ddd" style="float: left;width: 20vw;height: 80vh;text-align: center;">
        <div style="margin:3vh;color: white;">
            <div style="height: 7vh;font-size: 3.2vh;margin-top: 8vh;">Second-hand trading platform</div>
            <div style="height: 7vh;font-size: 3vh;margin-top: 8vh;">New here?</div>
            <div style="font-size: 2vh;">Then click the sign up button to join us!</div>
        </div>
        <div>
            <el-button style="margin-top: 25vh;border: white solid 2px;color: white;background-color: transparent;"
             round @click="register">SIGN UP</el-button>
        </div>
    </div>
    </div>
    

</div>
  </div>

</template>

<script>

import Regiter from '../register/Regiter.vue'
//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
// $('.message a').click(function(){
//    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
// });
export default {
  components: {Regiter},
name: "Login",
  data() {
    var validateUser = async (rule, value, callback) => {
      //判断用户是否为空，如果为空返回提示信息，否则就进行服务器请求，并把当前用户名携带给服务器
      if (value=="") {
        callback(new Error("请输入登录名称"))
      }else{
        if(value){
          const {data:res}=await this.$http.post('/api/cloud-member/member/getName/'+this.form.username,{
            username:this.form.username
          })
          if(res.code==='A00101'){
            return callback(new Error("该用户名不存在"))
          }else{
            callback();
          }
        }
      }
    };
    return {
      form: {
        username: "",
        password: "",
        address: "",

      },
      rules: {
        username: [
          {required: true,validator:validateUser, trigger: "blur"},
          {min: 3, max: 10, message: "长度为3-10个字符", trigger: "blur"},
        ],
        password: [
          {required: true, message: "请输入登录密码", trigger: "blur"},
          {min: 6, max: 18, message: "长度为6-18个字符", trigger: "blur"},
        ]
      },

    }
  },
  created() {
  },
  methods:{
    login(){
      //对整个表单进行验证
      this.$refs.loginFromRef.validate(vali=>{
        if(!vali){
          return
        }
        //将promise对象进行数据解构
         this.$http.post("/api/cloud-member/member/login",this.form)
             .then(res=>{
               // 状态不等于201表示登陆失败，其余的为成功
              if(res.data.code=='000000'){
                console.log(res.data.data.token)
                // window.location.reload();
                this.$notify.success({
                   title:"登录成功",
                   message:`亲爱的${res.data.data.member.nickname==undefined?res.data.data.member.username:res.data.data.member.nickname} 欢迎来到二手交易平台~ `
                 });
                //把登陆信息存储到本地
                sessionStorage.setItem("user",JSON.stringify(res.data.data.member));
                sessionStorage.setItem("token",res.data.data.token);
                sessionStorage.setItem("nickname",res.data.data.member.nickname);
                sessionStorage.setItem("user_id",res.data.data.id);
                this.$store.commit("saveUserName",res.data.data.member.username);
                //登陆信息保存到vuex里
                  //进行跳转至首页
                this.$router.push("/home");
              }else{
                return this.$notify({
                   title:res.data.msg,
                   message:"登录失败",
                   type:"error"
                 });
           }
         }).catch(err=>{
           return Promise.reject(err);
         })
      })
    },
    register(){
      // this.$emit('show');
      this.$router.push("/register")
    },
    fanhui(){
      this.$router.push("/index")
    }
  }
}
</script>
<style scoped>

.floatBox {
    width: 60vw;
    height: 80vh;
    /* background-color: rgb(255, 255, 255);    x=0代表移回原位置 */
    background-image: url("https://ts1.cn.mm.bing.net/th/id/R-C.6dfa47b05ddd9d86468cdd8f5a1e4272?rik=%2bdcjoX7ul4qbYg&riu=http%3a%2f%2fwebimg.finebornchina.cn%2foriginal%2fuploads%2fallimg%2f150126%2f1-15012613014T24.jpg&ehk=2CVmOBnG5AgwDvYxNEcKH1zmbofjNx72tWi4JRiWp5M%3d&risl=&pid=ImgRaw&r=0");
    /* transform: translateX(0); */
    /* 移回也需要0.4s时间过渡 */
    /* transition: transform 0.4s; */
    margin-top: 15vh;
    margin-left: 15vw;
    float: left;
}
 
/* hover样式 */
.sss:hover {
    /* x=-10px代表左移10px */
    translate: translateX(-10vw);
    /* 0.4s完成transform移动效果*/
    translate: transform 0.4s;
 
}
.sign-in{
    /* margin-top: 20vh; */
    margin-left: 10vw;
}
.sss{
    width: 40vw;
    height: 80vh;
    background-color: rgb(255, 255, 255);
    float: left;
}
.login-form{
  position: absolute;
  width: 20vw;
  padding: 0 15px;
  box-sizing: border-box;
  text-align: center;
}
.ddd{
    background-size: 20vw 80vh;
    translate: transform 0.4s;
    background-color: rgba(0, 0, 0, 0.5);
}
.ddd:hover{
    
    /* x=-10px代表左移10px */
    translate: translateX(-30vw);
    /* 0.4s完成transform移动效果*/
    translate: transform 0.4s;
}
</style>