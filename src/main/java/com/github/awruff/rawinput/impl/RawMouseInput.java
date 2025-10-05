package com.github.awruff.rawinput.impl;

import com.github.awruff.rawinput.RawInputMod;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;
import net.minecraft.client.input.MouseInput;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class RawMouseInput extends MouseInput {
    private int fails = 0;

    private final List<Mouse> mice = new ArrayList<>();

    public RawMouseInput() {
        refresh();
    }

    @Override
    public void tick() {
        if (!mice.isEmpty()) {
            mice.forEach(it -> {
                if (!it.poll()) refresh();

                dx += (int) it.getX().getPollData();
                dy -= (int) it.getY().getPollData();
            });

            boolean movement = (dx != 0 || dy != 0);

            if (!(Math.abs(getDX()) <= 5 && Math.abs(getDY()) <= 5 || movement)) {
                if (fails++ > 5) refresh();
            } else if (movement) {
                fails = 0;
            }
        }
    }

    private int getDX() {
        return org.lwjgl.input.Mouse.getDX();
    }

    private int getDY() {
        return org.lwjgl.input.Mouse.getDY();
    }

    private void refresh() {
        try {
            Class<?> clazz = Class.forName("net.java.games.input." + RawInputMod.environment);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            ControllerEnvironment env = (ControllerEnvironment) constructor.newInstance();

            mice.clear();

            for (net.java.games.input.Controller controller : env.getControllers()) {
                if (controller instanceof Mouse) {
                    mice.add((Mouse) controller);
                }
            }

        } catch (Exception ignored) {}
    }
}
