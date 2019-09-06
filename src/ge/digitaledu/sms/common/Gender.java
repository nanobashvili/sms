package ge.digitaledu.sms.common;

/**
 * ენუმერაცია სქესისთვის
 */
public enum Gender {
    MALE(1, "MALE"),

    FEMALE(2, "FEMALE");

    private int key;

    private String value;

    Gender(int key, String value) {
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
