package com.miguel.spring.infrastructure.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "tb_request")
public class Request implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "pizzaId")
    private Pizza pizzaId;

    @ManyToOne
    @JoinColumn(name = "telephone")
    private Customer customer;

    public Request() {}

    public Request(LocalDateTime date, Integer amount, Double price, Pizza pizzaId, Customer customer) {
        this.date = date;
        this.amount = amount;
        this.price = price;
        this.pizzaId = pizzaId;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Pizza getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Pizza pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Customer getCustomer() {
       return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", price=" + price +
                ", pizzaId=" + pizzaId +
                ", customer=" + customer +
                '}';
    }
}
