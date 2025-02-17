package org.grupo3.library.entities;

public record Genre (String name, String description) {
    @Override
    public String toString() {
        return  "Gênero: " + name + ", Descrição: " + description;
    }
}
