package com.org.ResponseVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseTypeResponse {

    private int code;
    private String message;
    private CourseTypeEntity data;

    @NoArgsConstructor
    @Data
    public static class CourseTypeEntity {
        private int id;
        private String type;
        private Object parentId;
        private Object picture;
        private CourseTypeEntity parent;
    }
}
