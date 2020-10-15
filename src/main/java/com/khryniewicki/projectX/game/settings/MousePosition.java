package com.khryniewicki.projectX.game.settings;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import lombok.Data;
import lombok.Getter;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

@Data
public class MousePosition {
    private float windowPositionX;
    private float windowPositionY;

    public void setWindowPositions(double x, double y) {
        windowPositionX = (float) ((x - Game.width / 2) / (Game.width / 20f));
        windowPositionY = (float) ((Game.height / 2 - y)) / (Game.height / 10f);
    }

    public Position getCursorPosition() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(Game.window, xBuffer, yBuffer);
        double x = xBuffer.get(0);
        double y = yBuffer.get(0);
        setWindowPositions(x, y);
        return new Position(x, y);
    }


}
