//配置开发中的跨域环境
'use strict'
const path = require('path')
const resolve = dir => path.join(__dirname, dir)
const ip = 'http://localhost:8085'
module.exports = {
  publicPath: './',
  // 使用cordova方式打包时的打包文件路径, 放在cordova/wwww/路径下, 若才有非cordova方式，可将下一行代码注释掉, 默认打包文件路径/dist
  outputDir: '../cordova/www/',
//   outputDir: 'dist',
//   assetsDir: 'static',
  productionSourceMap: false,
  // parallel: false,
  chainWebpack: config => {
    // 添加别名
    config.resolve.alias
      .set('@', resolve('src'))
      .set('assets', resolve('src/assets'))
  },
  devServer: {
    open: false,
    host: 'localhost',
    port: 8080,
    https: false,
    hotOnly: false,
    disableHostCheck: true,
    proxy: {
      // 配置跨域
      '/api': {
        target: ip,
        ws: true,
        changOrigin: true,
        pathRewrite: {
          '^/api': '/api'
        }
      }
      
    }
  }
}
