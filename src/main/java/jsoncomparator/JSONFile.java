package jsoncomparator;

import java.io.*;

public class JSONFile {

    //initialize file to hold JSON document.
    File jsonFile;

    /**
     * Constructor to set the file that holds the json contents.
     * @param jsonFile, file path to JSON.
     */
    public JSONFile(String jsonFile) {
        this.jsonFile = new File(jsonFile);
    }

    /**
     * Reads the file from JSON, and returns it in a string.
     * @return String, contents of the provided JSON file.
     * @throws Exception
     */
    public String readFile(){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            //instantiate a BufferedReader object to read from the JSON file.
            BufferedReader br = new BufferedReader(new FileReader(this.jsonFile));
            String st;
            //while the file still has contents, then append to the StringBuilder.
            while ((st = br.readLine()) != null) {
                contentBuilder.append(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fix naming conventions to allow for variables to be named properly in java.
        String jsonString = contentBuilder.toString().replace("-","_");
        return jsonString.replace("_list","s");
    }
}
