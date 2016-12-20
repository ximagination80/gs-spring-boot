package ximagination80.repos.weather;

import org.springframework.data.repository.CrudRepository;
import ximagination80.repos.config.AppConfig;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    List<Weather> findByCityOrderByTimeDesc(String city);

    List<Weather> findAllByOrderByTimeDesc();

}
