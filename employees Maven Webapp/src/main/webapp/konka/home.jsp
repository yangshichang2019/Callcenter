<%@ page language="java"  pageEncoding="UTF-8"%>


<html>
<head>
<title>简单实用国产jQuery UI框架 - DWZ富客户端框架(J-UI.com)</title>

<link href="/employees/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/employees/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/employees/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/employees/uploadify/css/uploadify.css" rel="stylesheetemployeesype="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lt IE 9]><script src="js/speedup.js" type="text/javascript"></script><script src="js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="/employees/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<script src="/employees/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/employees/js/jquery.validate.js" type="text/javascript"></script>
<script src="/employees/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/employees/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="/employees/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/employees/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="/employees/chart/raphael.js"></script>
<script type="text/javascript" src="/employees/chart/g.raphael.js"></script>
<script type="text/javascript" src="/employees/chart/g.bar.js"></script>
<script type="text/javascript" src="/employees/chart/g.line.js"></script>
<script type="text/javascript" src="/employees/chart/g.pie.js"></script>
<script type="text/javascript" src="/employees/chart/g.dot.js"></script>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6PYkS1eDz5pMnyfO0jvBNE0F"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>

<script src="/employees/js/dwz.core.js" type="text/javascript"></script>
<script src="/employees/js/dwz.util.date.js" type="text/javascript"></script>
<script src="/employees/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="/employees/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="/employees/js/dwz.drag.js" type="text/javascript"></script>
<script src="/employees/js/dwz.tree.js" type="text/javascript"></script>
<script src="/employees/js/dwz.accordion.js" type="text/javascript"></script>
<script src="/employees/js/dwz.ui.js" type="text/javascript"></script>
<script src="/employees/js/dwz.theme.js" type="text/javascript"></script>
<script src="/employees/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="/employees/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/employees/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/employees/js/dwz.navTab.js" type="text/javascript"></script>
<script src="/employees/js/dwz.tab.js" type="text/javascript"></script>
<script src="/employees/js/dwz.resize.js" type="text/javascript"></script>
<script src="/employees/js/dwz.dialog.js" type="text/javascript"></script>
<script src="/employees/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/employees/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="/employees/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="/employees/js/dwz.stable.js" type="text/javascript"></script>
<script src="/employees/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="/employees/js/dwz.ajax.js" type="text/javascript"></script>
<script src="/employees/js/dwz.pagination.js" type="text/javascript"></script>
<script src="/employees/js/dwz.database.js" type="text/javascript"></script>
<script src="/employees/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="/employees/js/dwz.effects.js" type="text/javascript"></script>
<script src="/employees/js/dwz.panel.js" type="text/javascript"></script>
<script src="/employees/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="/employees/js/dwz.history.js" type="text/javascript"></script>
<script src="/employees/js/dwz.combox.js" type="text/javascript"></script>
<script src="/employees/js/dwz.file.js" type="text/javascript"></script>
<script src="/employees/js/dwz.print.js" type="text/javascript"></script>

<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换时下面dwz.regional.zh.js还需要引入)
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="/employees/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/employees/konka/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>

</head>

<body>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">标志</a>
				<ul class="nav">
					
				</ul>
			
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						
							
							</li>
							
							<li><a>客服管理</a>
								<ul>
								
									<li><a href="${pageContext.request.contextPath }/agent/agentList" target="navTab" rel="w_table">客服账号管理</a></li>
								
								</ul>
							</li>
							</li>
						</ul>
							<ul class="tree treeFolder">
						
							
							</li>
							
							<li><a>权限管理</a>
								<ul>
								
									<li><a href="${pageContext.request.contextPath}/userManage/listUsers" target="navTab" rel="userManage">用户管理</a></li>
								
								</ul>
							</li>
							</li>
						</ul>
						
						<ul class="tree treeFolder">
						
							
							</li>
							
							<li><a>字典管理</a>
								<ul>
								
									<li><a href="${pageContext.request.contextPath}/systemddl/listSystemDDl" target="navTab" rel="systemddlManage">字典管理</a></li>
								
								</ul>
							</li>
							</li>
						</ul>
						
							<ul class="tree treeFolder">
						
							
							</li>
							
							<li><a>新字典管理</a>
								<ul>
								
									<li><a href="${pageContext.request.contextPath}/systemdd/listSystemDD" target="navTab" rel="systemDDManage">新字典管理</a></li>
								
								</ul>
							</li>
							</li>
						</ul>
						
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>典型页面</h2>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>流程演示</h2>
					</div>
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<p><a href="https://code.csdn.net/dwzteam/dwz_jui/tree/master/doc" target="_blank" style="line-height:19px"><span></span></a></p>
								<p><a href="http://pan.baidu.com/s/18Bb8Z" target="_blank" style="line-height:19px"></a></p>
							</div>
							<div class="center">
								<p><font style="line-height:20px">康佳呼叫中心工号管理</font></p>
							</div>
							
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">


<h2>首页</h2>
<div class="unit"><a href="https://git.oschina.net/dwzteam/dwz_jui" target="_blank"></a></div>
<div class="unit"><a href="https://git.oschina.net/dwzteam/dwz_group" target="_blank"></a></div>
<div class="unit"><a href="https://code.csdn.net/dwzteam/dwz_ssh2" target="_blank"></a></div>
<div class="unit"><a href="https://code.csdn.net/dwzteam/dwz_springmvc" target="_blank"></a></div>
<div class="unit"><a href="https://code.csdn.net/dwzteam/dwz_thinkphp" target="_blank"></a></div>
<div class="unit"><a href="https://code.csdn.net/dwzteam/dwz_zendframework" target="_blank"></a></div>
<div class="unit"><a href="http://www.yiiframework.com/extension/dwzinterface/" target="_blank"></a></div>



<div class="divider"></div>
<h2></h2>


<div class="divider"></div>


<a class="button" href="http://code.csdn.net/groups/2155" target="_blank"><span></span></a>
						</div>
						
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?width=0&height=430&fansRow=2&ptype=1&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=1739071261&verifier=c683dfe7"></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">DWZ团队</a> 京ICP备15053290号-2</div>

</body>
</html>