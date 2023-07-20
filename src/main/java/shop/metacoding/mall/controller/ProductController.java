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

@Controller // application.yml 에 적은 경로의 파일을 줌
//@RestController // return 값 자체를 줌 , @Controller 클래스일때 메서드에 @ResponseBody 붙인거랑 같음
public class ProductController {

    @Autowired // IOC에서 자동으로 DI(의존성주입)
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
        request.setAttribute("p", product); // setAttribute : 서버 측에서 HTTP 요청과 응답 사이에 데이터를 전달하고 유지하기 위한 임시 저장소

        return "detail";
    }



    @GetMapping("/") // @GetMapping = 클라이언트가 서버에게 데이터를 요청할때
    // 클라이언트가 주소창에 입력한 뒷부분이 /home 이면 이 메서드가 실행됨(invoke로 실행되서 메서드 이름이랑 상관없이)
    public String home(HttpServletRequest request){
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList); // jsp 제일 위에 추가해야함   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        return "home"; // @Controller라서 뷰리졸버로 경로의 파일을 실행 , application.yml에 적어둔대로 자동으로 파싱
        // 만약 @ResponseBody 를 메서드에 붙였다면 메세지 컨버터로 리턴값 자체를 반환
    }

    @GetMapping("/write")
    public String writepage(){
        return "write";
    }

    @PostMapping ("/product") // @PostMapping = 클라이언트가 서버로 데이터를 전송할 때
    public String write(String name, int price, int qty) throws IOException {
        System.out.println("name :"+name);
        System.out.println("price :"+price);
        System.out.println("qty :"+qty);

        productRepository.save(name, price, qty);

        return "redirect:/";
//        response.sendRedirect("/"); // 이거로도 되지만  매개변수에 HttpServletResponse response 넣고 반환타입을 void로 바꿔야함
    }

}

