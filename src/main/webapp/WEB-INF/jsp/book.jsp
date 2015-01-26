<%-- 
    Document   : book
    Created on : 2014-12-05, 12:21:52
    Author     : Damian
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="config/css/bootstrap.min.css" rel="stylesheet">
        <link href="config/css/style.css" rel="stylesheet">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="config/img/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="config/img/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="config/img/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="config/img/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="config/img/favicon.png">

        <script type="text/javascript" src="config/js/jquery.min.js"></script>
        <script type="text/javascript" src="config/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="config/js/scripts.js"></script>

        <title>Home page</title>

    </head>
    <body>
       <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <c:url value="/j_spring_security_logout" var="logoutUrl" />

        <!-- csrt for log out-->
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" 
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="page-header">
                        <h1>
                            Księgarnia <small><span>Czytaj dużo albo wcale</span></small>
                        </h1>
                    </div>
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="home.htm?styl=1">Strona główna</a>
                        </div>
                        <ul class="nav navbar-nav navbar-left">
                            <li>
                                <a href="category.htm">Książki</a>
                            </li>
                        </ul>
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <form class="navbar-form navbar-right" role="search" action="search.htm" method="POST">
                                <div class="form-group">
                                    <input type="text" name="s" class="form-control">
                                </div> <button type="submit" class="btn btn-default">Wyszukaj</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <c:choose>
                                <c:when test="${pageContext.request.userPrincipal.authorities == '[ROLE_PRACOWNIK]'}">
                                    <li>
                                        <a href="pracownik/panel.htm">Panel Pracownika</a>
                                    </li>         
                                </c:when>
                                    <c:when test="${pageContext.request.userPrincipal.authorities == '[ROLE_ADMIN]'}">
                                    <li>
                                        <a href="admin/panel.htm">Panel Administratora</a>
                                    </li>         
                                </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${pageContext.request.userPrincipal.authorities != null}">
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.request.userPrincipal.name}<strong class="caret"></strong></a>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <a href="user/koszyk.htm">Koszyk</a>
                                                </li>
                                                   <li>
                                                    <a href="user/historia.htm?username=${pageContext.request.userPrincipal.name}">Historia</a>
                                                </li>
                                                <li class="divider">
                                                </li>
                                                <li>
                                                    <a href="javascript:formSubmit()"> Wyloguj</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="divider"></li>
                                        <li>
                                            <a href=""></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="login.htm">Logowanie</a>
                                        </li>
                                        <li>
                                            <a href="rejestracja.htm">Rejestracja</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>

                    </nav>                 
                    <div class="container">
                        <div class="row clearfix">
                            <div class="col-md-12 column">
                                <ul class="breadcrumb">
                                    <c:forEach var="rodzic" items="${rodzice}">
                                        <li>
                                            <a href="category.htm?id=${rodzic.id}">
                                                ${rodzic.nazwa}
                                            </a>
                                            <span class="divider"></span>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="row clearfix">
                                    <div class="col-md-2 column">
                                        <img alt="140x140" src="http://lorempixel.com/140/140/" class="img-thumbnail" />
                                    </div>
                                    <div class="col-md-8 column">
                                        <dl class="dl-horizontal">
                                            <dt>
                                            Tytuł
                                            </dt>
                                            <dd>
                                                ${ksiazka.tytul}
                                            </dd>
                                            <dt>
                                            Autor
                                            </dt>
                                            <dd>
                                                ${ksiazka.autor}
                                            </dd>
                                               <dt>
                                            ISBN
                                            </dt>
                                            <dd>
                                                ${ksiazka.ISBN}
                                            </dd>
                                            <dd>
                                                <br>
                                            </dd>
                                            <dt>
                                            Liczba stron
                                            </dt>
                                            <dd>
                                                ${ksiazka.liczba_stron}
                                            </dd>
                                            <dt>
                                        </dl>
                                    </div>
                                    <div class="col-md-2 column">
                                        <form action="user/koszyk.htm" method="POST">
                                            <button type="submit" class="btn btn-primary">Dodaj do koszyka</button> 
                                            <input type="hidden" name="id" value="${ksiazka.id}" >
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <input type="hidden" name="username" value=${pageContext.request.userPrincipal.name} />
                                        </form>
                                        <h1> </h1>
                                        </div>
                                </div>
                                <br>
                                <blockquote>
                                    <p>
                                        ${ksiazka.cytat}
                                    </p> <small>${ksiazka.autor_cytatu}</small>
                                </blockquote>
                                <br>
                                <p>
                                    ${ksiazka.opis}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
