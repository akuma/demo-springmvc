<#macro html>
<!DOCTYPE html>
<html>
<#nested>
</html>
</#macro>

<#macro head title="${(systemInfo.name)!}" style="main.css">
<head>
<meta charset="utf-8" />
<title>${title!}</title>
<link type="text/css" href="${request.contextPath}/css/${style}" rel="stylesheet" />
<link type="text/css" href="${request.contextPath}/css/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link type="text/css" href="${request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="${request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet" />
<@jqueryScript />
<script src="${request.contextPath}/script/bootstrap.min.js"></script>
<#nested>
</head>
</#macro>

<#macro jqueryScript>
<script src="${request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script src="${request.contextPath}/script/jquery-ui-1.8.18.custom.min.js"></script>
<script src="${request.contextPath}/script/utils.js"></script>
</#macro>

<#macro loginInfo>
<div id="logininfo">
  您的 IP 是 &nbsp;:&nbsp; ${realRemoteAddr!} &nbsp; &nbsp; | &nbsp; &nbsp;
  您登录的用户 &nbsp;:&nbsp; ${(memoryUser.username)!} &nbsp; &nbsp; | &nbsp; &nbsp;
  <a href="${request.contextPath}/frame/welcome.htm">首页</a> &nbsp; &nbsp; | &nbsp; &nbsp;
  <a href="${request.contextPath}/frame/modifyPassword.htm">修改密码</a> &nbsp; &nbsp; | &nbsp; &nbsp;
  <a href="${request.contextPath}/logout.htm" target="_top">退出</a>
</div>
</#macro>

<#macro message>
  <div id="message">
  <#list actionErrors! as error>
    <#if error_index == 0>
    <ul>
    </#if>
      <li>${error}</li>
    <#if !error_has_next>
    </ul>
    </#if>
  </#list>
  <#list actionMessages! as message>
    <span class="msg">${message}</span>
  </#list>
  </div>
</#macro>

<#macro body currentModule="" ignoreLoginInfo=false>
<body>
<div id="navigator">${currentModule!}</div>
<#if ignoreLoginInfo>
<br />
<#else>
<@loginInfo />
</#if>
<#nested>
<div id="serverMark">${(systemInfo.serverMarker)!}</div>
</body>
</#macro>

<#macro simpleHead title="${(systemInfo.name)!}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!}</title>
<#nested>
</head>
</#macro>

<#macro pagination url=request.requestURL needJump=true>
<#if request.queryString??>
  <#local pageUrl = url + "?" + request.queryString?replace("&?pageNum(=[^&]*&?$)?", "", "rs")>
<#else>
  <#local pageUrl = url + "?">
</#if>
<#if !(pageUrl?ends_with("?"))>
  <#local pageUrl = pageUrl + "&">
</#if>

<div class="pagination_sw">
共 ${page.rowCount} 条记录&nbsp;&nbsp;这是${page.pageNum}/${page.pageCount} 页&nbsp;&nbsp;
<#if (page.pageNum > 1)>
<a href="${pageUrl}pageNum=1">首页</a>&nbsp;&nbsp;<a href="${pageUrl}pageNum=${page.pageNum - 1}">上一页</a>&nbsp;&nbsp;
<#else>
首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
</#if>
<#if (page.pageNum < page.pageCount)>
<a href="${pageUrl}pageNum=${page.pageNum + 1}">下一页</a>&nbsp;&nbsp;<a href="${pageUrl}pageNum=${page.pageCount}">末页</a>&nbsp;&nbsp;
<#else>
下一页&nbsp;&nbsp;末页&nbsp;&nbsp;
</#if>
<#if needJump == true>
第 <input id="_targetPageNum_" type="text" class="span1" size="3" maxlength="6" value="${page.pageNum}" onfocus="this.value=''" onblur="if(this.value=='')this.value='${page.pageNum}'" onkeyup="if(event.keyCode==13){jumpPage()}"> 页 <a href="javascript:jumpPage()">跳转</a>
<script type="text/javascript" language="javascript">
function jumpPage() {
  var jumpPageNum = document.getElementById("_targetPageNum_").value;
  if (isNaN(parseInt(jumpPageNum))) {
    alert("请输入要跳转的页码");
    return;
  }
  location.href = "${pageUrl}pageNum=" + jumpPageNum;
}
</script>
</#if>
</div>
</#macro>

<#macro swPage url=request.requestURL needJump=true>
<#if request.queryString??>
  <#local pageUrl = url + "?" + request.queryString?replace("&?page(=[^&]*&?$)?", "", "rs")>
<#else>
  <#local pageUrl = url + "?">
</#if>
<#if !(pageUrl?ends_with("?"))>
  <#local pageUrl = pageUrl + "&">
</#if>

<div class="pagination">
共 ${pager.total} 条记录&nbsp;&nbsp;这是${pager.pageth}/${pager.pageCnt} 页&nbsp;&nbsp;
<#if (pager.pageth > 1)>
<a href="${pageUrl}page=1">首页</a>&nbsp;&nbsp;<a href="${pageUrl}page=${pager.pageth - 1}">上一页</a>&nbsp;&nbsp;
<#else>
首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
</#if>
<#if (pager.pageth < pager.pageCnt)>
<a href="${pageUrl}page=${pager.pageth + 1}">下一页</a>&nbsp;&nbsp;<a href="${pageUrl}page=${pager.pageCnt}">末页</a>&nbsp;&nbsp;
<#else>
下一页&nbsp;&nbsp;末页&nbsp;&nbsp;
</#if>
<#if needJump == true>
第 <input id="_targetPageNum_" type="text" size="3" maxlength="6" value="${pager.pageth}" onfocus="this.value=''" onblur="if(this.value=='')this.value='${pager.pageth}'" onkeyup="if(event.keyCode==13){jumpPage()}"> 页 <a href="javascript:jumpPage()">跳转</a>
<script type="text/javascript" language="javascript">
function jumpPage() {
  var jumpPageNum = document.getElementById("_targetPageNum_").value;
  if (isNaN(parseInt(jumpPageNum))) {
    alert("请输入要跳转的页码");
    return;
  }
  location.href = "${pageUrl}page=" + jumpPageNum;
}
</script>
</#if>
</div>
</#macro>
