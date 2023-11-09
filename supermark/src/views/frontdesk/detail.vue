<template>
  <div id="detail" v-loading="listLoading">
    <a style="margin-left: 3rem;" v-if="fanhui" href="javascript:history.back (-1)" class="el-dropdown-link">
      <i class="el-icon-arrow-left"></i>返回
    </a>
    <!--    左右布局-->
    <el-row>
      <!--      左侧布局-->
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-col >
            <div style="text-align: center;margin-bottom: 5%;font-size: 3.3vh;" class="grid-content bg-purple title">{{ title }}</div>
          </el-col>
          <img :src="commodityImage" style="width: 23vw;height: 45vh;margin-left: 6vw" alt="">
          <div style="margin-top: 3.2vh;margin-left: 5vw;">
            <div id="info" class="info" style="height: 10vh;width: 25vw;text-align: center;border-radius: 10px;margin-left: 0.3vw;border: #ffd69d solid 1px;">
                <!-- <router-link :to="{ name: 'Message', params: { id: sellerId,seller:seller }}"> -->
                    <div style="width: 25vw;height: 8vh;border-radius: 0.5rem;margin-left: 2vw;" @click="Have()">
                      <div style="float: left">
                          <img style="border-radius: 50%;height: 7vh;width: 3.5vw;margin-left: 1vw;margin-right: 1vw;" src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp">
                      </div>
                      <div style="float: left;margin-top: 0.3vh;font-size: 2.5vh;width: 9vw;">
                        <div style="float: left;">
                            <div>{{ seller }}</div>
                        </div>
                        <div style="clear: left;font-size: 1.3vh;background-color:#000000;background-color:rgba(255, 115, 0, 0.51);border-radius: 10px;height: 3vh;width: 4vw;">
                            <div style="height: 0.4vh;"></div>
                            <div style="color: rgb(241, 241, 241);margin-top: 0.1vh;font-size: 1vh;">普通会员</div>
                        </div>
                      </div>
                      <div style="float: left;margin-top: 0.8vh;font-size: 2.5vh;margin-left: 1vh;margin-top: -0.1vh;">
                        <button class="el-dropdown-link" style="background-color: #f9a837;border-radius: 6px;">
                          <div style="width: 6vw;height: 4.5vh;margin-top: 1.8vh;color: #ffffff;font-size: 2vh;">聊一聊</div>
                        </button>
                      </div>
                    </div>
                <!-- </router-link> -->
            </div>
          </div>
        </div>
      </el-col>
      <!--      右侧布局-->
      <el-col :span="16">
        <div class="detail-box">
          <el-row>
            <el-col :span="24">
              <p style="font-size: 2.6vh;">商品简介</p>
            </el-col>
            <div style="height: 22vh;">
              <el-col :span="24">
                <div style="height: 21vh;font-size: 2.2vh;" class="grid-content bg-purple info">{{ info }}</div>
                <div style="height: 1vh;"></div>
              </el-col>
              <el-col :span="24">
                <span v-for="img in images" :key="img.id">
                  <el-image style="width: 10.5vw;height: 22vh;margin-left: 1vw;" alt="无法显示" :src="img.url" :preview-src-list="image"
                    v-if="[[images != null]]" />
                </span>

              </el-col>
            </div>

            <el-col :span="24">
              <el-divider></el-divider>

            </el-col>
            <div style="width: 100%;font-size: 2.2vh;">
              
              <div style="float: right;">库存:{{ save }}</div>
              <div style="float: right;margin-right: 5rem;">浏览量:{{ viewCount }}</div>
              <div style="clear: right;float: right;font-size: 3vh;">

                <div style="float: right;margin-left: 1.5rem;" class="grid-content bg-purple btn">
                  <el-button v-if="xianshi" :disabled="submissionFlag" type="danger" style="font-size: 2.6vh;"
                    @click="commentGoods()">立即购买</el-button>
                  <el-button v-if="!xianshi" :disabled="submissionFlag" type="danger" style="font-size: 2.6vh;" @click="qiangGou()">立即登录</el-button>
                </div>
                <div style="float: right;margin-top: 1rem;font-size: 3vh;" class="grid-content bg-purple price">
                  <span>{{ this.price_total }}元</span>
                </div>
              </div>


            </div>

          </el-row>
        </div>
      </el-col>
    </el-row>
    <div style="clear: left;width: 100%;margin-top: 5vh;">
      <div style="text-align: center;background-color: rgb(238, 238, 238);height: 6vh;
      margin-left: 10vw;margin-right: 10vw;font-size: 2.5vh;border-radius: 10px;">
      <div style="width: 100%;height: 1.35vh;"></div>
        <div>评论</div>
      </div>
      <div style="bottom: 10px;width: 100%;margin-top: 2vh;height: 5vh;background-color: white">
        <input @keyup.enter="addMessage" v-model="message" type="text" name="message" style="float: left;width: 55%;margin-left: 18.8vw;height: 4.7vh;
                    border-radius: 1.3vh;border: solid #787878 3px">

                <div style="float: left">
                    
                    <input class="el-dropdown-link" @click="addMessage" value="发送" type="button" style="height: 5vh;width: 5vw;border-radius: 0.5rem;margin-left: 0.7vw;
                font-size: 2.5vh;margin-top: 0.1vh;background-color: rgb(72, 113, 247);color: white;"/>
                </div>
            </div>
      <div v-if="messages" v-loading="listLoading">
        <div v-for="(message,index) in messages" :key="index" style="width: 100%;">
          <div style="width: 100%;height: 1vh;clear: left;"></div>
          <div style="margin-left: 15vw;width: 70%;clear: left;margin-top: 1.5vh;">
            <div style="float: left">
              <img style="border-radius: 50%;height: 7vh;width: 3.5vw;margin-left: 1vw;margin-right: 1vw;" 
              src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp">
            </div>
            <div style="float: left;">
              <div style="font-size: 1.7vh;color: #787878;margin-top: 0.5vh;">{{message.memberName}} · <span style="font-size: 1.5vh;">{{ message.createTime }}</span></div>
              <div style="font-size: 2vh;"><span v-if="message.parentName" style="font-size: 1.8vh;color: #d1d1d1;">回复:{{ message.parentName }}({{ message.parentContent }})</span>  {{message.content}}</div>
              <div @click="deleteMessage(message.id,index)" v-if="message.memberName==name" class="el-dropdown-link" style="font-size: 1.5vh;margin-top: 0.5vh;">删除</div>
              <div @click="huifu(message.id,message.memberName)" v-if="message.memberName!=name" class="el-dropdown-link" style="font-size: 1.5vh;margin-top: 0.5vh;">回复</div>
              <!-- <span v-if="true" class="el-dropdown-link" @click="bdisplayNone" style="font-size: 1.7vh;">
                查看全部回复<i v-if="!display" class="el-icon-caret-bottom el-icon--right"></i>
                <i v-if="display" class="el-icon-caret-top el-icon--right"></i>
              </span> -->
            </div>

          </div>
        </div>
        <div style="height: 4vh;width: 50vw;clear: left;"></div>
      </div>
      
      <div v-if="messages.length==0" style="font-size: 2vh;text-align: center;margin-top: 8vh;margin-bottom: 8vh;height: 10vh;">暂无评论</div>
    </div>
    <el-dialog :title="goodsFormTitle" :visible.sync="goodsFormShow">
      <el-form label-width="80px">
        <el-form-item prop="consignee" label="内容:">
          <el-input v-model="content" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addMessage()">回 复</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Countdown from "@/views/frontdesk/Countdown";
export default {
  name: "detail",
  data() {
    return {
      mobile: '',
      xianshi: sessionStorage.getItem('token'),
      submissionFlag: true,
      id: 0,
      listLoading: true,
      fanhui: true,
      gid: this.$route.params.gid,
      title: "商品标题",
      info: "商品描述",
      hot: "",
      did: "1",
      price: "99.99",
      price_total: "",
      phone: "",
      commodityImage: "",
      username: "",
      num: "1",
      save: 0,
      overTime: 0,
      flag: false,
      goodsId: "",
      showNotice: false,
      images: "",
      total: "",
      image: [],
      seller: '',
      viewCount:0,
      sellerId: 0,
      messages: [],
      message: '',
      name: sessionStorage.getItem('nickname')==null?sessionStorage.getItem('username'):sessionStorage.getItem('nickname'),
      parentId:'',
      display: false,
      goodsFormShow: false,
      goodsFormTitle: '',
      content: ''
    }
  },
  components: {
    Countdown
  },
  created() {
    this.fanhui = true;
  },
  // mounted() {
  //   //发送请求获取商品的详情信息
  //   this.getGoodById(this.gid);
  // },
  // 通过路由获取商品id
  activated() {
    this.xianshi = sessionStorage.getItem('token')
    if (this.$route.params.gid != undefined) {
      this.gid = this.$route.params.gid;
    }
    if (!this.$route.meta.isBack) {
      // 如果isBack是false，表明需要获取新数据，否则就不再请求，直接使用缓存的数据
      this.getGoodById(this.gid);
    }
    // 恢复成默认的false，避免isBack一直是true，导致下次无法获取数据
    this.$route.meta.isBack = false
  },
  watch: {
    // 监听商品id的变化，请求后端获取商品数据
    gid: function (val) {
      this.getGoodById(val)
    }
  },
  methods: {
    bdisplayNone(){},
    handleSave(){
      this.goodsFormShow = false;
    },
    closeDialog(){
      this.goodsFormShow = false;
      this.goodsFormTitle = ''
    },
    huifu(id,name){
      this.message = ''
      this.goodsFormShow = true;
      this.goodsFormTitle = "回复："+name
      this.parentId = id
    },
    deleteMessage(id,index){
      this.$http.delete("/api/cloud-message/commentReply/" + id).then(res => {
        console.log(res)
        this.messages.splice(index,1)
        this.$message.success("删除成功")
      })
    },
    getMessage(id){
      this.$http.get("/api/cloud-message/commentReply/" + id).then(res => {

        const messages = res.data.data
        messages.forEach(message=>{
          const now = new Date().getTime();
          const date = new Date(message.createTime).getTime()
          const inter = parseInt((now - date)/1000/60)
          if(inter == 0){
            message.createTime =  "刚刚";
          }
          //多少分钟前
          else if(inter < 60){
            message.createTime = inter.toString() + "分钟前";
          }
          //多少小时前
          else if(inter < 60*24){
            message.createTime = parseInt(inter/60).toString() + "小时前";
          }
          // 多少天前
          else {
            message.createTime = parseInt(inter/60/24).toString() + "天前";
          }
        })
        this.messages = messages;
      })
    },
    addMessage(){
        const message = (this.message != null && this.message != '')?
        this.message:(this.content != null && this.content != '')?this.content:null
      if(message != null && message != ''){
        var memberId = sessionStorage.getItem("user_id")
        const name = sessionStorage.getItem('nickname')==null?sessionStorage.getItem('username'):sessionStorage.getItem('nickname');
        this.$http.post("/api/cloud-message/commentReply/save",
        {
          memberId:memberId,content: message,goodsId: this.gid,parentId: this.parentId
        }
        ).then(res => {
          this.messages.push({id: res.data.data.id,memberName: name,content:res.data.data.content,
            createTime: "刚刚",parentId: this.parentId,parentName: res.data.data.parentName})
          this.message = ''
          this.content = ''
          this.parentId = ''
        })
      }
    },
    Have(){
      if(sessionStorage.getItem('user_id')!=this.sellerId){
        this.$router.push({ name: 'Message', params: { id: this.sellerId,seller:this.seller }})
      }else{
        // this.$message({
        //   message: `亲爱的${sessionStorage.getItem('nickname')==null?sessionStorage.getItem('username'):sessionStorage.getItem('nickname')}，不能跟自己聊天哦`,
        //   type: 'warning',
        //   title: "温馨提示"
        // });
        this.$notify.warning({
          title:"温馨提示",
          message: `亲爱的${sessionStorage.getItem('nickname')==null?sessionStorage.getItem('username'):sessionStorage.getItem('nickname')}，不能跟自己聊天哦`
        });
      }
    },
    getfanhui() {
      this.fanhui = true;
    },
    getViewCount(id){
      this.$http.get("/api/cloud-goods/goods/viewCount/" + id).then(res => {

        this.save = res.data.data.total,
        this.viewCount = res.data.data.viewCount
      })
    },
    // 通过ajax获取商品的详情信息
    getGoodById(id) {
      this.listLoading = true;
      var that = this
      this.getViewCount(id)
      this.getMessage(id)
      this.$http.get("/api/cloud-goods/goods/" + id).then(res => {
        if (res.data.data != null) {
          that.hot = res.data.data.cid,
            that.title = res.data.data.name,
            that.info = res.data.data.description,
            that.price_total = res.data.data.price,
            that.overTime = res.data.data.createTime,
            that.commodityImage = res.data.data.defaultImg
            that.seller = res.data.data.seller
            that.images = res.data.data.images
            that.id = res.data.data.id
            that.sellerId = res.data.data.sellerId
            that.mobile = res.data.data.mobile
            this.listLoading = false;
          if (res.data.data.total > 0) {
            this.submissionFlag = false;
          }
          this.images.forEach(image => {
            this.image.push(image.url)

          });
        } else {
          this.$router.push("/index")
        }

      })
    },
    
    startKill() {
      //倒计时结束后触发

      this.flag = true;

    },
    overKill() {
      this.flag = false;

    },
    showNo() {

      this.showNotice = true;

    },
    handleChange() {
      this.price_total = this.price_total * this.num;
    },
    commentGoods() {
      this.$router.push("/nowConfirmcart/" + this.id);

    },
    qiangGou() {
      if (!sessionStorage.getItem("token")) {
        this.$router.push("/login");
      }

    }
  }
}
</script>

<style scoped>
#detail .detail-box {
  margin-right: 100px;
}

#detail .title {
  margin: 0;
  padding: 0;
  font-size: 24px;
  font-weight: 400;
  color: #212121;
}

#detail .info {
  color: #b0b0b0;
  padding-top: 28px;
  line-height: 1.5;
}

#detail .price {
  color: #ff6700;
  font-size: 24px;
  padding-top: 20px;
}

#detail .btn {
  margin-top: 30px;
}

#detail .detail-box .show {
  padding-top: 40px;
}

#detail .detail-box .show a {
  text-decoration: none;
  font-weight: bold;
}
.el-dropdown-link:hover{
  color: #d1d1d1;
}
</style>