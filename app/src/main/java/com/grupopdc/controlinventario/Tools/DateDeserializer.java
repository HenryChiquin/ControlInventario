package com.grupopdc.controlinventario.Tools;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class DateDeserializer implements JsonDeserializer {
    private static final String[] DATE_FORMATS = new String[] {
            "yyyy'-'MM'-'dd'T'HH':'mm':'ss"
    };
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        for (String format: DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format, Locale.US).parse(json.getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
                Log.e("Error al parsear la fecha del JSON", e.getMessage());
            }
        }
        throw new JsonParseException("Error parseando fecha: \"" + json.getAsString() + "\". Formatos soportados: \n" + Arrays.toString(DATE_FORMATS));
    }
}
