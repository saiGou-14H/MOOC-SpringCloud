package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MStudentCourseCart;
import com.org.entity.vo.StudentCourseCartVo;
import com.org.service.MStudentCourseCartService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 购物车表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@RestController
@RequestMapping("/find/mStudentCourseCart")
public class MStudentCourseCartController {
    @Autowired
    private MStudentCourseCartService mStudentCourseCartService;

    /**
     * 分页查询用户的购物车
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchStudentCourseCart")
    public ServerResponseVO searchStudentCourseCart(HttpServletRequest request, HttpServletResponse response,
                                                    @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                                                    @RequestParam(name = "pageSize", defaultValue = "5") Long pageSize) {
        Long userId = JwtUtil.getId(request);

//        Long userId = 202361020504320;
        List<StudentCourseCartVo> mMessageCommentVOS = mStudentCourseCartService.searchStudentCourseCart(userId, (pageNum - 1) * pageSize, pageSize);
        return ServerResponseVO.success(mMessageCommentVOS);
    }


    /**
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(@RequestBody MStudentCourseCart mStudentCourseCart, HttpServletRequest request,
                      HttpServletResponse response) {
        mStudentCourseCartService.save(mStudentCourseCart);
        return "ok";
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String edit(@RequestBody MStudentCourseCart mStudentCourseCart, HttpServletRequest request,
                       HttpServletResponse response) {
        mStudentCourseCartService.updateById(mStudentCourseCart);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mStudentCourseCartService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response,
                         String id) {
        MStudentCourseCart mStudentCourseCart = mStudentCourseCartService.getById(id);
        return "ok";
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MStudentCourseCart> wrapper = new QueryWrapper<>();
        List<MStudentCourseCart> list = mStudentCourseCartService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MStudentCourseCart query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MStudentCourseCart> wrapper = new QueryWrapper<>();
        Page<MStudentCourseCart> pg = new Page<MStudentCourseCart>(pageNum, pageSize);
        pg = mStudentCourseCartService.page(pg, wrapper);
        return "ok";
    }

}

