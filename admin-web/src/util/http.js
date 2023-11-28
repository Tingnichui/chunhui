import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";
import {getToken} from "@/util/auth";
import router from "@/router";

export function createBaseAxios() {
    return axios.create({
        baseURL: "http://localhost:8080/api", // 基础请求地址
        timeout: 60 * 1000, // 请求超时设置
        withCredentials: false, // 跨域请求是否需要携带 cookie
    })
}

// 创建 axios 请求实例
const http = createBaseAxios();

// 添加请求拦截器
http.interceptors.request.use(function (config) {
    // 放入token
    config.headers['token'] = getToken()
    return config;
}, function (error) {
    return Promise.reject(error);
});

// 添加响应拦截器
http.interceptors.response.use(function (response) {
    const res = response.data;
    if (res.code !== "0000") {
        if (res.message === "未登录") {
            ElMessageBox.confirm('您未登录，点击确认前往登录', '消息提示', {
                confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning',
            }).then(() => {
                router.push('/login')
            })
        } else {
            ElMessage.error(res.message)
        }
        return Promise.reject(res.message)
    }
    return res;
}, function (error) {
    ElMessage.error('请求异常')
    return Promise.reject(error);
});


export default http;



