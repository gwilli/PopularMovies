package com.limerobotllc.popularmovies.model;

import java.io.Serializable;

public class MovieResults implements Serializable
{
    private static final long serialVersionUID = 3950727938159550304L;

    public int page;
    public Movie[] results;
}
