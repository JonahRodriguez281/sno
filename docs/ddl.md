```sqlite
CREATE TABLE IF NOT EXISTS `User`
(
    `user_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth_Key`    TEXT,
    `display_name` TEXT                              NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_User_oauth_Key` ON `User` (`oauth_Key`);

CREATE TABLE IF NOT EXISTS `Trip`
(
    `trip_id`    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `user_id`    INTEGER                           NOT NULL,
    `start_time` INTEGER,
    `end_time`   INTEGER,
    `distance`   REAL                              NOT NULL,
    `max_speed`  INTEGER                           NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Trip_start_time` ON `Trip` (`start_time`);

CREATE INDEX IF NOT EXISTS `index_Trip_end_time` ON `Trip` (`end_time`);

CREATE INDEX IF NOT EXISTS `index_Trip_distance` ON `Trip` (`distance`);

CREATE INDEX IF NOT EXISTS `index_Trip_user_id` ON `Trip` (`user_id`);

CREATE TABLE IF NOT EXISTS `SkiResort`
(
    `ski_resort_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`          TEXT                              NOT NULL,
    `latitude`      REAL                              NOT NULL,
    `longitude`     REAL                              NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_SkiResort_name` ON `SkiResort` (`name`);

CREATE INDEX IF NOT EXISTS `index_SkiResort_latitude` ON `SkiResort` (`latitude`);

CREATE INDEX IF NOT EXISTS `index_SkiResort_longitude` ON `SkiResort` (`longitude`);

CREATE TABLE IF NOT EXISTS `FavoriteSkiResort`
(
    `user_id`       INTEGER NOT NULL,
    `ski_resort_id` INTEGER NOT NULL,
    PRIMARY KEY (`user_id`, `ski_resort_id`),
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`ski_resort_id`) REFERENCES `SkiResort` (`ski_resort_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_FavoriteSkiResort_user_id` ON `FavoriteSkiResort` (`user_id`);

CREATE INDEX IF NOT EXISTS `index_FavoriteSkiResort_ski_resort_id` ON `FavoriteSkiResort` (`ski_resort_id`);

CREATE TABLE IF NOT EXISTS `Gear`
(
    `gear_id`     INTEGER NOT NULL,
    `user_id`     INTEGER NOT NULL,
    `description` TEXT,
    `gear_type`   INTEGER,
    PRIMARY KEY (`gear_id`)
);

CREATE INDEX IF NOT EXISTS `index_Gear_gear_type` ON `Gear` (`gear_type`);

CREATE INDEX IF NOT EXISTS `index_Gear_description` ON `Gear` (`description`);
```
[ddl.sql](sql/ddl.sql)