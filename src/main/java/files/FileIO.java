package files;

import java.util.ArrayList;

/**
 * Created by tmuringani on 7/9/17.
 */
public interface FileIO {

    public String[] splitString(String inputString);
    public ArrayList<String> readFile(String file)  throws Exception;
    public boolean validateInput(int[] intElements);
    public boolean validateInput(ArrayList<StringBuilder> sb);
    public void writeFile(String filename, String msg);
}
