<#if (user.id)??>
  <#assign moduleName="修改用户信息" isModify=true postAction="modifyUser.htm">
<#else>
  <#assign moduleName="新增用户信息" isModify=false postAction="addUser.htm">
</#if>

<@fm.html>
<@fm.head title="${moduleName}">
<script type="text/javascript" language="javascript">
$(document).ready(function() {
  $("#saveBtn1").click(function() {
    ajaxPost("${postAction}", $("#dataForm").serialize(), function(result) {
       drawMessages(result);
       if (!hasActionErrors(result)) {
         <#if !isModify>
         $("#dataForm")[0].reset();
         prepare();
         </#if>
       }
    });
  });

  $("#saveBtn2").click(function() {
    ajaxPost("${postAction}", $("#dataForm").serialize(), function(result) {
       if (hasActionErrors(result)) {
         drawMessages(result);
       } else {
         location.href = "listUser.htm";
       }
    });
  });

  $("#birthday").datepicker({dateFormat: "yy-mm-dd", appendText: " (yyyy-mm-dd)"});

  $("#backBtn").click(function() {
    location.href = "listUser.htm";
  });

  $("#username").focus();
});

function prepare() {
  $("#username").focus();
}
</script>
</@fm.head>
<@fm.body currentModule="用户信息管理 &gt;&gt; ${moduleName}">
<#if isModify>
<div id="titleMain">
  <div class="modules">
    <div class="other"><a href="listUser.htm" class="other">用户信息列表</a></div>
    <div class="other"><a href="newUser.htm" class="other">新增用户信息</a></div>
    <div id="current"><a href="" class="current">修改用户信息</a></div>
  </div>
</div>
<#else>
<div id="titleMain">
  <div class="modules">
    <div class="other"><a href="listUser.htm" class="other">用户信息列表</a></div>
    <div id="current"><a href="" class="current">新增用户信息</a></div>
    <div class="unusable">修改用户信息</div>
  </div>
</div>
</#if>
<div id="tableMain">
  <@fm.message />
  <form id="dataForm" class="form-horizontal">
    <input type="hidden" id="userId" name="id" value="${(user.id)!}" />
    <table id="dataTable" class="table table-bordered">
      <tr>
        <td width="15%" class="titleTd">用户名</td>
        <td><input type="text" id="username" name="username" value="${(user.username)!}" /></td>
      </tr>
      <tr>
        <td class="titleTd">密码</td>
        <td><input type="password" name="password" value="${(user.password)!}" /></td>
      </tr>
      <tr>
        <td class="titleTd">真实姓名</td>
        <td><input type="text" name="realName" value="${(user.realName)!}" /></td>
      </tr>
      <tr>
        <td class="titleTd">出生日期</td>
        <td><input type="text" id="birthday" name="birthday" value="${(user.birthday?date)!}" /></td>
      </tr>
    </table>
    <div id="buttonLeft" class=".form-actions">
      <button id="saveBtn1" class="btn btn-primary" type="button">保存</button>
      <button id="saveBtn2" class="btn btn-primary" type="button">保存并返回</button>
      <button id="backBtn" class="btn btn-primary" type="button">返回列表</button>
    </div>
  </form>
</div>
</@fm.body>
</@fm.html>
