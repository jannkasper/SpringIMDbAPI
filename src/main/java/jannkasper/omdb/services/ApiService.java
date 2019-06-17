package jannkasper.omdb.services;

import jannkasper.omdb.domain.Movie;

public interface ApiService {
    Movie getMovie (String title);
}
