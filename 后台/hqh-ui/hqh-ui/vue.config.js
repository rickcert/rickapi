module.exports = {
    devServer: {
        host: 'localhost',
        open: true, // 自动打开浏览器
        // 代理配置表，在这里可以配置特定的请求代理到对应的API接口
        proxy: {
            '/api': { // 匹配所有以 '/api'开头的请求路径
                target: 'http://localhost:9090/', // 代理目标的基础路径
                // secure: false, // 如果是https接口，需要配置这个参数
                changeOrigin: true, // 支持跨域
                pathRewrite: { // 重写路径: 去掉路径中开头的'/api'
                    '^/api': ''
                }
            }
        }
    },
    lintOnSave: false,
}