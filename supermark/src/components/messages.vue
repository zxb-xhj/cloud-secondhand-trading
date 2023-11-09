<template>
<div id="yourScrollElementId" style="float: left;clear: left;width: 100%;margin-top: 0.8rem;height: 80vh;overflow: auto;overflow: hidden;">
                <div v-for="message in list" :key="message.id">
                    <div style="width: 76vw;height: 0.1vh;clear: left;"></div>
                    <div style="width: 75vw;font-size: 1.6vh;height: 2vh;text-align: center;clear: right;">
                        <div style="clear: left;text-align: center;margin-left: 0.5vw;">{{ message.createTime }}</div>
                    </div>
                    <div v-if="message.senderId!=id" style="width: 76vw;height: 7vh;">
                        <div style="float: left;">
                            <img style="border-radius: 50%;height: 6.3vh;width: 3vw;margin-left: 4rem;margin-right: 0.7rem" src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp">
                        </div>
                        <div>
                            <!--                      <div style="margin-left: 0.7rem;font-size: 0.5rem">nih</div>-->
                            <div v-if="message.type!=2" style="float: left;background-color: white;height: 4.6vh;;border-radius: 1.3vh;margin-top: 0.9vh">
                                <div style="margin-left: 0.8vw;margin-right: 0.8vw;margin-top:0.68vh;font-size: 2.5vh">{{message.content}}</div>
                            </div>
                        </div>
                    </div>
                    <div v-if="message.senderId==id" style="width: 76vw;height: 7vh;">
                        <div style="float: right;clear: right;">
                            <img style="border-radius: 50%;height: 6.3vh;width: 3vw;margin-right: 4rem;margin-left: 0.7rem" src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp">
                        </div>
                        <div>
                            <!--                      <div style="margin-left: 0.7rem;font-size: 0.5rem">nih</div>-->
                            <div v-if="message.type!=2" style="float: right;background-color: cornflowerblue;height: 4.6vh;border-radius: 1.3vh;margin-top: 0.85vh;">
                                <div style="margin: 0.28vh;margin-left: 0.7vw;margin-right: 0.7vw;font-size: 2.5vh;margin-top: 0.57vh;">{{message.content}}</div>
                                <div style="font-size: 1.8vh;margin-left: 0.5rem;color: gray;margin-top: 0.2rem">
                                    <!-- <span v-if="message.isRead">已读</span>
                                    <span v-if="!message.isRead">未读</span> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="width: 100%;height: 4vh"></div>
            </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
    name:"Messages",
//import 引入的组件需要注入到对象中才能使用
components: {},
props: {
    list: {
    type: Array,
      required: true
    },
    id:String
},
data() {
//这里存放数据
return {
    
};
},
//计算属性 类似于 data 概念
computed: {},
//监控 data 中的数据变化
watch: {
    // list(newVal, oldVal) {
    //   console.log('message 变化了');
    //   console.log('旧值：' + oldVal);
    //   console.log('新值：' + newVal);
    //   this.getdd()
    // }
},
//方法集合
methods: {
    getdd(){
        // 获取滚动条元素
        var scrollElement = document.getElementById("yourScrollElementId");

        // // 获取滚动条当前位置
        var scrollTop = scrollElement.scrollTop;

        // // 获取滚动条内容高度
        var scrollHeight = scrollElement.scrollHeight;

        // 设置滚动条到最底部
        scrollElement.scrollTop = scrollHeight - scrollElement.clientHeight;
    }
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
updated() {
    this.getdd()
}, //生命周期 - 更新之后
beforeDestroy() {
    console.log("zole")
}, //生命周期 - 销毁之前
destroyed() {
    console.log("zole")
}, //生命周期 - 销毁完成
activated() {
    console.log("zole")
}, //如果页面有 keep-alive 缓存功能，这个函数会触发
}
</script>
<style>

</style>