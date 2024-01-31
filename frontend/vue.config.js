const { defineConfig } = require('@vue/cli-service')
const path = require("path")

module.exports = defineConfig({
  devServer: {
    // 프록시 설정
    proxy: {
      '/api/': {
        target: 'http://localhost:8081/',
        changeOrigin: true
      }
    }
  },
  transpileDependencies: true,
  // build 경로 설정
  outputDir: "../backend/src/main/resources/static",

  css: {
    sourceMap: true,
    loaderOptions: {
      scss: {
        additionalData: `
          @import "~@/assets/style/_mixins.scss";
          @import "~@/assets/style/_variables.scss";
          @import "~@/assets/style/_mediaQueries.scss";
          @import "~@/assets/style/_svg.scss";
          @import "~@/assets/style/_common.scss";
          @import "~@/assets/style/_reset.scss";
        `
      }
    }
  }
});