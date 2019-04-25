package in.edu.avantikauniversity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout the UI
        setContentView(R.layout.activity_main);

        // call the background service to fetch the data
        Intent intent = new Intent(this, ProfileService.class);
        intent.putExtra("email", "jaydeep.w@noemail.com");
        startService(intent);
    }

    private void showProfile(Profile profile) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(profile);
        list.add(profile.bio);
        RecyclerView recyclerView = findViewById(R.id.rvContacts);
        ProfileAdapter adapter = new ProfileAdapter(list);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(profileReceived, new IntentFilter("com.example.intent.filter"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(profileReceived);
    }

    BroadcastReceiver profileReceived = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "aciton: " + action);
            if (action.equalsIgnoreCase("com.example.intent.filter")) {
                String profileJson = intent.getStringExtra("data");
                Profile profile = new Gson().fromJson(profileJson, Profile.class);
                Log.d(TAG, "name: " + profile.name_first);
                Log.d(TAG, "dp: " + profile.dp);
                showProfile(profile);
            } else {
                Log.d(TAG, "Maybe useful intent action, but not of our interest");
            }

        }
    };
}
