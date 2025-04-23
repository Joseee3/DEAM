package com.upn.deam_firsts_project.entities;

import java.util.List;

public class PokemonDetail {
    private int id;
    private String name;
    private List<TypeWrapper> types;
    private Sprites sprites;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TypeWrapper> getTypes() {
        return types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public class TypeWrapper {
        private Type type;

        public Type getType() {
            return type;
        }
    }

    public class Type {
        private String name;

        public String getName() {
            return name;
        }
    }

    public class Sprites {
        private String front_default;

        public String getFrontDefault() {
            return front_default;
        }
    }
}