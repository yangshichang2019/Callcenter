DROP TABLE "OPENEAP"."KB_RIGHT_DEPT";
CREATE TABLE "OPENEAP"."KB_RIGHT_DEPT"
 ("ID"              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE, MINVALUE 1,NO MAXVALUE,NO CYCLE,ORDER), 
	"PARENT_ID"       INTEGER,
	"NUM"             VARCHAR(60),
	"NAME"            VARCHAR(50),
	"CATE"            VARCHAR(30),
	"DESC"            VARCHAR(200),
	"IS_PARENT"       VARCHAR(5) NOT NULL DEFAULT 'false',
	"SORT_ID"         INTEGER NOT NULL DEFAULT 0,
	"UPDATE_USER"     VARCHAR(50),
	"UPDATE_TIME"     TIMESTAMP,
	"CREATE_USER"     VARCHAR(50),
	"CREATE_TIME"     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
 ); 