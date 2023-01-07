CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL,
  name varchar(64),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS post (
  id bigint(20) NOT NULL,
  userID bigint(20),
  theme varchar(64),
  path varchar(64),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
