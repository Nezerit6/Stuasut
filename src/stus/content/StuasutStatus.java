package stus.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.type.*;

public class StuasutStatus {
    public static StatusEffect
            inGallium, inDiethylEther;

    public static void load() {
        inGallium = new StatusEffect("in-gallium") {{
            speedMultiplier = 0.65f;
            color = Color.gray;
            init(() -> {
                opposite(StatusEffects.overclock, StatusEffects.overdrive);
            });
        }};
        inDiethylEther = new StatusEffect("in-diethyl-ether") {{
            speedMultiplier = 0.45f;
            color = Color.coral;
            effect = Fx.wet;
            effectChance = 0.02f;
            transitionDamage = 25;
            init(() -> {
                opposite(StatusEffects.wet, StatusEffects.overclock, StatusEffects.overdrive);
                affinity(StatusEffects.melting, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                    result.set(StatusEffects.melting, result.time + time);
                });
                affinity(StatusEffects.burning, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                    result.set(StatusEffects.burning, result.time + time);
                });
            });
        }};
    }
}
