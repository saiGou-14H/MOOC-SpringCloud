package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MStudentCourseOrderDetail;
import com.org.service.MStudentCourseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 订单明细表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@RestController
@RequestMapping("/find/mStudentCourseOrderDetail")
public class MStudentCourseOrderDetailController {
    @Autowired
    private MStudentCourseOrderDetailService mStudentCourseOrderDetailService;


    /**
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(@RequestBody MStudentCourseOrderDetail mStudentCourseOrderDetail, HttpServletRequest request,
                      HttpServletResponse response) {
        mStudentCourseOrderDetailService.save(mStudentCourseOrderDetail);
        return "ok";
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(@RequestBody MStudentCourseOrderDetail mStudentCourseOrderDetail, HttpServletRequest request,
                         HttpServletResponse response) {
        mStudentCourseOrderDetailService.updateById(mStudentCourseOrderDetail);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mStudentCourseOrderDetailService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response,
                         String id) {
        MStudentCourseOrderDetail mStudentCourseOrderDetail = mStudentCourseOrderDetailService.getById(id);
        return "ok";
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MStudentCourseOrderDetail> wrapper = new QueryWrapper<>();
        List<MStudentCourseOrderDetail> list = mStudentCourseOrderDetailService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MStudentCourseOrderDetail query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MStudentCourseOrderDetail> wrapper = new QueryWrapper<>();
        Page<MStudentCourseOrderDetail> pg = new Page<MStudentCourseOrderDetail>(pageNum, pageSize);
        pg = mStudentCourseOrderDetailService.page(pg, wrapper);
        return "ok";
    }

}

