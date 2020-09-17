package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Grade;
import ps.sda.javagdy2.database.model.Przedmiot;
import ps.sda.javagdy2.database.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade/add")
public class GradeAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // muszę wiedzieć któremu studentowi dodajemy ocenę
        // z tego względu przesłaliśmy sobie studenta (jego identyfikator) w parametrze zapytania
        String studentId = req.getParameter("studentId");
        if (studentId == null || studentId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }

        req.setAttribute("student_identifier", studentId);
        // wysyłamy na widok listę wszystkich dostępnych przedmiotów,
        // żeby były dostępne na liście rozwijanej.
        req.setAttribute("przedmioty", Przedmiot.values());

        // chcemy wyświetlić użytkownikowi formularz dodawania nowej oceny
        req.getRequestDispatcher("/grade_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ocenaString = req.getParameter("ocena");
        String przedmiotString = req.getParameter("przedmiot");
        // jeśli którykolwiek z parametrów jest pusty, to przekieruj na stronę lista studentów
        if (ocenaString == null || przedmiotString == null || ocenaString.isEmpty() || przedmiotString.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }
        Przedmiot gradePrzedmiot = Przedmiot.valueOf(przedmiotString);
        double gradeOcena = Double.parseDouble(ocenaString);

        // na podstawie wartości dwóch pól tworzymy obiekt ocene.
        Grade grade = new Grade(gradePrzedmiot, gradeOcena);

        // brakuje nam wyłącznie stworzenia relacji.
        String studentId = req.getParameter("studentGraded");
        if (studentId == null || studentId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }
        Long studentIdLong = Long.parseLong(studentId);

        EntityDao dao = new EntityDao();
        Student student = dao.getById(Student.class, studentIdLong);

        // stworzenie relacji bazodanowej
        grade.setStudent(student);

        // zapisanie do bazy
        dao.saveOrUpdate(grade);

        // przekierowanie na stronę ze szczegółami studenta.
        resp.sendRedirect(req.getContextPath() + "/student/detail?identifier=" + studentId);
    }
}