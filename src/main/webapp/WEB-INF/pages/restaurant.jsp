<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <jsp:useBean id="restaurant" type="fr.myreview.modele.Restaurant" scope="request"/>
    <jsp:useBean id="avis" type="java.util.Collection<fr.myreview.modele.Avis>" scope="request"/>
    <jsp:useBean id="avisMean" type="java.lang.Integer" scope="request"/>
</head>
<body style="position:relative; display: flex; justify-content: center; align-items: center; align-content: center;" class="bg-dark">
<div class="card" style="width: 800px; margin-top: 5%;">
    <div class="card-header" style="text-align: left">
        <span style="float:right;" class="badge bg-primary">${avis.size()} avis ⭐️</span> ${restaurant.nom}
    </div>
    <div class="card-body">
        <div class="container">
            <div class="row">
                <div class="col">
                    <ul>
                        <li>Nom: ${restaurant.nom}</li>
                        <li>Description: ${restaurant.description}</li>
                        <li>Retrouvez nous ici : ${restaurant.adresse}</li>
                    </ul>
                    <hr>
                    <p><i>Avis:</i></p>
                    <c:if test="${empty avis}">
                            <p class="badge bg-danger">Pas encore d'avis pour ce restaurant.</p>
                    </c:if>
                    <c:if test="${!empty avis}">
                        <p class="badge bg-success">Moyenne des avis: ${avisMean}</p>
                    </c:if>
                    <c:forEach items="${avis}" var="avis">
                        <div class="card card-body bg-secondary text-light m-5 mt-1 mb-0">
                            <p>${avis.commentaire}</p>
                            <p>Note: ${avis.note}</p>
                            <blockquote><i>- ${avis.compte.username}</i></blockquote>
                        </div>
                    </c:forEach>
                </div>
                <div class="col">
                    <div class="card card-body bg-secondary text-light">
                        <p>Notez nous</p>
                        <form method="post" action="<c:url value="/review/restaurant?id=${restaurant.id}"/>">
                            <label for="field:note">Votre note</label><input style="margin:0;" class="form-control" value="3" id="field:note" name="note" type="number" min="0.0" max="5.0"><br>
                            <label for="field:comment">Votre commentaire</label><textarea style="margin:0;" class="form-control" id="field:comment" name="commentaire">Pas mal !</textarea>
                            <input type="submit" value="Envoyer">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a style="position: fixed; bottom: 15px; right: 15px;" class="btn btn-lg btn-success m-2" href="${pageContext.request.contextPath}/review/home">< Retour</a>

</body>
</html>
