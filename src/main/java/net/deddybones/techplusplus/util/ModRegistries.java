package net.deddybones.techplusplus.util;

import net.minecraft.core.Registry;

@SuppressWarnings("unused")
public class ModRegistries {
//    public static final Registry<ModArmorMaterial> MOD_ARMOR_MATERIAL;

    public ModRegistries() {
    }

//    private static <T> Registry<T> registerSimple(ResourceKey<? extends Registry<T>> pRegKey,
//                                                  ModRegistries.RegistryBootstrap<T> pRegBootstrap) {
//        return internalRegister(pRegKey, new MappedRegistry(pRegKey, Lifecycle.stable(), false), pRegBootstrap);
//    }
//
//    static {
//        MOD_ARMOR_MATERIAL = registerSimple(TechPlusPlus.MOD_ARMOR_MATERIAL, ModArmorMaterials::bootstrap);
//    }

    @FunctionalInterface
    interface RegistryBootstrap<T> {
        Object run(Registry<T> var1);
    }
}
