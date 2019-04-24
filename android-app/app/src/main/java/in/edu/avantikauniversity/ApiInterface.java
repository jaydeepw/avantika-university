package in.edu.avantikauniversity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/profile")
    Call<Profile> getProfile(@Query("email") String email);
}