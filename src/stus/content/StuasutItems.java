package stus.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;
import stus.content.StuasutItems.*;

public class StuasutItems {
    public static Item zinc;
    public static Item bariumraw;
    public static Item barium;
    public static Item cadmiumraw;
    public static Item cadmium;
    public static Item rheniumraw;
    public static Item rhenium;
    public static Item antimonyraw;
    public static Item antimony;
    public static Item dencealloy;

    public static final Seq<Item> StuasutItems = new Seq<>();

    public static void load() {
        zinc = new Item("zinc", Color.valueOf("8ca0b3ff")) {{
            hardness = 1;
            cost = 0.7f;
        }};
        bariumraw = new Item("barium-raw", Color.valueOf("c18f4dff")) {{
            hardness = 1;
            cost = 0.7f;
        }};
        barium = new Item("barium", Color.valueOf("ddba5fff")) {{
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
    }
}