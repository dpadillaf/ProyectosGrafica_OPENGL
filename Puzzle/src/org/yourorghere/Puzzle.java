package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
 * Puzzle.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Puzzle implements GLEventListener {
    int full, winI;
    static int [] aux = new int [9];
    static int [] aRandm = new int [9];
    int n=9; 
    static int foco=4;
    static int [] aRand = new int[9];
    static int [] puz = new int[9];
    int [][] coord = new int[9][4];
    static boolean win = false;
    //List<Integer> a; //= new ArrayList<Integer>();

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        
        canvas.addGLEventListener(new Puzzle());
        
        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if ( ke.getKeyCode() == KeyEvent.VK_UP ){
                    movimiento( "arriba" );
                    System.err.println("arriba");
                }else if ( ke.getKeyCode() == KeyEvent.VK_DOWN ){
                    movimiento( "abajo" );
                    System.out.println("abajo");
                }else if ( ke.getKeyCode() == KeyEvent.VK_RIGHT ){
                    movimiento( "derecha" );
                    System.out.println("derecha");
                }else if ( ke.getKeyCode() == KeyEvent.VK_LEFT ){
                    movimiento( "izquierda" );
                    System.out.println("izquierda");
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        });
        
        frame.add(canvas);
        frame.setSize(1080, 720);
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
            
            img = new File("src/img/0.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[0] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/1.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[1] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/2.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[2] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/3.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[3] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/4.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[4] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/5.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[5] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/6.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[6] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/7.jpg");
            tex = TextureIO.newTexture(img, true);
            aRandm[7] = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/full.jpg");
            tex = TextureIO.newTexture(img, true);
            full = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            img = new File("src/img/win.png");
            tex = TextureIO.newTexture(img, true);
            winI = tex.getTextureObject();
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        aRandm[8] = -1;
          //numeros aleatorios
        int k=n;  //auxiliar;
        int[] numeros=new int[n];
        Random rnd=new Random();
        int res;
        
        
        
        for(int i=0;i<n;i++){
            numeros[i]=i;
            aux[i] = aRandm[i]; 
            System.out.print(aRandm[i]+" ");
        }
        
        for(int i=0;i<n;i++){
            res=rnd.nextInt(k);            
            aRand[i]=numeros[res];
            numeros[res]=numeros[k-1];
            k--;
            
        }
        
        for( int i=0; i<n; i++ ){
            aRandm[aRand[i]] = aux[i];
        }
        
        
        int x=50, y=50;
        for (int i=0; i<9; i++){
            coord[i][0] = x;
            coord[i][1] = x+200;
            coord[i][2] = y;
            coord[i][3] = y+200;
            x = x+200;
            if (x == 650){
                x = 50;
                y = y +200;
            }
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
        gl.glTranslatef( 0, 0, 0 );

        gl.glLineWidth(10);
	gl.glColor3f(1.0f, 1.0f, 1.0f);
	gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(49, 49);
            gl.glVertex2d(651, 49);
            gl.glVertex2d(49, 651);
            gl.glVertex2d(651, 651);
            gl.glVertex2d(49, 49);
            gl.glVertex2d(49,651);
            gl.glVertex2d(651, 49);
            gl.glVertex2d(651, 651);
	gl.glEnd();
        
        gl.glEnable(GL.GL_TEXTURE_2D);
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, full);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(700, 210);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(1000, 210);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(1000, 510);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(700, 510); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        if ( win ){
            gl.glBindTexture(GL.GL_TEXTURE_2D, winI);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(800, 310);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(900, 310);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(900, 410);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(800, 410); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
        }
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[0]!=-1)?aRandm[0]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[0][0], coord[0][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[0][1], coord[0][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[0][1], coord[0][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[0][0], coord[0][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[1]!=-1)?aRandm[1]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[1][0], coord[1][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[1][1], coord[1][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[1][1], coord[1][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[1][0], coord[1][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[2]!=-1)?aRandm[2]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[2][0], coord[2][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[2][1], coord[2][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[2][1], coord[2][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[2][0], coord[2][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[3]!=-1)?aRandm[3]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[3][0], coord[3][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[3][1], coord[3][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[3][1], coord[3][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[3][0], coord[3][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[4]!=-1)?aRandm[4]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[4][0], coord[4][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[4][1], coord[4][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[4][1], coord[4][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[4][0], coord[4][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[5]!=-1)?aRandm[5]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[5][0], coord[5][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[5][1], coord[5][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[5][1], coord[5][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[5][0], coord[5][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[6]!=-1)?aRandm[6]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[6][0], coord[6][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[6][1], coord[6][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[6][1], coord[6][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[6][0], coord[6][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[7]!=-1)?aRandm[7]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[7][0], coord[7][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[7][1], coord[7][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[7][1], coord[7][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[7][0], coord[7][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glBindTexture(GL.GL_TEXTURE_2D, (aRandm[8]!=-1)?aRandm[8]:0);
            gl.glBegin(GL.GL_QUADS);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex2d(coord[8][0], coord[8][2]);  // Top Left
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex2d(coord[8][1], coord[8][2]);   // Top Right
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex2d(coord[8][1], coord[8][3]);  // Bottom Right
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex2d(coord[8][0], coord[8][3]); // Bottom Left
            // Done Drawing The Quad
            gl.glEnd();
            
        gl.glDisable(GL.GL_TEXTURE_2D);
        
        gl.glLineWidth(5);
	gl.glColor3f(1.0f, 0.0f, 1.0f);
	gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(coord[foco][1]-125, coord[foco][3]-125);
            gl.glVertex2d(coord[foco][1]-75, coord[foco][3]-75);
            gl.glVertex2d(coord[foco][1]-125, coord[foco][3]-75);
            gl.glVertex2d(coord[foco][1]-75, coord[foco][3]-125);
	gl.glEnd();
        
        //gl.glTranslatef(coord[foco][1]-100, coord[foco][3]-100, 0);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public static void movimiento( String mov ){
        switch ( foco ){
            case 0:
                if ( "derecha".equals(mov) ){
                    if ( aRandm[1] == -1 ){
                        aRandm[1] = aRandm[0];
                        aRandm[0] = -1;
                        foco = 1;
                    }else{
                        foco = 1;
                    }
                }else if ( "abajo".equals(mov) ){
                    if ( aRandm[3] == -1 ){
                        aRandm[3] = aRandm[0];
                        aRandm[0] = -1;
                        foco = 3;
                    }else{
                        foco = 3;
                    }
                }
            break;
                
            case 1:
                if ( "derecha".equals(mov) ){
                    if ( aRandm[2] == -1 ){
                        aRandm[2] = aRandm[1];
                        aRandm[1] = -1;
                        foco = 2;
                    }else{
                        foco = 2;
                    }
                }else if ( "abajo".equals(mov) ){
                    if ( aRandm[4] == -1 ){
                        aRandm[4] = aRandm[1];
                        aRandm[1] = -1;
                        foco = 4;
                    }else{
                        foco = 4;
                    }
                }else if ( "izquierda".equals(mov) ){
                    if ( aRandm[0] == -1 ){
                        aRandm[0] = aRandm[1];
                        aRandm[1] = -1;
                        foco = 0;
                    }else{
                        foco = 0;
                    }
                }
            break;
            
            case 2:
                if ( "abajo".equals(mov) ){
                    if ( aRandm[5] == -1 ){
                        aRandm[5] = aRandm[2];
                        aRandm[2] = -1;
                        foco = 5;
                    }else{
                        foco = 5;
                    }
                }else if ( "izquierda".equals(mov) ){
                    if ( aRandm[1] == -1 ){
                        aRandm[1] = aRandm[2];
                        aRandm[2] = -1;
                        foco = 1;
                    }else{
                        foco = 1;
                    }
                }
            break;
            
            case 3:
                if ( "derecha".equals(mov) ){
                    if ( aRandm[4] == -1 ){
                        aRandm[4] = aRandm[3];
                        aRandm[3] = -1;
                        foco = 4;
                    }else{
                        foco = 4;
                    }
                }else if ( "abajo".equals(mov) ){
                    if ( aRandm[6] == -1 ){
                        aRandm[6] = aRandm[3];
                        aRandm[3] = -1;
                        foco = 6;
                    }else{
                        foco = 6;
                    }
                }else if ( "arriba".equals(mov) ){
                    if ( aRandm[0] == -1 ){
                        aRandm[0] = aRandm[3];
                        aRandm[3] = -1;
                        foco = 0;
                    }else{
                        foco = 0;
                    }
                }
            break;
            
            case 4:
                if ( "derecha".equals(mov) ){
                    if ( aRandm[5] == -1 ){
                        aRandm[5] = aRandm[4];
                        aRandm[4] = -1;
                        foco = 5;
                    }else{
                        foco = 5;
                    }
                }else if ( "abajo".equals(mov) ){
                    if ( aRandm[7] == -1 ){
                        aRandm[7] = aRandm[4];
                        aRandm[4] = -1;
                        foco = 7;
                    }else{
                        foco = 7;
                    }
                }else if ( "izquierda".equals(mov) ){
                    if ( aRandm[3] == -1 ){
                        aRandm[3] = aRandm[4];
                        aRandm[4] = -1;
                        foco = 3;
                    }else{
                        foco = 3;
                    }
                }else if ( "arriba".equals(mov) ){
                    if ( aRandm[1] == -1 ){
                        aRandm[1] = aRandm[4];
                        aRandm[4] = -1;
                        foco = 1;
                    }else{
                        foco = 1;
                    }
                }
            break;
            
            case 5:
                if ( "abajo".equals(mov) ){
                    if ( aRandm[8] == -1 ){
                        aRandm[8] = aRandm[5];
                        aRandm[5] = -1;
                        foco = 8;
                    }else{
                        foco = 8;
                    }
                }else if ( "izquierda".equals(mov) ){
                    if ( aRandm[4] == -1 ){
                        aRandm[4] = aRandm[5];
                        aRandm[5] = -1;
                        foco = 4;
                    }else{
                        foco = 4;
                    }
                }else if ( "arriba".equals(mov) ){
                    if ( aRandm[2] == -1 ){
                        aRandm[2] = aRandm[5];
                        aRandm[5] = -1;
                        foco = 2;
                    }else{
                        foco = 2;
                    }
                }
            break;
            
            case 6:
                if ( "derecha".equals(mov) ){
                    if ( aRandm[7] == -1 ){
                        aRandm[7] = aRandm[6];
                        aRandm[6] = -1;
                        foco = 7;
                    }else{
                        foco = 7;
                    }
                }else if ( "arriba".equals(mov) ){
                    if ( aRandm[3] == -1 ){
                        aRandm[3] = aRandm[6];
                        aRandm[6] = -1;
                        foco = 3;
                    }else{
                        foco = 3;
                    }
                }
            break;
            
            case 7:
                if ( "derecha".equals(mov) ){
                    if ( aRandm[8] == -1 ){
                        aRandm[8] = aRandm[7];
                        aRandm[7] = -1;
                        foco = 8;
                    }else{
                        foco = 8;
                    }
                }else if ( "arriba".equals(mov) ){
                    if ( aRandm[4] == -1 ){
                        aRandm[4] = aRandm[7];
                        aRandm[7] = -1;
                        foco = 4;
                    }else{
                        foco = 4;
                    }
                }else if ( "izquierda".equals(mov) ){
                    if ( aRandm[6] == -1 ){
                        aRandm[6] = aRandm[7];
                        aRandm[7] = -1;
                        foco = 6;
                    }else{
                        foco = 6;
                    }
                }
            break;
            
            case 8:
                if ( "izquierda".equals(mov) ){
                    if ( aRandm[7] == -1 ){
                        aRandm[7] = aRandm[8];
                        aRandm[8] = -1;
                        foco = 7;
                    }else{
                        foco = 7;
                    }
                }else if ( "arriba".equals(mov) ){
                    if ( aRandm[5] == -1 ){
                        aRandm[5] = aRandm[8];
                        aRandm[8] = -1;
                        foco = 5;
                    }else{
                        foco = 5;
                    }
                }
            break;
        }
        
        comprobar();
    }
    
    private static void comprobar (){
        for ( int i=0; i<9; i++ ){
            if ( aRandm[i] != aux[i] ){
                return;
            }
        }
        win = true;
    } 
}

