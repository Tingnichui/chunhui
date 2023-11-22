import axios from "axios";

// 创建 axios 请求实例
const http = axios.create({
    baseURL: "http://localhost:8080/api", // 基础请求地址
    timeout: 10000, // 请求超时设置
    withCredentials: false, // 跨域请求是否需要携带 cookie
});

export default http;



