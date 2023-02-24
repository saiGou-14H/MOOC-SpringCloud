package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MMessage;
import com.org.entity.MMessageComment;
import com.org.entity.vo.MMessageCommentVO;
import com.org.service.MMessageCommentService;
import com.org.service.MMessageService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 资讯评论表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@RestController
@RequestMapping("/find/mMessageComment")
public class MMessageCommentController {
    @Autowired
    private MMessageCommentService mMessageCommentService;

    @Autowired
    private MMessageService mMessageService;

    /**
     * 根据资讯Id查询资讯的评论
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchMMessageCommentBy")
    public ServerResponseVO searchMMessageCommentBy(HttpServletRequest request, HttpServletResponse response,
                                                    Integer messageId) {

        List<MMessageCommentVO> mMessageCommentVOS = mMessageCommentService.searchMMessageCommentBy(messageId);
        return ServerResponseVO.success(mMessageCommentVOS);
    }

    /**
     * 用户点赞资讯
     */
    @RequestMapping(method = RequestMethod.POST, value = "/LikeMMessageByUserId")
    public String LikeMMessageByUserId(@RequestBody MMessageComment mMessageComment, HttpServletRequest request,
                                       HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        mMessageComment.setUserId(userId);
        //先查询用户有没有评论过，有就是修改，没有就插入
        //首先判断之前是否有，但是评论有多条
//        mMessageCommentService.save(mMessageComment);
        mMessageCommentService.addMMessageComment(mMessageComment);
        return "ok";
    }

    /**
     * 新增资讯评论
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ServerResponseVO add(@RequestBody MMessageComment mMessageComment, HttpServletRequest request,
                                HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        mMessageComment.setUserId(userId);
        mMessageCommentService.save(mMessageComment);
//        mMessageCommentService.addMMessageComment(mMessageComment);
        //修改资讯的评论数
        MMessage mMessage = mMessageService.getById(mMessageComment.getMessageId());
        Long commentNum = Long.valueOf(mMessage.getCommentNum() + 1);
        int i = mMessageCommentService.updateMessageCommentNum(commentNum, mMessage.getId());
        return ServerResponseVO.success(i);
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String edit(@RequestBody MMessageComment mMessageComment, HttpServletRequest request,
                       HttpServletResponse response) {
        mMessageCommentService.updateById(mMessageComment);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mMessageCommentService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public MMessageComment detail(HttpServletRequest request, HttpServletResponse response,
                                  String id) {
        MMessageComment mMessageComment = mMessageCommentService.getById(id);
        return mMessageComment;
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MMessageComment> wrapper = new QueryWrapper<>();
        List<MMessageComment> list = mMessageCommentService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MMessageComment query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MMessageComment> wrapper = new QueryWrapper<>();
        Page<MMessageComment> pg = new Page<MMessageComment>(pageNum, pageSize);
        pg = mMessageCommentService.page(pg, wrapper);
        return "ok";
    }

}

