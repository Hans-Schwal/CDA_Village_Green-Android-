package com.afpa.village_green;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
// Cette interface va jouer le rôle de l'API
public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();
}
// Cette interface va être utilisée par Retrofit pour aller chercher le Json à l'adresse générée par l'annotation: http://10.0.2.2:8080/index.php/api/liste/
