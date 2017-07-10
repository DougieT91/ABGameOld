package abgame;

import files.FileIOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmuringani on 7/10/17.
 */
public class ABGameMain {
    private static int Y;
    private static String result;

    public static void main(String[] args) {
        // Fields
        String inputFile = "./files/test1.txt", outputFile = "./files/output/test1-output.txt";
        List<String> inputList = new ArrayList<>();              //String ArrayList

        // Check for the right number of arguments
        if (args.length == 2) {
            inputFile = args[0];      // The <input-file> string
            outputFile = args[1];     // The <output-file> string
        } else {
            System.out.println("Please run program with 2 arguments (input and output) in the format: java -jar ABGame.jar <input.txt> <output.txt>");
            System.out.println("\nProgram Exiting...");
            System.exit(1);
        }


        //Read the File
        try {
            inputList = new FileIOImpl().readFile(inputFile);
        } catch (Exception e) {
            System.out.println("Error occurred while trying to read file: " + e.getMessage());
            System.out.println("\nProgram Exiting...");
            System.exit(1);
        }


        ArrayList<StringBuilder> sbList = new ArrayList<>();
        sbList.add(0, new StringBuilder(inputList.get(0)));
        sbList.add(1, new StringBuilder(inputList.get(1)));

        //Check Validity of Array against constraints specified. If any violations occur, exit the program
        boolean legal = new FileIOImpl().validateInput(sbList);
        if (legal == false) {
            System.out.println("Constrains have been violated. \nThe program will now exit...");
            System.exit(1);
        } else {      // Else continue to perform the AND Equation and write the result in a text file
            result = playGame(sbList);
            new FileIOImpl().writeFile(outputFile, result);
        }

        System.out.println("Result is " + result);
    }

    public static int[] StringToIntArr(String[] StrArr) {
        int[] intArr = new int[StrArr.length];

        try {
            for (int j = 0; j < StrArr.length; j++) {
                String curr = StrArr[j];
                intArr[j] = Integer.parseInt(curr.trim());
            }
        } catch (NumberFormatException n) {
            System.out.println("The input provided contains non-integer values. Make sure the input only contains numbers (and/or commas and curly braces)");
        }

        return intArr;
    }

    public static String playGame(ArrayList<StringBuilder> sbArr) {
        //StringBuilder buffer, initial = sbArr.get(0), target = sbArr.get(1);
        //buffer = initial;
        String initial=sbArr.get(0).toString(), target = sbArr.get(1).toString();

        for (initial = initial.toString(); initial.length() <= target.length(); ) {
            if(initial.length()==target.length()){
                if(initial.equals(target)){
                    return "possible";
                }else{
                    return "impossible";
                }
            }
            if (target.contains(optionA(initial))) {
                initial = optionA(initial);

            }else if(target.contains(optionB(initial))){
                initial=optionB(initial);

            }else{
                return "impossible";
            }
        }
        return "possible";
    }


    public static String optionA(String initial) {
        String A;
        A = initial + "A";
        return A;
    }

    public static String optionB(String initial) {
        String B;
        StringBuilder sb = new StringBuilder(initial);
        sb.reverse();
        sb.append('B');
        B = sb.toString();
        return B;
    }





}
