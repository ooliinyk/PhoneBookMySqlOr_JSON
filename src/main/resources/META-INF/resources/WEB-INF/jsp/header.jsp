<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='css/app.css' />" rel="stylesheet"/>

</head>

<body>


<!-- Static navbar -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Online Library</a>
        </div>
        <div id="navbar" >
            <ul class="nav navbar-nav">
                <li class="active"><a href="<c:url value='/listAdmin' />">Home</a></li>
                <li>
                    <%--<c:choose>--%>
                    <%--<c:when test="${pageContext.request.userPrincipal.name != null}">--%>
                        <%--<a> Hi ${pageContext.request.userPrincipal.name}</a>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<a href="<c:url value='/login' />">Login</a>--%>
                    <%--</c:otherwise>--%>

                    <a href="<c:url value='/addPhoneBookItem' />">Add item</a>

                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Add <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/addPhoneBookItem' />">Again add item:)</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="">Default <span class="sr-only">(current)</span></a></li>
                <li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <a> Hi ${pageContext.request.userPrincipal.name}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value='/login' />">Login</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li><a href="<c:url value='/registration' />">Registration</a></li>
                <li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <a href="<c:url value='/logout' />">Logout</a>
                        </c:when>
                    </c:choose>

                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
</nav>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

<script src="<c:url value='js/bootstrap.js'/>"></script>


</body>
</html>