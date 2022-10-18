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
import static stus.content.StuasutSectors.*;

public class StuasutTechTree {
    public void load(){
        StuasutPlanets.rapu.techTree = nodeRoot("decalin", StuasutBlocks.coreDawn, true, () -> {
            nodeProduce(DecalingItems.oldmateria, () -> {
                nodeProduce(DecalingItems.timefragment, () -> {
                });

                nodeProduce(Items.silicon, () -> {
                    nodeProduce(DecalingItems.viliniteAlloy, () -> {
                    });
                });
                nodeProduce(Items.graphite, () -> {
                    nodeProduce(DecalingItems.decaygraphite, () -> {
                        nodeProduce(DecalingItems.reliteplate, () -> {
                        });
                    });
                });
                nodeProduce(Items.lead, () -> {
                });
            });
            node(DecalingBlocks.repairer, Seq.with(new Produce(DecalingItems.oldmateria)), () -> {
                node(DecalingBlocks.changer, Seq.with(new SectorComplete(forgotLand)),() -> {
                    node(DecalingBlocks.recreator, () -> {
                        node(DecalingBlocks.pressureClet,Seq.with(new SectorComplete(orbitalCenter)),() -> {
                        });
                    node(DecalingBlocks.vilineForge, Seq.with(new SectorComplete(repairTerminal)),() -> {
                    });
                    });
                });
            });
            node(DecalingBlocks.decalwall, Seq.with(new Produce(DecalingItems.oldmateria)), () -> {
                node(DecalingBlocks.timewall, Seq.with(new Produce(DecalingItems.timefragment)), () -> {

                    node(DecalingBlocks.timewallLarge, () -> {
                    });
                });
                node(DecalingBlocks.decalwalllarge, () -> {
                });
            });
            node(DecalingBlocks.cluster, () -> {
                node(DecalingBlocks.starflood, Seq.with(new Produce(DecalingItems.timefragment)), () -> {
                    node(DecalingBlocks.interleet, Seq.with(new SectorComplete(forgotLand)), () -> {
                        node(DecalingBlocks.crystalFer, () -> {
                        });
                        node(DecalingBlocks.confronter, Seq.with(new SectorComplete(repairTerminal)),() -> {
                            node(DecalingBlocks.missileter, Seq.with(new Produce(DecalingItems.viliniteAlloy)),() -> {
                                node(DecalingBlocks.orbitalCannon, Seq.with(new SectorComplete(orbitalCenter)),() -> {

                                });
                            });
                        });
                    });
                });
            });
            node(DecalingBlocks.decayconsider, () -> {
                node(DecalingBlocks.timeDriver, Seq.with(new OnSector(highPeaks)),() -> {
                });
                node(DecalingBlocks.wire, () -> {
                    node(DecalingBlocks.largeWire, () -> {
                    });
                });
            });
            node(DecalingBlocks.mover, () -> {
                node(DecalingBlocks.lightLink, () -> {
                    node(DecalingBlocks.mediumLink, Seq.with(new SectorComplete(forgotLand)),() -> {
                        node(DecalingBlocks.heavyLink, Seq.with(new SectorComplete(highPeaks)),() -> {
                        });
                    });
                });
            });
            node(DecalingBlocks.decayFactory, Seq.with(new SectorComplete(forgotLand)),() -> {
                node(DecalingBlocks.decayModule, () -> {
                    node(DecalingBlocks.decayModuleT2, () -> {
                    });
                });
                node(DecalingBlocks.decayRefabricator, Seq.with(new SectorComplete(sectureBase)),() -> {
                    node(DecalingUnits.remove, () -> {
                    });
                    node(DecalingBlocks.decayAssembler, Seq.with(new SectorComplete(orbitalCenter)),() -> {
                        node(DecalingUnits.destroy, () -> {
                        });
                    });
                });
                node(DecalingUnits.clear, () -> {
                });
                node(DecalingBlocks.timeFactory, Seq.with(new SectorComplete(forgotLand)),() -> {
                    node(DecalingUnits.hour, () -> {
                    });
                    node(DecalingBlocks.timeRefabricator, Seq.with(new SectorComplete(sectureBase)),() -> {
                        node(DecalingUnits.clock, () -> {
                        });
                        node(DecalingBlocks.timeAssembler, Seq.with(new SectorComplete(orbitalCenter)),() -> {
                            node(DecalingUnits.timer, () -> {
                            });
                            node(DecalingUnits.day, Seq.with(new Research(DecalingBlocks.decayModule)),() -> {
                            });
                            node(DecalingUnits.year, Seq.with(new Research(DecalingBlocks.decayModuleT2)),() -> {
                            });
                        });
                    });
                });
            });
            node(DecalingBlocks.test, () -> {
                node(DecalingBlocks.oreCrusher, () -> {
                    node(DecalingBlocks.tectonicBomber, Seq.with(new SectorComplete(orbitalCenter)),() -> {

                    });
                });
            });
            node(forgotLand, () -> {
                node(sectureBase, Seq.with(new SectorComplete(forgotLand)),() -> {
                    node(repairTerminal, Seq.with(new SectorComplete(sectureBase)),() -> {
                        node(highPeaks, Seq.with(new SectorComplete(repairTerminal)),() -> {
                            node(orbitalCenter, Seq.with(new SectorComplete(highPeaks)),() -> {

                            });
                        });
                    });
                });
            });
        });
    }
}