package in.edu.avantikauniversity.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import in.edu.avantikauniversity.models.CarConverter;
import in.edu.avantikauniversity.models.User;

@Database(entities = {User.class}, version = 1)
@TypeConverters({CarConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
