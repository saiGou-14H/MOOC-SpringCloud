package com.org.ResponseVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@NoArgsConstructor
@Data
public class ClassResponse {

    private int code;
    private String message;
    private List<ClassListEntity> data;

    @NoArgsConstructor
    @Data
    public static class ClassListEntity {
        private long stuId;
        private int classId;
        private String joinDate;
        private ClassEntity classX;

        @NoArgsConstructor
        @Data
        public static class ClassEntity {
            private int id;
            private long teaId;
            private String name;
            private int numbers;
            private boolean deleted;
        }
    }
}
