package perfect.versions.common.base.json;

import org.jetbrains.annotations.NotNull;
import perfect.versions.common.base.VersionModule;

import java.lang.reflect.InvocationTargetException;

public class DefaultJsonModuleClassLoader implements JsonModuleClassLoader {

    @Override
    public @NotNull VersionModule loadFromClass(@NotNull Class<?> clazz) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        if (!VersionModule.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " is not a module");
        }
        return (VersionModule) clazz.newInstance();
    }
}