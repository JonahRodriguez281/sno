package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    primaryKeys = {"user_id", "mountain_id"},
    foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "user_id",
            childColumns = "user_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Mountain.class, parentColumns = "mountain_id",
            childColumns = "mountain_id", onDelete = ForeignKey.CASCADE)
    }
)
public class FavoriteMountain {

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "mountain_id", index = true)
  private long mountainId;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getMountainId() {
    return mountainId;
  }

  public void setMountainId(long mountainId) {
    this.mountainId = mountainId;
  }
}
