create schema torrent;
create userEntity postgres;
alter userEntity postgres with password 'admin';

CREATE TABLE "tracker"
(
 "id"       varchar(36) NOT NULL,
 "status"     varchar(8) NOT NULL,
 CONSTRAINT "PK_tracker" PRIMARY KEY ( "id" )
);


CREATE TABLE "user"
(
 "id"       varchar(36) NOT NULL,
 "status"     varchar(8) NOT NULL,
 "tracker_id" varchar(36) not null,
 CONSTRAINT "PK_user" PRIMARY KEY ( "id" ),
  CONSTRAINT "FK_user"  foreign KEY(tracker_id) REFERENCES "tracker"(id)
);



CREATE TABLE "file"
(
 "id"       varchar(36) NOT NULL,
 "name" varchar(36) not null,
 "size"     double precision NOT NULL,
 "user_id" varchar(36) not null,
 CONSTRAINT "PK_file" PRIMARY KEY ( "id" ),
  CONSTRAINT "FK_file"  foreign KEY(user_id) REFERENCES "user"(id)
);
