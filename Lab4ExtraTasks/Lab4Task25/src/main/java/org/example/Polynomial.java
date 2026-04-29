package org.example;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private final Map<Integer, Double> coefficients = new HashMap<>();
    private int degree = -1;

    public void add(Polynomial other) {
        for (Map.Entry<Integer, Double> entry : other.coefficients.entrySet()) {
            addCoefficient(entry.getKey(), entry.getValue());
        }
    }

    public void addCoefficient(int degree, double coefficient) {
        if (coefficient == 0) {
            return;
        }

        double currentCoefficient = coefficients.getOrDefault(degree, 0.0);
        double newCoefficient = currentCoefficient + coefficient;

        if (newCoefficient == 0) {
            coefficients.remove(degree);
        } else {
            coefficients.put(degree, newCoefficient);
        }

        updateDegree();
    }

    private void updateDegree() {
        degree = coefficients.keySet().stream()
                .max(Integer::compareTo)
                .orElse(-1);
    }

    @Override
    public String toString() {
        if (coefficients.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;

        for (Map.Entry<Integer, Double> entry : coefficients.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByKey().reversed())
                .toList()) {
            int degree = entry.getKey();
            double coefficient = entry.getValue();

            if (coefficient == 0) {
                continue;
            }

            if (first) {
                if (coefficient < 0) {
                    stringBuilder.append("-");
                }
                first = false;
            } else {
                stringBuilder.append(coefficient > 0 ? " + " : " - ");
            }

            double absCoefficient = Math.abs(coefficient);

            if (degree == 0) {
                stringBuilder.append(absCoefficient);
            } else if (degree == 1) {
                if (absCoefficient != 1) {
                    stringBuilder.append(absCoefficient);
                }
                stringBuilder.append("X");
            } else {
                if (absCoefficient != 1) {
                    stringBuilder.append(absCoefficient);
                }
                stringBuilder.append("X^").append(degree);
            }
        }

        return stringBuilder.toString();
    }
}