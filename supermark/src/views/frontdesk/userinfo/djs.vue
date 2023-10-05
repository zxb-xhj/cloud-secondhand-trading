<template>
    <span :endTime="endTime" :time="time" :callback="callback" :endText="endText">
        <slot>
            {{ content }}
        </slot>
    </span>
</template>
<script>
export default {
    data() {
        return {
            content: '',
            endTime: new Date().getHours(new Date() + 1),
            endText: '已结束',
            callback: '',
            time: ''
        }
    },
    props: {
        endTime: {
            type: String,
            default: ''
        },
        callback: {
            type: String,
            default: ''
        },
        time: {
            type: String,
            default: ''
        },

    },
    mounted() {

    },
    created() {
        // this.getTime()
        this.countdowm(this.endTime)
    },
    methods: {
        getTime() {

        },
        countdowm(timestamp) {
            // this.$http.get("/api/cloud-order/order/time/" + this.orderSn).then(ress => {
                console.log("endTime  "+this.orderSn)
                this.endTime = this.time.replace('T', ' ');
                // var res1 = this.format(this.endTime, "yyyy-MM-dd hh:mm:ss");
                var res = this.format(new Date(), "yyyy-MM-dd hh:mm:ss");
                
                // this.countdowm(this.endTime)
                var res = this.format(new Date(), "yyyy-MM-dd hh:mm:ss");
                console.log(res)
                // console.log(res1)
                var rawdatet = this.stringToDate(this.endTime);
                var rawdate = this.stringToDate(res);
                // 日期加上30分钟
                var addSecond = 1800;
                var i = 0;
                i++;
                var resDate = new Date(rawdatet.setSeconds(rawdatet.getSeconds() + addSecond));
                console.log("rawdatet"+rawdatet)
                console.log("rawdate"+rawdate)
                // var res = this.format(resDate, "yyyy-MM-dd hh:mm:ss");
                let self = this;
                let timer = setInterval(function () {
                    let nowTime = new Date();
                    let endTime = i == 1 ? resDate : new Date(timestamp);
                    // console.log(endTime)
                    let t = endTime.getTime() - nowTime.getTime();
                    if (t > 0) {
                        let day = Math.floor(t / 86400000);
                        let hour = Math.floor((t / 3600000) % 24);
                        let min = Math.floor((t / 60000) % 60);
                        let sec = Math.floor((t / 1000) % 60);
                        hour = hour < 10 ? "0" + hour : hour;
                        min = min < 10 ? "0" + min : min;
                        sec = sec < 10 ? "0" + sec : sec;
                        let format = '';
                        if (day > 0) {
                            format = `${day}天${hour}小时${min}分${sec}秒`;
                        }
                        if (day <= 0 && hour > 0) {
                            format = `${hour}小时${min}分${sec}秒`;
                        }
                        if (day <= 0 && hour <= 0) {
                            format = `${min}分${sec}秒`;
                        }
                        self.content = format;
                    } else {
                        clearInterval(timer);
                        self.content = self.endText;
                        self._callback();
                    }
                }, 1000);
            // });

        },
        _callback() {
            if (this.callback && this.callback instanceof Function) {
                this.callback(...this);
            }
        },
        stringToDate(strDate) {
            var tempStrs = strDate.split(" ");

            var dateStrs = tempStrs[0].split("-");
            var year = parseInt(dateStrs[0], 10);
            var month = parseInt(dateStrs[1], 10) - 1;
            var day = parseInt(dateStrs[2], 10);

            var timeStrs = tempStrs[1].split(":");
            var hour = parseInt(timeStrs[0], 10);
            var minute = parseInt(timeStrs[1], 10);
            var second = parseInt(timeStrs[2], 10);

            var date = new Date(year, month, day, hour, minute, second);
            return date;
        },
        format(date, fmt) {
            var o = {
                "M+": date.getMonth() + 1, //月份
                "d+": date.getDate(), //日
                "h+": date.getHours(), //小时
                "m+": date.getMinutes(), //分
                "s+": date.getSeconds(), //秒
                "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                "S": date.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ?
                        (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }
    }
}
</script>