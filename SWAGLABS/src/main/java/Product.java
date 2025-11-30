public class Product {
    private String name;
    private String price;
    private String description;
    private String addButtonId;
    private String removeButtonId;
    private String imageUrl;

    // Constructor
    public Product(String name, String price, String description,
                   String addButtonId, String removeButtonId, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.addButtonId = addButtonId;
        this.removeButtonId = removeButtonId;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getAddButtonId() {
        return addButtonId;
    }

    public String getRemoveButtonId() {
        return removeButtonId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
