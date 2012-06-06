/* 
 * @(#)ResponseMessage.java    Created on 2012-6-6
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id$
 */
package demo.spring.mvc.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Controller 操作的响应消息类，主要用在以 <code>ResponseBody</code> 方式返回结果的方法中。
 * 
 * @author wj.huang
 * @version $Revision: 1.0 $, $Date: 2012-6-6 下午12:05:08 $
 */
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = 6725085407815077443L;

    private Collection<String> actionMessages;
    private Collection<String> actionErrors;
    private Map<String, Collection<String>> fieldErrors;

    public Collection<String> getActionErrors() {
        return actionErrors;
    }

    public void setActionErrors(Collection<String> actionErrors) {
        this.actionErrors = actionErrors;
    }

    public Collection<String> getActionMessages() {
        return actionMessages;
    }

    public void setActionMessages(Collection<String> actionMessages) {
        this.actionMessages = actionMessages;
    }

    public Map<String, Collection<String>> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, Collection<String>> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}
