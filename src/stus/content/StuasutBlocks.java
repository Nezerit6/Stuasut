package stus.content;
import mindustry.content.*;
import mindustry.entities.effect.*;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;


public class StuasutBlocks {
    public static Block
    //environment
    gertwall, limestonewall, gert, limestone, mercurymud, oreZinc, oreBarium, oreCadmium, oreRhenium, oreAntimony,

    //liquid
    galliumPuddle,

    //defence
    bariumWall, bariumWallLarge, cadmiumWall, cadmiumWallLarge, rheniumWall, rheniumWallLarge,

    //crafting


    //furnace,

    //production
    zinccrusher,impulseCrusher,

    //power
    windgenerator, zincbattery, zincbatterylarge, zincnode, zincnodelarge,

    //storage
    coreDawn,

    //distribution
    zincBridge, zincDuct, bariumDuct

    //turrets
    //togi, pulse, collapse,

    //units
    ;

    public static void load(){
        //environment
        gert = new Floor("gert") {{
            playerUnmineable = true;
            variants = 3;
        }};
        gertwall = new StaticWall("gert-wall") {{
            variants = 2;
        }};
        limestonewall = new StaticWall("limestone-wall") {{
            variants = 2;
        }};
        mercurymud = new Floor("mercury-mud") {{
            variants = 3;
        }};
        limestone = new Floor("limestone") {{
            variants = 3;
        }};
        oreZinc = new OreBlock(StuasutItems.zinc) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        oreBarium = new OreBlock(StuasutItems.bariumraw) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        oreCadmium = new OreBlock(StuasutItems.cadmiumraw) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        oreRhenium = new OreBlock(StuasutItems.rheniumraw) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        oreAntimony = new OreBlock(StuasutItems.antimonyraw) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        //liquid
        galliumPuddle = new Floor("gallium-puddle"){{
           speedMultiplier = 0.25f;
           variants = 0;
           status = StuasutStatus.inGallium;
           statusDuration = 15f * 60f;
           liquidDrop = Liquids.gallium;
           isLiquid = true;
           supportsOverlay = true;
        }};

        //defence
        bariumWall = new Wall("barium-wall"){{
            requirements(Category.defense, with(StuasutItems.barium, 6));
            health = 80 * 6;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
        }};
        bariumWallLarge = new Wall("barium-wall-large"){{
            requirements(Category.defense, with(StuasutItems.barium, 24));
            health = 80 * 6 * 2;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
            size = 2;
        }};
        cadmiumWall = new Wall("cadmium-wall"){{
            requirements(Category.defense, with(StuasutItems.cadmium, 6));
            health = 80 * 10;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
        }};
        cadmiumWallLarge = new Wall("cadmium-wall-large"){{
            requirements(Category.defense, with(StuasutItems.cadmium, 24));
            health = 80 * 8 * 2;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
            size = 2;
        }};
        rheniumWall = new Wall("rhenium-wall"){{
            requirements(Category.defense, with(StuasutItems.rhenium, 6));
            health = 80 * 10;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
        }};
        rheniumWallLarge = new Wall("rhenium-wall-large"){{
            requirements(Category.defense, with(StuasutItems.rhenium, 24));
            health = 80 * 8 * 2;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
            size = 2;
        }};

        //crafting
        //oh shit

        //production

        zinccrusher = new BurstDrill("zinc-crusher"){{
            requirements(Category.production, with(StuasutItems.zinc, 12, StuasutItems.bariumraw, 6));
            drillTime = 60f * 5f;
            size = 2;
            hasPower = true;
            tier = 2;
            drillEffect = new MultiEffect(Fx.mineImpact, Fx.drillSteam, Fx.mineImpactWave.wrap(Pal.redLight, 40f));
            shake = 3;
            itemCapacity = 25;
            alwaysUnlocked = true;
            consumePower(20f / 60f);
        }};
        gallium.hidden = false;
        impulseCrusher = new BurstDrill("impulse-crusher"){{
            requirements(Category.production, with(StuasutItems.zinc, 50, StuasutItems.barium, 25, StuasutItems.cadmiumraw, 15));
            drillTime = 60f * 8f;
            size = 5;
            tier = 4;
            shake = 5f;
            itemCapacity = 150;

            consumePower(90f/60f);
            consumeLiquid(Liquids.gallium, 6f/60f);

            drillEffect = new MultiEffect(Fx.mineImpact, Fx.drillSteam, Fx.mineImpactWave.wrap(Pal.redLight, 40f));
            //TODO remake drillEffect, after make sprites
        }};

        //power

        windgenerator = new ConsumeGenerator("wind-generator"){{
            size = 2;
            requirements(Category.power, with(StuasutItems.zinc, 20, StuasutItems.bariumraw, 8));
            powerProduction = 0.5f;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 0.4f * 9f));
        }};//TODO make more power generator

        //storage

        coreDawn = new CoreBlock("core-dawn"){{
            requirements(Category.effect, BuildVisibility.editorOnly, with(StuasutItems.zinc, 2000, StuasutItems.dencealloy, 560, StuasutItems.barium, 800));
            alwaysUnlocked = true;

            drawArrow = false;
            isFirstTier = true;
            unitType = UnitTypes.alpha;
            health = 3000;
            itemCapacity = 4000;
            size = 4;
            unitCapModifier = 6;
        }};
        //distribution
        zincDuct = new Duct("zinc-duct"){{
            requirements(Category.distribution, with(StuasutItems.zinc, 1));
            health = 75;
            speed = 6f;
            solid = false;
        }};
        bariumDuct = new Duct("barium-duct"){{
           requirements(Category.distribution, with(StuasutItems.barium, 1, StuasutItems.zinc, 1));
           health = 90;
           speed = 5f;
           solid = false;
        }};
        zincBridge = new BufferedItemBridge("zinc-bridge"){{
            requirements(Category.distribution, with(StuasutItems.zinc, 4));
            fadeIn = moveArrows = false;
            range = 5;
            speed = 60f;
            arrowSpacing = 5f;
            bufferCapacity = 4;
            researchCost = with(StuasutItems.zinc, 16);
        }};
    }
}
