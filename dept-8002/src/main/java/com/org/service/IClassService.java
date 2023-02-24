package com.org.service;

import com.org.model.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.org.model.dto.ClassDTO1;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
public interface IClassService extends IService<Class> {

    public boolean updateCourse(Class classData);

    public List<Class> shClass(Long class_id);

    public List<Class> searchAllClass(Long tea_id);

    public List<ClassDTO1> shQueCom(Map<String, Long> map);
}
