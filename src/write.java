import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class write {
    void WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
