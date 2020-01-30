$(function(){

	$.ajaxSetup(
		{
			async: false
		});

	var tab = 'account_number';
	var codenumber;
	// 选项卡切换
	$(".account_number").click(function () {
		$('.tel-warn').addClass('hide');
		tab = $(this).attr('class').split(' ')[0];
		checkBtn();
        $(this).addClass("on");
        $(".message").removeClass("on");
        $(".form2").addClass("hide");
        $(".form1").removeClass("hide");
    });
	// 选项卡切换
	$(".message").click(function () {
		$('.tel-warn').addClass('hide');
		tab = $(this).attr('class').split(' ')[0];
		checkBtn();
		$(this).addClass("on");
        $(".account_number").removeClass("on");
		$(".form2").removeClass("hide");
		$(".form1").addClass("hide");
		
    });

	$('#num').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#pass').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#num2').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri-code').keyup(function(event) {
		$('.code-warn').addClass('hide');
		checkBtn();
	});

	// 按钮是否可点击
	function checkBtn()
	{
		$(".log-btn").off('click');
		if (tab == 'account_number') {
			var inp = $.trim($('#num').val());
			var pass = $.trim($('#pass').val());
			if (inp != '' && pass != '') {
				if (!$('.code').hasClass('hide')) {
					code = $.trim($('#veri').val());
					if (code == '') {
						$(".log-btn").addClass("off");
					} else {
						$(".log-btn").removeClass("off");
						sendBtn();
					}
				} else {
					$(".log-btn").removeClass("off");
						sendBtn();
				}
			} else {
				$(".log-btn").addClass("off");
			}
		} else {
			var phone = $.trim($('#num2').val());
			var code2 = $.trim($('#veri-code').val());
			if (phone != '' && code2 != '') {
				$('.tel-warn').addClass('hide');
				$('.code-warn').addClass('hide');
				$(".log-btn").removeClass("off");
				sendBtn();
			} else {
//				if(phone == ''){
//					$('.tel-warn').removeClass('hide').text('请输入手机号');
//				}
//				if(code2 == ''){
//					$('.code-warn').removeClass('hide').text('请输入验证码');
//				}
				$(".log-btn").addClass("off");
			}
		}
	}

	function checkAccount(username){
		if (username == '') {
			$('.num-err').removeClass('hide').find("em").text('请输入账户');
			return false;
		} else {
			$('.num-err').addClass('hide');
			return true;
		}
	}

	function checkPass(pass){
		if (pass == '') {
			$('.pass-err').removeClass('hide').text('请输入密码');
			return false;
		} else {
			$('.pass-err').addClass('hide');
			return true;
		}
	}

	function checkCode(code){
		if (code == '') {
			//$('.code-warn').removeClass('hide').text('请输入验证码');
			return false;
		}else if(code == codenumber){
			$('.code-warn').addClass('hide');
			alert("我可以登陆了！！！");
			return true;
		}else {
			//$('.code-warn').addClass('hide');
			$('.code-warn').removeClass('hide');
			return false;
		}
	}

	function checkPhone(phone){
		var status = true;
		if (phone == '') {
			$('.num2-err').removeClass('hide').find("em").text('请输入手机号');
			return false;
		}
		var param = /^1[34578]\d{9}$/;
		if (!param.test(phone)) {
			// globalTip({'msg':'手机号不合法，请重新输入','setTime':3});
			$('.num2-err').removeClass('hide');
			$('.num2-err').text('手机号不合法，请重新输入');
			return false;
		}

		$.post(getRootPath() + "/isPhoneExists",{
			phone:phone
		},function(data){
			if (data.code == 2001) {
				$('.num2-err').addClass('hide'); //手机号存在
				status = true;
			} else {
				$('.num2-err').removeClass('hide').text(data.message); //手机号不存在
				status = false;

			}
		},'json').fail(function(){
			status = false;
		});
		return status;
	}

	function checkPhoneCode(pCode){
		if (pCode == '') {
			$('.error').removeClass('hide').text('请输入验证码');
			return false;
		} else {
			$('.error').addClass('hide');
			return true;
		}
	}

	// 登录点击事件
	function sendBtn(){
		
		if (tab == 'account_number') {
			$(".log-btn").click(function(){
				// var type = 'phone';
				var inp = $.trim($('#num').val());
				var pass = $.trim($('#pass').val());
				if (checkAccount(inp) && checkPass(pass)) {
					//var ldata = {unknowLogin:inp,pwd:pass};
/*					if (!$('.code').hasClass('hide')) {
						code = $.trim($('#veri').val());
						if (!checkCode(code)) {
							return false;
						}
						ldata.code = code;
					}*/

					$.post(getRootPath() + "/doLogin",{
						'userPhone':inp,
						'pwd':pass
					},function(data){
						if (data.code == 2001) {
							// globalTip({'msg':'登录成功!','setTime':3,'jump':true,'URL':'http://www.ui.cn'});
							var map = JSON.parse(data.data);
							console.log(map);
							sessionStorage.setItem("user",JSON.stringify(map.user));
							sessionStorage.setItem("token",map.token);
							$(location).attr('href','../index.html');
						} else if(data.code == 2002){
							$(".log-btn").off('click').addClass("off");
							$('.pass-err').removeClass('hide').find('em').text(data.message);
							$('.pass-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
							return false;
						} else if(data.code == 2003){
							$(".log-btn").off('click').addClass("off");
							$('.num-err').removeClass('hide').find('em').text(data.message);
							$('.num-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
							return false;
						}
					},'json');
				} else {
					return false;
				}
			});
		} else {
			$(".log-btn").click(function(){
				// var type = 'phone';
				var phone = $.trim($('#num2').val());
				var pcode = $.trim($('#veri-code').val());

				if (checkPhone(phone) && checkCode(pcode)) {

					//doPhoneLogin
					$.post(getRootPath() + "/doPhoneLogin",{
						phone:phone,
						code:pcode
					},function(data){
						if (data.code == 2001) {
							//globalTip({'msg':'登录成功!','setTime':3,'jump':true,'URL':'http://www.ui.cn'});
							var map = JSON.parse(data.data);
							console.log(map);
							sessionStorage.setItem("user",JSON.stringify(map.user));
							sessionStorage.setItem("token",map.token);
							$(location).attr('href', '../index.html');
						} else if(data.code == 2003) {
							$(".log-btn").off('click').addClass("off");
							$('.num2-err').removeClass('hide').text(data.message);
							return false;
						}
					},'json');
				} else {
					$(".log-btn").off('click').addClass("off");
					// $('.tel-warn').removeClass('hide').text('登录失败');
					return false;
				}
			});
		}
	}

	function getRootPath() {
	    /*//获取当前网址，如： http://localhost:8088/test/test.jsp
	    var curPath = window.document.location.href;
		//获取主机地址之后的目录，如： test/test.jsp
		var pathName = window.document.location.pathname;
		var pos = curPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8088
	    var localhostPath = curPath.substring(0, pos);
		return (localhostPath);*/
	    return "http://localhost:8081";
	}
	
	// 登录的回车事件
	$(window).keydown(function(event) {
    	if (event.keyCode == 13) {
    		$('.log-btn').trigger('click');
    	}
    });


	$(".form-data").delegate(".send","click",function () {
		var phone = $.trim($('#num2').val());
		if (checkPhone(phone)) {
			$.post(getRootPath() + "/sendRandomNumber",{
				phone:phone
			},function(data){
				if (data.code == 2002) {
					$('.num2-err').removeClass('hide').text("验证码发送失败");
				} else {
					$('.num2-err').removeClass('hide').text("验证码发送成功");
					codenumber = JSON.parse(data.data).verCode;
					console.log(codenumber);
				}
			},'json');
	       	var oTime = $(".form-data .time"),
			oSend = $(".form-data .send"),
			num = parseInt(oTime.text()),
			oEm = $(".form-data .time em");
		    $(this).hide();
		    oTime.removeClass("hide");
		    var timer = setInterval(function () {
		   	var num2 = num-=1;
	            oEm.text(num2);
	            if(num2==0){
	                clearInterval(timer);
	                oSend.text("重新发送验证码");
				    oSend.show();
	                oEm.text("120");
	                oTime.addClass("hide");
	            }
	        },1000);
		}
    });



});