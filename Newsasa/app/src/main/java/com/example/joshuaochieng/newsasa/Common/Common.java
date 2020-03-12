package com.example.joshuaochieng.newsasa.Common;

import com.example.joshuaochieng.newsasa.Interface.NewsService;
import com.example.joshuaochieng.newsasa.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL="https://newsapi.org/";

    public static final String API_KEY="5f1ca67a3b0c4cb6893dc823836c22e2";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }
}
