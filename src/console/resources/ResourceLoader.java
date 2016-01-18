package console.resources;

import java.io.File;

public class ResourceLoader {

    static ResourceLoader rl = new ResourceLoader();

    public static File getConfigFile(){
        File file = null;
        try {
            String name = rl.getClass().getResource("configuration.xml").getPath();
            file = new File(name);
        } catch (Exception e){
            System.out.println("File not found!");
            System.exit(0);
        }
        return file;
    }
}
