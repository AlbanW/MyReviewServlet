<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <jsp:useBean id="error" class="java.lang.String" scope="request"/>
</head>
<body style="height: 100vh; display: flex; justify-content: center; align-content: center; align-items: center;" class="bg-dark">
    <div class="card">
        <div class="card-header">
            Me connecter
        </div>
        <div class="card-body">
            <form method="post" action="/review/login">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                            ${error}
                    </div>
                </c:if>
                <label for="field:pseudo" class="badge bg-primary m-1">Pseudo</label>
                    <input class="form-control m-1 mb-0" type="text" placeholder="Votre pseudo" id="field:pseudo" name="pseudo"><br>
                <label for="field:password" class="badge bg-primary m-1">Mot de passe</label>
                    <input class="form-control m-1 mb-0" placeholder="Votre mot de passe" type="password" id="field:password" name="password"><br>
                <input type="submit" class="btn btn-outline-primary btn-sm m-1 mt-0">
            </form>
        </div>
    </div>
</body>
</html>
