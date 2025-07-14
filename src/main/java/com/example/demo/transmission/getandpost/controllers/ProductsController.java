package com.example.demo.transmission.getandpost.controllers;

import com.example.demo.transmission.getandpost.model.Product;
import com.example.demo.transmission.getandpost.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductsController {

    private final ProductService productService;

    /* Чтобы получить бин сервиса из контекста Spring, мы используем DI в конструкторе контроллера */
    /* В подобных случаях @Autowired можно не использовать */
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    /* Аннотация @GetMapping связывает действие контроллера с HTTP-методом GET, имеющим заданный путь. */
    @GetMapping("/products")
    /* Определяем параметр типа Model для передачи данных в представление.
    * То есть, определяем переменную, через которую, будем обращаться к странице. */
    public String wievProducts(Model page) {
        /* Получаем от сервиса список продуктов. */
        var products = productService.findAll();
        /* Передаём список товаров в представление. */
        page.addAttribute("products", products);
        /* Возваращаем имя представления, которое будет получено и воспроизведено диспечером сервлетов.  */
        return "products.html";
    }

/* Связываем действие контроллера с путём /products.
   С помощью атрибута аннотации @RequestMapping изменяем HTTP-метод на POST. */
    /*@RequestMapping (path = "/products",
                    method = RequestMethod.POST)*/

    /* Аннотация @GetMapping связывает действие контроллера с HTTP-методом POST, имеющим заданный путь. */
    @PostMapping ("/products")
    public String addProduct(
            /* Класс модели можно передавать непосредственно в действие контроллера как параметр.
             Spring знает, что в этом случае нужно создать экземпляр,
              атрибуты которого соответствуют параметрам запроса.
               Для класса модели должен быть определен конструктор по умолчанию,
                чтобы Spring мог создать экземпляр этого класса перед вызовом метода действия. */
            Product product,
            Model page) {
        productService.addProduct(product);
        /* Получаем список товаров и передаём его в представление */
        var products = productService.findAll();
        page.addAttribute("products", products);
        /* Возвращаем имя представления, которое должно быть сформировано. */
        return "products.html";
    }
}
