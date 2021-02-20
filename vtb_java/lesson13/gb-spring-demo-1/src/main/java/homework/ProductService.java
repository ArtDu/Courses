package homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class ProductService {
    private List<Product> products;

    public void printAll() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public Product findByTitle(String title) {
        for (Product p : products) {
            if (title.equals(p.getTitle()))
                return p;
        }
        return null;
    }

    @PostConstruct
    public void ready(){
        String[] fruits = {"Apple","Mango","Peach","Banana","Orange","Grapes","Watermelon","Tomato"};
        products = new ArrayList<Product>();
        for (int i = 0; i < 10; i++) {
            int idx = new Random().nextInt(fruits.length);
            String random = (fruits[idx]);
            products.add(new Product(i, idx, random));
        }
    }
}
