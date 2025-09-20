package com.prakhar.movie;

public class Movie {
    private String movieId;
    private String title;
    private String duration;

    public Movie(String movieId, String title, String duration) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }
    
    public String getMovieId() {
        return movieId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return movieId.equals(movie.movieId);
    }
    
    @Override
    public int hashCode() {
        return movieId.hashCode();
    }
}
