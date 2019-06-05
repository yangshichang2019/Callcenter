你好 ${user}

${date1?string("yyyy-MM-dd HH:mm:ss")}

<#if user=="老张">
  高
</#if>

--------------------------
<#if random gte 60>
及格
<#elseif random gte 80>
良好
<#else>
不及格
</#if>

-------------------------

<#list list as address>
<b>${address.country}</b></br>
</#list>

<#include "include.txt">

----------
<#macro m1>
<b>aaaaa</b></br>
<b>bbbbb</b></br>
</#macro>

---------
call macro

<@m1/>


<#macro m2 a b c>
${a} - ${b} - ${c}
</#macro>

call m2

<@m2 a="张三" b="李四" c="王五"/>

<#macro border>
  <#nested>
</#macro>


nested--------
<@border>
  adfddddffffffffffffffffffffffffffff
  dddddddddddddddd
</@border>
<#import "b.ftl" as bb />
<@bb.copyright date="2018-2022" />
${bb.mail}
<#assign mail="my@124.com" />
<#assign mail="my@124.com" in bb/>
${mail}
${bb.mail}
<#assign num="12345" />
${num[1..4]}

