<#macro simpleHeader>
  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"></a>
        <a class="brand" href="${request.contextPath}">${appInfo.name}</a>
      </div>
    </div>
  </div>
</#macro>

<#macro simpleFooter>
  <hr>
  <footer>
    <p>&copy; 2013</p>
  </footer>
</#macro>

<#macro frameHeader>
  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="brand" href="${request.contextPath}/welcome">${appInfo.name}</a>
        <div class="nav-collapse collapse">
          <ul class="nav">
            <li class="active"><a href="${request.contextPath}/welcome">首页</a></li>
            <li><a href="${request.contextPath}/settings/modifyPassword">修改密码</a></li>
            <li><a href="${request.contextPath}/demo/">开发框架使用演示</a></li>
          </ul>
          <ul class="nav pull-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span>${(currentUser.username)!}</span> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="${request.contextPath}/logout">退出</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>
</#macro>
