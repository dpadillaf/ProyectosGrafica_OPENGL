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
 * TerceraClase.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Carro implements GLEventListener {
    
    int amanece = 0;
    boolean dia = true;
    static float r1, r2, e1, e2, e3, e4;
    int vaca;
    int cambio=-300; float cambio2=0;
    public static void main(String[] args) {
        Frame frame = new Frame("Carrito Animado");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Carro());
        frame.add(canvas);
        frame.setSize(840, 540);
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
            
            img = new File("src/img/vaca.png");
            tex = TextureIO.newTexture(img, true);
            vaca = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        reiniPos();
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
        
        if (amanece < 400){
            amanece++;
        }else{
            if (dia){
               dia = false;
               reiniPos();
            }else{
                dia = true;
            }
            amanece = 0;
        }
        
        
        
        gl.glTranslatef(3.0f, 1.5f, -6.0f);//---------------------------->
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f, 0.0f, 0.0f);    // Negro
            gl.glVertex3f(-8.0f, 11.0f, 0.0f);  // Top Left
            if(dia){ gl.glColor3f(0.0f, 1.0f, 1.0f); }else{ gl.glColor3f(0.0f, 0.274f, 0.549f); }    // Azul
            gl.glVertex3f(2.0f, 11.0f, 0.0f);   // Top Right
            if(dia){ gl.glColor3f(0.0f, 1.0f, 1.0f); }else{ gl.glColor3f(0.0f, 0.274f, 0.549f); }    // Azul    // Azul
            gl.glVertex3f(2.0f, -11.0f, 0.0f);  // Bottom Right
            if(dia){ gl.glColor3f(0.0f, 1.0f, 1.0f); }else{ gl.glColor3f(0.0f, 0.0f, 0.0f); }    // Negro
            gl.glVertex3f(-8.0f, -11.0f, 0.0f); // Bottom Left
        gl.glEnd();
        
        
        
//-------------------Luna-------------------
        gl.glColor3f(0.6f, 0.78f, 0.90f);
        gl.glBegin(GL.GL_POLYGON);
            for(int i=0; i<100; i++){
                float x = (float) Math.cos(i*2*3.1415/100);
                float y = (float) Math.sin(i*2*3.1415/100);
                gl.glVertex2f(x, y);
                if(dia){ gl.glColor3f(1.0f, 1.0f, 0.0f); }else{ gl.glColor3f(1.0f, 1.0f, 1.0f); }
            }
        gl.glEnd();
        
        gl.glTranslatef(-3.0f, -4.0f, -6.0f);//---------------------------->
        
        if (!dia){
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glPointSize(3);
            gl.glBegin(GL.GL_POINTS);
                gl.glVertex2d(e1,5);
            gl.glEnd();

            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glPointSize(3);
            gl.glBegin(GL.GL_POINTS);
                gl.glVertex2d(e2,4);
            gl.glEnd();

            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glPointSize(3);
            gl.glBegin(GL.GL_POINTS);
                gl.glVertex2d(e3,3);
            gl.glEnd();

            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glPointSize(3);
            gl.glBegin(GL.GL_POINTS);
                gl.glVertex2d(e4,6);
            gl.glEnd();
        }
        
//-------------------Pasto Arriba-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f, 0.501f, 0.0f);//VERDE
            gl.glVertex3f(-10.0f, 0.5f, 0.0f);  // Top Left
            gl.glVertex3f(10.0f, 0.5f, 0.0f);   // Top Right
            gl.glVertex3f(10.0f, 0.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-10.0f, 0.0f, 0.0f); // Bottom Left
        gl.glEnd();
//-------------------Montaña 1-------------------        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Blanco
            gl.glVertex3f(-8.0f, 3.5f, 0.0f);   // Arriba
            gl.glColor3f(0.509f, 0.243f, 0.243f);    // Cafe
            gl.glVertex3f(-11.0f, 0.5f, 0.0f); // Izquierdo
            gl.glColor3f(0.509f, 0.243f, 0.243f);   // Cafe
            gl.glVertex3f(-5.0f, 0.5f, 0.0f);  // Derecho
        gl.glEnd();
        
//-------------------Montaña 2-------------------        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Blanco
            gl.glVertex3f(-4.5f, 3.0f, 0.0f);   // Arriba
            gl.glColor3f(0.509f, 0.243f, 0.243f);    // Cafe
            gl.glVertex3f(-6.0f, 0.5f, 0.0f); // Izquierdo
            gl.glColor3f(0.509f, 0.243f, 0.243f);   // Cafe
            gl.glVertex3f(-3.0f, 0.5f, 0.0f);  // Derecho
        gl.glEnd();

//-------------------Montaña 3-------------------        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Blanco
            gl.glVertex3f(-1.0f, 3.0f, 0.0f);   // Arriba
            gl.glColor3f(0.509f, 0.243f, 0.243f);    // Cafe
            gl.glVertex3f(-3.5f, 0.5f, 0.0f); // Izquierdo
            gl.glColor3f(0.509f, 0.243f, 0.243f);   // Cafe
            gl.glVertex3f(1.0f, 0.5f, 0.0f);  // Derecho
        gl.glEnd();

//-------------------Montaña 4-------------------        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Blanco
            gl.glVertex3f(2.0f, 2.5f, 0.0f);   // Arriba
            gl.glColor3f(0.509f, 0.243f, 0.243f);    // Cafe
            gl.glVertex3f(0.8f, 0.5f, 0.0f); // Izquierdo
            gl.glColor3f(0.509f, 0.243f, 0.243f);   // Cafe
            gl.glVertex3f(3.5f, 0.5f, 0.0f);  // Derecho
        gl.glEnd();

//-------------------Montaña 5-------------------        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Blanco
            gl.glVertex3f(4.0f, 3.0f, 0.0f);   // Arriba
            gl.glColor3f(0.509f, 0.243f, 0.243f);    // Cafe
            gl.glVertex3f(3.0f, 0.5f, 0.0f); // Izquierdo
            gl.glColor3f(0.509f, 0.243f, 0.243f);   // Cafe
            gl.glVertex3f(7.0f, 0.5f, 0.0f);  // Derecho
        gl.glEnd();

//-------------------Montaña 6-------------------        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Blanco
            gl.glVertex3f(7.5f, 3.0f, 0.0f);   // Arriba
            gl.glColor3f(0.509f, 0.243f, 0.243f);    // Cafe
            gl.glVertex3f(6.0f, 0.5f, 0.0f); // Izquierdo
            gl.glColor3f(0.509f, 0.243f, 0.243f);   // Cafe
            gl.glVertex3f(10.0f, 0.5f, 0.0f);  // Derecho
        gl.glEnd();
        
//-------------------Casa------------------- 
    gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.8f, 0.8f, 0.8f);//Blanco
            gl.glVertex3f(-8.0f, 1.75f, 0.0f);  // Top Left
            gl.glVertex3f(-5.5f, 1.75f, 0.0f);   // Top Right
            gl.glVertex3f(-5.5f, 0.25f, 0.0f);  // Bottom Right
            gl.glVertex3f(-8.0f, 0.25f, 0.0f); // Bottom Left
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(0.5f, 0.0f, 0.5f);    // Blanco
            gl.glVertex3f(-6.75f, 2.75f, 0.0f);   // Arriba
            gl.glVertex3f(-8.2f, 1.75f, 0.0f); // Izquierdo
            gl.glVertex3f(-5.3f, 1.75f, 0.0f);  // Derecho
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.5f, 0.0f, 0.5f);//
            gl.glVertex3f(-7.25f, 1.25f, 0.0f);  // Top Left
            gl.glVertex3f(-6.25f, 1.25f, 0.0f);   // Top Right
            gl.glVertex3f(-6.25f, 0.25f, 0.0f);  // Bottom Right
            gl.glVertex3f(-7.25f, 0.25f, 0.0f); // Bottom Left
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
            if(dia){ gl.glColor3f(1.0f, 1.0f, 1.0f); }else{ gl.glColor3f(1.0f, 1.0f, 0.0f); }
            gl.glVertex3f(-7.9f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(-7.35f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(-7.35f, 1.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-7.9f, 1.0f, 0.0f); // Bottom Left
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
            if(dia){ gl.glColor3f(1.0f, 1.0f, 1.0f); }else{ gl.glColor3f(1.0f, 1.0f, 0.0f); }
            gl.glVertex3f(-6.15f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(-5.6f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(-5.6f, 1.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-6.15f, 1.0f, 0.0f); // Bottom Left
        gl.glEnd();
        
//-----------------vaca----------------------
            gl.glEnable(GL.GL_TEXTURE_2D);
            if(dia){
                gl.glBindTexture(GL.GL_TEXTURE_2D, vaca);
                gl.glBegin(GL.GL_QUADS);
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3f(r1, 1.0f, 0.0f);  // Top Left
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3f(r1+1, 1.0f, 0.0f);   // Top Right
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3f(r1+1, 0.5f, 0.0f);  // Bottom Right
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3f(r1, 0.5f, 0.0f); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();
                
                gl.glBindTexture(GL.GL_TEXTURE_2D, vaca);
                gl.glBegin(GL.GL_QUADS);
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3f(r2, 1.0f, 0.0f);  // Top Left
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3f(r2+1, 1.0f, 0.0f);   // Top Right
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3f(r2+1, 0.5f, 0.0f);  // Bottom Right
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3f(r2, 0.5f, 0.0f); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();
            }else{
              gl.glBindTexture(GL.GL_TEXTURE_2D, vaca);
                gl.glBegin(GL.GL_QUADS);
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3f(-3.8f, 1.0f, 0.0f);  // Top Left
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3f(-2.8f, 1.0f, 0.0f);   // Top Right
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3f(-2.8f, 0.5f, 0.0f);  // Bottom Right
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3f(-3.8f, 0.5f, 0.0f); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();

                gl.glBindTexture(GL.GL_TEXTURE_2D, vaca);
                gl.glBegin(GL.GL_QUADS);
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3f(-2.5f, 1.0f, 0.0f);  // Top Left
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3f(-1.5f, 1.0f, 0.0f);   // Top Right
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3f(-1.5f, 0.5f, 0.0f);  // Bottom Right
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3f(-2.5f, 0.5f, 0.0f); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();  
            }
            gl.glDisable(GL.GL_TEXTURE_2D);

        
//-------------------Corral-------------------
   gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.1f, 0.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-4.0,0.25);
            gl.glVertex2d(-4.0, 0.75);
    gl.glEnd(); 
    
    gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.1f, 0.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-3.0,0.25);
            gl.glVertex2d(-3.0, 0.75);
    gl.glEnd(); 
    
    gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.1f, 0.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-2.0,0.25);
            gl.glVertex2d(-2.0, 0.75);
    gl.glEnd(); 
    
    gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.1f, 0.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-1.0,0.25);
            gl.glVertex2d(-1.0, 0.75);
    gl.glEnd();
    
    gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.1f, 0.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-4.0,0.40);
            gl.glVertex2d(-1.0, 0.40);
    gl.glEnd();
    
    gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.1f, 0.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-4.0,0.60);
            gl.glVertex2d(-1.0, 0.60);
    gl.glEnd();


        
//-------------------Poste 1-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.674f);
            gl.glVertex3f(-5.5f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(-5.3f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(-5.3f, 0.1f, 0.0f);  // Bottom Right
            gl.glVertex3f(-5.5f, 0.1f, 0.0f); // Bottom Left
        gl.glEnd();
        //Lampara 1
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.68f);
            gl.glVertex3f(-5.7f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(-5.1f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(-5.1f, 1.4f, 0.0f);  // Bottom Right
            gl.glVertex3f(-5.7f, 1.4f, 0.0f); // Bottom Left
        gl.glEnd();
        //Luz 1
        if(!dia){
            gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-5.7,1.4);
            gl.glVertex2d(-5.1, 1.4);
        gl.glEnd();
        }
        
        
//-------------------Poste 2-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.674f);
            gl.glVertex3f(-2.0f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(-1.8f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(-1.8f, 0.1f, 0.0f);  // Bottom Right
            gl.glVertex3f(-2.0f, 0.1f, 0.0f); // Bottom Left
        gl.glEnd();
        //Lampara 2
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.68f);
            gl.glVertex3f(-2.2f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(-1.6f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(-1.6f, 1.4f, 0.0f);  // Bottom Right
            gl.glVertex3f(-2.2f, 1.4f, 0.0f); // Bottom Left
        gl.glEnd();
        //Luz 2
        if (!dia){
            gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-2.2,1.4);
            gl.glVertex2d(-1.6, 1.4);
        gl.glEnd();
        }
        
        
//-------------------Poste 3-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.674f);
            gl.glVertex3f(1.5f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(1.7f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(1.7f, 0.1f, 0.0f);  // Bottom Right
            gl.glVertex3f(1.5f, 0.1f, 0.0f); // Bottom Left
        gl.glEnd();
        //Lampara 3
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.68f);
            gl.glVertex3f(1.3f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(1.9f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(1.9f, 1.4f, 0.0f);  // Bottom Right
            gl.glVertex3f(1.3f, 1.4f, 0.0f); // Bottom Left
        gl.glEnd();
        //Luz 3
        if (!dia){
            gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(1.3,1.4);
            gl.glVertex2d(1.9, 1.4);
            gl.glEnd();
        }
        
        
//-------------------Poste 4-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.674f);
            gl.glVertex3f(5.0f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(5.2f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(5.2f, 0.1f, 0.0f);  // Bottom Right
            gl.glVertex3f(5.0f, 0.1f, 0.0f); // Bottom Left
        gl.glEnd();
        //Lampara 4
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.325f, 0.56f, 0.68f);
            gl.glVertex3f(4.8f, 1.5f, 0.0f);  // Top Left
            gl.glVertex3f(5.4f, 1.5f, 0.0f);   // Top Right
            gl.glVertex3f(5.4f, 1.4f, 0.0f);  // Bottom Right
            gl.glVertex3f(4.8f, 1.4f, 0.0f); // Bottom Left
        gl.glEnd();
        //Luz 4
        if(!dia){
           gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(4.8,1.4);
            gl.glVertex2d(5.4, 1.4);
            gl.glEnd(); 
        }
        
        
        
//-------------------Calzada-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.556f, 0.552f, 0.572f);//GRIS
            gl.glVertex3f(-10.0f, 0.0f, 0.0f);  // Top Left
            gl.glVertex3f(10.0f, 0.0f, 0.0f);   // Top Right
            gl.glVertex3f(10.0f, -1.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-10.0f, -1.0f, 0.0f); // Bottom Left
        gl.glEnd();
        
//-------------------Pasto Abajo-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f, 0.501f, 0.0f);//VERDE
            gl.glVertex3f(-10.0f, -1.0f, 0.0f);  // Top Left
            gl.glVertex3f(10.0f, -1.0f, 0.0f);   // Top Right
            gl.glVertex3f(10.0f, -3.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-10.0f, -3.0f, 0.0f); // Bottom Left
        gl.glEnd();
        
        
        gl.glLineWidth(3);
//-------------------Linea Carretera 1-------------------
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 0.5f, 0.0f);//NARANJA
            gl.glVertex2d(-10,0);
            gl.glVertex2d(10, 0);
        gl.glEnd();

//-------------------Lineas extras-------------------        
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-8,-0.5);
            gl.glVertex2d(-7, -0.5);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-6,-0.5);
            gl.glVertex2d(-5,-0.5);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-4,-0.5);
            gl.glVertex2d(-3,-0.5);
        gl.glEnd();
        
         gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(-2,-0.5);
            gl.glVertex2d(-1,-0.5);
        gl.glEnd();
        
         gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(0,-0.5);
            gl.glVertex2d(1,-0.5);
        gl.glEnd();
        
         gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(2,-0.5);
            gl.glVertex2d(3,-0.5);
        gl.glEnd();
        
         gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(4,-0.5);
            gl.glVertex2d(5,-0.5);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(6,-0.5);
            gl.glVertex2d(7,-0.5);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex2d(8,-0.5);
            gl.glVertex2d(9,-0.5);
        gl.glEnd();
       
//-------------------Linea Carretera 2-------------------
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 0.5f, 0.0f);//NARANJA
            gl.glVertex2d(-10,-1.0);
            gl.glVertex2d(10, -1.0);
        gl.glEnd();

        
        cambio++;
        
        //efecto para el cambio.
        gl.glTranslatef(0.05f*cambio, -1.0f, -6.0f);//---------------------------->
        gl.glScalef(0.5f, 0.5f, 0.0f);
//-------------------Cuerpo del carro-------------------
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.94f, 0.054f, 0.074f);    // Set the current drawing color to light blue
            gl.glVertex3f(-1.3f, 0.5f, 0.0f);  // Top Left
            gl.glVertex3f(3.0f, 0.5f, 0.0f);   // Top Right
            gl.glVertex3f(3.0f, -1.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-1.3f, -1.0f, 0.0f); // Bottom Left
        gl.glEnd();
        
        // Cuadro secundario del carro.
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.94f, 0.054f, 0.074f);    // Set the current drawing color to light blue
            gl.glVertex3f(-1.0f, 1.2f, 0.0f);  // Top Left
            gl.glVertex3f(2.0f, 1.2f, 0.0f);   // Top Right
            gl.glVertex3f(2.0f, -1.0f, 0.0f);  // Bottom Right
            gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
        gl.glEnd();

        // Cuadro, Ventana del carro.
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 0.0f);//AMARILLO
            gl.glVertex3f(-0.6f, 0.0f, 0.0f);  // Top Left
            gl.glVertex3f(1.2f, 0.0f, 0.0f);   // Top Right
            gl.glVertex3f(1.2f, 0.6f, 0.0f);  // Bottom Right
            gl.glVertex3f(-0.6f, 0.6f, 0.0f); // Bottom Left
        gl.glEnd();
        
//-------------------Llanta 1-------------------
        gl.glColor3f(0.501f, 0.501f, 0.501f);
        gl.glTranslatef(-0.5f, -1.0f, -6.0f);//---------------------------->
        gl.glScalef(0.5f, 0.5f, 0.0f);
        gl.glRotated(10.0*cambio, 0.0f, 0.0f, -0.5f);
        gl.glBegin(GL.GL_POLYGON);
            
            for(int i=0; i<100; i++){
                float x = (float) Math.cos(i*2*3.1415/100);
                float y = (float) Math.sin(i*2*3.1415/100);
                gl.glVertex2f(x, y);
                gl.glColor3f(0.0f, 0.0f, 0.0f);
            }
        gl.glEnd();
        
//-------------------Llanta 2-------------------
        gl.glColor3f(0.501f, 0.501f, 0.501f);
        gl.glRotated(10.0*cambio, 0.0f, 0.0f, 0.5f);//para contrarestar el sentido del movimiento anterior
        gl.glTranslatef(5.5f, 0.0f, -6.0f);//---------------------------->
        gl.glRotated(10.0*cambio, 0.0f, 0.0f, -0.5f);
        gl.glBegin(GL.GL_POLYGON);
            
            for(int i=0; i<100; i++){
                float x = (float) Math.cos(i*2*3.1415/100);
                float y = (float) Math.sin(i*2*3.1415/100);
                gl.glVertex2f(x, y);
                gl.glColor3f(0.0f, 0.0f, 0.0f);
            }
        gl.glEnd();
        
        if(cambio==300){
            cambio=-300;
        }
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    public static float getRandom() {
        Random rand = new Random();
        return  -1 + ( 6 - (-1) ) * rand.nextFloat();
    }
    
    public static float getRandom2() {
        Random rand = new Random();
        return  -9 + ( 9 - (-1) ) * rand.nextFloat();
    }
    
    private static void reiniPos(){
        r1 = getRandom();
        r2 = getRandom();
        e1 = getRandom2();
        e2 = getRandom2();
        e3 = getRandom2();
        e4 = getRandom2();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}