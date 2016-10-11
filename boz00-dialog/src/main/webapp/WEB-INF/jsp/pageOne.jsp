<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Dialog Demo Page</title>
<style type="text/css">
body {
    overflow: hidden;
}
</style>
</head>
<body>
    <div>
        <button id="showDialog">showDialog</button>
    </div>
</body>
<script>
    $(function() {
        $("#showDialog").click(function(){
            addWindow('dialogDiv', 'showDialog', [ 500, 300 ], dialogOnHidden, dialogOnShown, -200);
        });
    });

    function dialogOnHidden() {
        
    }
    function dialogOnShown() {
        
    }
</script>
</html>