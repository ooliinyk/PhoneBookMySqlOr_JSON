<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='css/app.css' />" rel="stylesheet"></link>

</head>

<body>
<div class="container">
    <jsp:include page="header.jsp"/>


    <!-- Main  -->
    <c:choose>
        <c:when test="${fail != null}">
            <div class="well">
                <div class="error">
                        ${fail}
                </div>
            </div>
        </c:when>
    </c:choose>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Items </span></div>

        <div class="tablecontainer">


            <div class="col-lg-6 ">
                <form:form method="POST" action="finPhoneBookItemByName" commandName="phoneBookItem">


                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Find by Name</label>

                        <div class="col-md-7">
                            <form:input type="text" path="name" id="name"/>
                            <input type="submit" value="Find">

                            <div class="has-error">
                                <form:errors type="text" path="name"/>
                            </div>

                        </div>
                    </div>


                </form:form>
            </div>
            <div class="col-lg-6 ">
                <form:form method="POST" action="finPhoneBookItemBySurname" commandName="phoneBookItem">


                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Find by Surname</label>

                        <div class="col-md-7">
                            <form:input type="text" path="surname" id="surname"/>
                            <input type="submit" value="Find">

                            <div class="has-error">
                                <form:errors type="text" path="surname"/>
                            </div>

                        </div>
                    </div>


                </form:form>
            </div>
            <div class="col-lg-6 ">
                <form:form method="POST" action="finPhoneBookItemByMobPhone" commandName="phoneBookItem">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Find by MOB</label>

                        <div class="col-md-7">
                            <form:input type="text" path="mobPhone" id="style"/>
                            <input type="submit" value="Find">

                            <div class="has-error">
                                <form:errors type="text" path="mobPhone"/>
                            </div>

                        </div>
                    </div>


                </form:form>
            </div>


        </div>


        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>
                    <th>mob_phone</th>
                    <th>home_phone</th>
                    <th>address</th>
                    <th>email</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${phoneBookItems}" var="phoneBookItem">
                    <tr>
                        <td>${phoneBookItem.id}</td>
                        <td>${phoneBookItem.name}</td>
                        <td>${phoneBookItem.surname}</td>
                        <td>${phoneBookItem.patronymic}</td>
                        <td>${phoneBookItem.mobPhone}</td>
                        <td>${phoneBookItem.homePhone}</td>
                        <td>${phoneBookItem.address}</td>
                        <td>${phoneBookItem.email}</td>
                        <td><a href="<c:url value='/edit-phoneBook-${phoneBookItem.id}' />"
                               class="btn btn-success custom-width">EDIT</a>
                        </td>
                        <td><a href="<c:url value='/delete-phoneBook-${phoneBookItem.id}' />"
                               class="btn btn-danger ">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<%--<div class="well">--%>
<%--<a href="<c:url value='/addbook' />">Add New User</a>--%>
<%--</div>--%>

</div>
<!-- /container -->


</body>
</html>