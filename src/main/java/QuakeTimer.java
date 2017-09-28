import Observerable.QuakeObservable;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class QuakeTimer extends JLabel {
    private Timer timer;
    private Date startTime;
    private QuakeObservable quakeObservable;

    public QuakeTimer(QuakeObservable observable){
        super("", SwingConstants.CENTER);
        this.quakeObservable = observable;
        this.setPreferredSize(new Dimension(CommonTimeUtils.PREFFERED_WIDHT,20));
        this.setFont(new Font("Impact", Font.PLAIN, 30));
        this.setForeground(new Color(195,0,0));

        timer = new Timer(1000, (e) -> {
            Date currentTime = new Date();

            long timeDifference = currentTime.getTime() - startTime.getTime();

            String time = CommonTimeUtils.getFormattedTime(timeDifference);

            this.setText(time);
            quakeObservable.notifyObservers(timeDifference);
        });
    }

    public void startTimer(){
        startTime = new Date();
        timer.start();
    }
}
