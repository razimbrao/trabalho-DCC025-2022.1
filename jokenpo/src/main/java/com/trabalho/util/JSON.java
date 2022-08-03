package com.trabalho.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.trabalho.Jogador;


public class JSON {
    public static String toJSON(Jogador jogador) {
        Gson gson = new Gson();
        return gson.toJson(jogador);
    }
    public static String toJSON(List<Jogador> jogadores) {
        Gson gson = new Gson();
        return gson.toJson(jogadores);
    }

    public static Jogador fromJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Jogador.class);
    }

    public static List<Jogador> fromJSONArray(String json) {
/*         GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Jogador.class, ); */
        Gson gson = new Gson();
        //TypeAdapter
        //TypeAdapter adapter = gson.getAdapter(TypeToken.get(new TypeToken<List<Jogador>>() {}.getType()));
        Type listType = new TypeToken<ArrayList<Jogador>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}
