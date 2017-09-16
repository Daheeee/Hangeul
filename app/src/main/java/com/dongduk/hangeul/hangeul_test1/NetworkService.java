package com.dongduk.hangeul.hangeul_test1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jiyoungwon on 2017. 9. 17..
 */


public interface NetworkService {
    @POST("/words/")
    Call<Word> post_word(@Body MyWord word);

    @PATCH("/api/words/{pk}/")
    Call<Word> patch_word(@Path("pk") int pk, @Body MyWord word);

    @DELETE("/api/words/{pk}/")
    Call<Word> delete_word(@Path("pk") int pk);

    @GET("/api/words/")
    Call<Word> get_word();

    @GET("/api/versions/{pk}/")
    Call<Word> get_pk_word(@Path("pk") int pk);


}
