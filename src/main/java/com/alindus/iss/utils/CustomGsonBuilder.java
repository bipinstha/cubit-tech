package com.alindus.iss.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class CustomGsonBuilder {

	public static GsonBuilder createCustomGsonBuilder(){
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        @Override
	        public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
	                throws JsonParseException {
	            try {
	                return df.parse(json.getAsString());
	            } catch (ParseException e) {
	                return null;
	            }
	        }
	    });
	    return gsonBuilder;
	}
}
