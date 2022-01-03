/**
 * System API, each class controls a pages UI
 */
package shoppingcart;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * System API, each class controls a pages UI
 * @author Joshua Sohan
 */
public class SystemAPI {
    private LinkedList<Customer> cList;
    private LinkedList<Seller> sList;
    private Customer ccurrent;
    private Seller scurrent;
    int flag = 0;
    int flag2 = 0;
    private JPanel cards;
    private JFrame frame;
    JPanel card1;
    JPanel card2;
    JPanel card3;
    JPanel card4;
    JPanel card5;
    
    /**
     * Class constructor
     * @throws ClassNotFoundException 
     */
    public SystemAPI() throws ClassNotFoundException{
        
        
        // creates card layout
        cards = new JPanel(new CardLayout());
        frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        card1 = new JPanel();
        card1.setLayout(new WrapLayout());
        cards.add(new JScrollPane(card1), "Seller");
        
        card3 = new JPanel();
        card3.setLayout(new WrapLayout());
        cards.add(new JScrollPane(card3), "Cart");
        
        card4 = new JPanel();
        card4.setLayout(new WrapLayout());
        
        card2 = new JPanel();
        card2.setLayout(new WrapLayout());
        cards.add(  new JScrollPane(card2), "Customer");
        
        card5 = new JPanel();
        card5.setLayout(new WrapLayout());
        cards.add( new JScrollPane(card5), "CheckOut");
        cList = new LinkedList();
        sList = new LinkedList();
        
        
        //listener to sterilaize when you exit
        frame.addWindowListener( new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                FileOutputStream file = new FileOutputStream("List.dat"); 
                ObjectOutputStream out = new ObjectOutputStream(file); 

                // Method for serialization of object 
                out.writeObject(cList);
                out.writeObject(sList);

                out.close(); 
                file.close(); 
                System.out.println("Sucessfull Sterylize");
                }
                catch(IOException ex){
                    System.out.println("Did not Sterylize");
                }
            }
        });

        
        try {
            //desteralizes and adds the objects into the linked lists
            FileInputStream file = new FileInputStream ("List.dat"); 
            ObjectInputStream in = new ObjectInputStream (file); 

            cList.addAll((LinkedList<Customer>) in.readObject());
            
            sList.addAll((LinkedList<Seller>) in.readObject());
           
           in.close(); 
           file.close(); 
        }
        catch(IOException ex){   
        }
        
        //shows the login API by default
        this.LoginAPI();
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "Login");

     }
    
   /**
    * Helps convert money string to int
    * @param amount String you want to converts
    * @param locale Location 
    * @return BigDecimal 
    * @throws ParseException 
    */
    public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));
    }
    
    /**
     * Controls the Login pages API and UI
     */
    public void LoginAPI(){
        
        
        //welcome greating
        JLabel ProductLabel = new JLabel("Welcome, please enter Username/Password");
        Font fontprod = ProductLabel.getFont();
        float sizeprod = fontprod.getSize() + 20.0f;
        ProductLabel.setFont(fontprod.deriveFont(sizeprod));
        JPanel prodLabelPan = new JPanel();
        prodLabelPan.setLayout(new WrapLayout());
        prodLabelPan.setPreferredSize(new Dimension(1022,55));
        prodLabelPan.add(ProductLabel);
        
        //shows if uesrname/password dont match
        JLabel wrong = new JLabel("Sorry your Username and Password don't match");
        wrong.setFont(fontprod.deriveFont(sizeprod-10));
        JPanel wrongPan = new JPanel();
        wrongPan.setLayout(new WrapLayout());
        wrongPan.setPreferredSize(new Dimension(1022,55));
        wrongPan.setVisible(false);
        wrongPan.add(wrong);
        
        
        //password and username field to log in
        final JPanel loginPan1 = new JPanel();
        loginPan1.setLayout(new BoxLayout(loginPan1, BoxLayout.X_AXIS));
        
        JTextArea username = new JTextArea("Username:",1, 6);
        Font font = username.getFont();
        float size = font.getSize() + 10.0f;
        username.setFont(font.deriveFont(size));
        username.setMargin(new Insets(10,5,5,0));
        username.setEditable(false);
        
        JTextArea userText = new JTextArea("",1, 10);
        userText.setEditable(true);
        userText.setFont(font.deriveFont(size));
        userText.setMargin(new Insets(10,5,5,0));
        
        loginPan1.add(username);
        loginPan1.add(userText);
        
        final JPanel loginPan = new JPanel();
        loginPan.setLayout(new BoxLayout(loginPan, BoxLayout.X_AXIS));
        
        JTextArea password = new JTextArea("Password:",1, 6);
        password.setFont(font.deriveFont(size));
        password.setMargin(new Insets(10,5,5,0));
        password.setEditable(false);
        
        JPasswordField passText = new JPasswordField(12);
        passText.setEditable(true);
        passText.setFont(font.deriveFont(size));
        passText.setMargin(new Insets(10,5,5,0));
        
        loginPan.add(password);
        loginPan.add(passText);
        
        card4.add(wrongPan);
        card4.add(prodLabelPan);
        card4.add(loginPan1);
        card4.add(loginPan);
        
        //button and action listner to check if real
        JButton sign = new JButton("Login");
        sign.setFont(font.deriveFont(size));

        //action listener to log in cutomer
        sign.addActionListener(new ActionListener(){
            private int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                //1 = costumer
                //2 = Seller
                //0 = no usernames match
                String pass = new String(passText.getPassword());
                String username = userText.getText().toLowerCase();
                
                for(int j = 0; j < cList.size(); j++){
                    if (cList.get(j).GetUsername().equals(username) && cList.get(j).GetPassword().equals(pass)){
                        i = 1;
                        ccurrent = cList.get(j);
                    }
                }
                for(int j = 0; j < sList.size(); j++){
                    if (sList.get(j).GetUsername().equals(username) && sList.get(j).GetPassword().equals(pass)){
                        i = 2;
                        scurrent = sList.get(j);
                    }
                }
                if (i == 1){
                    CustomerAPI();
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.show(cards, "Customer");
                }
                if (i == 2){
                    SellerAPI();
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.show(cards, "Seller");
                }
                if (i == 0){
                    wrongPan.setVisible(true);
                }
            }
            
        });
        
        card4.add(sign);

        //create a new user textAreaas
        JLabel newUserLabel = new JLabel("Welcome, Create new Customer/Seller account here");
        newUserLabel.setFont(fontprod.deriveFont(sizeprod));
        JPanel newUserPan = new JPanel();
        newUserPan.setLayout(new BoxLayout(newUserPan, BoxLayout.X_AXIS));
        newUserPan.setPreferredSize(new Dimension(1022,100));
        newUserPan.setMinimumSize(new Dimension(1022,100));
        newUserPan.add(newUserLabel);
        
        final JPanel createUser1 = new JPanel();
        createUser1.setLayout(new BoxLayout(createUser1, BoxLayout.X_AXIS));
        
        JTextArea username2 = new JTextArea("Username:",1, 6);
        username2.setFont(font.deriveFont(size));
        username2.setMargin(new Insets(10,5,5,0));
        username2.setEditable(false);
        
        JTextArea userText2 = new JTextArea("",1, 10);
        userText2.setEditable(true);
        userText2.setFont(font.deriveFont(size));
        userText2.setMargin(new Insets(10,5,5,0));
        
        createUser1.add(username2);
        createUser1.add(userText2);
        
        final JPanel createUser = new JPanel();
        createUser.setLayout(new BoxLayout(createUser, BoxLayout.X_AXIS));
        
        JTextArea password2 = new JTextArea("Password:",1, 6);
        password2.setFont(font.deriveFont(size));
        password2.setMargin(new Insets(10,5,5,0));
        password2.setEditable(false);
        
        JPasswordField passText2 = new JPasswordField(12);
        passText2 .setEditable(true);
        passText2 .setFont(font.deriveFont(size));
        passText2 .setMargin(new Insets(10,5,5,0));
        
        createUser.add(password2);
        createUser.add(passText2);
        
        //check what kind of user to create
        String[] select1 = {"Customer"," Seller"};
        
        JComboBox select = new JComboBox(select1);
        
        
        //button and action istner to create new user and log them in
        JButton create = new JButton("Create");
        create.setFont(font.deriveFont(size));
        create.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if boxes are empty
                if (userText2.getText() != "" && passText2.getPassword().length != 0){
                    
                    String name = userText2.getText().toLowerCase();
                    String pass = new String(passText2.getPassword());
                    
                    //see if customer or seller selected
                    if (select.getSelectedItem().equals(select1[0])){
                        Customer temp = new Customer();
                        temp.SetUsername(name);
                        temp.SetPassword(pass);
                        cList.add(temp);
                        ccurrent = temp;
                        
                        CustomerAPI();
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        cl.show(cards, "Customer");
                    }
                    else {
                        
                        Seller temp = new Seller();
                        temp.SetUsername(name);
                        temp.SetPassword(pass);
                        sList.add(temp);
                        scurrent = temp;
                        SellerAPI();
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        cl.show(cards, "Seller");
                    }
   
                }
            
            }
            
        });
        //add to cards
        card4.add(newUserLabel);
        card4.add(createUser1);
        card4.add(createUser);
        card4.add(select);
        card4.add(create);

        cards.add(card4, "Login");
        frame.add(cards);
        frame.pack();
	frame.setVisible(true);
        
    }
    
    
    /**
     * Controls the Customers pages API and UI
     */
    public void CustomerAPI(){

        //panel where items are laoded into
        final JPanel prodPan = new JPanel();
        prodPan.setLayout(new WrapLayout());

        //greating + name of cutomer
        JLabel ProductLabel = new JLabel("Welcome, " + ccurrent.GetUsername());
        Font fontprod = ProductLabel.getFont();
        float sizeprod = fontprod.getSize() + 20.0f;
        ProductLabel.setFont(fontprod.deriveFont(sizeprod));
        JPanel prodLabelPan = new JPanel();
        prodLabelPan.setLayout(new BorderLayout());
        prodLabelPan.setPreferredSize(new Dimension(1022,55));
        prodLabelPan.add(ProductLabel, BorderLayout.WEST);
        
        
        //button that takes you to schopping cart
        JButton shopButton = new JButton("Shopping Cart");
        shopButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                card3.removeAll();
                ShopCart();
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Cart");
            }
            
        });

        prodLabelPan.add(shopButton, BorderLayout.EAST);
        card2.add( prodLabelPan);
        
        //for loop that goes through all seller accounts
        for (int j = 0; j < sList.size(); j++)
        {
            Seller current = sList.get(j);
            
            /// gooes through all current sellers items and lists them
            for (int i = 0; i < current.ProdList().size(); i ++){

                //current product 
                final Product prod = current.ProdList().getItem(i);

                //temp panel to laod all items onto
                final JPanel temp = new JPanel();
                temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));

                //name text area
                JTextArea name = new JTextArea(prod.Getname(),4, 15);
                Font font = name.getFont();
                float size = font.getSize() + 3.0f;
                name.setFont(font.deriveFont(size + 6.0f));
                name.setMargin(new Insets(2,10,0,10));
                name.setLineWrap(true);
                name.setWrapStyleWord(true);

                //descreiption 
                JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
                description.setFont(font.deriveFont(size));
                description.setLineWrap(true);
                description.setWrapStyleWord(true);
                description.setMargin(new Insets(0,10,0,10));
                JScrollPane d = new JScrollPane(description);
                d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //inventory
                JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 5);
                inventory.setFont(font.deriveFont(size));
                inventory.setMargin(new Insets(20,30,0,0));

                //price
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
                price.setFont(font.deriveFont(size));
                price.setMargin(new Insets(20,30,0,0));

                
                //inventory combobox
                String[] comboxs = new String[prod.GetInventory()+1];
                
                for(int k = 0; k < prod.GetInventory()+1; k++){
                    comboxs[k] = Integer.toString(k);
                }
                
                JComboBox inbox = new JComboBox(comboxs);
                inbox.setSelectedIndex(0);
                inbox.setPreferredSize(new Dimension(50,10));
                
                //sets fields to false
                name.setEditable(false);
                description.setEditable(false);
                inventory.setEditable(false);
                price.setEditable(false);

                //buton to add item to shopping cart
                JButton addCart = new JButton("Add To Cart");
                addCart.setVisible(true);

                //action listener to add items to shoppping cart
                ActionListener add = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int num = Integer.parseInt((String)inbox.getSelectedItem());
                        
                        if (num > 0){
                            //makes copy of prodcut and changes the inventory
                            Product newp = prod.Getcopy();
                            newp.SetInventory(num);
                            ccurrent.Add(newp);
                            
                            //sets the items inventory acordingly
                            prod.SetInventory(prod.GetInventory() - num);
                            
                            //refersh customer 
                            card2.removeAll();
                            CustomerAPI();
                            card2.repaint();
                            card2.revalidate();
                            
                            //refesh shopping cart
                            card3.removeAll();
                            ShopCart();
                            card3.repaint();
                            card3.revalidate();
                        }
                    }
                        
                };///////////////======================================================== 
                //add Listeners
                addCart.addActionListener(add);

                //add componenets to the row
                temp.add(name);
                temp.add(d);
                temp.add(inventory);
                temp.add(price);
                temp.add(inbox);
                temp.add(addCart);

                //ad row to the product panel
                prodPan.add(temp);
            }
            
            
            
            /////does the same thing as the Product loop but for Discountedproduct type items+++++++++++++++++++++++++++++
            for (int i = 0; i < current.DisList().size(); i ++){

                //current product 
                final DiscountProduct prod = current.DisList().getItem(i);

                final JPanel temp = new JPanel();
                temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));

                //name text area
                JTextArea name = new JTextArea(prod.Getname(),4, 15);
                Font font = name.getFont();
                float size = font.getSize() + 3.0f;
                name.setFont(font.deriveFont(size + 6.0f));
                name.setMargin(new Insets(2,10,0,10));
                name.setLineWrap(true);
                name.setWrapStyleWord(true);

                //descreiption 
                JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
                description.setFont(font.deriveFont(size));
                description.setLineWrap(true);
                description.setWrapStyleWord(true);
                description.setMargin(new Insets(0,10,0,10));
                JScrollPane d = new JScrollPane(description);
                d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //inventory
                JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 5);
                inventory.setFont(font.deriveFont(size));
                inventory.setMargin(new Insets(20,30,0,0));

                //price
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
                price.setFont(font.deriveFont(size));
                price.setMargin(new Insets(20,30,0,0));

                String[] comboxs = new String[prod.GetInventory()+1];
                
                for(int k = 0; k < prod.GetInventory()+1; k++){
                    comboxs[k] = Integer.toString(k);
                }
                
                JComboBox inbox = new JComboBox(comboxs);
                inbox.setSelectedIndex(0);
                inbox.setPreferredSize(new Dimension(50,10));
                
               
                name.setEditable(false);
                description.setEditable(false);
                inventory.setEditable(false);
                price.setEditable(false);

                JButton addCart = new JButton("Add To Cart");
                addCart.setVisible(true);

                
                ActionListener add = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int num = Integer.parseInt((String)inbox.getSelectedItem());
                        
                        if (num > 0){
                            
                            DiscountProduct newp = prod.Getcopy();
                            newp.SetInventory(num);
                            ccurrent.Add(newp);
                            
                            prod.SetInventory(prod.GetInventory() - num);
                            
                            card2.removeAll();
                            CustomerAPI();
                            card2.repaint();
                            card2.revalidate();
                            
                            
                            card3.removeAll();
                            ShopCart();
                            card3.repaint();
                            card3.revalidate();
                            
                        }
                    } 
                };
                //add Listeners
                addCart.addActionListener(add);

                //add componenets to the row
                temp.add(name);
                temp.add(d);
                temp.add(inventory);
                temp.add(price);
                temp.add(inbox);
                temp.add(addCart);

                //ad row to the product panel
                prodPan.add(temp);
            }///////////////+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            
            
            
            ///////Does the same thing but fo Bundle Type products*************************************
            for (int i = 0; i < current.BunList().size(); i ++){

                //current product 
                final BundleProduct prod = current.BunList().getItem(i);

                final JPanel temp = new JPanel();
                temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));

                //name text area
                JTextArea name = new JTextArea(prod.Getname(),4, 15);
                Font font = name.getFont();
                float size = font.getSize() + 3.0f;
                name.setFont(font.deriveFont(size + 6.0f));
                name.setMargin(new Insets(2,10,0,10));
                name.setLineWrap(true);
                name.setWrapStyleWord(true);

                //descreiption 
                JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
                description.setFont(font.deriveFont(size));
                description.setLineWrap(true);
                description.setWrapStyleWord(true);
                description.setMargin(new Insets(0,10,0,10));
                JScrollPane d = new JScrollPane(description);
                d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //inventory
                JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 5);
                inventory.setFont(font.deriveFont(size));
                inventory.setMargin(new Insets(20,30,0,0));

                //price
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
                price.setFont(font.deriveFont(size));
                price.setMargin(new Insets(20,30,0,0));

                String[] comboxs = new String[prod.GetInventory()+1];
                
                for(int k = 0; k < prod.GetInventory()+1; k++){
                    comboxs[k] = Integer.toString(k);
                }
                
                JComboBox inbox = new JComboBox(comboxs);
                inbox.setSelectedIndex(0);
                inbox.setPreferredSize(new Dimension(50,10));
                
                
                name.setEditable(false);
                description.setEditable(false);
                inventory.setEditable(false);
                price.setEditable(false);

                JButton addCart = new JButton("Add To Cart");
                addCart.setVisible(true);

               
                ActionListener add = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int num = Integer.parseInt((String)inbox.getSelectedItem());
                        
                        if (num > 0){
                            
                            BundleProduct newp = prod.Getcopy();
                            newp.SetInventory(num);
                            ccurrent.Add(newp);
                            
                            prod.SetInventory(prod.GetInventory() - num);
                            
                            card2.removeAll();
                            CustomerAPI();
                            card2.repaint();
                            card2.revalidate();
                            
                            
                            card3.removeAll();
                            ShopCart();
                            card3.repaint();
                            card3.revalidate();
                        }
                    }
                        
                };
                //add Listeners
                addCart.addActionListener(add);

                //add componenets to the row
                temp.add(name);
                temp.add(d);
                temp.add(inventory);
                temp.add(price);
                temp.add(inbox);
                temp.add(addCart);

                //ad row to the product panel
                prodPan.add(temp);
            }///////////////*********************************************************
            
        }
        

        //makes it prodpan scrolable and adds it to card
        JScrollPane prodScroll = new JScrollPane(prodPan);
        prodScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        prodScroll.setPreferredSize(new Dimension(1022,700));
        card2.add(prodScroll);
        frame.pack();
    }
    
    
    /**
     * Controls the Shopping carts pages API and UI
     */
    public void ShopCart(){
        
        
        //flag to hide Succefull Purchase label
        flag2 = 0;
        //panel that holds all products in cart
        JPanel shop = new JPanel();
        shop.setLayout(new WrapLayout());
        
        //greeting
        JLabel ProductLabel = new JLabel("Shopping Cart:" );
        Font fontprod = ProductLabel.getFont();
        float sizeprod = fontprod.getSize() + 20.0f;
        ProductLabel.setFont(fontprod.deriveFont(sizeprod));
        JPanel prodLabelPan = new JPanel();
        prodLabelPan.setLayout(new BorderLayout());
        prodLabelPan.setPreferredSize(new Dimension(1022,55));
        prodLabelPan.add(ProductLabel, BorderLayout.WEST);
        
        
        //go back to shopping cart and refresh customerAPI
        JButton main = new JButton("Go Back Shopping");
        main.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                card2.removeAll();
                CustomerAPI();
                card2.repaint();
                card2.revalidate();
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Customer");
            }
            
        });
        prodLabelPan.add(main, BorderLayout.EAST);
        
        
        //takes cutomer to checkout
        JButton next = new JButton("Check out");
        next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                card5.removeAll();
                Checkout();
                card5.repaint();
                card5.revalidate();
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "CheckOut");
            }
            
        });
        prodLabelPan.add(next, BorderLayout.CENTER);
        

        card3.add(prodLabelPan);
        
        
        //shopping cart pordcuts in a list
        for(int i = 0; i < ccurrent.ProdList().size(); i++){
            Product prod = ccurrent.ProdList().getItem(i);
            
            final JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));

            //Name
            JTextArea name = new JTextArea(prod.Getname(),4, 15);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,10,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);

            //description 
            JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(0,10,0,10));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //inventory
            JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 5);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(20,30,0,0));

            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(20,30,0,0));

            //combbox to display how many items you want
            String[] comboxs = new String[prod.GetInventory()+1];

            for(int k = 0; k < prod.GetInventory()+1; k++){
                comboxs[k] = Integer.toString(k);
            }

            JComboBox inbox = new JComboBox(comboxs);
            inbox.setSelectedIndex(prod.GetInventory());
            inbox.setPreferredSize(new Dimension(50,10));
            
            
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);

            //updates item button
            JButton update = new JButton("Update Item");
            update.setVisible(true);

            //update shpoing carts and adjusts inventory
            ActionListener updateCart = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = Integer.parseInt((String)inbox.getSelectedItem());

                    if (num > 0){
                        //fix item inventory
                        int add = prod.GetInventory() - num;
                        prod.SetInventory(num);
                        
                        //fixes inventory for the Seller
                        for(int i = 0 ; i< sList.size(); i++){
                            Seller current = sList.get(i);
                            for (int j = 0; j < current.ProdList().size(); j++){
                                Product t = current.ProdList().getItem(j);
                                
                                if (prod.Getname().equals(t.Getname()) && prod.GetDescription().equals(t.GetDescription()) && prod.GetPrice() == t.GetPrice()){
                                    t.SetInventory(t.GetInventory() + add);
                                    break;
                                }

                            }
                        }
                        

                        card3.removeAll();
                        ShopCart();
                        card3.repaint();
                        card3.revalidate();

                    }
                    else{
                        ccurrent.Remove(prod);
                        shop.remove(temp);
                        shop.revalidate();
                        shop.repaint();
                    }
                }
            };
            //add Listeners
            update.addActionListener(updateCart);

            //add componenets to the row
            temp.add(name);
            temp.add(d);
            temp.add(inventory);
            temp.add(price);
            temp.add(inbox);
            temp.add(update);
            shop.add(temp);
        }
        
        
        //same thig as other for loop bu thtis adds discount items
        for(int i = 0; i < ccurrent.DisList().size(); i++){
            DiscountProduct prod = ccurrent.DisList().getItem(i);
            
            final JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));

            //Name
            JTextArea name = new JTextArea(prod.Getname(),4, 15);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,10,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);

            //description 
            JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(0,10,0,10));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //inventory
            JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 5);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(20,30,0,0));

            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(20,30,0,0));

            String[] comboxs = new String[prod.GetInventory()+1];

            for(int k = 0; k < prod.GetInventory()+1; k++){
                comboxs[k] = Integer.toString(k);
            }

            //
            JComboBox inbox = new JComboBox(comboxs);
            inbox.setSelectedIndex(prod.GetInventory());
            inbox.setPreferredSize(new Dimension(50,10));
            
            
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);

            JButton update = new JButton("Update Item");
            update.setVisible(true);

            
            ActionListener updateCart = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = Integer.parseInt((String)inbox.getSelectedItem());

                    if (num > 0){
                        int add = prod.GetInventory() - num;
                        prod.SetInventory(num);
                        
                        for(int i = 0 ; i< sList.size(); i++){
                            Seller current = sList.get(i);
                            for (int j = 0; j < current.DisList().size(); j++){
                                DiscountProduct t = current.DisList().getItem(j);
                                
                                if (prod.Getname().equals(t.Getname()) && prod.GetDescription().equals(t.GetDescription()) && prod.GetPrice() == t.GetPrice()){
                                    t.SetInventory(t.GetInventory() + add);

                                    break;
                                }

                            }
                        }
                        
                        card3.removeAll();
                        ShopCart();
                        card3.repaint();
                        card3.revalidate();
                    }
                    else{
                        ccurrent.Remove(prod);
                        shop.remove(temp);
                        shop.revalidate();
                        shop.repaint();
                    }
                }
            };
            //add Listeners
            update.addActionListener(updateCart);

            //add componenets to the row
            temp.add(name);
            temp.add(d);
            temp.add(inventory);
            temp.add(price);
            temp.add(inbox);
            temp.add(update);
            shop.add(temp);
        }
        
        ///Smae  thing as other loops but this adds Bundled Items
        for(int i = 0; i < ccurrent.BunList().size(); i++){
            BundleProduct prod = ccurrent.BunList().getItem(i);
            
            final JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));

            //Name
            JTextArea name = new JTextArea(prod.Getname(),4, 15);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,10,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);

            //description 
            JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(0,10,0,10));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //inventory
            JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 5);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(20,30,0,0));

            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(20,30,0,0));

            String[] comboxs = new String[prod.GetInventory()+1];

            for(int k = 0; k < prod.GetInventory()+1; k++){
                comboxs[k] = Integer.toString(k);
            }

            JComboBox inbox = new JComboBox(comboxs);
            inbox.setSelectedIndex(prod.GetInventory());
            inbox.setPreferredSize(new Dimension(50,10));
            
           
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);

            JButton update = new JButton("Update Item");
            update.setVisible(true);

            ActionListener updateCart = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = Integer.parseInt((String)inbox.getSelectedItem());

                    if (num > 0){
                        int add = prod.GetInventory() - num;
                        prod.SetInventory(num);
                        
                        for(int i = 0 ; i< sList.size(); i++){
                            Seller current = sList.get(i);
                            for (int j = 0; j < current.BunList().size(); j++){
                                BundleProduct t = current.BunList().getItem(j);
                                
                                if (prod.Getname().equals(t.Getname()) && prod.GetDescription().equals(t.GetDescription()) && prod.GetPrice() == t.GetPrice()){
                                    t.SetInventory(t.GetInventory() + add);
                                    break;
                                }

                            }
                        }

                        card3.removeAll();
                        ShopCart();
                        card3.repaint();
                        card3.revalidate();
                    }
                    else{
                        ccurrent.Remove(prod);
                        shop.remove(temp);
                        shop.revalidate();
                        shop.repaint();
                    }
                }
            };
            //add Listeners
            update.addActionListener(updateCart);

            //add componenets to the row
            temp.add(name);
            temp.add(d);
            temp.add(inventory);
            temp.add(price);
            temp.add(inbox);
            temp.add(update);
            shop.add(temp);
        }
        
        
        JScrollPane prodScroll = new JScrollPane(shop);
        prodScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        prodScroll.setPreferredSize(new Dimension(1022,700));
        
        card3.add(prodScroll);
        frame.pack();
        
    }
    
    
    
    
    /**
     * Controls the Sellers main page API and UI
     */
    public void SellerAPI(){
        
        //main panel for products
        final JPanel prodPan = new JPanel();
        prodPan.setLayout(new WrapLayout());
        

        //main panel for discount and Bundle prodcuts
        final JPanel disPan = new JPanel();
        disPan.setLayout( new WrapLayout());
        
        //greeting
        JLabel ProductLabel = new JLabel("View and Edit Products");
        Font fontprod = ProductLabel.getFont();
        float sizeprod = fontprod.getSize() + 20.0f;
        ProductLabel.setFont(fontprod.deriveFont(sizeprod));
        
        JPanel prodLabelPan = new JPanel();
        prodLabelPan.setLayout(new BorderLayout());
        prodLabelPan.setPreferredSize(new Dimension(1022,55));
        
        
        prodLabelPan.add(ProductLabel, BorderLayout.SOUTH);
        card1.add(prodLabelPan);
        
        
        //linked list to hold items adn jpanles they are in
        final LinkedList<Product> prodtemp = new LinkedList();
        final LinkedList<JPanel> prodPanelLst = new LinkedList();
        
        //buttons to add discount and bundle, gets added to the card later on
        JButton addDis = new JButton("Discount Selected item");
        JButton addBun = new JButton("Bundle Selected Items");
        addBun.setVisible(false);
        
        //header panel to show what the Product items are organizes ass
        JPanel head = new JPanel();
        head.setLayout(new BoxLayout(head, BoxLayout.X_AXIS));
        head.setPreferredSize(new Dimension(1022, 50));
        
        //ads textAreas to Panel
        for (int i = 0; i < 1; i++){
            JTextArea name = new JTextArea("Name",2, 10);
            Font font = name.getFont();
            float size = font.getSize() + 10.0f;
            name.setFont(font.deriveFont(size));
            name.setMargin(new Insets(10,10,0,0));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);

            //descreiption 
            JTextArea description = new JTextArea("Description",2, 5);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(10,10,0,0));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //inventory
            JTextArea inventory = new JTextArea("Inventory",2, 10);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(10,0,0,0));

            JTextArea price = new JTextArea("Price",2, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(10,0,0,0));

            //save and edit buttons
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);
            
            head.add(name);
            head.add(description);
            head.add(inventory);
            head.add(price);
        }
        
        card1.add(head);
        
        
        //get all items in current Sellers product list
        for (int i = 0 ; i < scurrent.ProdList().size(); i++){
            
            final Product prod = scurrent.ProdList().getItem(i);

            final JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
            

            //name text area
            JTextArea name = new JTextArea(prod.Getname(),4, 15);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,10,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);

            //descreiption 
            JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(0,10,0,10));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //inventory
            JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 10);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(20,30,0,0));

            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(20,30,0,0));

            //save and edit buttons
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);

            // edit/save 
            JButton edit = new JButton(" Edit ");
            JButton save = new JButton("Save");
            save.setVisible(false);
            JCheckBox box = new JCheckBox();

            //set text boxes to editbale
            ActionListener editable = new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                        name.setEditable(true);
                        description.setEditable(true);
                        inventory.setEditable(true);
                        price.setEditable(true);
                        edit.setVisible(false);
                        save.setVisible(true);
                }
            };

            //sets textareas to uneditable and updates the UI
            ActionListener savable;
            savable = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int flag = 0;
                    int inv = 0;
                    double value = 0;
                    try{
                        value = parse(price.getText(), Locale.US).doubleValue();
                        inv =parse(inventory.getText(), Locale.US).intValue();
                    }
                    catch(ParseException j){
                        flag = 1;
                    }
                    //if exception is not triggered then updat product
                    if (flag == 0){
                        //update inventory
                        prod.SetPrice(value);
                        price.replaceRange(formatter.format(prod.GetPrice()), 0, price.getText().length());

                        //update Price
                        prod.SetInventory(inv);
                        inventory.replaceRange(Integer.toString(prod.GetInventory()), 0, inventory.getText().length());

                        //update name
                        prod.SetName(name.getText());

                        //update description
                        prod.SetDescription(description.getText());

                        //update 
                        //refresh screem
                        card1.revalidate();
                        name.setEditable(false);
                        description.setEditable(false);
                        inventory.setEditable(false);
                        price.setEditable(false);
                        edit.setVisible(true);
                        save.setVisible(false);
                        card1.removeAll();
                        SellerAPI();
                        card1.repaint();
                        
                    }
                }
            };

            
            //ceck box action listener
            ItemListener morf = new ItemListener(){
                int flag =0;
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //if stat is checked add item to temprorary linked list
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        prodtemp.add(prod);
                        prodPanelLst.add(temp); 
                        flag = 1;
                    }
                    //if unchecked remove items from temp linked list
                    else if (flag == 1){
                        
                        prodtemp.remove(prod);
                        prodPanelLst.remove(temp);
                        flag = 0;
                    }
                    
                    //of more than one itme is checked it replaces the Discount
                    //button with the bundle button
                    if (prodtemp.size()>1){
                        addBun.setVisible(true);
                        addDis.setVisible(false);
                    }
                    //does the opposite
                    else{
                        addBun.setVisible(false);
                        addDis.setVisible(true);
                    }
                }
            };

            //add Listeners
            edit.addActionListener(editable);
            save.addActionListener(savable);
            box.addItemListener(morf);

            //add componenets to the row
            temp.add(name);
            temp.add(d);
            temp.add(inventory);
            temp.add(price);
            temp.add(box);
            temp.add(edit);
            temp.add(save);

            //ad row to the product panel
            prodPan.add(temp);
        }
        

        //set up too add new product to the sellers products and the API///////////////////////////////////////////
        final JPanel newtemp = new JPanel();
        newtemp.setLayout(new BoxLayout(newtemp, BoxLayout.X_AXIS));
        
        for (int i = 0; i< 1;i++){
            //name text area
            JTextArea name = new JTextArea("",2, 16);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,5,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);

            //descreiption 
            JTextArea description = new JTextArea("",2, 21);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(2,10,0,10));
            JScrollPane d = new JScrollPane(description);

            //inventory
            JTextArea inventory = new JTextArea("",2, 10);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(2,30,0,0));

            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea("",2, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(2,30,0,0));

            //save and edit buttons
            name.setEditable(true);
            description.setEditable(true);
            inventory.setEditable(true);
            price.setEditable(true);

            // button to add new item to products list
            JButton save = new JButton("Add Product");
            
            //saves product listener adds it to the API and the PDList****
            ActionListener savable;
            savable = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int flag = 0;
                    int inv = 0;
                    double value = 0;
                    try{
                        value = parse(price.getText(), Locale.US).doubleValue();
                        inv =parse(inventory.getText(), Locale.US).intValue();
                    }
                    catch(ParseException j){
                        flag = 1;
                    }
                    //if exception is not triggered then updat product
                    if (flag == 0){
                        //update inventory
                        Product newP = new Product();

                        newP.SetPrice(value);
                        price.setText(null);

                        //update Price
                        newP.SetInventory(inv);
                        inventory.setText(null);

                        //update name
                        newP.SetName(name.getText());
                        name.setText(null);

                        //update description
                        newP.SetDescription(description.getText());
                        description.setText(null);
                        scurrent.ProdList().add(newP);
                        
                        //refresh screem
                        card1.removeAll();
                        SellerAPI();
                        card1.repaint();
                        card1.revalidate();
                    }
                }
            };
            save.addActionListener(savable);
            newtemp.add(name);
            newtemp.add(d);
            newtemp.add(inventory);
            newtemp.add(price);
            newtemp.add(save);
        }//End save new API//////////////////////////////////////////////////////
        

        //set up to add new Discount/bundle item
        JTextArea setprice = new JTextArea("Set Price:",1, 5);
        Font fontt = setprice.getFont();
        float sizet = fontt.getSize() + 5.0f;
        setprice.setEditable(false);
        setprice.setFont(fontt.deriveFont(sizet));
        setprice.setMargin(new Insets(2,5,2,0));
        
        JTextArea numprice = new JTextArea("",1, 55);
        numprice.setFont(fontt.deriveFont(sizet));
        numprice.setMargin(new Insets(2,5,2,5));
        
        //deletes products selected///////////////////////////////////////////////////////////////
        JPanel prodButtons = new JPanel();
        prodButtons.setPreferredSize(new Dimension(1000,27));
        prodButtons.setLayout(new BoxLayout(prodButtons, BoxLayout.X_AXIS));
        JButton delProd = new JButton("Delete Selected Item(s)");
        delProd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                for(int i = 0; i < prodtemp.size(); i++){
                    scurrent.ProdList().remove(prodtemp.get(i));
                }
                
                prodtemp.clear();
                //refresh screem
                card1.removeAll();
                SellerAPI();
                card1.repaint();
                card1.revalidate();
            }
        });///////////////////////////////////////////////////////////////////////
        
        ///add action listeners to discount and bundle buttons>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        addDis.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prodtemp.size() == 1 && numprice.getText() != ""){
                    int f = 0;
                    double value = 0;
                    try{
                        value = parse(numprice.getText(), Locale.US).doubleValue();
                    }
                    catch(ParseException j){
                        f = 1;
                    }
                    if (f != 1 && value > 0){
                        DiscountProduct dis = new DiscountProduct(prodtemp.get(0), value);
                        
                        scurrent.Add(dis);
                        
                        card1.removeAll();
                        SellerAPI();
                        card1.repaint();
                        card1.revalidate();
                    }
                    
                }
            }
            
        });
        //////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        //action listner to add Bundled product
        addBun.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prodtemp.size() > 1){
                    int f = 0;
                    double value = 0;
                    try{
                        value = parse(numprice.getText(), Locale.US).doubleValue();
                    }
                    catch(ParseException j){
                        
                    }
                    if (f != 1){
                        BundleProduct bun = new BundleProduct();
                        for (int i = 0; i < prodtemp.size(); i++){
                            bun.AddProd(prodtemp.get(i));
                            System.out.println(prodtemp.size());
                            System.out.println(i);
                            System.out.println(prodtemp.get(i).Getname());
                        }
                        if (value >0){
                            bun.SetPrice(value);
                        }
                        
                        scurrent.Add(bun);
                        
                        //refresh screen
                        card1.removeAll();
                        SellerAPI();
                        card1.repaint();
                        card1.revalidate();
                    }
                    
                }
            }
            
        });
        

        //add panels to card********************************************************
        JScrollPane prodScroll = new JScrollPane(prodPan);
        prodScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        prodScroll.setPreferredSize(new Dimension(1022,300));
        card1.add(prodScroll);
        card1.add(newtemp);
        prodButtons.add(delProd);
        prodButtons.add(addDis);
        prodButtons.add(addBun);
        prodButtons.add(setprice);
        prodButtons.add(numprice);
        card1.add(prodButtons);
        //////////*********************************************************************
        
        //label for the budle product
        JLabel distLabel = new JLabel("View and Edit Discount/Bundle Products");
        distLabel.setFont(fontprod.deriveFont(sizeprod)); 
        JPanel disLabelPan = new JPanel();
        disLabelPan.setPreferredSize(new Dimension(1022,80));
        disLabelPan.setLayout(new BorderLayout());
        disLabelPan.add(distLabel, BorderLayout.SOUTH);
        card1.add(disLabelPan);
        
    //API that shows Bundle/discount products___________________________________________________
        for (int i = 0; i < scurrent.DisList().size(); i ++){

            //current product 
            final DiscountProduct prod = scurrent.DisList().getItem(i);
            
            final JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
            
            //name text area
            JTextArea name = new JTextArea(prod.Getname(),4, 15);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,10,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);
            
            //descreiption 
            JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(0,10,0,10));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            
            //inventory
            JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 10);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(20,30,0,0));
            
            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(20,30,0,0));
            
            //save and edit buttons
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);
             
            // deletes procuts
            JButton delete = new JButton("Delete Product");

            //acction listener to delete button
            ActionListener dele;
            dele = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    scurrent.DisList().remove(prod);
                    //refresh screem
                    card1.removeAll();
                    SellerAPI();
                    card1.repaint();
                    card1.revalidate();
                }
            };
            delete.addActionListener(dele);
            
            //add componenets to the row
            temp.add(name);
            temp.add(d);
            temp.add(inventory);
            temp.add(price);
            temp.add(delete);
            
                        
            //ad row to the product panel
            disPan.add(temp);
        }
        
        
        //API that shows Bundle products___________________________________________________
        for (int i = 0; i < scurrent.BunList().size(); i ++){

            //current product 
            final BundleProduct prod = scurrent.BunList().getItem(i);
            
            final JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
            
            //name text area
            JTextArea name = new JTextArea(prod.Getname(),4, 15);
            Font font = name.getFont();
            float size = font.getSize() + 3.0f;
            name.setFont(font.deriveFont(size + 6.0f));
            name.setMargin(new Insets(2,10,0,10));
            name.setLineWrap(true);
            name.setWrapStyleWord(true);
            
            //descreiption 
            JTextArea description = new JTextArea(prod.GetDescription(),4, 20);
            description.setFont(font.deriveFont(size));
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setMargin(new Insets(0,10,0,10));
            JScrollPane d = new JScrollPane(description);
            d.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            
            //inventory
            JTextArea inventory = new JTextArea(Integer.toString(prod.GetInventory()),4, 10);
            inventory.setFont(font.deriveFont(size));
            inventory.setMargin(new Insets(20,30,0,0));
            
            //price
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            JTextArea price = new JTextArea(formatter.format(prod.GetPrice()),4, 10);
            price.setFont(font.deriveFont(size));
            price.setMargin(new Insets(20,30,0,0));
            
            //save and edit buttons
            name.setEditable(false);
            description.setEditable(false);
            inventory.setEditable(false);
            price.setEditable(false);
             
            // delets product 
            JButton delete = new JButton("Delete Product");

            //delete action listener to delet BundleProdcut
            ActionListener savable;
            savable = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    scurrent.BunList().remove(prod);
                    //refresh screem
                    card1.removeAll();
                    SellerAPI();
                    card1.repaint();
                    card1.revalidate();
                }
            };
            delete.addActionListener(savable);
            
            //add componenets to the row
            temp.add(name);
            temp.add(d);
            temp.add(inventory);
            temp.add(price);
            temp.add(delete);
            //ad row to the product panel
            disPan.add(temp);
        }
        
        JScrollPane disScroll = new JScrollPane(disPan);
        disScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        disScroll.setPreferredSize(new Dimension(1025,300));
        card1.add(disScroll);
        
        
        ///gets total stats
        int prod = 0;
        int inv = 0;
        double total = 0;
        for(int i = 0; i < scurrent.ProdList().size(); i++){
            
            Product t = scurrent.ProdList().getItem(i);
            total = total + t.GetPrice() * t.GetInventory();
            inv = inv + t.GetInventory();
            prod++;
        } 
        for(int i = 0; i < scurrent.DisList().size(); i++){
            DiscountProduct t = scurrent.DisList().getItem(i);
            total = total + t.GetPrice() * t.GetInventory();
            inv = inv + t.GetInventory();
            prod++;
        }
        for(int i = 0; i < scurrent.BunList().size(); i++){
            BundleProduct t = scurrent.BunList().getItem(i);
            total = total + t.GetPrice() * t.GetInventory();
            inv = inv + t.GetInventory();
            prod++;
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        
        String price = formatter.format(total);
        
        //display total stats
        JPanel totalpan = new JPanel();
        totalpan.setLayout(new BoxLayout(totalpan, BoxLayout.X_AXIS));
                                      
        JTextArea tot= new JTextArea("Total Price:",1, 5);
        Font font = tot.getFont();
        float size = font.getSize() + 4.0f;
        tot.setFont(font.deriveFont(size + 5.0f));
        tot.setMargin(new Insets(10,5,5,0));
        tot.setEditable(false);
        
        JTextArea totaltext = new JTextArea(price,1,10);
        totaltext.setEditable(false);
        totaltext.setFont(font.deriveFont(size));
        totaltext.setMargin(new Insets(14,5,5,0));

        
        
        JTextArea totinv= new JTextArea("Total Inventory:",1, 6);
        totinv.setFont(font.deriveFont(size + 5.0f));
        totinv.setMargin(new Insets(10,5,5,0));
        totinv.setEditable(false);
        
        JTextArea totinvtext = new JTextArea(Integer.toString(inv),1,10);
        totinvtext.setEditable(false);
        totinvtext.setFont(font.deriveFont(size));
        totinvtext.setMargin(new Insets(14,5,5,0));
        
        
        
        JTextArea totprod= new JTextArea("Total Products:",1, 6);
        totprod.setFont(font.deriveFont(size + 5.0f));
        totprod.setMargin(new Insets(10,5,5,0));
        totprod.setEditable(false);
        
        JTextArea totprodtext = new JTextArea(Integer.toString(prod),1,10);
        totprodtext.setEditable(false);
        totprodtext.setFont(font.deriveFont(size));
        totprodtext.setMargin(new Insets(14,5,5,0));

        totalpan.add(totprod);
        totalpan.add(totprodtext);
        totalpan.add(totinv);
        totalpan.add(totinvtext);
        totalpan.add(tot);
        totalpan.add(totaltext);
        
        card1.add(totalpan);
        frame.pack();
    }
    
    
    /**
     * Controls the checkout pages API and UI
     */
    public void Checkout(){
        //main panel to hold everything
        final JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        
        
        //shows up when payment goes throguh
        JLabel success = new JLabel("Purchase Successfull");
        Font font1 = success.getFont();
        float size1 = font1.getSize() + 10.0f;
        success.setFont(font1.deriveFont(size1));
        if (flag2 == 0){
            success.setVisible(false);
        }
        else{
            success.setVisible(true);
        }
        main.add(success);
        
        //panel to hold button to go back to shopping cart
        JPanel prodLabelPan = new JPanel();
        prodLabelPan.setLayout(new BorderLayout());
        
        //allows user to go back to shopppng cart
        //refreshes shopping cart
        JButton back = new JButton("Go Back to Shopping Cart");
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                card3.removeAll();
                ShopCart();
                card3.repaint();
                card3.revalidate();
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Cart");
            }
            
        });
        prodLabelPan.add(back, BorderLayout.WEST);
        main.add(prodLabelPan);
        
        
        ///Name Jpanel
        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
        
        JTextArea username = new JTextArea("             Name:", 1, 6);
        username.setEditable(false);
        Font font = username.getFont();
        float size = font.getSize() + 10.0f;
        username.setFont(font.deriveFont(size));
        username.setMargin(new Insets(10,5,5,0));
        
        JTextArea userText = new JTextArea("",1, 20);
        userText.setEditable(true);
        userText.setFont(font.deriveFont(size));
        userText.setMargin(new Insets(10,0,5,0));
        
        name.add(username);
        name.add(userText);

        
        main.add(name);
        
        
        //card jpanel
        JPanel cardnum = new JPanel();
        cardnum.setLayout(new BoxLayout(cardnum, BoxLayout.X_AXIS));
                                      
        JTextArea card= new JTextArea("Card Number:",1, 6);   
        card.setFont(font.deriveFont(size));
        card.setMargin(new Insets(10,5,5,0));
        card.setEditable(false);
        
        JTextArea cardtext = new JTextArea("",1, 20);
        cardtext.setEditable(true);
        cardtext.setFont(font.deriveFont(size));
        cardtext.setMargin(new Insets(10,5,5,0));

        cardnum.add(card);
        cardnum.add(cardtext);
        
        main.add(cardnum);
        
        
        ///Address panel
        JPanel address = new JPanel();
        address.setLayout(new BoxLayout(address, BoxLayout.X_AXIS));
                                      
        JTextArea addr= new JTextArea("        Address:",1, 6);   
        addr.setFont(font.deriveFont(size));
        addr.setMargin(new Insets(10,5,5,0));
        addr.setEditable(false);
        
        JTextArea addrtext = new JTextArea("",1, 20);
        addrtext.setEditable(true);
        addrtext.setFont(font.deriveFont(size));
        addrtext.setMargin(new Insets(10,5,5,0));
        
        address.add(addr);
        address.add(addrtext);
        
        main.add(address);
        
         //gets total amount in shopping cart
        double total = 0;
        for(int i = 0; i < ccurrent.ProdList().size(); i++){
            
            Product t = ccurrent.ProdList().getItem(i);
            total = total + t.GetPrice() * t.GetInventory();
        } 
        for(int i = 0; i < ccurrent.DisList().size(); i++){
            DiscountProduct t = ccurrent.DisList().getItem(i);
            total = total + t.GetPrice() * t.GetInventory();
        }
        for(int i = 0; i < ccurrent.BunList().size(); i++){
            BundleProduct t = ccurrent.BunList().getItem(i);
            total = total + t.GetPrice() * t.GetInventory();
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        
        String price = formatter.format(total);
        
        
        JPanel totalpan = new JPanel();
        totalpan.setLayout(new BoxLayout(totalpan, BoxLayout.X_AXIS));
                                      
        JTextArea tot= new JTextArea("              Total:",1, 6);   
        tot.setFont(font.deriveFont(size));
        tot.setMargin(new Insets(10,5,5,0));
        tot.setEditable(false);
        
        JTextArea totaltext = new JTextArea(price,1, 20);
        totaltext.setEditable(false);
        totaltext.setFont(font.deriveFont(size));
        totaltext.setMargin(new Insets(10,5,5,0));

        totalpan.add(tot);
        totalpan.add(totaltext);
        main.add(totalpan);
        
        //button to confirm puchase
        JButton confirm = new JButton("Confirm Purchase");
        confirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // if all fields have text then allow button press
                if(!addrtext.getText().equals("") && !cardtext.getText().equals("") && !userText.getText().equals("")){
                    //clears shoping cart info
                    ccurrent.ProdList().removeAll();
                    ccurrent.BunList().removeAll();
                    ccurrent.DisList().removeAll();
                    System.out.println("Cleared");
                    
                    //refresh view
                    card5.removeAll();
                    //allows the payment successfull panel to show
                    flag2 = 1;
                    Checkout();
                    card5.repaint();
                    card5.revalidate();
                }
                
            }
            
        });
        main.add(confirm);
        

        card5.add(main);
        frame.pack();
    }

    public static void main (String args[]) throws ClassNotFoundException{
        //calls system and starts API
        SystemAPI n = new SystemAPI();
    }

    
}
