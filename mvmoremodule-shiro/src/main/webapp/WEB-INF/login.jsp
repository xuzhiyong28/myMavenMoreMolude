<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="baseurl" value="${pageContext.request.contextPath}/"/>
<html>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script>
    function tj(){
        $("#myform").submit();
    }
</script>
<body>
<form id="myform" action="http://localhost:8080/login" method="post">
    用户名 ：<input type="text" name="username"/> <br>
    密码 : <input type="text" name="password"/> <br>
    <input type="button" value="提交" onclick="tj()" />
</form>
</body>
</html>
