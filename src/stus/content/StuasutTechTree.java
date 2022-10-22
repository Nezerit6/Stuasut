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
    public static void load(){
        //start of tech tree - core
        StuasutPlanets.rapu.techTree = nodeRoot("stuasut", StuasutBlocks.coreDawn, () -> {
            //the first branch
            nodeProduce(StuasutItems.zinc, () -> {
                nodeProduce(StuasutItems.bariumraw, () -> {
                    nodeProduce(StuasutItems.barium, () -> {
                    });
                    nodeProduce(StuasutItems.cadmiumraw, () -> {
                        nodeProduce(StuasutItems.rheniumraw, () -> {
                            nodeProduce(StuasutItems.antimonyraw, () -> {
                               nodeProduce(StuasutItems.antimony, () -> {

                               });
                            });
                            nodeProduce(StuasutItems.rhenium, () -> {
                            });
                        });
                        nodeProduce(StuasutItems.cadmium, () -> {
                            nodeProduce(StuasutItems.dencealloy, () -> {
                            });
                        });
                    });
                });
            });
            node(StuasutBlocks.zinccrusher, () ->{
                //TODO more drills
            });
            node(StuasutBlocks.windgenerator, () ->{
                //TODO more power blocks
            });
            node(StuasutBlocks.zincBridge, () ->{
                //TODO more distribute blocks
            });
            node(StuasutBlocks.bariumForge, () ->{
                //TODO more factories
            });
            node(StuasutBlocks.bariumWall, () -> {
                node(StuasutBlocks.bariumWallLarge, () -> {
                });
                node(StuasutBlocks.cadmiumWall, () -> {
                    node(StuasutBlocks.cadmiumWallLarge, () -> {
                       node(StuasutBlocks.rheniumWall, () -> {
                          node(StuasutBlocks.rheniumWallLarge, () -> {
                          });
                       });
                       node(StuasutBlocks.cadmiumWallLarge, () -> {
                       });
                    });
                });
            });
        });
    }
}
