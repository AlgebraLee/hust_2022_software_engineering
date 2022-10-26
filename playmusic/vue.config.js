const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  productionSourceMap: false, // 生产环境打包不输出
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'https://api-sooty-nu.vercel.app/',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        }
      }
    }
  }
})
