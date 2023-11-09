<template>
    <div>
        <!--      <div style="float: left;height: 100vh;width: 1%"></div>-->
        <div style="float: left;width: 21%;height: 96vh;border-radius: 0.5rem;background-color: white;">
            <div class="el-dropdown-link" style="margin-left:2vh;margin-top: 1vw;margin-bottom: 0.7vw;background-color: white;">
                <a href="javascript:history.back (-1)" class="el-dropdown-link">
                    <i class="el-icon-arrow-left"></i>返回
                </a>
            </div>
            <div id="info" class="info1" style="height: 9vh;width: 100%;text-align: center;border-radius: 1.3vh;margin-left: 0.3vw;background-color: white;">
                <router-link :to="{ path: '/baseInfo' }">
                    <div style="width: 93%;height: 8vh;border-radius: 0.5rem;margin-left: 3vw;">
                    <div style="float: left">
                        <img style="border-radius: 50%;height: 7vh;width: 3.5vw;margin-left: 1rem;margin-right: 0.7rem;margin-top: 1vh;" src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp">
                    </div>
                    <div style="float: left;margin-top: 1.8vh;font-size: 2.5vh;">
                        <div style="margin-bottom: 0.2vh;">
                            {{ userName }}
                        </div>
                        <div style="font-size: 1.3vh;  background-color:#000000;background-color:rgba(255, 115, 0, 0.51);border-radius: 1.3vh;height: 3vh;width: 4.8vw;text-align: center;">
                            <div style="height: 0.4vh;"></div>
                            <div style="color: rgb(241, 241, 241);">普通会员</div>
                        </div>
                    </div>
                    </div>
                </router-link>
            </div>
            <hr/>
            <div style="height: 5vh;width: 100%;text-align: center;background-color: white;">
                <div style="margin-top: 2vh;font-weight: bold;font-size: 2.5vh">消息列表</div>
            </div>
            <hr/>
            <div v-loading="listLoading" v-if="this.userList.length==0" style="text-align: center;margin-top: 5vh;">暂无消息</div>
            <div style="overflow: auto;height: 77.5vh;">
                <div v-for="(name,index) in userList" :key="name.id">
                    <div v-bind:ref="name.id"  v-bind:id="name.id+'id'" class="messagediv" style="width: 18.5vw;height: 8vh;border-radius: 1.3vh;margin-left: 0.7vw;" @click="getStyMessage(name)">
                        <div style="float: left;clear: left;">
                            <img style="border-radius: 50%;height: 6.3vh;width: 3vw;margin-left: 1rem;margin-right: 0.7rem;margin-top: 5px;" src="https://cloud-secondhand-trading.oss-cn-shanghai.aliyuncs.com/u%3D3030190913%2C291621981%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp">
                        </div>
                        <div style="float: left;margin-top: 2vh;font-size: 2.5vh;width: 10vw;">
                            {{ name.nickname==null?name.username:name.nickname }}
                        </div>
                    </div>
                    <div style="width: 19.5vw">
                        <hr v-if="index!=list.length-1"
                            style="font-size: 0.001rem;margin-left: 9vh;color: gray;width: 70%;top: -10px"/>
                    </div>
                </div>
            </div>
        </div>
        <div style="float: left;height: 96vh;width: 0.2%"></div>
        <div v-loading="listLoading" style="float: left;width: 78%;background-color: rgb(250,250,249);height: 96vh;margin-top: 2vh;">
            <div style="width: 99%;height: 7vh;float: left;
          text-align: center;background-color: #f0f0f0;border-radius: 1.3vh;">
                <div style="margin-top: 1.8vh;font-size: 2.5vh">{{name}}</div>
            </div>
            <Messages ref="childComponentRef" :list="list" :id="id"></Messages>
            <div v-if="name" style="clear: left;position:absolute;bottom: 10px;width: 77%;height: 5vh;background-color: white">
                    <input @keyup.enter="addMessage" v-model="message" type="text" name="message" style="float: left;width: 55%;margin-left: 14vw;height: 4.7vh;
                    border-radius: 1.3vh;border: solid #787878 3px">

                <div style="float: left">
                    
                    <input class="el-dropdown-link" @click="addMessage" value="发送" type="button" style="height: 5vh;width: 5vw;border-radius: 0.5rem;margin-left: 0.7vw;
                font-size: 2.5vh;margin-top: 0.1vh;background-color: rgb(72, 113, 247);color: white;"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// import {Messages} from '../components/messages.vue' 
import WebSocketService from '../components/websocket'
import EventBus from './event-bus'
export default {
    // components: {Messages},
    name: "message",
    data(){
        return{
            name: '',
            lists:[],
            message: '',
            userList:[],
            list: [],
            userName: sessionStorage.getItem("nickname")!=null?sessionStorage.getItem("nickname"):sessionStorage.getItem("username"),
            id: sessionStorage.getItem("user_id"),
            date: 0,
            seller: '',
            receiverId: 0,
            testId: false,
            listLoading: true,
            fid: this.$route.params.id,
            fseller: this.$route.params.seller,
            userLists: {},
            set: new Set(),
            path:this.$route.path,
            i: 1,
            idd: 0,
            messages:''
        }
    },
    activated() {
        console.log(this)
        const name = sessionStorage.getItem("nickname")
        console.log(name=='null')
        this.userName=sessionStorage.getItem("nickname")=='null'?sessionStorage.getItem("username"):sessionStorage.getItem("nickname")
        this.id = sessionStorage.getItem("user_id")
        this.userList = []
        this.fid = this.$route.params.id
        this.fseller = this.$route.params.seller
        if(this.testId){
            this.getList()
        }
    },
    created() {
        if(!this.testId){
            this.getList()
        }
        // WebSocketService.init("ws://localhost:20007/ws/"+this.id)
        // WebSocketService.socket.onmessage = (event) => {  
        //     const message = event.data;  
        //     this.setMessage(message)
            
        // }; 
    },
    watch:{
        'messages':function(){
            if(this.messages!=''){
                const message = this.messages
                this.setMessage(message)
            }
            this.messages = ''
        }
    },
    mounted() {
        this.getList()
        EventBus.$on("message", (msg) => {
        // A发送来的消息
            this.messages = msg
        });
    },
    // beforeDestroy(){
    //     WebSocketService.close
    // },
    methods:{
        gettt(){
          console.log(this)  
        },
        // 接收消息
        setMessage(message){
            //date: "2023-10-26T14:06:06.387", read: 0, sender: "李四", name: "张三", id: 1, message: "你好2
            const senderId = message.substring(0,message.indexOf(':'));
            const name = message.substring(message.indexOf(":")+1,message.indexOf(":",message.indexOf(":")+1))
            const content = message.substring(message.indexOf(":",message.indexOf(":")+1)+1);
            
            console.log(senderId==this.receiverId)
            if(senderId==this.receiverId){
                console.log(senderId)
                // this.getList({id: senderId,nickname: name,username: name})
                        const date = new Date();
                        if (date.getTime()-this.date>=300000){
                            var month = date.getMonth() + 1
                            const year = date.getFullYear()+"-"+month+"-"+date.getDate()+" "
                                +date.getHours()+":"+date.getMinutes();
                            this.date = date.getTime();
                            this.list.push({createTime: year, isRead: 0,receiverId: this.receiverId,senderId: senderId, id: this.list.length+1+"id", content: content})
                        }else{
                            this.list.push({createTime: '', isRead: 0,receiverId: this.receiverId,senderId: senderId, id: this.list.length+1+"id", content: content})
                        }
                        console.log(this.list)
            }else{
                this.$notify.success({
                    title:`${name}发消息来了`,
                    message:content,
                    onClick: () => {
                        console.log(this.$route.path)
                        this.getList({id: senderId,nickname: name,username: name})
                        }
                        
                //     }
                });
            }
        },
        // 发送消息
        addMessage(){
            var i =1;
            const message = this.message;
            if (message!=null&&message!=''){
                this.$http.post('/api/cloud-message/privateMessage/webSocket',{
                    content:message,senderId: this.id,receiverId: this.receiverId 
                }).then(response => {
                    const list = response.data.data;
                    if (i<=1){
                        // 请求成功处理
                        const date = new Date(list.createTime);
                        if (date.getTime()-this.date>=300000){
                            this.date = date.getTime();
                            list.createTime = list.createTime.substring(0,16).replace('T',' ');
                            this.list.push(list);
                        }else{
                            list.createTime = '';
                            this.list.push(list);
                        }
                        i++;
                    }
            this.getMessage2()
                }).catch(error => {
                    // 请求失败处理
                    console.log(error);
                });
            }

            this.message = '';
        },
        getMessage(name){
            this.name = name.sender
            this.list = [];
        },
        // 修改时间
        getDateTime(name){
            this.lists.forEach(list=>{
                if ((list.senderId==this.id&&list.receiverId==name.id)||(list.senderId==name.id&&list.receiverId==this.id)){
                    // 5分钟 300000
                    let date = new Date(list.createTime).getTime();
                    if (date-this.date>300000){
                        this.date = date;
                        list.createTime = list.createTime.substring(0,16).replace('T',' ');
                    }else{
                        list.createTime = '';
                    }
                    this.list.push(list);
                }
            })
        },
        getStyMessage(name){
            this.getList(name)
            // this.receiverId =name.id
            // this.getMessage();
            // this.name = name.nickname==null?name.username:name.nickname;
            // this.list = [];
            // this.date = ''
            // this.getDateTime(name);
            // this.getStyle(name.id)
        },
        getStyle(id){
            let classNames = document.getElementsByClassName("messagediv");
            for (let i = 0; i < classNames.length; i++) {
                classNames[i].style.backgroundColor = ""
            }
            
            const ids = document.getElementById(id+"id");
            if(ids!=null){
                ids.style.backgroundColor = "#e1e1e1";
            }
        },
        getMessage2(){
            const startTime = performance.now();
                this.$http.get('/api/cloud-message/privateMessage/getMessage/'+this.id).then(response => {
                if(response.data.data.privateMessageVOs.length>0){
                this.lists = Array.from(response.data.data.privateMessageVOs);
                }
                if(response.data.data.list!=null){
                this.userList = Array.from(response.data.data.list)
                }
                this.testId = true;
                const set = new Set();
                const senderId = this.$route.params.senderId
                const id = this.$route.params.id
                const seller = this.$route.params.seller
                if(senderId!=null){
                    // this.getList({id: this.$route.params.senderId,nickname: this.$route.params.sellerName,username: this.$route.params.sellerName});
                }
                if(this.userLists.id!=null){
                    this.userList.forEach(list=>{
                        set.add(list.id)
                    })
                    if(!set.has(this.userLists.id)){
                        this.userList.push(this.userLists)
                    }
                }
                if(id!=null&&this.i<2){
                    this.userList.forEach(list=>{
                        set.add(list.id)
                    })
                    if(!set.has(id)){
                        this.userLists = {id: id,nickname: seller,username: seller}
                        this.userList.push({id: id,nickname: seller,username: seller})
                    }
                
                    this.i++;
                }
                this.idd = 1;
                // 表示方法执行完成了
                }).catch(error => {
                    // 请求失败处理
                    console.log(error);
                });
        },
        async getMessages(){
            return new Promise((resolve, reject) => {
                // 记录开始时间
                const startTime = performance.now();
                this.$http.get('/api/cloud-message/privateMessage/getMessage/'+this.id).then(response => {
                if(response.data.data.privateMessageVOs.length>0){
                this.lists = Array.from(response.data.data.privateMessageVOs);
                }
                if(response.data.data.list!=null){
                this.userList = Array.from(response.data.data.list)
                }
                this.testId = true;
                const set = new Set();
                const senderId = this.$route.params.senderId
                const id = this.$route.params.id
                const seller = this.$route.params.seller
                if(senderId!=null){
                    // this.getList({id: this.$route.params.senderId,nickname: this.$route.params.sellerName,username: this.$route.params.sellerName});
                }
                if(this.userLists.id!=null){
                    this.userList.forEach(list=>{
                        set.add(list.id)
                    })
                    if(!set.has(this.userLists.id)){
                        this.userList.push(this.userLists)
                    }
                }
                if(id!=null&&this.i<2){
                    this.userList.forEach(list=>{
                        set.add(list.id)
                    })
                    if(!set.has(id)){
                        this.userLists = {id: id,nickname: seller,username: seller}
                        this.userList.push({id: id,nickname: seller,username: seller})
                    }
                
                    this.i++;
                }
                this.idd = 1;
                    resolve();
                // 表示方法执行完成了
                }).catch(error => {
                    // 请求失败处理
                    console.log(error);
                });
                // }
                // 记录结束时间
                const endTime = performance.now();
            });
        },
            
        // },
        async getList(message){
            this.listLoading = true;
                try {
                // 等待方法执行完成
                const data1 = await this.getMessages();
                if(message!=null){
                    this.list = [];
                    this.date = ''
                    this.receiverId =message.id
                    this.name = message.nickname;
                    this.getDateTime(message);
                    this.getStyle(message.id)
                }else{
                    const data = {
                    id: this.$route.params.senderId!=null?this.$route.params.senderId:this.$route.params.id,
                    nickname: this.$route.params.sellerName!=null?this.$route.params.sellerName:this.$route.params.seller,
                    username: this.$route.params.sellerName!=null?this.$route.params.sellerName:this.$route.params.seller
                    };
                    if(this.$route.params.senderId!=null||this.$route.params.id!=null){
                        this.list = [];
                        this.date = ''
                        this.receiverId =data.id
                        this.name = data.nickname;
                        this.getDateTime(data);
                        this.getStyle(data.id)
                    }
                }
                
            this.listLoading = false;
            } catch (error) {
                console.error(error);
            }
            
            
            this.listLoading = false;
        }
    }
}

window.scrollTo(0, document.body.scrollHeight);
</script>

<style>
.messagediv:hover {
    background-color: #e1e1e1;
}
#info:hover {
    background-color: #ADD8E6;
}
.info1:hover {
    background-color: #ADD8E6;
}
</style>