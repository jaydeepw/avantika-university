package in.edu.avantikauniversity.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import in.edu.avantikauniversity.ProfileService;
import in.edu.avantikauniversity.R;
import in.edu.avantikauniversity.db.AppDatabase;
import in.edu.avantikauniversity.models.Car;
import in.edu.avantikauniversity.models.Profile;
import in.edu.avantikauniversity.models.User;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout the UI
        setContentView(R.layout.activity_main);

        // call the background service to fetch the data
        Intent intent = new Intent(this, ProfileService.class);
        intent.putExtra("email", "jaydeep.w@noemail.com");
        startService(intent);

        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readTable();
            }
        });

        dbThread.start();

        saveKeyValues();
        retrieveKeyValues();
    }

    private void retrieveKeyValues() {
        String username = sharedPref
                .getString("in.edu.avatikauniv.username", "unknown");
        Log.d(TAG,"==> username: " + username);
    }

    private void saveKeyValues() {
        // create the SP object.
        sharedPref = getSharedPreferences(
                "in.edu.avatikauniv.user_information", Context.MODE_PRIVATE);

        // start editing/saving
        SharedPreferences.Editor editor = sharedPref.edit();
        // tell SP, which value to store for which key.
        editor.putString("in.edu.avatikauniv.username", "jaydeepw");
        // actually save the data.
        editor.apply();
    }

    private void readTable() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "alumni.db").build();

        List<User> allUsers = db.userDao().getAll();
        Log.d(TAG, "allUsers.count: " + allUsers.size());
        User first = allUsers.get(0);
        Car car = first.car;
        Log.d(TAG, "allUsers[0]: " + car.make);
        Log.d(TAG, "allUsers[0]: " + car.model);
        Log.d(TAG, "allUsers[0]: " + car.type);
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
