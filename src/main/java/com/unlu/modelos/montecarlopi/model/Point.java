package com.unlu.modelos.montecarlopi.model;

import lombok.Getter;

@Getter
public class Point {
    private final double x;
    private final double y;
    private final boolean inside;

    public Point(double x, double y, boolean inside) {
        this.x = x;
        this.y = y;
        this.inside = inside;
    }
}
