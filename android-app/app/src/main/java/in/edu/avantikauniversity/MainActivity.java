package in.edu.avantikauniversity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
                Log.d(TAG, "Working");
                Profile profile = response.body();
                showProfile(profile);
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void showProfile(Profile profile) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(profile);
        RecyclerView recyclerView = findViewById(R.id.rvContacts);
        ProfileAdapter adapter = new ProfileAdapter(list);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
