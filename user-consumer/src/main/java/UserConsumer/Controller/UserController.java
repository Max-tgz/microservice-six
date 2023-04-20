package UserConsumer.Controller;

import UserConsumer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    //跳转注册
    @GetMapping(value = "/toregister")
    public String toregister(){return "register";}
    //注册
    @GetMapping(value = "/register")
    public String register(HttpServletRequest request, String upassword, String uname,String usex){
        int tmp = userService.register(upassword, uname, usex);
        System.out.print(tmp);
        if(tmp>0){
            return "login";
        }
        return "register";
    }


    @GetMapping(value = "/tologin")
    public  String tologin(){return "login";}
    //用户登陆
    @GetMapping(value = "/login")
    public String login(String uname,String upassword, HttpServletRequest request){
        if(userService.login(uname) != null){
            if(userService.login(uname).getUpassword().equals(upassword)){
                HttpSession session = request.getSession(true);
                session.setAttribute("user",userService.login(uname));
                return "redirect:http://localhost:8896/getAll?uaccount="+
                        userService.login(uname).getUaccount()+"&upassword="+userService.login(uname).getUpassword();
            }
        }
        request.setAttribute("msg","账号密码错误");
        return "login";
    }

}
