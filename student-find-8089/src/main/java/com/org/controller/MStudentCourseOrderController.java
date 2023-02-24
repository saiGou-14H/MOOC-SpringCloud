package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.entity.MStudentCourseCart;
import com.org.entity.MStudentCourseOrder;
import com.org.entity.MStudentCourseOrderDetail;
import com.org.entity.MUser;
import com.org.entity.dto.IDListDTO;
import com.org.entity.dto.OrderDTO;
import com.org.entity.vo.CourseVO;
import com.org.entity.vo.StudentOrderCourseVO;
import com.org.service.MStudentCourseCartService;
import com.org.service.MStudentCourseOrderDetailService;
import com.org.service.MStudentCourseOrderService;
import com.org.service.MUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseEnum;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@RestController
@RequestMapping("/find/mStudentCourseOrder")
public class MStudentCourseOrderController {
    @Autowired
    private MStudentCourseOrderService mStudentCourseOrderService;
    @Autowired
    private MStudentCourseOrderDetailService mStudentCourseOrderDetailService;
    @Autowired
    private MStudentCourseCartService mStudentCourseCartService;
    @Autowired
    private MUserService mUserService;

    /**
     * 分页查询用户的订单（包括课程的详情）
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchOrderCourseByPage")
    public ServerResponseVO searchStudentCourseOrder(HttpServletRequest request, HttpServletResponse response,
                                                     String couName,
                                                     @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                                                     @RequestParam(name = "pageSize", defaultValue = "5") Long pageSize) {
        Long userId = JwtUtil.getId(request);
        Map<String, Object> map = new HashMap<>();
        map.put("couName", couName);
        map.put("userId", userId);
        map.put("start", (pageNum - 1) * pageSize);
        map.put("end", pageSize);
//        Long userId = 202361020504320;
        List<StudentOrderCourseVO> studentOrderCourseVOList = mStudentCourseOrderService.searchOrderCourseByPage(map);
        return ServerResponseVO.success(studentOrderCourseVOList);
    }

    /**
     * 分页查询，通过购物车
     * couIdList查询课程的详情
     */
    @RequestMapping(method = RequestMethod.POST, value = "/searchCourseList")
    public ServerResponseVO searchCourseList(HttpServletRequest request, HttpServletResponse response, @RequestBody IDListDTO idListDTO) {
        List<CourseVO> list = mStudentCourseOrderService.searchCourseList(idListDTO.getIds());
        return ServerResponseVO.success(list);
    }

    /**
     * 新增订单
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addOrder")
    public ServerResponseVO add(@RequestBody OrderDTO orderDTO, HttpServletRequest request,
                                HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        System.out.println();

//        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
//        YitIdHelper.setIdGenerator(options);
//        Long OrderId = YitIdHelper.nextId() / 1000000;
//        mStudentCourseOrder.setId(OrderId);
//        //加入订单表
//        mStudentCourseOrder.setStuId(userId);
//        mStudentCourseOrder.setPayState(true);
//        mStudentCourseOrderService.save(mStudentCourseOrder);
//        //加入订单详情表
//        Long couId = Long.valueOf("346764331");
//        MStudentCourseOrderDetail mStudentCourseOrderDetail = new MStudentCourseOrderDetail();
//        Long OrderDetailId = YitIdHelper.nextId() / 1000000;
//        mStudentCourseOrderDetail.setId(OrderDetailId);
//        mStudentCourseOrderDetail.setCourseId(couId);
//        mStudentCourseOrderDetail.setOrderId(mStudentCourseOrder.getId());
//        mStudentCourseOrderDetail.setPrice(256);
//        mStudentCourseOrderDetailService.save(mStudentCourseOrderDetail);


//        如果钱不够 不能买
        MUser mUser = mUserService.getById(userId);
        if (mUser.getIntegral() >= orderDTO.getOrder().getAllIntegral()) {
            //生成订单id
            IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
            YitIdHelper.setIdGenerator(options);
            Long OrderId = YitIdHelper.nextId() / 1000000;
            //加入订单表
            orderDTO.getOrder().setId(OrderId);
            orderDTO.getOrder().setStuId(userId);
            orderDTO.getOrder().setPayState(true);
            mStudentCourseOrderService.save(orderDTO.getOrder());
            List<MStudentCourseOrderDetail> list = orderDTO.getList();
            for (MStudentCourseOrderDetail mStudentCourseOrderDetail : list) {
                //加入订单详情表
                //生成订单详情id
//                Long couId = Long.valueOf("346764331");
                Long OrderDetailId = YitIdHelper.nextId() % 1000000;
                mStudentCourseOrderDetail.setId(OrderDetailId);
//                mStudentCourseOrderDetail.setCourseId(couId);
                mStudentCourseOrderDetail.setOrderId(orderDTO.getOrder().getId());
                mStudentCourseOrderDetailService.save(mStudentCourseOrderDetail);
                //思考：如果购物车没有这个课程
                //删除购物车
                QueryWrapper<MStudentCourseCart> wrapper = new QueryWrapper<>();
                wrapper.eq("cou_id", mStudentCourseOrderDetail.getCourseId());
                mStudentCourseCartService.remove(wrapper);
            }

            //扣钱
            int money = mUser.getIntegral() - orderDTO.getOrder().getAllIntegral();
            mUser.setIntegral(money);
            mUserService.updateById(mUser);


        } else {
            return ServerResponseVO.error(ServerResponseEnum.ERROR);
        }
        return ServerResponseVO.success(ServerResponseEnum.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(@RequestBody MStudentCourseOrder mStudentCourseOrder, HttpServletRequest request,
                         HttpServletResponse response) {
        mStudentCourseOrderService.updateById(mStudentCourseOrder);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mStudentCourseOrderService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response,
                         String id) {
        MStudentCourseOrder mStudentCourseOrder = mStudentCourseOrderService.getById(id);
        return "ok";
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MStudentCourseOrder> wrapper = new QueryWrapper<>();
        List<MStudentCourseOrder> list = mStudentCourseOrderService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MStudentCourseOrder query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MStudentCourseOrder> wrapper = new QueryWrapper<>();
        Page<MStudentCourseOrder> pg = new Page<MStudentCourseOrder>(pageNum, pageSize);
        pg = mStudentCourseOrderService.page(pg, wrapper);
        return "ok";
    }

}

