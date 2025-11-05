import java.util.*;
public class Main {
    public static void main(String[] args) {
        InventoryManager manager=new InventoryManager();
        manager.loadFromFile("inventory.csv");
        Scanner scanner=new Scanner(System.in);

        while(true){
        System.out.println();
        System.out.println("Welcome to WMS Light System");
        System.out.println();
        System.out.println("Choose option from below ");
        System.out.println("1. Add product\n2. Remove product\n3. Search product\n4. Print products\n5. Check Low Stock\n6. Exit");
        int choice;
        
        
        System.out.print("Enter your choice: ");
        choice=scanner.nextInt();
        switch (choice) {
            case 1:

            System.out.println("Add products to the Inventory");
            System.out.println("-----------------------------");
            System.out.println();
            System.out.print("Enter Artlicle number: ");
            int articleNumber=scanner.nextInt();
            scanner.nextLine();
            if (manager.productExists(articleNumber)) {
                System.out.println("⚠️ This article number is already registered!");
                break; // skip adding duplicate
            }
            System.out.print("Enter Article name: ");
            String articleName=scanner.nextLine();
            if (manager.productExists(articleName)) {
                System.out.println("⚠️ This Product is already registered!");
                break; // skip adding duplicate
            }
            System.out.print("Enter Total Quantity: ");
            int quantity=scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter price: ");
            double price=scanner.nextDouble();
            scanner.nextLine();
            System.out.println();
            Products newProduct= new Products(articleNumber, articleName, quantity, price);
            manager.addProduct(newProduct);
            manager.saveSingleProductToFile("inventory.csv", newProduct);
            System.out.println();
                
                break;
            case 2:
            scanner.nextLine();
            System.out.print("Do you want to remove a product?(Y/N): ");
            String confirm=scanner.nextLine();
            if(confirm.equalsIgnoreCase("Y")){
                System.out.print("Enter Article number: ");
                int articleNumber2=scanner.nextInt();
                if(manager.productExists(articleNumber2)){
                    manager.removeProduct(articleNumber2);
                    System.out.println("Product removed Successfully");
                    manager.saveToFile("inventory.csv");
                    break;
                }
            }else{
                System.out.println("No Article is removed");
            }
            break;
            case 3:
            System.out.print("Enter Article number of the product you are search for: ");
            int articleNumber3=scanner.nextInt();
            if(manager.productExists(articleNumber3)){
                System.out.println(manager.findByArticleNumber(articleNumber3));
            }else{
                System.out.println("The article was not found");
            }
            break;

            case 4:
                manager.displayAllProducts();
                break;
            case 5:
            System.out.print("Enter stock threshold: ");
            int threshold = scanner.nextInt();
            manager.displayLowStock(threshold);
            break;


            case 6: 
            System.out.println("Exiting...");
            manager.saveToFile("inventory.csv");
            scanner.close();
            System.exit(0);
            break;
            default:
                throw new AssertionError();
        }
    }

    }

}


