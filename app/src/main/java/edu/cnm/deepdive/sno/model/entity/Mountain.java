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
public class Mountain {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "mountain_id")
  private long mountainId;

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "latitude")
  private double latitude;

  @ColumnInfo(name = "longitude")
  private double longitude;
}
