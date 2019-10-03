import org.junit.Assert;
import org.junit.Test;

import java.JsonParser;
import java.MockObject;
import java.ParseObject;
import java.util.Arrays;

public class parserTest {

    @Test
    public void test(){

        ParseObject parseObject = new ParseObject(
                100,
                "name",
                false,
                Arrays.asList(new MockObject(), new MockObject())
                ,
                new long[] {1,2,3,4});

        System.out.println(parseObject);
        Assert.assertNotNull(parseObject);
        String objectToJsonString = JsonParser.objectToJsonString(parseObject);
        System.out.println(objectToJsonString);
        }
    }


