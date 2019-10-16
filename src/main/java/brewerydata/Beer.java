package brewerydata;

public class Beer {

    private String name;
    private String color;
    private int international_bitterness_units;
    private double alcohol_by_volume;

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getInternational_bitterness_units() {
        return this.international_bitterness_units;
    }

    public double getAlcohol_by_volume() {
        return this.alcohol_by_volume;
    }
}
