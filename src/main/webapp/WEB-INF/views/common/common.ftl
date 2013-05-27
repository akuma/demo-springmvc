<#-- GM Freemarker 通用模版 -->

<#-- html5 框架宏定义 -->
<#macro html>
<!DOCTYPE html>
<html lang="zh-CN">
<#nested>
</html>
</#macro>

<#-- 定义 assets 路径前缀 -->
<#assign assetsPath=(appSettings.assetsPath)!>
<#if assetsPath = "">
  <#assign assetsPath=request.contextPath>
</#if>

<#-- html head 宏定义，默认设置了通用的 css、js -->
<#macro head title="" styles="" scripts="" styleMacro="" scriptMacro="">
<head>
<meta charset="utf-8">
<#-- 设置防止 CSRF 攻击的 token -->
<#if csrfToken! != "">
<meta name="csrf-param" content="authenticity_token">
<meta name="csrf-token" content="${csrfToken}">
</#if>
<title>${title!}</title>
<#-- 显示通用样式 -->
<@commonStyles />
<#-- 处理 styles 参数是单个值的情况 -->
<#if styles?is_string && styles != "">
<link rel="stylesheet" href="${assetsPath}/css/${styles}">
</#if>
<#-- 处理 styles 参数是数组的情况 -->
<#if styles?is_sequence>
  <#list styles as sty>
<link rel="stylesheet" href="${assetsPath}/css/${sty}">
  </#list>
</#if>
<#-- 处理 style 宏 -->
<#if styleMacro?is_macro>
  <@styleMacro />
</#if>
<#-- 显示通用脚本 -->
<@commonScripts />
<#-- 处理 scripts 参数是的单个值的情况 -->
<#if scripts?is_string && scripts != "">
<script src="${assetsPath}/js/${scripts}"></script>
</#if>
<#-- 处理 scripts 参数是的数组的情况 -->
<#if scripts?is_sequence>
  <#list scripts as script>
<script src="${assetsPath}/js/${script}"></script>
  </#list>
</#if>
<#-- 处理 script 宏 -->
<#if scriptMacro?is_macro>
  <@scriptMacro />
</#if>
<#nested>
<#-- 添加网页访问分析 js -->
<@pageTracker />
</head>
</#macro>

<#-- 简单的 html head，默认不设置 css、js -->
<#macro plainHead title="">
<head>
<meta charset="utf-8">
<title>${title!}</title>
<#nested>
</head>
</#macro>

<#-- 通用 css 宏定义 -->
<#macro commonStyles>
<#if (appSettings.assetsGlobalCss)! == "" || (appSettings.dev)!false>
<link rel="stylesheet" href="${assetsPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${assetsPath}/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="${assetsPath}/css/jquery-ui.min.css">
<#else>
<link rel="stylesheet" href="${assetsPath}/css/${(appSettings.assetsGlobalCss)!}">
</#if>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</#macro>

<#-- 通用 script 宏定义 -->
<#macro commonScripts>
<#if (appSettings.assetsGlobalJs)! == "" || (appSettings.dev)!false>
<script src="${assetsPath}/js/jquery.min.js"></script>
<script src="${assetsPath}/js/jquery-ui.min.js"></script>
<script src="${assetsPath}/js/jquery-ujs.min.js"></script>
<script src="${assetsPath}/js/bootstrap.min.js"></script>
<#else>
<script src="${assetsPath}/js/${(appSettings.assetsGlobalJs)!}"></script>
</#if>
<script src="${assetsPath}/js/gm-common.js"></script>
</#macro>

<#-- 网页访问分析 js 宏定义 -->
<#macro pageTracker>
<#if (appSettings.pageTrackEnable)!false>
<script>
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

ga('create', '${appSettings.pageTrackerId}', '${appSettings.pageTrackerDomain}');
ga('send', 'pageview');
</script>
</#if>
</#macro>

<#-- 包含通用 css、js 的 html 宏定义 -->
<#macro gmPage title="未命名" styles="" scripts="" styleMacro="" scriptMacro="">
<@html>
<@head title=title styles=styles scripts=scripts styleMacro=styleMacro scriptMacro=scriptMacro />
<body>
  <!--[if lt IE 9]>
  <div class="notice-bar">您的浏览器颇为古老，网站无法很好的支持！其实，您可以有更好的选择：
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <a href="http://se.360.cn/" target="_blank">360 安全浏览器</a>、
    <a href="http://www.google.com/intl/zh-CN/chrome/browser/" target="_blank">谷歌浏览器</a>、
    <a href="http://firefox.com.cn/" target="_blank">火狐浏览器</a>
  </div>
  <![endif]-->
  <#nested>
</body>
</@html>
</#macro>

<#-- 显示 Action 提示信息（actionError、actionMessage）的宏定义 -->
<#macro messages>
  <div id="messageBox">
  <@showMessage messages=actionWarnings />
  <@showMessage messages=actionErrors cssClass="alert-error" />
  <@showMessage messages=actionInfos cssClass="alert-success" />
  <@showMessage messages=actionMessages cssClass="alert-info" />
  </div>
</#macro>

<#macro showMessage messages=[] cssClass="">
  <#if messages?size != 0>
    <div class="alert alert-block ${cssClass}">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    <#list messages! as message>
      <#if message_index == 0>
      <ul>
      </#if>
        <li>${message}</li>
      <#if !message_has_next>
      </ul>
      </#if>
    </#list>
    </div>
  </#if>
</#macro>

<#-- 将两个 css（单个或者数组） 合并为一个数组的函数 -->
<#function mergeStyles a b>
  <#local retVal=[]>
  <#if a?is_string && a != "">
    <#local retVal=retVal + [a]>
  </#if>
  <#if a?is_sequence>
    <#local retVal=retVal + a>
  </#if>
  <#if b?is_string && b != "">
    <#local retVal=retVal + [b]>
  </#if>
  <#if b?is_sequence>
    <#local retVal=retVal + b>
  </#if>
  <#return retVal>
</#function>
