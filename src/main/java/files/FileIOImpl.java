package files;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by tmuringani on 7/9/17.
 */
public class FileIOImpl implements FileIO {

    @Override
    public ArrayList<String> readFile(String file) throws Exception {
        ArrayList<String> textlines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\"", "");
                line = line.replaceAll("\\{", "");
                line = line.replaceAll("}", "");
                System.out.println(line);
                textlines.add(line);
            }
        } catch (FileNotFoundException f) {
            throw new Exception("\nThe requested file was not found");
        }

        return textlines;
    }

    //---------------------------------------------------------------------------------------------------
    @Override
    public boolean validateInput(int[] intElements) {

        // Check if input contains between 2 and 50 elements, inclusive
        if (intElements.length < 2 || intElements.length > 50) {
            System.out.println("The input should contain between 2 and 50 elements, inclusive");
            return false;
        }

        // Check if each element's value is between 0 and 1048575.
        for (int i = 0; i < intElements.length; i++) {
            if (intElements[i] < 0 || intElements[i] > 1048575) {
                System.out.println("Each element's value should be between 0 and 1048575.");
                return false;
            }
        }
        return true;
    }   //todo : Method to validate And-Equation

    //---------------------------------------------------------------------------------------------------
    @Override
    public boolean validateInput(ArrayList<StringBuilder> sbArr) {
        StringBuilder sb1 = sbArr.get(0), sb2 = sbArr.get(1);

        //Check if initial length is between 1-999 inclusive
        if (sbArr.get(0).length() < 1 || sbArr.get(0).length() > 999) {
            System.out.println("The <initial> length should range between 1 and 999 characters inclusive");
            return false;
        }

        //Check if target length is between 2-1000 inclusive
        if (sbArr.get(1).length() < 2 || sbArr.get(1).length() > 1000) {
            System.out.println("The <target> length should range between 2 and 1000 characters inclusive");
            return false;
        }

        // Check if target is longer than initial
        if (sbArr.get(0).length() >= sbArr.get(1).length()) {
            System.out.println("The <target> should be longer than the <initial>");
            return false;
        }
        // Check if data contains only As and/or Bs
        for (int i = 0; i < sbArr.size(); i++) {
            for (int j = 0; j < sbArr.get(i).length(); j++) {
                if (!(sbArr.get(i).charAt(j) == 'A' | sbArr.get(i).charAt(j) == 'B')) {
                    System.out.println("The provided Data should only contain As and/or Bs");
                    return false;
                }
            }
        }

        return true;
    }


    //---------------------------------------------------------------------------------------------------
    @Override
    public void writeFile(String filename, String msg) {
        try {
            String file = ("./" + filename);
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.append(msg);
            writer.close();
        } catch (IOException ex) {
            System.out.println("File: " + filename + " could not be found >>>>>>>>>>>>>>>>>>" + ex.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------
    @Override
    public String[] splitString(String input) { // This method takes in a string/StingBuilder and splits the data into an String array
        String[] stringArray = input.split(",");
        return stringArray;
    }
}