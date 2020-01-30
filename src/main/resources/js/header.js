var head = new Vue({     //创建一个Vue的实例
    el:"#head",  //挂载点是id="app"的地方
    data:{
        keyword:'',
        logined:false, //判断是否登录
        programs:{
            programContent:'',
            programStatus:''
        },
        user:{}
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
                sessionStorage.removeItem("user");
                sessionStorage.removeItem("token");
                //跳转到首页
                window.location = "index.html"
            }).catch(() => {

            });
        },
        resetToken: function (token) {
            var params = new URLSearchParams();
            params.append('token', token);
            axios.post("http://localhost:8081/resetToken",params).then((response)=> {
                console.log(response.data.data)
            })
        },
        refresh_user:function(){
            //从sessionStorage中取出当前用户
            let activeUser= JSON.parse(sessionStorage.getItem("user"));
            //取出sessionStorage中的token
            let token = sessionStorage.getItem("token");
            //判断是不是空
            if (activeUser == null || token == null) {
                this.logined =false;
            }
            //通过token获取缓存中的用户
            var params = new URLSearchParams();
            params.append('token', token);
            axios.post("http://localhost:8081/isLogin",params).then((response)=>{
                this.user = JSON.parse(response.data.data);
                if(this.user.userId == activeUser.userId){
                    console.log("判断成功")
                    this.logined = true;
                    this.user = activeUser;
                }
                console.log(this.user)
            }).catch(function (err) {
                console.log(err)
            })
            this.resetToken(token);
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

    },
    mounted(){
        //刷新当前用户
        this.refresh_user()

    }
})