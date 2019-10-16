package jsoncomparator;

import brewerydata.StateBrewing;
import com.google.gson.Gson;

public class JSONData {

    //initialize the two JSONFile objects to compare.
    JSONFile jsonFile1;
    JSONFile jsonFile2;

    /**
     * JSONData constructor. Sets the JSONFiles to be read from, and calls to parse JSON.
     * @param jsonFile1, first JSONFile to compare
     * @param jsonFile2, second JSONFile to compare
     * @throws Exception
     */
    public JSONData(JSONFile jsonFile1, JSONFile jsonFile2) {
        this.jsonFile1 = jsonFile1;
        this.jsonFile2 = jsonFile2;
        this.getParsedJSON();
    }

    /**
     * Parses the JSON from each file, and calls to get a comparison score of the two.
     * @throws Exception
     */
    public void getParsedJSON() {
        Gson gson = new Gson();
        //map the JSON documents into the StateBrews objects.
        StateBrewing firstJSON = gson.fromJson(this.jsonFile1.readFile(), StateBrewing.class);
        StateBrewing secondJSON = gson.fromJson(this.jsonFile2.readFile(), StateBrewing.class);
        this.getComparison(firstJSON,secondJSON);
    }

    /**
     * Returns the comparison (equality) score of the two json documents.
     * @param firstJSON, StateBrews JSON object
     * @param secondJSON, StateBrews JSON object
     * @return double, equality score.
     */
    public double getComparison(StateBrewing firstJSON, StateBrewing secondJSON) {
        //Create a JSONComparator object to compare the two JSON documents.
        JSONComparator score = new JSONComparator(firstJSON,secondJSON);
        return score.getComparison();
    }
}
