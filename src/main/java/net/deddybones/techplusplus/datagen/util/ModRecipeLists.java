package net.deddybones.techplusplus.datagen.util;

import net.deddybones.techplusplus.util.ComponentCollection.ComponentName;
import net.deddybones.techplusplus.util.EquipmentCollection.EquipmentName;
import net.deddybones.techplusplus.util.ObjectNumbered;

import java.util.List;
import java.util.Map;

import static net.deddybones.techplusplus.util.ObjectNumbered.ObN;

public class ModRecipeLists {
    public static final Map<EquipmentName, List<ObjectNumbered<ComponentName>>> armorSetComponents = Map.ofEntries(
            Map.entry(EquipmentName.HELMET,      List.of(ObN(ComponentName.ROUND_PANEL, 2), ObN(ComponentName.FLAT_PANEL, 2))),
            Map.entry(EquipmentName.CHESTPLATE,  List.of(ObN(ComponentName.ROUND_PANEL, 4), ObN(ComponentName.FLAT_PANEL, 2))),
            Map.entry(EquipmentName.LEGGINGS,    List.of(ObN(ComponentName.ROUND_PANEL, 3))),
            Map.entry(EquipmentName.BOOTS,       List.of(ObN(ComponentName.ROUND_PANEL, 3))),
            Map.entry(EquipmentName.HORSE_ARMOR, List.of(ObN(ComponentName.ROUND_PANEL, 3), ObN(ComponentName.FLAT_PANEL, 3)))
    );
}
