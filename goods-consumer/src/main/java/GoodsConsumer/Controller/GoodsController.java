package GoodsConsumer.Controller;

import GoodsConsumer.Service.GoodsService;
import model.Cart;
import model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @GetMapping(value = "quit")
    public String quit(HttpSession session){
        session.removeAttribute("uaccount");
        return "redirect:http://localhost:8893/tologin";
    }
    @GetMapping(value = "/getAll")
    public String getAll(HttpServletRequest request, String uaccount,
                         String upassword,String uname,String usex, HttpSession session){
        session.setAttribute("uaccount",uaccount);
        List<Goods> goods1 = new ArrayList<>();
        List<Goods> goods2 = new ArrayList<>();
        List<Goods> goods3 = new ArrayList<>();
        List<Goods> goods4 = new ArrayList<>();
        List<Goods> oldgoods = goodsService.getAllgoods();
        for(Goods newgoods : oldgoods){
            if (newgoods.getTypes().equals(1)){
                goods1.add(newgoods);
            }
            if (newgoods.getTypes().equals(2)){
                goods2.add(newgoods);
            }
            if (newgoods.getTypes().equals(0)){
                goods3.add(newgoods);
            }
            if (newgoods.getTypes().equals(3)){
                goods4.add(newgoods);
            }
        }
        request.setAttribute("goods1",goods1);
        request.setAttribute("goods2",goods2);
        request.setAttribute("goods3",goods3);
        request.setAttribute("goods4",goods4);
        return "index";
    }
    @GetMapping(value = "/detail")
    public String detail(HttpServletRequest request, int gid){
        request.setAttribute("detail",goodsService.getOnegid(gid));
        return "detail";
    }
    @GetMapping(value = "/getOne")
    public String getOne(HttpServletRequest request, String name){
        List<Goods> goods1 = new ArrayList<>();
        List<Goods> goods2 = new ArrayList<>();
        List<Goods> goods3 = new ArrayList<>();
        List<Goods> goods4 = new ArrayList<>();
        List<Goods> oldgoods = goodsService.getOnegoods(name);
        for(Goods newgoods : oldgoods){
            System.out.println(newgoods.getTypes());
            if (newgoods.getTypes().equals(1)){
                goods1.add(newgoods);
            }
            if(newgoods.getTypes().equals(2)){
                goods2.add(newgoods);
            }
            if(newgoods.getTypes().equals(0)){
                goods3.add(newgoods);
            }
            if(newgoods.getTypes().equals(3)){
                goods4.add(newgoods);
            }
        }
        request.setAttribute("good1",goods1);
        request.setAttribute("good2",goods2);
        request.setAttribute("good3",goods3);
        request.setAttribute("good4",goods4);
        return"index";
    }

    @GetMapping(value = "cart")
    public String cart(HttpServletRequest request,String name,String price,String che,String number,HttpSession session){
        String uid = (String)session.getAttribute("uaccount");
        List<Cart> usercart = goodsService.getAllcart(Integer.parseInt(uid));
        int n = Integer.parseInt(price);
        for (int i=0;i<usercart.size();i++){
            if (usercart.get(i).getGoodid()==Integer.parseInt(che)){
                goodsService.updateCart(Integer.parseInt(number),usercart.get(i).getId());
                return "redirect:/cartAll";
            }
        }
        goodsService.intcart(name,Integer.parseInt(number),n,Integer.parseInt(che),Integer.parseInt(uid));
        return "redirect:/cartAll";
    }
    @GetMapping(value = "cartAll")
    public String cartAll(HttpServletRequest request,HttpSession session){
        String uid = (String) session.getAttribute("uaccount");
        request.setAttribute("carts",
                goodsService.getAllcart(Integer.parseInt(uid)));
        return "Settle";
    }
    //删除购物车
    @GetMapping(value = "deleteCart")
    public String deleteCart(HttpServletRequest request,String did){
        goodsService.deleteCart(Integer.parseInt(did));
        return "redirect:/cartAll";
    }
    @GetMapping(value = "/paygoods")
    public String paygoods(HttpServletRequest request,String[] goodid,String[] numaa,HttpSession session,String[] id){
        String uid = (String) session.getAttribute("uaccount");
        if (goodid.length == 0 || numaa.length==0||id.length ==0){
            return "redirect:/cartAll";
        }else{
            for (int i = 0; i< goodid.length;i++){
                goodsService.insertOrder(
                        goodsService.getOnegid(Integer.parseInt(goodid[i])).gname,Integer.parseInt(numaa[i]),
                        Integer.parseInt(numaa[i])* goodsService.getOnegid(Integer.parseInt(goodid[i])).gprice,
                        Integer.parseInt(uid));
                goodsService.deleteCart(goodsService.deleteCart(Integer.parseInt(id[i])));
            }
            request.setAttribute("mag3","购买失败");
            return "redirect:/getAllorder";
        }
    }
    //根据用户查询登陆者的历史购买记录
    @GetMapping(value = "/getAllorder")
    public String getAllorder(HttpServletRequest request,HttpSession session){
        String uid = (String) session.getAttribute("uaccount");
        request.setAttribute("order",goodsService.getAllorder(Integer.parseInt(uid)));
        if (uid.equals(null)){
            return "redirect:http:localhost:8893/admin/tologin";
        }
        return "order";
    }
}

