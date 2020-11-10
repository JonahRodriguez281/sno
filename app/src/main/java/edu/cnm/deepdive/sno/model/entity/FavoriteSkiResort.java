package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
    primaryKeys = {"user_id", "ski_resort_id"},
    foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "user_id",
            childColumns = "user_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = SkiResort.class, parentColumns = "ski_resort_id",
            childColumns = "ski_resort_id", onDelete = ForeignKey.CASCADE)
    }
)
public class FavoriteSkiResort {

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "ski_resort_id", index = true)
  private long skiResortId;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getSkiResortId() {
    return skiResortId;
  }

  public void setSkiResortId(long skiResortId) {
    this.skiResortId = skiResortId;
  }
}
