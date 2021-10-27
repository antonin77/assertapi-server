CREATE TABLE HTTP_URI (
  ID_URI INT NOT NULL,
  VA_URI VARCHAR(250) NOT NULL,
  VA_MTH VARCHAR(7), 
  VA_CHR VARCHAR(10),
  VA_EXL VARCHAR(500),   
  PRIMARY KEY (ID_URI)
);

CREATE TABLE API_TEST (
  ID_TST     INT NOT NULL,
  ID_URI_EXT INT NOT NULL,
  ID_URI_ACT INT NOT NULL,
  VA_TST_DSC VARCHAR(250),
  VA_TST_PRL BOOLEAN,
  VA_TST_STR BOOLEAN,
  VA_TST_ENB BOOLEAN,
  VA_TST_DBG BOOLEAN,
  VA_API_APP VARCHAR(50),
  VA_API_ENV VARCHAR(10),
  PRIMARY KEY (ID_TST)
);