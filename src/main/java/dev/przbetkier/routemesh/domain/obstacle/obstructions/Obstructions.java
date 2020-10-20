package dev.przbetkier.routemesh.domain.obstacle.obstructions;

public class Obstructions {

    private HeightObstruction height;
    private WeightObstruction weight;
    private WidthObstruction width;
    private ElevationObstruction elevation;
    private CurvatureObstruction curvature;

    public Obstructions() {
    }

    public Obstructions(HeightObstruction height,
                        WeightObstruction weight,
                        WidthObstruction width,
                        ElevationObstruction elevation,
                        CurvatureObstruction curvature) {
        this.height = height;
        this.weight = weight;
        this.width = width;
        this.elevation = elevation;
        this.curvature = curvature;
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

    public ElevationObstruction getElevation() {
        return elevation;
    }

    public CurvatureObstruction getCurvature() {
        return curvature;
    }
}

