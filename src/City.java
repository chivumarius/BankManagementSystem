public class City {

    // ▼ "Variables" ▼
    String cityName;
    String regionName;



    // ▬ "Constructor" ▬
    public City(String cityName, String regionName) {
        this.cityName = cityName;
        this.regionName = regionName;
    }




    // ▬ "toString()" Method ▬
    @Override
    public String toString() {
        return cityName + " - " +regionName;
    }
}
