package com.orange.base.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orange.base.entity.JSONResult;
import com.orange.base.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckLoginService {

    @Autowired
    JqueryCommonService jqueryCommonService ;

    /**
     * 校验密码，密码通过写入session
     * @param userId 用户名
     * @param passWord 密码
     * @param session session
     * @return JSONResult
     */
    public JSONResult check(String userId, String passWord, HttpSession session) throws Exception {

        Map<String,Object> map = new HashMap<>() ;
        map.put("userId",userId) ;

        int status ;
        String msg ;
        // todo 密码加密
        passWord = EncryptionUtil.getCipherByStr(passWord);
        // TODO 查数据库
        List<Map<String,String>> resList = new ArrayList<>() ;
        String str = jqueryCommonService.handle("com.orange.base.mapper.DoLoginMapper.doLogin",map) ;
        JSONArray json = JSONArray.parseArray(str) ;
        JSONObject obj = (JSONObject) json.get(0);
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
