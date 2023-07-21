package shop.metacoding.mall.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// build.gradle에 runtimeOnly 'com.h2database:h2' 이거 추가해야함



//@Rollback(false) // 테스트라도 메서드 종료시마다 롤백 안하기
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 DB로 테스트

@Import({ProductRepository.class, SellerRepository.class})
@DataJpaTest // 톰켓 -> DS(Dispatcher Servlet) -> 컨트롤러 -> Repository -> DB 중에서 (Repository -> DB 만 테스트)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;



    @Test
    public void findByIdDto_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("테스트용22", 1234, 12);

        // when (테스트 진행)
        ProductDTO productDTO = productRepository.findByIdDTO(1);

        // then (테스트 결과 확인)
        System.out.println("\n"+productDTO+"\n");
    }




    @Test
    public void findByIdJoinSeller_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        sellerRepository.create("테스트용판매자", "asd@gsedfsdf.asd");
        productRepository.save2("테스트용상품", 1234, 12,1);

        // when (테스트 진행)
        Product product1 = productRepository.findById(1);
        Product product2 = productRepository.findById2(1);
        Product product3 = productRepository.findByIdJoinSeller(1);
        Product product4 = productRepository.findByIdJoinSeller2(1);

        // then (테스트 결과 확인)
        System.out.println("********");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);
        System.out.println("********");
    }

    @Test
    public void findById_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        sellerRepository.create("테스트용판매자", "asd@gsedfsdf.asd");
        productRepository.save2("바34나나", 5000, 50,1);

        // when (테스트 진행)
        System.out.println("********");
        Product product = productRepository.findById(1);
        System.out.println("********");

        // then (테스트 결과 확인)
        System.out.println(product);
    }

    @Test
    public void findByAll_test(){
        // given
        productRepository.save("ASD", 2344, 1);
        productRepository.save("QWER", 234, 2);

        // when
        List<Product> plist = productRepository.findAll();

        // then
        for (Product product : plist) {
            System.out.println("***************");
            System.out.println(product);
        }
        System.out.println("***************");
    }




}
