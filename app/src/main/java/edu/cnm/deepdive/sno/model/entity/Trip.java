package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Entity class to store relevant information regarding a trip to a ski resort in the database.
 */
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
  private Long userId;

  @ColumnInfo(name = "start_time")
  private Date startTime;

  @ColumnInfo(name = "end_time")
  private Date endTime;

  private float distance;

  @ColumnInfo(name = "max_speed")
  private int maxSpeed;

  /**
   * Returns the id of a trip.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id of a trip.
   * @param id A trip id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the id of a {@link User}.
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Sets the id of a {@link User}.
   * @param userId A user id
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Returns the start time of a trip.
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Sets the start time of a trip.
   * @param startTime The start time of a trip
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * Returns the end time of a trip.
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Sets the end time of a trip.
   * @param endTime The end time of a trip
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * Returns the distance of a trip.
   */
  public float getDistance() {
    return distance;
  }

  /**
   * Sets the distance of a trip.
   * @param distance The distance traveled on a trip
   */
  public void setDistance(float distance) {
    this.distance = distance;
  }

  /**
   * Returns the highest speed recorded in the database.
   */
  public int getMaxSpeed() {
    return maxSpeed;
  }

  /**
   * Sets the highest speed recorded in the database.
   * @param maxSpeed Top speed recorded
   */
  public void setMaxSpeed(int maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

}
