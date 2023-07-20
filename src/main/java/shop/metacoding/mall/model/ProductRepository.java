package shop.metacoding.mall.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴퍼넌트 스캔
public class ProductRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }

    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productlist = query.getResultList();
        return productlist;
    }

    public Product findById(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
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
        String name2 = (String) object[1];
        int price2 = (int) object[2];
        int qty2 = (int) object[3];

        Product product = new Product();
        product.setId(id2);
        product.setName(name2);
        product.setPrice(price2);
        product.setQty(qty2);
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

