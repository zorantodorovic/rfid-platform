package hr.fer.service;


import hr.fer.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class IoTService {

    private final SensorRepository sensorRepository;
    protected String ip = "http://172.20.10.11:8080";
    protected String myIp = "172.20.20.10:8080";

    @Autowired
    public IoTService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Boolean pingSensor(String url) throws IOException {
        URL obj = new URL("http://" + url + "/RFID/rest/url");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setConnectTimeout(2000);
        try {
            int responseCode = con.getResponseCode();
            if (responseCode == 200)
            {
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public Boolean changeSensorIpAdress() throws IOException {
        URL obj = new URL(ip + "/RFID/rest/url/ACCESS_SERVICE_URL/" + myIp);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setConnectTimeout(2000);
        try {
            int responseCode = con.getResponseCode();
            if (responseCode == 200)
            {
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
