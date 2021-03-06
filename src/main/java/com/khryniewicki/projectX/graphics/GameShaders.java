package com.khryniewicki.projectX.graphics;

import com.khryniewicki.projectX.math.Matrix4f;

public class GameShaders {

    private GameShaders() {
    }

    private final static GameShaders INSTANCE = new GameShaders();

    public static GameShaders getINSTANCE() {
        return INSTANCE;
    }

    public static void loadAll() {
        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);

        Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BG.setUniform1i("tex", 1);
        Shader.OBSTACLE.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.OBSTACLE.setUniform1i("tex", 1);
        Shader.TERRAIN.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.TERRAIN.setUniform1i("tex", 1);


        Shader.HERO.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.HERO.setUniform1i("tex", 1);
        Shader.SPELL.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SPELL.setUniform1i("tex", 1);

        Shader.TEXT.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.TEXT.setUniform1i("tex", 1);

        Shader.STRIP.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.STRIP.setUniform1i("tex", 1);

        Shader.SYMBOL.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SYMBOL.setUniform1i("tex", 1);
    }
}
