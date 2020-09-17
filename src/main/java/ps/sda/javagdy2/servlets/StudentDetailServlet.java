package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/detail")
public class StudentDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identifierString = req.getParameter("identifier");
        // jeśli identyfikator (parametr) nie został podany, to
        // przekierowujemy użytkownika na listę studentów
        if(identifierString == null || identifierString.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }

        // parsowanie identyfikatora na long.
        Long identifier = Long.parseLong(identifierString);

        EntityDao dao = new EntityDao();
        Student student = dao.getById(Student.class, identifier);
        // jeśli nie uda się odnaleźć takiego użytkownika
        if(student == null){
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }

        // załadowanie studenta jako atrybutu żeby mieć możliwość
        // zaprezentowania wartości jego pól w widoku (jsp)
        req.setAttribute("studentDetails", student);

        // wyświetlenie widoku
        req.getRequestDispatcher("/student_detail.jsp").forward(req, resp);
    }
}