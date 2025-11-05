public class Products {
    private int articleNumber;
    private String articleName;
    private int amount;
    private double price;
    
    public Products(int newarticleNumber, String newarticleName, int newamount, double newprice){
        this.articleNumber=newarticleNumber;
        this.articleName=newarticleName;
        this.amount=newamount;
        this.price=newprice;
    }

    public int getArticleNumber(){
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber){
        this.articleNumber=articleNumber;
    }
    

    public String getArticleName(){
        return articleName;
    }
    public void setArticleName(String articleName){
        this.articleName=articleName;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }
    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }
    @Override
    public String toString(){
        return this.articleNumber+" | "+this.articleName+" | "+this.amount+" | "+this.price;
    }

}
