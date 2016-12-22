<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 12.12.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">
    <title>Basket</title>
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
            <a class="navbar-brand" href="index.jsp">TUNING SHOP</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Главная</a></li>
                <li><a href="selectedByCatalogAndCar.do?carId=&catalogId=">Каталог</a></li>
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
                <li><a href="basket.jsp">Корзина</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="nav col-sm-3 col-md-2 list-group">
            <c:forEach var="catalog" items="${catalogList}">
                <a href="selectedByCatalogAndCar.do?catalogId=${catalog.id}&carId=${selectedCar.id}" class="list-group-item">${catalog.name}</a>
            </c:forEach>
        </div>
        <div class="col-sm-9 col-md-10 main">
            <h1 class="page-header">PRODUCTS IN BASKET UNIT</h1>
            <h2 class="sub-header">Section title</h2>
            <%--Show products in basket--%>
            <ul class="list-group">
                <c:forEach var="productInBasket" items="${productsInBasket}">
                    <li class="list-group-item">
                        <div class="media">
                            <a class="pull-left" href="#">
                                <img class="media-object" src="..." alt="Picture">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    <a href="product.do?id=${productInBasket.key.id}">${productInBasket.key.name}</a>
                                </h4>

                                ${productInBasket.value} pcs

                                <a href="productAddToBasket.do?id=${productInBasket.key.id}">+</a>
                                <a href="productRemoveFromBasket.do?id=${productInBasket.key.id}">-</a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container-fluid">
        <p class="text-center">&copy; 2016 TuningShop.by</p>
    </div>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
