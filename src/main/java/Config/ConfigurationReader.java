package Config;

import Entity.EntityTimerTypes;
import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationReader implements ConfigurationProvider {
    private List<Character> keyBindings;
    private ArmorTimes armorTimes;

    private static ConfigurationReader instance;

    public static ConfigurationReader getInstance(){
        if(instance == null){
            instance = new ConfigurationReader();
            instance.loadConfig();
            return instance;
        }
        return instance;
    }

    private ConfigurationReader(){}

    @Override
    public void loadConfig(){
        Path path = Paths.get("Config.json");
        try {
            String jsonString = FileUtils.readFileToString(path.toFile(), Charset.defaultCharset());
            System.out.println(jsonString);

            Gson gson = new GsonBuilder().create();
            armorTimes = gson.fromJson(jsonString, ArmorTimes.class);

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jsonString);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            JsonArray keyBindingsArray = jsonObject.getAsJsonArray("key_bindings");
            keyBindings = new ArrayList<>();
            keyBindingsArray.forEach((jsonE) -> keyBindings.add(jsonE.getAsCharacter()) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Character> getKeyBindings(){
        return keyBindings;
    }

    @Override
    public char getKeyBindingForEntityType(EntityTimerTypes type) {
        switch (type){
            case YELLOW_ARMOR:
                return keyBindings.get(0);
            case RED_ARMOR:
                return keyBindings.get(1);
            case MEGA_HEALTH:
                return keyBindings.get(2);
        }
        return 0;
    }

    @Override
    public ArmorTimes getArmorTimes(){
        return armorTimes;
    }
}
