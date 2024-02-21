<template>
  <div>
    <div v-for="item in Object.values(list)[0]" :key="item.goodsId">
      <div class="photo-list" style="float: left;background-color: rgb(249, 249, 249);border-radius: 25px;border: 1px;width: 14.5vw;margin-right: 1.5vw;height: 40vh;
                margin-top: 2.5vh;">
        <div style="z-index:0;position: absolute;">
          <div style="margin-top: 2.2vh;margin-left: 2vw;width: 8vw;height: 2.6vh;text-overflow: ellipsis;font-size: 2vh;
                    white-space: nowrap;overflow: hidden;">{{ item.goodsName }}</div>
          <div style="height: 5px;"></div>
          <router-link :to="{ path: '/detail/' + [[item.goodsId]] }">
            <img :src="item.goodsImage" alt="" style="height: 27vh;width: 11vw;margin-left: 1.8vw;border-radius: 5px;">
          </router-link>
          <div style="margin-left: 9%;margin-top: 2%;width: 20vw;">
            <div style="float: left;font-size: 2.7vh;">￥{{ item.price }} </div>
            <div
              style="border: solid crimson 1px;border-radius: 13px;color: brown;float: left;margin-top: 1vh;margin-left: 0.5vw;">
              <div style="margin: 0.3vh;font-size: 1.35vh;">优惠后￥{{ item.price }}</div>
            </div>
          </div>
        </div>
        <div v-if="display" class="el-dropdown-link" style="z-index:1;position: absolute;background-color: rgb(127, 127, 127,0.5);border-radius: 25px;
                border: 1px;width: 14.5vw;height: 40vh;" @click="deleteId(item.goodsId,Object.keys(list)+'')">
          <img class="img"
            style="z-index:1;position: absolute;width: 4.5vw;height: 10vh;margin-top: 13vh;margin-left: 5vw;"
            src="https://s.aigei.com/prevfiles/eb596cca2d644ebbb1e07bc3d60a92fe.png?e=1735488000&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:toi2oCahCyZp82F193MocGENqbo=">
        </div>


      </div>


    </div>
  </div>
</template>
      
<script>
export default {
  name: "MyFootprint",
  props: {list:{}, display:{type:Boolean},getFootprintList:{type:Function},keys:{type:String}},
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
  methods: {
    // 跳转到商品详情的页面，根据id
    deleteId(id,DateTime) {
      this.$confirm(`确定删除吗？`, '是否继续?', '提示', {
        confirmButtonText: '删除',
        cancelButtonText: '算了吧',
        type: 'warning'
      }).then(() => {
        this.$http.delete("/api/cloud-goods/footprint/delete", { 
          params: { goodsId: id, memberId: sessionStorage.getItem('user_id'),createTime: DateTime } }).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })// 获取商品列表
          this.getFootprintList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消'
        })
      })
    },
  }
}
</script>
<style scoped>
.img {
  transition: all 0.6s;
}

.img:hover {
  transform: scale(1.2, 1.2);
  transition: all 0.6s ease;
  opacity: 0.6;
}
</style>