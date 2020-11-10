package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "latitude")
  private double latitude;

  @ColumnInfo(name = "longitude")
  private double longitude;

  public long getSkiResortId() {
    return skiResortId;
  }

  public void setSkiResortId(long skiResortId) {
    this.skiResortId = skiResortId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
