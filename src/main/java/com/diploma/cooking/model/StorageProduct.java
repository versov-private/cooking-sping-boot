package com.diploma.cooking.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "storage_products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"storage_id", "product_id"})})
public class StorageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity_of_product", nullable = false)
    private float quantityOfProduct;

    @Column(nullable = false)
    private Boolean active;

    public StorageProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(float quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "StorageProduct{" +
                "id=" + id +
                ", storage=" + storage +
                ", product=" + product +
                ", quantityOfProduct=" + quantityOfProduct +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageProduct that = (StorageProduct) o;
        return getId().equals(that.getId()) &&
                Float.compare(that.getQuantityOfProduct(), getQuantityOfProduct()) == 0 &&
                Objects.equals(getStorage(), that.getStorage()) &&
                Objects.equals(getProduct(), that.getProduct()) &&
                Objects.equals(getActive(), that.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStorage(), getProduct(), getQuantityOfProduct(), getActive());
    }
}
