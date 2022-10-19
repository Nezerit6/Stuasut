package stus;

import arc.*;
import arc.struct.Seq;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.type.Item;
import mindustry.ui.dialogs.*;
import stus.content.StuasutBlocks;
import stus.content.StuasutItems;
import stus.content.StuasutPlanets;
import stus.content.StuasutUnits;

public class Stuasut extends Mod{

    public static Seq<Item> hiddenItems;

    public Stuasut(){
        Log.info("Loaded Stuasut constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("stuasut-frog.png")).pad(20f).row();
                dialog.cont.button("I remember the penis is big", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some stus content.");
        StuasutBlocks.load();
        StuasutItems.load();
        StuasutUnits.load();
        StuasutPlanets.load();
        //StuasutTechTree.java();
    }

}
