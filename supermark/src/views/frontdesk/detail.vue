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
            <div style="text-align: center;margin-bottom: 5%;" class="grid-content bg-purple title">{{ title }}</div>
          </el-col>
          <img :src="commodityImage" style="width: 21.5rem;height: 21.5rem;margin-left: 6rem" alt="">
          <div style="margin-top: 3.5rem;margin-left: 5.8rem;">
            <div style="font-size: 1.1rem;">卖家： {{ seller }}</div>
            <div style="margin-top: 1.5rem;font-size: 1.2rem;">联系方式：{{mobile==null?'无':mobile}}</div>
          </div>
        </div>
      </el-col>
      <!--      右侧布局-->
      <el-col :span="16">
        <div class="detail-box">
          <el-row>
            <el-col :span="24">
              <p>商品简介</p>
            </el-col>
            <div style="height: 22rem;">
              <el-col :span="24">
                <div style="height: 10.7rem;" class="grid-content bg-purple info">{{ info }}</div>
              </el-col>
              <el-col :span="24">
                <span v-for="img in images" :key="img.id">
                  <el-image style="width: 10rem;height: 10rem;margin-left: 1rem;" alt="无法显示" :src="img.url" :preview-src-list="image"
                    v-if="[[images != null]]" />
                </span>

              </el-col>
            </div>

            <el-col :span="24">
              <el-divider></el-divider>

            </el-col>
            <div style="width: 100%;">
              
              <div style="float: right;">库存:{{ save }}</div>
              <div style="float: right;margin-right: 5rem;">浏览量:{{ viewCount }}</div>
              <div style="clear: right;float: right;">

                <div style="float: right;margin-left: 1.5rem;;" class="grid-content bg-purple btn">
                  <el-button v-if="xianshi" :disabled="submissionFlag" type="danger"
                    @click="commentGoods()">立即购买</el-button>
                  <el-button v-if="!xianshi" :disabled="submissionFlag" type="danger" @click="qiangGou()">立即登录</el-button>
                </div>
                <div style="float: right;margin-top: 1rem;" class="grid-content bg-purple price">
                  <span>{{ this.price_total }}元</span>
                </div>
              </div>


            </div>

          </el-row>
        </div>
      </el-col>
    </el-row>

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
      viewCount:0
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
    getfanhui() {
      this.fanhui = true;
    },
    // 通过ajax获取商品的详情信息
    getGoodById(id) {
      this.listLoading = true;
      var that = this
      this.$http.get("/api/cloud-goods/goods/" + id).then(res => {
        if (res.data.data != null) {
          console.log(res.data);
          that.hot = res.data.data.cid,
            that.title = res.data.data.name,
            that.info = res.data.data.description,
            that.price_total = res.data.data.price,
            that.save = res.data.data.total,
            that.overTime = res.data.data.createTime,
            that.commodityImage = res.data.data.defaultImg
            that.seller = res.data.data.seller
            that.images = res.data.data.images
            that.id = res.data.data.id
            that.mobile = res.data.data.mobile
            that.viewCount = res.data.data.viewCount
            this.listLoading = false;
          if (res.data.data.total > 0) {
            this.submissionFlag = false;
          }
          this.images.forEach(image => {
            this.image.push(image.url)

          });
          console.log(this.image)
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
</style>