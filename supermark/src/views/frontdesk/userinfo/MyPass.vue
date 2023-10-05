<template>
  <div id="mypass">
    <div style="height: 14rem;" v-loading="listLoading">
      <div style="height: 30%;"></div>
      <div style="margin-left: 5%;">
        <div style="width: 25%;float: left;">
          <div style="float: left;margin-top: 10px;margin-right: 30px;margin-left: 30px;">
            <img style="width: 3.5rem;height:3.5rem;border-radius: 1rem;"
              src="https://img.tukuppt.com/png_preview/00/38/48/rHWWp1MjEj.jpg!/fw/780">
          </div>
          <div style="float: left;">
            <p style="color: #000;margin-top: 10px;font-size: 1.2rem;">总销售额</p>
            <p style="margin: 5px;color: #000;font-size: 1.5rem;">{{ salesTotal==null?0:salesTotal }}</p>
          </div>
        </div>
        <div style="width: 25%;float: left;">
          <div style="float: left;margin-top: 10px;margin-right: 30px;margin-left: 30px;">
            <img style="width: 3.5rem;height:3.5rem;border-radius: 1rem;"
              src="https://img.tukuppt.com/png_preview/00/19/40/lhdYj32ZkM.jpg!/fw/780">
          </div>
          <div style="float: left;">
            <p style="color: #000;margin-top: 10px;font-size: 1.2rem;">总购买量</p>
            <p style="margin: 5px;color: #000;font-size: 1.5rem;">{{ purchasesTotal==null?0:purchasesTotal }}</p>
          </div>
        </div>
        <div style="width: 25%;float: left;">
          <div style="float: left;margin-top: 10px;margin-right: 30px;margin-left: 30px;">
            <img style="width: 3.5rem;height:3.5rem;border-radius: 1rem;"
              src="https://img.tukuppt.com/png_preview/00/24/47/5qRLo7NYny.jpg!/fw/780">
          </div>
          <div style="float: left;">
            <p style="color: #000;margin-top: 10px;font-size: 1.2rem;">入账总金额</p>
            <p style="margin: 5px;color: #000;font-size: 1.5rem;">{{ recordedTotal==null?0:recordedTotal }}</p>
          </div>
        </div>
        <div style="width: 25%;float: left;">
          <div style="float: left;margin-top: 10px;margin-right: 30px;margin-left: 30px;">
            <img style="width: 3.5rem;height:3.5rem;border-radius: 1rem;"
              src="https://bpic.588ku.com/element_origin_min_pic/19/06/15/c67d7eff24c31c6aff4000acc293dddd.jpg">
          </div>
          <div style="float: left;">
            <p style="color: #000;margin-top: 10px;font-size: 1.2rem;">钱包</p>
            <p v-if="pwdshow" @click="pwdshow=!pwdshow" style="margin: 5px;color: #000;font-size: 1.5rem;">{{ purse==null?0:purse }}</p>
            <p v-if="!pwdshow" @click="pwdshow=!pwdshow" style="margin: 5px;color: #000;font-size: 1.7rem;">*****</p>
          </div>
          <div style="float: right;margin-top: 2rem;margin-right: 1rem;">
            <el-button type="primary" @click="withdraw">提现</el-button>
          </div>
        </div>
      </div>

    </div>
    <div style="float: left;width: 49%;">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>修改密码</span>
        </div>

        <el-form ref="rulForm" label-width="200px" status-icon :model="ruleForm" class="demo-ruleForm" :rules="rules">
          <el-form-item>
            <span><i class="el-icon-lock">注意:</i>请保护好你的隐私</span>
          </el-form-item>
          <el-form-item label="原始密码:" prop="oldPass">
            <el-input type="password" v-model="ruleForm.oldPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码:" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码:" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item class="btn">
            <el-button type="warning" @click="submitForm()">保存修改</el-button>
            <el-button type="info" @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div style="float: right;width: 49%;" v-loading="listLoading">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>入账记录</span>
        </div>

        <el-form ref="rulForm" label-width="200px" status-icon :model="ruleForm" class="demo-ruleForm" :rules="rules">
          <div style="height: 19.55rem;overflow:auto">
            <el-table :data="recordedVos" v-loading="listLoading" :row-style="{ height: '45px' }" :cell-style="{ padding: '0px' }"
              element-loading-text="Loading" border fit highlight-current-row>
              <el-table-column align="center" label="订单号" prop="orderSn" width="120" :show-overflow-tooltip="true" />
              <el-table-column align="center" label="商品名称" prop="goodsName" width="120 " :show-overflow-tooltip="true" />
              <el-table-column label="买家" align="center" prop="memberUsername" width="120" :show-overflow-tooltip="true" />
              <el-table-column label="入账金额" align="center" prop="payAmount" width="116" :show-overflow-tooltip="true" />
              <el-table-column align="center" label="入账时间" prop="finallyTime" width="120" :show-overflow-tooltip="true" />
            </el-table>
          </div>

        </el-form>
      </el-card>
    </div>

  </div>
</template>

<script>
export default {
  name: "MyPass",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.rulForm.validateField('checkPass');
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
    var validateOldPass = async (rule, value, callback) => {
      //判断用户是否为空，如果为空返回提示信息，否则就进行服务器请求，并把当前用户名携带给服务器
      if (value == "") {
        callback(new Error("原始密码不能为空"))
      } else {
        //请求数据库，把当前用户的用户密码与输入的密码做比对
        if (value) {
          const { data: res } = await this.$http.post('/api/cloud-member/member/getPassword', {
            uid: sessionStorage.getItem("user_id"),
            password: this.ruleForm.oldPass
          })

          //如果密码是否错误返回提示信息给用户，否则不做任何处理
          if (res.code == 'A00100') {
            return callback(new Error("原始密码错误"))
          } else {
            callback();
          }
        }
      }

    };
    var userInfo = JSON.parse(sessionStorage.getItem("user"));
    return {
      pwdshow: false,
      memberId: JSON.parse(sessionStorage.getItem("user")).uid,
      purchasesTotal: 0,
      purse: 0,
      recordedTotal: 0,
      recordedVos: [],
      salesTotal: 0,
      listLoading: true,
      ruleForm: {
        uid: userInfo.uid,
        oldPass: "",
        pass: '',
        checkPass: '',
      },
      rules: {
        oldPass: [
          { required: true, validator: validateOldPass, trigger: "blur" },
          { min: 6, max: 11, message: "长度为6-11个字符", trigger: "blur" },
        ],
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' },
          { min: 6, max: 11, message: "长度为6-11个字符", trigger: "blur" },
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' },
          { min: 6, max: 11, message: "长度为6-11个字符", trigger: "blur" },
        ]
      }

    }
  },
  created() {
    this.secure()
  },
  activated(){
    this.secure()
  },
  methods: {
    secure() {
      this.memberId = JSON.parse(sessionStorage.getItem("user_id")),
        this.$http.get("/api/cloud-order/order/secure/" + this.memberId
        ).then(res => {
          this.purchasesTotal=res.data.data.purchasesTotal
            this.purse=res.data.data.purse
            this.recordedTotal=res.data.data.recordedTotal
            this.recordedVos=res.data.data.recordedVos
            this.salesTotal=res.data.data.salesTotal
            console.log(res.data.data)
          this.listLoading = false
        });
    },
    withdraw() {
      this.$notify.warning({
        title: "失败",
        message: "维护中"
      });
    },
    submitForm() {
      //对整个表单进行预验证，如果验证成功的话发起网络请求，携带注册用户名和密码给服务器进行处理，并给用户提示信息，后跳转至登陆页面进行登陆
      this.$refs.rulForm.validate(async (valid) => {
        if (!valid) {
          return
        }
        if (valid) {
          const { data: res } = await this.$http.post("/api/cloud-member/MemberLogin/updatePassword", {
            password: this.ruleForm.pass,
            uid: sessionStorage.getItem("user_id")
          })
          console.log(res)
          if (res.code == "A00100") {
            this.$message.error(res.msg)
          } else {
            this.$message.success("修改成功")
            this.ruleForm.oldPass = ''
            this.ruleForm.checkPass = ''
            this.ruleForm.pass = ''
          }
        }
      })
    },
    resetForm() {
      this.$refs.rulForm.resetFields();
    }
  }
}
</script>

<style scoped>
.box-card .el-input {
  width: 55%;
}
</style>