package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
 * Globos.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Globos implements GLEventListener {
    int g1, g2, g3, b;
    int vel = 2;
    int c_g1 = 0, c_g2 = 0, c_g3 = 0, c_b1 = 0, c_b2 = 0, c_b3 = 0;
    boolean aux3 = false;
    private static int posX = -1;
    private static int posY = -1;
    
    static Globo gb1 = new Globo (true);
    static Globo gb2 = new Globo (false);
    static Globo gb3 = new Globo (false);
    
    static Boom b1 = new Boom ();
    static Boom b2 = new Boom ();
    static Boom b3 = new Boom ();

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Globos());
        canvas.addMouseListener(new MouseListener() {
        
        public void mouseClicked(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
                explotar ();
            }

            public void mousePressed(MouseEvent e) {
                
            }

            public void mouseReleased(MouseEvent e) {
                
            }

            public void mouseEntered(MouseEvent e) {
                
            }

            public void mouseExited(MouseEvent e) {
                
            }
        });
        
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
        frame.setResizable(false);
        animator.start();
        
    }
    
    static void explotar (){
        if (posX < gb1.x && posX > (gb1.x-60) && posY < gb1.y && posY > (gb1.y-75)){
            gb1.esta = false;
            b1.x = gb1.x;
            b1.y = gb1.y;
            b1.esta = true;
        }
        if (posX < gb2.x && posX > (gb2.x-60) && posY < gb2.y && posY > (gb2.y-75)){
            gb2.esta = false;
            b2.x = gb2.x;
            b2.y = gb2.y;
            b2.esta = true;
        }
        if (posX < gb3.x && posX > (gb3.x-60) && posY < gb3.y && posY > (gb3.y-75)){
            gb3.esta = false;
            b3.x = gb3.x;
            b3.y = gb3.y;
            b3.esta = true;
        }
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
        File img;
        Texture tex;
        
        try {
            
            img = new File("src/img/globo1.png");
            tex = TextureIO.newTexture(img, true);
            g1 = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/globo2.png");
            tex = TextureIO.newTexture(img, true);
            g2 = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/globo3.png");
            tex = TextureIO.newTexture(img, true);
            g3 = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/boom.png");
            tex = TextureIO.newTexture(img, true);
            b = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Globos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        glu.gluOrtho2D(0, width, height, 0);
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
        gl.glTranslatef(0, 0, 0);
        
        
        gl.glEnable(GL.GL_TEXTURE_2D);
        if (gb1.esta){
            gl.glBindTexture(GL.GL_TEXTURE_2D, g1);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(gb1.x, gb1.y);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(gb1.x-60, gb1.y);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(gb1.x-60, gb1.y-75);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(gb1.x, gb1.y-75); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            gb1.y = gb1.y-vel;
        }else{
            c_g1++;
            if (c_g1 > 60){
                gb1 = new Globo (true);
                c_g1 = 0;
            }
        }
        
        if (gb2.esta){
            gl.glBindTexture(GL.GL_TEXTURE_2D, g2);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(gb2.x, gb2.y);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(gb2.x-60, gb2.y);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(gb2.x-60, gb2.y-75);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(gb2.x, gb2.y-75); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            gb2.y = gb2.y-vel;
            aux3 = true;
        }else{
            c_g2++;
            if (c_g2 > 60){
                gb2 = new Globo (true);
                c_g2 = 0;
            }
        }
        
        if (gb3.esta){
            gl.glBindTexture(GL.GL_TEXTURE_2D, g3);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(gb3.x, gb3.y);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(gb3.x-60, gb3.y);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(gb3.x-60, gb3.y-75);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(gb3.x, gb3.y-75); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            gb3.y = gb3.y-vel;
        }else if (!aux3){
            
        }else{
            c_g3++;
            if (c_g3 > 60){
                gb3 = new Globo (true);
                c_g3 = 0;
            }
        }
        
        if (b1.esta){
            gl.glBindTexture(GL.GL_TEXTURE_2D, b);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(b1.x, b1.y);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(b1.x-60, b1.y);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(b1.x-60, b1.y-75);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(b1.x, b1.y-75); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            c_g1++;
            if (c_g1 > 30){
                c_g1 = 0;
                b1.esta = false;
            }
        }
        
        if (b2.esta){
            gl.glBindTexture(GL.GL_TEXTURE_2D, b);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(b2.x, b2.y);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(b2.x-60, b2.y);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(b2.x-60, b2.y-75);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(b2.x, b2.y-75); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            c_g2++;
            if (c_g2 > 30){
                c_g2 = 0;
                b2.esta = false;
            }
        }
        
        if (b3.esta){
            gl.glBindTexture(GL.GL_TEXTURE_2D, b);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(b3.x, b3.y);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(b3.x-60, b3.y);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(b3.x-60, b3.y-75);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(b3.x, b3.y-75); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            c_g3++;
            if (c_g3 > 30){
                c_g3 = 0;
                b3.esta = false;
            }
        }
        
        gl.glDisable(GL.GL_TEXTURE_2D);
        
        if (gb1.y < 0){ gb1 = new Globo (true); }
        if (gb2.y < 0){ gb2 = new Globo (true); }
        if (gb3.y < 0){ gb3 = new Globo (true); }

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    static class Globo {
        
        private int x;
        private int y;
        private boolean esta;
        
        public Globo (boolean b){
            this.esta = b;
            this.y = 490;
            this.x = (int) (Math.random() * 610) + 30;
            System.out.println(this.x);
        }
    }
    
    static class Boom {
        private int x;
        private int y;
        private boolean esta;
        
        public Boom (){
            this.x = 0;
            this.y = 0;
            this.esta = false;
        }
    }
}

