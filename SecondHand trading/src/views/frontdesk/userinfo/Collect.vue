<template>
    <div>
        <div style="height: 70px;background-color: rgb(255, 255, 255);
        position: fixed;width: 85%;z-index: 9;top: 1px;margin-top: 39.5px;">
            <div style="height: 20px;"></div>
            <p style="float: left;font-size: 1.5vw;margin-left: 2.6vw;">收藏夹</p>
            <div style="margin-bottom: 20px;margin-right: 5vw;">
                <el-button v-if="!display" style="float: right;" type="primary" @click="display = true">管理</el-button>
                <el-button v-if="display" style="float: right;" type="success" @click="display = false">完成</el-button>
            </div>
        </div>
        <div style="height: 50px;"></div>
        <div v-loading="listLoading" style="height: 80vh;">
            <MyCollect style="clear: right;clear: left;" :list="collect" :getCollectList="getCollectList" :display="display" v-if="collect.length > 0">
            </MyCollect>
            <div style="clear: right;clear: left;margin-top: 35vh;text-align: center;" v-if="collect == []">
                <p style="font-size: 4vh;">无</p>
            </div>
        </div>

    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import MyCollect from './MyCollect.vue'
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: { MyCollect },
    props: {},
    data() {
        //这里存放数据
        return {
            collect: [],
            display: false,
            listLoading: true
        };
    },
    //计算属性 类似于 data 概念
    computed: {},
    //监控 data 中的数据变化
    watch: {},
    //方法集合
    methods: {
        getCollectList() {
            // 获取商品列表
            this.$http.get("/api/cloud-goods/collect/list", { params: { memberId: sessionStorage.getItem('user_id') } }).then(res => {
                this.collect = res.data;
                setTimeout(() => {
                    // 在这里执行你的方法
                    this.listLoading = false
                }, 500);
            });
        },
    },
    //生命周期 - 创建完成（可以访问当前 this 实例）
    created() {
        this.getCollectList()
    },
    //生命周期 - 挂载完成（可以访问 DOM 元素）
    mounted() {

    },
    beforeCreate() { }, //生命周期 - 创建之前
    beforeMount() { }, //生命周期 - 挂载之前
    beforeUpdate() { }, //生命周期 - 更新之前
    updated() { }, //生命周期 - 更新之后
    beforeDestroy() { }, //生命周期 - 销毁之前
    destroyed() { }, //生命周期 - 销毁完成
    activated() { }, //如果页面有 keep-alive 缓存功能，这个函数会触发
}
</script>
<style scoped></style>