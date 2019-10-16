package brewerydata;
import java.util.List;

public class Brewery {

    private String name;
    private Location location;
    private boolean FoodAvailable;
    private List<Beer> beers;

    public String getName() {
        return this.name;
    }

    public Location getLocation() {
        return location;
    }

    public boolean getFoodAvailable() {
        return FoodAvailable;
    }

    public List<Beer> getBeers() {
        return beers;
    }

}