package com.unlu.modelos.montecarlopi.controller;

import com.unlu.modelos.montecarlopi.model.MontecarloPISimulator;
import com.unlu.modelos.montecarlopi.model.Point;
import com.unlu.modelos.montecarlopi.view.MontecarloPIView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MontecarloPIController {
    private final MontecarloPIView view;
    private final MontecarloPISimulator model;
    private ScheduledExecutorService executor;
    private Future<?> task;
    private long startTime;

    public MontecarloPIController(MontecarloPIView view, MontecarloPISimulator model) {
        this.view = view;
        this.model = model;

        view.getStartBtn().addActionListener(e -> startSimulation());
        view.getStopBtn().addActionListener(e -> stopSimulation());
        view.getResetBtn().addActionListener(e -> resetSimulation());
    }

    private void startSimulation() {
        if (executor == null || executor.isShutdown()) {
            executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        }
        startTime = System.currentTimeMillis();

        task = executor.scheduleAtFixedRate(() -> {
            model.generatePoint();
            List<Point> puntos = model.getMostRecentPoints();
            double pi = model.getEstimatedPi();
            long inside = model.getPointsInsideCircle();
            long total = model.getTotalPoints();

            SwingUtilities.invokeLater(() -> view.update(puntos, pi, inside, total));
        }, 0, 5, TimeUnit.MILLISECONDS);
    }

    private void stopSimulation() {
        if (task != null) task.cancel(true);
        if (executor != null) executor.shutdown();
        saveResult();
    }

    private void resetSimulation() {
        stopSimulation();
        model.reset();
        SwingUtilities.invokeLater(() -> view.update(model.getMostRecentPoints(), 0, 0, 0));
    }

    private void saveResult() {
        long elapsedTime = System.currentTimeMillis() - startTime;

        JSONObject json = new JSONObject();
        json.put("pi_estimado", model.getEstimatedPi());
        json.put("puntos_dentro", model.getPointsInsideCircle());
        json.put("puntos_totales", model.getTotalPoints());
        json.put("tiempo_ms", elapsedTime);

        try {
            File jarDir = new File(MontecarloPIController.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getParentFile();
            File file = new File(jarDir, "resultado.json");

            JSONArray resultados;
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                resultados = new JSONArray(content);
            } else {
                resultados = new JSONArray();
            }

            resultados.put(json);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(resultados.toString(4));
            }

        } catch (IOException | URISyntaxException | JSONException e) {
            e.printStackTrace();
        }
    }

}