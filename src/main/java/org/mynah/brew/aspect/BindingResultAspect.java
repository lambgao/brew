package org.mynah.brew.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BindingResultAspect {

    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Before("execution(* org.mynah.brew.controller..*.*(..))")
    public void doAccessCheck(JoinPoint jp) {
        logger.error("before");
        logger.error("Before :" + jp.getArgs()[0].getClass());
    }
    
}
