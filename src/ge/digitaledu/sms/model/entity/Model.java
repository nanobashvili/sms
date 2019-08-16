package ge.digitaledu.sms.model.entity;

import ge.digitaledu.sms.common.LectureState;

public class Model {
    protected int id;

    private LectureState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
