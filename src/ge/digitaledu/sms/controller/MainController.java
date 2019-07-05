package ge.digitaledu.sms.controller;

import ge.digitaledu.sms.utils.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ეს არის აბსტრაქტული კლასი, მთავარი კონტროლერი, რომელიც ე.წ. სერვლეტის შვილობილი კლასია
 * ეს კლასი იქნება ერთგვარი მშობელი კლასი ყველა სხვა კონტროლერისა, სტუდენტი იქნება, გადახდების კონტროლერი თუ მენეჯერები
 * ამ კლასის ამოცანაა, გააერთიანოს ყველა საჭირო მეთოდი თუ პარამეტრი შვილობილი კონტროლერებისთის
 * ყველა კონტროლერი თავისი არსით სერვლეტი გამოდის პროექტში (ჩვენს შემთხვევაში)
 */
abstract public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        request(req, resp, Method.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        request(req, resp, Method.POST);
    }

    /**
     * გადავფარეთ ორივე ტიპის მოთხოვნა, GET და POST და მოვაქციეთ ერთ მეთოდში
     * ამავე მეთოდში გადავცემთ მოთხოვნის ტიპს (GET არის თუ POST)
     * დავარქვით request, რომელიც აბსტრაქტული მეთოდია და მხოლოდ გამოცხადება შეუძლია, ანუ ლოგიკა არ გვიწერია
     * შესაბამისად ყველა შვილობილ კონტროლერს(სერვლეტს), რომელიც გამოვა ამ მშობელი კონტროლერიდან ექნება ეს მეთოდი გადატვირთული
     * იხ. StudentController კლასი
     */
    abstract protected void request(HttpServletRequest request, HttpServletResponse response, Method method);
}
