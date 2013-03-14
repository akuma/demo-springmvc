<@fm.simplePage title="freemarker 演示页" useHeader=false>

<div class="row">&nbsp;</div>

<div class="well">
  <h2>Freemarker 演示页面</h2>
</div>

本页面使用了宏 &lt;fm.simplePage useHeader=false /&gt;

<p>
  Controller 中添加到 model 中的提示信息：${message}
</p>

<h4>静态方法调用测试</h4>

<ul>
  <li>
    短类名用法：${r'${StringUtils.getRealLength("测试")}'} ->
    ${StringUtils.getRealLength("测试")}
  </li>
  <li>
    全类名用法：${r'${statics["com.guomi.meazza.util.StringUtils"].getRealLength("测试")}'} ->
    ${statics["com.guomi.meazza.util.StringUtils"].getRealLength("测试")}
  </li>
</ul>

<h4>枚举类调用测试</h4>

<ul>
  <li>
    短类名用法：${r"${UserType.TEACHER.getText()}"} ->
    ${UserType.TEACHER.getText()}
  </li>
  <li>
    全类名用法：${r'${enums["demo.spring.mvc.enums.UserType"].TEACHER.getText()}'} ->
    ${enums["demo.spring.mvc.enums.UserType"].TEACHER.getText()}
  </li>
</ul>

<hr>
<button class="btn btn-large" onclick="history.back()">返回</button>

</@fm.simplePage>
