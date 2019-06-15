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
    int x,o, los, res, nul;
    static int linea=7;
    static int matriz [][] = new int [3][3];
    static boolean sombra [][] = new boolean [3][3];
    private static int posX = -1;
    private static int posY = -1;
    static boolean lose = false, reset = false, nulle = false, dib_lin = true;
    
    

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
        
        if ( posX>527 && posX<611 && posY>328 && posY<405 && reset ){
            resetear();
        }
    }
    
    private static void comprueba (){
        if (matriz[0][0]==0 && matriz[0][1]==0 && matriz[0][2]==0){
            dib_lin = true;
            linea = 1;
            lose = true;
            reset = true;
        }
        
        if (matriz[1][0]==0 && matriz[1][1]==0 && matriz[1][2]==0){
            dib_lin = true;
            linea = 2;
            lose = true;
            reset = true;
        }
        
        if (matriz[2][0]==0 && matriz[2][1]==0 && matriz[2][2]==0){
            dib_lin = true;
            linea = 3;
            lose = true;
            reset = true;
        }
        
        if (matriz[0][0]==0 && matriz[1][0]==0 && matriz[2][0]==0){
            dib_lin = true;
            linea = 4;
            lose = true;
            reset = true;
        }
        
        if (matriz[0][1]==0 && matriz[1][1]==0 && matriz[2][1]==0){
            dib_lin = true;
            linea = 5;
            lose = true;
            reset = true;
        }
        
        if (matriz[0][2]==0 && matriz[1][2]==0 && matriz[2][2]==0){
            dib_lin = true;
            linea = 6;
            lose = true;
            reset = true;
        }
        
        if (matriz[0][0]==0 && matriz[1][1]==0 && matriz[2][2]==0){
            dib_lin = true;
            linea = 7;
            lose = true;
            reset = true;
        }
        
        if (matriz[0][2]==0 && matriz[1][1]==0 && matriz[2][0]==0){
            dib_lin = true;
            linea = 8;
            lose = true;
            reset = true;
        }
        
        if (nulo()){
            reset = true;
            nulle = true;
        }
    }
    
    static private boolean nulo (){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (sombra[i][j]){ return false; }
            }
        }
        return true;
    }
    
    private static void maquina (){
        if ( sombra[1][1] ){
            matriz[1][1] = 0;
            sombra[1][1] = false;
        }else{
            Coordenada verM = verifica (0);
            Coordenada verJ = verifica (1);
            //System.out.println(verM);
            //System.out.println(verJ);
            if (verM.x != -1 && sombra[verM.x][verM.y]){
                matriz[verM.x][verM.y] = 0;
                sombra[verM.x][verM.y] = false;
            }else if (verJ.x != -1 && sombra[verJ.x][verJ.y]){
                matriz[verJ.x][verJ.y] = 0;
                sombra[verJ.x][verJ.y] = false;
            }else if (matriz[0][0]==1 && matriz[2][2]==1 && sombra[0][1] && sombra[0][2] && sombra[1][0]
                    && sombra[1][2] && sombra[2][0] && sombra[2][1]){
                Coordenada cor = posicion(0);
                matriz[cor.x][cor.y] = 0;
                sombra[cor.x][cor.y] = false;
            }else if (matriz[0][2]==1 && matriz[2][0]==1 && sombra[0][1] && sombra[0][0] && sombra[1][0]
                    && sombra[1][2] && sombra[2][2] && sombra[2][1]){
                Coordenada cor = posicion(0);
                matriz[cor.x][cor.y] = 0;
                sombra[cor.x][cor.y] = false;
            }else if (matriz[0][1]==1 && matriz[1][0]==1 && sombra[0][2] && sombra[0][0] && sombra[2][0]
                    && sombra[1][2] && sombra[2][2] && sombra[2][1]){
                matriz[0][0] = 0;
                sombra[0][0] = false;
            }else if (matriz[2][1]==1 && matriz[1][0]==1 && sombra[0][2] && sombra[0][0] && sombra[2][0]
                    && sombra[1][2] && sombra[2][2] && sombra[0][1]){
                matriz[2][0] = 0;
                sombra[2][0] = false;
            }else if (matriz[2][1]==1 && matriz[1][2]==1 && sombra[0][2] && sombra[0][0] && sombra[2][0]
                    && sombra[1][0] && sombra[2][2] && sombra[0][1]){
                matriz[2][2] = 0;
                sombra[2][2] = false;
            }else if (matriz[0][1]==1 && matriz[1][2]==1 && sombra[0][2] && sombra[0][0] && sombra[2][0]
                    && sombra[1][0] && sombra[2][2] && sombra[2][1]){
                matriz[0][2] = 0;
                sombra[0][2] = false;
            }else{
                if (sombra[0][0] || sombra[0][2] || sombra[2][0] || sombra[2][2]){
                    Coordenada cor = posicion(1);
                    matriz[cor.x][cor.y] = 0;
                    sombra[cor.x][cor.y] = false;
                    System.out.println("entra aqui1 "+cor.x+" "+cor.y);
                }else if (sombra[0][1] || sombra[1][0] || sombra[1][2] || sombra[2][1]){
                    Coordenada cor = posicion(0);
                    matriz[cor.x][cor.y] = 0;
                    sombra[cor.x][cor.y] = false;
                    System.out.println("entra aqui4 "+cor.x+" "+cor.y);
                }
            }
            
        }
        comprueba();
    }
    
    private static Coordenada verifica (int v){
        Coordenada una = new Coordenada ();
        if (matriz[0][0]==v && matriz[0][1]==v && sombra[0][2]){ una.x = 0; una.y = 2; return una; }
        if (matriz[0][0]==v && matriz[1][0]==v && sombra[2][0]){ una.x = 2; una.y = 0; return una; }
        if (matriz[0][0]==v && matriz[1][1]==v && sombra[2][2]){ una.x = 2; una.y = 2; return una; }
        if (matriz[0][0]==v && matriz[0][2]==v && sombra[0][1]){ una.x = 0; una.y = 1; return una; }
        if (matriz[0][0]==v && matriz[2][2]==v && sombra[1][1]){ una.x = 1; una.y = 1; return una; }
        if (matriz[0][0]==v && matriz[2][0]==v && sombra[1][0]){ una.x = 1; una.y = 0; return una; }
        
        if (matriz[0][1]==v && matriz[0][2]==v && sombra[0][0]){ una.x = 0; una.y = 0; return una; }
        if (matriz[0][1]==v && matriz[1][1]==v && sombra[2][1]){ una.x = 2; una.y = 1; return una; }
        if (matriz[0][1]==v && matriz[2][1]==v && sombra[1][1]){ una.x = 1; una.y = 1; return una; }
        
        if (matriz[0][2]==v && matriz[1][1]==v && sombra[2][0]){ una.x = 2; una.y = 0; return una; }
        if (matriz[0][2]==v && matriz[1][2]==v && sombra[2][2]){ una.x = 2; una.y = 2; return una; }
        if (matriz[0][2]==v && matriz[2][0]==v && sombra[1][1]){ una.x = 1; una.y = 1; return una; }
        if (matriz[0][2]==v && matriz[2][2]==v && sombra[1][2]){ una.x = 1; una.y = 2; return una; }
        
        if (matriz[1][0]==v && matriz[1][1]==v && sombra[1][2]){ una.x = 1; una.y = 2; return una; }
        if (matriz[1][0]==v && matriz[2][0]==v && sombra[0][0]){ una.x = 0; una.y = 0; return una; }
        if (matriz[1][0]==v && matriz[1][2]==v && sombra[1][1]){ una.x = 1; una.y = 1; return una; }
        
        if (matriz[1][1]==v && matriz[1][2]==v && sombra[1][0]){ una.x = 1; una.y = 0; return una; }
        if (matriz[1][1]==v && matriz[2][2]==v && sombra[0][0]){ una.x = 0; una.y = 0; return una; }
        if (matriz[1][1]==v && matriz[2][1]==v && sombra[0][1]){ una.x = 0; una.y = 1; return una; }
        if (matriz[1][1]==v && matriz[2][0]==v && sombra[0][2]){ una.x = 0; una.y = 2; return una; }
        
        if (matriz[1][2]==v && matriz[2][2]==v && sombra[0][2]){ una.x = 0; una.y = 2; return una; }
        
        if (matriz[2][0]==v && matriz[2][1]==v && sombra[2][2]){ una.x = 2; una.y = 2; return una; }
        if (matriz[2][0]==v && matriz[2][2]==v && sombra[2][1]){ una.x = 2; una.y = 1; return una; }
        
        if (matriz[2][1]==v && matriz[2][2]==v && sombra[2][0]){ una.x = 2; una.y = 0; return una; }
        
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
        
        resetear();
        
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
        
        try {
            
            img = new File("src/img/lose.png");
            tex = TextureIO.newTexture(img, true);
            los = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/reset.png");
            tex = TextureIO.newTexture(img, true);
            res = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Triqui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/null.png");
            tex = TextureIO.newTexture(img, true);
            nul = tex.getTextureObject();
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
        
        if (lose){
            gl.glBindTexture(GL.GL_TEXTURE_2D, los);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-3.3f, -2.0f, 0.0f);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(-2.3f, -2.0f, 0.0f);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(-2.3f, -1.0f, 0.0f);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-3.3f, -1.0f, 0.0f); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
        }
        
        if (nulle){
            gl.glBindTexture(GL.GL_TEXTURE_2D, nul);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-3.3f, -2.0f, 0.0f);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(-2.3f, -2.0f, 0.0f);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(-2.3f, -1.0f, 0.0f);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-3.3f, -1.0f, 0.0f); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
        }
        
        if (reset){
            gl.glBindTexture(GL.GL_TEXTURE_2D, res);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(2.3f, -2.0f, 0.0f);  // Top Left
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(3.3f, -2.0f, 0.0f);   // Top Right
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(3.3f, -1.0f, 0.0f);  // Bottom Right
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(2.3f, -1.0f, 0.0f); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
        }
        
        gl.glDisable(GL.GL_TEXTURE_2D);
        
        if (dib_lin){
            gl.glLineWidth(15);
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glBegin(GL.GL_LINES);
            switch(linea){
                case 1:
                    gl.glVertex2d(-2.2,1.4);
                    gl.glVertex2d(2.2,1.4);
                    break;
                case 2:
                   gl.glVertex2d(-2.2,0.0);
                   gl.glVertex2d(2.2,0.0);
                   break;
                case 3:
                   gl.glVertex2d(-2.2,-1.4);
                   gl.glVertex2d(2.2,-1.4);
                   break;
                case 4:
                   gl.glVertex2d(-1.4,2.2);
                   gl.glVertex2d(-1.4,-2.2);
                   break;
                case 5:
                   gl.glVertex2d(0.0,2.2);
                   gl.glVertex2d(0.0,-2.2);
                   break;
                case 6:
                   gl.glVertex2d(1.4,2.2);
                   gl.glVertex2d(1.4,-2.2);
                   break;
                case 8:
                   gl.glVertex2d(-1.9,-2.0);
                   gl.glVertex2d(1.9,2.0);
                   break;
                case 7:
                   gl.glVertex2d(-1.9,2.0);
                   gl.glVertex2d(1.9,-2.0);
                     
            }
            gl.glEnd();
        }
        
        /*gl.beginRendering(gl.getWidth(), gl.getHeight()); 
        gl.setColor(1.0f, 0.0f, 0.0f, 0.8f); // Recuerda RGB son los tres primeros 
        gl.draw("MARCOS", 300, 300); // La cadena y la posicion 
        gl.endRendering(); */

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
    
    private static Coordenada posicion(int p_i){
        int x =  (int) (Math.random() * 9) + 1;
        System.out.println("entra aqui3 "+x+" "+p_i);
        if (p_i == 1){
            while(x % 2 == 0 || x == 5){
                x = (int) (Math.random() * 9) + 1;
            }
        }else{
            while( x % 2 != 0 ){
                x = (int) (Math.random() * 9) + 1;
            }
            System.out.println("entra aqui 2 "+x);
        }
        
        Coordenada cor = transformacion (x);
        if ( sombra[cor.x][cor.y]== false ){
           System.out.println("entra aqui 5 "+cor.x+" "+cor.y);
           if ( sombra[0][0] ){ cor.x=0; cor.y=0; }
           else if ( sombra[0][2] ){ cor.x=0; cor.y=2; }
           else if ( sombra[2][0] ){ cor.x=2; cor.y=0; }
           else if ( sombra[2][2] ){ cor.x=2; cor.y=2; }
           else if ( sombra[0][1] ){ cor.x=0; cor.y=1; }
           else if ( sombra[1][0] ){ cor.x=1; cor.y=0; }
           else if ( sombra[1][2] ){ cor.x=1; cor.y=2; }
           else if ( sombra[2][1] ){ cor.x=2; cor.y=1; }
        }
        
        return cor;
    }
    
    private static Coordenada transformacion (int n){
        Coordenada cor = new Coordenada();
        switch(n){
            case 1: cor.x = 0; cor.y = 0; break;
            case 2: cor.x = 0; cor.y = 1; break;
            case 3: cor.x = 0; cor.y = 2; break;
            case 4: cor.x = 1; cor.y = 0; break;
            case 5: cor.x = 1; cor.y = 1; break;
            case 6: cor.x = 1; cor.y = 2; break;
            case 7: cor.x = 2; cor.y = 0; break;
            case 8: cor.x = 2; cor.y = 1; break;
            case 9: cor.x = 2; cor.y = 2; break;
        }
        return cor;
    }
    
    private static void resetear (){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                matriz[i][j] = -1;
                sombra[i][j] = true;
            }
        }
        
        lose = false;
        reset = false;
        nulle = false;
        dib_lin = false;
    }
}

