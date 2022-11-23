package stus.content;

import mindustry.type.*;

import static stus.content.StuasutPlanets.*;

public class StuasutSectors {
    public static SectorPreset
    oldConstruction;
    public static void load() {
        oldConstruction = new SectorPreset("old-construction", rapu, 135) {{
            captureWave = 25;
            difficulty = 5;
            startWaveTimeMultiplier = 3f;
        }};
    }
}
