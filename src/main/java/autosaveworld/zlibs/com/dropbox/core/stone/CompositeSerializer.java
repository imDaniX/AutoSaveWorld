package autosaveworld.zlibs.com.dropbox.core.stone;

import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonGenerationException;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonGenerator;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonParseException;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonParser;
import autosaveworld.zlibs.com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

public abstract class CompositeSerializer<T> extends StoneSerializer<T> {
    protected static final String TAG_FIELD = ".tag";

    protected static boolean hasTag(JsonParser p) throws IOException {
        return p.getCurrentToken() == JsonToken.FIELD_NAME && TAG_FIELD.equals(p.getCurrentName());
    }

    protected static String readTag(JsonParser p) throws IOException {
        if (!hasTag(p)) {
            return null;
        }
        p.nextToken();
        String tag = getStringValue(p);
        p.nextToken();

        return tag;
    }

    protected void writeTag(String tag, JsonGenerator g) throws IOException {
        if (tag != null) {
            g.writeStringField(TAG_FIELD, tag);
        }
    }
}

