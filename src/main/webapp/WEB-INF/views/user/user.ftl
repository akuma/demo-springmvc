<#if (user.id)??>
  <#assign moduleName="修改用户信息" isModify=true postAction="modifyUser">
<#else>
  <#assign moduleName="新增用户信息" isModify=false postAction="addUser">
</#if>

<#macro scriptMacro>
<script>
var source;
$(function() {
  $(document).on("click", "button", function() {
    source = this.id;
  });
});

var saveCallback = function(result, event) {
  if ($.gmc.hasErrors(result)) {
    $.gmc.drawMessages(result);
  } else {
    if (source === "saveBtn1") {
      $.gmc.drawMessages(result);
<#if !isModify>
      $("form input").val("");
      $("#username").focus();
</#if>
    } else {
      location.href = "userList";
    }
  }
}
</script> 
</#macro>

<@fm.framePage title="${moduleName}" scriptMacro=scriptMacro activeModule="user">
  <div class="tabbable">
    <ul class="nav nav-tabs">
      <li><a href="userList">用户信息列表</a></li>
    <#if isModify>
      <li><a href="addUser">新增用户信息</a></li>
      <li class="active"><a href="modifyUser?userId=${user.id}">修改用户信息</a></li>
    <#else>
      <li class="active"><a href="addUser">新增用户信息</a></li>
      <li class="disabled"><a>修改用户信息</a></li>
    </#if>
    </ul>
  </div>

  <div class="row-fluid">
    <@fm.messages />
  </div>

  <form class="form-horizontal" action="${postAction}" method="post"
        data-remote="true" data-type="json" data-success="saveCallback">
    <input type="hidden" id="userId" name="id" value="${(user.id)!}">
    <fieldset>
      <!--
      <legend>Form control states</legend>
      -->
      <div class="control-group">
        <label class="control-label" for="username">用户名</label>
        <div class="controls">
          <input type="text" id="username" name="username" value="${(user.username?html)!}" autofocus>
          <span id="usernameError" class="help-inline">长度小于16个字符</span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="password">密码</label>
        <div class="controls">
          <input type="password" id="password" name="password" value="${(user.password?html)!}">
          <span id="passwordError" class="help-inline">长度应大于6个字符，小于20个字符</span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="realName">真实姓名</label>
        <div class="controls">
          <input type="text" id="realName" name="realName" value="${(user.realName?html)!}">
          <span id="realNameError" class="help-inline">长度应小于64个字符</span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="birthday">生日</label>
        <div class="controls">
          <input type="text" id="birthday" name="birthday" value="${(user.birthday?date)!}" data-date data-yearRange="1970:">
          <span id="birthdayError" class="help-inline">出生日期，例如：2000-01-01</span>
        </div>
      </div>

      <div class="form-actions">
        <button id="saveBtn1" type="submit" class="btn btn-primary">保存</button>
        <button id="saveBtn2" type="submit" class="btn btn-primary">保存并返回</button>
        <a class="btn" href="userList">返回列表</a>
      </div>
    </fieldset>
  </form>
</@fm.framePage>
