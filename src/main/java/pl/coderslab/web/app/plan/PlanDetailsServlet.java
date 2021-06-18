
package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DisplayPlan;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name ="PlanDetailsServlet", urlPatterns ={"/app/plan/details"})
public class PlanDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        DisplayPlan displayPlan = recipePlanDao.getDetailsForPlan(id);
        if(displayPlan.getPlan().getName() != null) {
            request.setAttribute("recipePlanDao", displayPlan);
            request.setAttribute("plan", displayPlan.getPlan());
            request.setAttribute("recipPlanId", id);
        }else {
            Plan recipePlan = new PlanDao().readById(id);
            request.setAttribute("plan", recipePlan);
        }
        request.getRequestDispatcher("/app-details-schedules.jsp").forward(request, response);

    }
}

