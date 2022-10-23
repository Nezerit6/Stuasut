package stus.content;
import arc.audio.Sound;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.Vec2;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import stus.graphics.*;
import stus.world.blocks.storage.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
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
    bariumForge,

    //furnace

    //production
    zinccrusher,impulseCrusher,

    //power
    windgenerator, zincbattery, zincbatterylarge, zincnode, zincnodelarge,

    //storage
    coreDawn,

    //distribution
    zincBridge, zincDuct, bariumDuct,

    //turrets
    clor, togis, pulse, collapse,

    //units
    airFactory;

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
           drownTime = 110f;

           status = StuasutStatus.inGallium;
           statusDuration = 15f * 60f;

           liquidDrop = Liquids.gallium;
           isLiquid = true;
           liquidMultiplier = 0.7f;
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
        bariumForge = new GenericCrafter("barium-forge"){{
            requirements(Category.crafting, with(StuasutItems.zinc, 70, StuasutItems.bariumraw, 30));
            health = 80;
            size = 3;
            craftEffect = new MultiEffect(Fx.pointShockwave, Fx.pointShockwave);
            outputItem = new ItemStack(StuasutItems.barium, 3);
            craftTime = 180f;
            itemCapacity = 10;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(2.3f);
            consumeItems(with(StuasutItems.bariumraw, 3));
        }};

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

        coreDawn = new RegenCore("core-dawn"){{
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
           speed = 4.5f;
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
        clor = new ItemTurret("clor"){{
            requirements(Category.turret, with(
                    StuasutItems.zinc, 60,
                    StuasutItems.bariumraw, 40
            ));
            scaledHealth = 189;
            size = 2;
            reload = 60f;
            range = 90f;
            recoil = 1.2f;
            shoot.shots = 4;
            shoot.shotDelay = 4f;
            coolant = consumeCoolant(0.2f);
            outlineColor = RapuPal.rapuOutline;
            ammo(
                    StuasutItems.zinc, new RailBulletType(){{
                        length = 90f;
                        ammoMultiplier = 2.4f;
                        damage = 16f;
                        pierce = false;
                        hitColor = Color.valueOf("feb380");
                        hitEffect = endEffect = Fx.hitBulletColor;;
                        lineEffect = new Effect(20f, e -> {
                            if(!(e.data instanceof Vec2 v)) return;

                            color(Color.valueOf("8ca0b3ff"));
                            stroke(e.fout() * 0.9f + 0.6f);

                            Fx.rand.setSeed(e.id);
                            for(int i = 0; i < 7; i++){
                                Fx.v.trns(e.rotation, Fx.rand.random(8f, v.dst(e.x, e.y) - 8f));
                                Lines.lineAngleCenter(e.x + Fx.v.x, e.y + Fx.v.y, e.rotation + e.finpow(), e.foutpowdown() * 20f * Fx.rand.random(0.5f, 1f) + 0.3f);
                            }

                            e.scaled(14f, b -> {
                                stroke(b.fout() * 1.5f);
                                color(Color.valueOf("8ca0b3ff"));
                                Lines.line(e.x, e.y, v.x, v.y);
                            });
                        });
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 200, StuasutItems.bariumraw, 150);
        }};
        togis = new ItemTurret("togis"){{
            requirements(Category.turret, with(StuasutItems.zinc, 50, StuasutItems.bariumraw, 20));
            scaledHealth = 180;
            reload = 30f;
            range = 100f;
            recoil = 2f;
            rotateSpeed = 2.6f;
            size = 2;
            inaccuracy = 19f;
            xRand = 4f;
            targetInterval = 5f;
            ammo(
                    StuasutItems.bariumraw, new BasicBulletType(){{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 9;
                        fragVelocityMin = 0.7f;
                        fragRandomSpread = 70;
                        fragLifeMin = 0.6f;
                        fragBullet =  new BasicBulletType(){{
                            damage = 15f;
                            speed = 4f;
                            lifetime = 36f;
                            pierceBuilding = true;
                            width = 9f;
                            height = 7f;
                        }};
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 250, StuasutItems.bariumraw, 110);
        }};
        pulse = new ItemTurret("pulse"){{
            requirements(Category.turret, with(StuasutItems.zinc, 90, StuasutItems.bariumraw, 40));
            scaledHealth = 210;
            reload = 35f;
            range = 260f;
            recoil = 2f;
            shootCone = 40f;
            rotateSpeed = 2.6f;
            size = 2;
            ammo(
                    StuasutItems.barium, new BasicBulletType(){{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 4;
                        fragVelocityMin = 0.7f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.6f;
                        fragBullet =  new BasicBulletType(){{
                            damage = 18f;
                            speed = 4f;
                            lifetime = 110f;
                            width = 8f;
                            height = 8f;
                        }};
                    }},
                    StuasutItems.dencealloy, new BasicBulletType(){{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 4;
                        fragVelocityMin = 0.2f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.6f;
                        fragBullet =  new ArtilleryBulletType(){{
                            damage = 29f;
                            speed = 3.5f;
                            lifetime = 130f;
                            width = 12f;
                            height = 12f;
                        }};
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 500, StuasutItems.bariumraw, 220);
        }};
        collapse = new ItemTurret("collapse"){{
            requirements(Category.turret, with(StuasutItems.zinc, 110, StuasutItems.barium, 50, StuasutItems.dencealloy, 35));
            scaledHealth = 240;
            reload = 130f;
            range = 260f;
            recoil = 2f;
            shootCone = 40f;
            rotateSpeed = 1.3f;
            size = 3;
            targetAir = false;
            ammo(
                    StuasutItems.barium, new BasicBulletType(){{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 10;
                        fragVelocityMin = 0.7f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.6f;
                        fragBullet =  new BasicBulletType(){{
                            damage = 56f;
                            speed = 4f;
                            lifetime = 110f;
                            width = 12f;
                            height = 12f;
                        }};
                    }},
                    StuasutItems.dencealloy, new BasicBulletType(){{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 10;
                        fragVelocityMin = 0.4f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.9f;
                        fragBullet =  new BasicBulletType(){{
                            damage = 63f;
                            speed = 3.5f;
                            lifetime = 130f;
                            width = 16f;
                            height = 16f;
                        }};
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 550, StuasutItems.barium, 250, StuasutItems.dencealloy, 175);
        }};
        airFactory = new UnitFactory("air-fuck"){{
            requirements(Category.units, with(StuasutItems.zinc, 130, StuasutItems.barium, 70));
            size = 3;
            plans.add(new UnitPlan(StuasutUnits.navicula, 60f * 34f, with(StuasutItems.zinc, 30, StuasutItems.barium, 20)));
            researchCostMultiplier = 0.7f;
            consumePower(2.6f);
        }};
    }
}
