DROP TABLE "OPENEAP"."KB_RIGHT_USER";
CREATE TABLE "OPENEAP"."KB_RIGHT_USER"
 ("ID"              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE, MINVALUE 1,NO MAXVALUE,NO CYCLE,ORDER), 
	"USERNAME"        VARCHAR(50),
	"REALNAME"        VARCHAR(30),
	"PASSWORD"        VARCHAR(50),
	"DEPT_ID"         INTEGER,
	"EMAIL"        VARCHAR(100),
	"PHONE"        VARCHAR(30),
	"LAST_LOGIN_TIME" TIMESTAMP,
	"ENABLE_FLAG"     VARCHAR(1) NOT NULL DEFAULT 'T',
	"UPDATE_USER"     VARCHAR(50),
	"UPDATE_TIME"     TIMESTAMP,
	"CREATE_USER"     VARCHAR(50),
	"CREATE_TIME"     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE "OPENEAP"."KB_RIGHT_USER" ADD CONSTRAINT "P_Key" PRIMARY KEY ("ID");

INSERT INTO "OPENEAP"."KB_RIGHT_USER" ("USERNAME","REALNAME","PASSWORD","CREATE_USER") VALUES 
('admin','管理员','7FEF6171469E80D32C0559F88B377245','admin');