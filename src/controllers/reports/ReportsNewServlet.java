package controllers.reports;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Project;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/reports/new")
public class ReportsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        EntityManager em = DBUtil.createEntityManager();

        request.setAttribute("project", new Project());

        List<Project> projects = em.createNamedQuery("getAllProjects", Project.class).getResultList();

        long projects_count = (long) em.createNamedQuery("getProjectsCount", Long.class).getSingleResult();
        List<String> errors = new ArrayList<String>();
        if(projects_count == 0){
            errors.add("プロジェクトを作成してください。");
        }
        if(errors.size() > 0){
            request.setAttribute("errors", errors);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/projects/new.jsp");
            rd.forward(request, response);
        }else{
        request.getSession().setAttribute("project_list", projects);
        request.setAttribute("projects", projects);
        Report r = new Report();
        r.setReport_date(new Date(System.currentTimeMillis()));
        request.setAttribute("report", r);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
        rd.forward(request, response);
        }
    }

}
