package net.kielsaenz.consultorio.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware {

    protected HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
