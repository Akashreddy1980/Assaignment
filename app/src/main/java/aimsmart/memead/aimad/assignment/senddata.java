package aimsmart.memead.aimad.assignment;

public class senddata {
    private String title , imageurl , capital, region , subregion , population , languages, border;
    public senddata() { }

    public senddata(String title, String imageurl) {
        this.title = title;
        this.imageurl = imageurl;
    }

    public senddata(String capital, String region, String subregion, String population , String languages , String border) {
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.languages = languages;
        this.border = border;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getCapital() {
        return capital;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
