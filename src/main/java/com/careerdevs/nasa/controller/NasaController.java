package com.careerdevs.nasa.controller;

import com.careerdevs.nasa.model.NasaModel;
import com.careerdevs.nasa.repository.NasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api/nasa")
@CrossOrigin(origins = "http://localhost:4900") // FRONTEND SERVER
public class NasaController {
    @Autowired
    private Environment env;
    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=";
    private NasaRepository nasaRepository;

    @Value("${APOD_KEY}")
    private String apiKey;

    @GetMapping("/testkey")
    private String getApiKey () {
        return apiKey;
    }

    @GetMapping("/sql/all")
    public ResponseEntity<?> getAllDataSQL() {
        try {
            String key = env.getProperty("APOD_KEY", "DEMO_KEY");
            String url = nasaApodEndpoint + key;

            ArrayList<NasaModel> allData = (ArrayList<NasaModel>) nasaRepository.findAll();

            return ResponseEntity.ok(allData);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/sql/all")
    public ResponseEntity<?> uploadAllDataToSQL(RestTemplate restTemplate) {
        try {
            String key = env.getProperty("APOD_KEY", "DEMO_KEY");
            String url = nasaApodEndpoint + key;

            // retrieve data from JPH API and save to array of UserModels
            NasaModel[] allDatas = restTemplate.getForObject(url, NasaModel[].class);

            // checks if allUsers is present, otherwise exception will be thrown
            assert allDatas != null;

//             remove id from each user
            for (NasaModel allData : allDatas) {
                allData.removeId();
            }

            // saves users to database and updates each user's id field to the saved database ID
            nasaRepository.saveAll(Arrays.asList(allDatas));

            // response with data that was just saved to the database
            return ResponseEntity.ok(allDatas);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

}
