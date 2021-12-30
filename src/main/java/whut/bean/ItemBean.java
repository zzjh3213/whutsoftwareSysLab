package whut.bean;

import java.util.Objects;

public abstract class ItemBean {
    private Integer Id;
    private String Name;
    private String Author;
    private String Publisher;
    private Double Price;
    private Integer TotalCnt;   //库存中总数量
    private Integer Remain;     //库存总剩余数量

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getTotalCnt() {
        return TotalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        TotalCnt = totalCnt;
    }

    public Integer getRemain() {
        return Remain;
    }

    public void setRemain(Integer remain) {
        Remain = remain;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Price=" + Price +
                ", TotalCnt=" + TotalCnt +
                ", Remain=" + Remain +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemBean itemBean = (ItemBean) o;
        return Objects.equals(Name, itemBean.Name) &&
                Objects.equals(Author, itemBean.Author) &&
                Objects.equals(Publisher, itemBean.Publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Author, Publisher);
    }
}
