<template>
  <div style="background-color: rgb(246, 246, 246);width: 100vw;height: 100vh;">
    <a style="margin-left: 3.5vw;float: left;margin-top: 8vh;" class="el-dropdown-link"
      href="javascript:history.back (-1)">
      <i class="el-icon-arrow-left"></i>返回
    </a>
    <div class="floatBox">
      <div class="ddd" style="float: left;width: 20vw;height: 80vh;text-align: center;">
        <div style="margin:3vh;color: white;">
          <div style="height: 7vh;font-size: 3.2vh;margin-top: 8vh;">Second-hand trading platform</div>
          <div style="height: 7vh;font-size: 3vh;margin-top: 8vh;">Got an account?</div>
          <div style="font-size: 2vh;">If you already has an account, just sign in. We've missed you!</div>
        </div>
        <div>
          <el-button style="margin-top: 25vh;border: white solid 2px;color: white;background-color: transparent;" round
            @click="login">SIGN IN</el-button>
        </div>
      </div>
      <div class="sss">
        <div class="sign-in">
          <div style="height: 16vh;"></div>
          <el-form ref="rulForm" class="login-form" :model="ruleForm" :rules="rules">
            <div style="font-size: 2.8vh;text-align: center;height: 8vh;">Please register</div>
            <div style="height: 4vh;font-size: 2.1vh;color: rgb(157, 176, 183);">name</div>
            <el-form-item prop="username">
              <el-input prefix-icon="el-icon-user" v-model="ruleForm.username"></el-input>
            </el-form-item>
            <div style="height: 4vh;font-size: 2.1vh;color: rgb(157, 176, 183);">password</div>

            <el-form-item prop="pass">
              <el-input prefix-icon="el-icon-lock" v-model="ruleForm.pass" type="password"></el-input>
            </el-form-item>
            <div style="height: 4vh;font-size: 2.1vh;color: rgb(157, 176, 183);">Confirm your password</div>

            <el-form-item prop="checkPass">
              <el-input @keyup.enter.native="login" prefix-icon="el-icon-lock" v-model="ruleForm.checkPass"
                type="password"></el-input>
            </el-form-item>
            <el-form-item class="btn" style="margin-top: 4vh;">
              <el-button type="success" round class="login-btn" @click="handleSave()">NEXT STEP</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>

    </div>
    <el-dialog :title="goodsFormTitle" :visible.sync="goodsFormShow">
      <el-form :rules="rules" label-width="120px">
        <el-form-item prop="aid" label="学校">
          <el-cascader size="medium" :props="props" v-model="schools" :options="schoolList" filterable></el-cascader>
        </el-form-item>
      </el-form>
      <div v-if="schools.length > 0" disabled
        style="width: 22.5vw;text-align: center;border: 2px solid #d3ebf7;border-radius: 25px;margin-left: 13vw;">
        <p style="margin: 0.1vw;font-size: 1.2vw;">
          {{ schools[schools.length - 1] }}</p>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">CANCELLATION</el-button>
        <el-button type="primary" @click="submitForm()">SIGN UP</el-button>
      </div>
    </el-dialog>

  </div>
</template>
    
<script>

//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
// $('.message a').click(function(){
//    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
// });
export default {
  name: "Regiter",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.rulForm.validateField('checkPass');
        }
        // 检查是否只包含英文字母、数字和符号
        if (!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)) {
          callback(new Error("只能包含英文、数字和符号"));
          return;
        }

        // 检查长度是否在3-16之间
        if (value.length < 3 || value.length > 16) {
          callback(new Error("长度为3-16个字符"));
          return;
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    var validateUser = async (rule, value, callback) => {
      //判断用户是否为空，如果为空返回提示信息，否则就进行服务器请求，并把当前用户名携带给服务器
      if (value == "") {
        callback(new Error("用户名不能为空"))
      } else {
        if (value) {
          const { data: res } = await this.$http.post('/api/cloud-member/MemberLogin/getUserByUserName', {
            name: this.ruleForm.username
          })

          //如果用户名已被注册返回提示信息给用户，否则不做任何处理
          if (res.code == 'A00104') {
            return callback(new Error("该用户名已被注册"))
          } else {
            // 检查是否以英文字母开头
            if (!/^[a-zA-Z]/.test(value)) {
              callback(new Error("必须以英文字母开头"));
              return;
            }

            // 检查长度是否在3-18之间
            if (value.length < 3 || value.length > 18) {
              callback(new Error("长度为3-5个字符"));
              return;
            }

            // 检查是否只包含英文字母和数字
            if (!/^[a-zA-Z0-9]+$/.test(value)) {
              callback(new Error("只能包含英文和数字"));
              return;
            }

            callback();
          }
        }
      }

    };
    return {
      ruleForm: {
        username: '',
        pass: '',
        checkPass: '',
      },
      goodsFormShow: false,
      goodsFormTitle: '选择学校',
      props: {
        label: 'name',
        value: 'name',
        children: 'schoolVOS',
        expandTrigger: 'hover'
      },
      schoolList: [],
      schools: {},
      rules: {
        aid: [{ required: true, trigger: 'blur', message: '请先选择学校' }],
        username: [
          { required: true, validator: validateUser, trigger: "blur" },
          { min: 3, max: 18, message: "长度为3-5个字符", trigger: "blur" },
        ],
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' },
          { min: 6, max: 18, message: "长度为6-18个字符", trigger: "blur" },
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' },
          { min: 6, max: 18, message: "长度为6-18个字符", trigger: "blur" },
        ]
      }

    }
  },
  activated() {
    this.getSchool();
  },
  methods: {
    getSchool() {
      this.$http.post("/api/cloud-member/school/list"
      ).then(res => {
        this.schoolList = res.data.data
      });
    },
    closeDialog() { this.goodsFormShow = false; this.schools = {} },
    handleSave() {
      //对整个表单进行预验证，如果验证成功的话发起网络请求，携带注册用户名和密码给服务器进行处理，并给用户提示信息，后跳转至登陆页面进行登陆
      this.$refs.rulForm.validate((valid) => {
        if (!valid) {
          return
        }
        if (valid) {
          this.goodsFormShow = true;
        }
      })
    },
    submitForm() {
      console.log(this.schools[this.schools.length - 1])
      if (this.schools[this.schools.length - 1] != undefined) {
        this.$http.post("/api/cloud-member/MemberLogin/register", {
          name: this.ruleForm.username,
          password: this.ruleForm.pass, schoolName: this.schools[this.schools.length - 1]
        }).then(res => {
          if (res.data.code === 503) {
            this.$message.error("抱歉,账号注册失败");
          } else {
            this.$message.success("恭喜,账号注册成功");
            this.$router.push("/login");
          }
          this.goodsFormShow = false;
        })
      } else {
        this.$message({
          message: '请选择学校',
          type: 'warning'
        });
      }

    },
    login() {
      // this.$emit('show');
      this.$router.push("/login");
    },
    resetForm() {
      this.$refs.rulForm.resetFields();
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

.sign-in {
  /* margin-top: 20vh; */
  margin-left: 10vw;
}

.sss {
  width: 40vw;
  height: 80vh;
  background-color: rgb(255, 255, 255);
  float: left;
}

.login-form {
  position: absolute;
  width: 20vw;
  padding: 0 15px;
  box-sizing: border-box;
  text-align: center;
}

.ddd {
  background-size: 20vw 80vh;
  translate: transform 0.4s;
  background-color: rgba(0, 0, 0, 0.5);
}

.ddd:hover {

  /* x=-10px代表左移10px */
  translate: translateX(-30vw);
  /* 0.4s完成transform移动效果*/
  translate: transform 0.4s;
}</style>