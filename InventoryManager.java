import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class InventoryManager {
    private ArrayList<Products> inventory;  // field of the class

    public InventoryManager() {
        inventory = new ArrayList<>();  // initialize the field
    }

    public void addProduct(Products product){
        inventory.add(product);
    }

    public boolean removeProduct(int articleNumber){
        for(int i=0; i<inventory.size();i++){
            Products p=inventory.get(i);

            if(p.getArticleNumber()==articleNumber){
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateAmount(int articleNumber, int amount){

        for(int i=0; i<inventory.size();i++){
            Products p=inventory.get(i);

            if(p.getArticleNumber()==articleNumber){
                p.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    public void displayAllProducts(){
        System.out.println("Products List");

        for(int i=0; i<inventory.size(); i++){
            Products p=inventory.get(i);
            System.out.println(p);
        }
    }

    public Products findByArticleNumber(int articleNumber){
        for (int i = 0; i < 10; i++) {
            Products p= inventory.get(i);
            if(p.getArticleNumber()==articleNumber){
                return p;
            }
        }
        return null;
    }

    public void saveToFile(String filename) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        for (Products p : inventory) {
            writer.println(p.getArticleNumber() + "," +
                           p.getArticleName() + "," +
                           p.getAmount() + "," +
                           p.getPrice());
        }
        System.out.println("✅ Inventory saved to " + filename);
    } catch (IOException e) {
        System.out.println("⚠️ Error saving file: " + e.getMessage());
    }

    
    }

    public void loadFromFile(String filename) {
     inventory.clear();  // start fresh
     try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                int articleNumber = Integer.parseInt(parts[0]);
                String articleName = parts[1];
                int amount = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                inventory.add(new Products(articleNumber, articleName, amount, price));
            }
        }
        System.out.println("✅ Inventory loaded from " + filename);
        } catch (IOException e) {
        System.out.println("⚠️ Error loading file: " + e.getMessage());
     }
    }

    public void saveSingleProductToFile(String filename, Products product) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(product.getArticleNumber() + "," +
                           product.getArticleName() + "," +
                           product.getAmount() + "," +
                           product.getPrice());
        } catch (IOException e) {
            System.out.println("⚠️ Error appending product: " + e.getMessage());
        }
    }

    public boolean productExists(int articleNumber) {
        for (Products p : inventory) {
            if (p.getArticleNumber() == articleNumber) {
                return true;
            }
        }
        return false;
    } 

    public boolean productExists(String articleName) {
        for (Products p : inventory) {
            if (p.getArticleName() == articleName) {
                return true;
            }
        }
        return false;
    }

    public void displayLowStock(int threshold) {
        boolean found = false;
        System.out.println("\n=== Low Stock Report (Below " + threshold + " units) ===");
        for (Products p : inventory) {
            if (p.getAmount() < threshold) {
                System.out.println(p);
                found = true;
            }
        }
    
        if (!found) {
            System.out.println("✅ All products are above the threshold.");
        }
    }

    public ArrayList<Products> getInventory() {
        return inventory;
    }
    
    
    
    


}
