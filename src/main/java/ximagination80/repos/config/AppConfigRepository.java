package ximagination80.repos.config;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppConfigRepository extends CrudRepository<AppConfig, Long> {

    Optional<AppConfig> findByKey(String key);

}
