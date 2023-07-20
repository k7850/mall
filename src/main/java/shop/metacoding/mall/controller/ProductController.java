package shop.metacoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.metacoding.mall.model.Product;
import shop.metacoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/product/update")
    public String update(int id, String name, int price, int qty) {
        productRepository.update(id, name, price, qty);
        return "redirect:/product/"+id;
    }


    @PostMapping("/product/delete")
    public String delete(int id) {
        productRepository.deleteById(id);
        return "redirect:/"; // 리다이렉트 하지 않고 그냥  return "home";  을 하면 상품목록이 안나온다. 리퀘스트에 안담았으니까
//        response.sendRedirect("/"); // 이거로도 되지만  매개변수에 HttpServletResponse response 넣고 반환타입을 void로 바꿔야함
    }



    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        System.out.println("id: "+id);
        Product product = productRepository.findById(id);
        request.setAttribute("p", product);

        return "detail";
    }



    @GetMapping("/")
    public String home(HttpServletRequest request){
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);
        return "home";
    }

    @GetMapping("/write")
    public String writepage(){
        return "write";
    }

    @PostMapping ("/product")
    public String write(String name, int price, int qty) throws IOException {
        System.out.println("name :"+name);
        System.out.println("price :"+price);
        System.out.println("qty :"+qty);
//        return "write";
        productRepository.save(name, price, qty);
//        response.sendRedirect("/");
        return "redirect:/";
    }

}

