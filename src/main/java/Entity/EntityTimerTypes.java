package Entity;

public enum EntityTimerTypes {
    YELLOW_ARMOR('0'),
    RED_ARMOR('0'),
    MEGA_HEALTH('0');

    private char c;

    EntityTimerTypes(char c){
        this.c = c;
    }

    public void setKeyBinding(char c){
        this.c = c;
    }

    public char getKeybinding(){
        return c;
    }
}
