import './assets/main.css'

import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import router from '@/router/index'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import * as echarts from 'echarts';
import App from './App.vue'

let app = createApp(App);
app.use(ElementPlus, {
    locale: zhCn,
})
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

/* echart */
app.config.globalProperties.$echarts = echarts

app.use(router)
app.mount('#app')
