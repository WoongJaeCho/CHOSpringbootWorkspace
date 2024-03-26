package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
//@NoArgsConstructor // 기본생성자 반드시 필수!! JPA -> new Customer() ..
@Table(name="Customer_tb")
public class Customer {

    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate regDate;

    public Customer(String name) {

        this.name = name;
        regDate = LocalDate.now();
    }

    public Customer() {

    }
}
