package com.example.faruqtraders.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class ApiResponseModel {

    public Products products;

    public ApiResponseModel(Products products) {
        this.products = products;
    }

    public ApiResponseModel() {
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public class Datum{
        public String id;
        public String name;
        public String slug;
        public String thumbnail;
        public String discount;
        public String price;
        public Object discounted_price;
        public boolean has_attribute;
    }

    public class Pagination{
        public int total;
        public int count;
        public int per_page;
        public int current_page;
        public int total_pages;
    }

    public class Products{
        public ArrayList<Datum> data;
        public Pagination pagination;
    }
}
