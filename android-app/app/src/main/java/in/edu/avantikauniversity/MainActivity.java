package in.edu.avantikauniversity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<Profile> call = ApiClient
                            .getRetrofit()
                            .getProfile("jaydeep.w@noemail.com");

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Log.d(TAG,"Working");
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
    }
}
