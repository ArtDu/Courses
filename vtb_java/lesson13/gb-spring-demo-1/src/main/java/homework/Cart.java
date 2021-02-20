package homework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Cart {
    private final List<Product> products;

    Cart(){
        products = new ArrayList<Product>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product){
        if(product != null)
            products.add(product);
    }

}
