package stus.content;

import arc.graphics.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import stus.graphics.RapuPal;

public class StuasutUnits {
    public static UnitType

    //units
    navicula, frontis,

    //core units
    sunrise;

    public static BasicBulletType naviculaBullet = new BasicBulletType(3, 30){{

        lifetime = 30f;

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
        buildingDamageMultiplier = 0.65f;

        fragBullet = new BasicBulletType(2.5f, 10f){{
            width = 6f;
            height = 7f;

            lifetime = 15f;

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
            buildingDamageMultiplier = 0.65f;

            fragBullet = new BasicBulletType(2f, 5f){{
                width = 5f;
                height = 6f;

                lifetime = 7.5f;

                trailLength = 5;
                trailWidth = 1.2f;
                trailSinScl = 2f;
                trailSinMag = 0.5f;
                trailEffect = Fx.none;
                trailColor = Color.valueOf("FF6F89FF");

                frontColor = Color.valueOf("FFFFFF");
                backColor = Color.valueOf("D85876FF");
                buildingDamageMultiplier = 0.65f;
            }};
        }};
    }};
    public static Weapon getNaviculaWeapon(float x, float y){
        Weapon weapon = new Weapon("stus-main-weapon"){{
            reload = 30f;
            rotate = false;
            shootCone = 15f;
            mirror = false;
            bullet = naviculaBullet;
        }};
        weapon.x = x;
        weapon.y = y;
        return weapon;
    }
    public static Weapon getFrontisWeapon(float x, float y){
        Weapon weapon = new Weapon("stus-w1"){{
            reload = 50f;
            rotate = false;
            shootCone = 20f;
            mirror = false;
            bullet = new LaserBoltBulletType(4f, 30){{
                width = 4f;
                height = 9f;

                lifetime = 40f;
                pierceArmor = true;
                pierce = true;
                pierceCap = 2;
                pierceBuilding = true;

                trailLength = 14;
                trailWidth = 2f;
                trailSinScl = 2f;
                trailSinMag = 0.5f;
                trailEffect = Fx.none;
                trailColor = Color.valueOf("FF6F89FF");

                frontColor = Color.valueOf("FFFFFF");
                backColor = Color.valueOf("D85876FF");
            }};
        }};
        weapon.x = x;
        weapon.y = y;
        return weapon;
    }
    public static void load(){

        navicula = new UnitType("navicula"){{
            health = 50;
            hitSize = 11;
            outlineColor = RapuPal.rapuOutline;
            constructor = UnitEntity::create;

            speed = 2.6f;
            rotateSpeed = 5.4f;
            drag = 0.03f;
            accel = 0.1f;

            targetAir = true;
            targetGround = false;
            targetFlags = new BlockFlag[]{BlockFlag.generator, null};
            faceTarget = true;
            flying = true;

            engineOffset = 5.7f;
            lowAltitude = true;
            itemCapacity = 10;

            weapons.add(getNaviculaWeapon(0, -2));
        }};
        frontis = new UnitType("frontis"){{
            health = 230;
            hitSize = 17;
            outlineColor = RapuPal.rapuOutline;
            constructor = UnitEntity::create;

            speed = 2.1f;
            rotateSpeed = 5.4f;
            drag = 0.05f;
            accel = 0.05f;

            targetFlags = new BlockFlag[]{BlockFlag.factory, null};
            faceTarget = true;
            flying = true;

            engineOffset = 7f;
            engineSize = 2;
            lowAltitude = true;
            itemCapacity = 20;

            weapons.add(getNaviculaWeapon(3.5f, -4.75f), getNaviculaWeapon(-3.5f, -4.75f), getFrontisWeapon(3.5f, 5.5f), getFrontisWeapon(-3.5f, 5.5f));
        }};
        sunrise = new UnitType("sunrise"){{
           aiController = BuilderAI::new;
           isEnemy = false;
           constructor = UnitEntity::create;

           lowAltitude = true;
           flying = true;
           mineSpeed = 7f;
           mineTier = 1;
           buildSpeed = 0.75f;
           drag = 0.05f;
           speed = 3.6f;
           rotateSpeed = 17f;
           accel = 0.1f;
           itemCapacity = 50;
           health = 210;
           engineOffset = 6f;
           hitSize = 10f;
           alwaysUnlocked = true;
           faceTarget = false;

           weapons.add(new Weapon("stus-sunrise-weapon"){{
               top = false;
               reload = 20f;
               mirror = false;

               x = 0f;
               y = 0f;

               rotate = true;
               shoot.shots = 2;
               shoot.shotDelay = 5f;

               ejectEffect = Fx.none;

               bullet = new MissileBulletType(3.6f, 18){{
                  width = 7f;
                  height = 9f;

                  homingPower = 0.05f;
                  homingRange = 90;

                  lifetime = 65f;

                  shootSound = Sounds.missile;
                  shootEffect = Fx.shootSmall;
                  smokeEffect = Fx.shootSmallSmoke;
                  buildingDamageMultiplier = 0.001f;
               }};
           }});
        }};
    }
}