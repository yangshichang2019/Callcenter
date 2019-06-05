DROP TABLE "OPENEAP"."KB_RIGHT";
CREATE TABLE "OPENEAP"."KB_RIGHT"
 ("ID"              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE, MINVALUE 1,NO MAXVALUE,NO CYCLE,ORDER), 
  "PARENT_ID"       INTEGER,
  "NUM"             VARCHAR(60),
  "REL"             VARCHAR(30),
	"NAME"            VARCHAR(50),
	"HREF"            VARCHAR(100),
	"TARGET"          VARCHAR(10),
	"FRESH"           VARCHAR(10),
	"EXTERNAL"        VARCHAR(10),
	"IS_PARENT"       VARCHAR(5) NOT NULL DEFAULT 'false',
  "SORT_ID"         INTEGER NOT NULL DEFAULT 0,
  "UPDATE_USER"     VARCHAR(50),
  "UPDATE_TIME"     TIMESTAMP,
	"CREATE_USER"     VARCHAR(50),
	"CREATE_TIME"     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE "OPENEAP"."KB_RIGHT" ADD CONSTRAINT "P_Key" PRIMARY KEY ("ID");
 
INSERT INTO "OPENEAP"."KB_RIGHT" ("PARENT_ID","NUM","REL","NAME","HREF","TARGET","FRESH","EXTERNAL","IS_PARENT","CREATE_USER") VALUES 
(0,'001','001','系统配置','','navTab','false','false','true','admin'),
(1,'001001','001001','用户权限','','navTab','false','false','true','admin'),
(2,'001001001','001001001','权限管理','right/toRightManage','navTab','true','false','false','admin'),
(2,'001001002','001001002','角色管理','right/toRoleManage','navTab','true','false','false','admin'),
(2,'001001003','001001003','用户管理','right/toUserManage','navTab','true','false','false','admin'),
(2,'001001004','001001004','部门管理','right/toDeptManage','navTab','true','false','false','admin'),
(2,'001001005','001001005','工作组管理','right/toGroupManage','navTab','true','false','false','admin'),
(1,'001002','001002','基础数据','','navTab','false','false','true','admin'),
(8,'001002001','001002001','配置参数','database/toDBParamManage','navTab','true','false','false','admin'),
(8,'001002002','001002002','数据字典','database/toDataBaseManage','navTab','true','false','false','admin'),
(0,'002','002','用户服务','','navTab','false','false','true','admin'),
(11,'002001','002001','用户工单','','navTab','false','false','true','admin'),
(12,'002001001','002001001','工单类型','/KFlow/flow/toFlowTypeList','navTab','true','false','false','admin'),
(12,'002001002','002001002','发起工单','/KFlow/flow/toPublicFlowTypeList','navTab','true','false','false','admin'),
(11,'002002','002002','预测回访','','navTab','false','false','true','admin'),
(15,'002002001','002002001','回访项目管理','/KKCallBack/callback/toCbProjectList','navTab','true','false','false','admin'),
(15,'002002002','002002002','数据整理管理','/KKCallBack/callback/toCbSortList','navTab','true','false','false','admin'),
(15,'002002003','002002003','未处理工单','/KKCallBack/callback/toCbDealList','navTab','true','false','false','admin'),
(15,'002002004','002002004','重复未处理','/KKCallBack/callback/toDealRepeatBillList','navTab','true','false','false','admin'),
(15,'002002007','002002007','自动处理','/KKCallBack/callback/toAutoDealBill','navTab','false','false','false','admin'),
(15,'002002005','002002005','系统日志','/KKCallBack/callback/toCbRecordList','navTab','true','false','false','admin'),
(15,'002002006','002002006','POM','','navTab','true','false','true','admin'),
(22,'002002006001','002002006001','POM控制台','http://172.40.1.197:8080/RoadtelOutcall/index.jsp','navTab','true','true','false','admin'),
(22,'002002006002','002002006002','活动管理','/KKCallBack/pom/toPomCampaignList','navTab','true','false','false','admin'),
(22,'002002006003','002002006003','联系人管理','/KKCallBack/pom/toPomContactStore','navTab','true','false','false','admin'),
(22,'002002006004','002002006004','数据源管理','/KKCallBack/pom/toPomImportDBList','navTab','true','false','false','admin'),
(15,'002002007','002002007','自定义工单','','navTab','true','false','true','admin'),
(27,'002002007001','002002007001','数据导入记录','/KKCallBack/callback/toCallBackBillImportList','navTab','true','false','false','admin'),
(27,'002002007002','002002007002','开机率单据','/KKCallBack/callback/toCallBackBillList?billtype=OPENRATE','navTab','true','false','false','admin'),
(11,'002003','002003','服务工具','','navTab','false','false','true','admin'),
(30,'002003001','002003001','工具箱','/KKTool/index','navTab','false','true','false','admin'),
(30,'002003002','002003002','收费标准','/KKTool/tool/toCostList','navTab','false','false','false','admin'),
(30,'002003003','002003003','生活电器','/KKTool/tool/toMachineNoList','navTab','false','false','false','admin'),
(11,'002004','002004','一次派务','','navTab','false','false','true','admin'),
(34,'002004001','002004001','数据信息维护','/OTS/ots/userAreaList','navTab','true','false','false','admin'),
(34,'002004002','002004002','网点地图绘制','/OTS/ots/userNetList','navTab','true','false','false','admin'),
(34,'002004003','002004003','服务商地图绘制','/OTS/ots/userCompanyList','navTab','true','false','false','admin'),
(34,'002004004','002004004','服务商信息管理','/OTS/ots/companyList','navTab','true','false','false','admin'),
(34,'002004005','002004005','网点请假登记','/OTS/ots/attendList','navTab','true','false','false','admin'),
(34,'002004006','002004006','派务测试','/OTS/fixCreate','navTab','true','true','false','admin'),
(34,'002004007','002004007','缓存监控','/OTS/ehcacheStatus','navTab','true','false','false','admin'),
(34,'002004008','002004008','错误记录','/OTS/ots/errorList','navTab','true','false','false','admin'),
(34,'002004009','002004009','地址更正','/OTS/ots/modifyList','navTab','true','false','false','admin'),
(34,'002004010','002004010','网点区域信息','/OTS/areaList?type=show','navTab','true','false','false','admin'),
(34,'002004011','002004011','分公司区域划分(查看)','/OTS/deptList?type=view','navTab','true','false','false','admin');


INSERT INTO "OPENEAP"."KB_RIGHT" ("PARENT_ID","NUM","REL","NAME","HREF","TARGET","FRESH","EXTERNAL","IS_PARENT","CREATE_USER") VALUES 
(11,'002005','002005','用户服务','','navTab','false','false','true','admin'),
(46,'002005001','002005001','夜间转接电话','/KKCallBack/night/infoList','navTab','true','false','false','admin');

INSERT INTO "OPENEAP"."KB_RIGHT" ("PARENT_ID","NUM","REL","NAME","HREF","TARGET","FRESH","EXTERNAL","IS_PARENT","CREATE_USER") VALUES 
(11,'002006','002006','红黄线工单','','navTab','false','false','true','admin'),
(48,'002006001','002006001','工单信息','/RedBill/warning/billList','navTab','true','false','false','admin'),
(48,'002006002','002006002','预警日志','/RedBill/warning/logList','navTab','true','false','false','admin');
