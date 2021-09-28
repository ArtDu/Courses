package com.geekbrains.spring.current.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ShopService {
    private ProductService productService;
    private OrderService orderService;
    private MailService mailService;
    private BeanInterface beanInterface;
    private BeanInterface beanInterface2;

    @Autowired
    @Qualifier("beanInterfaceImpl")
    public void setBeanInterface(BeanInterface beanInterface) {
        this.beanInterface = beanInterface;
    }

    @Autowired
    @Qualifier("advBeanInterfaceImpl")
    public void setBeanInterface2(BeanInterface beanInterface2) {
        this.beanInterface2 = beanInterface2;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void buy() {
        productService.getProducts();
        orderService.createOrder();
        mailService.sendMail();
        beanInterface.doSomething();
    }
}
