package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/remove")
public class StudentRemoveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identyfikator = req.getParameter("identToRemove");

        if(identyfikator==null||identyfikator.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/student/list");
            // jeśli nie otrzymałem parametru, to przekieruję z powrotem na listę
            return;
        }
        //parsujemy String na liczbę
        Long identyfikatorLong = Long.parseLong(identyfikator);

        // wyciąganie obiektu z bazy
        // w dao jest funkcja usunięcia obiektu, ale nie ma funkcji usunięcia po id
        // dlatego musimy najpierw odnaleźć obiekt, a następnie go usunąć przekazując do dao
        EntityDao dao = new EntityDao();
        Student student = dao.getById(Student.class,identyfikatorLong);

        dao.delete(student);

        resp.sendRedirect(req.getContextPath() + "/student/list");

    }
}
