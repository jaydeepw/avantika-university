package in.edu.avantikauniversity.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import in.edu.avantikauniversity.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
