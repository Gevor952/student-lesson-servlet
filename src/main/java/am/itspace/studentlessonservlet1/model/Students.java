package am.itspace.studentlessonservlet1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    private int id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Lessons lesson;
    User user;
}
