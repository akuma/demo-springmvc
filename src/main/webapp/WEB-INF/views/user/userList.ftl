<#macro scriptMacro>
<script>
var removeCallback = function(result) {
  if (!$.gmc.hasErrors(result)) {
    location.reload();
  }
}

$(document).ready(function() {
  $("#checkAll").click(function() {
    var status = this.checked;
    $("input[type=checkbox]").prop("checked", status);
  });
});
</script>
</#macro>

<@fm.framePage title="用户信息列表" scriptMacro=scriptMacro>
  <div class="tabbable">
    <ul class="nav nav-tabs">
      <li class="active"><a href="userList">用户信息列表</a></li>
      <li><a href="addUser">新增用户信息</a></li>
      <li class="disabled"><a>修改用户信息</a></li>
    </ul>
  </div>

  <div class="row-fluid">
    <@fm.messages />
  </div>

  <form class="well form-search" name="searchForm" action="userList" method="get">
    <label class="control-label" for="username">用户名</label>
    <input type="text" class="input-small" id="username" name="username" value="${(user.username?html)!}">
    <label class="control-label" for="realName">真实姓名</label>
    <input type="text" class="input-small" id="realName" name="realName" value="${(user.realName?html)!}">
    <button type="submit" class="btn">查询</button>
  </form>

  <@fm.pager "userList" />

  <table class="table table-striped table-hover">
    <thead>
      <tr>
        <!--<th align="center" width="5%">No.</td>-->
        <th><input type="checkbox" id="checkAll"></th>
        <th>用户ID</th>
        <th>用户名</th>
        <th>真实姓名</th>
        <th>修改时间</th>
        <th>创建时间</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
    <#list users as user>
      <tr id="record_${user.id}">
        <!--<td>${user_index + 1}</td>-->
        <td><input type="checkbox" id="check${user.id}" name="userIds" value="${user.id}"></td>
        <td>${user.id}</td>
        <td>${user.username?html}</td>
        <td>${user.realName?html}</td>
        <td>${user.modifyTime?datetime}</td>
        <td>${user.creationTime?datetime}</td>
        <td>
          <a class="btn btn-mini" href="modifyUser?userId=${user.id}">修改</a>
          <a class="btn btn-mini btn-danger" href="removeUser?userId=${user.id}"
             data-remote="true" data-method="post" data-type="json" data-success="removeCallback"
             data-confirm="您确定要删除吗">删除</a>
        </td>
      </tr>
    </#list>
    </tbody>
  </table>
</@fm.framePage>
