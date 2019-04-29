package in.edu.avantikauniversity.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}
