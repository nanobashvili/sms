package ge.digitaledu.sms.controller;

import ge.digitaledu.sms.common.Gender;
import ge.digitaledu.sms.common.LectureState;
import ge.digitaledu.sms.common.StudentStatus;
import ge.digitaledu.sms.model.Model;
import ge.digitaledu.sms.model.StudentModelService;
import ge.digitaledu.sms.model.entity.Lecture;
import ge.digitaledu.sms.model.entity.Student;
import ge.digitaledu.sms.utils.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ეს კლასი ერთ-ერთი კონტროლერია(ანუ ჯავასთვის სერვლეტი), რომელიც გამომდინარეობს ჩვენთვის ნაცნობი MainController დან
 * ხოლო MainController გამომდინარეობს ჯავას HttpServlet დან, შესაბამისად მემკვიდრეობით ეს კლასიც სერვლეტია
 * მოცემული გვაქვს მემკვიდრეობითობის მაგალითი ამ შემთხვევაში
 *
 * დაიმახსოვრეთ, რომ ჯავაშ ცნება კონტროლერი არ არსებობს, ეს ენაზე არ არის დამოკიდებული, უბრალოდ არქიტექტურის ნაწილია
 * ხოლო სერვლეტი ჯავას სტანდარტია, კლასია, მისი ნაწილია, უბრალოდ ლოგიკით და არქიტექტურით გავს კონტროლერს
 * შესაბამისად რაც არ უნდა დავარქვათ ჩვენ, ჯავასთვის ეს მაინც სერვლეტი იქნება და ამიტომ, web.xml ში აღწერა არ უნდა დაგავიწყდეთ
 */
public class StudentController extends MainController {

    /**
     * MainController-დან, ანუ მშობელი კონტროლერიდან გადატვირთული აბსტრაქტული მეთოდი request
     * ამ მეთოდში ჩვენ უკვე გვაქვს მოთხოვნის ტიპი (GET თუ POST)
     * და ყველა საჭირო პარამეტრი, რომელსაც ჯავა თავად აწესრიგებს
     * HttpServletRequest request, HttpServletResponse response
     * კონტროლერშ მოცემულია სტუდენტის მოდელის მაგალითი და მასზე მანიპულაცია, წაშლა და შენახვა
     */
    @Override
    protected void request(HttpServletRequest request, HttpServletResponse response, Method method) {

        Model model = new StudentModelService(Student.class);

        if (method == Method.POST) {
            boolean edit = Boolean.valueOf(request.getParameter("edit"));
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String bDate = request.getParameter("birthdate");
            String gender = request.getParameter("gender");
            String lecture = request.getParameter("lecture");
            String status = request.getParameter("status");
            String privateNumber = request.getParameter("pn");

            /**
             * ახალი სტუდენტის ობიექტის შექმნა
             */
            Student student = new Student();

            student.setPn(privateNumber);
            student.setLastName(lastName);
            student.setFirstName(firstName);
            student.setBirthDate(bDate);
            student.setStatus(StudentStatus.valueOf(status));

            if (gender.equals("m")) {
                student.setGender(Gender.MALE);
            } else {
                student.setGender(Gender.FEMALE);
            }

            // TODO
            // ეს გადასაკეთებელია, ისე რომ ლექცია აქ არ იქმნებოდეს და დინამიურად მოქონდეს ბაზიდან
            Lecture lectureObj = new Lecture();
            lectureObj.setLectureName(lecture);
            lectureObj.setState(LectureState.IN_PROGRESS);
            lectureObj.setId(1);
            student.setLecture(lectureObj);


            student.setStatus(StudentStatus.ACTIVE);

            /**
             * შენახვა ან ცვლილება
             */
            if (edit) {
                model.update(student, Integer.parseInt(request.getParameter("id")));
            } else {
                model.save(student);
            }

            try {
                response.sendRedirect(request.getContextPath() + "/Students");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (method == Method.GET) {
            String action = request.getParameter("action");
            if (action == null) {
                showData(model, request, response);
            } else if (action.equals("add")) {
                addOrEditData(model, request, response, false, 0);
            } else if (action.equals("edit")) {
                addOrEditData(model, request, response, true, Integer.parseInt(request.getParameter("id")));
            } else if (action.equals("delete")) {
                deleteData(model, request, response, Integer.parseInt(request.getParameter("id")));
            } else {
                showData(model, request, response); // default page
            }
        }
    }

    private void showData(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Student> students = model.getAll();

            request.setAttribute("students", students);
            request.setAttribute("statusList", StudentStatus.values());
            request.setAttribute("genders", Gender.values());
            request.setAttribute("lectureList", LectureState.values());
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/students/students.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addOrEditData(Model model, HttpServletRequest request, HttpServletResponse response, boolean edit, int id) {
        try {
            Student student = (Student) model.getSingle(id);

            request.setAttribute("isEdit", edit);
            request.setAttribute("student", student);
            request.setAttribute("statusList", StudentStatus.values());
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/students/students_edit.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteData(Model model, HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            model.delete(id);
            response.sendRedirect(request.getContextPath() + "/Students");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
