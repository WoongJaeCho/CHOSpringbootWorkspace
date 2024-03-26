package entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="student_tbl")
@ToString(exclude = "major")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;
    private String name;
    private String grade;

    //private Long majorID;             // FetchType.LAZY 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY) // 관계 구성 FetchType.EAGER(기본값) : 즉시로딩 : 연관되어 있는 모든
    @JoinColumn(name="majorID") // 테이블 컬럼의 FK명
    private Major major;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true) // 이름 안주면 클래스이름_ID로 생성함
    private Locker locker; //locker_id 1를 참고하고 있으면 다른 student lock-
    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}




