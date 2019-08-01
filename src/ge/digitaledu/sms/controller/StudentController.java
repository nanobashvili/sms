package ge.digitaledu.sms.controller;

import ge.digitaledu.sms.common.Gender;
import ge.digitaledu.sms.common.LectureState;
import ge.digitaledu.sms.common.StudentStatus;
import ge.digitaledu.sms.model.MainModel;
import ge.digitaledu.sms.model.MainModelService;
import ge.digitaledu.sms.model.entity.Lecture;
import ge.digitaledu.sms.model.entity.Student;
import ge.digitaledu.sms.utils.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
        if (method == Method.POST) {
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

            if (gender.equals("m")) {
                student.setGender(Gender.MALE);
            } else {
                student.setGender(Gender.FEMALE);
            }

            Lecture lectureObj = new Lecture();
            lectureObj.setLectureName(lecture);
            lectureObj.setState(LectureState.IN_PROGRESS);
            lectureObj.setId(1);
            student.setLecture(lectureObj);
            student.setStatus(StudentStatus.ACTIVE);

            /**
             * მთავარი მოდელის ჩატვირთვა
             */
            MainModel studentModel = new MainModelService();

            /**
             * შენახვა
             */
            studentModel.save(student);
        }

        if (method == Method.GET) {
            try {
                request.setAttribute("statusList", StudentStatus.values());
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {

            }
        }
        // TODO writer
    }

    public void testMethod(String argument) throws NullPointerException, IllegalArgumentException {
        if (argument == null) {
            throw new NullPointerException("");
        }

        if (argument.length() <= 1) {
            throw new IllegalArgumentException("String must pass minimum 2 characters");
        }
        System.out.println("method example 1 " + argument.toLowerCase());
    }
}
