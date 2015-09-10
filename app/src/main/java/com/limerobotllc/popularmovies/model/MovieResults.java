package com.limerobotllc.popularmovies.model;

import java.io.Serializable;

public class MovieResults implements Serializable
{
    private static final long serialVersionUID = 3950727938159550304L;

    public MovieResults()
    {
        super();
    }

    public MovieResults(int page, Movie[] results)
    {
        super();
        this.page = page;
        this.results = results;
    }

    public int page;
    public Movie[] results;
}
