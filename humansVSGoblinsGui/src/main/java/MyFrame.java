import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MyFrame extends JFrame {
    public JPanel mapPanel = new JPanel();
    public JPanel inventoryPanel = new JPanel();
    public JPanel informationPanel = new JPanel();
    public JPanel actionPanel = new JPanel(); // handles user input
    public final JLabel mapArea = new JLabel("");
    public final JLabel textFeedback = new JLabel();
    
    MyFrame(Land map) {
        this.setSize(1000, 600);
        this.setTitle("Humans Vs Goblins");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Icon.jpg");
        this.setIconImage(image.getImage());
        createGui(map);
        this.setVisible(true);
    }
    
    public void createGui(Land map) {
        //map component
        mapArea.setText(map.toString());
        Font mapFont = new Font("Monospaced", Font.BOLD, 13);
        mapArea.setFont(mapFont);
        // mapPanel.setBackground(new Color(0, 0, 0));
        Border border = BorderFactory.createLineBorder(new Color(0,0,0), 2);
        mapArea.setBorder(border);
        mapPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        mapArea.setBackground(new Color(50, 50, 50));
        mapArea.setOpaque(true);
        mapPanel.add(mapArea);
        this.add(mapPanel, BorderLayout.PAGE_START);

        //info Component
        Font main = new Font("Arial", Font.BOLD, 14);
        textFeedback.setFont(main);
        ImageIcon infoIcon = new ImageIcon("info.png");
        textFeedback.setIcon(infoIcon);
        textFeedback.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        textFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        textFeedback.setHorizontalAlignment(JLabel.LEFT);
        textFeedback.setIconTextGap(25);
        textFeedback.setText("Which direction would you like to move? (N, S, E, W) or (M) to manageInventory");
        textFeedback.setBorder(border);
        textFeedback.setOpaque(true);
        textFeedback.setBackground(new Color(50,50,50));
        this.add(textFeedback, BorderLayout.SOUTH);

        updateColors();
    }

    private void updateColors() {
        // credit:
        // https://stackoverflow.com/questions/36128291/how-to-make-a-swing-application-have-dark-nimbus-theme-netbeans
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            UIManager.put("control", new Color(128, 128, 128));
            UIManager.put("info", new Color(128, 128, 128));
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(115, 164, 209));
            UIManager.put("nimbusGreen", new Color(176, 179, 50));
            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
            UIManager.put("nimbusOrange", new Color(191, 98, 4));
            UIManager.put("nimbusRed", new Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
            UIManager.put("text", new Color(230, 230, 230));
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Nimbus: Unsupported Look and feel!");
        }
    }
}
