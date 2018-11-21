package com.orange.base.service;

import com.orange.base.entity.JSONResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CheckLoginService {

    /**
     * 校验密码，密码通过写入session
     * @param userId 用户名
     * @param passWord 密码
     * @param session session
     * @return JSONResult
     */
    public JSONResult check(String userId, String passWord, HttpSession session){

        int status = 0 ;
        String msg = "" ;
        // TO-DO 密码加密
        passWord = passWord+"1" ;
        // TO-DO 查数据库
        List<Map<String,String>> resList = new ArrayList<>() ;
        if(resList.size()==0){
            status = 201 ;
            msg = "用户不存在。" ;
        } else if (!passWord.equals(resList.get(0).get("PASSWORD"))) {
            status = 202 ;
            msg = "密码不正确。" ;
        } else if("N".equals(resList.get(0).get("YXBZ"))){
            status = 203 ;
            msg = "用户状态异常。" ;
        } else{
            status = 200 ;
            msg = "用户校验成功。" ;
            session.setAttribute("current_user_id",userId);
        }
        return JSONResult.build(status,msg) ;
    }
}
