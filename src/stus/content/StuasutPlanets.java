package stus.content;

import arc.graphics.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import stus.planets.*;

public class StuasutPlanets {
    public static Planet
    rapu;
    public static void load(){
        Planets.serpulo.hiddenItems.addAll(Vars.content.items()).removeAll(Items.serpuloItems);
        Planets.erekir.hiddenItems.addAll(Vars.content.items()).removeAll(Items.erekirItems);
         rapu = new Planet("rapu", Planets.sun, 1f, 3){{
            defaultCore = StuasutBlocks.coreDawn;
            sectorSeed = 3;
            generator = new RapuPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                         new HexSkyMesh(this, 6, 0.1f, 0.23f, 5, Color.valueOf("393939").a(0.75f), 2, 0.45f, 1.13f, 0.45f),
                         new HexSkyMesh(this, 3, 0.2f, 0.19f, 5, Color.valueOf("555555").a(0.65f), 3, 0.25f, 1.22f, 0.45f));
            iconColor = StuasutBlocks.gert.mapColor;
            accessible = true;
            alwaysUnlocked = true;
            atmosphereColor = Color.valueOf("B79E54");
            startSector = 135;
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.3f;
            clearSectorOnLose = true;
            ruleSetter = r -> {
                r.loadout = ItemStack.list(StuasutItems.zinc, 140, StuasutItems.bariumraw, 50);
                r.waveTeam = Team.green;
                r.attributes.clear();
                r.showSpawns = true;
                r.fog = false;
                r.onlyDepositCore = false;
            };
            unlockedOnLand.add(StuasutBlocks.coreDawn);
            hiddenItems.addAll(Vars.content.items()).removeAll(StuasutItems.rapuItems);
        }};
    }
}
