<template>
  <div class="app">
    <!--      顶部导航栏-->
    <div class="nav-topbar">
      <div class="container">
        <div></div>
        <div class="topbar-user">
          <el-dropdown trigger="click" v-if="username" style="margin-right: 20px" @command="handleCommand">
            <span class="el-dropdown-link">
              欢迎！{{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="b">个人中心</el-dropdown-item>
              <el-dropdown-item command="a">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <a href="javascript:;" v-if="!username" @click="login()">登录</a>
          <a href="javascript:;" @click="reg()">注册</a>
          <a href="/order" v-if="username">我的订单</a>
          <a href="/homepage" v-if="username == 'admin'">返回后台</a>
        </div>
      </div>
    </div>
    <!--  顶部导航栏end-->
    <div v-loading="listLoadingz" style="background-color: rgb(238, 238, 238);height: 100%;">

      <div style="height: 60px;background-color: white;">
        <div style="float: left;margin-left: 40px;margin-top: 5px;">
          <a href="javascript:history.back (-1)" class="el-dropdown-link">
            <i class="el-icon-arrow-left"></i>返回
          </a>
        </div>

        <h2 style="margin-top: 30px;margin-left: 170px;color: #656565;">确认订单</h2>
      </div>
      <div style="height: 20px;"></div>
      <div v-loading="listLoading">
        <!--第一层-->
        <div style="background-color: white;width: 88%;margin-left: 6%;">
          <div style="height: 30px;"></div>
          <div style="margin-left: 4%;width: 100%;">
            <div v-if="deliveryMethod == 0" style="width: 40vw;">
              <span style="font-size: 20px;">收货方式</span>
              <div style="height: 20px;"></div>
              <el-button round @click="goods.deliveryMethod = 1">自提</el-button>
              <el-button round @click="goods.deliveryMethod = 2">快递</el-button>
              <div style="height: 20px;"></div>
            </div>
            <div v-if="goods.deliveryMethod == 1" style="width: 40vw;">
              <span style="font-size: 20px;">提货地址</span>
              <div style="height: 20px;"></div>
              <el-form ref="form" label-width="100px">
                <el-form-item label="学校：">
                  <el-tooltip content="卖家设置了地址，无法修改" placement="top" effect="light">
                    <div disabled style="width: 33.5vw;text-align: center;border: 2px solid #d3ebf7;border-radius: 25px;">
                      <p style="margin: 0.1vw;font-size: 1.2vw;">{{ schoolName }}</p>
                    </div>
                  </el-tooltip>
                </el-form-item>
                <div style="height: 10px;"></div>
                <el-form-item label="详细地址：">
                  <el-input disabled v-model="schoolAddr">{{ schoolAddr }}</el-input>
                </el-form-item>
              </el-form>
              <div style="height: 20px;"></div>
            </div>
            <div v-if="goods.deliveryMethod == 2">
              <span style="font-size: 20px;">收货地址</span>
              <div style="float: right;margin-right: 8%;">
                <el-button plain @click="addOrderAddr">添加收货地址</el-button>
              </div>
              <div style="height: 20px;"></div>
              <div v-if="!addrState" style="height: 140px;">
                <div style="font-size: 20px;margin: 40px;color: #444444;">暂无收货地址</div>
              </div>
              <div v-if="addrState">
                <div class="addrdiv" style="border: solid #ff0000 1px;width: 60%;height: 80px;margin-top: 20px;"
                  @mouseleave="bdisplaybianji()" @mouseenter="bdisplaybianji()">
                  <div style="height: 10px;"></div>
                  <div style="margin-left: 2%;float: left;">
                    <span>{{ addr.consignee }}</span>
                    <span style="margin-left: 20px;">{{ addr.mobile.replace(/^(\d{3})\d{4}(\d+)/, '$1****$2') }}</span>
                    <el-tag id="acquiesces" v-if="addr.isDefault == 1" type="danger" size="mini"
                      style="margin-left: 50px; ">默认地址</el-tag>
                    <div style="height: 10px;"></div>
                    <div>{{ addr.province }} {{ addr.city }} {{ addr.area }} {{ addr.addr }} </div>
                  </div>
                  <div v-if="displaybianji">
                    <div @click="deleteAddr(addr.addrId, 1000)" id="adddiv" class="el-dropdown-link"
                      style="float: right;margin-right: 40px;font-size: 10px;">删除</div>
                    <div @click="updateAddr(addr.addrId, 1000)" id="adddiv" class="el-dropdown-link"
                      style="float: right;margin-right: 10px;font-size: 10px;">编辑</div>
                    <div @click="updateIsDefault(addr.addrId, 1000, 1)" id="adddiv" class="el-dropdown-link"
                      v-if="addr.isDefault == 0" style="float: right;margin-right: 10px;font-size: 10px;">设为默认</div>
                  </div>
                </div>
                <div v-if="display">
                  <div @click="transpositionaddr(index)" @mouseleave="bdisplaybianjiy1(index)"
                    @mouseenter="bdisplaybianjix1(index)" class="addrdiv"
                    style="border: solid #d5d5d5 1px;width: 60%;height: 80px;margin-top: 20px;"
                    v-for="(addr, index) in addrs" :key="index">
                    <div style="height: 10px;"></div>
                    <div style="margin-left: 2%;float: left;">
                      <span>{{ addr.consignee }}</span>
                      <span style="margin-left: 20px;">{{ addr.mobile.replace(/^(\d{3})\d{4}(\d+)/, '$1****$2') }}</span>
                      <el-tag :id="'acquiesce' + index" v-if="addr.isDefault == 1" type="danger" size="mini"
                        style="margin-left: 50px;">默认地址</el-tag>
                      <div style="height: 10px;"></div>
                      <div>{{ addr.province }} {{ addr.city }} {{ addr.area }} {{ addr.addr }} </div>
                    </div>
                    <div :id="'id' + index" class="addrdiv" :style="[style1]">
                      <div @click="deleteAddr(addr.addrId, index)" id="adddiv" class="el-dropdown-link"
                        style="float: right;margin-right: 40px;font-size: 10px;">删除</div>
                      <div @click="updateAddr(addr.addrId, index)" id="adddiv" class="el-dropdown-link"
                        style="float: right;margin-right: 10px;font-size: 10px;">编辑</div>
                      <div @click="updateIsDefault(addr.addrId, index, 1)" id="adddiv" class="el-dropdown-link"
                        v-if="addr.isDefault == 0" style="float: right;margin-right: 10px;font-size: 10px;">设为默认</div>
                    </div>
                  </div>
                </div>

                <div style="height: 30px;"></div>
                <span class="el-dropdown-link" @click="bdisplayNone">
                  查看全部地址<i v-if="!display" class="el-icon-caret-bottom el-icon--right"></i>
                  <i v-if="display" class="el-icon-caret-top el-icon--right"></i>
                </span>
                <div style="height: 10px;"></div>
              </div>

            </div>
          </div>
        </div>
        <div style="height: 40px;"></div>
        <!--第二层-->
        <div style="height: 480px;background-color: white;width: 88%;text-align: center;margin:auto;">
          <div style="height: 30px;"></div>
          <div style="margin-left: 4%;">
            <!--第2.1层-->
            <div style="border: solid rgb(238, 238, 238) 1px;width: 70%;height: 420px;float: left;">
              <div style="height: 38%;border: solid rgb(238, 238, 238) 1px;width: 100%;">
                <img :src="goods.defaultImg" style="width: 120px;height: 120px;float: left;margin: 15px;">
                <div style="margin-top: 20px;">
                  <div style="float: left;width: 50%;">{{ goods.name }}</div>
                  <div style="float: left;width: 5%;"></div>
                  <div style="float: left;width: 10%;">×1</div>
                  <div style="float: left;width: 10%;">¥ {{ goods.price }}</div>
                </div>
              </div>
              <div>
                <div style="float: left;width: 17%;margin-top: 10%;font-size: 20px;margin-left: 6%;">发票信息:</div>
                <div style="float: left;margin-top: 10.5%;">
                  <div style="font-size: 13px;color: #bbbbbb;">注：如果商品由第三方卖家销售，发票内容由其卖家决定，发票由卖家开具并寄出</div>
                  <div style="height: 15px;"></div>
                  <div style="font-size: 16px;">电子普通发票 个人 </div>
                </div>
              </div>
            </div>
            <!--第2.2层-->
            <div
              style="border: solid rgb(238, 238, 238) 1px;width: 25.5%;height: 420px;float: left;background-color: #f0f0f0;">
              <!--第2.2.1层-->
              <div style="border: solid rgb(238, 238, 238) 1px;width: 100%;height: 200px;">
                <div style="height: 30px;"></div>
                <div style="font-size: 20px;">商品由<span style="color: #dc1515;">二手平台</span>进行展示</div>
                <div style="height: 20px;"></div>
                <div style="margin-left: 5%;">
                  <div style="float: left;">
                    <img style="width: 20px;height: 20px;margin: 15px;">
                  </div>
                  <div style="height: 10px;"></div>
                  <div style="float: left;" v-if="goods.deliveryMethod != 1">
                    <div style="float: left;">标准配送</div>
                    <div style="height: 8px;"></div>
                    <div style="clear: left;">今天23:29前付款，预计后天送达</div>
                  </div>
                  <div style="float: left;" v-if="goods.deliveryMethod == 1">
                    <div style="float: left;">自提</div>
                    <div style="height: 8px;"></div>
                    <div style="clear: left;">请联系卖家确定自提时间</div>
                  </div>
                  <div style="height: 30px;"></div>
                </div>
              </div>
              <!--第2.2.2层-->
              <div v-if="addrState">
                <div style="height: 30px;"></div>
                <div style="float: left;width: 60%;">
                  <div style="float: right;">
                    <div style="float: right;margin-top: 10px;">商品总金额:</div>
                    <div style="height: 10px;"></div>
                    <div style="float: right;margin-top: 10px;">运费:</div>
                    <div style="height: 10px;"></div>
                    <div style="float: right;margin-top: 10px;">优惠金额:</div>
                    <div style="height: 10px;"></div>
                    <div style="float: right;margin-top: 10px;">积分:</div>
                    <div style="height: 10px;"></div>
                  </div>
                </div>
                <div style="float: right;width: 30%;margin-right: 15px;">
                  <div style="float: right;margin-top: 10px;">¥ {{ goods.price }}</div>
                  <div style="float: right;margin-top: 10px;clear: right;" v-if="goods.deliveryMethod == 2">¥ {{
                    goods.freight }}</div>
                  <div style="float: right;margin-top: 10px;clear: right;" v-if="goods.deliveryMethod == 1">¥ 0.00</div>
                  <div style="float: right;margin-top: 10px;clear: right;" v-if="goods.deliveryMethod == 0">¥ 0.00</div>
                  <div style="float: right;margin-top: 10px;clear: right;">¥ 0.00</div>
                  <div style="float: right;margin-top: 10px;clear: right;">¥ 0.00</div>
                  <div style="height: 10px;"></div>
                </div>
                <div style="margin-top: 20px;clear: right;margin-right: 15px;">
                  <div style="float: right;margin-top: 20px;">结算金额: <span style="font-size: 19px;color: #ff0000;">¥
                      {{ goods.deliveryMethod == 2 ? goods.price + goods.freight : goods.price }}</span></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div style="height: 20px;"></div>
      <!-- 第三层-->
      <div style="background-color: white;width: 88%;margin-left: 6%;height: 250px;">
        <div style="height: 30px;"></div>
        <div style="float: right;margin-right: 40px;" v-if="goods.deliveryMethod != 0">
          <div style="font-size: 21px;float: right;">应付金额: <span style="font-size: 25px;color: #ff0000;">
              ¥{{ goods.deliveryMethod == 1 ? goods.price : goods.price + goods.freight }}
            </span></div>
          <div style="height: 30px;"></div>
          <div
            style="width: 380px;font-size: 13px;margin-top: 30px;background-color: rgb(251, 238, 234);height: 70px;border: solid rgb(251, 232, 143) 1px;">
            <div style="height: 1px;"></div>
            <div style="width: 350px;margin: 10px;margin-left: 20px;">
              <div v-if="goods.deliveryMethod == 2"><span style="color: rgb(214, 214, 214);">配送至：</span><span>{{
                addr.province }} {{ addr.city }} {{
    addr.area }}
                  {{ addr.addr }} </span></div>
              <div v-if="goods.deliveryMethod == 1"><span style="color: rgb(214, 214, 214);">提货地址：</span><span>{{
                schoolName }}
                  {{ schoolAddr }} </span></div>
              <div style="height: 5px;"></div>
              <div v-if="goods.deliveryMethod == 2"> <span>{{ addr.consignee }}</span> <span>{{ addr.mobile }}</span>
              </div>
            </div>
          </div>
          <div style="height: 30px;"></div>
          <div @click="checkstand" style="float: right;"><el-button type="danger" style="size: 290px;">提交订单</el-button>
          </div>
        </div>
      </div>
      <div style="height: 30px;"></div>
      <!-- 第四层-->
      <div style="background-color: rgb(232, 232, 232);height: 100px;">
        <div style="height: 40px;"></div>
        <span style="margin-left: 80px;">二手交易平台经营许可证： </span>
        <span style="margin-left: 80px;">合字2B-20230913</span>
        <span style="margin-left: 80px;">京ICP备20230913号</span>
        <span style="margin-left: 80px;">-7京公网安备0000000000000号</span>
        <span style="margin-left: 80px;">隐私声明和</span>
        <span style="margin-left: 80px;">Cookie法律声明</span>
      </div>
    </div>
    <input type="text" disabled v-model="token" />
    <el-dialog :title="goodsFormTitle" :visible.sync="goodsFormShow">
      <el-form :rules="rules" :model="goodsForm" label-width="80px">
        <el-form-item prop="consignee" label="姓名">
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
        <el-button type="primary" @click="handleSave()">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
  
<script>

export default {
  name: "nowhome",

  beforeUpdate() {
    this.activeIndex = this.$route.path;
  },
  data() {
    return {
      xian: '4楼604',
      acquiesce: true,
      indexs: 0,
      status: 0,
      props: {
        label: 'areaName',
        value: 'areaName',
        children: 'areaList',
        expandTrigger: 'hover'
      },
      addrsList: [],
      goodsForm: {},
      goodsFormShow: false,
      goodsFormTitle: '添加地址',
      addrState: false,
      listLoading: true,
      listLoadingz: false,
      style1: { display: 'none' },
      displaybianji: false,
      displaybianji1: false,
      display: false,
      id: this.$route.params.id,
      activeIndex: "",
      search: {
        query: ""
      },
      username: "",
      goods: {},
      deliveryMethod: 0,
      token: '',
      addrs: {},
      addr: {},
      addrss: {},
      memberId: JSON.parse(sessionStorage.getItem("user_id")),
      // 表单校验规则
      rules: {
        aid: [{ required: true, trigger: 'blur', message: '请先选择地址' }],
        consignee: [{ required: true, trigger: 'blur', message: '姓名不能为空' }],
        mobile: [{ required: true, message: '手机号必填', trigger: 'blur' }, { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }],
        detailedAddress: [{ required: true, trigger: 'blur', message: '详细地址不能为空' }],
      },
    };
  },
  activated() {
    if (sessionStorage.getItem("token")) {
      this.username = this.$store.getters.getUser;
    }
    if (this.$route.params.id != undefined) {
      this.id = this.$route.params.id;
    }
    if (!this.$route.meta.isBack) {
      // 如果isBack是false，表明需要获取新数据，否则就不再请求，直接使用缓存的数据
      this.getMembergoods();
      this.getMemberAddrs();
      this.getAddr();
    }
  },
  watch: {
    // id:function(val){
    //   // this.listLoading = true;
    //   this.getMembergoods();
    //   this.getMemberAddrs();
    //   this.getAddr();
    // }
  },
  created() {
    // this.getMemberAddrs();
    // this.getAddr();
  },
  methods: {
    deleteAddr(addrId, index) {
      window.event.cancelBubble = true;
      this.$http.delete("/api/cloud-member/memberAddr/" + addrId,
      ).then(res => {
        if (index == 1000) {
          this.addr = this.addrs[0]
          this.addrs.splice(0, 1)
        } else {
          this.addrs.splice(index, 1)
          this.$message.success("地址删除成功")
        }
      });
    },
    updateAddr(addrId, index) {
      this.goodsFormTitle = '修改地址'
      window.event.cancelBubble = true;
      this.goodsFormShow = true;
      this.status = 1;
      this.indexs = index
      this.$http.get("/api/cloud-member/memberAddr/" + addrId,
      ).then(res => {
        this.goodsForm = res.data.data
      });
    },
    updateIsDefault(addrId, index) {
      window.event.cancelBubble = true;
      this.goodsForm.isDefault = 1;
      this.goodsForm.addrId = addrId;
      this.goodsForm.memberId = this.memberId;
      this.$http.post("/api/cloud-member/memberAddr/updateMemberAddr", this.goodsForm
      ).then(res => {
        for (var i = 0; i < this.addrs.length; i++) {
          this.addrs[i].isDefault = 0;
        }
        if (index == 1000) {
          this.addr.isDefault = 1;
        } else {
          this.addr.isDefault = 0;
          this.addrs[index].isDefault = 1;
        }
      })
    },
    handleSave() {
      this.goodsForm.memberId = JSON.parse(sessionStorage.getItem("user_id"));
      if (this.status == 0) {
        this.$http.post("/api/cloud-member/memberAddr/savememberAddr", this.goodsForm
        ).then(res => {
          this.getMemberAddrs()
          this.goodsFormShow = false;
          this.$notify.success({
            title: "添加成功",
          });

          this.goodsForm = {}
        });
      } else if (this.status == 1) {
        this.$http.post("/api/cloud-member/memberAddr/updateMemberAddr", this.goodsForm
        ).then(res => {
          if (this.indexs == 1000) {
            this.addr = res.data.data;
          } else {
            this.addrs.splice(this.indexs, 1, res.data.data);
          }
          this.goodsFormShow = false;
          this.$notify.success({
            title: "修改成功",
          });
          this.goodsForm = {}
        });
      }
    },
    closeDialog() { this.goodsFormShow = false; this.goodsFrom = {} },
    addOrderAddr() {
      this.goodsFormTitle = '添加地址'
      this.goodsForm = {}
      this.goodsFormShow = true;
      this.status = 0;
    },
    getAddr() {
      this.$http.post("/api/cloud-member/area/list"
      ).then(res => {
        this.addrsList = res.data.data
      });
    },
    checkstand() {
      console.log(this.addr)
      console.log(this.deliveryMethod)
      console.log(this.goods.deliveryMethod)
      this.listLoadingz = true;
      if (this.goods.deliveryMethod == 2) {
        var addr = this.addr.addr
        var city = this.addr.city
        var area = this.addr.area
        var province = this.addr.province
        var mobile = this.addr.mobile
        var consignee = this.addr.consignee
        var freight = this.goods.freight
      }
      if (this.goods.deliveryMethod == 1) {
        var area = this.schoolName
        var addr = this.schoolAddr
        var freight = 0
      }
      this.$http.post("/api/cloud-order/order/addOrder",
        {
          goodsId: this.goods.id,
          freight: freight,
          addr: addr,
          city: city,
          area: area,
          province: province,
          mobile: mobile,
          consignee: consignee,
          deliveryMethod: this.goods.deliveryMethod,
          memberId: JSON.parse(sessionStorage.getItem("user_id")),
          memberName: sessionStorage.getItem("username"),
          price: this.goods.price + this.goods.freight,
          token: this.token
        }
      ).then(res => {
        console.log(res.data)
        if (res.data.data.code == 1) {
          this.$router.push({ path: "/checkstand", query: { orderSn: res.data.data.id } });
          this.listLoadingz = false;
        } else if (res.data.data.code == 0) {
          this.$notify.error({
            title: "请勿重复提交",
          });
          window.history.go(-1);
          this.listLoadingz = false;
        } else {
          this.$notify.error({
            title: "提交失败",
          });
          window.history.go(-1);
          this.listLoadingz = false;
        }

      }).catch(() => {
        this.$notify.error({
          title: "提交失败",
        });
          window.history.go(-1);
          this.listLoadingz = false;
      });
      // } else {
      //   this.$notify.error({
      //     title: "请选择地址",
      //   });
      // }
    },
    transpositionaddr(index) {
      this.addrss = this.addrs[index];
      this.addrs.splice(index, 1);
      this.addrs.push(this.addr);
      this.addr = this.addrss;
    },
    bdisplaybianji() {
      this.displaybianji = !this.displaybianji;
    },
    bdisplaybianjix1(index) {
      var id = document.getElementById("id" + index);
      if (this.displaybianji1) {
        // 修改索引为index的样式
        id.style.display = "none";
      } else {
        // 修改索引为index的样式
        id.style.display = "";
      }
      this.displaybianji1 = true;
      this.displaybianji = false;
    },
    bdisplaybianjiy1(index) {
      var id = document.getElementById("id" + index);
      if (this.displaybianji1) {
        // 修改索引为index的样式
        id.style.display = "none";
      } else {
        // 修改索引为index的样式
        id.style.display = "";
      }
      this.displaybianji1 = false;
      this.displaybianji = false;
    },
    bdisplayNone() {
      this.display = !this.display;
    },
    getMemberAddrs() {
      console.log(this.deliveryMethod)
      if (this.deliveryMethod != 1) {
        var memberId = JSON.parse(sessionStorage.getItem("user_id"));
        this.$http.get("/api/cloud-member/memberAddr/list?pageNo=1&pageSize=10&memberId=" + memberId).then(res => {
          this.addrs = res.data.data.list;
          if (this.addrs.length > 0 && this.addrs != null) {
            this.addr = res.data.data.list[0]
            this.addrs.splice(0, 1);
            this.addrState = true
          }
        });
      }
      if (this.deliveryMethod != 2) {
        var id = this.$route.params.id;
        this.$http.get("/api/cloud-goods/delivery/getSchool/" + id).then(res => {
          this.schoolName = res.data.data.schoolName
          this.schoolAddr = res.data.data.addr
        });
      }
    },
    getMembergoods() {
      console.log("----")
      var id = this.$route.params.id;
      if (id != null) {
        this.$http.get("/api/cloud-goods/goods/infoFeign/" + id).then(res => {
          this.goods = res.data.data;
          if (this.goods == undefined) {
            window.history.go(-1);
            this.$notify.error({
              title: "没有该商品",
            });
            this.listLoadingz = false;
          } else {
            this.deliveryMethod = res.data.data.deliveryMethod
            this.token = res.data.data.token
          }
          this.listLoading = false;
        });
      }

    },
    login() {
      this.$router.push("/login")
    },
    handleCommand(command) {
      if (command == 'b') {
        this.$router.push('/userInfo')
      }
      if (command == 'a') {
        sessionStorage.clear()
        this.$message.success("账号退出成功")
        this.$router.push("/login")
        // 删除vuex中的数据,让当前的界面刷新
        // window.location.reload();
      }
    },
    reg() {
      this.$router.push('/register')

    },
    searchGoods() {
      if (this.search.query != "") {
        this.$router.push({ path: "/goods", query: { search: this.search.query } });
        this.search.query = "";
      }
    },

  }
}
</script>
  
<style>
* {
  margin: 0;
  padding: 0;
  list-style: none;
  border: 0;
}

.app .nav-topbar {
  height: 39px;
  line-height: 39px;
  background: #333333;
  color: #B0B0B0;
}

#adddiv:hover {
  color: #fb1b1b;
}

.addrdiv:hover {
  color: #acacac;
}

.app .nav-topbar .container {
  width: 80%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.app .nav-topbar .container a {
  /*display: inline-block;*/
  color: #B0B0B0;
  margin-right: 17px;
  font-weight: normal;
  text-decoration: none;
}

.nav-header .container {
  height: 112px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.el-dropdown-link {
  cursor: pointer;
  color: #B0B0B0;
}

.header-search {
  width: 319px;
  margin-left: 60px;
}</style>