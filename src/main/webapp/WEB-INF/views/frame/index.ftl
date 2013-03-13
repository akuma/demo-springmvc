<@fm.framePage title="Demo App">
  <h2>欢迎使用</h2>

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
</@fm.framePage>
