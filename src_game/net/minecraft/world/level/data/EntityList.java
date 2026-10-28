/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.world.level.data;

import com.mojang.nbt.NBTTagCompound;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Painting;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.GiantZombie;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class EntityList {
    private static Map<String, Class<?>> stringToClassMapping = new HashMap();
    private static Map<Class<?>, String> classToStringMapping = new HashMap();
    private static Map<Integer, Class<?>> IDtoClassMapping = new HashMap();
    private static Map<Class<?>, Integer> classToIDMapping = new HashMap();

    static {
        EntityList.addMapping(ItemEntity.class, "Item", 1);
        EntityList.addMapping(Painting.class, "Painting", 9);
        EntityList.addMapping(Arrow.class, "Arrow", 10);
        EntityList.addMapping(PrimedTnt.class, "PrimedTnt", 20);
        EntityList.addMapping(Mob.class, "Mob", 48);
        EntityList.addMapping(Monster.class, "Monster", 49);
        EntityList.addMapping(Creeper.class, "Creeper", 50);
        EntityList.addMapping(Skeleton.class, "Skeleton", 51);
        EntityList.addMapping(Spider.class, "Spider", 52);
        EntityList.addMapping(GiantZombie.class, "Giant", 53);
        EntityList.addMapping(Zombie.class, "Zombie", 54);
        EntityList.addMapping(Pig.class, "Pig", 90);
        EntityList.addMapping(Sheep.class, "Sheep", 91);
    }

    private static void addMapping(Class<?> var0, String var1, int var2) {
        stringToClassMapping.put(var1, var0);
        classToStringMapping.put(var0, var1);
        IDtoClassMapping.put(var2, var0);
        classToIDMapping.put(var0, var2);
    }

    public static Entity createEntityInLevel(String var0, Level var1) {
        Entity var2 = null;
        try {
            Class<?> var3 = stringToClassMapping.get(var0);
            if (var3 != null) {
                var2 = (Entity)var3.getConstructor(Level.class).newInstance(var1);
            }
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
        return var2;
    }

    public static Entity createEntityFromNBT(NBTTagCompound var0, Level var1) {
        Entity var2 = null;
        try {
            Class<?> var3 = stringToClassMapping.get(var0.getString("id"));
            if (var3 != null) {
                var2 = (Entity)var3.getConstructor(Level.class).newInstance(var1);
            }
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
        if (var2 != null) {
            var2.readFromNBT(var0);
        } else {
            System.out.println("Skipping Entity with id " + var0.getString("id"));
        }
        return var2;
    }

    public static Entity createEntity(int var0, Level var1) {
        Entity var2 = null;
        try {
            Class<?> var3 = IDtoClassMapping.get(var0);
            if (var3 != null) {
                var2 = (Entity)var3.getConstructor(Level.class).newInstance(var1);
            }
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
        if (var2 == null) {
            System.out.println("Skipping Entity with id " + var0);
        }
        return var2;
    }

    public static int getEntityID(Entity var0) {
        return classToIDMapping.get(var0.getClass());
    }

    public static String getEntityString(Entity var0) {
        return classToStringMapping.get(var0.getClass());
    }

    public static Entity loadEntity(Level level, String name) {
        if (name.equals("Pig")) {
            return new Pig(level);
        }
        if (name.equals("Sheep")) {
            return new Sheep(level);
        }
        if (name.equals("Creeper")) {
            return new Creeper(level);
        }
        if (name.equals("Skeleton")) {
            return new Skeleton(level);
        }
        if (name.equals("Spider")) {
            return new Spider(level);
        }
        if (name.equals("Zombie")) {
            return new Zombie(level);
        }
        if (name.equals("Giant")) {
            return new GiantZombie(level);
        }
        if (name.equals("Item")) {
            return new ItemEntity(level);
        }
        if (name.equals("Painting")) {
            return new Painting(level);
        }
        if (name.equals("PrimedTnt")) {
            return new PrimedTnt(level);
        }
        if (name.equals("Arrow")) {
            return new Arrow(level);
        }
        return null;
    }
}

