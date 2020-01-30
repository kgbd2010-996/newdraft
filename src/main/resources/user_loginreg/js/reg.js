$(function(){
	$.ajaxSetup(
		{
			async: false
		});
	//$('.username-err').removeClass('hide').find("em").text("111");
	var codenumber;
	$('#username').keyup(function(event) {
		$('.username-err').addClass('hide');
		checkBtn();
	});
	$('#tel').keyup(function(event) {
		$('.tel-err').addClass('hide');
		checkBtn();
	});
	$('#veri-code').keyup(function(event) {
		$('.error').addClass('hide');
		checkBtn();
	});
	$('#passport').keyup(function(event) {
		$('.pwd-err').addClass('hide');
		checkBtn();
	});
	$('#passport2').keyup(function(event) {
		$('.confirmpwd-err').addClass('hide');
		checkBtn();
	});
	$('input:radio[name="role"]').click(function(event){
		checkBtn();
	})

	
	
	function checkUsername(username){
		if (username == '') {
			$('.username-err').removeClass('hide').find("em").text('请输入用户名');
			return false;
		} 
		var status = true;

		$.post(getRootPath() + "/isUsernameExists",{
			username:username
		},function(data){
			if (data.code == 2002) {
				$('.username-err').addClass('hide');
				status = true;
			} else {
				$('.username-err').removeClass('hide').text('用户名存在，请重新输入');
				status = false;
			}
		},'json').fail(function(){
			status = false;
		});
		return status;
	}
	
	function checkTel(tel) {
		var status = true;
		if (tel == '') {
			$('.tel-err').removeClass('hide').find("em").text('请输入手机号');
			return false;
		}
		var param = /^1[34578]\d{9}$/;
		if (!param.test(tel)) {
			// globalTip({'msg':'手机号不合法，请重新输入','setTime':3});
			$('.tel-err').removeClass('hide');
			$('.tel-err').text('手机号不合法，请重新输入');
			return false;
		}

		$.post(getRootPath() + "/isPhoneExists",{
			phone:tel
		},function(data){
			if (data.code == 2002) {
				status = true;
			} else {
				$('.tel-err').removeClass('hide').text('手机号存在，请重新输入');
				status = false;

			}
		},'json').fail(function(){
			status = false;
		});
		return status;
	}
	
	function checkCode(veri_code){
		if (veri_code == '') {
			$('.error').removeClass('hide').find("em").text('请输入验证码');
			return false;
		}else if(veri_code == codenumber){
			$('.error').addClass('hide');
			return true;
		}else {
			$('.error').removeClass('hide').text("验证码输入错误");
			return false;
		}
	}
	
	function checkPassport(passport){
		if (passport == '') {
			$('.pwd-err').removeClass('hide').find("em").text('请输入密码');
			return false;
		} else {
			$('.pwd-err').addClass('hide');
			return true;
		}
	}
	
	function checkPassport2(passport,passport2){
		if (passport2 == '') {
			$('.confirmpwd-err').removeClass('hide').find("em").text('请输入确认密码');
			return false;
		} else if(passport != passport2){
			$('.confirmpwd-err').removeClass('hide').find("em").text('两次密码不一致');
			return false;
		} else {
			$('.confirmpwd-err').addClass('hide');
			return true;
		}
	}

	function checkRole(role){
		if (passport == '') {
			$('.role-err').removeClass('hide').find("em").text('请选中一项身份');
			return false;
		} else {
			$('.role-err').addClass('hide');
			return true;
		}
	}

	// 按钮是否可点击
	function checkBtn()
	{
		//只要这些按钮都不为空 我就可以点击
		$(".lang-btn").off('click');
		var username = $.trim($('#username').val());
		var tel = $.trim($('#tel').val());
		var veri_code = $.trim($('#veri-code').val());
		var passport = $.trim($('#passport').val());
		var passport2 = $.trim($('#passport2').val());
		var role = $.trim($('input:radio[name="role"]:checked').val());

		if(username!='' && tel!='' && veri_code!='' && passport!='' && passport2!='' && role != ''){
			$(".lang-btn").removeClass("off");
			sendBtn();
		}else{
			$(".lang-btn").addClass("off");
		}
		//console.log(username + "\t" + tel + "\t" + veri_code + "\t" + passport + "\t" + passport2 + "\t" + address + "\t" + email);
	}
	function sendBtn(){
		$(".lang-btn").click(function(){
			var username = $.trim($('#username').val());
			var tel = $.trim($('#tel').val());
			var veri_code = $.trim($('#veri-code').val());
			var passport = $.trim($('#passport').val());
			var passport2 = $.trim($('#passport2').val());
			var role = $.trim($('input:radio[name="role"]:checked').val());

			if(checkUsername(username) & checkTel(tel) & checkCode(veri_code) & checkPassport(passport) & checkPassport2(passport,passport2) & checkRole(role)){
				//发起注册请求
				$.post(getRootPath() + "/register",{
					userWebName:username,
					userPhone:tel,
					userPassword:passport,
					userRole:role
				},function(data){
					if (data.code == 2001) {
						alert("注册成功");
						$(location).attr('href','user_loginreg/login.html');
					} else {
						alert("注册失败");
						$(location).attr('href','user_loginreg/reg.html');
					}
				},'json');
			}else{
				return false;
			}
		});
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
	
	$(".form-data").delegate(".send","click",function () {
		var tel = $.trim($('#tel').val());
		if (checkTel(tel)) {
			$.post(getRootPath() + "/sendRandomNumber",{
				phone:tel
			},function(data){
				if (data.code == 2002) {
					$('.error').removeClass('hide').text("验证码发送失败");
				} else {
					$('.error').removeClass('hide').text("验证码发送成功");
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