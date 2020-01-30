var vue = new Vue({     //创建一个Vue的实例
    el:"#app",  //挂载点是id="app"的地方
    data:{
        programs:{
            programContent:'',
            programStatus:''
        },
        firms:{}
    },
    methods:{
        //初始化数据
        jigou: function () {
            var _this = this;
            axios.get("http://39.105.20.225:8081/indexInit").then(function (response) {
                var map = JSON.parse(response.data.data)
                console.log(map)
                _this.programs = map.programsContent;
                console.log(_this.programs.programContent)
                _this.firms = map.firmsByProgramId;
                console.log(_this.firms)
            })
        },
        //时间格式化函数，此处仅针对yyyy-MM-dd hh:mm:ss 的格式进行格式化
        dateFormat:function(time) {
            var date=new Date(time);
            var year=date.getFullYear();
            /* 在日期格式中，月份是从0开始的，因此要加0
             * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
             * */
            var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
            var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
            var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
            var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
            var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
            // 拼接
            return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        }
    },
    created() {
        this.jigou();
    },
    mounted(){

    }
})