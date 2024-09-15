package com.ai.devs.ai_devs.service;
import com.ai.devs.ai_devs.model.PostData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ControllerAdvice
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String[]> getTwoStrings() {
        String url = "https://poligon.aidevs.pl/dane.txt";
        String fullResp = "";
        try {
            fullResp = restTemplate.getForObject(url, String.class);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        if(fullResp.isBlank()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
        }

        return new ResponseEntity<>(fullResp.split("\n"), HttpStatus.OK);
    }

    public ResponseEntity postTwoStrings() {
        ResponseEntity<String[]> strings = getTwoStrings();
        String response;
        if(strings.getStatusCode() != HttpStatus.OK)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        String url = "https://poligon.aidevs.pl/verify";
        PostData body = new PostData("POLIGON","",strings.getBody());
        try{
             response = restTemplate.postForObject(url, body, String.class);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.get("code").equals(0)){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}