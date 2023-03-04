<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: albanweill
  Date: 04/03/2023
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <jsp:useBean id="restaurant" scope="request" type="java.util.Collection<fr.myreview.modele.Restaurant>"/>
    <jsp:useBean id="user" scope="session" type="fr.myreview.modele.Compte"/>
    <jsp:useBean id="success" class="java.lang.String" scope="request"/>
</head>
<body style="height: 100vh; display: flex; justify-content: center; align-content: center; align-items: center;" class="bg-dark">
    <div class="card" style="margin-left: 25%; margin-right: 25%;">
        <div class="card-header">
            Listes des restaurants
            <span style="float:right;" class="badge bg-success">${restaurant.size()} restaurant(s) 🧑‍🍳</span>
        </div>
        <div class="card-body">
            <p>Bonjour ${user.username}, comment allez-vous ?</p>
            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                        ${success}
                </div>
            </c:if>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Description</th>
                    <th scope="col">Adresse</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${restaurant}" var="resto">
                    <tr>
                        <td>${resto.nom}</td>
                        <td>${resto.adresse}</td>
                        <td>${resto.description}</td>
                        <td><a class="btn btn-primary btn-sm" href="<c:url value="/review/restaurant?id=${resto.id}"/>">Notez ce Resto 📝</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
