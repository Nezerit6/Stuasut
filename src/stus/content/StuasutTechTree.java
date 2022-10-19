package stus.content;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.*;
import static mindustry.content.TechTree.*;
//import static stus.content.StuasutSectors.*;

public class StuasutTechTree {
    public void load() {
        StuasutPlanets.rapu.techTree = nodeRoot("stuasut", StuasutBlocks.coreDawn, true,() -> {
            nodeProduce(StuasutItems.zinc, () -> {
                nodeProduce(StuasutItems.bariumraw, () -> {
                });
            });
            node(StuasutBlocks.bariumWall, () -> {
            });

            });
    }
}
