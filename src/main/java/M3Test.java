import org.json.JSONObject;
import org.json.XML;

import java.io.*;

public class M3Test {
    public static void main(String[] args) throws IOException {
        interface MyFunction {
            String apply(String a);
        }

        // implementation of the functional interface using lambda
        MyFunction addPrefix = (a) -> "swe262_" + a;

        File xmlFile = new File("gnwikibooks-20221201-pages-logging.xml");
        Reader fileReader = null;
        try {
            fileReader = new FileReader(xmlFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        JSONObject jobj = XML.toJSONObject(fileReader, addPrefix::apply);

        FileWriter file = null;
        try {
            file = new FileWriter("output.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.write(jobj.toString(4));
        file.close();

    }
}
