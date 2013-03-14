<#-- GM Freemarker 公共模版：页面分页显示 -->

<#macro pager url=request.requestUri needJump=true>
<#if request.queryString??>
  <#local pageUrl = url + "?" + request.queryString?replace("&?pageNum(=[^&]*&?$)?", "", "rs")>
<#else>
  <#local pageUrl = url + "?">
</#if>
<#if !(pageUrl?ends_with("?"))>
  <#local pageUrl = pageUrl + "&">
</#if>

<div class="form-search">
共 ${page.rowCount} 条记录&nbsp;&nbsp;这是${page.pageNum}/${page.pageCount} 页&nbsp;&nbsp;
<#if (page.pageNum > 1)>
<a href="${pageUrl}pageNum=1">首页</a>&nbsp;&nbsp;<a href="${pageUrl}pageNum=${page.pageNum - 1}">上一页</a>&nbsp;&nbsp;
<#else>
首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
</#if>
<#if (page.pageNum < page.pageCount)>
<a href="${pageUrl}pageNum=${page.pageNum + 1}">下一页</a>&nbsp;&nbsp;<a href="${pageUrl}pageNum=${page.pageCount}">末页</a>&nbsp;&nbsp;
<#else>
下一页&nbsp;&nbsp;末页&nbsp;&nbsp;
</#if>
<#if needJump == true>
第 <input id="_targetPageNum_" type="text" class="span1" style="min-height:25px;" size="3" maxlength="6" value="${page.pageNum}" onfocus="this.value=''" onblur="if(this.value=='')this.value='${page.pageNum}'" onkeyup="if(event.keyCode==13){jumpPage()}"> 页
<a class="btn btn-small" href="javascript:jumpPage()">跳转</a>
<script>
function jumpPage() {
  var jumpPageNum = document.getElementById("_targetPageNum_").value;
  if (isNaN(parseInt(jumpPageNum))) {
    alert("请输入要跳转的页码");
    return;
  }
  location.href = "${pageUrl}pageNum=" + jumpPageNum;
}
</script>
</#if>
</div>
</#macro>

<#macro pagination url=request.requestUri width="939" jumpEnable=true ajaxMethod="">
<#if request.queryString??>
  <#local pageUrl = url + "?" + request.queryString?replace("&?pageNum(=[^&]*&?$)?", "", "rs")>
<#else>
  <#local pageUrl = url + "?">
</#if>
<#if !(pageUrl?ends_with("?"))>
  <#local pageUrl = pageUrl + "&">
</#if>

<#local commonShowCount = 6>
<#local commonSelectCount = 1>
<#local commonAfterCount = 2>
<#local commonPrevCount = commonShowCount - commonSelectCount - commonAfterCount>
<#local maxPage = (page.pageCount)!(1)>
<#local pageIndex = (page.pageNum)!(1)>
<#if ajaxMethod == "">
<div class="hx-pagination" style="width:${width}px;">
  <div class="hx-pagination-top"></div>
  <ul class="hx-pagination-ul">
<#if pageIndex gt 1>
    <li class="prev"><a href="${pageUrl}pageNum=${pageIndex - 1}">上一页</a></li>
</#if>
<#if maxPage lte commonShowCount>
  <#if pageIndex gt 1>
    <#list 1..(pageIndex - 1) as n>
    <li><a href="${pageUrl}pageNum=${n}">${n}</a></li>
    </#list>
  </#if>
    <li><span class="hx-pagination-selected">${pageIndex}</span></li>
  <#if pageIndex + 1 lte maxPage>
    <#list (pageIndex + 1)..maxPage as n>
    <li><a href="${pageUrl}pageNum=${n}">${n}</a></li>
    </#list>
  </#if>
<#else>
  <#if pageIndex - 1 lt commonPrevCount>
    <#local prevPageCount = pageIndex - 1>
  <#else>
    <#local prevPageCount = commonPrevCount>
  </#if>

  <#if maxPage - pageIndex lt commonAfterCount>
    <#local afterCount = maxPage - pageIndex>
  <#else>
    <#local afterCount = commonAfterCount>
  </#if>

  <#if pageIndex - prevPageCount lte pageIndex - 1>
    <#list (pageIndex - prevPageCount)..(pageIndex - 1) as n>
    <li><a href="${pageUrl}pageNum=${n}">${n}</a></li>
    </#list>
  </#if>

    <li><span class="hx-pagination-selected">${pageIndex}</span></li>

  <#if pageIndex + 1 lte pageIndex + afterCount>
    <#list (pageIndex + 1)..(pageIndex + afterCount) as n>
    <li><a href="${pageUrl}pageNum=${n}">${n}</a></li>
    </#list>
  </#if>

  <#if pageIndex + afterCount lt maxPage>
    <li>...</li>
  </#if>
</#if>

<#if pageIndex lt maxPage>
    <li class="next"><a href="${pageUrl}pageNum=${pageIndex + 1}">下一页</a></li>
</#if>

    <li><p>共 <span>${maxPage}</span> 页</p></li>
    
<#else>
<div class="hx-pagination" style="width:${width}px;">
  <div class="hx-pagination-top"></div>
  <ul class="hx-pagination-ul">
<#if pageIndex gt 1>
    <li class="prev"><a href="javascript: ${ajaxMethod}('${pageIndex - 1}')">上一页</a></li>
</#if>
<#if maxPage lte commonShowCount>
  <#if pageIndex gt 1>
    <#list 1..(pageIndex - 1) as n>
    <li><a href="javascript: ${ajaxMethod}('${n}')">${n}</a></li>
    </#list>
  </#if>
    <li><span class="hx-pagination-selected">${pageIndex}</span></li>
  <#if pageIndex + 1 lte maxPage>
    <#list (pageIndex + 1)..maxPage as n>
    <li><a href="javascript: ${ajaxMethod}('${n}')">${n}</a></li>
    </#list>
  </#if>
<#else>
  <#if pageIndex - 1 lt commonPrevCount>
    <#local prevPageCount = pageIndex - 1>
  <#else>
    <#local prevPageCount = commonPrevCount>
  </#if>

  <#if maxPage - pageIndex lt commonAfterCount>
    <#local afterCount = maxPage - pageIndex>
  <#else>
    <#local afterCount = commonAfterCount>
  </#if>

  <#if pageIndex - prevPageCount lte pageIndex - 1>
    <#list (pageIndex - prevPageCount)..(pageIndex - 1) as n>
    <li><a href="javascript: ${ajaxMethod}('${n}')">${n}</a></li>
    </#list>
  </#if>

    <li><span class="hx-pagination-selected">${pageIndex}</span></li>

  <#if pageIndex + 1 lte pageIndex + afterCount>
    <#list (pageIndex + 1)..(pageIndex + afterCount) as n>
    <li><a href="javascript: ${ajaxMethod}('${n}')">${n}</a></li>
    </#list>
  </#if>

  <#if pageIndex + afterCount lt maxPage>
    <li>...</li>
  </#if>
</#if>

<#if pageIndex lt maxPage>
    <li class="next"><a href="javascript: ${ajaxMethod}('${pageIndex + 1}')">下一页</a></li>
</#if>

    <li><p>共 <span>${maxPage}</span> 页</p></li>
</#if>

<#if jumpEnable == true>
    <li><p>转到第</p>
      <div class="hx-pagination-jump">
        <input id="__pageNum__" class="hx-pagination-jump-text" type="text" value="${pageIndex}" onfocus="this.value=''" onblur="if(this.value=='')this.value='${pageIndex}'" onkeyup="if(event.keyCode==13){jumpPage()}">
        <span>页</span><button class="hx-pagination-jump-button" onclick="javascript:jumpPage()">确定</button>
      </div>
    </li>
    <script>
    function jumpPage() {
      var pageNum = document.getElementById('__pageNum__').value;
      if (isNaN(parseInt(pageNum))) {
        alert("请输入要跳转的页码");
        return;
      }
      <#if ajaxMethod == "">
      location.href = "${pageUrl}pageNum=" + pageNum;
      <#else>
      ${ajaxMethod}(pageNum);
      </#if>
    }
    </script>
</#if>
  </ul>
</div>
</#macro>
