package edu.cnm.deepdive.sno.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity class to store the name and location regarding various ski resorts in the database.
 */
@Entity(
    indices = {
        @Index(value = "name", unique = true),
        @Index(value = "latitude"),
        @Index(value = "longitude")
    }
)
public class SkiResort {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "ski_resort_id")
  private long skiResortId;

  @NonNull
  private String name;

  private double latitude;

  private double longitude;

  /**
   * Returns the id of a ski resort.
   */
  public long getSkiResortId() {
    return skiResortId;
  }

  /**
   * Sets the id of a ski resort.
   * @param skiResortId A ski resort id
   */
  public void setSkiResortId(long skiResortId) {
    this.skiResortId = skiResortId;
  }

  /**
   * Returns the name of a ski resort.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets the name of a ski resort.
   * @param name Name of the ski resort
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Returns the latitude of a ski resort.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude of a ski resort.
   * @param latitude The latitude of a ski resort.
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Returns the longitude of a ski resort
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude of a ski resort
   * @param longitude
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
