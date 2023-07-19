package shop.metacoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void write(String name, int price, int qty, HttpServletResponse response) throws IOException {
        System.out.println("name :"+name);
        System.out.println("price :"+price);
        System.out.println("qty :"+qty);
//        return "write";

        productRepository.save(name, price, qty);
        response.sendRedirect("/");
//        return "redirect:/";
    }

}

