package com.org.service;

import com.org.model.StudentClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学生班级关联表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
public interface IStudentClassService extends IService<StudentClass> {

    public List<Long> shClassStu(Long cla_id);
}
