package ge.digitaledu.sms.common;

/**
 * სტუდენტის სტატუსის მდგომარეობა
 * აქტიური, არააქტიური, შეჩერებული (შეიძლება ქონდეს სტუდენტს შეჩერებული სტატუსი)
 */
public enum StudentStatus {
    ACTIVE(1, "ACTIVE"),

    INACTIVE(2, "INACTIVE"),

    PAUSED(3, "PAUSED");

    private int key;

    private String value;

    StudentStatus(int key, String value) {
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
