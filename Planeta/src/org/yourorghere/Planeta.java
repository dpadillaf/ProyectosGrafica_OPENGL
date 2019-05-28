package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;



/**
 * Planeta.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Planeta implements GLEventListener {

    float x;
    private int tierra, luna, estrella;
    float [] ranX = new float[20];
    float [] ranY = new float[20];
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Planeta());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        x=-1.5f;
        // Enable VSync
        gl.setSwapInterval(1);
        
        File img;
        Texture tex;
        gl.glEnable(GL.GL_TEXTURE_2D); 
           
        for (int i=0; i<20; i++){
            ranX[i] = getRandom();
            ranY[i] = getRandom();
        }
        
        try {
            
            img = new File("src/img/tierra.png");
            tex = TextureIO.newTexture(img, true);
            tierra = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Planeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Planeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/luna.png");
            tex = TextureIO.newTexture(img, true);
            luna = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Planeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Planeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/estrella.png");
            tex = TextureIO.newTexture(img, true);
            estrella = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Planeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Planeta.class.getName()).log(Level.SEVERE, null, ex);
        }
                

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        gl.glTranslatef(1.5f*x, 0.0f, -6.0f);
        gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        
        
        for (int i=0; i<20; i++){
            gl.glBindTexture(GL.GL_TEXTURE_2D, estrella);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-2.0f+ranX[i], -2.0f+ranY[i], 0.0f);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(-2.0f+ranX[i]+0.4f, -2.0f+ranY[i], 0.0f);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(-2.0f+ranX[i]+0.4f, -2.0f+ranY[i]+0.4f, 0.0f);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-2.0f+ranX[i], -2.0f+ranY[i]+0.4f, 0.0f); // Bottom Left
                // Done Drawing The Quad
            gl.glEnd();
        }
        
        // Drawing Using Triangles
        gl.glBindTexture(GL.GL_TEXTURE_2D, tierra);
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, 0.0f);  // Top Left
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, 0.0f);   // Top Right
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, 0.0f);  // Bottom Right
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
            // Done Drawing The Quad
        gl.glEnd();
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, luna);
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-2.0f, -2.0f, 0.0f);  // Top Left
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(-3.0f, -2.0f, 0.0f);   // Top Right
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-3.0f, -1.0f, 0.0f);  // Bottom Right
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-2.0f, -1.0f, 0.0f); // Bottom Left
            // Done Drawing The Quad
        gl.glEnd();

        //x=x+0.01f;
        //System.out.println(x);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    public static float getRandom() {
        Random rand = new Random();
        return  -4 + ( 4 - (-4) ) * rand.nextFloat();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

