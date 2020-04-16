package com.example.practicas_movies.retrofit;


import com.example.practicas_movies.models.CreditsFeed;
import com.example.practicas_movies.models.MovieDetail;
import com.example.practicas_movies.models.MovieFeed;
import com.example.practicas_movies.models.TVShowFeed;
import com.example.practicas_movies.models.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {


    //Movies
    @GET("movie/top_rated")
    Call<MovieFeed> getTopRated(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/now_playing")
    Call<MovieFeed> getNowPaying(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/upcoming")
    Call<MovieFeed> getUpcoming(@Query("api_key") String apiKey, @Query("language") String language, @Query("region") String region);

    @GET("/3/search/movie")
    Call<MovieFeed> getSearch(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query);

    @GET("movie/{movie_id}")
    Call<MovieDetail> getSearchById(@Path ("movie_id") String id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/videos")
    Call<Trailer> getSearchTrailerById(@Path ("movie_id") String id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/credits")
    Call<CreditsFeed> getCredits(@Path ("movie_id") String id, @Query("api_key") String apiKey, @Query("language") String language);


    //Series
    @GET("tv/top_rated")
    Call<TVShowFeed> getTopRatedTv(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/on_the_air")
    Call<TVShowFeed> getNowPayingTv(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("search/tv")
    Call<TVShowFeed> getSearchTv(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query);

}