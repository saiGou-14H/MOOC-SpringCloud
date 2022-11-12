package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.entity.MCourseChapter;
import com.org.entity.MLearnProgress;
import com.org.service.IStudentLearnProgressService;
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
public class StudentLearnProgressController {
    @Autowired
    IStudentLearnProgressService iStudentLearnProgressService;

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
        if (mLearnProgress1!=null){
            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("stu_id",Stuid);
            uw.eq("cou_id",mLearnProgress.getCouId());
            uw.eq("cha_id",mLearnProgress.getChaId());
            return ServerResponseVO.success(iStudentLearnProgressService.update(mLearnProgress,uw));
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
