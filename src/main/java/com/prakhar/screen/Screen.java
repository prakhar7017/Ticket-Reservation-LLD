package com.prakhar.screen;

import java.util.ArrayList;
import java.util.List;

import com.prakhar.movie.Movie;
import com.prakhar.shows.Show;

public class Screen {
    private String screenId;
    private int capacity;
    private String screenName;
    List<Show> shows = new ArrayList<>();
    public Screen(String screenId, String screenName, int capacity) {
        this.screenId = screenId;
        this.capacity = capacity;
        this.screenName = screenName;
    }

    public void addShow(Show show) {
        this.shows.add(show);
    }

    public List<Show> getShowsByMovie(Movie movie) {
        List<Show> result = new ArrayList<>();
        for (Show show : this.shows) {
            if (show.getMovie().equals(movie)) {
                result.add(show);
            }
        }
        return result;
    }
}
