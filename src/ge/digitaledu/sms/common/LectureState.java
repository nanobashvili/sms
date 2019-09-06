package ge.digitaledu.sms.common;

/**
 * ენუმერაცია ლექციის მიმდინარეობის პროცესისთის
 * დასრულებული კურსი, მიმდინარე კურსი, შეჩერებული კურსი
 */
public enum LectureState {
    DONE(1, "DONE"),

    IN_PROGRESS(2, "IN_PROGRESS"),

    STOPPED(3, "STOPPED");

    private int key;

    private String value;

    LectureState(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
