package jannkasper.omdb.services;

import jannkasper.omdb.domain.Movie;
import jannkasper.omdb.domain.MovieData;
import jannkasper.omdb.domain.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    RestTemplate restTemplate;

    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("http://www.omdbapi.com/") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<Search> getList (String title) {

        title = title.replace(" ", "+");
        title = title.replace("%20", "+");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .queryParam("apikey","3167f002")
                .queryParam("s", title);


        MovieData movieData = restTemplate.getForObject(uriBuilder.toUriString(), MovieData.class);

        return movieData.getSearch();
    }

    @Override
    public Movie getMovie(String imdbID) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .queryParam("apikey","3167f002")
                .queryParam("i", imdbID);

        Movie movie = restTemplate.getForObject(uriBuilder.toUriString(), Movie.class);

        System.out.println(movie.getDirector());

        return movie;
    }
}
