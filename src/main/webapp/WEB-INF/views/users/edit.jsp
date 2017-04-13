<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url var="loginUrl" value="/j_spring_security_check"/>
<spring:url var="logoutUrl" value="/j_spring_security_logout"/>
<spring:url value="/users?form" var="addUserUrl"/>
<spring:url value="/users" var="UserUrl"/>

<html>
<head>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet" type="text/css">
    <title>Example</title>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">TUNING SHOP</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Главная</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">О нас<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">История</a></li>
                        <li><a href="#">Доставка</a></li>
                        <li><a href="#">Оплата</a></li>
                        <li><a href="#">Гарантия</a></li>
                        <li><a href="#">Контакты</a></li>
                    </ul>
                </li>
                <li><a href="/basket/">Корзина</a></li>
            </ul>

            <sec:authorize access="isAuthenticated()">
                <form name="logoutForm" action="${logoutUrl}" method="post" class="navbar-form navbar-right">
                    <div class="form-group">
                        Welcome
                    </div>
                    <div class="form-group">
                        <sec:authentication property="principal.username"/>
                    </div>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-primary" href="/admin" role="button">Go to admin page</a>
                    </sec:authorize>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button name="submit" type="submit" value="Logout" class="btn btn-success">Logout</button>
                </form>
            </sec:authorize>

            <sec:authorize access="isAnonymous()">
                <div id="login" class="form-group ${error != null ? 'has-error' : ''}">
                    <form name="loginForm" action="${loginUrl}" method="post" class="navbar-form navbar-right">
                        <small>${message}</small>
                        <small>${error}</small>
                        <div class="form-group">
                            <input type="text" name="j_username" placeholder="Login" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" name="j_password" placeholder="Password" class="form-control">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button name="submit" type="submit" value="Login" class="btn btn-success">Sign in</button>
                        <a class="btn btn-primary" href="/registration" role="button">Register</a>
                    </form>
                </div>
            </sec:authorize>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Change </a></li>
                <li><a href="${UserUrl}">Users</a></li>
                <li><a href="#">Cars</a></li>
                <li><a href="#">Catalogs</a></li>
                <li><a href="#">Products</a></li>
            </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">USERS</h1>


            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>

            </div>

            <h2 class="sub-header">Section title</h2>
            <a class="btn btn-primary btn-lg" href="${addUserUrl}" role="button">Add new User</a>
            <br/>
            <h2 class="sub-header">All Users</h2>
            <div class="list-group">
                <c:forEach var="user" items="${users}">
                    <a href="/users/${user.id}" class="list-group-item">
                            ${user.id}
                            ${user.username}
                        <small>Roles:</small>
                        <c:forEach var="role" items="${user.roles}">
                            <small>${role.name}</small>
                        </c:forEach>

                    </a>
                </c:forEach>

            </div>

        </div>
    </div>
</div>


<footer class="footer">
    <div class="container-fluid">
        <p class="text-center">&copy; 2017 TuningShop.by</p>
    </div>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>