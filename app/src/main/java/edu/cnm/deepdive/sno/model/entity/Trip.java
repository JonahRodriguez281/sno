package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    indices = {
        @Index(value = "start_time"),
        @Index(value = "end_time"),
        @Index(value = "distance")
    },
    foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "user_id",
            childColumns = "user_id", onDelete = ForeignKey.CASCADE)
    }
)
public class Trip {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "trip_id")
  private long id;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "start_time")
  private Date startTime;

  @ColumnInfo(name = "end_time")
  private Date endTime;

  @ColumnInfo(name = "distance")
  private float distance;

  @ColumnInfo(name = "max_speed")
  private int maxSpeed;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public float getDistance() {
    return distance;
  }

  public void setDistance(float distance) {
    this.distance = distance;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(int maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
}
