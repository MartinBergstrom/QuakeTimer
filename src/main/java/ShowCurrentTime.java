import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowCurrentTime extends JLabel {
    private SimpleDateFormat simpleDateFormat;

    public ShowCurrentTime(){
        super("",SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(CommonTimeUtils.PREFFERED_WIDHT,20));
        this.setForeground(Color.WHITE);

        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        Timer timer = new Timer(1000, (e) -> {
            Date date = new Date();
            this.setText(simpleDateFormat.format(date));
        } );

        timer.start();
    }
}
