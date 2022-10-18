package stus;

import mindustry.mod.Mod;
import stus.content.*;

public class Stuasut extends Mod{
    @Override
    public void loadContent(){
        new StuasutItems().load();
//        new StuasutUnits().load();
        new StuasutBlocks().load();

//        new StuasutPlanets().load();
//        new StuasutTechTree().load();
    }

}
