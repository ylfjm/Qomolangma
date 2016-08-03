<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<script type="text/javascript" src="js/jquery/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
</head>
<body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="menu" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
</body>
</html>