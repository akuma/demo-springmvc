var ajaxDebug = false;

function ajaxPost(url, param, callback, mime) {
  if (ajaxDebug) {
    alert("param: " + param + "\nmime: " + mime);
  }

  var loadingDiv = document.createElement('DIV');
  loadingDiv.setAttribute("id", "loading");
  loadingDiv.style.cssText = "display:none;font-family:Verdana;font-size:11px;border:1px solid #00CC00;background-color:#A4FFA4;padding:1px;position:absolute;right:2px;top:1px;height:14px;z-index:10000";
  loadingDiv.innerHTML = "Loading...";
  document.body.appendChild(loadingDiv);

  $("#loading").show(); // 显示进度条
  var _callback = function(result) {
    if (ajaxDebug) {
      alert("result: " + result);
    }

    try {
      callback(result);
    } catch (ex) {
      ; // Ignore
    } finally {
      $("#loading").hide(); // 隐藏进度条
    }
  };

  $.post(url, param, _callback, mime ? mime : "json");
}

function clientDrawError(elementId, error) {
  var html = "<ul><li>" + error + "</li></ul>";
  $("#message").html(html);
  $("#" + elementId).focus();
}

// 用来显示服务端返回信息
function drawMessages(result) {
  var allMessages = "";

  // 如果返回消息有错误, 则显示异常信息
  if (result.exception) {
    var errorDiv = $("#actionError");
    if (errorDiv.length) {
      allMessages = result.exception.message;
      errorDiv.attr("style", errorStyle);
      errorDiv.html(errorImage + allMessages);
    } else {
      var stackTrace = result.exception.stackTrace.replace(/\t/g, "&nbsp; &nbsp; ").replace(/\n/g, "<br/>");
      allMessages += "<ul style='color:maroon'>";
      allMessages += "<h1>Server Error</h1>";
      allMessages += "<p>您可以将此错误报告给我们的系统管理员，也可以稍后重试刚才的操作。谢谢您的合作！</p>";
      allMessages += "<h3>错误信息</h3><p>" + result.exception.message + "</p>";
      allMessages += "<h3><a id='stackTraceText' href='javascript:stackTraceSwitch()'>显示错误详情</a></h3><p id='stackTrace' style='display:none'>"
          + stackTrace + "</p>";
      allMessages += "</ul>";
      $("#message").html(allMessages);
    }
    return;
  }

  // 如果是脚本就执行
  if (result.script) {
    eval(result.script);
  }

  if (result.fieldErrors) {
    for ( var i in result.fieldErrors) {
      var errorDiv = $("#" + i + "Error");
      if (errorDiv.length) {
        errorDiv.attr("style", feildErrorStyle);
        errorDiv.html(errorImage + result.fieldErrors[i]);
        // if ($("#" + i).length) {
        // $("#" + i).focus();
        // } else {
        // $(":text[name='" + i + "']").focus();
        // }
      } else {
        allMessages += "<li>" + result.fieldErrors[i] + "</li>";
      }
    }
  }

  if (result.actionMessages && result.actionMessages.length > 0) {
    var errorDiv = $("#actionError");
    if (errorDiv.length) {
      errorDiv.attr("style", messageStyle);
      errorDiv.html(messageImage + result.actionMessages);
    } else {
      for ( var i = 0; i < result.actionMessages.length; i++) {
        allMessages += "<span class=\"msg\">" + result.actionMessages[i] + "</span>";
      }
    }
  } else if (result.actionErrors && result.actionErrors.length > 0) {
    var errorDiv = $("#actionError");
    if (errorDiv.length) {
      errorDiv.attr("style", errorStyle);
      errorDiv.html(errorImage + result.actionErrors);
    } else {
      for ( var i = 0; i < result.actionErrors.length; i++) {
        allMessages += "<li>" + result.actionErrors[i] + "</li>";
      }
    }
  }

  if ($("#message").length && allMessages) {
    allMessages = "<ul>" + allMessages + "</ul>";
    $("html,body").animate({
      scrollTop : 0
    }, 100);
    $("#message").html(allMessages);
  }
}

// 显示客户端错误信息
function drawClientError(divId, error) {
  var div = $("#" + divId);
  div.attr("style", errorStyle);
  div.html(errorImage + error);
}

// 显示客户端提示信息
function drawClientMessage(divId, message) {
  var div = $("#" + divId);
  div.attr("style", messageStyle);
  div.html(messageImage + message);
}

function stackTraceSwitch() {
  if ($("#stackTrace").css("display") == "none") {
    $("#stackTrace").css("display", "");
    $("#stackTraceText").text("隐藏错误详情");
  } else {
    $("#stackTrace").css("display", "none");
    $("#stackTraceText").text("显示错误详情");
  }
}

function hasErrors(result) {
  if (result.exception) {
    return true;
  }

  if (!$.isEmptyObject(result.fieldErrors)) {
    return true;
  }

  if (result.actionErrors && result.actionErrors.length > 0) {
    return true;
  }

  return false;
}

function hasActionErrors(result) {
  return hasErrors(result);
}

function setHome(url, title) {
  window.external.AddFavorite(url, title);
}

function setSelected(id, value) {
  $("#" + id).attr("value", value);
}

/**
 * 对字符串进行 Base64 编码。
 */
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
function base64encode(str) {
  var out, i, len;
  var c1, c2, c3;
  len = str.length;
  i = 0;
  out = "";
  while (i < len) {
    c1 = str.charCodeAt(i++) & 0xff;
    if (i == len) {
      out += base64EncodeChars.charAt(c1 >> 2);
      out += base64EncodeChars.charAt((c1 & 0x3) << 4);
      out += "==";
      break;
    }
    c2 = str.charCodeAt(i++);
    if (i == len) {
      out += base64EncodeChars.charAt(c1 >> 2);
      out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
      out += base64EncodeChars.charAt((c2 & 0xF) << 2);
      out += "=";
      break;
    }
    c3 = str.charCodeAt(i++);
    out += base64EncodeChars.charAt(c1 >> 2);
    out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
    out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
    out += base64EncodeChars.charAt(c3 & 0x3F);
  }
  return out;
}

/**
 * 将 UTF-16 字符转换为 UTF-8。
 */
function utf16to8(str) {
  var out, i, len, c;
  out = "";
  len = str.length;
  for (i = 0; i < len; i++) {
    c = str.charCodeAt(i);
    if ((c >= 0x0001) && (c <= 0x007F)) {
      out += str.charAt(i);
    } else if (c > 0x07FF) {
      out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
      out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
      out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
    } else {
      out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
      out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
    }
  }
  return out;
}
