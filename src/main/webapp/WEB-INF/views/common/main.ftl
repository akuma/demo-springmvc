<#include "common.ftl">
<#include "header.ftl">

<#-- 公共 css -->
<#assign applicationStyles=["main.css"]>

<#-- 包含公共 css、js 资源的页面宏 -->
<#macro simplePage title="未命名" styles="" scripts="" styleMacro="" scriptMacro="" noContainer=false useHeader=true>
<@gmPage title=title styles=mergeStyles(applicationStyles, styles) scripts=scripts styleMacro=styleMacro scriptMacro=scriptMacro>
<#if !noContainer>
<div class="container">
</#if>

<#if useHeader>
  <@simpleHeader />
</#if>

  <#nested>

<#if useHeader>
  <@simpleFooter />
</#if>

<#if !noContainer>
</div>
</#if>
</@gmPage>
</#macro>

<#assign allModules = [
    {"id" : "user", "name" : "用户信息管理", "path" : "/user/userList"},
    {"id" : "group", "name" : "用户组管理", "path" : ""}
]>

<#macro framePage title="未命名" styles="" scripts="" styleMacro="" scriptMacro="" activeModule="">
<@simplePage title=title styles=styles scripts=scripts styleMacro=styleMacro
             scriptMacro=scriptMacro noContainer=true useHeader=false>
  <@frameHeader />

  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span2">
        <div class="well sidebar-nav">
          <ul class="nav nav-list">
            <li class="nav-header"></li>
          <#list allModules as module>

            <#local modulePath = module["path"]>
            <#if modulePath != "">
              <#local modulePath = request.contextPath + modulePath>
            <#else>
              <#local modulePath = "javascript:;">
            </#if>
            <li<#if module["id"] == activeModule> class="active"</#if>>
              <a href="${modulePath}">${module["name"]}</a>
            </li>
          </#list>
          </ul>
        </div><!--/.well -->
      </div><!--/span-->

      <div class="span10">
        <#nested>
      </div><!--/span-->
    </div><!--/.row-fluid-->

    <@simpleFooter />
  </div><!--/.container-fluid -->
</@simplePage>
</#macro>
