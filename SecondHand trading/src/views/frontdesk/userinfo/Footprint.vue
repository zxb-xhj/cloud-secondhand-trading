<template>
    <div>
        <div style="height: 70px;background-color: rgb(255, 255, 255);
        position: fixed;width: 85%;z-index: 9;top: 1px;margin-top: 39.5px;">
            <div style="height: 20px;"></div>
            <p style="float: left;font-size: 1.5vw;">浏览记录</p>
            <el-button v-if="!display" style="float: right;margin-right: 5vw;" type="primary" @click="display = true">管理</el-button>
            <el-button v-if="display" style="float: right;margin-right: 5vw;" type="success" @click="display = false">完成</el-button>
        </div>
        
        <!-- <div style="height: 50px;"></div> -->
        <div v-loading="listLoading" style="height: 80vh;margin-top: 50px;">
            <div style="clear: right;clear: left;" v-for="(list, index) in footprint" :key="index">
                <div style="height: 2vh;"></div>
                <div style="font-size: 2.6vw;margin-left: 2vw;">{{ (Object.keys(list) + "") }} </div>
                <MyFootprint :list="list" :display="display" :getFootprintList="getFootprintList"
                    v-if="footprint.length > 0">
                </MyFootprint>
            </div>
            <div style="clear: right;clear: left;margin-top: 35vh;text-align: center;" v-if="footprint == []">
                <p style="font-size: 4vh;">无</p>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import MyFootprint from './MyFootprint.vue'
export default {
    //import 引入的组件需要注入到对象中才能使用
    components: { MyFootprint },
    props: {},
    data() {
        //这里存放数据
        return {
            footprint: [],
            display: false,
            datas: [
                { id: 1, time: '2024-2-9' },
                { id: 2, time: '2024-2-10' },
                { id: 3, time: '2024-2-11' }
            ],
            listLoading: true
        };
    },
    //计算属性 类似于 data 概念
    computed: {},
    //监控 data 中的数据变化
    watch: {},
    //方法集合
    methods: {
        getFootprintList() {
            // 获取商品列表
            this.$http.get("/api/cloud-goods/footprint/footprintList", { params: { memberId: sessionStorage.getItem('user_id') } }).then(res => {
                this.footprint = res.data.data;
                setTimeout(() => {
                    // 在这里执行你的方法
                    this.listLoading = false
                }, 500);
            });
        },
    },
    //生命周期 - 创建完成（可以访问当前 this 实例）
    created() {
        this.getFootprintList()
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