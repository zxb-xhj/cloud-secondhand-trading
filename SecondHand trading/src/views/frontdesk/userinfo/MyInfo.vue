<template>
  <div class="infinite-list" style="overflow:auto;height: 87vh;">
    <el-card style="width: 80vw;">
      <div slot="header" class="clearfix">
        <span style="font-size: 2.5vh;">个人资料</span>
      </div>

      <div
        style="background-color: rgb(243, 88, 121);height: 27vh;z-index:-1;margin-top: 1vh;margin-left: 1vw;margin-right: 1vw;width: 75vw;">
        <div style="float: left;width: 25vw;height: 8vh;margin: 6vh;">
          <div style="float: left;height: 100%;width: 10vw;margin-top: 3vh;">
            <img style="border-radius: 100%;height: 10vh;width: 4.8vw;margin-left: 6vw;"
              src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp"
              ref="显示错误">
          </div>
          <div style="float: left;margin-left: 2.5vw;margin-top: 4vh;">
            <p style="color: #ffffff;font-size: 3vh;margin-left: 0.3vw;">{{ name }}</p>
            <div style="background-color:#000000;background-color:rgba(0,0,0,0.1);border-radius: 10px;height: 2.6vh;">
              <div style="color: rgb(241, 241, 241);margin: 1vh;text-align: center;">
                <div style="height: 0.3vh;"></div>
                <div style="font-size: 1.5vh;">普通会员</div>
              </div>
            </div>
            <div></div>
          </div>
        </div>
        <div v-loading="listLoading"
          style="float: left;background-color: white;border-radius: 1.2rem;width: 35vw;height: 17vh;margin: 2vh;margin-top: 5.5vh;margin-left: 4vw;">
          <div style="margin-top: 5vh;">
            <router-link :to="{ path: '/order/' }">
              <div style="width: 8vw;float: left;margin-left: 3vw;">
                <div style="float: left;">
                  <p style="color: #000;font-size: 2.4vh;">订单数</p>
                  <p style="margin: 1.2vh;color: #000;font-size: 2.2vh;">{{ orderCount }}</p>
                </div>
                <div style="float: right;margin-top: 1vh;margin-right: 1vw;">
                  <img style="width: 3vw;height: 6vh;border-radius: 1.2rem;"
                    src="https://bpic.588ku.com/element_origin_min_pic/19/04/22/a1b22e857e44bef2b2601a86720263d2.jpg">
                </div>
              </div>
            </router-link>

            <router-link :to="{ path: '/releaseGoods/' }">
              <div style="width: 8vw;float: left;margin-left: 3vw;">
                <div style="float: left;">
                  <p style="color: #000;font-size: 2.4vh;">发布数</p>
                  <p style="margin: 1.2vh;color: #000;font-size: 2.2vh;">{{ releaseCount }}</p>
                </div>
                <div style="float: right;margin-top: 1vh;margin-right: 1vw;">
                  <img style="width: 3vw;height: 6vh;border-radius: 1.2rem;"
                    src="https://img.88icon.com/download/jpg/20210209/847926e0082b18df122dece9a75186a9_512_512.jpg!bg">
                </div>
              </div>
            </router-link>

            <router-link :to="{ path: '/updatePass/' }">
              <div style="width: 8vw;float: left;margin-left: 3vw;">
                <div style="float: left;">
                  <p style="color: #000;font-size: 2.4vh;">钱包</p>
                  <p style="margin: 1.2vh;color: #000;font-size: 2.4vh;">****</p>
                </div>
                <div style="float: right;margin-top: 1vh;margin-right: 1vw;">
                  <img style="width: 3vw;height: 6vh;border-radius: 1.2rem;"
                    src="https://bpic.588ku.com/element_origin_min_pic/19/06/15/c67d7eff24c31c6aff4000acc293dddd.jpg">
                </div>
              </div>
            </router-link>
          </div>

        </div>
      </div>
      <div v-loading="goodsFormShowxinxi" style="height: 6rem;width: 90vw;font-size: 2.3vh;">
        <div style="height: 2.3rem;width: 100vw;"></div>
        <div style="margin-left: 3vw;clear: left;width: 40vw;">
          <p style="float: left;">昵称：</p>
          <P style="float: left;width: 30vw;">{{ nickname }}</P>
        </div>
        <div style="float: left;width: 40vw;">
          <p style="float: left;">手机号码：</p>
          <P style="float: left;width: 30vw;">{{ mobile.replace(/^(\d{3})\d{4}(\d+)/, '$1****$2') }}</P>
        </div>
        <div style="height: 2.5rem;width: 100vw;"></div>
        <div style="clear: left;margin-left: 3vw;width: 40vw;">
          <p style="float: left;">邮箱：</p>
          <P style="float: left;width: 30vw;">{{ email }}</P>
        </div>
        <div style="float: left;width: 40vw;">
          <p style="float: left;">学校：</p>
          <P style="float: left;width: 30vw;">{{ schoolName }}</P>
        </div>
      </div>
      <!-- <div style="height: 1.5rem;"></div> -->
    </el-card>
    <div style="font-size: 2.3vh;">
      <div style="margin-top: 7px;">
        <div style="float: left;margin-left: 2%;" @click="getMember">
          <el-button plain>修改信息</el-button>
        </div>
        <div style="float: left;margin-left: 1%;">
          <el-button plain @click="Refresh">刷新</el-button>
        </div>
        <div style="float: left;margin-left: 1%;">
          <el-button plain @click="addOrderAddr">添加收货地址</el-button>
        </div>
      </div>
      <div style="clear: left;"></div>
      <template>
        <el-table v-loading="listLoading" :row-style="{ height: '5vh' }" :cell-style="{ padding: '0px' }"
          :data="orderAddrsList" element-loading-text="Loading" border fit highlight-current-row>
          <!-- <el-table-column type="selection" width="55" /> -->
          <el-table-column align="center" label="名字" prop="consignee" :show-overflow-tooltip="true" width="150"  />
          <el-table-column label="手机号码" align="center" prop="mobile" :show-overflow-tooltip="true" width="150"  />
          <el-table-column align="center" label="省" prop="province" :show-overflow-tooltip="true" width="100"  />
          <el-table-column align="center" label="市" prop="city" :show-overflow-tooltip="true"  width="100" />
          <el-table-column label="区\县" align="center" prop="area" />
          <el-table-column label="详细地址" align="center" prop="addr" :show-overflow-tooltip="true" />
          <el-table-column label="默认" align="center" width="100" >
            <template v-slot="scope">
              <el-switch v-model="scope.row.isDefault" :active-value="1" :inactive-value="0" active-color="#13ce66"
                inactive-color="#ff4949" @change="updateIsDefault(scope.row)" />
            </template>
          </el-table-column>
          <!-- <el-table-column label="修改时间" align="center" prop="payAmount" width="164" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="164" :show-overflow-tooltip="true" /> -->
          <el-table-column align="center" prop="created_at" label="操作" style="font-size: 1.8vh;" width="180" >
            <template v-slot="scope">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                @click="updateAddr(scope.row.addrId)">修改</el-button>
              <el-button type="danger" icon="el-icon-delete" size="mini"
                @click="deleteAddr(scope.row.addrId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum" :page-sizes="[5, 10, 15]" :page-size="queryInfo.pagesize"
          layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </template>
    </div>
    <el-dialog :title="goodsFormTitle" :visible.sync="goodsFormShow">
      <el-form :rules="rules" :model="goodsForm" label-width="80px">
        <el-form-item prop="consignee" label="姓名" >
          <el-input v-model="goodsForm.consignee" />
        </el-form-item>
        <el-form-item prop="mobile" label="手机号码">
          <el-input v-model="goodsForm.mobile" />
        </el-form-item>
        <el-form-item prop="aid" label="地址">
          <el-cascader :props="props" v-model="goodsForm.addrsPath" :options="addrsList" filterable></el-cascader>
        </el-form-item>
        <el-form-item prop="addr" label="详细地址">
          <el-input v-model="goodsForm.addr" />
        </el-form-item>
        <el-form-item prop="isDefault" label="默认">
          <el-switch v-model="goodsForm.isDefault" :active-value="1" :inactive-value="0" active-color="#13ce66"
            inactive-color="#ff4949" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">关 闭</el-button>
        <el-button type="primary" @click="handleSave(null)">保 存</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="goodsFormMemberTitle" :visible.sync="goodsFormShowMember">
      <el-form :rules="rules" :model="goodsForm" label-width="80px">
        <el-form-item prop="username" label="用户名">
          <el-input v-model="goodsForm.username" readonly unselectable="on" style="" />
        </el-form-item>
        <el-form-item prop="nickname" label="昵称">
          <el-input v-model="goodsForm.nickname" />
        </el-form-item>
        <el-form-item prop="mobile" label="手机号码">
          <el-input v-model="goodsForm.mobile" />
        </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="goodsForm.email" />
        </el-form-item>
        <el-form-item prop="schoolName" label="学校">
          <div style="width: 15vw;float: left;">
            <el-input disabled v-model="goodsForm.schoolName" />
          </div>
          <div style="width: 20vw;float: left;">
            <el-cascader size="medium" :props="sprops" v-model="schools" :options="schoolList" filterable clearable></el-cascader>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">关 闭</el-button>
        <el-button type="primary" @click="handleSaveMember()">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    var validateName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("昵称不能为空"))
      } else {
        if (value !== "") {
          const realnameReg = /^([a-zA-Z0-9\u4e00-\u9fa5\·]{1,5})$/;
          if (!realnameReg.test(value)) {
            callback(new Error('您的昵称格式错误,请输入1—5个英文或汉字!'));
          }
        }
        callback();
      }
    }
    var validateUserName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("真实姓名不能为空"))
      } else {
        if (value !== "") {
          const realnameReg = /^([a-zA-Z0-9\u4e00-\u9fa5\·]{1,5})$/;
          if (!realnameReg.test(value)) {
            callback(new Error('您的真实姓名格式错误,请输入1—5个英文或汉字!'));
          }
        }
        callback();
      }
    }
    var vaildateNumber = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('电话号码不能为空'));
      } else {
        if (value !== "") {
          var reg = /^1[3456789]\d{9}$/;
          if (!reg.test(value)) {
            callback(new Error('请输入有效的手机号码'));
          }
        }
        callback();
      }
    };

    var validateAddr = (rule, value, callback) => {
      if (value === "") {
        callback(new Error('地址不能为空'));
      } else {
        if (value !== "") {
          var regEmail = /^([a-zA-Z0-9\u4e00-\u9fa5\·]{1,30})$/;
          if (!regEmail.test(value)) {
            callback(new Error('您的地址格式错误,请输入30个以内的英文或汉字!'))
          }
        }
        callback();
      }
    };
    var validateEmail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error('邮箱不能为空'));
      } else {
        if (value !== "") {
          var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
          if (!regEmail.test(value)) {
            callback(new Error('请输入有效的邮箱'));
          }
        }
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
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
    var useInfo = JSON.parse(sessionStorage.getItem("user"))
    return {
      schoolList: [],
      schools: {},
      email: '无',
      mobile: '无',
      nickname: '无',
      schoolName: '无',
      goodsFormMemberTitle: '修改用户信息',
      status: 0,
      addrsCount: 0,
      orderCount: 0,
      releaseCount: 0,
      name: 'xhj',
      props: {
        label: 'areaName',
        value: 'areaName',
        children: 'areaList',
        expandTrigger: 'hover'
      },
      sprops: {
        label: 'name',
        value: 'name',
        children: 'schoolVOS',
        expandTrigger: 'hover'
      },
      total: 0,
      addrsList: [],
      goodsFormShow: false,
      goodsFormShowxinxi: false,
      goodsFormShowMember: false,
      goodsFormTitle: '添加地址',
      goodsForm: {},
      orderAddrsList: [],
      //查询分页信息
      queryInfo: {
        uid: JSON.parse(sessionStorage.getItem("user_id")),
        pagenum: 1,
        pagesize: 5,
      },
      listLoading: true,
      ruleForm: {
        uid: useInfo.uid,
        username: useInfo.username,
        sex: useInfo.sex,
        phone: useInfo.phone,
        address: useInfo.address,
        email: useInfo.email,
        name: useInfo.name,
        password: useInfo.password,
        modifyTime: useInfo.modifyTime,
        avatarUrl: useInfo.avatarUrl,
      },
      rules: {
        name: [
          { validator: validateName, trigger: "blur" }
        ],
        username: [
          { validator: validateUserName, trigger: "blur" }
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        number: [
          { validator: vaildateNumber, trigger: 'blur' }
        ],
        address: [
          { validator: validateAddr, trigger: 'blur' }
        ],
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择您的性别', trigger: 'change' }
        ]
      }
    };
  },
  watch: {
    schools: function (val) {
      this.goodsForm.schoolName = this.schools[this.schools.length-1]
      if(this.goodsForm.schoolName==null){
        this.goodsForm.schoolName=this.schoolName
      }
    }
  },
  created() {
    this.name = JSON.parse(sessionStorage.getItem("user")).username;
    this.getAddr()
    this.Refresh()
  },
  activated() {
    if (!this.$route.meta.isBack) {
      // 如果isBack是false，表明需要获取新数据，否则就不再请求，直接使用缓存的数据
      // this.Refresh()
    }
    // 恢复成默认的false，避免isBack一直是true，导致下次无法获取数据
    this.$route.meta.isBack = false
  },
  methods: {
    getSchool() {
      this.$http.post("/api/cloud-member/school/list"
      ).then(res => {
        this.schoolList = res.data.data
      });
    },
    handleSaveMember() {
      this.$http.post("/api/cloud-member/member/updata",
        {
          nickname: this.goodsForm.nickname,
          mobile: this.goodsForm.mobile,
          email: this.goodsForm.email,
          memberId: this.queryInfo.uid,
          schoolName: this.goodsForm.schoolName==null?this.schoolName:this.goodsForm.schoolName
        }
      ).then(res => {
        this.$notify.success({
          title: "修改成功",
        });
        this.goodsFormShowMember = false;
        this.getMenberOne()
        this.goodsForm = {};
      });
    },
    getMenberOne() {
      this.goodsFormShowxinxi = true
      this.$http.get("/api/cloud-member/member/memberId/" + this.queryInfo.uid
      ).then(res => {
        this.nickname = res.data.data.nickname == null ? '无' : res.data.data.nickname
        this.mobile = res.data.data.mobile == null ? '无' : res.data.data.mobile
        this.email = res.data.data.email == null ? '无' : res.data.data.email
        this.schoolName = res.data.data.schoolName == null ? '无' : res.data.data.schoolName
        this.goodsFormShowxinxi = false
      }).catch(err => {
        this.goodsFormShowxinxi = false;
      });
    },
    getMember() {
      this.getSchool()
      this.goodsFormShowMember = true;
      this.$http.get("/api/cloud-member/member/memberId/" + this.queryInfo.uid
      ).then(res => {
        this.goodsForm = res.data.data;
      });
    },
    updateIsDefault(goodsForm) {
      this.status = 1;
      this.handleSave(goodsForm)
    },
    Refresh() {
      this.getCount()
      this.getOrderAddr()
      this.getMenberOne()
    },
    getCount() {
      this.$http.get("/api/cloud-order/order/count/" + this.queryInfo.uid
      ).then(res => {
        this.releaseCount = res.data.data.releaseCount
        this.orderCount = res.data.data.orderCount
        this.addrsCount = res.data.data.addrsCount
      });
    },
    addOrderAddr() {
      this.goodsFormTitle = '添加地址'
      this.goodsForm = {};
      this.status = 0
      this.goodsFormShow = true;
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage//更新当前页码的数据
      this.getOrderAddr()
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize//更新每页的显示数据的条数
      this.getOrderAddr()//重新发起网络请求
    },
    getAddr() {
      this.$http.post("/api/cloud-member/area/list"
      ).then(res => {
        this.addrsList = res.data.data
      });
    },
    handleSave(goodsForm) {
      if (goodsForm != null) { this.goodsForm.isDefault = goodsForm.isDefault; this.goodsForm.addrId = goodsForm.addrId }
      this.goodsForm.memberId = JSON.parse(sessionStorage.getItem("user_id"));
      if (this.status == 0) {
        this.$http.post("/api/cloud-member/memberAddr/savememberAddr", this.goodsForm
        ).then(res => {
          this.getOrderAddr()
          this.goodsFormShow = false;
          this.$notify.success({
            title: "添加成功",
          });

          this.goodsForm = {}
        });
      } else if (this.status == 1) {
        this.$http.post("/api/cloud-member/memberAddr/updateMemberAddr", this.goodsForm
        ).then(res => {
          this.addr = res.data.data;
          this.goodsFormShow = false;
          this.$notify.success({
            title: "修改成功",
          });
          this.getOrderAddr()
          this.goodsForm = {}
        });
      }
    },
    closeDialog() { this.goodsFormShow = false; this.goodsFrom = {}, this.goodsFormShowMember = false },
    deleteAddr(addrId) {
      this.$http.delete("/api/cloud-member/memberAddr/" + addrId,
      ).then(res => {
        this.$message.success("地址删除成功")
        this.getOrderAddr()
      });
    },
    updateAddr(addrId) {
      this.goodsFormTitle = '修改地址'
      this.goodsFormShow = true;
      this.status = 1;
      this.$http.get("/api/cloud-member/memberAddr/" + addrId,
      ).then(res => {
        this.goodsForm = res.data.data
      });
    },
    getOrderAddr() {
      this.listLoading = true;
      this.$http.get("/api/cloud-member/memberAddr/list",
        {
          params: { memberId: this.queryInfo.uid, pageNo: this.queryInfo.pagenum, pageSize: this.queryInfo.pagesize }
        }).then(res => {
          this.orderAddrsList = res.data.data.list;
          this.total = res.data.data.total;
          this.listLoading = false
        })
    },
    submitForm(formName) {
      var that = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post("/api/modifyUserInfo", {
            username: this.ruleForm.username,
            sex: this.ruleForm.sex,
            name: this.ruleForm.name,
            email: this.ruleForm.email,
            address: this.ruleForm.address,
            phone: this.ruleForm.phone,
            uid: this.ruleForm.uid,
            password: this.ruleForm.password,
            modifyTime: this.ruleForm.modifyTime,
            avatarUrl: this.ruleForm.avatarUrl,
            role: this.ruleForm.role
          }).then(res => {

            if (res.data.state.code === 204) {
              this.$message.success(res.data.state.message);
              sessionStorage.setItem("user", JSON.stringify(this.ruleForm))
            }
          })
        } else {
          return false;
        }
      });
    }
  },

}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card .el-input {
  width: 45vw;
}
</style>