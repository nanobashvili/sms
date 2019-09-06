package ge.digitaledu.sms.controller;

import ge.digitaledu.sms.common.LectureState;
import ge.digitaledu.sms.model.entity.Lecture;
import ge.digitaledu.sms.utils.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//git test

public class LectureController extends MainController {

    @Override
    protected void request(HttpServletRequest request, HttpServletResponse response, Method method) {
        if (method == Method.GET){
            // load jsp
            try {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/lecture.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {

            }
        } else if (method == Method.POST) {
            String name = request.getParameter("lectureName");
            String status = request.getParameter("lectureStatus");

            Lecture lecture = new Lecture();

            if (status.equalsIgnoreCase("progress")) {
                lecture.setState(LectureState.IN_PROGRESS);
            } else if (status.equalsIgnoreCase("done")) {
                lecture.setState(LectureState.DONE);
            } else {
                lecture.setState(LectureState.STOPPED);
            }

            lecture.setLectureName(name);

        } else {
            // exception handling მომავალ ლექციაზე
        }
    }
}
