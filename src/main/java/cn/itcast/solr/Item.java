package cn.itcast.solr;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Mary on 2018/1/30.
 */
public class Item {
    @Field("id")
    private String id;
    @Field("title")
    private String title;
    @Field("price")
    private Long price;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Item(String id, String title, Long price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    public Item() {
    }
}
