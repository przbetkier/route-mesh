package dev.przbetkier.routemesh.domain.obstacle.obstructions;

public class Obstructions {

    private HeightObstruction height;
    private WeightObstruction weight;

    public Obstructions() {
    }

    public Obstructions(HeightObstruction height, WeightObstruction weight) {
        this.height = height;
        this.weight = weight;
    }

    public HeightObstruction getHeight() {
        return height;
    }

    public WeightObstruction getWeight() {
        return weight;
    }
}

