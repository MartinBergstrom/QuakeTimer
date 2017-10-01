package Config;

import Entity.EntityTimerTypes;

import java.util.List;

public interface ConfigurationProvider {

    public void loadConfig();

    public List<Character> getKeyBindings();

    public char getKeyBindingForEntityType(EntityTimerTypes type);

    public ArmorTimes getArmorTimes();
}
