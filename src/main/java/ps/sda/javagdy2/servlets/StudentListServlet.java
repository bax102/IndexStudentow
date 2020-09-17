package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


// podstawowy wygląd servletu (adnotacja + dziedziczenie

// Klasa StudentListServlet może być wywoływana z przeglądarki
// http://localhost:8080/student/list - wyołuje
@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {
    // httpservlet request - klasa reprezentująca zapytanie które otrzymaliśmy od użytkownika
    //  zapytanie które przyszło wraz z wywołaniem tej metody
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityDao dao = new EntityDao();
        List<Student> list = dao.list(Student.class);

        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Long.compare(o1.getId(),o2.getId());
            }
        });

        // attribute to sposób (mapa) obiektów które chcemy wysłać/zaprezentować na widokach
        req.setAttribute("students", list);
        // Request Dispatcher to klasa która służy do renderowania/przetwarzania plików jsp (między innymi kompilacja kodu java)
        //  następnie naniesienie na nią danych i zwrócenie użytkownikowi gotowego widoku html
        RequestDispatcher dispatcher = req.getRequestDispatcher("/student_list.jsp"); // nazwa widoku jsp
        dispatcher.forward(req, resp);
    }
}















