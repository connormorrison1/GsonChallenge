import jsoncomparator.JSONData;
import jsoncomparator.JSONFile;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception{
        Scanner jsonFileInput = new Scanner(new InputStreamReader(System.in));
        System.out.println("Please type the name of the first JSON file to compare:");
        String firstJSONFile = jsonFileInput.nextLine();
        System.out.println("Please type the name of the second JSON file to compare:");
        String secondJSONFile = jsonFileInput.nextLine();
        JSONData compareJSONFiles = new JSONData(new JSONFile(firstJSONFile), new JSONFile(secondJSONFile));
    }
}
