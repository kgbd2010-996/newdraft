var vue = new Vue({     //创建一个Vue的实例
    el:"#app",  //挂载点是id="app"的地方
    data:{
        keyword:'',
        logined:true, //判断是否登录
        programs:{
            programContent:'',
            programStatus:''
        },
        firms:{},
        user:{
            user_id:'',
            user_web_name: '13840084349',
            user_role:''
        },
        news:{},
        newsList:{}
    },
    methods:{
        search: function() {
            if (this.keyword === '') {
                // window.location = "/mint/search"
                console.log(this.keyword);
            } else {
                let keyword = encodeURIComponent(this.keyword)
                // window.location = "/mint/search?keyword="+keyword
                console.log(keyword);
            }
        },
        logout: function () {
            this.$confirm('确认退出吗?', '提示', {
            }).then(() => {
                //跳转到统一登陆
                window.location = "index.html"
            }).catch(() => {

            });
        },
        getUser: function(token){
            var _this=this;
            axios.post("http://localhost/isLogin",{
                params:{
                    token: token
                }
            }).then(function (response) {
                _this.user = response.data;
            }).catch(function (err) {
                console.log(err)
            })
        },
        resetToken: function (token) {
            axios.get("http://localhost/resetToken").then(function (response) {

            })
        },
        refresh_user:function(){
            //从sessionStorage中取出当前用户
            let activeUser= sessionStorage.getItem("user");
            //取出sessionStorage中的token
            let token = sessionStorage.getItem("token");
            //通过token获取缓存中的用户
            this.getUser(token);
            console.log(this.user)
            if(activeUser !=null && this.user.user_id !=null && this.user.user_id == activeUser.uid){
                this.logined = true;
                this.user = activeUser;
                this.resetToken(token)
            }
            if (activeUser == null || token == null) {
                this.logined =false;
            }
        },
        firm: function(index){
            return "firm"+index+1;
        },
        //初始化数据
        init: function () {
            var _this = this;
            axios.get("http://localhost:80/indexInit").then(function (response) {
                var map = JSON.parse(response.data.data)
                console.log(map)
                _this.programs = map.programsContent;
                console.log(_this.programs.programContent)
                _this.firms = map.firmsByProgramId;
                console.log(_this.firms)
                _this.news = map.newestNews;
                console.log(_this.news)
                _this.newsList = map.newsByCreatedTime;
                console.log(_this.newsList)
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
        this.init();
    },
    mounted(){
        //刷新当前用户
       // this.refresh_user()


    }
})