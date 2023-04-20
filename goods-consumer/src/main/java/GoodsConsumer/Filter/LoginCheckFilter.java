package GoodsConsumer.Filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object
                             handle)throws Exception
    {
        HttpSession session = request.getSession();
        String userInfo = (String) session.getAttribute("uaccount");
        if(userInfo==null){
            response.sendRedirect(request.getContextPath()+
                    "http://localhost:8893/tologin");
            return false;
        }
        return true;

    }
}
