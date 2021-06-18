<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
</head>

<body>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="tabMenu.jsp" %>

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="${pageContext.request.contextPath}/app/plan/list" class="btn btn-success rounded-4 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${requestScope.plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${requestScope.plan.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="day" items="${requestScope.recipePlanDao.displayMap.keySet()}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">${day}</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">

                            <c:forEach var="recipePlan" items="${requestScope.recipePlanDao.displayMap.get(day)}">
                            <tr class="d-flex">
                                <td class="col-2">${recipePlan.mealName}</td>
                                <td class="col-7">${recipePlan.recipeName}</td>
                                <td class="col-1 center">
                                    <a href="${pageContext.request.contextPath}/app/plan/delete-recipe?planId=${recipePlan.id}&recipPlanId=${requestScope.recipPlanId}" class="btn btn-danger rounded-4 text-light m-1">Usuń</a>
                                </td>
                                <td class="col-2 center">
                                    <a href="${pageContext.request.contextPath}/app/recipe/details?id=${recipePlan.recipeId}"
                                       class="btn btn-info rounded-4 text-light m-1">Szczegóły</a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>