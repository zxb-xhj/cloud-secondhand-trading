<template>
  <div class="index" id="index">
    <!--  主题区域侧边栏-->
    <div class="main">


          <div class="block" style="width: 80%;">
            <el-carousel trigger="click" height="28.2rem" arrow="never">
              <el-carousel-item v-for="(item, index) in imageArr" :key="index">
                <img :src="item.imageUrl" @click="showGoods(item.targetUrl)" style="width: 100%; height: 28.2rem;border-radius: 10px;" />
              </el-carousel-item>
            </el-carousel>
          </div>
    </div>

    <!--  主题区域侧边栏end-->
    <!--  商品展示区域-->
    <div>
      <div style="height: 2rem;"></div>
      <div>
        <div class="jpg">
          <!-- <img style="width: 90%;border-radius: 10px;" src="@/assets/img/active.jpg" alt=""> -->
          <!-- <img style="width: 90%;border-radius: 10px;" :src="imageUrl" alt=""> -->
          <div style="width: 90%;height: 19vh;background-color: #4797c8;border-radius: 10px;">
            <div style="float: left;">
              <img style="border-radius: 10px;height: 19vh;width: 23vw;margin-left: 6vw;" :src="imageUrl1" alt="">
            </div>
            <div style="float: left;background-color: white;width: 40vw;height: 15vh;margin-top: 2vh;border-radius: 10px;margin-left: 11vw;">
              <div>
                <img style="border-radius: 10px;margin-top: 3vh;margin-left: 4.3vw;width: 32vw;height: 10vh;" :src="imageUrl" alt="">
              </div>
            </div>
                <!-- <img style="border-radius: 10px;" :src="imageUrl2" alt=""> -->
          </div>
        </div>
        <!-- <div style="width: 10rem;float: left;">
          <p class="active-font"><img src="@/assets/img/shopActive.png" alt=""></p>
          <router-link to="/activityNotic" class="active-link">
            闲置大甩卖
          </router-link>
        </div> -->
        <div style="height: 3rem;"></div>
        <div class="" style="margin-left: 7vw;margin-right: 2vw;background-color: rgb(209, 209, 209);">
          <MyIndexList :list="product" v-if="product.length > 0"></MyIndexList>
          <div v-else class="none-product">抱歉没有找到相关的商品，请看看其他的商品</div>
        </div>
        <!-- 主要内容区 -->
      </div>
    </div>
    
    <div style="height: 1rem;clear: left;"></div>
    <div class="footer" style="clear: left;margin-top: 3rem;">
      <p class="footer-con">
        <router-link to="/home">
          首页
        </router-link>|
        <router-link to="/goods">
          全部商品
        </router-link>|
        <router-link to="/userInfo">
          个人中心
        </router-link>
      </p>
    </div>
  </div>
</template>

<script>
import MyList from "@/views/frontdesk/MyList";
import xiaomiK40 from "@/assets/img/phone.png";
import television from "@/assets/img/television.png";
import cloth from "@/assets/img/cloth.jpg";
import shoe from "@/assets/img/shoe.jpg";
import kitchen from "@/assets/img/kitchen2.gif";
import MyIndexList from "@/views/frontdesk/MyIndexList";
export default {
  name: "home",
  components: {
    MyList, MyIndexList
  },
  beforeUpdate() {
    this.activeIndex = this.$route.path;
  },
  data() {
    return {
      fanhui: false,
      commodityIdArr: [1, 2, 3, 4, 5],
      imageArr: [{ url: xiaomiK40 }, { url: kitchen }, { url: television }, { url: cloth }, { url: shoe }],
      activeIndex: "",
      cateList: [],//侧边栏商品列表
      phoneList: [],//手机商品列表
      category_id: 1,
      pageNum: 1,
      pageSize: 10,
      product: "", // 商品列表
      imageUrl: require('@/assets/img/ershou.png'),
      imageUrl1: require('@/assets/img/R-C.png'),
      imageUrl2: require('@/assets/img/dzxucenpic10.png')
    };
  },
  created() {
    this.getProductList();
    this.getBannerList();
    console.log(this.$route.path)
    // this.getTime1()
  },
  activated() {
    if (!this.$route.meta.isBack) {
      // 如果isBack是false，表明需要获取新数据，否则就不再请求，直接使用缓存的数据
      // this.getBannerList()
      // this.getProductList()
    }
    // 恢复成默认的false，避免isBack一直是true，导致下次无法获取数据
    this.$route.meta.isBack = false
    // this.getTime()
  },
  methods: {
    // getTime(){
    //   this.$http.get("/api/cloud-order/order/time/" + id ).then(res => {
    //         console.log(res.data)
    //     });
    // },
    getBannerList() {
      this.fanhui = false;
      this.$http.get("/api/cloud-goods/banner/bannerList").then(res => {
        this.imageArr = res.data.data
      });
    },
    // 跳转到商品详情的页面，根据id
    showGoods(path) {
      // 跳转到详情页
      this.$router.push({
        path: path
      })
    },
    getProductList() {

      // 获取商品列表
      this.$http.get("/api/cloud-goods/recommend/list?pageNo=1&pageSize=8").then(res => {
        this.product = res.data.data.list;
      });
    },
    //倒计时
    

  }
}
</script>

<style scoped>
.index .main {
  display: flex;
  justify-content: center;
}
.jpg {
  display: flex;
  justify-content: center;
}

/* .index .main .nav-menu {
  position: absolute;
  width: 18rem;
  height: 28.2rem;
  z-index: 5;
  padding: 23px 0;
  background: #55585a7a;
  box-sizing: border-box;
} */

/* .index .menu-wrap .menu-item {
  height: 43px;
  line-height: 40px;
} */

/* .menu-item a {
  position: relative;
  display: block;
  text-decoration: none;
  font-size: 16px;
  color: #fff;
  padding-left: 30px;
} */

/* .menu-item a:after {
  position: absolute;
  right: 30px;
  top: 17px;
  content: '';

} */

.block img {
  cursor: pointer;
}

.menu-item:hover {
  background: #FF6600;
}

.menu-item:hover .children {
  display: block;
}

.menu-item .children {
  display: none;
  background: #fff;
  position: absolute;
  top: 0;
  border: 1px solid #e5e5e5;
}

.menu-item .children ul {
  display: flex;
  justify-content: space-between;
}



.menu-item .children ul li a {
  color: #333;
  font-size: 2rem;
}


.footer {
  height: 13rem;
  background: #2f2f2f;
}

.footer p,
.footer a {
  padding: 80px 20px;
  margin-top: 20px;
  text-align: center;
  color: #b0b0b0;
}

.footer .footer-con a {
  text-decoration: none;
}

.footer .footer-con a:hover {
  color: #fff;
}
</style>