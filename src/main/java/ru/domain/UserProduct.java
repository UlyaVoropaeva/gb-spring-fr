package ru.domain;

import javax.persistence.*;

@Entity
@Table(name = "users_product")
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "price")
    private int price;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public Client getUser() {
        return client;
    }


    public Product getProduct() {
        return product;
    }
}