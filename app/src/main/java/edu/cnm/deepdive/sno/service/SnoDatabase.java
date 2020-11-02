package edu.cnm.deepdive.sno.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.sno.model.dao.FavoriteMountainDao;
import edu.cnm.deepdive.sno.model.dao.GearDao;
import edu.cnm.deepdive.sno.model.dao.MountainDao;
import edu.cnm.deepdive.sno.model.dao.TripDao;
import edu.cnm.deepdive.sno.model.dao.UserDao;
import edu.cnm.deepdive.sno.model.entity.FavoriteMountain;
import edu.cnm.deepdive.sno.model.entity.Gear;
import edu.cnm.deepdive.sno.model.entity.Mountain;
import edu.cnm.deepdive.sno.model.entity.Trip;
import edu.cnm.deepdive.sno.model.entity.User;
import edu.cnm.deepdive.sno.service.SnoDatabase.Converters;
import java.util.Date;

@Database(entities =
    {User.class, Trip.class, Mountain.class, FavoriteMountain.class, Gear.class},
    version = 1)
@TypeConverters({Gear.GearType.class, Converters.class})
public abstract class SnoDatabase extends RoomDatabase {

  private static final String DB_NAME = "sno_db";

  public static Application context;

  public static void setContext(Application context) {
    SnoDatabase.context = context;
  }

  public static SnoDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract UserDao getUserDao();

  public abstract TripDao getTripDao();

  public abstract MountainDao getMountainDao();

  public abstract FavoriteMountainDao getFavoriteMountainDao();

  public abstract GearDao getGearDao();

  private static class InstanceHolder {

    private static final SnoDatabase INSTANCE = Room
        .databaseBuilder(context, SnoDatabase.class, DB_NAME)
        .build();

  }
  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }
}
