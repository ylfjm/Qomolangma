<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap-theme.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap-dialog.css">
<script type="text/javascript" src="script/jquery/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="script/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="script/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="script/bootstrap/bootstrap-dialog.js"></script>
<script type="text/javascript" src="script/boz.js"></script>
</head>
<style type="text/css">
.table-default {
    width: 1000px;
    margin: 0 auto;
}
.middle {
    height: 500px;
}
.menuLeft {
    width: 150px;
}
</style>
<body>
    <table class="table table-bordered text-center table-default">
        <tr>
            <td colspan="2"><tiles:insertAttribute name="header" /></td>
        </tr>
        <tr class="middle">
            <td class="menuLeft text-right"><tiles:insertAttribute name="menu" /></td>
            <td class="text-left"><tiles:insertAttribute name="body" /></td>
        </tr>
        <tr>
            <td colspan="2"><tiles:insertAttribute name="footer" /></td>
        </tr>
    </table>
</body>
</html>