package stus.world.blocks.storage;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.storage.*;
import stus.graphics.*;

import static mindustry.Vars.*;

public class RegenCore extends CoreBlock {
    static final float refreshInterval = 6f;

    public float range = 80f;
    public Color circleColor = RapuPal.rushCol, glowColor = RapuPal.rushCol.cpy().a(0.5f);
    public float circleSpeed = 120f, circleStroke = 3f, polyRad = 5f, polySpinScl = 0.8f, glowMag = 0.5f, glowScl = 8f;
    public int polySides = 6;
    public float damage = 1;
    public TextureRegion glow;

    public RegenCore(String name){
        super(name);
        solid = true;
        update = true;
        hasPower = true;
        hasItems = true;
        emitLight = true;
        lightRadius = 50f;
        suppressable = true;
        envEnabled |= Env.space;
        buildType = RegenCoreBuild::new;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);
    }
    @Override
    public void load() {
        super.load();
        glow = Core.atlas.find(this.name + "-glow");
    }

    public class RegenCoreBuild extends CoreBuild implements Ranged {
        public float refresh = Mathf.random(refreshInterval);
        public float warmup = 0f;
        public float totalProgress = 0f;
        public Seq<Unit> targets = new Seq<>();
        public Seq<Unit> targetsAlly = new Seq<>();

        @Override
        public void updateTile(){

            if(potentialEfficiency > 0 && (refresh += Time.delta) >= refreshInterval){
                targets.clear();
                targetsAlly.clear();
                refresh = 0f;
                Units.nearbyEnemies(team, x, y, range, u -> {
                    targets.add(u);
                });
                Units.nearby(team, x, y, range, u -> {
                    targetsAlly.add(u);
                });
            }

            boolean any = false;
            if(efficiency > 0){
                for(var target : targets){
                    target.damage(damage);
                    any = true;
                }
                for(var targetA : targetsAlly){
                    targetA.heal(damage);
                    any = true;
                }
            }

            warmup = Mathf.lerpDelta(warmup, any ? efficiency : 0f, 0.08f);
            totalProgress += Time.delta / circleSpeed;
        }

        @Override
        public boolean shouldConsume(){
            return targets.size > 0;
        }

        @Override
        public void draw(){
            super.draw();

            if(warmup <= 0.001f) return;

            Draw.z(Layer.effect);
            float mod = totalProgress % 1f;
            Draw.color(circleColor);
            Lines.stroke(circleStroke * (1f - mod) * warmup);
            Lines.circle(x, y, range * mod);
            Draw.color(circleColor);
            Fill.poly(x, y, polySides, polyRad * warmup, Time.time / polySpinScl);
            Draw.reset();

            Drawf.additive(glow, glowColor, warmup * (1f - glowMag + Mathf.absin(Time.time, glowScl, glowMag)), x, y, 0f, Layer.blockAdditive);
        }

        @Override
        public float range(){
            return range;
        }

        @Override
        public float warmup(){
            return warmup;
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, Pal.placing);
        }
    }
}
