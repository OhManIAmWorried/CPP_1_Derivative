package GUI.Panels.Input;

import edu.hws.jcm.data.Expression;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public interface Input {
    void setDirectory(String dir);                                             //obtains file directory
    static void writeExpression(String directory, Expression expression) {     //writes expression to the file
        File file = new File(directory);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(expression.toString());
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
