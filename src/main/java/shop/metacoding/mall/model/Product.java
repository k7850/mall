package shop.metacoding.mall.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString

@Entity // 이 클래스가 JPA 엔티티임을 나타냄. 데이터베이스의 테이블과 자동으로 매핑되는 객체임을 의미
@Table(name = "product_tb") // 클래스명이랑 DB랑 테이블명이 다르면 직접 매핑
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // @Id = PK ,     @GeneratedValue(strategy = GenerationType.IDENTITY) = AI
    private String name;
    private Integer price; // Integer라서 null값 넣어짐
    private Integer qty;

    @ManyToOne // 안적으면 오류, 상품 여러개를 판매자 1명이 팔수도 있으니까 Product가 fk, Seller가 pk
    private Seller seller;
}
