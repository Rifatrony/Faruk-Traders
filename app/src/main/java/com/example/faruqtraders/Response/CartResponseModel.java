package com.example.faruqtraders.Response;

import java.util.ArrayList;

public class CartResponseModel {

    public ArrayList<Datum> data;

    public CartResponseModel(ArrayList<Datum> data) {
        this.data = data;
    }

    public CartResponseModel() {
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public class Product{
        public String id;
        public String name;
        public String slug;
        public String thumbnail;
        public String discount;
        public String price;
        public double discounted_price;
        public boolean has_attribute;

        public Product(String id, String name, String slug, String thumbnail, String discount, String price, double discounted_price, boolean has_attribute) {
            this.id = id;
            this.name = name;
            this.slug = slug;
            this.thumbnail = thumbnail;
            this.discount = discount;
            this.price = price;
            this.discounted_price = discounted_price;
            this.has_attribute = has_attribute;
        }
    }

    public class Datum{
        public int quantity;
        public String price;
        public double total;
        public Product product;
    }

}
