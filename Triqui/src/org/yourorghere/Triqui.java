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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;



/**
 * Triqui.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Triqui implements GLEventListener {
    int x,o;
    static int matriz [][] = new int [3][3];
    static boolean sombra [][] = new boolean [3][3];
    private static int posX = -1;
    private static int posY = -1;
    

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        
        canvas.addGLEventListener(new Triqui());
        canvas.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
                jugada();
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

        //canvas.addGLEventListener(new Triqui());
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
    
    private static void jugada (){
        if ( posX>134 && posX<238 && posY>45 && posY<145 && sombra[0][0] ){
            matriz[0][0] = 1;
            sombra[0][0] = false;
            maquina();
        }
        
        if ( posX>248 && posX<384 && posY>45 && posY<145 && sombra[0][1] ){
            matriz[0][1] = 1;
            sombra[0][1] = false;
            maquina();
        }
        
        if ( posX>393 && posX<499 && posY>45 && posY<145 && sombra[0][2] ){
            matriz[0][2] = 1;
            sombra[0][2] = false;
            maquina();
        }
        
        if ( posX>134 && posX<238 && posY>155 && posY<293 && sombra[1][0] ){
            matriz[1][0] = 1;
            sombra[1][0] = false;
            maquina();
        }
        
        if ( posX>248 && posX<384 && posY>155 && posY<293 && sombra[1][1] ){
            matriz[1][1] = 1;
            sombra[1][1] = false;
            maquina();
        }
        
        if ( posX>393 && posX<499 && posY>155 && posY<293 && sombra[1][2] ){
            matriz[1][2] = 1;
            sombra[1][2] = false;
            maquina();
        }
        
        if ( posX>134 && posX<238 && posY>301 && posY<404 && sombra[2][0] ){
            matriz[2][0] = 1;
            sombra[2][0] = false;
            maquina();
        }
        
        if ( posX>248 && posX<384 && posY>301 && posY<404 && sombra[2][1] ){
            matriz[2][1] = 1;
            sombra[2][1] = false;
            maquina();
        }
        
        if ( posX>393 && posX<499 && posY>301 && posY<404 && sombra[2][2] ){
            matriz[2][2] = 1;
            sombra[2][2] = false;
            maquina();
        }
    }
    
    private static void comprueba (){
        if (matriz[0][0]==0 && matriz[0][1]==0 && matriz[0][2]==0){
            System.out.println( "linea superior_H" );
        }
        
        if (matriz[1][0]==0 && matriz[1][1]==0 && matriz[1][2]==0){
            System.out.println( "linea media_H" );
        }
        
        if (matriz[2][0]==0 && matriz[2][1]==0 && matriz[2][2]==0){
            System.out.println( "linea inferior_H" );
        }
        
        if (matriz[0][0]==0 && matriz[1][0]==0 && matriz[2][0]==0){
            System.out.println( "linea izquierda_V" );
        }
        
        if (matriz[0][1]==0 && matriz[1][1]==0 && matriz[2][1]==0){
            System.out.println( "linea media_V" );
        }
        
        if (matriz[0][2]==0 && matriz[1][2]==0 && matriz[2][2]==0){
            System.out.println( "linea derecha_V" );
        }
        
        if (matriz[0][0]==0 && matriz[1][1]==0 && matriz[2][2]==0){
            System.out.println( "linea diagonal_1" );
        }
        
        if (matriz[0][2]==0 && matriz[1][1]==0 && matriz[2][0]==0){
            System.out.println( "linea diagonal_2" );
        }
    }
    
    private static void maquina (){
        if ( sombra[1][1] ){
            matriz[1][1] = 0;
            sombra[1][1] = false;
        }else{
            
        }
        comprueba();
    }
    
    private static Coordenada verifica (int v){
        Coordenada una = new Coordenada ();
        if (matriz[0][0]==v && matriz[0][1]==v){ una.x = 0; una.y = 2; return una; }
        if (matriz[0][0]==v && matriz[1][0]==v){ una.x = 2; una.y = 0; return una; }
        if (matriz[0][0]==v && matriz[1][1]==v){ una.x = 2; una.y = 2; return una; }
        
        if (matriz[0][1]==v && matriz[0][2]==v){ una.x = 0; una.y = 0; return una; }
        if (matriz[0][1]==v && matriz[1][1]==v){ una.x = 2; una.y = 1; }
        
        if (matriz[0][2]==v && matriz[1][1]==v){ una.x = 2; una.y = 0; return una; }
        if (matriz[0][2]==v && matriz[1][2]==v){ una.x = 2; una.y = 2; return una; }
        
        if (matriz[1][0]==v && matriz[1][1]==v){ una.x = 1; una.y = 2; return una; }
        if (matriz[1][0]==v && matriz[2][0]==v){ una.x = 0; una.y = 0; return una; }
        
        if (matriz[1][1]==v && matriz[1][2]==v){ una.x = 1; una.y = 0; return una; }
        if (matriz[1][1]==v && matriz[2][2]==v){ una.x = 0; una.y = 0; return una; }
        if (matriz[1][1]==v && matriz[2][1]==v){ una.x = 0; una.y = 1; return una; }
        if (matriz[1][1]==v && matriz[2][0]==v){ una.x = 0; una.y = 2; return una; }
        
        if (matriz[2][0]==v && matriz[2][1]==v){ una.x = 2; una.y = 2; return una; }
        
        if (matriz[2][1]==v && matriz[2][2]==v){ una.x = 2; una.y = 0; return una; }
        
        return una;
    }
    
    private static void bloquear(){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                sombra[i][j] = false;
            }
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
        
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                matriz[i][j] = -1;
                sombra[i][j] = true;
            }
        }
        
        File img;
        Texture tex;
        
        
        try {
            
            img = new File("src/img/x.png");
            tex = TextureIO.newTexture(img, true);
            x = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/o.png");
            tex = TextureIO.newTexture(img, true);
            o = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
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
        gl.glTranslatef(0.0f, 0.0f, -6.0f);

        gl.glLineWidth(5);
	gl.glColor3f(1.0f, 1.0f, 1.0f);
	gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(2.0,0.8);
            gl.glVertex2d(-2.0,0.8);
            gl.glVertex2d(2.0,-0.8);
            gl.glVertex2d(-2.0,-0.8);
            gl.glVertex2d(0.8,2.0);
            gl.glVertex2d(0.8,-2.0);
            gl.glVertex2d(-0.8,2.0);
            gl.glVertex2d(-0.8,-2.0);
	gl.glEnd();
        
        
        /*gl.glEnable(GL.GL_TEXTURE_2D);
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, x);
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 4.0f, -6.0f);  // Top Left
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 4.0f, -6.0f);   // Top Right
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, 2.0f, -6.0f);  // Bottom Right
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, 2.0f, -6.0f); // Bottom Left
            // Done Drawing The Quad
        gl.glEnd();
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, x);
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, -6.0f);  // Top Left
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, -6.0f);   // Top Right
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, -6.0f);  // Bottom Right
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, -6.0f); // Bottom Left
            // Done Drawing The Quad
        gl.glEnd(); 
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, x);
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-4.0f, 1.0f, -6.0f);  // Top Left
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(-2.0f, 1.0f, -6.0f);   // Top Right
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-2.0f, -1.0f, -6.0f);  // Bottom Right
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-4.0f, -1.0f, -6.0f); // Bottom Left
            // Done Drawing The Quad
        gl.glEnd(); 
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, x);
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(2.0f, 1.0f, -6.0f);  // Top Left
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(4.0f, 1.0f, -6.0f);   // Top Right
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(4.0f, -1.0f, -6.0f);  // Bottom Right
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(2.0f, -1.0f, -6.0f); // Bottom Left
            // Done Drawing The Quad
        gl.glEnd(); 
        
        
        gl.glDisable(GL.GL_TEXTURE_2D);*/
        
        gl.glEnable(GL.GL_TEXTURE_2D);
        float ej_x = -4.0f, ej_y = 2.0f;
        for (int i=0; i<3; i++){
            ej_x = -4.0f;
            for (int j=0; j<3; j++){
                if (matriz[i][j] != -1){
                    int aux;
                    if (matriz[i][j] == 0){
                        aux = o;
                    }else{
                        aux = x;
                    }
                    gl.glBindTexture(GL.GL_TEXTURE_2D, aux);
                    gl.glBegin(GL.GL_QUADS);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3f(ej_x, ej_y, -6.0f);  // Top Left
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3f(ej_x+2, ej_y, -6.0f);   // Top Right
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3f(ej_x+2, ej_y+2, -6.0f);  // Bottom Right
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3f(ej_x, ej_y+2, -6.0f); // Bottom Left
                    // Done Drawing The Quad
                    gl.glEnd(); 
                }
                ej_x = ej_x + 3;
            }
            ej_y = ej_y - 3;
        }
        gl.glDisable(GL.GL_TEXTURE_2D);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public static class Coordenada {
        private int x;
        private int y;
        
        public Coordenada ( int x, int y ){
            this.x = x;
            this.y = y;
        }
        
        public Coordenada (){
            this.x = -1;
            this.y = -1;
        }
    }
}

