<@fm.html>
<@fm.head title="Welcome"></@fm.head>
<@fm.body currentModule="欢迎使用">
<div id="titleMain"></div>
<div id="tableMain">
  <div id="description">欢迎使用本系统，当前时间为 ${timeNow?datetime}</div>
  <div id="message"></div>
  <table id="dataTable" border="0" align="center" cellpadding="5" cellspacing="1">
    <tr class="titleTr">
      <td>${(systemInfo.id)?default("$\{id}")}</td>
    </tr>
    <tr>
      <td>${(systemInfo.name)?default("$\{name}")} 应用的描述信息</td>
    </tr>
  </table>
  <div align="center">Copyright &copy; ${timeNow?string("yyyy")} [${(systemInfo.copyright)?default("$\{copyright}")}] All Rights Reserved.</div>
</div>
</@fm.body>
</@fm.html>
