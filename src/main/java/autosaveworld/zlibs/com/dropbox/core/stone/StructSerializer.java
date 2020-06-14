package autosaveworld.zlibs.com.dropbox.core.stone;

import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonGenerationException;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonGenerator;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonParseException;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;

public abstract class StructSerializer<T> extends CompositeSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator g) throws IOException {
        serialize(value, g, false);
    }

    public abstract void serialize(T value, JsonGenerator g, boolean collapse) throws IOException;

    @Override
    public T deserialize(JsonParser p) throws IOException {
        return deserialize(p, false);
    }

    public abstract T deserialize(JsonParser p, boolean collapsed) throws IOException;
}
