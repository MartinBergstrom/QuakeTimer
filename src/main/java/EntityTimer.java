import Observerable.Observer;
import Observerable.Observerable;

import javax.swing.*;
import java.awt.*;

public class EntityTimer extends JLabel implements Observer<Long> {
    private long offset;

    public EntityTimer(long initialOffset){
        super("", JLabel.RIGHT);
        this.setPreferredSize(new Dimension(CommonTimeUtils.PREFFERED_WIDHT, 50));
        this.offset = initialOffset;
        this.setFont(new Font("Impact", Font.PLAIN, 18));
        this.setForeground(new Color(165,0,0));
    }

    @Override
    public void onChanged(Observerable observerable, Long e) {
        String convertedTime = CommonTimeUtils.getFormattedTime(e.longValue() + offset );
        this.setText(convertedTime + " ");
    }
}
