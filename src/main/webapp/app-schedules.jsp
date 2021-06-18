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

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">LISTA PLANÓW</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <a href="${pageContext.request.contextPath}/app/recipe/plan/add" class="btn btn-success rounded-4 pt-0 pb-0 pr-4 pl-4">Dodaj przepis do planu</a>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <a href="${pageContext.request.contextPath}/app/plan/add" class="btn btn-success rounded-4 pt-0 pb-0 pr-4 pl-4">Dodaj nowy plan</a>
                        </div>
                    </div>

                    <div class="schedules-content">
                        <table class="table border-bottom">
                            <thead>
                                <tr class="d-flex">
                                    <th class="col-1">ID</th>
                                    <th class="col-2">NAZWA</th>
                                    <th class="col-7">OPIS</th>
                                    <th class="col-2 center">AKCJE</th>
                                </tr>
                            </thead>
                            <tbody class="text-color-lighter">

                            <c:forEach var="allPlanList" items="${requestScope.allPlans}">
                                <tr class="d-flex">
                                    <th scope="row" class="col-1">
                                        ${allPlanList.id}
                                    </th>
                                    <td class="col-2">
                                        ${allPlanList.name}
                                    </td>
                                    <td class="col-7">
                                            ${allPlanList.description}
                                    </td>
                                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                        <a href="${pageContext.request.contextPath}/app/plan/delete?id=${allPlanList.id}" class="btn btn-danger rounded-4 text-light m-1">Usuń</a>
<%--                                        <a href="${pageContext.request.contextPath}/app/plan/details"--%>
                                        <a href="${pageContext.request.contextPath}/app/plan/details?id=${allPlanList.id}"
                                           class="btn btn-info rounded-4 text-light m-1">Szczegóły</a>
                                        <a href="${pageContext.request.contextPath}/app/plan/edit?id=${allPlanList.id}"
                                           class="btn btn-warning rounded-4 text-light m-1">Edytuj</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>