<template>
  <div style="background-color: #b9b9b9">
    <div v-for="item in list" :key="item.id">
      <div class="photo-list" style="float: left;background-color: rgb(249, 249, 249);border: 1px;border-radius: 25px;width: 19.5vw;margin-right: 2vw;height: 66vh;
            margin-top: 2%;">

        <h3
          style="margin-top: 2.8vh;margin-left: 2vw;width: 15vw;height: 5.6vh;text-overflow: ellipsis;font-size: 3vh;white-space: nowrap;overflow: hidden;border-radius: 10px;">
          {{ item.name }}</h3>
        <router-link :to="{ path: '/detail/' + item.id }">
          <img :src="item.defaultImg" alt="" style="height: 41vh;width: 16vw;margin-left: 1.8vw;border-radius: 5px;">
        </router-link>
        <!-- <div style="margin-left: 8%;margin-top: 2%;size: 15px;font-size: 20px;">￥{{ item.price }}
          <span style="border: solid crimson 1px;border-radius: 12px;color: brown;font-size: 2%;"><span
              style="margin: 2%;font-size: 10px;">优惠后￥{{ item.price }}</span></span>
        </div> -->
        <div style="margin-left: 9%;margin-top: 2%;width: 20vw;">
                  <div style="float: left;font-size: 2.7vh;">￥{{item.price}} </div>
                  <div style="border: solid crimson 1px;border-radius: 13px;color: brown;float: left;margin-top: 1vh;margin-left: 0.5vw;">
                    <div style="margin: 0.3vh;font-size: 1.35vh;">优惠后￥{{item.price}}</div>
                  </div>
                </div>
        <router-link :to="{ path: '/nowConfirmcart/' + [[item.id]] }">
          <button class="el-dropdown-link" v-if="xianshi" style="border-radius: 30px;background-color: white;width: 80%;margin-left: 10%;
                border: solid 1px;height: 6vh;margin-top: 2vh;">
            <div style="color: #575757;font-size: 3vh;font-weight: bold;">立即购买</div>
          </button>
        </router-link>
        <router-link :to="{ path: '/login/' }">
          <button class="el-dropdown-link" v-if="!xianshi" style="border-radius: 30px;background-color: white;width: 80%;margin-left: 10%;
                border: solid 1px;height: 6vh;margin-top: 2vh;">
            <div style="color: #575757;font-size: 3vh;font-weight: bold;">立即登录</div>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyList",
  props: ["list"],
  data: {
    return() {
      xianshi: sessionStorage.getItem('token')
    }
  },
  created() {
    this.xianshi = sessionStorage.getItem('token')
  },
  activated() {
    this.xianshi = sessionStorage.getItem('token')
  },
  // methods:{
  //   // 跳转到商品详情的页面，根据id
  //   showGoods(id){
  //     // 跳转到详情页
  //     this.$router.push({
  //       path:"/detail/"+id
  //     })
  //   },
  // }
}
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}

.myList ul li {
  width: 224px;
  height: 280px;
  float: left;
  z-index: 1;
  background: white;
  margin: 10px;
  transition: all .3s linear;
  position: relative;
}

.myList ul li a {
  text-decoration: none;
}

.myList ul li:hover {
  z-index: 2;
  box-shadow: 0 15px 30px rgba(0, 0, 0, .1);
  transform: translate3d(0, -2px, 0);
}

.myList ul li img {
  display: block;
  width: 160px;
  height: 160px;
  margin: 0 auto;
}

.myList ul li h2 {
  margin: 25px 10px 0;
  font-size: 14px;
  font-weight: 400;
  color: #333;
  line-height: 19px;
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.myList ul li h3 {
  margin: 5px 10px;
  height: 18px;
  font-size: 12px;
  font-weight: 400;
  color: #b0b0b0;
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.myList ul .price span {
  display: block;
  margin: 10px auto;
  text-align: center;
  color: #FF6700;
}

.product-box .box-head .photo-list {
  float: left;
  height: 615px;
  width: 234px;
}</style>