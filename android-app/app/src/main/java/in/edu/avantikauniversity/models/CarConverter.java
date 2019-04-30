package in.edu.avantikauniversity.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class CarConverter {
    @TypeConverter
    public static Car fromTimestamp(String carJson) {
        return new Gson().fromJson(carJson, Car.class);
    }

    @TypeConverter
    public static String dateToTimestamp(Car car) {
        return new Gson().toJson(car);
    }
}
