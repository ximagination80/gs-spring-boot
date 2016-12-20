package ximagination80.repos.weather;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class Weather extends AbstractPersistable<Long> {

    private String city;
    private double temperature;
    private long time;

    public Weather() {
        this(null, 0D, 0L);
    }

    public Weather(String city, double temperature, long time) {
        this.city = city;
        this.temperature = temperature;
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
