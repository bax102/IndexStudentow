package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {

    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // w jednej linii tworzę request despatcher i przekierowuje go na widok formularza (wyświetlam formularz)
        req.getRequestDispatcher("/student_form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student student = Student.builder()
                .imie(request.getParameter("imie"))
                .nazwisko(request.getParameter("nazwisko"))
                .pelnoletni(request.getParameter("pelnoletni") != null)
                .wzrost(Double.parseDouble(request.getParameter("wzrost")))
                .build();

        EntityDao dao = new EntityDao();
        dao.saveOrUpdate(student);

//    response.sendRedirect("student_list.jsp");
        response.sendRedirect("/student/list");

        new PrintWriter(response.getOutputStream()).println("<html>");
        new PrintWriter(response.getOutputStream()).println("</html>");
    }
}
