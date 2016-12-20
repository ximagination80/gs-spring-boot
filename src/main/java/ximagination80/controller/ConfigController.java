package ximagination80.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ximagination80.exception.KeyNotFound;
import ximagination80.repos.config.AppConfig;
import ximagination80.repos.config.AppConfigRepository;

import java.util.function.Supplier;

@RestController
public class ConfigController {

    @Autowired
    AppConfigRepository appConfigRepository;

    @RequestMapping("/config/get/{key}")
    public AppConfig config(@PathVariable String key) {
        return appConfigRepository.findByKey(key).orElseThrow((Supplier<RuntimeException>) KeyNotFound::new);
    }

    @RequestMapping("/config/set/{key}:{value}")
    public AppConfig config(@PathVariable String key, @PathVariable String value) {
        AppConfig appConfig = appConfigRepository.findByKey(key).orElse(new AppConfig(key, value, ""));
        appConfig.setValue(value);
        return appConfigRepository.save(appConfig);
    }

    @RequestMapping("/config/all")
    public Iterable<AppConfig> configs() {
        return appConfigRepository.findAll();
    }

}
