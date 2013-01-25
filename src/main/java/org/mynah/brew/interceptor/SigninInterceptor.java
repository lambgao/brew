package org.mynah.brew.interceptor;

import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.mynah.brew.service.UserService;
import org.mynah.brew.util.Constants;
import org.mynah.brew.util.CookieUtil;

public class SigninInterceptor extends HandlerInterceptorAdapter {

    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug(request.getServletPath() + "preHandle-----SigninInterceptor Start!");
        HttpSession session = request.getSession();
        Object object = session.getAttribute(Constants.SESSION_USER);
        if (null == object) {
            Cookie[] cookies = request.getCookies();
            String su = CookieUtil.getValue(cookies, Constants.COOKIE_SU);
            if (null != su) {
                String[] values = su.split(":");
                String email = values[0];
                String password = values[1];
                if (userService.verifyPassword(email, password)) {
                    session.setAttribute(Constants.SESSION_USER, userService.get(email));
                }
            }
        }
        logger.debug(request.getServletPath() + "preHandle-----SigninInterceptor Done!");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug(request.getServletPath() + "postHandle-----SigninInterceptor Start!");
        if (modelAndView != null) {
            Map<String, Object> model = modelAndView.getModel();
            for (String key : model.keySet()) {
                if (key.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
                    BindingResult result = (BindingResult) model.get(key);
                    logger.info(key + ":" + result.hasErrors());
                }
            }
        }
        logger.debug(request.getServletPath() + "postHandle-----SigninInterceptor Done!");
    }
}
