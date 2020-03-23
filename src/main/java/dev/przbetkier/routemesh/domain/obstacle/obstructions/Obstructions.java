package dev.przbetkier.routemesh.domain.obstacle.obstructions;

public class Obstructions {

    private HeightObstruction height;

    public Obstructions() {
    }

    public Obstructions(HeightObstruction height) {
        this.height = height;
    }

    public HeightObstruction getHeight() {
        return height;
    }
}

