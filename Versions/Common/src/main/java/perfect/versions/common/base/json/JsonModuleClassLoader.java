package perfect.versions.common.base.json;

import org.jetbrains.annotations.NotNull;
import perfect.versions.common.base.VersionModule;

import java.lang.reflect.InvocationTargetException;

public interface JsonModuleClassLoader {

    @NotNull VersionModule loadFromClass(@NotNull Class<?> clazz) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

}