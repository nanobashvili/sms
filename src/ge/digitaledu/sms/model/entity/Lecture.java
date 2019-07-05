package ge.digitaledu.sms.model.entity;

import ge.digitaledu.sms.common.LectureState;

public class Lecture {

    private int id;

    private String lectureName;

    private LectureState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public LectureState getState() {
        return state;
    }

    public void setState(LectureState state) {
        this.state = state;
    }
}
