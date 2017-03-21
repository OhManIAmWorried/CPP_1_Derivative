package GUI.Panels.Input;

import GUI.DoublePoint;
import edu.hws.jcm.data.Expression;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface Input {

    void setDirectory(String dir);                                                                 //obtains file directory
    static void write(String directory, Expression expression, String type, String variable) {     //writes expression to the file
        File file = new File(directory);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.println(type);
                out.println(variable);
                out.print(expression.toString());
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void write(String directory, ArrayList<DoublePoint> arrayList, String type) {
        File file = new File(directory);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.println(type);
                for (DoublePoint doublePoint: arrayList) out.println(doublePoint.toString());
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
