package ru.ibs.framework;

public class Product {
    private String productName;
    private int productPrice;
    private String guarantee;
    private int guaranteePrice;
    private String article;
    private int count;

    public Product(String productName, int productPrice, String guarantee, int guaranteePrice, String article, int count) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.guarantee = guarantee;
        this.guaranteePrice = guaranteePrice;
        this.article = article;
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }


    public enum GuaranteeType {
        NONE ("Гарантия от продавца12 мес"),
        ONE_YEAR ("Доп. гарантия+ 12 мес.");
        private final String type;
        GuaranteeType(String type) {
            this.type = type;
        }
        @Override
        public String toString() {
            return type;
        }
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public int getGuaranteePrice() {
        return guaranteePrice;
    }

    public void setGuaranteePrice(int guaranteePrice) {
        this.guaranteePrice = guaranteePrice;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
