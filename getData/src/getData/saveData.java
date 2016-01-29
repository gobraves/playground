package getData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cty on 2016/1/26.
 */
public class saveData {
    public  static void save(String address, String saveFileName) throws IOException {
        File file = new File(saveFileName);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        pw.write(address+"\n");
        pw.close();

    }
}
