CREATE TABLE IF NOT EXISTS O_REQ (
  ID_REQ BIGINT NOT NULL,
  VA_URI VARCHAR(500) NOT NULL,
  VA_MTH VARCHAR(8) NOT NULL,
  VA_HDR CLOB(1K),  --JSON : Map<String,String>
  VA_BDY CLOB(1M),  --JSON
  VA_NME VARCHAR(50),
  VA_DSC VARCHAR(500),
  VA_VRS INT NOT NULL,
  VA_PRL BOOLEAN NOT NULL, --parallel
  VA_ENB BOOLEAN NOT NULL, --enable
  VA_STT VARCHAR(50),  --list of acceptable status
  CNT_CMP CLOB(1K), --JSON
  PRIMARY KEY (ID_REQ)
);

CREATE TABLE IF NOT EXISTS O_REQ_ENV (
  ID_REQ     BIGINT NOT NULL,
  VA_APP VARCHAR(50) NOT NULL,
  VA_RLS VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS O_REQ_MIG (
  ID_MIG   BIGINT NOT NULL,
  ID_REQ_1 BIGINT NOT NULL,
  ID_REQ_2 BIGINT NOT NULL,
  CNT_CMP CLOB(1K), --JSON
  PRIMARY KEY (ID_MIG)
);

CREATE TABLE IF NOT EXISTS E_ASR (
  ID_EXC     BIGINT NOT NULL,
  ID_REQ     BIGINT, --nullable => local test files
  DH_STB_STR TIMESTAMP(3) NOT NULL,
  DH_STB_END TIMESTAMP(3) NOT NULL,
  VA_STB_SIZ INT NOT NULL,
  VA_STB_STT INT NOT NULL,
  DH_LTS_STR TIMESTAMP(3) NOT NULL,
  DH_LTS_END TIMESTAMP(3) NOT NULL,
  VA_LTS_SIZ INT NOT NULL,
  VA_LTS_STT INT NOT NULL,
  VA_STT VARCHAR(5) NOT NULL,
  VA_STP VARCHAR(20),
  PRIMARY KEY (ID_EXC, ID_REQ)
);

CREATE TABLE IF NOT EXISTS E_ASR_EXC (
  ID_EXC BIGINT NOT NULL,
  VA_USR VARCHAR(50),
  VA_OS  VARCHAR(20),
  VA_ADR VARCHAR(15),
  VA_JRE VARCHAR(15),
  VA_BRC VARCHAR(15),
  VA_APP VARCHAR(50) NOT NULL,
  VA_STB_RLS VARCHAR(20) NOT NULL,
  VA_LTS_RLS VARCHAR(20) NOT NULL,
  VA_STT VARCHAR(7) NOT NULL,
  PRIMARY KEY (ID_EXC)
);

CREATE TABLE IF NOT EXISTS R_ENV (
  ID_ENV INT NOT NULL,
  VA_HST VARCHAR(200) NOT NULL,
  VA_PRT INT NOT NULL,
  VA_APP VARCHAR(50) NOT NULL,
  VA_RLS VARCHAR(20) NOT NULL,
  FL_PRD BOOLEAN NOT NULL,
  AUT_CNF CLOB(1K), --JSON VA_API_AUT_HST VARCHAR(200) VA_AUT_MTH VARCHAR(20)
  PRIMARY KEY (ID_ENV)
);

