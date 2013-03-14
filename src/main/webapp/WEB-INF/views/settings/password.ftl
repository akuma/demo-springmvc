<@fm.framePage title="修改密码">
  <form class="form" action="modifyPassword" method="post" data-remote="true" data-type="json">
    <fieldset>
      <legend>修改密码</legend>
      <div id="messageBox"></div>
      <div class="control-group">
        <label class="control-label" for="password0">新密码</label>
        <div class="controls">
          <input type="password" id="password0" name="password0" autofocus>
          <span id="password0Error" class="help-inline">长度为 6-20 的字符串</span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="password1">确认密码</label>
        <div class="controls">
          <input type="password" id="password1" name="password1">
          <span id="password1Error" class="help-inline">再输入一次</span>
        </div>
      </div>
      <div class="control-group">
        <div class="controls">
          <button type="submit" id="saveBtn" class="btn btn-primary">保存</button>
          <a class="btn" href="javascript:history.back();">返回</a>
        </div>
      </div>
    </fieldset>
  </form>
</@fm.framePage>
