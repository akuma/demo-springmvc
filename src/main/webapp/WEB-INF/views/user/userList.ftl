<@fm.html>
<@fm.head title="用户信息列表">
<script>
function removeUser(userId) {
  if (!confirm("您确定要删除此用户吗？")) {
    return;
  }

  ajaxPost("removeUser.htm", "userId=" + userId, function(result) {
    drawMessages(result);
    if (!hasActionErrors(result)) {
      location.reload();
    }
  });
}

$(document).ready(function() {
  $("#checkAll").click(function() {
    var status = this.checked;
    $(":checkbox[@name=user.id]").each(function() {
      $(this).attr("checked", status);
    });
  });
});
</script>
</@fm.head>
<@fm.body currentModule="用户信息管理 &gt;&gt; 用户信息列表">
<div id="titleMain">
  <div class="modules">
    <div id="current"><a href="listUser.htm" class="current">用户信息列表</a></div>
    <div class="other"><a href="newUser.htm" class="other">新增用户信息</a></div>
    <div class="unusable">修改用户信息</div>
  </div>
</div>
<div id="tableMain">
  <@fm.message />
  <div class="description">
  <form name="searchForm" action="listUser.htm" method="get" class="form-search">
    <label for="username">用户名：</label><input type="input" class="span2" id="username" name="username" value="${user.username!?html}" />
    <label for="realName">真实姓名：</label><input type="input" class="span2" id="realName" name="realName" value="${user.realName!?html}" />
    <input id="searchBtn" type="submit" class="colorButton" value="  查 询  " />
  </form>
  </div>
  <@fm.pagination "listUser.htm" />
  <table id="dataTable" class="table table-striped table-bordered table-condensed">
    <thead>
      <tr class="titleTr">
        <!--<th align="center" width="5%">No.</td>-->
        <th class="span1"><input type="checkbox" id="checkAll" /></th>
        <th class="span2">用户ID</th>
        <th class="span3">用户名</th>
        <th class="span3">真实姓名</th>
        <th class="span2">修改时间</th>
        <th class="span2">创建时间</th>
        <th class="span2">操作</th>
      </tr>
    </thead>
    <tbody>
    <#list users as user>
      <tr id="record_${user.username}">
        <!--<td>${user_index + 1}</td>-->
        <td><input type="checkbox" id="check${user.id}" name="userIds" value="${user.id}" /></td>
        <td>${user.id}</td>
        <td>${user.username?html}</td>
        <td>${user.realName?html}</td>
        <td>${user.modifyTime?datetime}</td>
        <td>${user.creationTime?datetime}</td>
        <td>
          <a class="btn btn-mini btn-primary span1" href="loadUser.htm?userId=${user.id}">修改</a>
          <a class="btn btn-mini btn-danger span1" href="javascript:removeUser('${user.id}');">删除</a>
        </td>
      </tr>
    </#list>
    </tbody>
  </table>
</div>
</@fm.body>
</@fm.html>
