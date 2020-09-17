package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Grade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade/delete")
public class GradeRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // odbieramy parametr od użytkownika
        String identyfikator = req.getParameter("gradeId");
        if(identyfikator == null || identyfikator.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/student/list");
            // jeśli nie otrzymałem parametru, to przekieruję z powrotem na listę
            return;
        }
        // parsujemy string na liczbę
        Long identyfikatorLong = Long.parseLong(identyfikator);

        EntityDao dao = new EntityDao();
        Grade ocena = dao.getById(Grade.class, identyfikatorLong);
        if(ocena == null){
            resp.sendRedirect(req.getContextPath() + "/student/list");
            // jeśli nie udało się znaleźć studenta, to przekieruję z powrotem na listę
            return;
        }
        // dokonanie usunięcia
        dao.delete(ocena);

        // przekierowanie z powrotem na stronę ze szczeółowymi informacjami na temat studenta
        resp.sendRedirect(req.getContextPath() + "/student/detail?identifier=" + ocena.getStudent().getId());
    }
}