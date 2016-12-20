package ximagination80.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ximagination80.Settings;

import javax.annotation.PostConstruct;

@Service
public class OpenWeatherClient {

    @Autowired
    Settings settings;

    private NewOwmClient owmClient = new NewOwmClient();

    @PostConstruct
    public void init() throws Exception {
        owmClient.setAPPID(settings.appid);
    }

    public NewOwmClient getOwmClient() {
        return owmClient;
    }
}
