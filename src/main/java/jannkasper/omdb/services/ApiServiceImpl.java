package jannkasper.omdb.services;

import jannkasper.omdb.domain.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiServiceImpl implements ApiService {

    RestTemplate restTemplate;

    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("http://www.omdbapi.com/") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public Movie getMovie(String title) {

        title = title.replace(" ", "+");
        title = title.replace("%20", "+");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .queryParam("apikey","3167f002")
                .queryParam("t", title);

        Movie movieData = restTemplate.getForObject(uriBuilder.toUriString(), Movie.class);

        return movieData;
    }
}
