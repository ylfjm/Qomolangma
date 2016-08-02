<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<title>注册</title>
</head>
<body>
    <div class="container">
        <div class="page-container">
            <div class="page-box">
                <div class="page-box-inner">
                    <form id="registerForm" method="post" onsubmit="return false;">
                        <h1>创建账户</h1>
                        <div class="page-box-row">
                            <label for="ap_username">用户名</label> <input type="text" maxlength="50" id="ap_username" name="username"
                                class="form-control" tabindex="1" autoComplete="off">
                            <div id="username_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="username_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <label for="ap_phone_number">手机号码</label> <input type="text" maxlength="14" id="ap_phone_number" name="phone"
                                class="form-control" tabindex="2">
                            <div id="phone_number_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="phone_number_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <label for="ap_mail_address">邮箱地址</label> <input type="email" maxlength="64" id="ap_mail_address" name="email"
                                class="form-control" tabindex="3">
                            <div id="mail_address_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="mail_address_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <label for="ap_password">密码</label> <input type="password" maxlength="1024" id="ap_password" name="password"
                                placeholder="至少6个字符" class="form-control" tabindex="4">
                            <div id="password_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="password_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <label for="ap_password_check">再次输入密码</label> <input type="password" maxlength="1024" id="ap_password_check"
                                class="form-control" tabindex="5">
                            <div id="password_check_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="password_check_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <input type="button" id="ap_register_submit" class="btn btn-primary btn-block page-box-submit" value="创建账户" tabindex="6"
                                onclick="REGISTER.reg();">
                        </div>
                        <div class="page-box-row">
                            <div class="page-box-divider"></div>
                        </div>
                        <div class="page-box-row">
                            <span>已有账户？</span><a href="/page/login">登录</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
                    $(function() {
                        $("#ap_username").keyup(function() {
                            $("#username_warning").hide();
                        });
                        $("#ap_phone_number").keyup(function() {
                            $("#phone_number_warning").hide();
                        });
                        $("#ap_mail_address").keyup(function() {
                            $("#mail_address_warning").hide();
                        });
                        $("#ap_password").keyup(function() {
                            $("#password_warning").hide();
                        });
                        $("#ap_password_check").keyup(function() {
                            $("#password_check_warning").hide();
                        });
                    });
                    var REGISTER = {
                        param : {
                            //单点登录系统的url
                            surl : ""
                        },
                        inputcheck : function() {
                            var checkResult = true;
                            //$("#username_warning").hide();
                            //$("#phone_number_warning").hide();
                            //$("#mail_address_warning").hide();
                            //$("#password_warning").hide();
                            //$("#password_check_warning").hide();
                            //不能为空检查
                            if ($("#ap_username").val() == "") {
                                $("#username_warning_content").text("用户名不能为空");
                                $("#username_warning").show();
                                $("#ap_username").focus();
                                checkResult = false;
                            }
                            if ($("#ap_phone_number").val() == "") {
                                $("#phone_number_warning_content").text("手机号不能为空");
                                $("#phone_number_warning").show();
                                $("#ap_phone_number").focus();
                                checkResult = false;
                            }
                            if ($("#ap_mail_address").val() == "") {
                                $("#mail_address_warning_content").text("邮箱地址不能为空");
                                $("#mail_address_warning").show();
                                $("#ap_mail_address").focus();
                                checkResult = false;
                            }
                            if ($("#ap_password").val() == "") {
                                $("#password_warning_content").text("密码不能为空");
                                $("#password_warning").show();
                                $("#ap_password").focus();
                                checkResult = false;
                            }
                            //密码检查
                            if ($("#ap_password").val() != $("#ap_password_check").val()) {
                                $("#password_check_warning_content").text("确认密码和密码不一致，请重新输入！");
                                $("#password_check_warning").show();
                                $("#ap_password_check").select();
                                $("#ap_password_check").focus();
                                checkResult = false;
                            }
                            return checkResult;
                        },
                        beforeSubmit : function() {
                            //检查用户是否已经被占用
                            $.ajax({
                                url : REGISTER.param.surl + "/user/check/" + escape($("#ap_username").val()) + "/1?r=" + Math.random(),
                                success : function(data) {
                                    if (data.data) {
                                        //检查手机号是否存在
                                        $.ajax({
                                            url : REGISTER.param.surl + "/user/check/" + $("#ap_phone_number").val() + "/2?r=" + Math.random(),
                                            success : function(data) {
                                                if (data.data) {
                                                    REGISTER.doSubmit();
                                                } else {
                                                    //alert("此手机号已经被注册！");
                                                    $("#ap_phone_number").select();
                                                }
                                            }
                                        });
                                    } else {
                                        //alert("此用户名已经被占用，请选择其他用户名");
                                        $("#ap_username").select();
                                    }
                                }
                            });

                        },
                        doSubmit : function() {
                            $.post("/user/register", $("#registerForm").serialize(), function(data) {
                                if (data.status == 200) {
                                    //alert('用户注册成功，请登录！');
                                    REGISTER.login();
                                } else {
                                    //alert("注册失败！");
                                }
                            });
                        },
                        login : function() {
                            location.href = "/page/login";
                            return false;
                        },
                        reg : function() {
                            if (this.inputcheck()) {
                                this.beforeSubmit();
                            }
                        }
                    };
                </script>
</body>
</html>