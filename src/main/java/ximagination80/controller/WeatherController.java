package ximagination80.controller;

import com.google.common.collect.ImmutableList;
import org.bitpipeline.lib.owm.WeatherForecastResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ximagination80.repos.config.AppConfig;
import ximagination80.repos.config.AppConfigRepository;
import ximagination80.repos.weather.Weather;
import ximagination80.repos.weather.WeatherRepository;
import ximagination80.service.OpenWeatherClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherController {

    public static final ImmutableList<String> CITIES
            = new ImmutableList.Builder<String>()
            .add("Kiev")
            .add("Kharkiv")
            .add("Lviv")
            .add("Samara")
            .add("Istanbul")
            .add("Mirnyy")
            .add("Tokyo")
            .add("Tarawa")
            .add("Dhaka")
            .add("Apia")
            .add("Jakarta")
            .add("Noumea")
            .build();


    @Autowired
    AppConfigRepository appConfigRepository;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    OpenWeatherClient openWeatherClient;

    @RequestMapping("/weather/in/{city}")
    public Object cachedCity(@PathVariable String city) {
        return weatherRepository.findByCityOrderByTimeDesc(city);
    }

    @RequestMapping("/weather/all")
    public Object configs() {
        return weatherRepository.findAllByOrderByTimeDesc();
    }

    @RequestMapping("/load/forecasts")
    public String load() throws IOException {
        List<String> cities = getCities();
        for (String city : cities) {
            WeatherForecastResponse wfr = openWeatherClient.getOwmClient().forecastWeatherAtCity(city);
            weatherRepository.save(wfr.getForecasts().stream().map(e ->
                    new Weather(wfr.getCity().getName(), e.getMain().getTemp(), e.getCalcDateTime())
            ).collect(Collectors.toList()));
        }
        return "Cities have been loaded to the database " + cities;
    }

    private List<String> getCities() {
        ArrayList<String> copy = new ArrayList<>(CITIES);
        Collections.shuffle(copy);

        int configValue = Integer.parseInt(appConfigRepository.findByKey("count").map(AppConfig::getValue).orElse("1"));
        return copy.stream().limit(Math.min(configValue, CITIES.size())).collect(Collectors.toList());
    }
}
