package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadOccupancy {
    public static void loadOccupancy(XMLLoader loader) {
        OccupancyUtil occUtil = new OccupancyUtil();

        loader.moveToNode("slavery");
        occUtil.setGeneratedIncome(loader.getCurrentElementAttributeAsInt("generatedIncome"));
        occUtil.setGeneratedUpkeep(loader.getCurrentElementAttributeAsInt("generatedUpkeep"));

        if (loader.moveToIfHasChildren("slavesAtJob")) {
            loader.forEachAttributeOfElement("slaves", "job", slaveJob -> {
                SlaveJob job = SlaveJob.valueOf(slaveJob);
                occUtil.charactersAtJob.put(job, new ArrayList<>());
                loader.forEachTextContent("id", slaveID -> {
                    if (!occUtil.charactersAtJob.get(job).contains(slaveID))
                        occUtil.charactersAtJob.get(job).add(slaveID);
                });
            });
            loader.goToParent();
        }
        loader.forEachAttributeOfElement("milkingRoom", "worldType", milkWorldType -> {
            int x = loader.getCurrentElementAttributeAsInt("x");
            int y = loader.getCurrentElementAttributeAsInt("y");
            AbstractWorldType milkWorld = WorldType.getWorldTypeFromId(milkWorldType);
            Vector2i location = new Vector2i(x, y);
            MilkingRoom room = loadMilkingRoom(loader, milkWorld, location);
            if (occUtil.getMilkingRoom(milkWorld, location) == null)
                occUtil.addMilkingRoom(room);
        });


        Main.game.occupancyUtil = occUtil;
        loader.goToParent();
    }

    public static MilkingRoom loadMilkingRoom(XMLLoader loader, AbstractWorldType worldType, Vector2i location) {
        MilkingRoom room = new MilkingRoom(worldType, location);
        // I have a sneaking suspicion I might've made a fluid dupe by doing this... Let's see if anyone discovers one, Sep 14 2026, 0928 UTC-5
        if (loader.currentElementHasChildren()) {
            Map<FluidStored, Float> uniqueFluids = new HashMap<>();
            String charFluidID;
            while (!(charFluidID = loader.getNextElementAttributeAsStr("fluidStored", "charactersFluidID")).isEmpty()) {
                FluidStored stored = LoadFluidStored.loadFluidStored(loader, charFluidID, null);

                room.getFluidsStored().add(stored);
            }
            loader.goToParent();


            for (FluidStored fluid : room.getFluidsStored()) {
                if (uniqueFluids.containsKey(fluid)) {
                    uniqueFluids.put(fluid, fluid.getMillilitres() + uniqueFluids.get(fluid));
                } else {
                    uniqueFluids.put(fluid, fluid.getMillilitres());
                }
            }

            room.fluidsStored = new ArrayList<>();
            for (Map.Entry<FluidStored, Float> entry : uniqueFluids.entrySet()) {
                entry.getKey().setMillilitres(entry.getValue());
                room.fluidsStored.add(entry.getKey());
            }
        }


        room.clothingRemovedForMilking = new HashMap<>();
        if (loader.moveToIfHasChildren("clothingRemovedForMilking")) {
            List<AbstractClothing> clothingLoaded = new ArrayList<>();
            loader.forEachAttributeOfElement("clothingCharacter", "id", clothID -> {
                loader.forEachAttributeOfElement("clothing", "slotEquippedTo", slot -> {
                    clothingLoaded.add(LoadInventory.loadClothing(loader));
                });
                room.clothingRemovedForMilking.put(clothID, clothingLoaded);
            });
            loader.goToParent();
        }

        return room;
    }
}
