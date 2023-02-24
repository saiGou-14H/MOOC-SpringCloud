package com.org.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.entity.MIntegralHistory;
import com.org.entity.MLearnProgress;
import com.org.entity.MUser;
import com.org.service.IIntegralHistoryService;
import com.org.service.IUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("app/integralhistory")
@Component
public class IntegralHistoryController implements ApplicationContextAware {
    @Autowired
    static IIntegralHistoryService iIntegralHistoryService;
    @Autowired
    static IUserService iUserService;
    protected static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext app) throws BeansException {
        if (applicationContext == null) {
            applicationContext = app;
        }
    }

    /**
     * 通过类的class从容器中手动获取对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }


    final static String UP = "签到";
    final static String FINSH_CATA = "完成章节";
    final static String PAY_COURSE = "购买课程";
    final static String SEND_QUESTION = "提出问题";
    final static String Accept_ANSWER  = "回答被采纳";

    //写入流水并更新余额
    public static boolean adIntegralHistory(HttpServletRequest request, String massage, int Integralchild, boolean type){
        Long StuId = JwtUtil.getId(request);
        MIntegralHistory mIntegralHistory = new MIntegralHistory();
        mIntegralHistory.setStuId(StuId);
        mIntegralHistory.setOrigin(massage);
        mIntegralHistory.setType(type);
        mIntegralHistory.setIntegralchild(Integralchild);
        QueryWrapper<MUser> qw = new QueryWrapper<>();
        qw.eq("id",StuId);
        MUser mUser = getBean(IUserService.class).getOne(qw);
        UpdateWrapper<MUser> uw = new UpdateWrapper<>();
        uw.eq("id",StuId);
        if (mIntegralHistory.getType()){
            mUser.setIntegral(mUser.getIntegral()+mIntegralHistory.getIntegralchild());
        }else{
            mUser.setIntegral(mUser.getIntegral()-mIntegralHistory.getIntegralchild());
        }
        getBean(IUserService.class).update(mUser,uw);
        mIntegralHistory.setTime(LocalDateTime.now());
        return getBean(IIntegralHistoryService.class).save(mIntegralHistory);
    }
    @RequestMapping("shintegralhistory")
    public ServerResponseVO getintegralhistory(HttpServletRequest request){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MIntegralHistory> qw = new QueryWrapper<>();
        qw.eq("stu_id",StuId);
        List<MIntegralHistory> mIntegralHistoryList =  getBean(IIntegralHistoryService.class).list(qw);
        mIntegralHistoryList.sort(Comparator.comparing(MIntegralHistory::getTime).reversed());
        return ServerResponseVO.success(mIntegralHistoryList);
    }

    @RequestMapping("adintegralhistory")//显示信息，积分额度，操作类型（加/减）
    public ServerResponseVO Requests_adIntegralHistory(HttpServletRequest request,@RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        return ServerResponseVO.success(IntegralHistoryController.adIntegralHistory(request, (String) jsonObject.get("message"), (Integer) jsonObject.get("money"),(boolean)jsonObject.get("type")));
    }

}
