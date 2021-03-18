package app.gameproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyAPI {

    // 유저 - 회원가입 요청
    @POST("/api/v1/user/")
    Call<UserItem> post_user(@Body UserItem post);

    // 유저 - 회원 전체 정보 출력
    @GET("/api/v1/user/")
    Call<List<UserItem>> get_user();

    // 유저 - 회원 아이디 별 정보 출력
    @GET("/api/v1/user/user_id/{user_id}/")
    Call<List<UserItem>> get_user_by_user_id(@Path("user_id") String user_id);

    // 어드민 - 게임 생성
    @POST("/api/v1/game/")
    Call<AdminItem> post_game(@Body AdminItem post);

    // 어드민 - 게임 전체 출력
    @GET("/api/v1/game/")
    Call<List<AdminItem>> get_game();

    // 어드민 - 게임 아이디 별 출력
    @GET("/api/v1/game/id/{id}/")
    Call<List<AdminItem>> get_game_by_id(@Path("id") String id);

    // 어드민 - 게임 이름 별 출력
    @GET("/api/v1/game/game_name/{game_name}/")
    Call<List<AdminItem>> get_game_by_game_name(@Path("game_name") String game_name);

    // 어드민 - 게임 온라인/오프라인 별 출력
    @GET("/api/v1/game/on_off/{on_off}/")
    Call<List<AdminItem>> get_game_by_on_off(@Path("on_off") String on_off);

    // 어드민 - 게임 삭제
    @DELETE("/api/v1/game/id/{id}/")
    Call<AdminItem> delete_game_by_id(@Path("id") String id);

    // 어드민 - 게임 수정
    @PUT("/api/v1/game/id/{id}/")
    Call<AdminItem> update_game_by_id(@Path("id") String id, @Body AdminItem update);
}
