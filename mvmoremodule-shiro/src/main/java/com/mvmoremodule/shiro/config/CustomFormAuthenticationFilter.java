package com.mvmoremodule.shiro.config;/**
 * Created by Administrator on 2018-06-07.
 */

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author xuzhiyong
 * @createDate 2018-06-07-11:52
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }
}
