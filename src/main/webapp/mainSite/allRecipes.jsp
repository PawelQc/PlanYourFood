<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>All Recipes</title>
    <%@ include file="/fragments/head.jsp" %>
</head>

<body>
<%@ include file="/fragments/header.jsp" %>

<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>
<section class="mr-4 ml-4">
    <table class="table">
        <thead>
        <tr class="d-flex text-color-darker">
            <th scope="col" class="col-1">ID</th>
            <th scope="col" class="col-5">NAZWA</th>
            <th scope="col" class="col-5">OPIS</th>
            <th scope="col" class="col-1">AKCJE</th>
        </tr>
        </thead>
        <tbody class="text-color-lighter">
        <c:forEach items="${recipes}" var="recipe" varStatus="count">
            <tr class="d-flex">
                <th scope="row" class="col-1">${count.count}</th>
                <td class="col-5">
                        ${recipe.name}
                </td>
                <td class="col-5"> ${recipe.description}
                </td>
                <td class="col-1"><a href="/recipes/details?recipeId=${recipe.id}"
                                     class="btn btn-info rounded-0 text-light m-1">Szczegóły</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<%@ include file="/fragments/footer.jsp" %>

<%@ include file="/fragments/jsCode.jsp" %>

</body>
</html>