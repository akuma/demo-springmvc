<@fm.html>
<@fm.head title="ERROR" />
<@fm.body currentModule="ERROR">
<div id="titleMain"></div>
<div id="tableMain">
  <div id="message" style="color:maroon">
    <h2>发生了一个错误</h2>
    <p>您可以将此错误报告给我们的系统管理员，也可以返回 <a href="javascript:history.back()">刚才操作的页面重试</a>。谢谢您的合作！</p>
    <h3>错误信息</h3>
    <p><@s.property value="%{exception.message}" /></p>
    <h3>错误原因</h3>
    <p><@s.property value="%{exceptionStack}" /></p>
  </div>
</div>
</@fm.body>
</@fm.html>
