CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(64),
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS post (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  userID bigint(20),
  author varchar(10),
  theme varchar(64),
  path varchar(64),
  content varchar(255),
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;