import java.util.Arrays;
import java.util.List;

public class ParseObject {
    private int number;
    private String name;
    private boolean aBoolean;
    private List<MockObject> mockObjects;
    private long[] arrLong;
    private static final String CONST = "testString";


    public ParseObject(int number, String name, boolean aBoolean, List<MockObject> mockObjects,
                     long[] arrLong) {
        this.number = number;
        this.name = name;
        this.aBoolean = aBoolean;
        this.mockObjects = mockObjects;
        this.arrLong = arrLong;

    }

    @Override
    public String toString() {
        return "from Something object info = " +
                "{number=" + number +
                ",name=" + name +
                ",aBoolean=" + aBoolean + "," +
                ("arrLong=" + Arrays.toString(arrLong) + ",") +
                ("mockList=" + mockObjects + ",") +
                "CONST=" + CONST + "," +
                "}"; }

}
