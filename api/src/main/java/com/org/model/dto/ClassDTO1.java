package com.org.model.dto;

import com.org.model.QuestionComment;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClassDTO1 implements Serializable {

    private static final long serialVersionUID = 1L;
    //班级id
    private Long claId;
    //班级名
    private String claName;
    //学生提出的问题以及回复
    private List<QuestionAnswerDTO1> questionAnswers;
}
