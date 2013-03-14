The Spring MVC Demo
================================

This is a webapp for demonstrating Spring MVC Framework.

如何在本地运行系统
----------------
执行 maven 任务：clean tomcat7:run -Dwebapp.port=8080（这里指定了 8080 端口）

demo 功能查看
------------
* 演示 ajax 调用：/demo/ajax/
* 演示 freemarker 模版使用：/demo/freemarker/
* 演示系统信息提示：/demo/message/

命名规则
------------
1. CSS: 小写字母，多个单词采用 “-” 分割，例如：icon-trash
2. JavaScript: 采用驼峰命名
3. Controller: XxxController (QuestionController, QuestionInputControll)
4. Controller method: viewXxx, addXxxx, modifyXxx, removeXxx, etc
5. Service: XxxService, XxxServiceImpl (StudentService, StudentServiceImpl)
6. Service method: getXxx, countXxx, addXxx, modifyXxx, removeXxx, etc
7. Dao: XxxDao (StudentDao)
8. Dao method: find, findByXxx, countByXxx, insert, update, updateXxx, delete, deleteByXxx

前端 & 视图层
------------

* html 页面采用 html5 的写法，比如：
  * link、input 等元素不需要闭合：
`<input name="test" type="text">`
  * 按钮使用 button 元素定义：`<button>我是一个按钮</button>`
  * script 元素可以简写为：`<script src="...引用文件..."></script>、<script>...代码...</script>`
  * 避免在 html 元素中直接写事件代码的方式，采用 jQuery 的事件绑定，降低 js 和 html 之间的代码耦合。
* 充分使用 jQuery 来减少 js 中的重复代码。
* 避免在页面上直接编写大量 js 代码，如果代码超过 20 行，就应该将代码抽取到独立的 js 文件中。
* 反复用到的相似的 html 代码，应该用 freemarker 宏来实现，这样可以减少前端的重复代码。
* 实现某个 Controller 方法的时候，注意控制台输出的 SQL 情况，尽量减少 SQL 查询和避免重复查询。
* 一般添加、修改、更新页面局部数据内容等功能都使用 ajax 方式实现，同时根据不同的场景选择最佳的数据返回格式（json、html）。

业务层
------------

* 注意 Service 方法的可重用性，在写新的方法之前先看看有没有已经存在的方法。
* Service 中引用其他 Service 或 DAO 时，优先使用 Service。
* 一般情况下 Service 数量小于 DAO 数量，因为一个 Service 包含了某种业务的操作，可能会用到多个 DAO/Service。
* 充分利用 commons 中的工具类来解决问题。
  * 字符串操作用 StringUtils
  * 日期操作用 DateUtils、DateFormatUtils
  * JavaBean 中属性方面的反射操作用 BeanUtils

数据库层
------------

* 原则上每个表一个 DAO 类，一般情况下一个 DAO 类不能操作多个表。
* 除非 MyBatisBasicDao 中缺少可以使用的方法，否则不要新增方法。
* 尽量使用简洁的 SQL 查询，使 SQL 覆盖数据库索引。
* 避免复杂的多表连接查询，改由程序实现连接。
