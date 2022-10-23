package stus.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.world.meta.BlockFlag;
import stus.graphics.RapuPal;

public class StuasutUnits {
    public static UnitType

    //unit
    navicula;

    public static void load(){
        navicula = new UnitType("navicula"){{
            health = 50;
            hitSize = 11;
            outlineColor = RapuPal.rapuOutline;
            constructor = UnitEntity::create;

            speed = 2.4f;
            rotateSpeed = 5.4f;
            drag = 0.03f;
            accel = 0.1f;

            targetAir = true;
            targetGround = false;
            targetFlags = new BlockFlag[]{BlockFlag.generator, null};
            flying = true;

            engineOffset = 5.7f;
            lowAltitude = true;
            itemCapacity = 10;

            alwaysUnlocked = true;

            weapons.add(new Weapon("stus-main-weapon"){{
                x = 0f;
                y = -2f;
                reload = 30f;
                rotate = true;
                shootCone = 15f;
                mirror = false;
                bullet = new BasicBulletType(3, 30){{
                    lifetime = 60f;
                    collidesGround = false;

                    trailLength = 15;
                    trailWidth = 1.6f;
                    trailSinScl = 2f;
                    trailSinMag = 0.5f;
                    trailEffect = Fx.none;
                    trailColor = Color.valueOf("FF6F89FF");

                    frontColor = Color.valueOf("FFFFFF");
                    backColor = Color.valueOf("D85876FF");

                    width = 7;
                    height = 8;

                    fragBullets = 2;
                    fragVelocityMin = 0.7f;
                    fragVelocityMax = 3f;
                    fragRandomSpread = 16f;
                    fragLifeMin = 0.6f;
                    fragLifeMax = 1f;
                    despawnShake = 0f;
                    fragOnHit = true;

                    fragBullet = new BasicBulletType(2.5f, 10f){{
                       width = 6f;
                       height = 7f;

                       lifetime = 40f;
                       collidesGround = false;

                       trailLength = 10;
                       trailWidth = 1.4f;
                       trailSinScl = 2f;
                       trailSinMag = 0.5f;
                       trailEffect = Fx.none;
                       trailColor = Color.valueOf("FF6F89FF");

                       frontColor = Color.valueOf("FFFFFF");
                       backColor = Color.valueOf("D85876FF");

                       fragBullets = 6;
                       fragRandomSpread = 25f;
                       fragVelocityMin = 0.7f;
                       fragVelocityMax = 2f;
                       fragLifeMin = 1f;
                       fragLifeMax = 5f;
                       fragOnHit = true;

                       fragBullet = new BasicBulletType(2f, 5f){{
                          width = 5f;
                          height = 6f;

                          lifetime = 25f;
                          collidesGround = false;

                           trailLength = 5;
                           trailWidth = 1.2f;
                           trailSinScl = 2f;
                           trailSinMag = 0.5f;
                           trailEffect = Fx.none;
                       }};
                    }};
                }};
            }});
        }};
    }//TODO make core unit
}