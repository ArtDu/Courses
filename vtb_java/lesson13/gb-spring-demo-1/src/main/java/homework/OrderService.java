package homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {
    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void calculate(){
        List<Product> products = cart.getProducts();
        int sum = 0;
        System.out.println("Check:");
        for(Product p : products){
            System.out.println(p);
            sum += p.getCost();
        }
        System.out.println("Sum = " + sum);
    }
}
