<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add recipe to plan</title>
    <%@ include file="/fragments-after-logging/head.jsp" %>
</head>

<body>
<%@ include file="/fragments-after-logging/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="/fragments-after-logging/sidePanel.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <form action="/app/recipe/add-to-plan" method="post">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h4>${errorNotCompleteData}</h4>
                            <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                        </div>
                    </div>

                    <div class="schedules-content">
                        <div class="form-group row">
                            <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                Wybierz plan
                            </label>
                            <div class="col-sm-3">
                                <select class="form-control" id="choosePlan" name="planId">
                                    <option value="">Wybierz...</option>
                                    <c:forEach items="${plans}" var="plan">
                                        <option value="${plan.id}">${plan.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="chooseMealName" class="col-sm-2 label-size col-form-label">
                                Nazwa posiłku
                            </label>
                            <div class="col-sm-3">
                                <select class="form-control" id="chooseMealName" name="mealName">
                                    <option value="">Wybierz...</option>
                                    <option value="Śniadanie">Śniadanie</option>
                                    <option value="Obiad">Obiad</option>
                                    <option value="Kolacja">Kolacja</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="number" class="col-sm-2 label-size col-form-label">
                                Numer posiłku
                            </label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" value="" id="number"
                                       placeholder="Numer posiłku" name="mealNumber">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="recipie" class="col-sm-2 label-size col-form-label">
                                Przepis
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="recipie" name="recipeId">
                                    <option value="">Wybierz...</option>
                                    <c:forEach items="${recipes}" var="recipe">
                                        <option value="${recipe.id}">${recipe.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="day" class="col-sm-2 label-size col-form-label">
                                Dzień
                            </label>
                            <div class="col-sm-2">
                                <select class="form-control" id="day" name="dayId">
                                    <option value="">Wybierz...</option>
                                    <c:forEach items="${days}" var="day">
                                        <option value="${day.id}">${day.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="/fragments-after-logging/jsCode.jsp" %>
</body>
</html>
