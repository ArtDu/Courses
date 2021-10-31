package homework;


public class Product {
    private final int id;
    private final int cost;
    private final String title;

    public Product(int id, int cost, String title) {
        this.id = id;
        this.cost = cost;
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", cost=" + cost +
                ", title='" + title + '\'' +
                '}';
    }
}
