package entity;

public class Category {
    private int category_id;
    private String cname;
    private String img;

    public Category() {}

    public Category(int category_id, String cname, String img) {
        this.category_id = category_id;
        this.cname = cname;
        this.img = img;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Category{" + "category_id=" + category_id + ", cname=" + cname + ", img=" + img + '}';
    }
}
