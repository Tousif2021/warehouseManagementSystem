import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class WarehouseGUI extends JFrame {
    private InventoryManager manager;
    private DefaultTableModel tableModel;
    private JTable table;

    public WarehouseGUI() {
        manager = new InventoryManager();
        manager.loadFromFile("inventory.csv");

        setTitle("WMS Light System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table setup
        String[] columns = {"Article Number", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button panel
        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Add Product");
        JButton removeBtn = new JButton("Remove Product");
        JButton searchBtn = new JButton("Search Product");
        JButton refreshBtn = new JButton("Refresh");
        panel.add(addBtn);
        panel.add(removeBtn);
        panel.add(searchBtn);
        panel.add(refreshBtn);

        add(panel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addProductDialog());
        removeBtn.addActionListener(e -> removeProductDialog());
        searchBtn.addActionListener(e -> searchProductDialog());
        refreshBtn.addActionListener(e -> refreshTable());
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // clear old rows
        for (Products p : manager.getInventory()) {
            Object[] row = {p.getArticleNumber(), p.getArticleName(), p.getAmount(), p.getPrice()};
            tableModel.addRow(row);
        }
    }

    private void addProductDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField qtyField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] fields = {
            "Article Number:", idField,
            "Name:", nameField,
            "Quantity:", qtyField,
            "Price:", priceField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            if (manager.productExists(id)) {
                JOptionPane.showMessageDialog(this, "Product already exists!");
                return;
            }
            String name = nameField.getText();
            int qty = Integer.parseInt(qtyField.getText());
            double price = Double.parseDouble(priceField.getText());
            Products p = new Products(id, name, qty, price);
            manager.addProduct(p);
            manager.saveSingleProductToFile("inventory.csv", p);
            refreshTable();
        }
    }

    private void removeProductDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Article Number to Remove:");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            if (manager.removeProduct(id)) {
                JOptionPane.showMessageDialog(this, "Product removed.");
                manager.saveToFile("inventory.csv");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "No such product found.");
            }
        }
    }

    private void searchProductDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Article Number to Search:");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Products p = manager.findByArticleNumber(id);
            if (p != null) {
                JOptionPane.showMessageDialog(this, "Found: " + p);
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WarehouseGUI gui = new WarehouseGUI();
            gui.setVisible(true);
        });
    }
}
