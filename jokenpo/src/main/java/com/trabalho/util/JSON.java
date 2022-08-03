package com.trabalho.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trabalho.Usuario;


public class JSON {
    public static String toJSON(Usuario usuario) {
        Gson gson = new Gson();
        return gson.toJson(usuario);
    }
    public static String toJSON(List<Usuario> usuarios) {
        Gson gson = new Gson();
        return gson.toJson(usuarios);
    }

    public static Usuario fromJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Usuario.class);
    }

    public static List<Usuario> fromJSONArray(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}
