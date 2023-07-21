package shop.metacoding.mall.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString

@Entity // 이 클래스가 JPA 엔티티임을 나타냄. 데이터베이스의 테이블과 자동으로 매핑되는 객체임을 의미
@Table(name = "seller_tb") // 클래스명이랑 DB랑 테이블명이 다르면 직접 매핑
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // @Id = PK ,     @GeneratedValue(strategy = GenerationType.IDENTITY) = AI
    private String name;
    private String email;
}
