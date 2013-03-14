<#macro styleMacro>
<link rel="stylesheet" href="http://twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css">
</#macro>

<#macro scriptMacro>
<script src="http://google-code-prettify.googlecode.com/svn/trunk/src/prettify.js"></script>
<script>
var sayHello = function(result) {
  $("#helloBox").html("Hello, " + result.data.name + "!");
}

var showUserCount = function(data) {
  alert("Got " + data.length + " users.");
}

$(function() {
  prettyPrint(); // 显示演示代码的行数

  $("#clearPhotoBtn").click(function() {
    $("#photoBox").html("");
  });
});
</script>
</#macro>

<@fm.simplePage title="ajax 操作演示" styleMacro=styleMacro scriptMacro=scriptMacro>

<div class="row">&nbsp;</div>
<div class="well">
  <h2>ajax 操作演示（基于 jquery + jquery-ujs）</h2>
</div>

<div id="messageBox"></div>

<h4>链接请求演示</h4>
<ul>
  <li>
    <a href="sayHello" data-remote="true" data-type="json" data-success="sayHello">返回 json 的 ajax 请求 A</a>
    <span id="helloBox">Hello, AJAX!</span>
    <span class="help-block">演示代码</span>
    <pre class="prettyprint linenums">
    &lt;a href="sayHello" data-remote="true" data-type="json" data-success="sayHello"&gt;演示返回 json 的 ajax 请求&lt;/a&gt;
    &lt;script&gt;
    var sayHello = function(result) {
      $("#helloBox").html("Hello, " + result.data.name + "!");
    }
    &lt;/script&gt;
    </pre>
  </li>
  <li>
    <a href="getUsers" data-remote="true" data-type="json" data-success="showUserCount">返回 json 的 ajax 请求 B</a>
  </li>
  <li>
    <a href="showPhoto" data-remote="true" data-fragment="photoBox">返回 html 的 ajax 请求</a>
    <button type="button" id="clearPhotoBtn" class="btn btn-mini">清除照片</button>
    <div id="photoBox"></div>
    <span class="help-block">演示代码</span>
    <pre class="prettyprint linenums">
    // 方式一：通过 data-fragment 参数指定需要被替换内容的节点，该节点内容会在请求成功后自动更新）
    &lt;a href="showPhoto" data-remote="true" data-fragment="photoBox"&gt;演示返回 html 的 ajax 请求&lt;/a&gt;

    // 方式二：通过 data-success 参数指定回调接口，请求成功会调用该接口
    &lt;a href="showPhoto" data-remote="true" data-success="showPhoto"&gt;演示返回 html 的 ajax 请求&lt;/a&gt;
    &lt;script&gt;
    var showPhoto = function(data) {
      $("#photoBox").html(data);
    }
    &lt;/script&gt;
    </pre>
  </li>
</ul>

<hr>

<h4>表单提交演示</h4>
<form action="testFormSubmit" method="post" data-remote="true" data-type="json">
  <div class="control-group">
    <label class="control-label" for="username">请输入登录名（第1种 field 出错提示方式）</label>
    <div class="controls">
      <input type="text" id="username" name="username" placeholder="你的登录名">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="password">请输入密码（第2种 field 出错提示方式）</label>
    <div class="controls">
      <input type="password" id="password" name="password" placeholder="你的密码">
      <span id="passwordError" class="help-inline"></span>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="realName">请选择真实姓名（第3种 field 出错提示方式）</label>
    <div class="controls">
      <select id="realName" name="realName" placeholder="你的真实姓名">
        <option value="">不告诉你</option>
        <option value="1">刘邦</option>
        <option value="2">项羽</option>
        <option value="3">萧何</option>
      </select>
      <span id="realNameError" class="help-block"></span>
    </div>
  </div>
  <button type="submit" class="btn">提交</button>
</form>
<span class="help-block">演示代码</span>
<pre class="prettyprint linenums">
&lt;form action="testFormSubmit" method="post" data-remote="true" data-type="json"&gt;
...
&lt;/form&gt;
&lt;script&gt;
// gm-common.js 默认的 ajax 回调方法定义
// 仅仅输出 controller 中返回的 json 格式的操作提示信息
// 如果默认的方法不能满足需求，则重新定义一个方法，并通过 form 中的 data-success 属性指定
var ajaxSuccess = function(data, status, xhr) {
  var ct = xhr.getResponseHeader("content-type") || "";
  if (ct.indexOf("text/html") == -1) {
    $.gmc.drawMessages(data); // 输出 json 对象中的系统提示信息
  }
}
&lt;/script&gt;
</pre>

<hr>
<button class="btn btn-large" onclick="history.back()">返回</button>

</@fm.simplePage>
