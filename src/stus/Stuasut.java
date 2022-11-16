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
import stus.content.*;

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
                dialog.cont.image(Core.atlas.find("icon.png")).pad(40f).row();
                dialog.cont.button("I remember the penis is big", dialog::hide).size(300f, 60f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        /**
         * Initialization
         * - Teams
         * - Status effects
         * - Weather
         * - Items
         * - Liquids
         * - Bullet
         * - Units
         * - Blocks
         * - Planets(Sectors)
         * - Tech tree
         */
        Log.info("Loading some stus content.");

        StuasutStatus.load();
        StuasutItems.load();
        StuasutUnits.load();
        StuasutBlocks.load();
        StuasutSchematics.load();
        StuasutPlanets.load();
        StuasutTechTree.load();
    }

}
