<@fm.html>
<@fm.simpleHead title="Menu">
<link type="text/css" href="${request.contextPath}/css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="${request.contextPath}/script/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/script/utils.js"></script>
<script type="text/javascript" language="javascript">
function doHeartbeat() {
  ajaxPost("heartbeat.htm", null, function(result) {
  });
}

$(document).ready(function() {
  setInterval("doHeartbeat()", 20 * 60 * 1000);
});
</script>
</@fm.simpleHead>
<body>
<div><img src="${request.contextPath}/images/logo.jpg" border="0" /></div>
<div id="menu">
  <ul>
    <li><a href="${request.contextPath}/user/listUser.htm" target="mainFrame" title="用户信息管理">用户信息管理</a></li>
    <li><a href="${request.contextPath}/logout.htm" target="_top">退出系统</a></li>
  </ul>
  <ul>
</div>
</body>
</@fm.html>
