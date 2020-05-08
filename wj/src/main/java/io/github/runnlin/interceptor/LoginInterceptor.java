package io.github.runnlin.interceptor;

import io.github.runnlin.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();
        // 需要进行拦截的路径
        String[] requireAuthPages = new String[] {
                "index",
        };

        String uri = httpServletRequest.getRequestURI();
        // 只取得资源路径
        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;

        if (beginWith(page, requireAuthPages)) {
            User user = (User)session.getAttribute("user");
            if (null == user) {
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    /*
      判断当前路径是否是需要拦截的路径
     */
    private boolean beginWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if (StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
