package ps.sda.javagdy2.servlets;

import ps.sda.javagdy2.database.EntityDao;
import ps.sda.javagdy2.database.model.Grade;
import ps.sda.javagdy2.database.model.Przedmiot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade/edit")
public class GradeEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long gradeId = Long.parseLong(req.getParameter("gradeId"));

            // pobranie z bazy obecnego stanu oceny
            EntityDao dao = new EntityDao();
            Grade grade = dao.getById(Grade.class, gradeId);

            // ustawienie zmiennej (przekazanie do widoku oceny która będzie edytowana)
            req.setAttribute("gradeToEdit", grade);
            req.setAttribute("student_identifier", grade.getStudent().getId());
            req.setAttribute("przedmioty", Przedmiot.values());

            // załadowanie widoku
            req.getRequestDispatcher("/grade_form.jsp").forward(req, resp);

            // jeśli exception będzie numberformatexception LUB nullpointerexception, to...
        } catch (NumberFormatException | NullPointerException ne) {
            // brak parametru = przekieruj na listę studentów
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long editedGradeId = Long.parseLong(req.getParameter("editedGrade"));

            // pobranie z bazy obecnego stanu oceny
            EntityDao dao = new EntityDao();
            Grade grade = dao.getById(Grade.class, editedGradeId);

            Przedmiot przedmiot = Przedmiot.valueOf(req.getParameter("przedmiot"));
            double ocena = Double.parseDouble(req.getParameter("ocena"));

            grade.setOcena(ocena);
            grade.setPrzedmiot(przedmiot);

            dao.saveOrUpdate(grade);

            resp.sendRedirect(req.getContextPath() + "/student/detail?identifier=" + grade.getStudent().getId());
        } catch (NumberFormatException | NullPointerException ne) {
            // brak parametru = przekieruj na listę studentów
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }
    }
}

