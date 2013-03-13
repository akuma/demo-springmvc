<#macro header>
  <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
          <span class="icon-bar"></span> <span class="icon-bar"></span>
        </a> <a class="brand" href="#">${systemInfo.name}</a>
        <div class="nav-collapse">
          <ul class="nav">
            <li class="active"><a href="#">管理平台</a></li>
            <li><a href="#settings">个人设置</a></li>
            <li><a href="#about">关于</a></li>
          </ul>
          <p class="navbar-text pull-right">
            欢迎您！ <a href="#">${memoryUser.username?html}</a>
          </p>
        </div>
        <!--/.nav-collapse -->
      </div>
    </div>
  </div>

  <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="brand" href="#">${systemInfo.name}</a>
        <div class="nav-collapse collapse">
          <p class="navbar-text pull-right">
            欢迎您！<a href="#" class="navbar-link">${memoryUser.username}</a>
          </p>
          <ul class="nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#settings">个人设置</a></li>
            <li><a href="#about">About</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>
</#macro>
