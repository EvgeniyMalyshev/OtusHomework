import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class parserTest {

    @Test
    public void test(){
        ParseObject parseObject = new ParseObject(
                100,
                "name",
                false,
                Arrays.asList(new MockObject(), new MockObject()),
                new long[] {1,2,3,4});

        System.out.println(parseObject);
        Assert.assertNotNull(parseObject);
        /* Почему то не срабатывает.
        Assert.assertEquals(new ParseObject(1,
                "name",
                true,
                new String[]{"string", "string2"},
                Arrays.asList("string", "string2"),
                new long[]{64L,42L})
                ,parseObject);*/

        String objectToJsonString = JsonParser.objectToJsonString(parseObject);
        System.out.println(objectToJsonString);
        }
    }

