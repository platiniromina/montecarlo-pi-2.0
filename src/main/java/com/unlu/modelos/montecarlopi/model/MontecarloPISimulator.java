package com.unlu.modelos.montecarlopi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MontecarloPISimulator {
    private final AtomicLong pointsInsideCircle = new AtomicLong(0);
    private final AtomicLong totalPoints = new AtomicLong(0);

    private final List<Point> mostRecentPoints = Collections.synchronizedList(new ArrayList<>());

    public void generatePoint() {
        double x = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
        double y = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);

        boolean insideCircle = (Math.pow(x, 2) + Math.pow(y, 2)) <= 1.0;
        if (insideCircle) pointsInsideCircle.incrementAndGet();
        totalPoints.incrementAndGet();

        synchronized (mostRecentPoints) {
            mostRecentPoints.add(new Point(x, y, insideCircle));
        }
    }

    public double getEstimatedPi() {
        long total = totalPoints.get();
        return (total == 0) ? 0.0 : 4.0 * pointsInsideCircle.get() / total;
    }

    public long getPointsInsideCircle() {
        return pointsInsideCircle.get();
    }

    public long getTotalPoints() {
        return totalPoints.get();
    }

    public List<Point> getMostRecentPoints() {
        synchronized (mostRecentPoints) {
            return new ArrayList<>(mostRecentPoints);
        }
    }

    public void reset() {
        pointsInsideCircle.set(0);
        totalPoints.set(0);
        synchronized (mostRecentPoints) {
            mostRecentPoints.clear();
        }
    }
}

