package ge.digitaledu.sms.model.entity;

import ge.digitaledu.sms.common.LectureState;

public class Lecture extends Model {

    private String lectureName;

    private LectureState state;

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

    public void setData(int id, String name, LectureState state) {
        setState(state);
        setLectureName(name);
        setId(id);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureName='" + lectureName + '\'' +
                ", state=" + state +
                ", id=" + id +
                '}';
    }
}