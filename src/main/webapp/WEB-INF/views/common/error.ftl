<@fm.simplePage title="ERROR">
  <div class="well">
    <h2>服务器开小差啦~</h2>
    <p>感谢您的注意——我们将尽快修复并恢复正常</p>
<#--
<#if debug?? && debug>
-->
    <h4><a id="stackTraceText" href="javascript:$.gmc.stackTraceSwitch()">显示错误详情</a></h4>
<#--
</#if>
-->
    <pre id="stackTrace" style="display:none;background-color:#fff;">${(exception.stackTrace?html)!}</pre>
  </div>
</@fm.simplePage>
