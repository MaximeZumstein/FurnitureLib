package de.Ste3et_C0st.FurnitureLib.ModelLoader;

import de.Ste3et_C0st.FurnitureLib.Crafting.Project;
import de.Ste3et_C0st.FurnitureLib.SchematicLoader.ProjectLoader;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.Type.PlaceableSide;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.bukkit.configuration.file.YamlConfiguration;

public class ModelFileLoader {

    public static void loadModelFiles() {
        File folder = new File(FurnitureLib.isNewVersion() ? "plugins/FurnitureLib/models/" : "plugins/FurnitureLib/Crafting/");
        if (folder.exists()) {
            Arrays.asList(folder.listFiles()).forEach(ModelFileLoader::loadModelFile);
        }
    }

    public static Project loadModelFile(File file) {
        try (InputStream stream = new FileInputStream(file)) {
            String name = file.getName().replace(".dModel", "").replace(".yml", "");
            FurnitureLib.debug("ModelFileLoader: Start loading -> " + name);
            Project pro = new Project(name, FurnitureLib.getInstance(), stream, PlaceableSide.TOP, ProjectLoader.class);
            pro.applyFunction();
            pro.setEditorProject(true);
            return pro;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
