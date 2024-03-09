<template>
  <div class="page" style="height: 100%">

    <el-card>
      <div>
        <div>
          <el-calendar ref="calendar" class="test">
            <template #header="{ date }">
              <span>会员生日</span>
              <span>{{ date }}</span>
              <el-button-group>
                <el-button size="small" @click="selectDate('today')">今天</el-button>
                <el-button size="small" @click="selectDate('next-month')">
                  下个月
                </el-button>
              </el-button-group>
            </template>
            <template #date-cell="{ data }">
              <p :style="getBirthDayMember(data) ? 'color: #E64A19;' : ''">
                {{ data.day.split('-').slice(1).join('-')}}
                {{ getBirthDayMember(data) }}
              </p>
            </template>
          </el-calendar>
        </div>
      </div>
      <div style="height: 300px">
        上课统计
        <div ref="classStats" style="height: 100%;"></div>
      </div>
      <div>
        合同统计
      </div>
      <div>
        合同即将到期统计
      </div>
    </el-card>
  </div>
</template>

<script>

import {getClassStatsByDay, getMemberBirthStats} from "@/api/jljs-stats";
import {ref} from "vue";

export default {
  data() {
    return {
      classStatsOption: {
        dataZoom: [{
          type: 'inside',
          startValue: 0,
          endValue: 1,
          xAxisIndex: 0,
          filterMode: 'filter',
          show: true
        }],
        xAxis: [{
          type: 'category',
          name: '日期',
          axisTick: {
            alignWithLabel: true
          },
          gridIndex: 0,
          data: []
        }],
        yAxis: [{
          name: '节',
          gridIndex: 0
        }],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        series: [
          {
            data: [],
            type: 'bar'
          }
        ]
      },
      birthDayList: []
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      this.classStatsInit()

      // 获取会员生日列表
      getMemberBirthStats().then(res => {
        this.birthDayList = res.data
      })

    },
    getBirthDayMember(data) {
      const find = this.birthDayList.find(v => data.day.split('-').slice(1).join('/') == (v.sort.length < 5 ? '0' + v.birthDay : v.birthDay));
      if (find) {
        return find.memberName
      }
      return ''
    },
    classStatsInit() {
      getClassStatsByDay().then(res => {
        // 基于准备好的dom，初始化echarts实例
        var classStats = this.$echarts.getInstanceByDom(this.$refs.classStats);
        if (classStats == null) {
          classStats = this.$echarts.init(this.$refs.classStats)
          window.onresize = function () {
            classStats.resize()
          }
        }

        this.classStatsOption.dataZoom[0].startValue = res.data.length - 30;
        this.classStatsOption.dataZoom[0].endValue = res.data.length;
        this.classStatsOption.xAxis[0].data = [];
        this.classStatsOption.series[0].data = [];
        for (let item of res.data) {
          this.classStatsOption.xAxis[0].data.push(item.day.length == 8 ? item.day.toString().replace(/^(\d{4})(\d{2})(\d{2})$/, "$1-$2-$3") : item.day.toString().replace(/^(\d{4})(\d{2})$/, "$1-$2"));
          this.classStatsOption.series[0].data.push(item.count);
        }

        classStats.setOption(this.classStatsOption)

      })
    },
    selectDate(val) {
      const calendar = ref<CalendarInstance>(calendar);
      console.log(JSON.stringify(calendar) )
      if (!calendar.value) return
      calendar.value.selectDate(val)
    }
  }
}

</script>

<style scoped>
</style>