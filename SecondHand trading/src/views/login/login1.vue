<template>
<div>
    <!-- <el-button @click="getShow()">show</el-button> -->
    <!-- <transition name="el-zoom-in-top">
        <Regiter @show="getShow" v-show="show" class="transition-box"></Regiter>
      </transition>
    <transition name="el-zoom-in-bottom">
        <Login @show="getShow" v-show="show1"></Login>
    </transition> -->
    <input id="name" name="name"/>
<input id="age" name="age"/>
<input id="file" type="file" name="file" multiple>
<button @click="getss">提交+1</button>
<button @click="getsss">提交-1</button>
<input type="text" v-model="test" >
<div>{{ test }}</div>
<!-- <el-upload
            :data="dataObj"
            action="http://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com"
            multiple
            accept="jpg,jpeg,png,PNG"
            list-type="picture-card"
            :on-success="handleDefaultImgUploadSuccess"
            :show-file-list="false"
            :before-upload="beforeUpload"
          >
            <img style="width: 9.3rem;height: 9.3rem;" v-if="this.goodsForm.defaultImg" :src="this.goodsForm.defaultImg" class="avatar">
            <i v-else class="el-icon-plus" />
          </el-upload> -->
    <el-dialog title="上传文件" :visible.sync="goodsFormShow">
      <el-form label-width="">
        <div style="float: left;" @click="fileType = ['.mp4']">
            <el-upload style="margin-left: 3vw;" :data="dataObj" action="http://cloud-zxb.oss-cn-guangzhou.aliyuncs.com" multiple
             list-type="picture-card" :on-success="handleUploadSuccess"
             :before-upload="beforeUpload" :show-file-list="false">
             上传视频
             <!-- <img style="width: 9.3rem;height: 9.3rem;" v-if="goodsForm.defaultImg" :src="goodsForm.defaultImg" class="avatar"> -->
            <!-- <i v-if="!goodsForm.defaultImg" class="el-icon-plus" /> -->
          </el-upload>
        </div>
        
          <div style="width: 13.6vw;float: left;" @click="fileType = ['.txt']">
            <el-upload style="margin-left: 2vw;" :data="dataObj" action="http://cloud-zxb.oss-cn-guangzhou.aliyuncs.com" multiple
             list-type="picture-card" 
             :before-upload="beforeUpload" :show-file-list="false">
             上传文件
             <!-- <img style="width: 9.3rem;height: 9.3rem;" v-if="goodsForm.defaultImg" :src="goodsForm.defaultImg" class="avatar"> -->
            <!-- <i v-if="!goodsForm.defaultImg" class="el-icon-plus" /> -->
          </el-upload>
          </div>
          
          <div  @click="fileType = ['.jpg']">
            <el-upload style="margin-left: 2vw;" :data="dataObj" action="http://cloud-zxb.oss-cn-guangzhou.aliyuncs.com" multiple
             list-type="picture-card"  :on-success="handleDefaultImgUploadSuccess"
             :before-upload="beforeUpload" :show-file-list="false">
             上传图片
             <!-- <img style="width: 9.3rem;height: 9.3rem;" v-if="goodsForm.defaultImg" :src="goodsForm.defaultImg" class="avatar"> -->
            <!-- <i v-if="!goodsForm.defaultImg" class="el-icon-plus" /> -->
          </el-upload>
          </div>
          
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">关 闭</el-button>
      </div>
    </el-dialog>
          

</div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Login from '../login/Login.vue'
import Regiter from '../register/Regiter.vue'
import { getUUID } from '../../utils/index'
export default {
    // data: () => ({
    //   show: true
    // }),
//import 引入的组件需要注入到对象中才能使用
components: {Login,Regiter},
props: {
    // 大小限制(MB)
    fileSize: {
      type: Number,
      default: 2,
    },
    // 文件类型, 例如['png', 'jpg', 'jpeg']
    fileType: {
      type: Array,
      default: () => [".mp4"],
    },
},
data() {
//这里存放数据
return {
    show: true,
    show1: false,
    formData: '',
    dataObj:{},
    goodsForm:{},
    goodsFormShow: true,
    test: 'dd'
};
},
//计算属性 类似于 data 概念
computed: {},
//监控 data 中的数据变化
watch: {},
//方法集合
methods: {
    getss(){
        this.$http({url:'/api/cloud-order/undolog/test',
        method: "get"})
    },
    getsss(){
        this.$http({url:'/api/cloud-order/undolog/test1',
        method: "get"})
    },
    closeDialog(){
        this.goodsFormShow =false
    },
    handleDefaultImgUploadSuccess() {
        console.log(this.dataObj.host + '/' + this.dataObj.key)
      this.goodsForm.defaultImg = this.dataObj.host + '/' + this.dataObj.key;
    },
    handleUploadSuccess(response, file, fileList){
        console.log(file)
    },
    getFile1(){
        this.$http({url:'http://cloud-zxb.oss-cn-guangzhou.aliyuncs.com/',
        method: "post",
        data: this.dataObj
        // data:forms,
    }).then(response=>{

    })
    },
    getFile(){
        let files = document.getElementById('file').files
        console.log(files)
        // 校检文件类型
      if (this.fileType) {
        let fileExtension = "";
        if (files[0].name.lastIndexOf(".") > -1) {
          fileExtension = files[0].name.slice(files[0].name.lastIndexOf("."));
        }
        const isTypeOk = this.fileType.some((type) => {
          if (files[0].type.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true;
          return false;
        });
        if (!isTypeOk) {
          this.$message.error(`文件格式不正确, 请上传${this.fileType.join("/")}格式文件!`);
          return false;
        }
      }
      // 校检文件大小
      if (this.fileSize) {
        const isLt = files[0].size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$message.error(`上传文件大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      
        // this.getaa(files)
        // let forms = new FormData()
        // // 上传多个文件
        // Array.from(files).forEach(item => {
        //     forms.append('file', item)
        // })

    //     const file = document.getElementsByName("file");
    //     // 创建一个FormData对象，用于存储文件数据
        const formData = new FormData();
        formData.append('file', files[0]);
    //     this.formData = formData
    //     console.log(file[0].value)
        this.$http({url:'/api/cloud-oss/oss/policy',
        method: "get",
           
        // data:forms,
    }).then(response=>{
        this.dataObj.policy = response.data.data.policy
        this.dataObj.signature = response.data.data.signature
        this.dataObj.ossaccessKeyId = response.data.data.accessid
        this.dataObj.dir = response.data.data.dir
        this.dataObj.host = response.data.data.host
        this.dataObj.key = response.data.data.dir + getUUID() + files[0].name
        // this.dataObj.file = files[0]
        
        this.$message.success(`上传成功`);
        console.log(this.dataObj)
    })
},
beforeUpload(files){
    // 校检文件类型
    if (this.fileType) {
        let fileExtension = "";
        if (files.name.lastIndexOf(".") > -1) {
          fileExtension = files.name.slice(files.name.lastIndexOf("."));
        }
        const isTypeOk = this.fileType.some((type) => {
          if (files.type.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true;
          return false;
        });
        if (!isTypeOk) {
          this.$message.error(`文件格式不正确, 请上传${this.fileType.join("/")}格式文件!`);
          return false;
        }
      }
      // 校检文件大小
      if (this.fileSize) {
        const isLt = files.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$message.error(`上传文件大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
    console.log(files)
    const _self = this
      return new Promise((resolve, reject) => {
        this.$http.get("/api/cloud-oss/oss/policy")
          .then(response => {
            console.log(response)
            _self.dataObj.policy = response.data.policy
            _self.dataObj.signature = response.data.signature
            _self.dataObj.ossaccessKeyId = response.data.accessid
            _self.dataObj.dir = response.data.dir
            _self.dataObj.host = response.data.host
            _self.dataObj.key = response.data.dir + getUUID() + files.name
            console.log(_self.dataObj)
            resolve(true)
          })
          .catch(err => {
            reject(false)
          })
      this.$message.success(`上传成功`);
      })
      
},
    get(ms){
        return new Promise(resolve => {
            setTimeout(resolve, ms)

        });
    },
    async getShow(){
        this.show = !this.show
        await this.get(150);

        this.show1 = !this.show1
    },
},
//生命周期 - 创建完成（可以访问当前 this 实例）
created() {

},
//生命周期 - 挂载完成（可以访问 DOM 元素）
mounted() {

},
beforeCreate() {}, //生命周期 - 创建之前
beforeMount() {}, //生命周期 - 挂载之前
beforeUpdate() {}, //生命周期 - 更新之前
updated() {}, //生命周期 - 更新之后
beforeDestroy() {}, //生命周期 - 销毁之前
destroyed() {}, //生命周期 - 销毁完成
activated() {}, //如果页面有 keep-alive 缓存功能，这个函数会触发
}
</script>
<style scoped>

</style>