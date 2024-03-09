<template>
  <div class="page" style="height: 100%">

    <el-card>
      <div style="height: 300px">
        上课统计
        <div ref="classStats" style="height: 100%;"></div>
      </div>
      <div>
        合同统计
      </div>
      <div>
        会员生日
      </div>
      <div>
        合同即将到期统计
      </div>
    </el-card>
  </div>
</template>

<script>

import {getClassStatsByDay} from "@/api/jljs-stats";

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
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      this.classStatsInit()

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


    }
  }
}

</script>

<style scoped>
</style>