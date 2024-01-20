export function formatDate(date, format) {
    var o = {
        "M+": date.getMonth() + 1,
        "d+": date.getDate(),
        "H+": date.getHours(),
        "m+": date.getMinutes(),
        "s+": date.getSeconds(),
        "q+": Math.floor((date.getMonth() + 3) / 3),
        "S": date.getMilliseconds()
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return format;
}

/**
 * 获取当前月期号
 * 返回格式: YYYY-mm
 * */
export function getCurrentMonthIssue(date) {
    let month = parseInt(date.getMonth() + 1);
    if (month < 10) {
        month = '0' + month
    }
    return date.getFullYear() + "-" + month;
}

/**
 * 获取当前的日期
 * 返回格式: YYYY-mm-dd
 * */
export function getCurrentDate(date) {
    let month = parseInt(date.getMonth() + 1);
    let day = date.getDate();
    if (month < 10) {
        month = '0' + month
    }
    if (day < 10) {
        day = '0' + day
    }
    return date.getFullYear() + '-' + month + '-' + day;
}

/**
 * 获取本周的第一天
 * 返回格式: YYYY-mm-dd
 *    例子: 当日为: 2020-11-27
 *      返回日期为: 2020-11-23
 * */
export function getCurrentWeekFirstDay(date) {
    var day = date.getDay() || 7;
    return formatDate(new Date(date.getFullYear(), date.getMonth(), date.getDate() + 1 - day), "yyyy-MM-dd");
}

/**
 * 获取本周的最后一天
 * 返回格式: YYYY-mm-dd
 *    例子: 当日为: 2020-11-27
 *      返回日期为: 2020-11-29
 * */
export function getCurrentWeekLastDay(date) {
    let weekFirstDay = new Date(date - (date.getDay() - 1) * 86400000)
    let weekLastDay = new Date((weekFirstDay / 1000 + 6 * 86400) * 1000)
    let lastMonth = Number(weekLastDay.getMonth()) + 1
    if (lastMonth < 10) {
        lastMonth = '0' + lastMonth
    }
    let weekLastDays = weekLastDay.getDate();
    if (weekLastDays < 10) {
        weekLastDays = '0' + weekLastDays;
    }
    return weekFirstDay.getFullYear() + '-' + lastMonth + '-' + weekLastDays;
}

/**
 * 获取当前月的第一天
 * 返回格式: YYYY-mm-dd
 *    例子: 当日为: 2020-11-27
 *      返回日期为: 2020-11-01
 * */
export function getCurrentMonthFirstDay() {
    let date = new Date();
    date.setDate(1);
    let month = parseInt(date.getMonth() + 1);
    let day = date.getDate();
    if (month < 10) {
        month = '0' + month
    }
    if (day < 10) {
        day = '0' + day
    }
    return date.getFullYear() + '-' + month + '-' + day;
}

/**
 * 获取当前月的最后一天
 * 返回格式: YYYY-mm-dd
 *    例子: 当日为: 2020-11-27
 *      返回日期为: 2020-11-30
 * */
export function getCurrentMonthLastDay() {
    let date = new Date();
    let currentMonth = date.getMonth();
    let nextMonth = ++currentMonth;
    let nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
    let oneDay = 1000 * 60 * 60 * 24;
    let lastTime = new Date(nextMonthFirstDay - oneDay);
    let month = parseInt(lastTime.getMonth() + 1);
    let day = lastTime.getDate();
    if (month < 10) {
        month = '0' + month
    }
    if (day < 10) {
        day = '0' + day
    }
    return date.getFullYear() + '-' + month + '-' + day;
}

export function lastMonthFirst() {
    var time = new Date();
    var year = time.getFullYear();
    var month = time.getMonth();
    var day = time.getDate();
    if (month < 10) {
        month = "0" + month;
    }
    if (month == 0) {
        year = year - 1;
        month = 12;
    }
    //上个月的第一天
    var v = year + '-' + month + '-' + '0' + 1;
    return v;
}


export function lastMonthLast() {
    var time = new Date();
    var year = time.getFullYear();
    var month = time.getMonth();
    var day = time.getDate();
    if (month < 10) {
        month = "0" + month;
    }
    if (month == 0) {
        year = year - 1;
        month = 12;
    }

    var lastday = new Date(year, month, 0).getDate();
    //上个月的最后一天
    var v2 = year + '-' + month + '-' + lastday;
    return v2;
}