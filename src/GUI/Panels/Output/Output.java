package GUI.Panels.Output;

import GUI.MainFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public interface Output {

    void setDirectory(String dir);                   //obtains file directory

    static String obtainExpression(String dir) {     //obtains expression from the file
        StringBuilder sb = new StringBuilder();
        File file = new File(dir);

        if(!file.exists()) {return "null";}
        else {
            try {
                BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
                try {
                    String s;
                    while ((s = in.readLine()) != null) {
                        sb.append(s);
                        sb.append("\n");
                    }
                } finally {
                    in.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }

            return sb.toString().trim();
        }
    }

    void showExpression();
}

