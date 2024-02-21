import Vue from 'vue'

const WebSocketService = {
    socket: null,
    init: function(url) {
      this.socket = new WebSocket(url);
      
      this.socket.onopen = () => {
        console.log('WebSocket连接已打开');
      };
      
      this.socket.onmessage = (event) => {
        // console.log('收到消息:', event.data);
      };
      
      this.socket.onclose = () => {
        // console.log('WebSocket连接已关闭');
      };
      
      this.socket.onerror = (error) => {
        // console.error('WebSocket连接发生错误:', error);
      };
    },
    send: function(message) {
      if (this.socket.readyState === WebSocket.OPEN) {
        this.socket.send(message);
      } else {
        // console.error('WebSocket连接未打开，无法发送消息');
      }
    },
    close: function() {
      if (this.socket) {
        this.socket.close();
      }
    }
  };
  export default WebSocketService;