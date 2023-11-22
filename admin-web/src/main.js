import './assets/main.css'

import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import router from '@/router/index'
import App from './App.vue'

let app = createApp(App);
app.use(ElementPlus, {
    locale: zhCn,
})
app.use(router)
app.mount('#app')
