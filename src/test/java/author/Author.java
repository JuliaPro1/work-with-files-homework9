package author;
import java.util.List;

public class Author {
    private String name;
    private int id;
    private double averagePriceBook;
    private int totalQuantity;
    private List<String> books;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getAveragePriceBook() {
        return averagePriceBook;
    }
    public void setAveragePriceBook(double averagePriceBook) {
        this.averagePriceBook = averagePriceBook;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<String> getBooks() {
        return books;
    }
    public void setBooks(List<String> books) {
        this.books = books;
    }
}

