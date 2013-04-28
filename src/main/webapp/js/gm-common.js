/*! GM Common JS.
 * Copyright (c) 2013 Guomi. All rights reserved.
 */

(function($, window, undefined) {

  "use strict"; // jshint ;_;

  // 当 gm-common.js 被重复引入时进行提示
  if ($.gmc !== undefined) {
    alert("gm-common has already been loaded!");
    return;
  }

  var document = window.document,
  console = window.console,
  gmc;

  $.gmc = gmc = {

    // 系统提示信息相关的（fieldErrors、actionErrors、actionMessages）的样式名定义
    messageBoxId_ : "messageBox",
    controlGroupClass_ : "control-group",
    fieldErrorGroupClass_ : "error",
    fieldErrorClass_ : "alert-field-error", // 输入框的固定位置的出错信息（div/span）的样式
    fieldErrorDriftFieldClass_ : "alert-error-drift", // 输入框的动态浮动的出错信息
    fieldErrorDriftClass_ : "alert alert-block alert-error-drift",
    fieldErrorFieldClass_ : "hx-error-field", // 标注出错的输入框
    actionErrorClass_ : "alert alert-block alert-error",
    actionMessageClass_ : "alert alert-block alert-success",
    exceptionErrorClass_ : "alert alert-block",
    // 关闭系统提示信息框的按钮
    messageBoxCloseBtn_ : "<button type='button' class='close' data-dismiss='alert'>&times;</button>",

    // ajax 操作成功时（仅仅是 status 200，也可能包含业务错误）的回调接口
    ajaxSuccess : function(data, status, xhr, pageFragmentId) {
      var ct = xhr.getResponseHeader("content-type") || "";
      if (ct.indexOf("text/html") === -1) {
        // 判断返回的数据类型，如果是 string，则需要解析为 json 对象
        if ($.type(data) === "string") {
          data = $.parseJSON(data);
        }
        gmc.drawMessages(data);
      } else if ($("#" + pageFragmentId).length) {
        $("#" + pageFragmentId).html(data);
      }
    },

    // ajax 操作失败时（status 非 200，如服务器内部错误）
    ajaxError : function(error, status, xhr) {
      gmc.drawMessages(error, null, xhr);
    },

    // 将系统返回的 fieldErrors、actionErrors、actionMessages 显示到页面
    drawMessages : function(reply, messageBoxId, xhr) {
      gmc.clearMessages(messageBoxId);

      // 定义将被多个代码块使用的局部变量，避免 JSLint 提示：'xx' is already defined.
      var position, i;

      if (!messageBoxId) {
        messageBoxId = gmc.messageBoxId_;
      }

      // 显示系统提示信息的 div 容器
      var $messageBox = $("#" + messageBoxId);
      if (!$messageBox.length) {
        $("body").prepend("<div id=\"" + gmc.messageBoxId_ + "\"></div>");
        $messageBox = $("#" + gmc.messageBoxId_);
      }

      // 如果返回消息有错误, 则显示异常信息
      if (xhr && xhr.status != 200 || reply.exception) {
        var statusCode = xhr ? xhr.status : "",
        statusText = xhr ? xhr.statusText : "",
        exceptionHtml = "<div>" + gmc.messageBoxCloseBtn_;

        exceptionHtml += "<h4>" + statusText + " " + statusCode + "</h4>";
        exceptionHtml += ((statusCode === 404 || statusCode === 405) ? "您请求的资源不存在。" : "对不起，服务器开小差了，请稍后再试试吧！");
        exceptionHtml += "</div>";
        $messageBox.append($(exceptionHtml).addClass(gmc.exceptionErrorClass_));

        position = $messageBox.offset();
        window.scrollTo(0, position.top - 200);
        return;
      }

      var hasActionMessages = gmc.hasMessages(reply);
      if (hasActionMessages) {
        var messages = gmc.getMessages(reply),
        messageHtml = "<div>" + gmc.messageBoxCloseBtn_ + "<ul>";
        for (i = 0; i < messages.length; i++) {
          messageHtml += "<li>" + messages[i] + "</li>";
        }
        messageHtml += "</ul></div>";

        $messageBox.append($(messageHtml).addClass(gmc.actionMessageClass_));

        position = $messageBox.offset();
        window.scrollTo(0, position.top - 200);
      }

      var errors = gmc.getErrors(reply),
      hasActionErrors = errors && errors.length > 0;
      if (hasActionErrors) {
        var errorHtml = "<div>" + gmc.messageBoxCloseBtn_ + "<ul>";
        for (i = 0; i < errors.length; i++) {
          errorHtml += "<li>" + (errors[i].defaultMessage || errors[i]) + "</li>";
        }
        errorHtml += "</ul></div>";
        $messageBox.append($(errorHtml).addClass(gmc.actionErrorClass_));

        position = $messageBox.offset();
        window.scrollTo(0, position.top - 200);
      }

      var fieldErrors = reply.fieldErrors;
      if (fieldErrors) {
        var $firstFieldErrorBox,
        $firstErrorField,
        fieldName;

        for (fieldName in fieldErrors) {
          var anError = fieldErrors[fieldName][0],
          useDriftStyle = false,
          escapeFieldName = fieldName.replace(/\[/, "\\[").replace(/\]/, "\\]"),
          fieldErrorBox = $("#" + escapeFieldName + "Error")[0];

          if (!fieldErrorBox) {
            useDriftStyle = true;
            fieldErrorBox = document.createElement("div");
            fieldErrorBox.id = fieldName + "Error";
          }

          // 获取删除了 spring 返回的数组 error 字段中的 [xx] 部分，如：answer[0] => answer
          var fieldNameWithoutSq = fieldName.replace(/\[.*\]/, ""),
          $field = $("[name='" + fieldNameWithoutSq + "']").first();
          if (!$firstErrorField) {
            $firstErrorField = $field;
            $firstErrorField.focus();
          }
          $field.addClass(gmc.fieldErrorFieldClass_); // 用样式标注出错的输入框
          $field.closest("." + gmc.controlGroupClass_).addClass(gmc.fieldErrorGroupClass_);

          $(fieldErrorBox).addClass(gmc.fieldErrorClass_);
          $(fieldErrorBox).html(anError);
          if (useDriftStyle) {
            $(fieldErrorBox).addClass(gmc.fieldErrorDriftClass_);
            $(fieldErrorBox).css({
              "position" : "absolute",
              "display" : "none",
              "z-index" : "999"
            });
            $field.after(fieldErrorBox);

            $field.bind("mouseover", gmc.mouseoverHandler)
                  .bind("mouseout", gmc.mouseoutHandler)
                  .bind("focus",gmc.focusHandler).bind("blur", gmc.blurHandler);
            $field.mouseover();
          }

          if (!$firstFieldErrorBox) {
            $firstFieldErrorBox = $(fieldErrorBox);
          }
        }

        if ($firstFieldErrorBox && !hasActionMessages && !hasActionErrors) {
          var firstPosition = $firstFieldErrorBox.offset();
          window.scrollTo(0, firstPosition.top - 200);
        }
      }
    },

    clearMessages : function(messageBoxId) {
      // TODO 将按钮设置为正在提交的状态
      try {
        var array = document.getElementsByTagName("input"), i;
        if (array.length > 0) {
          for (i = 0; i < array.length; i++) {
            if (array[i].type === "submit") {
              array[i].disabled = false;
            }
          }
        }
      } catch (ex) {
        // ignore
      }

      // 清除 Success 提示信息
      if (!messageBoxId) {
        messageBoxId = gmc.messageBoxId_;
      }
      var $messageBox = $("#" + messageBoxId);
      $messageBox.empty();
      $messageBox.removeClass();

      // 清除 Action Error 提示信息
      var $errorBox = $("." + gmc.actionErrorClass_);
      $errorBox.empty();
      $errorBox.removeClass();

      // 清除 Field Error 提示信息
      // 先把所有出错字段的提示信息清空并移除样式
      var $fieldErrorBoxes = $("." + gmc.fieldErrorClass_);
      $fieldErrorBoxes.empty();
      $fieldErrorBoxes.removeClass(gmc.fieldErrorClass_);
      // 再把所有浮动提示的出错字段删除
      $("." + gmc.fieldErrorDriftFieldClass_).remove();
      // 最后移除 bootstrap control group 的 error 样式
      var $controlGroups = $("." + gmc.controlGroupClass_);
      $controlGroups.removeClass(gmc.fieldErrorGroupClass_);
      // 清除 Error Field 的绑定时间
      var $errorFields = $("." + gmc.fieldErrorFieldClass_);
      $errorFields.unbind("mouseover", gmc.mouseoverHandler);
      $errorFields.unbind("mouseout", gmc.mouseoutHandler);
      $errorFields.unbind("focus", gmc.focusHandler);
      $errorFields.unbind("blur", gmc.blurHandler);
    },

    mouseoutHandler : function() {
      var box = $("#" + this.name + "Error");
      if (box.size() !== 0) {
        box.hide();
      }
    },

    mouseoverHandler : function() {
      var box = $("#" + this.name + "Error");
      if (box.size() !== 0) {
        // 这里用 position() 比用 offset() 更通用
        // 在父元素使用相对定位的情况下，用 offset 取到的位置不符合要求
        var position = $(this).position();
        // 这里用 innerHeight() 比用 height() 更通用，因为有可能输入框会设置 padding
        // height() 不包含 padding，而 innerHeight() 包含 padding
        box.css("top", position.top + $(this).innerHeight() + 4);
        box.css("left", position.left);
        box.show();
      }
    },

    focusHandler : function() {
      var box = $("#" + this.name + "Error");
      if (box.size() !== 0) {
        var position = $(this).position();
        box.css("top", position.top + $(this).innerHeight() + 4);
        box.css("left", position.left);
        box.show();
      }
    },

    blurHandler : function() {
      var box = $("#" + this.name + "Error");
      if (box.size() !== 0) {
        box.hide();
      }
    },

    hasErrors : function(reply) {
      if (!reply) {
        return false;
      }

      if (reply.hasErrors || reply.exception) {
        return true;
      }

      // 以下是通过 Controller.addActionError()、Controller.addFieldError() 添加的消息的判断方式
      if (!$.isEmptyObject(reply.fieldErrors)) {
        return true;
      }

      var errors = gmc.getErrors(reply);
      return errors && errors.length > 0;
    },

    hasMessages : function(reply) {
      if (!reply) {
        return false;
      }

      var messages = gmc.getMessages(reply);
      return messages && messages.length > 0;
    },

    getErrors : function(reply) {
      return reply.errors || reply.actionErrors;
    },

    getMessages : function(reply) {
      return reply.messages || reply.actionMessages;
    },

    stackTraceSwitch : function() {
      if ($("#stackTrace").is(":hidden")) {
        $("#stackTraceText").text("隐藏错误详情");
        $("#stackTrace").show();
      } else {
        $("#stackTraceText").text("显示错误详情");
        $("#stackTrace").hide();
      }
    },

    autoLoadByScroll : function(handler) {
      if (!$.isFunction(handler)) {
        return;
      }

      $(document).scroll(function() {
        if ($(window).scrollTop() + $(window).height() === $(document).height()) {
          handler();
        }
      });
    }
  };

  $(function() {
    // Show loading status when ajax call
    var ajaxLoadingBox = "<div id='hx-loading-box' class='loading-box-wrapper' style='display:none'>" +
      "<div class='loading-box'>正在加载...</div></div>";
    $("body").append(ajaxLoadingBox);
    $(document).ajaxStart(function() {
      $("#hx-loading-box").show();
    }).ajaxStop(function() {
      $("#hx-loading-box").hide();
    }).ajaxSuccess(function(event, xhr) {
      var data = xhr.responseText,
      ct = xhr.getResponseHeader("content-type") || "";
      // 判断返回的数据类型，如果是 string，则需要解析为 json 对象
      if (ct.indexOf("text/html") === -1) {
        if ($.type(data) === "string") {
          data = $.parseJSON(data);
          // 如果是一段脚本则执行，主要为了支持 session 过期后，请求 ajax 后可以刷新页面
          if (data.script) {
            new Function(data.script)();
          }
        }
      }
    });

    // 定义 ajax 成功后的回调方法
    var ajaxSuccessCallback = function(evt, data, status, xhr) {
      evt.stopPropagation(); // 阻止冒泡事件

      var dataCallback,
      dataCallbackStr = $(this).data("success");
      //console.log("data-success: " + dataCallbackStr);
      if (dataCallbackStr) {
        dataCallback = window[dataCallbackStr];
      }

      if (!dataCallback) {
        dataCallback = gmc.ajaxSuccess;
      }

      // 获取在返回 html 数据时需要被替换内容的页面片段节点 ID
      var pageFragmentId = $(this).data("fragment");
      try {
        dataCallback(data, status, xhr, pageFragmentId);
      } catch (e) {
        $("#hx-loading-box").hide();
        if (console) {
          console.error(e.message);
        }
      }
    };

    // 定义 ajax 出错后的回调方法
    var ajaxErrorCallback = function(evt, xhr, status, error) {
      evt.stopPropagation(); // 阻止冒泡事件

      var dataCallback,
      dataCallbackStr = $(this).data("error");
      if (dataCallbackStr) {
        dataCallback = window[dataCallbackStr];
      }

      if (!dataCallback) {
        dataCallback = gmc.ajaxError;
      }

      try {
        dataCallback(error, status, xhr);
      } catch (e) {
        $("#hx-loading-box").hide();
        if (console) {
          console.error(e.message);
        }
      }
    };

    // 绑定 ajax 回调方法
    $(document).on("ajax:success", "form, a, button, input, textarea, select", ajaxSuccessCallback).
      on("ajax:error", "form, a, button, input, textarea, select", ajaxErrorCallback);

    // 找出所有设置了 data-date 属性的字段，设置日期控件
    // TODO 增加日期格式控制
    $("input[data-date]").datepicker();
  });
})(jQuery, window);

/* Chinese initialisation for the date picker and time picker. */
(function($) {
  if ($.datepicker) {
    $.datepicker.regional['zh-CN'] = {
      closeText : '关闭',
      prevText : '&#x3C;上月',
      nextText : '下月&#x3E;',
      currentText : '今天',
      monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
      monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
      dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
      dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
      dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
      weekHeader : '周',
      dateFormat : 'yy-mm-dd',
      firstDay : 1,
      isRTL : false,
      showMonthAfterYear : true,
      yearSuffix : '年'
    };
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
  }

  if ($.timepicker) {
    $.timepicker.regional['zh-CN'] = {
      timeOnlyTitle: '选择时间',
      timeText: '时间',
      hourText: '小时',
      minuteText: '分钟',
      secondText: '秒钟',
      millisecText: '微秒',
      timezoneText: '时区',
      currentText: '现在时间',
      closeText: '关闭',
      timeFormat: 'HH:mm',
      amNames: ['AM', 'A'],
      pmNames: ['PM', 'P'],
      isRTL: false
    };
    $.timepicker.setDefaults($.timepicker.regional['zh-CN']);
  }
})(jQuery);
