package brewerydata;
import java.util.List;

public class StateBrewing {

    private String state;
    private String state_fish;
    private String state_bird;
    private List<Brewery> breweries;

    public String getState() {
        return this.state;
    }

    public String getState_fish() {
        return this.state_fish;
    }

    public String getState_bird() {
        return this.state_bird;
    }

    public List<Brewery> getBreweries() {
        return this.breweries;
    }
}
