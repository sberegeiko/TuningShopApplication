<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet" type="text/css">
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
            <a class="navbar-brand" href="/">TUNING SHOP</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Главная</a></li>
                <li><a href="/">Каталог</a></li>
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
            <a href="" class="list-group-item">MENU</a>
            <a href="" class="list-group-item">Login</a>
            <a href="" class="list-group-item">Logout</a>
            <a href="" class="list-group-item">Settings</a>
        </div>

        <div class="col-sm-9 col-md-10 main">
            <h1 class="page-header">PRODUCTS IN BASKET UNIT</h1>
            <h2 class="sub-header">Section title</h2>

            <ul class="list-group">
                <c:forEach var="product" items="${basket.products}">
                    <li class="list-group-item">
                        <div class="media">
                            <a class="pull-left" href="#">
                                <img class="media-object" src="..." alt="Picture">
                            </a>
                            <div class="media-body">
                                <div class="media-left">
                                    <h4 class="media-heading">
                                        <a href="/cars/${carId}/products/${product.key.id}">
                                                ${product.key.name}
                                                ${product.key.producer}
                                                ${product.key.productCode}
                                                ${product.key.producerProductCode}
                                        </a>
                                    </h4>
                                    <small>For cars:</small>
                                    <c:forEach var="car" items="${product.key.cars}">
                                        <small>${car.brand} ${car.model}</small>
                                    </c:forEach>
                                    <br/>
                                    <br/>

                                    <div class="btn-group" role="group">
                                        <a href="/basket/add/${product.key.id}" class="btn btn-default btn-sm"
                                           role="button">
                                            <span class="glyphicon glyphicon-plus"></span>
                                        </a>

                                        <button type="button" class="btn btn-default btn-sm">
                                                ${product.value} pcs
                                        </button>
                                        <a href="/basket/subtract/${product.key.id}" class="btn btn-default btn-sm"
                                           role="button">
                                            <span class="glyphicon glyphicon-minus"></span>
                                        </a>
                                    </div>
                                </div>

                            </div>
                            <div class="media-right">
                                <a href="/basket/remove/${product.key.id}">Удалить</a>
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
        <p class="text-center">&copy; 2017 TuningShop.by</p>
    </div>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>