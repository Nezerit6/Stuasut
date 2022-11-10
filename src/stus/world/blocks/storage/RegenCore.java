package stus.world.blocks.storage;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.storage.*;

import static mindustry.Vars.*;

public class RegenCore extends CoreBlock {
    public final int timerUse = timers++;
    public Color baseColor = Color.valueOf("84f491");
    public TextureRegion topRegion;
    public float reload = 250f;
    public float range = 60f;
    public float healPercent = 12f;
    public float useTime = 400f;

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
    }

    @Override
    public boolean outputsItems(){
        return false;
    }
    @Override
    public void setStats(){
        stats.timePeriod = useTime;
        super.setStats();

        stats.add(Stat.repairTime, (int)(100f / healPercent * reload / 60f), StatUnit.seconds);
        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
    }
    @Override
    public void load() {
        super.load();
        topRegion = Core.atlas.find(this.name + "-top");
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, baseColor);

        indexer.eachBlock(player.team(), x * tilesize + offset, y * tilesize + offset, range, other -> true, other -> Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f))));
    }

    public class RegenCoreBuild extends CoreBuild implements Ranged {
        public float heat, charge = Mathf.random(reload), smoothEfficiency;

        @Override
        public float range() {
            return range;
        }

        @Override
        public void updateTile() {
            boolean canHeal = !checkSuppression();

            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 && canHeal ? 1f : 0f, 0.08f);
            charge += heat * delta();

            if (optionalEfficiency > 0 && timer(timerUse, useTime) && canHeal) {
                consume();
            }

            if (charge >= reload && canHeal) {
                float realRange = range;
                charge = 0f;

                indexer.eachBlock(this, realRange, b -> b.damaged() && !b.isHealSuppressed(), other -> {
                    other.heal(other.maxHealth() * healPercent / 100f * efficiency);
                    other.recentlyHealed();
                    Fx.healBlockFull.at(other.x, other.y, other.block.size, baseColor, other.block);
                });
            }
        }

        @Override
        public double sense(LAccess sensor) {
            if (sensor == LAccess.progress) return Mathf.clamp(charge / reload);
            return super.sense(sensor);
        }

        @Override
        public void drawSelect() {
            float realRange = range;

            indexer.eachBlock(this, realRange, other -> true, other -> Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f))));

            Drawf.dashCircle(x, y, realRange, baseColor);
        }

        @Override
        public void drawLight() {
            Drawf.light(x, y, lightRadius * smoothEfficiency, baseColor, 0.7f * smoothEfficiency);
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            heat = read.f();
        }
    }
}
