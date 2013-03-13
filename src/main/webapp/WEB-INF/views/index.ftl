<#macro scriptMacro>
<script src="js/sha1.js"></script>
<script>
if (top.location != self.location) {
  top.location = self.location;
}

$(function() {
  $(".form-signin").submit(function() {
    if ($("input[name=password]").val()) {
      $("input[name=password]").val(hex_sha1($("input[name=password]").val()));
    }
  });
});
</script>
</#macro>

<@fm.simplePage title="${(systemInfo.id)!}" styles="login.css" scriptMacro=scriptMacro>
  <form class="form-signin" action="login" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <@fm.messages />
    <input type="text" class="input-block-level" name="username" value="${(username?html)!}" placeholder="用户名" autofocus>
    <input type="password" class="input-block-level" name="password" placeholder="密码">
    <button type="submit" class="btn btn-large btn-primary">登录</button>
  </form>
</@fm.simplePage>
