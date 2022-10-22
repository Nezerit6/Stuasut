package stus.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.type.*;

public class StuasutStatus {
    public static StatusEffect
            inGallium;
    public static void load(){
        inGallium = new StatusEffect("in-gallium"){{
            speedMultiplier = 0.65f;
            color = Color.gray;
            init(() -> {
                opposite(StatusEffects.overclock, StatusEffects.overdrive);
            });
        }};
    }
}
