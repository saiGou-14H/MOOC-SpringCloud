package com.org.entity.dto;

import com.org.entity.MStudentCourseOrder;
import com.org.entity.MStudentCourseOrderDetail;
import lombok.Data;

import java.util.List;

/**
 * @Author Jie
 * @Date 2022-11-08
 */
@Data
public class OrderDTO {
     MStudentCourseOrder order;

    private List<MStudentCourseOrderDetail> list;

}
