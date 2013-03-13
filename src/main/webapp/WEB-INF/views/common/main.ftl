<#include "common.ftl">
<#include "header.ftl">

<#-- 公共 css -->
<#assign applicationStyles=["main.css"]>

<#-- 包含公共 css、js 资源的页面宏 -->
<#macro simplePage title="未命名" styles="" scripts="" styleMacro="" scriptMacro="" noContainer=false>
<@gmPage title=title styles=mergeStyles(applicationStyles, styles) scripts=scripts styleMacro=styleMacro scriptMacro=scriptMacro>
<#if noContainer>
  <#nested>
<#else>
<div class="container">
  <#nested>
</div>
</#if>
</@gmPage>
</#macro>

<#macro framePage title="未命名" styles="" scripts="" styleMacro="" scriptMacro="">
<@simplePage title=title styles=styles scripts=scripts styleMacro=styleMacro scriptMacro=scriptMacro noContainer=true>
  <@header/>

  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2">
        <div class="well sidebar-nav">
          <ul class="nav nav-list">
            <li class="nav-header">用户信息管理</li>
            <li class="active"><a href="${request.contextPath}/user/userList">用户信息管理</a></li>
            <li class="nav-header">退出系统</li>
            <li><a href="${request.contextPath}/logout">退出</a></li>
          </ul>
        </div><!--/.well -->
      </div><!--/span-->

      <div class="span10">
        <#nested>
      </div><!--/span-->
    </div><!--/.row-fluid-->

    <hr>

    <footer>
      <p>&copy; 2013 GM</p>
    </footer>
  </div><!--/.container-fluid -->

</@simplePage>
</#macro>
