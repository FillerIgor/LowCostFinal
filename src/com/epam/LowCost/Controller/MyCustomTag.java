package com.epam.LowCost.Controller;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;


public class MyCustomTag extends SimpleTagSupport {
    private String message;
    StringWriter strWriter = new StringWriter();
    public void setMessage(String mes){
        this.message=mes;
    }
public void  doTag() throws JspException, IOException{
    JspWriter out = getJspContext().getOut();
    if (message != null) {

         out = getJspContext().getOut();
        out.println( message );
    }
    else {

        getJspBody().invoke(strWriter);
        out.println(strWriter.toString());
    }
    
}
}
