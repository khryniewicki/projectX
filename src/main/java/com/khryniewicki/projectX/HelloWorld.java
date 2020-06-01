package com.khryniewicki.projectX;


import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.game.heroes.Factory.WizardFactory;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.menu.TextureLoader;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.utils.TextUtil;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.springframework.stereotype.Component;

import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.util.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@Component
public class HelloWorld implements Runnable {


    public static int width = 1600;
    public static int height = 800;

    private Thread thread;
    private boolean running = false;
    public static long window;
    private String inputText;

    private Level level;
    private Scanner scanner = new Scanner(System.in);
    TextureLoader textureLoader;
    public static final Map<String, TextureLoader> mapWithTextures = new HashMap<>();

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        // Create the window
        window = glfwCreateWindow(width, height, "Project X", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
//        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);


        GL.createCapabilities();

        glfwMakeContextCurrent(window);

        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        Shader.loadAll();

        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);

        loadGraphicForObjects(pr_matrix);


    }

    private SuperHero getWizardType() {
        WizardFactory factory = new WizardFactory();

        return factory.createWizard(inputText);
    }

    private void checkedInput() {

        getInput();
        if (inputText != null) {
            Set<String> characters = new HashSet<>();
            characters.addAll(Arrays.asList("fire", "ice", "thunder"));
            boolean contains = characters.contains(inputText);

            if (!contains) {
                inputText = null;
                renderError();
            }
        }
    }


    private void getInput() {
        glfwPollEvents();
        glfwSetKeyCallback(HelloWorld.window, (window, key, scancode, action, mods) -> {

            if (key == GLFW_KEY_1 && action != GLFW_RELEASE) {
                inputText = "fire";
                renderText(TextUtil.CHOSE_FIREWIZARD);

            } else if (key == GLFW_KEY_2 && action != GLFW_RELEASE) {
                inputText = "ice";
                renderText(TextUtil.CHOSE_ICEWIZARD);

            } else if (key == GLFW_KEY_3 && action != GLFW_RELEASE) {
                inputText = "thunder";
                renderText(TextUtil.CHOSE_THUNDERWIZARD);

            } else
                inputText = "else";

    });


}


    private void loadGraphicForObjects(Matrix4f pr_matrix) {
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

    }

    public void run() {
        init();


        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        initializePlayerType();

        initializeWebsocketConnection();

        level = new Level(getWizardType());

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                glfwSetWindowTitle(window, "Project X | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
            if (glfwWindowShouldClose(window))
                running = false;
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void initializeWebsocketConnection() {
        renderWaitingForConnection();
        Application application = new Application();
        application.startWebsocket();
    }

    private void renderWaitingForConnection() {
        renderText(TextUtil.CONNECTION);
    }

    private void initializePlayerType() {

        running = false;
        do {
            renderChooseWizard();
            checkedInput();
            if (inputText != null) {
                running = true;
            }
        } while (!running);
    }

    private void renderChooseWizard() {
        renderText(TextUtil.ASK_FOR_CHAR);
    }

    private void renderError() {
        renderText(TextUtil.ERROR);
    }

    private void update() {
        glfwPollEvents();
        level.update();
    }

    private void renderText(String path) {
        textureLoader = new TextureLoader(path);

        textScheme(path);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        textureLoader.render();

        swapBuffers();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void textScheme(String path) {

        if (!mapWithTextures.containsKey(TextUtil.ASK_FOR_CHAR))
            mapWithTextures.put(path, textureLoader);
        if (!path.equals(TextUtil.ASK_FOR_CHAR)) {
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            mapWithTextures.put(generatedString, textureLoader);
        }
        if (mapWithTextures.size()==20){
            mapWithTextures.clear();
        }
    }


    private void render() {
        int error2 = glGetError();
        if (error2 != GL_NO_ERROR)
            System.out.println("ERROR2: " + error2);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        level.render();

        swapBuffers();
    }

    private void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);

        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new HelloWorld().start();
    }


}
