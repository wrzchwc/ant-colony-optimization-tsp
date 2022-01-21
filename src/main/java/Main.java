import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Effective algorithms design");
        try {
            Ini ini = new Ini(new File("src/main/resources/setup.ini"));
            System.out.println(ini.get("kuba","age"));
            System.out.println(ini.get("kuba","city"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
