package stus.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class StuasutLiquids {
    public static Liquid diethylEther;

    public static void load() {
        diethylEther = new Liquid("diethyl-ether", Color.valueOf("E9B27FFF")) {{
            temperature = 2.15f;
            viscosity = 0.35f;
            flammability = 5f;
            heatCapacity = -0.5f;
            boilPoint = 0.25f;
        }};
    }
}
