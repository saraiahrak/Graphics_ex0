import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    public Reader() {}

    public static ArrayList<String> readLines(String filename) {
        ArrayList<String> arr = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                arr.add(line);
                line = br.readLine();
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return arr;
    }

}
