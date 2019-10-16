package brewerydata;
public class Location {

    private String address;
    private String state;
    private String city;
    private String zip_code;
    private Coordinates GPS;

    public String getAddress() {
        return this.address;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getZip() {
        return this.zip_code;
    }

    public double getLatitude() {
        return this.GPS.getLatitude();
    }

    public double getLongitude() {
        return this.GPS.getLongitude();
    }

    public Coordinates getGPS() {
        return GPS;
    }
}
