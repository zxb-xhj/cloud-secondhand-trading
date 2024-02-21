<template>
  <div class="goods" id="goods" name="goods" style="width: 100%;">
    <!-- 面包屑 -->
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item to="/">首页</el-breadcrumb-item>
        <el-breadcrumb-item>全部商品</el-breadcrumb-item>
        <el-breadcrumb-item v-if="search">搜索</el-breadcrumb-item>
        <el-breadcrumb-item v-else>分类</el-breadcrumb-item>
        <el-breadcrumb-item v-if="search">{{ search }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 面包屑END -->

    <!-- 分类标签 -->
    <div class="nav">
      <div class="product-nav">
        <div class="title">分类</div>
        <el-tabs v-model="activeName" type="card" style="font-size: 2.2vh;">
          <el-tab-pane name="0" label="全部" style="font-size: 2.2vh;"></el-tab-pane>
          <el-tab-pane style="font-size: 2.2vh;" v-for="category in categorys" :key="category.id" :name="category.id+''" :label="category.name"></el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <div style="height: 2rem;"></div>
    <!-- 分类标签END -->
    <div  v-loading="listLoading">
      <!-- 主要内容区 -->
      <div class="box-head" style="margin-left: 7%;margin-right: 2%;">

        <div style="">
          <MyList style="background-color: #F5F5F5;" :list="product" v-if="product.length > 0"></MyList>
          <div v-else class="none-product">抱歉没有找到相关的商品，请看看其他的商品</div>
        </div>
      </div>
    </div>
    <div class="fixed-div el-dropdown-link" style="text-align: center;font-size: 2vh;" @click="backtop()">
      <div class=" el-icon-arrow-up"></div>
      <div>回到顶部</div>
    </div>
    <!-- 分页 -->
    <!-- <div style="clear: left;float: right;margin-right: 10%;height: 50px;" v-if="product.length >= 12">
      <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :page-size="pageSize"
        :total="total">
      </el-pagination>
    </div> -->
    <!-- <div style="height: 20px;"></div> -->
    <!-- 分页END -->
    <!-- 主要内容区END -->
  </div>
</template>
<script>
import MyList from "@/views/frontdesk/MyList";
export default {
  data() {
    return {
      fanhui: false,
      categoryID: [], // 分类id
      product: "", // 商品列表
      total: 0, // 商品总量
      pageNo: 1,
      category_id: 0,
      pageSize: 15, // 每页显示的商品数量
      activeName: "-1", // 分类列表当前选中的id
      search: "", // 搜索条件
      categorys:[],
      listLoading:true
    };
  },
  components: {
    MyList
  },
  created() {
    // 获取分类列表
    // this.getData();
    this.getCategory()
  },
  activated() {
    this.category_id = 0;
    this.search = "";
    this.total = 0;
    this.activeName = "0";
    this.pageNo = 1;
    // 如果路由没有传递参数，默认为显示全部商品
    // if (Object.keys(this.$route.query).length == 0) {
    //   this.category_id =0;
    //   this.activeName = "0";
    //   return;
    // }
    if (this.$route.query.search != undefined) {
      this.search = this.$route.query.search;
    }
    if (!this.$route.meta.isBack) {
      // 如果isBack是false，表明需要获取新数据，否则就不再请求，直接使用缓存的数据
      this.getData()
    }
    // 恢复成默认的false，避免isBack一直是true，导致下次无法获取数据
    this.$route.meta.isBack = false
  },
  watch: {
    // 监听点击了哪个分类标签，通过修改分类id，响应相应的商品，每次发生变化都初始化pageNo和total
    activeName: function (val) {
      if (val == 0) {
        this.category_id = 0;
        this.search = "";
      }
      if (val > 0) {
        this.category_id = val;
      }
      this.pageNo = 1;
      this.total = 0;
      this.$router.push({
        path: "/goods",
        query: { categoryID: this.category_id }
      });
    },
    //分类id数据发生变化，则调用对应的商品数据，并清除搜索内容
    category_id: function () {
      this.search = "";
      this.getData();
    },
    // 监听搜索条件，响应相应的商品
    search: function (val) {
      if (val != "") {
        this.getData(val);
      }
    },
    // 监听路由变化，更新路由传递了搜索条件
    $route: function (val) {
      if (val.path == "/goods") {
        if (val.query.search != undefined) {
          this.activeName = "0";
          this.currentPage = 1;
          this.total = 0;
          this.search = val.query.search;
        }
      }
    }
  },
  methods: {
    getCategory(){
      this.$http.get("/api/cloud-goods/category" ).then(res => {
        this.categorys = res.data.data
      });
    },
    // 返回顶部
    backtop() {
      const timer = setInterval(function () {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
        const ispeed = Math.floor(-scrollTop / 5);
        document.documentElement.scrollTop = document.body.scrollTop = scrollTop + ispeed;
        if (scrollTop === 0) {
          clearInterval(timer);
        }
      }, 22);
    },
    // 页码变化调用currentChange方法
    handleCurrentChange(pageNo) {
      this.pageNo = pageNo;
      //重新调用商品列表
      if (this.search != "") {
        this.getData();
      } else {
        this.getData();
      }
      this.backtop()
    },
    getData() {
      this.listLoading = true
      this.fanhui = true;
      this.$http.post("/api/cloud-goods/goods/es/list",
        {
          category_id: this.category_id,
          pageNo: this.pageNo,
          pageSize: 1000,
          name: this.search
        }).
        then(res => {
          this.product = res.data.data.list;
          this.total = res.data.data.total;
        })
        this.listLoading = false
    },
    getProductBySearch() {
      this.$http.get("/api/cloud-goods/goods/es/list?pageNo=" + this.pageNo + "&pageSize=" + this.pageSize + "&name=" + this.search, {
        commodityName: this.search,
        pageNo: this.pageNo,
        pageSize: this.pageSize
      }).then(res => {
        this.product = res.data.commodityList;
        this.total = res.data.commodityCount;
      }).catch(err => {
        return Promise.reject(err);
      });
    }
  }
};
</script>

<style scoped>
.goods {
  background-color: white;
}
.fixed-div {
    position: fixed;
    top: 56vh;
    right: 2.5vw;
  }
.el-tabs--card .el-tabs__header {
  border-bottom: none;
}

.goods .breadcrumb {
  /* height: 50px; */
  background-color: white;
}
.goods .breadcrumb .el-breadcrumb {
  width: 65vw;
  line-height: 2vh;
  font-size: 2.2vh;
  margin-left: 9vw;
}

/* .goods .nav {
  background-color: rgb(249, 249, 249)
} */

.goods .nav .product-nav {
  width: 80vw;
  height: 2.5vh;
  margin: 0 auto;
  font-size: 2vh;
}

.nav .product-nav .title {
  width: 3vw;
  font-size: 2.2vh;
  font-weight: 500;
  float: left;
  margin-top: 1.3vh;
}

.goods .main {
  margin: 0 auto;
  max-width: 1225px;
}

.goods .main .list {
  min-height: 650px;
  padding-top: 14.5px;
  margin-left: -13.7px;
  overflow: auto;
}

.goods .main .pagination {
  height: 50px;
  text-align: center;
}

.goods .main .none-product {
  color: #333;
  margin-left: 13.7px;
}

.product-box .box-head .photo-list {
  float: left;
  height: 615px;
  width: 234px;
}
</style>