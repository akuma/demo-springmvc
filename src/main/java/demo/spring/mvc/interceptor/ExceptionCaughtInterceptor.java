/* 
 * @(#)ExceptionCaughtInterceptor.java    Created on 2012-3-15
 * Copyright (c) 2005-2012 shunwang. All rights reserved.
 * $Id: ExceptionCaughtInterceptor.java 2 2012-04-05 08:03:20Z wj.huang $
 */
package demo.spring.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对异常捕获并根据条件进行包装的拦截器。
 * 
 * <p>
 * 对于输出方式为 JSON 的 Action，目前有两种处理方式：
 * 
 * <ul>
 * <li>如果抛出异常为 <code>DataExistsException</code>、<code>DataMissingException</code>、<code>DataModifiedException</code>，会添加
 * Action 出错提示信息；</li>
 * <li>否则捕获异常后会封装为 <code>org.apache.struts2.json.JSONException</code> 后抛出。</li>
 * </ul>
 * 
 * @author wj.huang
 * @version $Revision: 2 $, $Date: 2012-04-05 16:03:20 +0800 (Thu, 05 Apr 2012) $
 */
public class ExceptionCaughtInterceptor {

    private static final long serialVersionUID = -7368264899101543792L;

    private static Logger logger = LoggerFactory.getLogger(ExceptionCaughtInterceptor.class);

    // @Override
    // public String intercept(ActionInvocation invocation) throws Exception {
    // String namespace = invocation.getProxy().getNamespace();
    // String actionName = invocation.getProxy().getActionName();
    // logger.debug("Request action info: namespace={}, actionName={}", namespace, actionName);
    //
    // try {
    // return invocation.invoke();
    // } catch (Exception e) {
    // if (isJsonAction(invocation)) {
    // if (e instanceof DataExistsException || e instanceof DataMissingException
    // || e instanceof DataModifiedException) {
    // Object action = invocation.getProxy().getAction();
    // if (action instanceof ActionSupport) {
    // ActionSupport actionSupport = (ActionSupport) action;
    // actionSupport.addActionError(e.getMessage());
    // return Action.SUCCESS;
    // }
    // }
    //
    // logger.error("Action invoke error", e);
    // throw new JSONException(e);
    // } else {
    // logger.error("Action invoke error", e);
    // throw e;
    // }
    // }
    // }
    //
    // /**
    // * 判断 Action 是否为采用 JSON 为输出方式 (ResultType) 的 Action。
    // *
    // * @param invocation
    // * ActionInvocation 对象
    // * @return true/false
    // */
    // private static boolean isJsonAction(ActionInvocation invocation) {
    // boolean isJsonAction = false;
    // Map<String, ResultConfig> results = invocation.getProxy().getConfig().getResults();
    // if (!results.isEmpty()) {
    // ResultConfig resultConf = results.values().iterator().next();
    // if (JSONResult.class.getName().equals(resultConf.getClassName())) {
    // isJsonAction = true;
    // }
    // }
    // return isJsonAction;
    // }

}
