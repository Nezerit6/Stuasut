package stus.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import stus.graphics.*;
import stus.world.blocks.effects.*;
import stus.world.blocks.storage.*;
import multicraft.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;


public class StuasutBlocks {
    public static Block
            //environment
            gertwall, limestonewall, gert, limestone, mercurymud, slateWall, slate,

    //boulders
    gertBoulder, limestoneBoulder,
    //ores
    oreZinc, oreBarium, oreCadmium, oreRhenium, oreAntimony,

    //liquid
    galliumPuddle, diethylEtherPuddle, zincPump, anodizedConduit, anodizedRouter, anodizedJunction, anodizedBridge, zincLiquidContainer, zincLiquidTank,

    //defence
    bariumWall, bariumWallLarge, cadmiumWall, cadmiumWallLarge, rheniumWall, rheniumWallLarge,

    //crafting
    forgeT1, forgeT2,

    //furnace

    //production
    zinccrusher, impulseCrusher,

    //effects
    polygonForceProjector,

    //power
    windgenerator, convector, zincbattery, zincbatterylarge, zincnode, zincnodelarge,

    //storage
    coreDawn,

    //distribution
    zincBridge, zincDuct, bariumDuct, itemRouter,

    //turrets
    clor, togis, pulse, collapse, landAir, landAirLaser, landAirMinigun,

    //units
    airFactory;

    public static void load() {
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
        slateWall = new StaticWall("slate-wall") {{
            variants = 2;
        }};
        slate = new Floor("slate") {{
            variants = 3;
        }};
        //boulders
        gertBoulder = new Prop("gert-boulder") {{
            variants = 2;
        }};
        limestoneBoulder = new Prop("limestone-boulder") {{
            variants = 2;
        }};
        //ores
        oreZinc = new OreBlock(StuasutItems.zinc) {{
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        oreBarium = new OreBlock(StuasutItems.bariumraw) {{
            oreThreshold = 0.828f;
            oreScale = 23.952381f;
        }};
        oreCadmium = new OreBlock(StuasutItems.cadmiumraw) {{
            oreThreshold = 0.864f;
            oreScale = 24.904762f;
        }};
        oreRhenium = new OreBlock(StuasutItems.rheniumraw) {{
            oreThreshold = 0.864f;
            oreScale = 24.904762f;
        }};
        oreAntimony = new OreBlock(StuasutItems.antimonyraw) {{
            oreThreshold = 0.882f;
            oreScale = 25.380953f;
        }};
        //liquid
        galliumPuddle = new Floor("gallium-puddle") {{
            speedMultiplier = 0.55f;
            variants = 0;
            drownTime = 110f;

            status = StuasutStatus.inGallium;
            statusDuration = 15f * 60f;

            liquidDrop = Liquids.gallium;
            isLiquid = true;
            liquidMultiplier = 0.8f;
            cacheLayer = CacheLayer.tar;
        }};
        diethylEtherPuddle = new Floor("diethyl-ether-puddle") {{
            speedMultiplier = 0.35f;
            variants = 0;
            drownTime = 110f;

            status = StuasutStatus.inDiethylEther;
            statusDuration = 15f * 60f;

            liquidDrop = StuasutLiquids.diethylEther;
            isLiquid = true;
            liquidMultiplier = 1f;
            cacheLayer = CacheLayer.water;
        }};
        zincPump = new Pump("zinc-pump") {{
            requirements(Category.liquid, with(StuasutItems.zinc, 15, StuasutItems.barium, 18));
            pumpAmount = 12f / 60f;
            liquidCapacity = 24f;
            size = 1;
        }};
        anodizedConduit = new Conduit("anodized-conduit") {{
            requirements(Category.liquid, with(StuasutItems.barium, 1));
            buildCost = 0.01f / 60f;
            liquidCapacity = 24f;
            liquidPressure = 1.1f;
            health = 175;
        }};
        anodizedRouter = new LiquidRouter("anodized-liquid-router") {{
            requirements(Category.liquid, with(StuasutItems.zinc, 8, StuasutItems.barium, 5));
            buildCost = 0.01f / 60f;
            liquidCapacity = 38f;
            liquidPadding = 3f / 4f;
            underBullets = true;
            solid = false;
            health = 85;
        }};
        anodizedJunction = new LiquidJunction("anodized-liquid-junction") {{
            requirements(Category.liquid, with(StuasutItems.zinc, 5, StuasutItems.barium, 8));
            buildCost = 0.01f / 60f;
            health = 75;
            researchCostMultiplier = 2;
            solid = false;
            underBullets = true;

            ((Conduit) StuasutBlocks.anodizedConduit).junctionReplacement = this;
        }};
        anodizedBridge = new DirectionLiquidBridge("anodized-bridge-conduit") {{
            requirements(Category.liquid, with(StuasutItems.zinc, 20, StuasutItems.barium, 10));
            range = 6;
            health = 95;
            hasPower = false;
            researchCostMultiplier = 2;
            //I just hate these bridge conduit on Erekir and decided to make them not solid
            solid = false;
            underBullets = true;

            ((Conduit) StuasutBlocks.anodizedConduit).rotBridgeReplacement = this;
        }};
        zincLiquidContainer = new LiquidRouter("zinc-liquid-containter") {{
            requirements(Category.liquid, with(StuasutItems.zinc, 25, StuasutItems.bariumraw, 15, StuasutItems.barium, 12));
            liquidCapacity = 1250f;
            size = 2;
            liquidPadding = 6f / 4f;
            researchCostMultiplier = 4;
            solid = true;
        }};
        zincLiquidTank = new LiquidRouter("zinc-liquid-tank") {{
            requirements(Category.liquid, with(StuasutItems.zinc, 50, StuasutItems.bariumraw, 30, StuasutItems.barium, 24));
            liquidCapacity = 3000f;
            size = 3;
            liquidPadding = 2f;
            researchCostMultiplier = 3;
            solid = true;
        }};

        //defence
        bariumWall = new Wall("barium-wall") {{
            requirements(Category.defense, with(StuasutItems.barium, 6));
            health = 80 * 6;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
        }};
        bariumWallLarge = new Wall("barium-wall-large") {{
            requirements(Category.defense, with(StuasutItems.barium, 24));
            health = 80 * 6 * 2;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
            size = 2;
        }};
        cadmiumWall = new Wall("cadmium-wall") {{
            requirements(Category.defense, with(StuasutItems.cadmium, 6));
            health = 80 * 10;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
        }};
        cadmiumWallLarge = new Wall("cadmium-wall-large") {{
            requirements(Category.defense, with(StuasutItems.cadmium, 24));
            health = 80 * 8 * 2;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
            size = 2;
        }};
        rheniumWall = new Wall("rhenium-wall") {{
            requirements(Category.defense, with(StuasutItems.rhenium, 6));
            health = 80 * 10;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
        }};
        rheniumWallLarge = new Wall("rhenium-wall-large") {{
            requirements(Category.defense, with(StuasutItems.rhenium, 24));
            health = 80 * 8 * 2;
            researchCostMultiplier = 0.1f;
            envDisabled |= Env.scorching;
            size = 2;
        }};

        //crafting
        forgeT1 = new MultiCrafter("forge-t1") {{
            requirements(Category.crafting, with(StuasutItems.zinc, 70, StuasutItems.bariumraw, 30));
            health = 80;
            size = 2;
            craftEffect = new MultiEffect(Fx.pointShockwave, Fx.ventSteam); //new MultiEffect(Fx.pointShockwave, Fx.pointShockwave, Fx.drillSteam);
            //can't make StuasutItems.antimony, balance
            resolvedRecipes = Seq.with(
                    //barium recipe
                    new Recipe(
                            //consumes
                            new IOEntry(
                                    //items
                                    Seq.with(ItemStack.with(
                                            StuasutItems.bariumraw, 4
                                    )),
                                    //liquids
                                    Seq.with(),
                                    90f / 60f),
                            //outputs
                            new IOEntry(
                                    //items
                                    Seq.with(ItemStack.with(
                                            StuasutItems.barium, 2
                                    )),
                                    //liquids
                                    Seq.with()),
                            //craftTime
                            3f * 60f
                    ),
                    //cadmium recipe
                    new Recipe(
                            //consumes
                            new IOEntry(
                                    //items
                                    Seq.with(ItemStack.with(
                                            StuasutItems.cadmiumraw, 4
                                    )),
                                    //liquids
                                    Seq.with(),
                                    180f / 60f),
                            //outputs
                            new IOEntry(
                                    //items
                                    Seq.with(ItemStack.with(
                                            StuasutItems.cadmium, 1
                                    )),
                                    //liquids
                                    Seq.with()),
                            //craftTime
                            3.5f * 60f
                    ),
                    //rhenium recipe
                    new Recipe(
                            //consumes
                            new IOEntry(
                                    //items
                                    Seq.with(ItemStack.with(
                                            StuasutItems.rheniumraw, 4
                                    )),
                                    //liquids
                                    Seq.with(),
                                    360f / 60f),
                            //outputs
                            new IOEntry(
                                    //items
                                    Seq.with(ItemStack.with(
                                            StuasutItems.rhenium, 1
                                    )),
                                    //liquids
                                    Seq.with()),
                            //craftTime
                            3.75f * 60f
                    ));
            itemCapacity = 10;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());
        }};

        //production

        zinccrusher = new BurstDrill("zinc-crusher") {{
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
        impulseCrusher = new BurstDrill("impulse-crusher") {{
            requirements(Category.production, with(StuasutItems.zinc, 50, StuasutItems.barium, 25, StuasutItems.cadmiumraw, 15));
            drillTime = 60f * 8f;
            size = 5;
            tier = 4;
            shake = 5f;
            itemCapacity = 150;

            consumePower(270f / 60f);
            consumeLiquid(Liquids.gallium, 6f / 60f);

            drillEffect = new MultiEffect(Fx.mineImpact, Fx.drillSteam, Fx.mineImpactWave.wrap(Pal.redLight, 40f));
            //TODO remake drillEffect, after make sprites
        }};

        //effects
        polygonForceProjector = new PolyForceProjector("polygon-force-projector") {{
            requirements(Category.effect, with());
            size = 3;
            shieldHealth = 800f;
            hasPower = true;
            consumesPower = true;
            consumePower(420f / 60f);
            cooldownNormal = 1f;
            polygon = new float[]{
                    //right side
                    0f, -64f,
                    24f, -56f,
                    40f, -40f,
                    56f, -8f,
                    56f, 64f,
                    40f, 64f,
                    32f, 56f,
                    16f, 56f,
                    8f, 64f,
                    //left side
                    -8f, 64f,
                    -16f, 56f,
                    -32f, 56f,
                    -40f, 64f,
                    -56f, 64f,
                    -56f, -8f,
                    -40f, -40f,
                    -24f, -56f,
                    0f, -64f
            };
        }};

        //power

        windgenerator = new ConsumeGenerator("wind-generator") {{
            size = 2;
            requirements(Category.power, with(StuasutItems.zinc, 20, StuasutItems.bariumraw, 8));
            powerProduction = 1f;
            drawer = new DrawMulti(new DrawDefault(),
                    new DrawRegion("-rotator", 0.4f * 9f));
        }};
        convector = new ImpactReactor("convector") {{
            requirements(Category.power, with(StuasutItems.zinc, 45, StuasutItems.bariumraw, 20, StuasutItems.barium, 30));
            powerProduction = 420f / 60f;
            health = 375;
            size = 3;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;
            warmupSpeed = 0.15f;

            consumePower(90f / 60f);
            consumeLiquid(StuasutLiquids.diethylEther, 12f / 60f);
        }};
        zincnode = new BeamNode("zinc-power-node") {{
            requirements(Category.power, with(StuasutItems.zinc, 5, StuasutItems.bariumraw, 2));
            consumesPower = outputsPower = true;
            consumePowerBuffered(750f);
            range = 12;
            health = 90;
            researchCost = with(StuasutItems.zinc, 40, StuasutItems.bariumraw, 16);
        }};
        zincnodelarge = new BeamNode("large-zinc-power-node") {{
            requirements(Category.power, with(StuasutItems.zinc, 10, StuasutItems.bariumraw, 5, StuasutItems.barium, 2));
            consumesPower = outputsPower = true;
            consumePowerBuffered(1500f);
            range = 20;
            health = 185;
        }};
        //storage

        coreDawn = new RegenCore("core-dawn") {{
            requirements(Category.effect, with(StuasutItems.zinc, 2000, StuasutItems.bariumraw, 800));
            alwaysUnlocked = true;

            drawArrow = false;
            isFirstTier = true;
            unitType = StuasutUnits.sunrise;
            health = 3000;
            itemCapacity = 4000;
            size = 4;
            unitCapModifier = 6;
        }};
        //distribution
        zincDuct = new Duct("zinc-duct") {{
            requirements(Category.distribution, with(StuasutItems.zinc, 1));

            hasPower = consumesPower = conductivePower = true;

            health = 75;
            speed = 5f;
            solid = false;
            researchCost = with(StuasutItems.zinc, 10);
        }};
        itemRouter = new DuctRouter("item-router") {{
            requirements(Category.distribution, with(StuasutItems.zinc, 6, StuasutItems.bariumraw, 4));

            hasPower = consumesPower = conductivePower = true;

            speed = 3.5f;
            regionRotated1 = 1;
            solid = false;
            researchCost = with(StuasutItems.zinc, 30, StuasutItems.bariumraw, 20);
        }};
        bariumDuct = new Duct("barium-duct") {{
            requirements(Category.distribution, with(StuasutItems.barium, 1, StuasutItems.zinc, 1));

            hasPower = consumesPower = conductivePower = true;

            health = 90;
            speed = 4f;
            solid = false;
        }};
        zincBridge = new ItemBridge("zinc-bridge") {{
            requirements(Category.distribution, with(StuasutItems.zinc, 4));
            hasPower = false;
            fadeIn = moveArrows = false;
            solid = false;
            range = 5;
            arrowSpacing = 5f;
            researchCost = with(StuasutItems.zinc, 20);
        }};
        clor = new ItemTurret("clor") {{
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
                    StuasutItems.zinc, new RailBulletType() {{
                        length = 90f;
                        ammoMultiplier = 2.4f;
                        damage = 16f;
                        pierce = false;
                        hitColor = Color.valueOf("feb380");
                        hitEffect = endEffect = Fx.hitBulletColor;
                        ;
                        lineEffect = new Effect(20f, e -> {
                            if (!(e.data instanceof Vec2 v)) return;

                            color(Color.valueOf("8ca0b3ff"));
                            stroke(e.fout() * 0.9f + 0.6f);

                            Fx.rand.setSeed(e.id);
                            for (int i = 0; i < 7; i++) {
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
        togis = new ItemTurret("togis") {{
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
                    StuasutItems.bariumraw, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 9;
                        fragVelocityMin = 0.7f;
                        fragRandomSpread = 70;
                        fragLifeMin = 0.6f;
                        fragBullet = new BasicBulletType() {{
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
        pulse = new ItemTurret("pulse") {{
            requirements(Category.turret, with(StuasutItems.zinc, 90, StuasutItems.bariumraw, 40));
            scaledHealth = 210;
            reload = 35f;
            range = 260f;
            recoil = 2f;
            shootCone = 40f;
            rotateSpeed = 2.6f;
            size = 2;
            ammo(
                    StuasutItems.barium, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 4;
                        fragVelocityMin = 0.7f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.6f;
                        fragBullet = new BasicBulletType() {{
                            damage = 18f;
                            speed = 4f;
                            lifetime = 110f;
                            width = 8f;
                            height = 8f;
                        }};
                    }},
                    StuasutItems.dencealloy, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 4;
                        fragVelocityMin = 0.2f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.6f;
                        fragBullet = new ArtilleryBulletType() {{
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
        collapse = new ItemTurret("collapse") {{
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
                    /*StuasutItems.barium, new BasicBulletType() {{
                        shootEffect = Fx.shootPyraFlame;
                        smokeEffect = Fx.shootSmokeTitan;
                        hitColor = Pal.surge;

                        sprite = "large-bomb";
                        trailEffect = Fx.missileTrailShort;
                        trailInterval = 3f;
                        trailParam = 4f;
                        fragOnHit = true;
                        speed = 4.5f;
                        damage = 125f;
                        lifetime = 90f;
                        width = height = 16f;
                        backColor = Color.valueOf("ddba5fff");
                        frontColor = Color.white;
                        shrinkX = shrinkY = 0f;
                        trailColor = Color.valueOf("ddba5fff");
                        trailLength = 12;
                        trailWidth = 2.2f;
                        despawnEffect = new ExplosionEffect() {{
                            waveColor = Color.valueOf("ddba5fff");
                            smokeColor = Pal.lightOrange;
                            waveStroke = 3f;
                            waveRad = 60f;
                        }};
                        despawnSound = hitSound = Sounds.explosion;

                        shootSound = Sounds.shootBig;
                    }},//TODO finish it later */
                    StuasutItems.barium, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 10;
                        fragVelocityMin = 0.7f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.6f;
                        fragBullet = new BasicBulletType() {{
                            damage = 56f;
                            speed = 4f;
                            lifetime = 110f;
                            width = 12f;
                            height = 12f;
                        }};
                    }},
                    StuasutItems.dencealloy, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        fragBullets = 10;
                        fragVelocityMin = 0.4f;
                        fragRandomSpread = 0;
                        fragLifeMin = 0.9f;
                        fragBullet = new BasicBulletType() {{
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
        landAir = new ItemTurret("land-air") {{
            requirements(Category.turret, with(StuasutItems.zinc, 160, StuasutItems.barium, 120, StuasutItems.bariumraw, 90));
            scaledHealth = 250;
            reload = 80f;
            range = 100f;
            recoil = 2f;
            rotateSpeed = 2f;
            size = 3;
            ammo(
                    StuasutItems.barium, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        spawnUnit = new MissileUnitType("basic-sentinel") {{

                            speed = 1.4f;
                            rotateSpeed = 4f;
                            maxRange = 40f;
                            lifetime = 60f * 3.2f;
                            health = 200;
                            loopSoundVolume = 0.1f;
                            constructor = TimedKillUnit::create;
                            collidesTeam = true;

                            weapons.add(new Weapon("stus-basic-sentinel-weapon") {{
                                x = 0f;
                                rotate = true;
                                rotateSpeed = 8f;
                                mirror = false;
                                reload = 30f;
                                bullet = new BasicBulletType() {{
                                    damage = 8f;
                                    speed = 4f;
                                    lifetime = 10f;
                                    width = 5f;
                                    height = 7f;
                                }};
                            }});
                        }};
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 800, StuasutItems.barium, 600, StuasutItems.bariumraw, 450);
        }};
        landAirLaser = new ItemTurret("land-air-laser") {{
            requirements(Category.turret, with(StuasutItems.zinc, 180, StuasutItems.cadmium, 80, StuasutItems.cadmiumraw, 85));
            scaledHealth = 250;
            reload = 140f;
            range = 100f;
            recoil = 2f;
            rotateSpeed = 2f;
            size = 3;
            ammo(
                    StuasutItems.cadmium, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        spawnUnit = new MissileUnitType("laser-sentinel") {{

                            speed = 1.55f;
                            rotateSpeed = 4f;
                            maxRange = 40f;
                            lifetime = 60f * 3.2f;
                            health = 160;
                            loopSoundVolume = 0.1f;
                            constructor = TimedKillUnit::create;
                            collidesTeam = true;

                            weapons.add(new Weapon("stus-laser-sentinel-weapon") {{
                                x = 0f;
                                rotate = true;
                                rotateSpeed = 8f;
                                mirror = false;
                                reload = 60f;
                                continuous = true;
                                bullet = new ContinuousLaserBulletType() {{
                                    damage = 4f;
                                    speed = 0f;
                                    lifetime = 60f;
                                    width = 2f;
                                    length = 40f;
                                    pierceCap = 1;

                                }};
                            }});
                        }};
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 900, StuasutItems.cadmium, 400, StuasutItems.cadmiumraw, 425);
        }};
        landAirMinigun = new ItemTurret("land-air-minigun") {{
            requirements(Category.turret, with(StuasutItems.zinc, 260, StuasutItems.cadmium, 80, StuasutItems.rheniumraw, 120));
            scaledHealth = 250;
            reload = 120f;
            range = 100f;
            recoil = 2f;
            rotateSpeed = 2f;
            size = 3;
            ammo(
                    StuasutItems.rheniumraw, new BasicBulletType() {{
                        damage = 0f;
                        lifetime = 0f;
                        speed = 1000f;
                        spawnUnit = new MissileUnitType("minigun-sentinel") {{
                            speed = 1.15f;
                            rotateSpeed = 4f;
                            maxRange = 50f;
                            lifetime = 60f * 4.6f;
                            health = 300;
                            loopSoundVolume = 0.1f;
                            constructor = TimedKillUnit::create;
                            collidesTeam = true;

                            weapons.add(new Weapon("stus-minigun-sentinel-weapon") {{
                                x = 0f;
                                rotate = true;
                                rotateSpeed = 8f;
                                mirror = false;
                                reload = 10f;
                                bullet = new BasicBulletType() {{
                                    damage = 5f;
                                    speed = 5f;
                                    lifetime = 10f;
                                    width = 5f;
                                    height = 7f;
                                }};
                            }});
                        }};
                    }});
            drawer = new DrawTurret("rapu-");
            researchCost = with(StuasutItems.zinc, 1300, StuasutItems.cadmium, 400, StuasutItems.rheniumraw, 600);
        }};
        //units
        airFactory = new UnitFactory("air-fuck") {{
            requirements(Category.units, with(StuasutItems.zinc, 130, StuasutItems.barium, 70));
            size = 3;
            plans.add(new UnitPlan(StuasutUnits.navicula, 60f * 34f, with(StuasutItems.zinc, 30, StuasutItems.barium, 20)));
            researchCostMultiplier = 0.7f;
            consumePower(2.6f);
        }};
    }
}
