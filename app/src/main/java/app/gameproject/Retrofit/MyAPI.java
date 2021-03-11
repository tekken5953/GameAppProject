package app.gameproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPI {

    @POST("/api/v1/user/")
    Call<UserItem> post_user(@Body UserItem post);

    @GET("/api/v1/user/")
    Call<List<UserItem>> get_user();

    @GET("/api/v1/user/user_id/{user_id}/")
    Call<List<UserItem>> get_user_by_user_id(@Path("user_id") String user_id);

}
