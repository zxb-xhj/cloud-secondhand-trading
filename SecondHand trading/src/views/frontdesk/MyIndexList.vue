<template>
<div>
    <div v-for="item in list" :key="item.id">
        <div class="photo-list" style="float: left;background-color: rgb(249, 249, 249);border-radius: 25px;border: 1px;width: 19.5vw;margin-right: 2vw;height: 65vh;
            margin-top: 4.5vh;">
              
                <div style="margin-top: 2.8vh;margin-left: 2vw;width: 15vw;height: 5.6vh;text-overflow: ellipsis;font-size: 3vh;
                white-space: nowrap;overflow: hidden;">{{item.goodsName}}</div>
                <router-link :to="{path:'/detail/'+[[item.goodsId]]}">
                <img :src="item.goodsImage" alt="" style="height: 41vh;width: 16vw;margin-left: 1.8vw;border-radius: 5px;">
                </router-link>
                <div style="margin-left: 9%;margin-top: 2%;width: 20vw;">
                  <div style="float: left;font-size: 2.7vh;">￥{{item.goodsPrice}} </div>
                  <div style="border: solid crimson 1px;border-radius: 13px;color: brown;float: left;margin-top: 1vh;margin-left: 0.5vw;">
                    <div style="margin: 0.3vh;font-size: 1.35vh;">优惠后￥{{item.goodsPrice}}</div>
                  </div>
                </div>
                  <!-- this.$router.push("/nowConfirmcart/"+this.id); -->
                  <router-link :to="{path:'/nowConfirmcart/'+[[item.goodsId]]}">
                  <button class="el-dropdown-link" v-if="xianshi" style="border-radius: 30px;background-color: white;width: 80%;margin-left: 10%;
                    border: solid 1px;height: 6vh;margin-top: 2vh;">
                      <div style="color: #575757;font-size: 3vh;font-weight: bold;">立即购买</div>
                  </button>
                  </router-link>
                  <router-link :to="{path:'/login/'}">
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
  name: "MyIndexList",
    props:["list"],
    data:{
    return(){
      xianshi:sessionStorage.getItem('token')
    }
  },
  created(){
    this.xianshi= sessionStorage.getItem('token')
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

  </style>