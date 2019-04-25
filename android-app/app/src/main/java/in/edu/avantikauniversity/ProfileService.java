package in.edu.avantikauniversity;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileService extends IntentService {

    public static final String TAG = ProfileService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ProfileService(String name) {
        super(name);
    }

    public ProfileService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "==> Service is being called");
        String email = intent.getStringExtra("email");
        Log.d(TAG, "Calling the API for the email: " + email);
        Call<Profile> call = ApiClient
                .getRetrofit()
                .getProfile(email);

        try {
            // using the same thread as that of the service.
            Response<Profile> result = call.execute();
            if (result.code() == HttpsURLConnection.HTTP_OK) {
                Profile profile = result.body();
                Intent returningIntent = new Intent("com.example.intent.filter");
                String profileJson = new Gson().toJson(profile);
                returningIntent.putExtra("data", profileJson);
                sendBroadcast(returningIntent);
            } else {
                Log.e(TAG,"Some http error" + result.code()
                        + " " + result.message());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
