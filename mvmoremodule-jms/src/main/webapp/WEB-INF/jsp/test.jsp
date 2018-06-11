<%@ page language="java" pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WebSocket client</title>
    <script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
        $(function(){
            var obj = {};
            obj.ymth = "13123213";
            $.ajax({
                url:'/socket/testjson',
                dataType:'json',
                contentType : 'application/json',
                type: "post",
                data : JSON.stringify(obj),
                success:function(ret){

                }
            });
        });
    </script>
</head>
<body>
xuzhiyong
</body>
</html>