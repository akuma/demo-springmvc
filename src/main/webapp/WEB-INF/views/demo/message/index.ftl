<@fm.simplePage title="message 显示演示">

<div class="row">&nbsp;</div>
<div class="well">
  <h2>系统提示信息演示</h2>
</div>

<!-- 用来放置提示信息的 div -->
<div id="messageBox"></div>

<h4>ajax 请求时的 message 显示演示</h4>
<ol>
  <li>如果是 ajax + json 请求，则提示信息会由 gm-common.js 处理后显示。</li>
  <li>如果是 ajax + html 或者普通请求，则提示信息要在页面上使用 &lt;@fm.messages /&gt; 这个自定义的 freemarker 宏显示。</li>
</ol>

<ul>
  <li><a href="showMessages">演示 普通请求 actionError、actionMessage</a></li>
  <li><a href="showActionError" data-remote="true" data-type="json">演示 ajax actionError</a></li>
  <li><a href="showActionMessage" data-remote="true" data-type="json">演示 ajax actionMessage</a></li>
  <li><a href="showServerException" data-remote="true" data-type="json">演示 ajax serverException</a></li>
  <li><a href="showServerError" data-remote="true" data-type="json">演示 ajax serverError</a></li>
</ul>

<hr>
<button class="btn btn-large" onclick="history.back()">返回</button>

</@fm.simplePage>
