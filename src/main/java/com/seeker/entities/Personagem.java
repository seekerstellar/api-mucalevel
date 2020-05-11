package com.seeker.entities;

public class Personagem {
	
    private String name;
    private String level;
    private String reset;

    public Personagem(String name, String level, String reset) {
        this.name = name;
        this.level = level;
        this.reset = reset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    @Override
    public String toString() {
        return "Personagem{" + "name=" + name + ", level=" + level + ", reset=" + reset + '}';
    }

}
