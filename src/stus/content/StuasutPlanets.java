package stus.content;

import arc.graphics.*;
import stus.graphics.*;
import stus.world.meta.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.Team;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import stus.planets.*;

import static stus.content.StuasutItems.StuasutItems;


public class StuasutPlanets {
    public static Planet
    //Present time
    rapu, deathPlanet,
    oldSun, oldSerpulo, oldErekir, oldTantros, oldRapu;
    public static void load(){
         rapu = new Planet("rapu", Planets.sun, 1f, 3){{
            defaultCore = StuasutBlocks.coreDawn;
            sectorSeed = 3;
            generator = new RapuPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
             cloudMeshLoader = () -> new MultiMesh(
                     new HexSkyMesh(this, 6, 0.1f, 0.23f, 5, Color.valueOf("393939").a(0.75f), 2, 0.45f, 1.13f, 0.45f),
                     new HexSkyMesh(this, 3, 0.2f, 0.19f, 5, Color.valueOf("555555").a(0.65f), 3, 0.25f, 1.22f, 0.45f),
                     new HexSkyMesh(this, 2, 0.3f, 0.27f, 5, Color.valueOf("686868").a(0.55f), 4, 0.35f, 1.35f, 0.45f)
             );
             iconColor = RapuPal.rapuOutline;
            accessible = true;
            alwaysUnlocked = true;
            atmosphereColor = Color.valueOf("393939");
            startSector = 16;
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.3f;
            clearSectorOnLose = true;
            ruleSetter = r -> {
                r.loadout = ItemStack.list(StuasutItems.oldmateria, 160);
//                r.waveTeam = StuasutTeams.decayed;
                r.attributes.clear();
//                r.attributes.set(DecalingAttributes.timedrive, 0.4f);
                r.showSpawns = true;
                r.fog = false;
                r.onlyDepositCore = false;
            };
             unlockedOnLand.add(StuasutBlocks.coreDawn);
        }};
        oldSun = new Planet("old-sun", null, 4f){{
            radius = 4f;
            bloom = true;
            accessible = false;

            meshLoader = () -> new SunMesh(
                    this, 4,
                    5, 0.3, 1.4, 1.2, 1,
                    1f,
                    Color.valueOf("ff7a38"),
                    Color.valueOf("ff9638"),
                    Color.valueOf("ffc64c"),
                    Color.valueOf("ffc64c"),
                    Color.valueOf("ffe371"),
                    Color.valueOf("f4ee8e")
            );
        }};
        oldErekir = new Planet("old-erekir", oldSun, 1f, 2){{
            generator = new ErekirPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 2, 0.15f, 0.14f, 5, Color.valueOf("eba768").a(0.75f), 2, 0.42f, 1f, 0.43f),
                    new HexSkyMesh(this, 3, 0.6f, 0.15f, 5, Color.valueOf("eea293").a(0.75f), 2, 0.42f, 1.2f, 0.45f)
            );
            alwaysUnlocked = false;
            landCloudColor = Color.valueOf("ed6542");
            atmosphereColor = Color.valueOf("f07218");
            defaultEnv = Env.scorching | Env.terrestrial;
            startSector = 10;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            tidalLock = true;
            orbitSpacing = 2f;
            totalRadius += 2.6f;
            lightSrcTo = 0.5f;
            lightDstFrom = 0.2f;
            clearSectorOnLose = true;
            defaultCore = Blocks.coreBastion;
            hiddenItems.addAll(Items.serpuloItems).removeAll(Items.erekirItems);

            updateLighting = false;

            ruleSetter = r -> {
                r.waveTeam = Team.malis;
                r.placeRangeCheck = true;
                r.attributes.set(Attribute.heat, 0.8f);
                r.showSpawns = true;
                r.fog = true;
                r.staticFog = true;
                r.lighting = false;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };

            unlockedOnLand.add(Blocks.coreBastion);
        }};
        oldSerpulo = new Planet("old-serpulo", oldSun, 1f, 3){{
            generator = new stus.planets.OldSerpuloGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Pal.heal).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.heal, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            iconColor = Pal.heal;
            alwaysUnlocked = false;
            launchCapacityMultiplier = 0.5f;
            sectorSeed = 2;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            prebuildBase = false;
            ruleSetter = r -> {
//                r.waveTeam = DecalingTeams.oldCrux;
                r.placeRangeCheck = false;
                r.attributes.clear();
                r.showSpawns = true;
            };
            atmosphereColor = Pal.heal;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 43;
            landCloudColor = Pal.heal.cpy().a(0.5f);
            hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
        }};

        oldTantros = new Planet("old-tantros", oldSun, 1f, 3){{
            generator = new OldTantrosGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 6, new Color().set(Color.white).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 6, Color.white.cpy().lerp(Color.cyan, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            iconColor = Color.cyan;
            alwaysUnlocked = false;
            sectorSeed = 10;
            allowWaves = true;
            enemyCoreSpawnReplace = true;
            prebuildBase = false;
            clearSectorOnLose = true;
            ruleSetter = r -> {
                r.placeRangeCheck = false;
                r.attributes.clear();
                r.waveTeam = DecalingTeams.shared;
                r.defaultTeam = Team.sharded;
                r.showSpawns = true;
                r.fog = false;
            };
            atmosphereColor = Color.cyan;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 43;
            landCloudColor = Color.cyan.cpy().a(0.5f);
            defaultEnv = DecalingEnv.icy | Env.terrestrial;
        }};
        decalin.hiddenItems.addAll(Vars.content.items()).removeAll(decalinItems);
}}