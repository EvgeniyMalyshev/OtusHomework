import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class ParseObject {
    private int number;
    private String name;
    private boolean aBoolean;
    private String[] strings;
    private List<String> stringList;
    private long[] arrLong;
    private static final String CONST = "testString";

    public ParseObject(int number, String name, boolean aBoolean, String[] strings,
                       List<String> stringList, long[] arrLong) {
        this.number = number;
        this.name = name;
        this.aBoolean = aBoolean;
        this.strings = strings;
        this.stringList = stringList;
        this.arrLong = arrLong;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParseObject.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("name='" + name + "'")
                .add("isPrim=" + aBoolean)
                .add("strings=" + Arrays.toString(strings))
                .add("stringList=" + stringList)
                .add("arrLong=" + Arrays.toString(arrLong))
                .toString();
    }
}
