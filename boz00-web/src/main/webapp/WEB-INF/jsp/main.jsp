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
        <h1 id="loginbar"></h1>
    </div>
    <script type="text/javascript">
                    var TT = {
                        checkLogin : function() {
                            var _ticket = $.cookie("BOZ_TOKEN");
                            if (!_ticket) {
                                return;
                            }
                            $.ajax({
                                url : "http://localhost:8081/user/token/" + _ticket,
                                dataType : "jsonp",
                                type : "GET",
                                success : function(data) {
                                    if (data.status == 200) {
                                        var username = data.data.username;
                                        var html = username + "欢迎<a href=\"\" class=\"link-logout\">[退出]</a>";
                                        $("#loginbar").html(html);
                                    } else {
                                        $("#loginbar").html("<h1>" + data.msg + "</h1>");
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