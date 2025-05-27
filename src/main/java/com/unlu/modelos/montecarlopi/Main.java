package com.unlu.modelos.montecarlopi;

import com.unlu.modelos.montecarlopi.controller.MontecarloPIController;
import com.unlu.modelos.montecarlopi.model.MontecarloPISimulator;
import com.unlu.modelos.montecarlopi.view.MontecarloPIView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MontecarloPISimulator model = new MontecarloPISimulator();
            MontecarloPIView view = new MontecarloPIView();
            new MontecarloPIController(view, model);
        });
    }
}
