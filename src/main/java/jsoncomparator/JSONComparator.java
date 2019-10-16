package jsoncomparator;

import brewerydata.Beer;
import brewerydata.Brewery;
import brewerydata.Location;
import brewerydata.StateBrewing;
import java.util.HashSet;
import java.util.List;

public class JSONComparator {

    //initialize equality score, set to 1.
    double score = 1.0;
    StateBrewing firstJSON;
    StateBrewing secondJSON;

    /**
     * JSONComparator Constructor, sets the two StateBrews objects.
     * @param firstJSON, StateBrews object
     * @param secondJSON, StateBrews object
     */
    public JSONComparator(StateBrewing firstJSON, StateBrewing secondJSON) {
        this.firstJSON = firstJSON;
        this.secondJSON = secondJSON;
    }

    /**
     * Return the equality of strings.
     * @param str1, first string to compare
     * @param str2, second string to compare
     * @return true if strings are equal, false otherwise.
     */
    public boolean compareStrings(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

    /**
     * Compare beer lists to determine an equality score.
     * @param one, first list of Beer objects
     * @param two, second list of Beer objects
     * @return double, equality score of the beer lists
     */
    public double compareBeers(List<Beer> one, List<Beer> two) {
        //set the initial score to be zero.
        double score = 0.0;
        //hashset to store all not found beers, is hashset to prevent duplicates.
        HashSet<String> notFoundBeers = new HashSet<>();
        //nested for loop to compare each Beer with all other beers found within the list
        for (Beer a : one) {
            boolean beerFound = false;
            for (Beer b : two) {
                //check to see if the name of the beer is found in both.
                if (compareStrings(a.getName(), b.getName())) {
                    beerFound = true;
                    //if so, then compare the color, bitterness, and alcohol to determine if they are in common.
                    if (!compareStrings(a.getColor(), b.getColor())) {
                        //if not in common, increase the score.
                        score += 0.25;
                    }
                    if (a.getInternational_bitterness_units() != b.getInternational_bitterness_units()) {
                        score += 0.25;
                    }
                    if (a.getAlcohol_by_volume() != b.getAlcohol_by_volume()) {
                        score += 0.25;
                    }
                    break;
                }
            }
            //if the beer is not found, add it to the hashset
            if (!beerFound) {
                notFoundBeers.add(a.getName());
            }
        }
        //set the score to be the number of incorrect beers (up to 4) times the 0.25 multiplier.
        //if the score is greater than 1, then just set to 1.
        score = (notFoundBeers.size() > 4 ? 1.0 :  score + 0.25 * notFoundBeers.size());
        return score;
    }

    /**
     * Compare locations to determine an equality score.
     * @param location1, first location
     * @param location2, second location
     * @return double, equality score between locations.
     */
    public double compareLocation(Location location1, Location location2) {
        double score = 0.0;
        double scoreMultiplier = 0.25;
        //since GPS seems to be a rare event, don't penalize if both are null.
        if (location1 != null && location2 != null) {
            if (location1.getGPS() != null && location2.getGPS() != null) {
                scoreMultiplier = 0.2;
                //check to see if the latitudes are the same.
                if (location1.getLatitude() == location2.getLatitude()) {
                    score += 0.1;
                }
                if (location1.getLongitude() == location2.getLongitude()) {
                    score += 0.1;
                }
            }
            //check if the contents of the location are the same, if not then increment score.
            if (!compareStrings(location1.getAddress(), location2.getAddress())) {
                score += scoreMultiplier;
            }
            if (!compareStrings(location1.getState(), location2.getState())) {
                score += scoreMultiplier;
            }
            if (!compareStrings(location1.getCity(), location2.getCity())) {
                score += scoreMultiplier;
            }
            if (!compareStrings(location1.getZip(), location2.getZip())) {
                score += scoreMultiplier;
            }
        }
        return score;
    }

    /**
     * Compare the equality of two brewery objects.
     * @param one, first list of Brewery objects
     * @param two, second list of Brewery objects
     * @return double, an equality score of the breweries.
     */
    public double compareBreweries(List<Brewery> one, List<Brewery> two) {
        double score = 1.0;
        List<Brewery> largest = (one.size() > two.size() ? one : two);
        List<Brewery> smallest = (one.size() < two.size() ? one : two);
        double incrementAmount = score/largest.size();
        //nested for loop to compare each Brewery with all other Breweries found within the list
        for (Brewery a : largest) {
            boolean breweryFound = false;
            for (Brewery b : smallest) {
                //check to see if the name of the brewery is found in both.
                if (compareStrings(a.getName(), b.getName())) {
                    breweryFound = true;
                    //if so, then check to see if foodAvailable, location, and beers are in common. If not, then increment.
                    if (a.getFoodAvailable() != b.getFoodAvailable()) {
                        score -= incrementAmount;
                    }
                    //for location, multiply by the increment amount and the equality score of the location.
                    if (compareLocation(a.getLocation(), b.getLocation()) > 0.0) {
                        score -= incrementAmount * compareLocation(a.getLocation(), b.getLocation());
                    }
                    //for beer comparison, multiply by the increment amount and the equality score of the beers.
                    if (this.compareBeers(a.getBeers(), b.getBeers()) > 0.0) {
                        score -= incrementAmount * this.compareBeers(a.getBeers(), b.getBeers());
                    }
                    break;
                }
            }
            score = (!breweryFound ? score - incrementAmount : score);
        }
        //if it's less than zero, then just set equal to zero.
        score = (score < 0.0 ? 0.0 : score);
        return score;
    }

    /**
     * Return the overall equality score of the two json documents.
     * @return double, the equality score.
     */
    public double getComparison() {
        //set the initial score by comparing all breweries and child objects.
        this.score = this.compareBreweries(this.firstJSON.getBreweries(), this.secondJSON.getBreweries());
        //compare state bird, state, and state fish. If not in common, then decrement score.
        if (!compareStrings(this.firstJSON.getState_bird(), this.secondJSON.getState_bird())) {
            this.score -= 0.1;
        }
        if (!compareStrings(this.firstJSON.getState(), this.secondJSON.getState())) {
            this.score -= 0.2;
        }
        if (!compareStrings(this.firstJSON.getState_fish(), this.secondJSON.getState_fish())) {
            this.score -= 0.1;
        }
        System.out.println(this.score);
        return this.score;
    }
}
