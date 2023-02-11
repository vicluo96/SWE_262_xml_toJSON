import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;

import java.io.StringReader;
import java.util.function.Function;

public class M2Test {
    public static void main(String[] args) {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";

        try {
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), new JSONPointer("/contact/address/street"));
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }

        System.out.println("-----------------------");

        try {
            JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n" +
                    " <zipcode>92612</zipcode>");
            System.out.println("Given replacement: " + replacement);
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), new JSONPointer("/contact/address"), replacement);
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }

        System.out.println("-----------------------");

        try {
            Function<String, String> keyTransformer = (key) -> key + "_swe262!";
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), keyTransformer);
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }
}
