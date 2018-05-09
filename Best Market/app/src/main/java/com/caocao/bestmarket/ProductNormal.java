package com.caocao.bestmarket;

public class ProductNormal {
    String Id;
    String Name;
    int Count;
    int Price;          //giá cho sp bình thường
    String Info_Product;
    String Kind;

    public ProductNormal() {
    }

    public ProductNormal(String id, String name, int count, int price, String info_Product, String kind) {
        Id = id;
        Name = name;
        Count = count;
        Price = price;
        Info_Product = info_Product;
        Kind = kind;
    }
}
