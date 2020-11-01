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

  @ColumnInfo(name = "user_id")
  private long userId;

  @ColumnInfo(name = "start_time")
  private Date startTime;

  @ColumnInfo(name = "end_time")
  private Date endTime;

  @ColumnInfo(name = "distance")
  private float distance;
}
