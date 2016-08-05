<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<title>登录</title>
</head>
<body>
    <div class="container">
        <div class="page-logo">
            <img alt="" src="images/bozlogo.png">
        </div>
        <div class="page-container" id="reg_resp_err">
            <div class="page-box">
                <div class="page-box-inner">
                    <i class="page-box-error-icon"></i>
                    <h4>出现了一个问题</h4>
                    <div id="reg_resp_err_content" class="page-box-error-content"></div>
                </div>
            </div>
        </div>
        <div class="page-container">
            <div class="page-box">
                <div class="page-box-inner">
                    <form id="loginForm" method="post" onsubmit="return false;">
                        <h1>登录</h1>
                        <div class="page-box-row">
                            <label for="ap_username">用户名或手机号或邮箱</label> <input type="text" maxlength="50" id="ap_username" name="username"
                                class="form-control" tabindex="1" autoComplete="off">
                            <div id="username_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="username_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <label for="ap_password">密码</label> <input type="password" maxlength="1024" id="ap_password" name="password"
                                class="form-control" tabindex="2">
                            <div id="password_warning" class="page-box-warning">
                                <i class="page-box-warning-icon"></i>
                                <div id="password_warning_content" class="page-box-warning-content"></div>
                            </div>
                        </div>
                        <div class="page-box-row">
                            <input type="button" id="ap_login_submit" class="btn btn-primary btn-block page-box-submit" value="登录" tabindex="3"
                                onclick="LOGIN.login();">
                        </div>
                        <div class="page-box-row">
                            <div class="page-box-divider"></div>
                        </div>
                        <div class="page-box-row">
                            <span>还没有账户？&nbsp;</span><a href="/page/register">免费注册</a>
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
                        $("#ap_password").keyup(function() {
                            $("#password_warning").hide();
                        });
                    });
                    var redirectUrl = "${redirect}";
                    var LOGIN = {
                        checkInput : function() {
                            var checkResult = true;
                            //$("#username_warning").hide();
                            //$("#password_warning").hide();
                            //不能为空检查
                            if ($("#ap_username").val() == "") {
                                $("#username_warning_content").text("用户名不能为空");
                                $("#username_warning").show();
                                $("#ap_username").focus();
                                checkResult = false;
                            }
                            if ($("#ap_password").val() == "") {
                                $("#password_warning_content").text("密码不能为空");
                                $("#password_warning").show();
                                $("#ap_password").focus();
                                checkResult = false;
                            }
                            return checkResult;
                        },
                        doLogin : function() {
                            $.post("/user/login", $("#loginForm").serialize(), function(data) {
                                if (data.status == 200) {
                                    if (redirectUrl == "") {
                                        location.href = "http://localhost:8080/main";
                                    } else {
                                        location.href = redirectUrl;
                                    }
                                } else {
                                    $("#reg_resp_err_content").text("登录失败，原因是：" + data.msg);
                                    $("#reg_resp_err").show();
                                    $("#ap_username").select();
                                }
                            });
                        },
                        login : function() {
                            if (this.checkInput()) {
                                this.doLogin();
                            }
                        }
                    };
                </script>
</body>
</html>