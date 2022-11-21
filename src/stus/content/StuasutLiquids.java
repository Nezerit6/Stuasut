package stus.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class StuasutLiquids {
    public static Liquid diethylEther;

    public static void load() {
        diethylEther = new Liquid("diethyl-ether", Color.valueOf("E9B27FFF")) {{
            temperature = 0.57f;
            viscosity = 0.35f;
            flammability = 2f;
            heatCapacity = -0.5f;
            boilPoint = 0.25f;
        }};
    }
}
