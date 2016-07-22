<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>HOME</title>
<style type="text/css">
body {
    margin: 0px;
    height: 100%;
    overflow: hidden;
    background: -moz-radial-gradient(center, ellipse cover, rgba(255, 197, 120, 1) 0%, rgba(251, 157, 35, 1) 100%);
    background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%, rgba(255, 197, 120, 1)),
        color-stop(100%, rgba(251, 157, 35, 1)));
    background: -webkit-radial-gradient(center, ellipse cover, rgba(255, 197, 120, 1) 0%, rgba(251, 157, 35, 1) 100%);
    background: -o-radial-gradient(center, ellipse cover, rgba(255, 197, 120, 1) 0%, rgba(251, 157, 35, 1) 100%);
    background: -ms-radial-gradient(center, ellipse cover, rgba(255, 197, 120, 1) 0%, rgba(251, 157, 35, 1) 100%);
    background: radial-gradient(ellipse at center, rgba(255, 197, 120, 1) 0%, rgba(251, 157, 35, 1) 100%);
}

canvas {
    height: 100%;
}
</style>
</head>
<body>
    <canvas id='canvas'></canvas>
</body>
<script type="text/javascript" src="script/matrix/drawworm.js"></script>
<script type="text/javascript" src="script/matrix/matrix2d.js"></script>
</html>