package stus.planets;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.noise.*;
import stus.content.*;
import mindustry.ai.*;
import mindustry.ai.Astar.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class RapuPlanetGenerator extends PlanetGenerator {

    String launchSchem = "bXNjaAF4nGNgYWBhZmDJS8xNZeDNyU9MyS8tCU7OSAVyuVNSi5OLMgtKMvPzGBgY2HISk1JzihmYomMZGfiKS0qLdZPzi1J1UxLLQdKMIMTIwAwAfS0Ufg==";

    RapuBase basegen = new RapuBase();
    public static final int seed = 29;
    public static int widthSeed = 1, heightSeed = 2, roomSeed = 3, strokeSeed = 4;

	public Block[] arr = {
			StuasutBlocks.gert,
			StuasutBlocks.limestone,
			StuasutBlocks.limestone,
			StuasutBlocks.gert,
			Blocks.stone,
			StuasutBlocks.gert,
			StuasutBlocks.mercurymud,
			StuasutBlocks.mercurymud,
	};
	{
		defaultLoadout = StuasutSchematics.basicDawn;
	}
	ObjectMap<Block, Block> dec = ObjectMap.of(
			StuasutBlocks.gert, StuasutBlocks.gertBoulder,
			StuasutBlocks.limestone, StuasutBlocks.limestoneBoulder
	);

    float rawHeight(Vec3 pos) {
        return Simplex.noise3d(seed, 13, 0.6f, 0.9f, pos.x, pos.y, pos.z);
    }

    float humidity(Vec3 pos) {
        return Simplex.noise3d(13, 7, 0.5f, 0.5f, pos.x, pos.y, pos.z);
    }

    Block getBlock(Vec3 pos) {
        float height = 1 - rawHeight(pos);
        float humidity = humidity(pos);
        return arr[Mathf.clamp((int) (height + humidity * arr.length), 0, arr.length - 1)];
    }

    Block getBlock(float x, float y, float z) {
        Vec3 pos = new Vec3(x, y, z);
        float height = 1 - rawHeight(pos);
        float humidity = humidity(pos);
        return arr[Mathf.clamp((int) (height + humidity * arr.length), 0, arr.length - 1)];
    }

    @Override
    public float getHeight(Vec3 pos) {
        return Math.max(0.1f, rawHeight(pos));
    }

    @Override
    public void generateSector(Sector sector) {
        if (sector.id == 226 || sector.id == 12) {
            sector.generateEnemyBase = true;
            return;
        }
        Ptile tile = sector.tile;

        boolean any = false;
        float poles = Math.abs(tile.v.y);
        float noise = Noise.snoise3(tile.v.x, tile.v.y, tile.v.z, 0.001f, 0.6f);

        if (noise + poles / 7.2 > 0.12 && poles > 0.23) {
            any = true;
        }
        if (noise < 0.17) {
            for (Ptile other : tile.tiles) {
                var osec = sector.planet.getSector(other);

                //no sectors near start sector!
                if (
                        osec.id == sector.planet.startSector || //near starting sector
                                osec.generateEnemyBase && poles < 0.85 || //near other base
                                (sector.preset != null && noise < 0.11) //near preset
                ) {
                    return;
                }
            }
        }

        sector.generateEnemyBase = any;
    }

    @Override
    public Color getColor(Vec3 pos) {
        return getBlock(pos).mapColor;
    }

    @Override
    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag) {
        return Simplex.noise2d(seed, octaves, falloff, 1f / scl, x, y) * (float) mag;
    }

    protected float noise3d(int seed, Vec3 p, double octaves, double falloff, double scl, double mag) {
        return Simplex.noise3d(seed, octaves, falloff, 1f / scl, p.x, p.y, p.z) * (float) mag;
    }

    @Override
    public Seq<Tile> pathfind(int startX, int startY, int endX, int endY, TileHueristic th, DistanceHeuristic dh) {
        return Astar.pathfind(startX, startY, endX, endY, th, dh, tile -> true);
    }

    @Override
    protected void generate() {

        Vec2 pos = new Vec2();
        Seq<Room> r = new Seq<>();
        Seq<Room> roomseq = new Seq<>();
        float maxd = Mathf.dst(width / 2f, height / 2f);

        // enemy and player rooms
        Vec2 trns = Tmp.v1.trns(rand.random(360f), width / 2.6f);
        int
                spawnX = (int) (trns.x + width / 2f), spawnY = (int) (trns.y + height / 2f),
                launchX = (int) (-trns.y + width / 2f), launchY = (int) (-trns.y + height / 2f);
        r.add(
                new Room(
                        spawnX,
                        spawnY,
                        (int) (10f + noise3d(strokeSeed * 90, sector.tile.v, 1, 1, 1f, 5))
                ),
                new Room(
                        launchX,
                        launchY,
                        (int) (10f + noise3d(strokeSeed * 96, sector.tile.v, 1, 1, 1f, 5))
                )
        );

        // floor
        pass((x, y) -> {
            floor = getBlock(x / (width * 0.5f), y / (height * 0.5f), sector.tile.v.z);
        });

        pass((x, y) -> {
            if (block == Blocks.air) {
                block = floor.asFloor().wall;
            }

            //decoration
            if (rand.chance(0.01) && block == Blocks.air) {
                block = dec.get(floor, floor.asFloor().decoration);
            }

            //gallium
            Block[] liquidsTiles = new Block[]{StuasutBlocks.gert, StuasutBlocks.mercurymud, StuasutBlocks.limestone, StuasutBlocks.slate};
            for (Block b : liquidsTiles) {
                if (floor == b) {
                    if (Math.abs(0.5f - noise(x - 40, y, 2, 0.7, 80)) > 0.25f &&
                            Math.abs(0.5f - noise(x, y + sector.id * 10, 1, 1.5f, 80)) > 0.41f && !(roomseq.contains(t -> Mathf.within(x, y, t.x, t.y, 15)))) {
                        floor = StuasutBlocks.galliumPuddle;
                    }
                }
            }

            //diethyl ether
            if (floor == StuasutBlocks.gert) {
                if (Math.abs(0.5f - noise(x - 40, y, 2, 0.7, 80)) > 0.25f &&
                        Math.abs(0.5f - noise(x, y + sector.id * 10, 1, 1.5f, 80)) > 0.41f && !(roomseq.contains(t -> Mathf.within(x, y, t.x, t.y, 15)))) {
                    floor = StuasutBlocks.diethylEtherPuddle;
                }
            }
        });

        // create rooms
        for (int i = 0; i < 7; i++) {
            pos.set(
                    Mathf.clamp((int) noise3d(widthSeed * i, sector.tile.v, 1, 1, 1f, width), 20, width - 20),
                    Mathf.clamp((int) noise3d(heightSeed * i, sector.tile.v, 1, 1, 1f, height), 20, height - 20)
            );
            r.add(
                    new Room(
                            (int) pos.x,
                            (int) pos.y,
                            (int) (10f + noise3d(strokeSeed * i, sector.tile.v, 1, 1, 1f, 5))
                    )
            );
        }

        // connect rooms
        r.each(room -> {
            int roomId = 0;

            // get room to connect
            room.connect(
                    r.get(
                            (int) noise3d((int) roomSeed * roomId, sector.tile.v, 1, 1, 1f, r.size - 1)
                    )
            );

            // if it tries to connect to itself, it'll connect to spawn instead
            if (room.connected == room) room.connect(r.get(0));

            // actually connect the rooms
            room.open();
            if (room.isConnected()) {
                brush(pathfind(room.x, room.y, room.connected.x, room.connected.y, tile -> 5000f, Astar.manhattan), 20);
            }
            roomId++;
        });

        // make connections look more natural
        distort(130f, 76f);

        // make core and enemy area
        erase(spawnX, spawnY, 20);
        erase(launchX, launchY, 20);
        brush(pathfind(r.get(0).x, r.get(0).y, r.get(1).x, r.get(1).y, tile -> 5000f, Astar.manhattan), 20);

        // more roughness
        distort(136f, 31f);
        distort(10f, 12f);
        distort(6f, 7f);
        median(4);


        // ores
        Seq<Block> ores = Seq.with(StuasutBlocks.oreZinc, StuasutBlocks.oreBarium);
        float poles = 1f - Math.abs(sector.tile.v.y);
        float nmag = 0.5f;
        float scl = 1f;
        float addscl = 1.3f;
        if (Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x, sector.tile.v.y, sector.tile.v.z) * nmag + poles > 0.3f * addscl) {
            ores.add(StuasutBlocks.oreZinc);
        }
        if (Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x, sector.tile.v.y, sector.tile.v.z) * nmag + poles > 0.25f * addscl) {
            ores.add(StuasutBlocks.oreCadmium);
        }

        if (Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x + 1, sector.tile.v.y, sector.tile.v.z) * nmag + poles > 0.5f * addscl) {
            ores.add(StuasutBlocks.oreRhenium);
        }

        if (Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x + 2, sector.tile.v.y, sector.tile.v.z) * nmag + poles > 0.7f * addscl) {
            ores.add(StuasutBlocks.oreAntimony);
        }
        FloatSeq frequencies = new FloatSeq();
        for (int i = 0; i < ores.size; i++) {
            frequencies.add(rand.random(-0.01f, 0.07f) - i * 0.01f + poles * 0.04f);
        }

        pass((x, y) -> {
            if (!floor.asFloor().hasSurface()) return;

            float offsetX = x - 4, offsetY = y + 23;
            for (int i = ores.size - 1; i >= 0; i--) {
                Block entry = ores.get(i);
                float freq = frequencies.get(i);
                if (Math.abs(0.5 - noise(offsetX, offsetY + i * 999, 2, 0.7f, (40 + i * 2))) > 0.22f + i * 0.01 &&
                        Math.abs(0.5 - noise(offsetX, offsetY - i * 999, 1, 1, (30 + i * 4))) > 0.35f + freq) {
                    ore = entry;
                    break;
                }
            }
        });
		/*
		old ore system
		pass((x, y) -> {
			if (noise(x, y, 2, 0.3f, 30f, 1f) > 0.85f * poles && block == Blocks.air) ore = DecalingBlocks.oreFragment;

			if (noise(x, y, 3, 0.3f, 20f, 1f) > 0.75f * poles && block == Blocks.air) ore = DecalingBlocks.oreMateria;

			if (noise(x, y, 3, 0.2f, 20f, 1f) > 1.1f * poles && block == Blocks.air) ore = Blocks.air;
		});*/

        // put core and enemy spawn in the map
        Room spawn = null;
        Seq<Room> enemies = new Seq<>();
        int enemySpawns = rand.random(1, Math.max(Mathf.floor(sector.threat * 4), 1));
        int offset = rand.nextInt(360);
        float length = (float) (width / 2.55 - rand.random(13, 23));
        int angleStep = 5;

        for (int i = 0; i < 360; i += angleStep) {
            int angle = offset + i;
            int cx = (int) Math.floor(width / 2f + Angles.trnsx(angle, length));
            int cy = (int) Math.floor(height / 2f + Angles.trnsy(angle, length));

            if (i + angleStep >= 360) {
                spawn = new Room(cx, cy, rand.random(10, 18));
                r.add(spawn);

                for (int j = 0; j < enemySpawns; j++) {
                    float enemyOffset = rand.range(60);

                    Tmp.v1.set(cx - width / 2f, cy - height / 2f).rotate(180 + enemyOffset).add(width / 2f, height / 2f);
                    Room espawn = new Room((int) Math.floor(Tmp.v1.x), (int) Math.floor(Tmp.v1.y), rand.random(10, 16));
                    r.add(espawn);
                    enemies.add(espawn);
                }
                break;
            }
        }
        Schematics.placeLoadout(Schematics.readBase64(launchSchem), spawnX, spawnY, Team.sharded);

        tiles.getn(r.get(1).x, r.get(1).y).setOverlay(Blocks.spawn);

        if (sector.hasEnemyBase()) {
            basegen.generate(tiles, enemies.map(room -> tiles.getn(room.x, room.y)), tiles.get(spawnX, spawnY), state.rules.waveTeam, sector, sector.threat);
            state.rules.attackMode = sector.info.attack = true;
        } else {
            state.rules.winWave = sector.info.winWave = 10 + 5 * (int) Math.max(sector.threat * 10, 1);
        }

        state.rules.waveSpacing = Mathf.lerp(60 * 65 * 2, 60f * 60f * 1f, Math.max(sector.threat - 0.4f, 0f));
        state.rules.spawns = RapuWaves.generate(sector.threat, new Rand(), state.rules.attackMode);
        state.rules.winWave = sector.info.winWave = 10 + 5 * (int) Math.max(sector.threat * 12, 1);
        state.rules.waves = sector.info.waves = true;
        state.rules.env = sector.planet.defaultEnv;
    }

    @Override
    public void postGenerate(Tiles tiles) {
    }

    public class Room {
        int x, y, size;
        Room connected;

        public Room(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.connected = this;
        }

        public int getDistance(Room to) {
            int
                    distX = Math.abs(x - to.x),
                    distY = Math.abs(y - to.y);
            return (int) (distX + distY / 2f);
        }

        public boolean isConnected() {
            return connected != this;
        }

        public void open() {
            erase(x, y, size);
        }

        public void connect(Room to) {
            if (
                    to.connected == this ||
                            connected != this ||
                            getDistance(to) < size
            ) return;

            connected = to;
        }

    }

}