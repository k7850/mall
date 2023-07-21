package shop.metacoding.mall.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String des; // 상품설명


    @Builder
    public ProductDTO(Integer id, String name, Integer price, Integer qty, String des) { // 롬복으로 올아귀먼트생성자 만들면 매개변수 넣는 순서가 꼬일 수 있으니까 지양하자
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.des = des;
    }



}

