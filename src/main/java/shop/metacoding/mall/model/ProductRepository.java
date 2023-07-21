package shop.metacoding.mall.model;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴퍼넌트 스캔 // 클래스를 IoC에 bean으로 등록하고 + 데이터베이스와의 상호작용을 처리하는 repository임을 표시  / repository는 DAO랑 비슷한 개념
public class ProductRepository {

    @Autowired // IOC에서 자동으로 DI(의존성주입)
    private EntityManager em; // @Entity 붙은것들 불러옴




    public Product findByIdJoinSeller2(int id) {
        Query query = em.createNativeQuery("select p.id a, p.name b, p.price c, p.qty d, p.seller_id e, s.id f, s.email g, s.name h from product_tb p inner join seller_tb s on p.seller_id = s.id where p.id = :id");
        query.setParameter("id", id);
        // row가 1건
        // 1, 바나나, 1000, 50

        Object[] object = (Object[]) query.getSingleResult();
        int id2 = (int) object[0];
        String name2 = (String) object[1];
        int price2 = (int) object[2];
        int qty2 = (int) object[3];

        Product product = new Product();
        product.setId(id2);
        product.setName(name2);
        product.setPrice(price2);
        product.setQty(qty2);

//        System.out.println(object[4].getClass());

        Seller seller = new Seller();
        seller.setId((Integer) object[5]);
        seller.setEmail((String) object[6]);
        seller.setName((String) object[7]);

        product.setSeller(seller);

        return product;
    }

    public Product findByIdJoinSeller(int id){
        Query query = em.createNativeQuery("select * from product_tb inner join seller_tb on product_tb.seller_id = seller_tb.id where product_tb.id = :id", Product.class);
        query.setParameter("id", id);
        return (Product)query.getSingleResult();
    }


    public ProductDTO findByIdDTO(int id){
        Query query = em.createNativeQuery("select id, name, price, qty, '설명' as des from product_tb where id= :id");
        query.setParameter("id", id);

        JpaResultMapper mapper = new JpaResultMapper(); // build.gradle 에 QLRM 추가해야함
        ProductDTO productDTO = mapper.uniqueResult(query, ProductDTO.class);
        return productDTO;
    }


    @Transactional // db작업 전부 정상적으로 종료되면 커밋, 예외가 발생하면 롤백   /   DB 변경(C,U,D) 있는데 @Transactional 빼먹으면 오류뜸
    public void save2(String name, int price, int qty, int seller){ // 매개변수 이름이 jsp의 이름이랑도 같아야 함
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty, seller_id) values(:name, :price, :qty, :seller)"); // 앞에 :붙은건 그냥 문법
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.setParameter("seller", seller);
        query.executeUpdate();
    }

    @Transactional // db작업 전부 정상적으로 종료되면 커밋, 예외가 발생하면 롤백   /   DB 변경(C,U,D) 있는데 @Transactional 빼먹으면 오류뜸
    public void save(String name, int price, int qty){ // 매개변수 이름이 jsp의 이름이랑도 같아야 함
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)"); // 앞에 :붙은건 그냥 문법
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }

    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class); //  쿼리 결과를 Product클래스 형태로
        List<Product> productlist = query.getResultList();
        return productlist;
    }

    public Product findById(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class); //  쿼리 결과를 Product클래스 형태로
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();
        return product;
    }

    public Product findById2(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id");
        query.setParameter("id", id);
        // row가 1건
        // 1, 바나나, 1000, 50

        Object[] object = (Object[]) query.getSingleResult();
        int id2 = (int) object[0];
        String name = (String) object[1];
        int price = (int) object[2];
        int qty = (int) object[3];

//        System.out.println(object[4].getClass());

        Seller seller = new Seller();
        seller.setId((Integer) object[4]);

        Product product = new Product();
        product.setId(id2);
        product.setName(name);
        product.setPrice(price);
        product.setQty(qty);
        product.setSeller(seller);
        return product;
    }

    @Transactional // db작업 전부 정상적으로 종료되면 커밋, 예외가 발생하면 롤백   /   DB 변경(C,U,D) 있는데 @Transactional 빼먹으면 오류뜸
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from product_tb where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    @Transactional // db작업 전부 정상적으로 종료되면 커밋, 예외가 발생하면 롤백   /   DB 변경(C,U,D) 있는데 @Transactional 빼먹으면 오류뜸
    public void update(int id, String name, int price, int qty) {
        Query query = em.createNativeQuery("update product_tb set name=:name, price=:price, qty=:qty where id=:id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }


}

