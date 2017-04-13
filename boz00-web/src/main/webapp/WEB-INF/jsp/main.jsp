<%@ page language="java" contentType="text/html; charset=utf-8"%>
<title>BOZ的个人博客</title>
<div class="base-main-top">
    <div class="container">
        <h1>BOZ的博客</h1>
        <p>欢迎来到我的博客，希望您可以找到有帮助的信息。</p>
    </div>
</div>
<div class="base-main-content">
    <div class="container">
        <h1>首页内容即将呈现。。。</h1>
    </div>
    <script type="text/javascript">
        var TT = {
            checkLogin : function() {
                var _ticket = $.cookie("BOZ_TOKEN");
                if (!_ticket) {
                    return;
                }
                $.ajax({
                    url : "/user/token/" + _ticket,
                    dataType : "jsonp",
                    type : "GET",
                    success : function(data) {
                        if (data.status == 200) {
                            var username = data.data.username;
                            var html = "欢迎&nbsp;" + username + "&nbsp;<a href='#' onclick='TT.logout();'>[退出]</a>";
                            $("#loginbar").html(html);
                        } else {
                            $("#loginbar").html("<a href='/page/register'>[免费注册]</a>&nbsp;<a href='/page/login'>[登录]</a>");
                        }
                    }
                });
            },
            logout : function() {
                if (!confirm("您确定要退出吗？")) {
                    return;
                }
                var _ticket = $.cookie("BOZ_TOKEN");
                if (!_ticket) {
                    return;
                }
                $.ajax({
                    url : "/user/logout/" + _ticket,
                    dataType : "jsonp",
                    type : "GET",
                    success : function(data) {
                        if (data.status == 200) {
                            var html = "<a href='/page/register'>[免费注册]</a>&nbsp;<a href='/page/login'>[登录]</a>";
                            $("#loginbar").html(html);
                        } else {
                            $("#loginbar").html(data.data);
                        }
                    }
                });
            }
        }
        $(function() {
            // 查看是否已经登录，如果已经登录查询登录信息
            TT.checkLogin();

        });
    </script>
</div>