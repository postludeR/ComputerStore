import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.HashSet;


public class Assignment_3 {


    public static void main(String[] args) {

        readFile();
        loadPerson();
        startFrame();

    }

    //Digital part

    private static final List<Computer> computers = new ArrayList<>();
    private static final List<Person> persons = new ArrayList<>();

    public static void readFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("computers.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                String[] result = str.split(",");
                if (str.contains("Desktop")) {
                    Desktop desktop = new Desktop(result[0], result[1], result[2], result[3], result[4], result[5], result[6], Integer.parseInt(result[7]));
                    computers.add(desktop);
                } else if (str.contains("Laptop")){
                    Laptop laptop = new Laptop(result[0], result[1], result[2], result[3], result[4], result[5], result[6], result[7], Integer.parseInt(result[8]));
                    computers.add(laptop);
                } else if (str.contains("Tablet")){
                    Tablet tablet = new Tablet(result[0], result[1], result[2], result[3], result[4], result[5], Integer.parseInt(result[6]));
                    computers.add(tablet);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public static void loadPerson() {
        Salespersons salespersons_1 = new Salespersons("p1", "p1");
        Salespersons salespersons_2 = new Salespersons("p2", "p2");
        Salespersons salespersons_3 = new Salespersons("p3", "p3");
        Manager manager_1 = new Manager("m1", "m1");
        Manager manager_2 = new Manager("m2", "m2");

        persons.add(salespersons_1);
        persons.add(salespersons_2);
        persons.add(salespersons_3);
        persons.add(manager_1);
        persons.add(manager_2);
    }


    //Graphic part
    public static JFrame startFrame;
    public static JFrame loginFrame;
    public static JFrame listFrame;

    public static void startFrame () {
        startFrame = new JFrame();
        startFrame.setTitle("Computer Products Management System");
        startFrame.setSize(580, 235);

        JButton login = new JButton();
        login.setLayout(null);

        JLabel graph = new JLabel(new ImageIcon("logo.jpg"));
        graph.setBounds(0, 0, 300, 200);

        JLabel loginText = new JLabel("Click to login");
        loginText.setFont(new Font(null, Font.BOLD, 40));
        loginText.setBounds(300, 70, 400, 50);

        login.add(graph);
        login.add(loginText);
        startFrame.add(login);

        login.addActionListener(e -> loginFrame());

        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void loginFrame() {
        loginFrame = new JFrame();
        loginFrame.setTitle("Sales Person Login");
        loginFrame.setResizable(false);
        loginFrame.setSize(300, 122);
        loginFrame.setLayout(null);

        JPanel panelN = new JPanel();
        panelN.setSize(300, 47);
        panelN.setLayout(null);
        panelN.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel usernameL = new JLabel("Username:");
        usernameL.setBounds(2, 3, 83, 20);
        JTextField usernameF = new JTextField();
        usernameF.setBounds(80, 3, 205, 20);
        JLabel passwordL = new JLabel("Password:");
        passwordL.setBounds(2, 25, 83, 20);
        JTextField passwordF = new JTextField();
        passwordF.setBounds(80, 25, 205, 20);

        JPanel panelS = new JPanel();
        panelS.setBounds(0, 47, 300, 53);

        JButton login = new JButton("Login");
        JButton cancel = new JButton("Cancel");


        panelN.add(usernameL);
        panelN.add(usernameF);
        panelN.add(passwordL);
        panelN.add(passwordF);
        panelS.add(login);
        panelS.add(cancel);

        loginFrame.add(panelN);
        loginFrame.add(panelS);

        loginFrame.setVisible(true);



        login.addActionListener(e -> {
            String permisson = "person";
            for(Person person : persons) {
                if (Objects.equals(person.getUsername(), usernameF.getText()) && Objects.equals(person.getPassword(), passwordF.getText())) {
                    startFrame.setVisible(false);
                    loginFrame.setVisible(false);
                    if (person instanceof Salespersons) {
                        permisson = "Salesperson";
                        computerList(false);
                    } else if (person instanceof Manager) {
                        permisson = "Manager";
                        computerList(true);
                    }
                    break;
                }
            }
            if(permisson.equals("person")){
                JOptionPane.showMessageDialog(login, "Incorrect username or password");
            }
        });


        cancel.addActionListener(e -> loginFrame.setVisible(false));


    }

    public static JPanel BP;
    public static JPanel CPD;
    public static Object[][] tableDate;
    public static Computer selectComputer;
    public static JTabbedPane operationTabs;

    public static void adjList (String type, String category) {
        tableDate = new Object[40][6];
        int i = 0;
        for (Computer computer : computers ) {
            if ((category.equals("ALL") || category.equals(computer.getCategory())) && (type.equals("ALL") || type.equals(computer.getType()))) {
                tableDate[i][0] = computer.getCategory();
                tableDate[i][1] = computer.getType();
                tableDate[i][2] = computer.getID();
                tableDate[i][3] = computer.getBrand();
                tableDate[i][4] = computer.getCPUfamily();
                tableDate[i][5] = computer.getPrice();
                i++;
            }
        }
    }

    public static void computerList(boolean permission) {
        listFrame = new JFrame("Computer Products Management System");
        listFrame.setSize(700,700);
        listFrame.setLayout(new BorderLayout());

        operationTabs = new JTabbedPane();
        //build BP

        JLabel categoryL = new JLabel("Computer Category");
        categoryL.setBounds(0, 3, 150, 20);
        adjList("ALL", "ALL");
        Object[] name={"Category", "Type", "ID", "Brand", "CPU Family", "Price($)"};
        JTable table = new JTable(tableDate, name){ public boolean isCellEditable(int row, int column) { return false; }};
        JScrollPane pane = new JScrollPane(table);

        pane.setLayout(new ScrollPaneLayout());
        pane.setBounds(2, 55, listFrame.getWidth() - 20, 400);

        JComboBox<String> categoryBox = new JComboBox<>();
        categoryBox.setBounds(150, 3, 200, 20);


        Set<String> set = new HashSet<>();
        for(Computer computer : computers){
            set.add(computer.getCategory());
        }

        categoryBox.addItem("ALL");

        for(String category : set){
            categoryBox.addItem(category);
        }

        JLabel typeL = new JLabel("Computer Type");
        typeL.setBounds(10, 25, 150, 20);
        JComboBox<String> typeBox = new JComboBox<>();
        typeBox.setBounds(150, 25, 200, 20);




        JPanel div = new JPanel();
        div.setLayout(null);

        div.add(categoryL);
        div.add(categoryBox);
        div.add(typeL);
        div.add(typeBox);
        div.setBounds(0, 5, listFrame.getWidth(), 50);

        BP = new JPanel();
        BP.setLayout(null);
        BP.add(div);
        BP.add(pane);


        operationTabs.add("Browse Products", BP);
        //build CPD
        CPD = new JPanel();
        CPD.setLayout(new GridLayout(11, 2));

        JLabel ID = new JLabel("Model ID:");
        JTextField IDF = new JTextField();
        JLabel category = new JLabel("Category:");

        JComboBox<String> editCategoryBox = new JComboBox<>();
        editCategoryBox.setBounds(150, 0, 200, 20);
        editCategoryBox.addItem("Desktop PC");
        editCategoryBox.addItem("Laptop");
        editCategoryBox.addItem("Tablet");

        JLabel type = new JLabel("Type:");
        JComboBox<String> editTypeBox = new JComboBox<>();
        editTypeBox.setBounds(150, 0, 200, 20);

        set = new HashSet<>();
        for(Computer computer : computers){
            set.add(computer.getCategory());
        }

        for(String editCategory : set){
            editCategoryBox.addItem(editCategory);
        }

        HashSet<Object> Tset = new HashSet<>();
        for(Computer computer : computers){

            Tset.add(computer.getType());
        }

        for(Object editType : Tset){
            editTypeBox.addItem((String) editType);
        }


        JLabel brand = new JLabel("Brand:");
        JTextField brandF = new JTextField();

        JLabel CPU = new JLabel("CPU Family:");
        JTextField CPUF = new JTextField();

        JLabel memory = new JLabel("Memory Size:");
        JTextField memoryF = new JTextField();

        JLabel capacity = new JLabel("SSD Capacity:");
        JTextField capacityF = new JTextField();

        JLabel screen = new JLabel("Screen Size:");
        JTextField screenF = new JTextField();

        JLabel price = new JLabel("Price:");
        JTextField priceF = new JTextField();
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        if (permission) {
            add.setEnabled(true);
            update.setEnabled(true);
            delete.setEnabled(true);
            clear.setEnabled(true);
            IDF.setEnabled(true);
            editCategoryBox.setEnabled(true);
            editTypeBox.setEnabled(true);
            brandF.setEnabled(true);
            CPUF.setEnabled(true);
            memoryF.setEnabled(true);
            capacityF.setEnabled(true);
            screenF.setEnabled(true);
            priceF.setEnabled(true);
        } else {
            add.setEnabled(false);
            update.setEnabled(false);
            delete.setEnabled(false);
            clear.setEnabled(false);
            IDF.setEnabled(false);
            editCategoryBox.setEnabled(false);
            editTypeBox.setEnabled(false);
            brandF.setEnabled(false);
            CPUF.setEnabled(false);
            memoryF.setEnabled(false);
            capacityF.setEnabled(false);
            screenF.setEnabled(false);
            priceF.setEnabled(false);
        }

        CPD.add(ID);
        CPD.add(IDF);
        CPD.add(category);
        CPD.add(editCategoryBox);
        CPD.add(type);
        CPD.add(editTypeBox);
        CPD.add(brand);
        CPD.add(brandF);
        CPD.add(CPU);
        CPD.add(CPUF);
        CPD.add(memory);
        CPD.add(memoryF);
        CPD.add(capacity);
        CPD.add(capacityF);
        CPD.add(screen);
        CPD.add(screenF);
        CPD.add(price);
        CPD.add(priceF);
        CPD.add(add);
        CPD.add(update);
        CPD.add(delete);
        CPD.add(clear);

        operationTabs.add("Check/Update Products Details", CPD);
        //build listFrame
        listFrame.add(operationTabs);

        JPanel bottomP = new JPanel(new BorderLayout());
        bottomP.setPreferredSize(new Dimension(startFrame.getWidth(), 200));
        JButton logout = new JButton();
        logout.setLayout(null);

        JLabel image = new JLabel(new ImageIcon("logo.jpg"));

        image.setBounds(bottomP.getWidth()/2 , bottomP.getHeight()/2, 400, 200);

        logout.add(image);
        JLabel logoutText = new JLabel("Click to Logout");

        logoutText.setFont(new Font(null, Font.BOLD, 40));
        logoutText.setBounds(bottomP.getWidth()/2 + 350, bottomP.getHeight()/2, 400, 200);

        logout.add(logoutText);
        bottomP.add(logout);

        logout.addActionListener(e -> {
            int ifLogout = JOptionPane.showConfirmDialog(listFrame, "Are you sure to logout?");
            if(ifLogout == 0){
                listFrame.setVisible(false);
                startFrame.setVisible(true);
            }
        });

        listFrame.add(bottomP, BorderLayout.SOUTH);
        listFrame.setVisible(true);
        //ActionLL


        table.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int selectRow = table.getSelectedRow();
                    for (Computer computer : computers) {
                        if(computer.getID().equals(tableDate[selectRow][2])){
                            selectComputer = computer;
                            break;
                        }
                    }

                    if(selectComputer instanceof Desktop){
                        IDF.setText(selectComputer.getID());
                        editCategoryBox.setSelectedItem(selectComputer.getCategory());
                        editTypeBox.setSelectedItem(selectComputer.getType());
                        brandF.setText(selectComputer.getBrand());
                        CPUF.setText(selectComputer.getCPUfamily());
                        memoryF.setText(((Desktop) selectComputer).getMemory());
                        capacityF.setText(((Desktop) selectComputer).getSSD());
                        screenF.setText("NULL");
                        priceF.setText(String.valueOf(selectComputer.getPrice()));
                    }else if(selectComputer instanceof Laptop){
                        IDF.setText(selectComputer.getID());
                        editCategoryBox.setSelectedItem(selectComputer.getCategory());
                        editTypeBox.setSelectedItem(selectComputer.getType());
                        brandF.setText(selectComputer.getBrand());
                        CPUF.setText(selectComputer.getCPUfamily());
                        memoryF.setText(((Laptop) selectComputer).getMemory());
                        capacityF.setText(((Laptop) selectComputer).getSSD());
                        screenF.setText(((Laptop) selectComputer).getScreenSize());
                        priceF.setText(String.valueOf(selectComputer.getPrice()));
                    }else if(selectComputer instanceof Tablet){
                        IDF.setText(selectComputer.getID());
                        editCategoryBox.setSelectedItem(selectComputer.getCategory());
                        editTypeBox.setSelectedItem(selectComputer.getType());
                        brandF.setText(selectComputer.getBrand());
                        CPUF.setText(selectComputer.getCPUfamily());
                        memoryF.setText("NULL");
                        capacityF.setText("NULL");
                        screenF.setText(((Tablet) selectComputer).getScreenSize());
                        priceF.setText(String.valueOf(selectComputer.getPrice()));
                    }
                    operationTabs.setSelectedIndex(1);
                }
            }
        });


        categoryBox.addItemListener(e -> {
            String divCate = (String) categoryBox.getSelectedItem();


            if (e.getItem().equals("Desktop PC")) {
                HashSet<String> Dset = new HashSet<>();
                for(Computer computer : computers){
                    if(computer instanceof Desktop)
                        Dset.add(computer.getType());
                }

                String[] desktopType = new String[Dset.size() + 1];
                desktopType[0] = "ALL";

                ArrayList<String> pT = new ArrayList<>(Dset);

                for (int i = 1; i <= Dset.size(); i++) {
                    desktopType[i] = pT.get(i-1);
                }

                typeBox.setModel(new DefaultComboBoxModel<>(desktopType));


            } else if (e.getItem().equals("Laptop")) {
                HashSet<String> Lset = new HashSet<>();
                for(Computer computer : computers){
                    if(computer instanceof Laptop)
                        Lset.add(computer.getType());
                }

                String[] laptopType = new String[Lset.size() + 1];
                laptopType[0] = "ALL";

                ArrayList<String> lT = new ArrayList<>(Lset);

                for (int i = 1; i <= Lset.size(); i++) {
                    laptopType[i] = lT.get(i-1);
                }

                typeBox.setModel(new DefaultComboBoxModel<>(laptopType));
            } else if (e.getItem().equals("Tablet")) {
                HashSet<String> Taset = new HashSet<>();
                for(Computer computer : computers){
                    if(computer instanceof Tablet)
                        Taset.add(computer.getType());
                }

                String[] tabletType = new String[Taset.size() + 1];
                tabletType[0] = "ALL";

                ArrayList<String> tT = new ArrayList<>(Taset);

                for (int i = 1; i <= Taset.size(); i++) {
                    tabletType[i] = tT.get(i-1);
                }

                typeBox.setModel(new DefaultComboBoxModel<String>(tabletType));
            } else {
                HashSet<String> Aset = new HashSet<>();
                for(Computer computer : computers){
                    Aset.add(computer.getType());
                }

                String[] allType = new String[Aset.size() + 1];
                allType[0] = "ALL";
                ArrayList<String> aT = new ArrayList<>(Aset);

                for (int i = 1; i <= Aset.size(); i++) {
                    allType[i] = aT.get(i-1);
                }

                typeBox.setModel(new DefaultComboBoxModel<>(allType));
            }
            String divType = (String) typeBox.getSelectedItem();

            adjList(divType, divCate);
            TableModel dataModel = new DefaultTableModel(tableDate, name);
            table.setModel(dataModel);
        });




        typeBox.addItemListener(e -> {
            String divCate = (String) categoryBox.getSelectedItem();
            String divType = (String) typeBox.getSelectedItem();
            adjList(divType, divCate);
            TableModel dataModel = new DefaultTableModel(tableDate, name);
            table.setModel(dataModel);
        });


        clear.addActionListener(e -> {
            IDF.setText(null);
            editCategoryBox.setSelectedItem(null);
            editTypeBox.setSelectedItem(null);
            brandF.setText(null);
            CPUF.setText(null);
            memoryF.setText(null);
            capacityF.setText(null);
            screenF.setText(null);
            priceF.setText(null);
        });

        delete.addActionListener(e -> {
            computers.removeIf(computer -> IDF.getText().equals(computer.getID()));
            adjList("ALL", "ALL");
            categoryBox.setSelectedItem("ALL");
            typeBox.setSelectedItem("ALL");
            TableModel dataModel = new DefaultTableModel(tableDate, name);
            table.setModel(dataModel);
            JOptionPane.showMessageDialog(listFrame, "The record for the computer is deleted successfully.");
        });

        update.addActionListener(e -> {
            String Desktop = "Desktop PC";
            String Laptop = "Laptop";
            String Tablet = "Tablet";
            boolean found = false;
            for (int i = 0 ; i < computers.size(); i++){

                if(computers.get(i).getID().equals(IDF.getText())){
                    found = true;
                    if(Desktop.equals(editCategoryBox.getSelectedItem())){
                        Desktop temp = new Desktop((String)editCategoryBox.getSelectedItem(), (String)editTypeBox.getSelectedItem(), IDF.getText(), brandF.getText(), CPUF.getText(), memoryF.getText(), capacityF.getText(), Float.parseFloat(priceF.getText()));
                        computers.set(i, temp);
                    }else if(Laptop.equals(editCategoryBox.getSelectedItem())){
                        Laptop temp = new Laptop((String)editCategoryBox.getSelectedItem(), (String)editTypeBox.getSelectedItem(), IDF.getText(), brandF.getText(), CPUF.getText(), memoryF.getText(), capacityF.getText(), screenF.getText(), Float.parseFloat(priceF.getText()));
                        computers.set(i, temp);
                    }else if(Tablet.equals(editCategoryBox.getSelectedItem())){
                        Tablet temp = new Tablet((String)editCategoryBox.getSelectedItem(), (String)editTypeBox.getSelectedItem(), IDF.getText(), brandF.getText(), CPUF.getText(), screenF.getText(), Float.parseFloat(priceF.getText()));
                        computers.set(i, temp);
                    }
                    break;
                }

            }

            adjList("ALL", "ALL");
            categoryBox.setSelectedItem("ALL");
            typeBox.setSelectedItem("ALL");
            TableModel dataModel = new DefaultTableModel(tableDate, name);
            table.setModel(dataModel);

            if(found){
                JOptionPane.showMessageDialog(listFrame, "The record for the computer is updated successfully.");
            }else{
                JOptionPane.showMessageDialog(listFrame, "You cannot change ID of computer.");
            }
        });

        add.addActionListener(e -> {
            String Desktop = "Desktop PC";
            String Laptop = "Laptop";
            String Tablet = "Tablet";
            boolean found = false;

            for (Computer computer : computers) {
                if (computer.getID().equals(IDF.getText())) {
                    found = true;
                }
            }

            if(!found) {
                if (Desktop.equals(editCategoryBox.getSelectedItem())) {
                    Desktop temp = new Desktop((String) editCategoryBox.getSelectedItem(), (String) editTypeBox.getSelectedItem(), IDF.getText(), brandF.getText(), CPUF.getText(), memoryF.getText(), capacityF.getText(), Float.parseFloat(priceF.getText()));
                    computers.add(temp);
                } else if (Laptop.equals(editCategoryBox.getSelectedItem())) {
                    Laptop temp = new Laptop((String) editCategoryBox.getSelectedItem(), (String) editTypeBox.getSelectedItem(), IDF.getText(), brandF.getText(), CPUF.getText(), memoryF.getText(), capacityF.getText(), screenF.getText(), Float.parseFloat(priceF.getText()));
                    computers.add(temp);
                } else if (Tablet.equals(editCategoryBox.getSelectedItem())) {
                    Tablet temp = new Tablet((String) editCategoryBox.getSelectedItem(), (String) editTypeBox.getSelectedItem(), IDF.getText(), brandF.getText(), CPUF.getText(), screenF.getText(), Float.parseFloat(priceF.getText()));
                    computers.add(temp);
                }
                JOptionPane.showMessageDialog(listFrame, "Add successfully.");
            }else {
                JOptionPane.showMessageDialog(listFrame, "You cannot add the same ID.");
            }

            adjList("ALL", "ALL");
            categoryBox.setSelectedItem("ALL");
            typeBox.setSelectedItem("ALL");
            TableModel dataModel = new DefaultTableModel(tableDate, name);
            table.setModel(dataModel);

        });

    }

}
