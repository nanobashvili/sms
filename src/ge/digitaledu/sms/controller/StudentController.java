package ge.digitaledu.sms.controller;

import ge.digitaledu.sms.model.MainModel;
import ge.digitaledu.sms.model.MainModelService;
import ge.digitaledu.sms.model.entity.Student;
import ge.digitaledu.sms.utils.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        /**
         * მთავარი მოდელის ჩატვირთვა
         */
        MainModel studentModel = new MainModelService();

        /**
         * ახალი ობიექტი სტუდენტიდან
         */
        Student student = new Student();

        student.setFirstName("Misha");
        student.setLastName("Nanobashvili");
        student.setPn("01027043866");

        /**
         * შენახვა
         */
        studentModel.save(student);

        /**
         * წაშლა
         */
        studentModel.delete(student, 1);
    }
}
