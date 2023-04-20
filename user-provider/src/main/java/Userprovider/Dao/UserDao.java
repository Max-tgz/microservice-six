package Userprovider.Dao;

import model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    //注册
    int register(@Param(value = "upassword") String upassword,
                 @Param(value = "uname") String uname,
                 @Param(value =  "usex") String usex);
    //登陆
    User login(@Param(value = "uname") String uname);
}
