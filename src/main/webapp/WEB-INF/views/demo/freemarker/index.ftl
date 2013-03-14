<@fm.simplePage title="freemarker 演示页">

<div class="well">
  <h2>Freemarker 演示页面</h2>
</div>

本页面使用了 &lt;@fm.simplePage /&gt; 宏，页头页尾会由宏添加<br>

<p>
  Controller 中添加到 model 中的提示信息：${message}
</p>

<br>
<a href="simple">查看另外一个不带页头页尾的页面</a>

<hr>
<button class="btn btn-large" onclick="history.back()">返回</button>

</@fm.simplePage>
