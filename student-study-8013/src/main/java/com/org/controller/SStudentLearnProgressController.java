package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.entity.MLearnProgress;
import com.org.entity.MUser;
import com.org.service.IStudentLearnProgressService;
import com.org.service.IUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("app/study")
public class SStudentLearnProgressController {
    @Autowired
    IStudentLearnProgressService iStudentLearnProgressService;
    @Autowired
    IUserService iUserService;

    @RequestMapping("shLearnProgress/{courseid}/{chaid}")
    public ServerResponseVO shLearnProgress(HttpServletRequest request, @PathVariable Integer courseid,@PathVariable Integer chaid){
        Long Stuid = JwtUtil.getId(request);
        QueryWrapper<MLearnProgress> qw = new QueryWrapper<>();
        qw.eq("stu_id",Stuid).eq("cou_id",courseid).eq("cha_id",chaid);
        return ServerResponseVO.success(iStudentLearnProgressService.getOne(qw));
    }
    @RequestMapping("upLearnProgress")
    public ServerResponseVO upLearnProgress(HttpServletRequest request,@RequestBody MLearnProgress mLearnProgress){
        Long Stuid = JwtUtil.getId(request);
        QueryWrapper<MLearnProgress> qw = new QueryWrapper<>();
        qw.eq("stu_id",Stuid).eq("cou_id",mLearnProgress.getCouId()).eq("cha_id",mLearnProgress.getChaId());
        MLearnProgress mLearnProgress1 = iStudentLearnProgressService.getOne(qw);

        QueryWrapper<MUser> qw2 = new QueryWrapper<>();
        qw2.eq("id",Stuid);
        MUser user = iUserService.getOne(qw2);
        UpdateWrapper<MUser> uw2 = new UpdateWrapper<>();
        uw2.eq("id",Stuid);
        Long TodayStudyTime =0L;
        Long StudyTime = 0L;
        System.out.println(user.getTodayStudyTime());
        System.out.println(user.getStudyTime());
        if(user.getTodayStudyTime()!=null){
            TodayStudyTime = user.getTodayStudyTime();
        }
        if(user.getStudyTime()!=null){
            StudyTime = user.getStudyTime();
        }

        if (mLearnProgress1!=null){
            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("stu_id",Stuid);
            uw.eq("cou_id",mLearnProgress.getCouId());
            uw.eq("cha_id",mLearnProgress.getChaId());
            user.setTodayStudyTime(TodayStudyTime+(mLearnProgress.getDayTime()-mLearnProgress1.getDayTime())/1000);
            user.setStudyTime(StudyTime+(mLearnProgress.getDayTime()-mLearnProgress1.getDayTime())/1000);
            iUserService.update(user,uw2);
            if(mLearnProgress1.getProgress()<1&&mLearnProgress.getProgress()>=1){
                IntegralHistoryController.adIntegralHistory(request,IntegralHistoryController.FINSH_CATA,2,true);
            }
            return ServerResponseVO.success(iStudentLearnProgressService.update(mLearnProgress,uw));
        }
        user.setTodayStudyTime(TodayStudyTime+mLearnProgress.getDayTime()/1000);
        user.setStudyTime(StudyTime+mLearnProgress.getDayTime()/1000);
        iUserService.update(user,uw2);
        mLearnProgress.setStuId(Stuid);
        if(mLearnProgress.getProgress()>=1){
            IntegralHistoryController.adIntegralHistory(request,IntegralHistoryController.FINSH_CATA,2,true);
        }
        return ServerResponseVO.success(iStudentLearnProgressService.save(mLearnProgress));
    }

    @RequestMapping("shLearnProgress/{courseid}")
    public ServerResponseVO shLearnProgressBycourse(HttpServletRequest request, @PathVariable Integer courseid){
        Long Stuid = JwtUtil.getId(request);
        QueryWrapper<MLearnProgress> qw = new QueryWrapper<>();
        qw.eq("stu_id",Stuid).eq("cou_id",courseid);
        List<MLearnProgress> mLearnProgress = iStudentLearnProgressService.list(qw);
        mLearnProgress.sort(Comparator.comparing(MLearnProgress::getChaIndex));
        return ServerResponseVO.success(mLearnProgress);
    }
}
