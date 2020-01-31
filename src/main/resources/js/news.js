var vue = new Vue({     //创建一个Vue的实例
    el:"#app",  //挂载点是id="app"的地方
    data:{
        url:sessionStorage.getItem('url'),
        newsList:{},
        currentPage:1,
        pagesize:5,
        pageNews:{},
        totalPage:0
    },
    methods:{
        search: function() {
            if (this.keyword === '') {
                // window.location = "/mint/search"
                console.log(this.keyword);
            } else {
                let keyword = encodeURIComponent(this.keyword);
                // window.location = "/mint/search?keyword="+keyword
                console.log(keyword);
            }
        },
        //初始化数据
        xinwen: function () {
            var params = new URLSearchParams();
            params.append("currentPage",this.currentPage);
            params.append("pagesize",this.pagesize);
            axios.post(this.url+"getPageNews",params).then((response) =>{
                var map = JSON.parse(response.data.data);
                console.log(map);
                this.pageNews = map.pageNews;
                this.totalPage = map.total;
                console.log("总页数："+this.totalPage)
            })
        },
        getNews: function (newsId) {
            localStorage.setItem("newsId",newsId);
            window.location.href = "newsContent.html"
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
            return year+"-"+month+"-"+day;
        },
        // 初始页currentPage、初始每页数据数pagesize和数据data
        handleSizeChange: function (size) {
            this.pagesize = size;
            console.log(this.pagesize)  //每页下拉显示数据
            this.xinwen();
        },
        handleCurrentChange: function(currentPage){
            this.currentPage = currentPage;
            console.log(this.currentPage)  //点击第几页
            this.xinwen();
        }
    },
    created() {
        this.xinwen();
    },
    mounted(){

    }
})