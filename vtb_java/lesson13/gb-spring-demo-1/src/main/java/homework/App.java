package homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        productService.printAll();
        
        Cart cart  = context.getBean("cart", Cart.class);
        cart.add(productService.findByTitle("Banana"));
        cart.add(productService.findByTitle("Apple"));

        OrderService os = context.getBean("orderService", OrderService.class);
        os.calculate();
        context.close();
    }
}
