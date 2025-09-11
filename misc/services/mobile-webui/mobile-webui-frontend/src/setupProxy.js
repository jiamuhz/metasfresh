const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        // 匹配所有以 /api 开头的请求
        '/app',
        createProxyMiddleware({
            // 目标服务器地址 - 修改为你的 metasfresh 后端 URL
            target: 'http://localhost:8282',
            // 改变Origin头为目标URL，通常需要设置为true
            changeOrigin: true,
            // 如果需要，可以重写路径（根据你的后端API结构决定）
            // pathRewrite: {
            //   '^/api': '/rest/api', // 例如，将 /api 重写为 /rest/api
            // },
            // 如果你后端的SSL证书是自签名的，需要关闭证书验证（仅用于开发！）
            // secure: false,
        })
    );
};
