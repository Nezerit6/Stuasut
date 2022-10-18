package stus.content;

import arc.graphics.*;
import arc.struct.*;
//import stus.world.blocks.campaning.*;
//import stus.world.blocks.distribution.*;
//import stus.world.blocks.power.*;
//import stus.world.blocks.production.*;
//import stus.world.blocks.storage.*;
//import stus.graphics.*;
//import stus.world.blocks.defence.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.meta.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.units.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.defense.turrets.*;


import static mindustry.type.ItemStack.*;

public class StuasutBlocks{
    public static Block

            //environment
            gertwall, limestonewall, gertboulder, limestoneboulder, gert, limestone, mercurymud, orezinc, orebarium, orecadmium, orerhenium,

    //defence
    bariumwall, bariumwalllarge, cadmiumwall, cadmiumwalllarge, rheniumwall, rheniumwalllarge,

    //breackers

    //crafting
    furnace,

    //production
    zinccrusher,

    //power
    windgenerator, zincbattery, zincbatterylarge, zincnode, zincnodelarge,

    //storage
    dawn,

    //distribution


    //turrets
    togi, pulse, collapse,

    //units
    dronefabricator;


    public void load() {
        //environment
        gert = new Floor("gert"){{
            playerUnmineable = true;
            variants = 3;
        }};
        gertwall = new StaticWall("gert-wall"){{
            variants = 3;
        }};
        limestonewall = new StaticWall("limestone-wall"){{
            variants = 3;
        }};
        mercurymud = new Floor("mercury-mud"){{
            variants = 3;
        }};
        orezinc = new OreBlock(StuasutItems.zinc){{
            oreDefault = true;
        }};