package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MQuestionAnswer;
import com.org.entity.MQuestionComment;
import com.org.entity.vo.MQuestionCommentVO;
import com.org.entity.vo.MyMQuestionCommentVO;
import com.org.service.MQuestionAnswerService;
import com.org.service.MQuestionCommentService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@RestController
@RequestMapping("/find/mQuestionComment")
public class MQuestionCommentController {
    @Autowired
    private MQuestionCommentService mQuestionCommentService;

    @Autowired
    private MQuestionAnswerService mQuestionAnswerService;

    /**
     * 分页查询我的回答列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchMyMQuestionCommentByPage")
    public ServerResponseVO searchMyMQuestionCommentByPage(HttpServletRequest request, HttpServletResponse response,
                                                           @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                                                           @RequestParam(name = "pageSize", defaultValue = "5") Long pageSize) {
        Long userId = JwtUtil.getId(request);
        List<MyMQuestionCommentVO> myMQuestionCommentVOS = mQuestionCommentService.searchMyMQuestionCommentByPage(userId, (pageNum - 1) * pageSize, pageSize);
        return ServerResponseVO.success(myMQuestionCommentVOS);
    }

    /**
     * 根据问题Id查询问题的回答
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchMQuestionCommentBy")
    public ServerResponseVO searchMQuestionCommentBy(HttpServletRequest request, HttpServletResponse response,
                                                     Integer questionId) {
        List<MQuestionCommentVO> mQuestionCommentVOS = mQuestionCommentService.searchMQuestionCommentBy(questionId);
        return ServerResponseVO.success(mQuestionCommentVOS);
    }

    /**
     * 采纳问题回答
     */
    @RequestMapping(method = RequestMethod.GET, value = "/takeQuestionComment")
    public ServerResponseVO takeQuestionComment(String id, Boolean isBest, HttpServletRequest request,
                                                HttpServletResponse response) {
        //修改问题回答的采纳
        MQuestionComment mQuestionComment = mQuestionCommentService.getById(id);
        mQuestionComment.setIsBest(isBest);
        boolean i = mQuestionCommentService.updateById(mQuestionComment);
        return ServerResponseVO.success(i);
    }

    /**
     * 新增问题回答
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ServerResponseVO add(@RequestBody MQuestionComment mQuestionComment, HttpServletRequest request,
                                HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        mQuestionComment.setUserId(userId);

//        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
//        YitIdHelper.setIdGenerator(options);
//        Long QuestionCommentId = YitIdHelper.nextId() / 1000000;
//        mQuestionComment.setId(QuestionCommentId);
        mQuestionCommentService.save(mQuestionComment);
        //修改问题的回答数
        MQuestionAnswer mQuestionAnswer = mQuestionAnswerService.getById(mQuestionComment.getQuestionId());
        Long commentNum = Long.valueOf(mQuestionAnswer.getCommentNum() + 1);
        int i = mQuestionCommentService.updateQuestionAnswerCommentNum(commentNum, mQuestionAnswer.getId());
        return ServerResponseVO.success(i);
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(@RequestBody MQuestionComment mQuestionComment, HttpServletRequest request,
                         HttpServletResponse response) {
        mQuestionCommentService.updateById(mQuestionComment);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mQuestionCommentService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public ServerResponseVO detail(HttpServletRequest request, HttpServletResponse response,
                                   String id) {
        MQuestionComment mQuestionComment = mQuestionCommentService.getById(id);
        return ServerResponseVO.success(mQuestionComment);
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MQuestionComment> wrapper = new QueryWrapper<>();
        List<MQuestionComment> list = mQuestionCommentService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MQuestionComment query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MQuestionComment> wrapper = new QueryWrapper<>();
        Page<MQuestionComment> pg = new Page<MQuestionComment>(pageNum, pageSize);
        pg = mQuestionCommentService.page(pg, wrapper);
        return "ok";
    }

}

