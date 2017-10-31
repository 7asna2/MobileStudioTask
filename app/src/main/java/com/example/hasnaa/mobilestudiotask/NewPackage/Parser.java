package com.example.hasnaa.mobilestudiotask.NewPackage;

import com.example.hasnaa.mobilestudiotask.Modules.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by Hasnaa on 28-10-2017.
 */
public class Parser {

//    public static final int TYPE_JSON = 200;
    // more types could be added

    public <T> T parseJson(String response , Class<T> classType) {
        Gson gson = new GsonBuilder().create();
        T result = gson.fromJson(response, classType);
        return Primitives.wrap(classType).cast(result);
    }

}
