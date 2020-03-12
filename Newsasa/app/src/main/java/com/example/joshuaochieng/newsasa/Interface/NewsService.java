package com.example.joshuaochieng.newsasa.Interface;

import com.example.joshuaochieng.newsasa.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {
    @GET("v2/sources?language=en")
    Call<WebSite> getSources();
}
