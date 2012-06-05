<@fm.html>
<@fm.head title="${(systemInfo.id)!} - Login" style="login.css">
<script type="text/javascript" src="script/sha1.js"></script>
<script type="text/javascript" language="javascript">
if (top.location != self.location) {
  top.location = self.location;
}

$(document).ready(function() {
  $("#username").focus();

  $("#loginForm").submit(function() {
    if ($("#password").val() != "") {
      $("#password").val(hex_sha1($("#password").val()));
    }
  });
});
</script>
</@fm.head>
<body>
  <table border="0" cellspacing="0" cellpadding="0" width="100%" class="top-background">
    <tr>
      <td valign="middle" align="left" nowrap="nowrap"><img src="images/logo.jpg" border="0" /></td>
    </tr>
    <tr class="top-info-background">
      <td align="right" height="18">${(systemInfo.serverMarker)!}</td>
    </tr>
  </table>
  <table border="0" cellspacing="0" cellpadding="5" align="left" width ="100%">
    <tr>
      <td width="150" nowrap="nowrap">&nbsp;</td>
      <td valign="top" align="left" nowrap="nowrap">
        <br />
        <span class="dialog-info">欢迎登录</span> <span class="dialog-label">${(systemInfo.name)!}</span><br /><br />
        <form id="loginForm" method="post" action="login.htm">
          <@fm.message />
          <table border="0" cellspacing="0" cellpadding="5" width="100%">
            <tr>
              <td align="right" valign="middle" nowrap="nowrap"><span class="dialog-label">用户名:</span></td>
              <td colspan="2" align="left" valign="middle"><input type="text" id="username" name="username" value="${(user.username)!}" style="width:160px" /></td>
            </tr>
            <tr>
              <td align="right" valign="middle" nowrap="nowrap"><span class="dialog-label">密码:</span></td>
              <td colspan="2" align="left" valign="middle"><input type="password" id="password" name="password" style="width:160px" /></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td colspan="2" align="right" nowrap="nowrap"><input type="submit" class="buttons" value=" 登 录 " /></td>
            </tr>
          </table>
        </form>
      </td>
      <td width="100%" nowrap="nowrap">&nbsp;</td>
    </tr>
  </table>
</body>
</@fm.html>
