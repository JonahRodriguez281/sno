package edu.cnm.deepdive.sno.service;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.sno.R;
import edu.cnm.deepdive.sno.model.dao.FavoriteSkiResortDao;
import edu.cnm.deepdive.sno.model.dao.GearDao;
import edu.cnm.deepdive.sno.model.dao.SkiResortDao;
import edu.cnm.deepdive.sno.model.dao.TripDao;
import edu.cnm.deepdive.sno.model.dao.UserDao;
import edu.cnm.deepdive.sno.model.entity.FavoriteSkiResort;
import edu.cnm.deepdive.sno.model.entity.Gear;
import edu.cnm.deepdive.sno.model.entity.Gear.GearType;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import edu.cnm.deepdive.sno.model.entity.Trip;
import edu.cnm.deepdive.sno.model.entity.User;
import edu.cnm.deepdive.sno.service.SnoDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * The database for Sno
 */
@Database(entities =
    {User.class, Trip.class, SkiResort.class, FavoriteSkiResort.class, Gear.class},
    version = 1)
@TypeConverters({Gear.GearType.class, Converters.class})
public abstract class SnoDatabase extends RoomDatabase {

  private static final String DB_NAME = "sno_db";

  /**
   * Application context
   */
  public static Application context;

  /**
   * Sets the application context of {@link SnoDatabase}
   *
   * @param context Context of the application
   */
  public static void setContext(Application context) {
    SnoDatabase.context = context;
  }

  /**
   * Returns a singleton instance of SnoDatabase
   */
  public static SnoDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Returns an instance of {@link UserDao} using a singleton instance.
   */
  public abstract UserDao getUserDao();

  /**
   * Returns an instance of {@link TripDao} using a singleton instance.
   */
  public abstract TripDao getTripDao();

  /**
   * Returns an instance of {@link SkiResortDao} using a singleton instance.
   */
  public abstract SkiResortDao getSkiResortDao();

  /**
   * Returns an instance of {@link FavoriteSkiResortDao} using a singleton instance.
   */
  public abstract FavoriteSkiResortDao getFavoriteSkiResortDao();

  /**
   * Returns an instance of {@link GearDao} using a singleton instance.
   */
  public abstract GearDao getGearDao();

  private static class InstanceHolder {

    private static final SnoDatabase INSTANCE = Room
        .databaseBuilder(context, SnoDatabase.class, DB_NAME)
        .addCallback(new Callback())
        .build();

  }

  private static class Callback extends RoomDatabase.Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      insertSkiResorts();
      insertTrips();
      insertGear();
    }

    private void insertTrips() {
      List<Trip> trips = new LinkedList<>();
      Trip trip = new Trip();
      trip.setDistance(1024.56f);
      trip.setStartTime(new Date(1606806198));
      trip.setEndTime(new Date(1606842198));
      trip.setMaxSpeed(34);
      trips.add(trip);
      Trip trip2 = new Trip();
      trip2.setDistance(940.32f);
      trip2.setStartTime(new Date(1606806198));
      trip2.setEndTime(new Date(1606842198));
      trip2.setMaxSpeed(24);
      trips.add(trip2);
      SnoDatabase.getInstance().getTripDao().insert(trips)
          .subscribeOn(Schedulers.io())
          .subscribe(
              (ids) -> {
              },
              (throwable) -> Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable)
          );
    }

    private void insertGear() {
//      String[] boots = {"Burton Photon Boa", "Vans High Standard"};
//      String[] jackets = {"Burton Covert Insulated Jacket", "Burton Dunmore Jacket"};
//      String[] pants = {"Burton Covert Insulated Pants", "Volcom Carbon Pants"};
//      String[] goggles = {"Oakley Air Mail", "Oakley Line Miner", "Oakley Flight Deck"};
//      String[] helmets = {"Smith MIPS Helmet", "Oakley MOD 3"};
//      String[] snowboards = {"Travis Rice Pro", "GNU Head Space"};
      List<Gear> gearList = new LinkedList<>();
      Gear boots = new Gear();
      boots.setDescription("Burton Photon Boa");
      boots.setGearType(GearType.BOOTS);
      gearList.add(boots);
      Gear jacket = new Gear();
      jacket.setDescription("Burton Covert Insulated Jacket");
      jacket.setGearType(GearType.JACKET);
      gearList.add(jacket);
//      Gear pants = new Gear();
//      pants.setDescription("Burton Covert Insulated Pants");
//      pants.setGearType(GearType.PANTS);
//      gearList.add(pants);
//      Gear goggles = new Gear();
//      goggles.setDescription("Oakley Air Mail");
//      goggles.setGearType(GearType.GOGGLES);
//      gearList.add(goggles);
//      Gear helmet = new Gear();
//      helmet.setDescription("Smith MIPS Helmet");
//      helmet.setGearType(GearType.HELMET);
//      gearList.add(helmet);
//      Gear snowboard = new Gear();
//      snowboard.setDescription("Travis Rice Pro Model");
//      snowboard.setGearType(GearType.SNOWBOARD);
//      gearList.add(snowboard);
      SnoDatabase.getInstance().getGearDao().insert(gearList)
          .subscribeOn(Schedulers.io())
          .subscribe(
              (ids) -> {
              },
              (throwable) -> Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable)
          );
//      for (String jacket : jackets) {
//        Gear gear = new Gear();
//        gear.setGearType(GearType.JACKET);
//        gear.setDescription(jacket);
//        gearList.add(gear);
//      }
//      for (String pant : pants) {
//        Gear gear = new Gear();
//        gear.setGearType(GearType.PANTS);
//        gear.setDescription(pant);
//        gearList.add(gear);
//      }
//      for (String goggle : goggles) {
//        Gear gear = new Gear();
//        gear.setGearType(GearType.GOGGLES);
//        gear.setDescription(goggle);
//        gearList.add(gear);
//      }
//      for (String helmet : helmets) {
//        Gear gear = new Gear();
//        gear.setGearType(GearType.HELMET);
//        gear.setDescription(helmet);
//        gearList.add(gear);
//      }
//      for (String snowboard : snowboards) {
//        Gear gear = new Gear();
//        gear.setGearType(GearType.SNOWBOARD);
//        gear.setDescription(snowboard);
//        gearList.add(gear);
//      }

    }

    private void insertSkiResorts() {
      try (
          InputStream input = context.getResources().openRawResource(R.raw.resorts);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = CSVParser.parse(reader,
              CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreEmptyLines()
                  .withIgnoreSurroundingSpaces());
      ) {
        List<SkiResort> resorts = new LinkedList<>();
        for (CSVRecord record : parser) {
          SkiResort resort = new SkiResort();
          resort.setName(record.get(0));
          resort.setLatitude(Double.parseDouble(record.get(1)));
          resort.setLongitude(Double.parseDouble(record.get(2)));
          resorts.add(resort);
        }
        SnoDatabase.getInstance().getSkiResortDao().insert(resorts)
            .subscribeOn(Schedulers.io())
            .subscribe(
                (ids) -> {
                },
                (throwable) -> Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable)
            );
      } catch (IOException e) {
        Log.e(getClass().getSimpleName(), e.getMessage(), e);
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Converts a Date object to a Long, or a Long to a Date.
   */
  public static class Converters {

    /**
     * Returns a Long from a Date.
     *
     * @param value Date
     * @return A Long
     */
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    /**
     * Returns a Date from a Long
     *
     * @param value Long
     * @return A Date
     */
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }
}
