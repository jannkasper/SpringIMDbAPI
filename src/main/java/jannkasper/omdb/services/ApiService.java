package jannkasper.omdb.services;

import jannkasper.omdb.domain.Movie;
import jannkasper.omdb.domain.Search;

import java.util.List;

public interface ApiService {
    Movie getMovie (String title);
    List<Search>  getList (String title);
}
