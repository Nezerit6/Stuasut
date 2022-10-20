package stus.planets;

import arc.func.Cons;
import arc.func.Intc2;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.*;
import mindustry.ai.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.ai.BaseRegistry.BasePart;

public class RapuBase {
    public String[] schematics;
    public Seq<BasePart> coreParts = new Seq<>(), parts = new Seq<>();
    public ObjectMap<Content, Seq<BasePart>> reqParts = new ObjectMap<>();
    public static ObjectMap<Item, Block> uores = new ObjectMap<>();

    private Tiles tiles;
    private Seq<Tile> cores;
    private static final Vec2 axis = new Vec2(), rotator = new Vec2();
    //Slotterleet's code
    public RapuBase() {
        schematics = new String[]{
                //cores
                "bXNjaAF4nGNgYWBhZmDJS8xNZeDNyU9MyS8tCU7OSAVyuVNSi5OLMgtKMvPzGBgY2HISk1JzihmYomMZGfiKS0qLdZPzi1J1UxLLQdKMIMTIwAwAfS0Ufg==",
                "bXNjaAF4nGNgYWBhZmDJS8xNZeDNyU9MyS8tCU7OSAVyuVNSi5OLMgtKMvPzGBgY2HISk1JzihmYomMZGfiKS0qLdZPzi1J1UxLLQdKMIMTIwAwAfS0Ufg==",

                //defenses
                /*"bXNjaAF4nF2PwW6EMAxEBxIiw6bbU+/9Af6if1HtIQtphZTSCraq+vf1xOylgvDw2OMBnHF28Gv6yHh4yVP6fZ7zW16njNOc92lbvm7L5woglHTNZUf7evF4nHW0jPX5k0rB0z9hLGl715WHvC2l5O1eTuV7v2kJjEADr0fvDk3DHD1aiYk9xVavCm8IBjEMNHBHy1U6yzkPp7/FZawEjr1jMhIdDURkzBEaaHcKjxoTuCWovfYGQ7WL5YnlieWJ5YnlieWJGXrL6y1v0HdHeEMwiKH6IntERzHaJ0aOED3cH6eIKZg=",
                "bXNjaAF4nFWPQW7DIBBFP8YGh7SNKvUAWXbjTc6QW1RdUDOJLBG7wlar3D4zzKKqEHqa+Y8B8Iwni3aON8LhTGO8HxNdaB7peMI+0TqW6XublhmAy/GL8orm47PFW2I5D9t0o9+Y85BjuRJe/7pDKtMPFbz8F3HQet1iueRlSTz4nTcaGEGrcIpeYGrKCGgMLC8uG3QwAjal2YtuxRSh04oPVE9RZzi+ocKjztgpqunUdGIKdoqaec28Zl4zLxn0bsMti/qSTqtefxAEQb8YOLMCJ27Q9p4DK2gVTtHDPgACKCsZ",
                "bXNjaAF4nFVQ0WqEQAyc3fV07zy1hX7A/cA99QsK/YvSB6vrIexpUWnp3zezEUoVMmQy2WSCBo1DNrX3gOY1dO3PpQ9DmLpweUbZh7Vbxs9tnCcAeWw/Qlxh395zNL2I4zXF7zZG1Eps4z2k/Ol/fo3tcgt4/GOv/TJ+hQUPSnXzNCzztAmzv71u7TLEee5l9gu4AGAECqTPwxrJDySN1gxrBA9HOMJZ8E8hY4NVpVWlpZK1XVnCyTVYS3JI4G8OEo5KljAcapBmV3BpL87LlczZ5+QN6SMUfLPASSVnhb2vZkY/hmAUrMJZa5VCzQavSq9Kr0ovSkuoONiL0tC5SGjLwh0EMq504i40ktwexYqlUvro3LKh5NaE5LZUyZnWCIVm6fIVj0DIFQol9xoHyybp1nXyJCC1XwbxObk="*/

                //factory
        };

        for(String str : schematics){
            Schematic schem = Schematics.readBase64(str);

            BasePart part = new BasePart(schem);
            Tmp.v1.setZero();
            int drills = 0;

            for(Schematic.Stile tile : schem.tiles){
                //keep track of core type
                if(tile.block instanceof CoreBlock){
                    part.core = tile.block;
                }

                //save the required resource based on item source - multiple sources are not allowed
                if(tile.block instanceof ItemSource){
                    Item config = (Item)tile.config;
                    if(config != null) part.required = config;
                }

                //same for liquids - this is not used yet
                if(tile.block instanceof LiquidSource){
                    Liquid config = (Liquid)tile.config;
                    if(config != null) part.required = config;
                }

                //calculate averages
                if(tile.block instanceof Drill || tile.block instanceof Pump){
                    Tmp.v1.add(tile.x*tilesize + tile.block.offset, tile.y*tilesize + tile.block.offset);
                    drills ++;
                }
            }
            schem.tiles.removeAll(s -> s.block.buildVisibility == BuildVisibility.sandboxOnly);

            part.tier = schem.tiles.sumf(s -> Mathf.pow(s.block.buildCost / s.block.buildCostMultiplier, 1.4f));

            if(part.core != null){
                coreParts.add(part);
            }else if(part.required == null){
                parts.add(part);
            }

            if(drills > 0){
                Tmp.v1.scl(1f / drills).scl(1f / tilesize);
                part.centerX = (int)Tmp.v1.x;
                part.centerY = (int)Tmp.v1.y;
            }else{
                part.centerX = part.schematic.width/2;
                part.centerY = part.schematic.height/2;
            }

            if(part.required != null && part.core == null){
                reqParts.get(part.required, Seq::new).add(part);
            }

            coreParts.sort(b -> b.tier);
            parts.sort();
            reqParts.each((key, arr) -> arr.sort());
        }
    }

    public static Block getDifficultyWall(int size, float difficulty){
        Seq<Block> wallsSmall = content.blocks().select(b -> b instanceof Wall && b.size == size
                && !b.insulated && b.buildVisibility == BuildVisibility.shown
                && !(b instanceof Door)
                && !(Structs.contains(b.requirements, i -> state.rules.hiddenBuildItems.contains(i.item))));
        wallsSmall.sort(b -> b.buildCost);
        return wallsSmall.getFrac(difficulty * 0.91f);
    }

    public void generate(Tiles tiles, Seq<Tile> cores, Tile spawn, Team team, Sector sector, float difficulty){
        this.tiles = tiles;
        this.cores = cores;

        //don't generate bases when there are no loaded schematics
        if(coreParts.isEmpty()) return;

        Mathf.rand.setSeed(sector.id);

        float bracketRange = 0.17f;
        float baseChance = Mathf.lerp(0.7f, 2.1f, difficulty);
        int wallAngle = 70; //180 for full coverage
        double resourceChance = 0.5 * baseChance;
        double nonResourceChance = 0.0005 * baseChance;
        BasePart coreschem = coreParts.getFrac(difficulty);
        int passes = difficulty < 0.4 ? 1 : difficulty < 0.8 ? 2 : 3;

        Block wall = getDifficultyWall(1, difficulty), wallLarge = getDifficultyWall(2, difficulty);

        for(Tile tile : cores){
            tile.clearOverlay();
            Schematics.placeLoadout(coreschem.schematic, tile.x, tile.y, team, false);

            //fill core with every type of item (even non-material)
            Building entity = tile.build;
            for(Item item : content.items()){
                entity.items.add(item, entity.block.itemCapacity);
            }
        }

        for(int i = 0; i < passes; i++){
            //random schematics
            pass(tile -> {
                if(!tile.block().alwaysReplace) return;

                if(((tile.overlay().asFloor().itemDrop != null || tile.overlay() instanceof OreBlock || (tile.drop() != null && Mathf.chance(nonResourceChance)))
                        || (tile.floor().liquidDrop != null && Mathf.chance(nonResourceChance * 2))) && Mathf.chance(resourceChance)){
                    Seq<BasePart> parts = forResource(
                            tile.overlay() instanceof OreBlock uo ? uo.itemDrop :
                                    tile.drop() != null ? tile.drop() : tile.floor().liquidDrop
                    );
                    if(!parts.isEmpty()){
                        tryPlace(parts.getFrac(difficulty + Mathf.range(bracketRange)), tile.x, tile.y, team);
                    }
                }else if(Mathf.chance(nonResourceChance)){
                    tryPlace(parts.getFrac(difficulty + Mathf.range(bracketRange)), tile.x, tile.y, team);
                }
            });
        }

        //small walls
        pass(tile -> {

            if(tile.block().alwaysReplace){
                boolean any = false;

                for(Point2 p : Geometry.d4){
                    Tile o = tiles.get(tile.x + p.x, tile.y + p.y);

                    //do not block payloads
                    if(o != null && (o.block() instanceof PayloadConveyor || o.block() instanceof PayloadBlock)){
                        return;
                    }
                }

                for(Point2 p : Geometry.d8){
                    if(Angles.angleDist(Angles.angle(p.x, p.y), spawn.angleTo(tile)) > wallAngle){
                        continue;
                    }

                    Tile o = tiles.get(tile.x + p.x, tile.y + p.y);
                    if(o != null && o.team() == team && !(o.block() instanceof Wall)){
                        any = true;
                        break;
                    }
                }

                if(any){
                    tile.setBlock(wall, team);
                }
            }
        });

        //large walls
        pass(curr -> {
            int walls = 0;
            for(int cx = 0; cx < 2; cx++){
                for(int cy = 0; cy < 2; cy++){
                    Tile tile = tiles.get(curr.x + cx, curr.y + cy);
                    if(tile == null || tile.block().size != 1 || (tile.block() != wall && !tile.block().alwaysReplace)) return;

                    if(tile.block() == wall){
                        walls ++;
                    }
                }
            }

            if(walls >= 3){
                curr.setBlock(wallLarge, team);
            }
        });

        //clear path for ground units
        for(Tile tile : cores){
            Astar.pathfind(tile, spawn, t -> t.team() == state.rules.waveTeam && !t.within(tile, 25f * 8) ? 100000 : t.floor().hasSurface() ? 1 : 10, t -> !t.block().isStatic()).each(t -> {
                if(!t.within(tile, 25f * 8)){
                    if(t.team() == state.rules.waveTeam){
                        t.setBlock(Blocks.air);
                    }

                    for(Point2 p : Geometry.d8){
                        Tile other = t.nearby(p);
                        if(other != null && other.team() == state.rules.waveTeam){
                            other.setBlock(Blocks.air);
                        }
                    }
                }
            });
        }
    }

    void pass(Cons<Tile> cons){
        Tile core = cores.first();
        core.circle(160, (x, y) -> cons.get(tiles.getn(x, y)));
    }

    public static void tryPlace(BasePart part, int x, int y, Team team){
        tryPlace(part, x, y, team, null);
    }

    public static void tryPlace(BasePart part, int x, int y, Team team, @Nullable Intc2 posc){
        if (part == null) return;

        int rotation = Mathf.range(2);
        axis.set((int)(part.schematic.width / 2f), (int)(part.schematic.height / 2f));
        Schematic result = Schematics.rotate(part.schematic, rotation);
        int rotdeg = rotation*90;

        rotator.set(part.centerX, part.centerY).rotateAround(axis, rotdeg);
        //bottom left schematic corner
        int cx = x - (int)rotator.x;
        int cy = y - (int)rotator.y;

        for(Schematic.Stile tile : result.tiles){
            int realX = tile.x + cx, realY = tile.y + cy;
            if(isTaken(tile.block, realX, realY)){
                return;
            }

            if(posc != null){
                posc.get(realX, realY);
            }
        }

        if(part.required instanceof Item item){
            for(Schematic.Stile tile : result.tiles){
                if(tile.block instanceof Drill){

                    tile.block.iterateTaken(tile.x + cx, tile.y + cy, (ex, ey) -> {

                        if(world.tiles.getn(ex, ey).floor().hasSurface()){
                            set(world.tiles.getn(ex, ey), item);
                        }

                        Tile rand = world.tiles.getc(ex + Mathf.range(1), ey + Mathf.range(1));
                        if(rand.floor().hasSurface()){
                            //random ores nearby to make it look more natural
                            set(rand, item);
                        }
                    });
                }
            }
        }

        Schematics.place(result, cx + result.width/2, cy + result.height/2, team);

        //fill drills with items after placing
        if(part.required instanceof Item item){
            for(Schematic.Stile tile : result.tiles){
                if(tile.block instanceof Drill){

                    Building build = world.tile(tile.x + cx, tile.y + cy).build;

                    if(build != null){
                        build.items.add(item, build.block.itemCapacity);
                    }
                }
            }
        }

    }

    static void set(Tile tile, Item item){
        if (uores.containsKey(item)) {
            tile.setOverlay(uores.get(item));
        } else if (bases.ores.containsKey(item)){
            tile.setOverlay(bases.ores.get(item));
        } else if (bases.oreFloors.containsKey(item)) {
            tile.setFloor(bases.oreFloors.get(item));
        }
    }

    static boolean isTaken(Block block, int x, int y){
        int offsetx = -(block.size - 1) / 2;
        int offsety = -(block.size - 1) / 2;
        int pad = 1;

        for(int dx = -pad; dx < block.size + pad; dx++){
            for(int dy = -pad; dy < block.size + pad; dy++){
                if(overlaps(dx + offsetx + x, dy + offsety + y)){
                    return true;
                }
            }
        }

        return false;
    }

    static boolean overlaps(int x, int y){
        Tile tile = world.tiles.get(x, y);

        return tile == null || !tile.block().alwaysReplace || world.getDarkness(x, y) > 0;
    }

    public Seq<BasePart> forResource(Content item){
        return reqParts.get(item, Seq::new);
    }
}
