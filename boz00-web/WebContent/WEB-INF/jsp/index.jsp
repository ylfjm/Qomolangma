<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Welcome to boz's page.</title>
<link type="text/css" rel="stylesheet" href="css/textillate/animate.css">
<link type="text/css" rel="stylesheet" href="css/textillate/style.css">
<style type="text/css">
body {
    overflow: hidden;
    user-select: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -o-user-select: none;
    -ms-user-select: none;
}
</style>
</head>
<body>
    <div class="container fade in">
        <div class="viewport">
            <div class="tlt" style="margin-top: 50px; font-size: 18px;">
                <ul class="texts">
                    <li>温馨提示：为保证最佳的效果，请在 IE10+、Chrome、Firefox 和 Safari 等现代浏览器中浏览。</li>
                </ul>
            </div>
            <form class="form"></form>
        </div>
    </div>
    <div id="canvas"></div>
</body>
<script type="text/javascript" src="script/jquery/jquery-3.1.0.min.js"></script>
<!-- 球组件 -->
<script type="text/javascript" src="script/ball-pool/protoclass.js"></script>
<script type="text/javascript" src="script/ball-pool/box2d.js"></script>
<script type="text/javascript" src="script/ball-pool/Main.js"></script>
<!-- 动态字体组件 -->
<script type="text/javascript" src="script/textillate/jquery.fittext.js"></script>
<script type="text/javascript" src="script/textillate/jquery.lettering.js"></script>
<script type="text/javascript" src="script/textillate/jquery.textillate.js"></script>
<script type="text/javascript" src="script/textillate/highlight.min.js"></script>
<script>
    $(function() {

        var $form = $('.form'), $viewport = $('.viewport');

        var getFormData = function() {
            var data = {
                "loop" : true,
                "in" : {
                    callback : null
                },
                "out" : {
                    callback : null
                }
            };
            data["in"]["type"] = "sequence";
            data["in"]["effect"] = "fadeInLeftBig";
            data["out"]["type"] = "shuffle";
            data["out"]["effect"] = "hinge";
            data["in"].shuffle = false;
            data["in"].reverse = false;
            data["in"].sync = false;
            data["out"].shuffle = true;
            data["out"].reverse = false;
            data["out"].sync = false;
            return data;
        };

        setTimeout(function() {
            $('.fade').addClass('in');
        }, 250);

        var $tlt = $viewport.find('.tlt').on('start.tlt', null).on('inAnimationBegin.tlt', null).on('inAnimationEnd.tlt', null).on(
                'outAnimationBegin.tlt', null).on('outAnimationEnd.tlt', null).on('end.tlt', null);

        $form.on('change', function() {
            var obj = getFormData();
            $tlt.textillate(obj);
        }).trigger('change');

    });
</script>
</html>