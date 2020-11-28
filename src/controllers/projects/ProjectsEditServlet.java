package controllers.projects;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Project;
import utils.DBUtil;

/**
 * Servlet implementation class ProjectsEditServlet
 */
@WebServlet("/projects/edit")
public class ProjectsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Project p = em.find(Project.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("project", p);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("project_id", p.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/projects/edit.jsp");
        rd.forward(request, response);
    }

}
