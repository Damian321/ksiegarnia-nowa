<%-- 
    Document   : home
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

        <link href="../config/css/bootstrap.min.css" rel="stylesheet">
        <link href="../config/css/style.css" rel="stylesheet">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../config/img/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../config/img/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../config/img/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="../config/img/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="../config/img/favicon.png">

        <script type="text/javascript" src="../config/js/jquery.min.js"></script>
        <script type="text/javascript" src="../config/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../config/js/scripts.js"></script>

        <title>Home page:</title>

    </head>
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
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="../home.htm?styl=1">Strona główna</a>
                        </div>
                        <ul class="nav navbar-nav navbar-left">
                            <li>
                                <a href="../category.htm">Książki</a>
                            </li>
                        </ul>
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <form class="navbar-form navbar-right" role="search" action="../search.htm" method="POST">
                                <div class="form-group">
                                    <input type="text" name="s" class="form-control">
                                </div> <button type="submit" class="btn btn-default">Wyszukaj</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <c:choose>
                                <c:when test="${pageContext.request.userPrincipal.authorities == '[ROLE_PRACOWNIK]'}">
                                    <li>
                                        <a href="../pracownik/panel.htm">Panel Pracownika</a>
                                    </li>         
                                </c:when>
                                    <c:when test="${pageContext.request.userPrincipal.authorities == '[ROLE_ADMIN]'}">
                                    <li>
                                        <a href="../admin/panel.htm">Panel Administratora</a>
                                    </li>         
                                </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${pageContext.request.userPrincipal.authorities != null}">
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.request.userPrincipal.name}<strong class="caret"></strong></a>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <a href="../user/koszyk.htm">Koszyk</a>
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
                                            <a href="../login.htm">Logowanie</a>
                                        </li>
                                        <li>
                                            <a href="../rejestracja.htm">Rejestracja</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>

                    </nav>                 

                    <h3>
                        Panel administratora
                    </h3>
                    <br>
                    <div class="tabbable" id="tabs-635136">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#panel-791964" data-toggle="tab">Książki</a>
                            </li>
                            <li>
                                <a href="#panel-692676" data-toggle="tab">Pracownicy</a>
                            </li>
                            <li>
                                <a href="#panel-692671" data-toggle="tab">Użytkownicy</a>
                            </li>
                            <li>
                                <a href="#panel-692670" data-toggle="tab">News</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="panel-791964">

                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>
                                                Tytuł
                                            </th>
                                            <th>
                                                Autor
                                            </th>
                                            <th>
                                                Cena
                                            </th>
                                            <th>
                                                ISBN
                                            </th>
                                            <th>

                                            </th>
                                            <th>

                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="ksiazka" items="${lista_ksiazek}">                           
                                            <tr>
                                                <td>
                                                    <a href="../book.htm?id=${ksiazka.id}">${ksiazka.tytul}</a>
                                                </td>
                                                <td>
                                                    ${ksiazka.autor}
                                                </td>
                                                <td>
                                                    ${ksiazka.cena}
                                                </td>
                                                <td>
                                                    ${ksiazka.ISBN}
                                                </td>
                                                <td>
                                                    <a href="panel.htm?edycja&id_ksiazki=${ksiazka.id}">edycja</a>
                                                </td>
                                                <td>
                                                    <a href="panel.htm?usun&id_ksiazki=${ksiazka.id}">usuń</a>
                                                </td>
                                            </tr>
                                        </c:forEach>                          
                                    </tbody>
                                </table>
                                <a href="panel.htm?dodaj=ksiazka">Dodaj Książkę</a>
                            </div>
                            <div class="tab-pane" id="panel-692676">
                                <table class="table">
                                    <thead>
                                        <tr>                                         
                                            <th>
                                                Login
                                            </th>
                                            <th>
                                                Hasło
                                            </th>
                                            <th>
                                                Aktywność konta
                                            </th>
                                            <th>

                                            </th>
                                            <th>

                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="pracownik" items="${lista_pracownikow}">                           
                                            <tr>
                                                <td>
                                                    ${pracownik.username}
                                                </td>
                                                <td>
                                                    ${pracownik.password}
                                                </td>
                                                <td>
                                                    <div class="checkbox">
                                                        <input type="checkbox" disabled="disabled"<c:if test="${pracownik.enabled==true}"> checked </c:if>/> 
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a href="panel.htm?edycja&username=${pracownik.username}">edycja</a>
                                                </td>
                                                <td>
                                                    <a href="panel.htm?usun&username=${pracownik.username}">usuń</a>
                                                </td>
                                            </tr>
                                        </c:forEach>                          
                                    </tbody>
                                </table>
                                <a href="panel.htm?dodaj=pracownik">Dodaj pracownika</a>
                            </div>
                            <div class="tab-pane" id="panel-692671">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>
                                                Login
                                            </th>
                                            <th>
                                                Hasło
                                            </th>
                                            <th>
                                                Aktywność konta
                                            </th>                                         
                                            <th>

                                            </th>
                                            <th>

                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${lista_uzytkownikow}">                           
                                            <tr>
                                                <td>
                                                    ${user.username}
                                                </td>
                                                <td>
                                                    ${user.password}
                                                </td>
                                                <td>
                                                    <div class="checkbox">
                                                        <input type="checkbox" disabled="disabled"<c:if test="${user.enabled==true}"> checked </c:if>/> 
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a href="panel.htm?edycja&username=${user.username}">edycja</a>
                                                </td>
                                                <td>
                                                    <a href="panel.htm?usun&username=${user.username}">usuń</a>
                                                </td>
                                            </tr>
                                        </c:forEach>                          
                                    </tbody>
                                </table>
                                <a href="panel.htm?dodaj=user">Dodaj użytkownika</a>
                            </div>
                            <div class="tab-pane" id="panel-692670">
                                <form role="form" method="POST" action="panel.htm">
                                    <div class="form-group">
                                        <label for="exampleInputPassword2">News</label><TEXTAREA class="form-control" Name="tresc_newsa" ROWS=20 ></TEXTAREA>
                                        <button type="submit" class="btn btn-default">Dodaj</button>                             
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <br><br>
                    <c:choose>
                        <c:when test="${not empty edit_user}">
                            <form role="form" method="POST" action="panel.htm">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Login</label><input type="text" class="form-control" id="exampleInputEmail1" name=" " disabled="disabled" value="${edit_user.username}"/>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Hasło</label><input type="text" value="${edit_user.password}" class="form-control" id="exampleInputPassword1" name="password"/>
                                </div>
                                Aktywność konta: <input type="checkbox" name="active" value="true"/> 
                                <br>
                                <button type="submit" class="btn btn-default">Edytuj</button>
                                <input type="hidden" name="edycja" value="" />
                                <input type="hidden" name="username" value="${edit_user.username}" />
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </c:when>
                        <c:when test="${not empty dodaj_usera}">
                            <form role="form" method="POST" action="panel.htm">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Login</label><input type="text" class="form-control" id="exampleInputEmail1" name="username"/>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Hasło</label><input type="text" value="" class="form-control" id="exampleInputPassword1" name="password"/>
                                </div>
                                Aktywność konta: <input type="checkbox" name="active" value="true"/> 
                                <br>
                                <button type="submit" class="btn btn-default">Dodaj</button>
                                <input type="hidden" name="dodaj_usera" value="${dodaj_usera}" />  
                                <input type="hidden" name="dodaj" value="1" /> 
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </c:when>
                        <c:when test="${not empty edit_book}">
                            <form role="form" method="GET" action="panel.htm">                           
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Tytuł</label><input type="text" class="form-control" id="exampleInputEmail1" name="tytul" value="${edit_book.tytul}"/>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Autor</label><input type="text" value="${edit_book.autor}" class="form-control" id="exampleInputPassword1" name="autor"/>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword2">Opis</label><TEXTAREA class="form-control" Name="opis" ROWS=7 >${edit_book.opis}</TEXTAREA>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword3">Cytat</label><TEXTAREA class="form-control" Name="cytat" ROWS=4>${edit_book.cytat}</TEXTAREA>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword4">Autor cytatu</label><input type="text" value="${edit_book.autor_cytatu}" class="form-control" id="exampleInputPassword4" name="autor_cytatu"/>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword5">Liczba stron</label><input type="text" value="${edit_book.liczba_stron}" class="form-control" id="exampleInputPassword5" name="liczba_stron"/>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword6">ISBN</label><input type="text" value="${edit_book.ISBN}" class="form-control" id="exampleInputPassword6" name="" disabled="disabled"/>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword7">Cena</label><input type="text" value="${edit_book.cena}" class="form-control" id="exampleInputPassword7" name="cena"/>
                            </div>
                                                     
                            <button type="submit" class="btn btn-default">Edytuj</button>
                            <input type="hidden" name="edycja" value="" />
                             <input type="hidden" name="isbn" value="${edit_book.ISBN}" />
                            <input type="hidden" name="id_ksiazki" value="${edit_book.id}" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                        </c:when>
                    </c:choose>

                </div>
            </div>
        </div>
    </body>
</html>
