package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.entity.MQuestionAnswer;
import com.org.entity.vo.QuestionAnswerPageVO;
import com.org.service.MQuestionAnswerService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 话题/问题表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@RestController
@RequestMapping("/find/mQuestionAnswer")
public class MQuestionAnswerController {
    @Autowired
    private MQuestionAnswerService mQuestionAnswerService;

    /**
     * 分页查询所有问题列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchMQuestionAnswerByPage")
    public ServerResponseVO searchMQuestionAnswerByPage(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                        @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<QuestionAnswerPageVO> messagePageVOList = mQuestionAnswerService.searchmQuestionAnswerByPage((pageNum - 1) * pageSize, pageSize);
        return ServerResponseVO.success(messagePageVOList);
    }

    /**
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(@RequestBody MQuestionAnswer mQuestionAnswer, HttpServletRequest request,
                      HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        mQuestionAnswer.setAuId(userId);

        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
        YitIdHelper.setIdGenerator(options);
        Long QuestionAnswerId = YitIdHelper.nextId() / 1000000;
        mQuestionAnswer.setId(QuestionAnswerId);
        mQuestionAnswerService.save(mQuestionAnswer);
        return "ok";
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(@RequestBody MQuestionAnswer mQuestionAnswer, HttpServletRequest request,
                         HttpServletResponse response) {
        mQuestionAnswerService.updateById(mQuestionAnswer);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mQuestionAnswerService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response,
                         String id) {
        MQuestionAnswer mQuestionAnswer = mQuestionAnswerService.getById(id);
        return "ok";
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MQuestionAnswer> wrapper = new QueryWrapper<>();
        List<MQuestionAnswer> list = mQuestionAnswerService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MQuestionAnswer query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MQuestionAnswer> wrapper = new QueryWrapper<>();
        Page<MQuestionAnswer> pg = new Page<MQuestionAnswer>(pageNum, pageSize);
        pg = mQuestionAnswerService.page(pg, wrapper);
        return "ok";
    }

}

