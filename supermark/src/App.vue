<template>
  <div id="app">
    <keep-alive>
      <router-view/>
    </keep-alive>

  </div>
</template>
<script>
import WebSocketService from './components/websocket';
import EventBus from './components/event-bus'
import Message from './components/message.vue'
// App.vue
export default {
	data() {
		return {
			// socket参数
			id: sessionStorage.getItem("user_id")
		};
	},
    mounted() {
      EventBus.$on("login", (id) => {
        // A发送来的消息
        this.id = id
      });
		// this.initWebSocket(userId); // userId为socket链接的参数
	},
    methods:{
      
    },
    watch:{
      'id':function(){
        this.websocket()
      }
    },
    // components:{
    //   Message
    // },
    activated(){
      console.log("----")
      // WebSocketService.init("ws://localhost:20007/ws/"+sessionStorage.getItem("user_id"))
      // // 设置全局websocket
      // if(sessionStorage.getItem("user_id")!=null){
      //   WebSocketService.init("ws://localhost:20007/ws/"+sessionStorage.getItem("user_id"))
      // WebSocketService.socket.onmessage = (event) => {  
      //   const path = this.$route.path
      //       // EventBus.$emit("aMsg", '来自A页面的消息');
      //       const message = event.data;  
      //       console.log(message)
      //       const senderId = message.substring(0,message.indexOf(':'));
      //       const name = message.substring(message.indexOf(":")+1,message.indexOf(":",message.indexOf(":")+1))
      //       const content = message.substring(message.indexOf(":",message.indexOf(":")+1)+1);
      //       this.$notify.success({
      //               title:`${name}发消息来了`,
      //               message:content,
      //               onClick: () => {
      //                   // 在点击消息时执行路由跳转
      //                   if(path!="/message"){
      //                       this.$router.push({ name: 'Message', params: { senderId: senderId,sellerName:name }}); // 替换为你要跳转的目标路由路径
      //                   }else{
      //                     // Message.methods.setMessage(message)
      //                   }
                        
      //               }
      //           });
      //         }; 
      // }
    },
    created(){
      // 设置全局websocket
      this.websocket()
    },
    methods:{
      websocket(){
        if(sessionStorage.getItem("user_id")!=null){
        WebSocketService.init("ws://localhost:20007/ws/"+sessionStorage.getItem("user_id"))
        WebSocketService.socket.onmessage = (event) => {  
            const path = this.$route.path
            const message = event.data;  
            const senderId = message.substring(0,message.indexOf(':'));
            const name = message.substring(message.indexOf(":")+1,message.indexOf(":",message.indexOf(":")+1))
            const content = message.substring(message.indexOf(":",message.indexOf(":")+1)+1);
            if(path!="/message"){
              this.$notify.success({
                    title:`${name}发消息来了`,
                    message:content,
                    onClick: () => {
                        // 在点击消息时执行路由跳转
                        if(path!="/message"){
                            this.$router.push({ name: 'Message', params: { senderId: senderId,sellerName:name }}); // 替换为你要跳转的目标路由路径
                        }else{
                          // 如果在消息页面则调用消息总线发送消息
                          // EventBus.$emit("message",message)
                        }
                        
                    }
                });
            }else{
              EventBus.$emit("message",message)
            }
            
        }; 
      }
      }
    }
    // beforeDestroy(){
    //   WebSocketService.close
    // }
  }
</script>

<style>

</style>
