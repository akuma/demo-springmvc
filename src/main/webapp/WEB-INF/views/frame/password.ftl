<@fm.html>
<@fm.head title="password">
<script type="text/javascript" language="javascript">
$(document).ready(function() {
  $("#saveBtn").click(function() {
    ajaxPost("modifyPassword.htm", $("#dataForm").serialize(), function(result) {
       drawMessages(result);
    });
  });

  $("#backBtn").click(function() {
    history.back();
  });

  $("#password0").focus();
});
</script>
</@fm.head>
<@fm.body currentModule="修改密码">
<div id="titleMain">
  <div class="modules">
    <div id="current"><a href="" class="current">修改密码</a></div>
  </div>
</div>
<div id="tableMain">
  <@fm.message />
  <form id="dataForm">
    <table id="dataTable" border="0" align="center" cellpadding="5" cellspacing="1">
      <tr>
        <td width="15%" class="titleTd">新密码：</td>
        <td><input type="password" id="password0" name="password0" /></td>
      </tr>
      <tr>
        <td class="titleTd">确认密码：</td>
        <td><input type="password" name="password1" /></td>
      </tr>
    </table>
    <div id="buttonCenter">
      <input type="button" id="saveBtn" class="colorButton" value=" 保存 " />
      <input type="button" id="backBtn" class="colorButton" value=" 返回 " />
    </div>
  </form>
</div>
</@fm.body>
</@fm.html>
