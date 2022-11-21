package stus.world.blocks.effects;

import arc.graphics.Color;
import mindustry.gen.Groups;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.consumers.ConsumeCoolant;
import mindustry.world.meta.*;

public class PolygonForceProjector extends ForceProjector{

    public PolygonForceProjector(String name){
        super(name);
        update = true;
        solid = true;
        group = BlockGroup.projectors;
        hasPower = true;
        hasLiquids = true;
        hasItems = true;
        envEnabled |= Env.space;
        ambientSound = Sounds.shield;
        ambientSoundVolume = 0.08f;
    }

    public class PolygonForceBuild extends ForceBuild{
        public void deflectBullets(){
            float realRadius = realRadius();

            if(realRadius > 0 && !broken){
                paramEntity = this;
                paramEffect = absorbEffect;
                Groups.bullet.intersect(x - realRadius, y - realRadius, realRadius * 2f, realRadius * 2f, shieldConsumer);
            }
        }
    }
}
