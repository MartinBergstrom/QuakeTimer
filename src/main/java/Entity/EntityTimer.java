package Entity;

import Main.CommonTimeUtils;
import Observerable.Observer;
import Observerable.Observerable;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel representing the timer of a specific entity such as yello/red armor and mega health
 */
public class EntityTimer extends JPanel implements Observer<Long>, GlobalKeyListener{
    private long offset;
    private JLabel mainTimerPanel;
    private JLabel snapshotLabel;
    private EntityTimerTypes myType;

    public EntityTimer(int initialOffset, EntityTimerTypes type){
        super(new BorderLayout());
        this.myType = type;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(CommonTimeUtils.PREFFERED_WIDHT, 50));
        this.offset = initialOffset*1000;

        //main label
        mainTimerPanel = new JLabel("", JLabel.RIGHT);
        mainTimerPanel.setOpaque(false);
        mainTimerPanel.setFont(new Font("Impact", Font.PLAIN, 24));
        mainTimerPanel.setForeground(new Color(165,0,0));

        //snapshot label
        snapshotLabel = new JLabel("", JLabel.RIGHT);
        snapshotLabel.setOpaque(false);
        snapshotLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        snapshotLabel.setForeground(new Color(174, 174, 174));
        snapshotLabel.setEnabled(false);

        this.add(mainTimerPanel, BorderLayout.NORTH);
        this.add(snapshotLabel, BorderLayout.SOUTH);
    }

    public void changeKeyBinding(char c){
        myType.setKeyBinding(c);
    }

    @Override
    public void onChanged(Observerable observerable, Long e) {
        String convertedTime = CommonTimeUtils.getFormattedTime(e.longValue() + offset );
        mainTimerPanel.setText(convertedTime + " ");
    }


    @Override
    public void keyPressed(GlobalKeyEvent globalKeyEvent) {
        if(globalKeyEvent.getKeyChar() == myType.getKeybinding()){
            snapshotLabel.setText(mainTimerPanel.getText());
        }
    }

    @Override
    public void keyReleased(GlobalKeyEvent globalKeyEvent) {}
}
