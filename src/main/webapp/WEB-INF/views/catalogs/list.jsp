<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 27.02.2017
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">
    <title>Example</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
    <div class="nav col-sm-3 col-md-2 list-group">
        <c:if test="${not empty catalogs}">
            <p>    Id                Catalog</p>
            <br/>
                <c:forEach items="${catalogs}" var="catalog">
                      <a href="#" class="list-group-item">  ${catalog.id}  ${catalog.name}</a>
                </c:forEach>
        </c:if>
    </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>