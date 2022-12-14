package stus.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class StuasutItems {
    public static Item zinc, bariumraw, barium, cadmiumraw, cadmium, rheniumraw, rhenium, antimonyraw, antimony, dencealloy;

    public static final Seq<Item> rapuItems = new Seq<>();

    public static void load(){
        zinc = new Item("zinc", Color.valueOf("8ca0b3ff")) {{
            alwaysUnlocked = true;
            hardness = 1;
            cost = 0.7f;
        }};
        bariumraw = new Item("barium-raw", Color.valueOf("c18f4dff")) {{
            alwaysUnlocked = true;
            hardness = 1;
            cost = 0.7f;
        }};
        barium = new Item("barium", Color.valueOf("ddba5fff")) {{
            cost = 0.7f;
        }};

        cadmiumraw = new Item("cadmium-raw", Color.valueOf("246807ff")) {{
            hardness = 2;
            cost = 0.7f;
        }};
        cadmium = new Item("cadmium", Color.valueOf("4d8f30ff")) {{
            cost = 0.7f;
        }};

        rheniumraw = new Item("rhenium-raw", Color.valueOf("c99292ff")) {{
            hardness = 3;
            cost = 0.7f;
        }};
        rhenium = new Item("rhenium", Color.valueOf("c8425fff")) {{
            cost = 0.7f;
        }};
        antimonyraw = new Item("antimony-raw", Color.valueOf("4579a7ff")) {{
            hardness = 4;
            cost = 0.7f;
        }};
        antimony = new Item("antimony", Color.valueOf("7bb1e1ff")) {{
            cost = 0.7f;
        }};
        dencealloy = new Item("dencealloy", Color.valueOf("7bb1e1ff")) {{
            cost = 0.7f;
        }};
        rapuItems.addAll(
                zinc, bariumraw, barium, cadmiumraw, cadmium, rheniumraw, rhenium, antimonyraw, antimony, dencealloy
        );
    }
}