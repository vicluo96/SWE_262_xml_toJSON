import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class M5Test {
    public static void main(String[] args) throws IOException {

        //function for tranformation
        interface MyFunction {
            String apply(String a);
        }

        // implementation of the functional interface using lambda
        MyFunction addPrefix = (a) -> "swe262_" + a;

        File xmlFile = new File("frwikiquote-20221201-flow.xml");
        Reader fileReader = null;
        try {
            fileReader = new FileReader(xmlFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //function for writing
        interface MyWriteFunction {
            void apply(JSONObject a);
        }

        FileWriter file = null;
        try {
            file = new FileWriter("output.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter finalFile = file;
        MyWriteFunction writeToFile = (a) -> {
            try {
                finalFile.write(a.toString(4));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        ////function for exceptions
        Consumer<Exception> exceptionHandler = new Consumer<Exception>() {
            public void accept(Exception e) {
                e.printStackTrace();
            }
        };

        XML.toJSONObject(fileReader, addPrefix::apply, writeToFile::apply, exceptionHandler);

        for(int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        file.close();
    }
}
