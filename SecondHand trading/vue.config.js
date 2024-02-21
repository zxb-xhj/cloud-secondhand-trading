module.exports={
  devServer: {
    historyApiFallback:true,
    noInfo:true,
    overlay:true,
    proxy: {
      // 配置跨域
      '/api': {
        target: 'http://121.37.208.253:20000/',
        changOrigin: true,
        secure:false
      }
    }
  },
  configureWebpack:{
    resolve:{
      alias:{
        'assets':'@/assets',
        'common':'@/common',
        'components':'@/components',
        'network':'@/network',
        'views':'@/views',
      }
    }
  }
}