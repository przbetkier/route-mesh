package dev.przbetkier.routemesh.domain.obstacle.obstructions;

public class Obstructions {

    private HeightObstruction height;
    private WeightObstruction weight;
    private WidthObstruction width;

    public Obstructions() {
    }

    public Obstructions(HeightObstruction height, WeightObstruction weight, WidthObstruction width) {
        this.height = height;
        this.weight = weight;
        this.width = width;
    }

    public HeightObstruction getHeight() {
        return height;
    }

    public WeightObstruction getWeight() {
        return weight;
    }

    public WidthObstruction getWidth() {
        return width;
    }
}

