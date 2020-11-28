package controllers.projects;

import java.io.IOException;
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
 * Servlet implementation class ProjectsShowServlet
 */
@WebServlet("/projects/show")
public class ProjectsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


       // Project project_title = em.find(Project.class, request.getParameter("title"));
        Project p = em.find(Project.class, Integer.parseInt(request.getParameter("id")));

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Report> reports = em.createNamedQuery("getAllProjectReports", Report.class)
                                  .setParameter("project", p)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long reports_count = (long)em.createNamedQuery("getProjectReportsCount", Long.class)
                                     .setParameter("project", p)
                                     .getSingleResult();

        em.close();

        request.setAttribute("project", p);
        request.setAttribute("reports", reports);
        request.setAttribute("reporst_count", reports_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/projects/show.jsp");
        rd.forward(request, response);
    }

}
