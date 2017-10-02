package Main;

import Config.ConfigurationFactory;
import Config.ConfigurationProvider;
import Entity.EntityTimer;
import Entity.EntityTimerTypes;
import Observerable.QuakeObservable;
import lc.kra.system.keyboard.GlobalKeyboardHook;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {
        new Launcher();
    }

    private static JFrame frame;
    private ConfigurationProvider configurationProvider;
    private static JPanel mainPanel;
    private static GlobalKeyboardHook globalKeyboardHook;

    public Launcher(){
        globalKeyboardHook = new GlobalKeyboardHook(true);

        configurationProvider = ConfigurationFactory.INSTANCE.getConfigurationProvider();
        setUpGradientButtons();

        frame = new JFrame("Quake");
        JLabel backgroundImage = new JLabel(new ImageIcon(Launcher.class.getClassLoader().getResource("abstract_dark.jpg")));
        backgroundImage.setPreferredSize(new Dimension(CommonTimeUtils.PREFFERED_WIDHT, 280));
        frame.setContentPane(backgroundImage);

        frame.setAlwaysOnTop(true);
        frame.setLocationByPlatform(true);

        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(5,1));

        //create observable
        QuakeObservable quakeObservable = new QuakeObservable();

        //set up key binings
        setUpKeyBindings();
        //create observers
        ImageIcon yellowArmorPic = getResizedImageIcon(new ImageIcon(Launcher.class.getClassLoader().getResource("yellow_armor.jpg")));
        EntityTimer yellowArmor = new EntityTimer(configurationProvider.getArmorTimes().getYellowArmorTime(), EntityTimerTypes.YELLOW_ARMOR);
        //GlobalScreen.addNativeKeyListener(yellowArmor);


        ImageIcon redArmorPic = getResizedImageIcon(new ImageIcon(Launcher.class.getClassLoader().getResource("red_armor.jpg")));
        EntityTimer redArmor = new EntityTimer(configurationProvider.getArmorTimes().getRedArmorTime(), EntityTimerTypes.RED_ARMOR);

        ImageIcon megaPic = getResizedImageIcon(new ImageIcon(Launcher.class.getClassLoader().getResource("mega.jpg")));
        EntityTimer mega = new EntityTimer(configurationProvider.getArmorTimes().getMegaHealthTime(), EntityTimerTypes.MEGA_HEALTH);

        QuakeTimer quakeTimer = new QuakeTimer(quakeObservable);

        quakeObservable.addObserver(yellowArmor);
        quakeObservable.addObserver(redArmor);
        quakeObservable.addObserver(mega);

        //Button
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(12,15,11,15));
        buttonPanel.setOpaque(false);
        JButton startGame = new JButton("Start game");
        startGame.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        startGame.setForeground(new Color(0,0,0));
        startGame.setFocusPainted(false);
        startGame.addActionListener((e) -> quakeTimer.startTimer());
        buttonPanel.add(startGame, BorderLayout.CENTER);

        //Add everything
        frame.add(new ShowCurrentTime(), BorderLayout.NORTH);
        mainPanel.setOpaque(false);
        mainPanel.add(buttonPanel);
        mainPanel.add(quakeTimer);
        mainPanel.add(createEntityPanel(yellowArmorPic, yellowArmor));
        mainPanel.add(createEntityPanel(redArmorPic, redArmor));
        mainPanel.add(createEntityPanel(megaPic, mega));
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    globalKeyboardHook.shutdownHook();
                    System.exit(0);
                }
            }
        });
    }

    private void setUpKeyBindings() {
        for(EntityTimerTypes type : EntityTimerTypes.values()){
            type.setKeyBinding(configurationProvider.getKeyBindingForEntityType(type));
        }
    }

    private static void setUpGradientButtons() {
        try {
            UIManager manager=new UIManager();
            List<Object> a=new LinkedList<>();
            a.add(0.3);
            a.add(0.3);
            a.add(new ColorUIResource(41, 41, 41));
            a.add(new ColorUIResource(96, 95, 88));
            a.add(new ColorUIResource(0, 0, 0));

            manager.put("Button.gradient",a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ImageIcon getResizedImageIcon(ImageIcon icon){
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private static JPanel createEntityPanel(ImageIcon icon, EntityTimer timerPanel){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(icon, JLabel.LEFT), BorderLayout.WEST);
        panel.add(timerPanel, BorderLayout.EAST);
        panel.setOpaque(false);
        globalKeyboardHook.addKeyListener(timerPanel);
        return panel;
    }
}
