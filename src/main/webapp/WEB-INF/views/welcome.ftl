<@fm.framePage title="Demo App">
  <h2>欢迎使用</h2>

  <div class="well">
    <p>欢迎使用本系统，当前时间为 ${timeNow?datetime}</p>
    <p>${(systemInfo.id)?default("$\{id}")}</p>
    <p>${(systemInfo.name)?default("$\{name}")} 应用的描述信息</p>
  </div>
</@fm.framePage>
