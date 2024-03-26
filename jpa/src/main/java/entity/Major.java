package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="major_tbl")
@ToString(exclude = "students") //연관관계가 있는 필드는 toString에서 제외를 시켜야 한다. 무한루프 걸림
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorID;
    private String name;
    private String category;

    // 관계형 데이터베이스 mysql에서 생성안됨
    @OneToMany(mappedBy = "major") // 연관관계 주인은 Student table의 major 프로퍼티라는 의미
    private List<Student> students = new ArrayList<>();

    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
