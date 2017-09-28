import Config.ConfigurationReader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartGameKeyListener implements KeyListener {
    private final QuakeTimer quakeTimer;
    private final ConfigurationReader configurationReader;

    public StartGameKeyListener(QuakeTimer quakeTimer, ConfigurationReader configurationReader){
        this.quakeTimer = quakeTimer;
        this.configurationReader = configurationReader;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        for(char c: configurationReader.getKeyBindings()){
            if(c == e.getKeyChar()){
                quakeTimer.startTimer();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
