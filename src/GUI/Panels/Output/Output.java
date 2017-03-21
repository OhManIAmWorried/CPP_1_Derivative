package GUI.Panels.Output;

import GUI.MainFrame;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.IllegalFormatException;

public interface Output {

    void setDirectory(String dir);                   //obtains file directory

    static String obtainString(String dir) {     //obtains expression from the file
        File file = new File(dir);
        String res = "null";

        if(!file.exists()) {return "null";}
        else try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                s = in.readLine();
                switch (s) {
                    case "Expression": {                                                                                //Expression x x*2
                        res = s + " " + in.readLine() + " " + in.readLine();
                        break;
                    }
                    case "Points": {                                                                                    //Points 1:1 2:2
                        StringBuilder sb = new StringBuilder();
                        sb.append(s + " ");
                        while ((s = in.readLine()) != null) {
                            sb.append(s);
                            sb.append(" ");
                            res = sb.toString();
                        }
                        break;
                    }
                    //add cases for other means of data storing if needed
                    default: {
                        throw new IOException() ;
                    }
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    void showDerivative();
}

