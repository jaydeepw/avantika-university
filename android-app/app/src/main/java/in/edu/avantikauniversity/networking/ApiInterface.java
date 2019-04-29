package in.edu.avantikauniversity.networking;

import in.edu.avantikauniversity.models.Profile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/profile")
    Call<Profile> getProfile(@Query("email") String email);
}