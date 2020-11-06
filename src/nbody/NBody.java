package nbody;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

import nbody.util.*;

/**
 * Nbody
 *
 * @author Xiaocheng Zhang
 * @version 1.0
 *
 */
public class NBody extends JPanel implements ActionListener {
    private String name;
    private int length;
    private double mass;
    private double xVelo;
    private double yVelo;
    private double xForce;
    private double yForce;
    private int xCoord;
    private int yCoord;
    private Color color;

    private nbody.util.List<NBody> list;
    private double scale;

    /**
     * Constructor
     *
     */
    public NBody() {
        //Random for colors
        Random r = new Random();
        //Random color for each nBody
        color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    /**
     * Setter name
     */
    void setname(String name){
        this.name = name;
    }


    /**
     * Setter mass
     *
     * @param mass
     */
    void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * Getter mass
     *
     * @return mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * Setter xCoord
     *
     * @param xCoord
     */
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Getter xCoord
     *
     * @return xCoord
     */
    public int getXCoord() {
        return xCoord;
    }

    /**
     * Setter yCoord
     * @param yCoord
     */
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Getter yCoord
     *
     * @return yCoord
     */
    public int getYCoord() {
        return yCoord;
    }

    /**
     * Setter xVelo
     *
     * @param xVelo
     *
     */
    public void setXVelo(double xVelo) {
        this.xVelo = xVelo;
    }

    /**
     * Getter xVelo
     *
     * @return xVelo
     */
    public double getXVelo() {
        return xVelo;
    }

    /**
     * Setter yVelo
     * 
     */
    public void setYVelo(double yVelo){
        this.yVelo = yVelo;
    }

    /**
     * Getter yVelo
     *
     * @return yVelo
     *
     */
    public double getYVelo() {
        return yVelo;
    }

    /**
     * The length
     *
     * @return length
     */
    public int length() {
        return length;
    }

    public void setLength(int length) {
       this.length =  length;   
    }

    /**
     * Setter scale
     * 
     * @param scale
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Setter list
     *
     */
    public void setList(nbody.util.List<NBody> list){
        this.list = list;
    }

    /**
     * Getter Color
     * 
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /*
     * force calculate
     *
     */
    public void force(NBody a, double scale) {
        NBody b = this;
        double x = a.xCoord - b.xCoord;
        double y = a.yCoord - b.yCoord;
        double distance = Math.sqrt(x * x + y * y);
        double force = (6.673e-11 * b.mass * a.mass / ((distance * distance) / scale));
        b.xForce += force * x / distance;
        b.yForce += force * y / distance;
    }

    /**
     * Reset force
     */
    public void resetForce() {
        xForce = 0;
        yForce = 0;
    }

    /**
     * updatePosition
     */
    public void updatePosition() {
        xVelo += xForce/mass;
        yVelo += yForce/mass;
        xCoord += xVelo;
        yCoord += yVelo;
    }


    /**
     * Paint
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Timer time = new Timer(400, this);
        
	//loop
        for (int i = 0; i < list.size(); i++) {
            g.setColor(list.get(i).getColor());
            g.fillOval(list.get(i).getXCoord(), list.get(i).getYCoord(), list.get(i).length(), list.get(i).length());
        }

        time.start();
    }


    /**
     * Action Performed
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        updateShapes();
        repaint();
    }

    /**
     * Update Shapes
     */
    public void updateShapes() {
        int i;
        for (i = 0; i < list.size() - 1; i++) {
            list.get(i).force(list.get(i + 1), scale);
            list.get(i).updatePosition();
            list.get(i).resetForce();
        }
        
	if(list.size() > 1) {
            list.get(i).force(list.get(i-1), scale);
            list.get(i).updatePosition();
            list.get(i).resetForce();
        }
    }

    //Usage
    private static void usage() {
       System.out.println("Usage: java NBody nbody_input.txt");
    }



    /**
     * Main
     */
    public static void main(String[] args) {
	NBody nBody = new NBody();
        nbody.util.List<NBody> list = null;

	if(args.length != 1){
	    usage();
	    return;
	}

        // read file
        File input = new File(args[0]);
        try {
            Scanner sc = new Scanner(input);
            String typeOfList = sc.nextLine();

            if(typeOfList.equals("ArrayList")) {
                list = new ArrayList<>();
		nBody.setList(list);
            } else if (typeOfList.equals("LinkedList")) {
                list = new LinkedList<>();
		nBody.setList(list);
            } else {
                System.out.println("Invalid list type.");
            }

            nBody.setScale(Double.parseDouble(sc.nextLine()));
            sc.useDelimiter(",");

            while (sc.hasNext()) {
    		NBody nextBody = new NBody();
		nextBody.setname(sc.next());
    	    	nextBody.setMass(Double.parseDouble(sc.next()));
        	nextBody.setXCoord(Integer.parseInt(sc.next()));
        	nextBody.setYCoord(Integer.parseInt(sc.next()));
        	nextBody.setXVelo(Double.parseDouble(sc.next()));
        	nextBody.setYVelo(Double.parseDouble(sc.next()));
        	nextBody.setLength(Integer.parseInt(sc.nextLine().substring(1)));
		list.add(nextBody);
	    }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        
	JFrame jf = new JFrame();
        //Title
        jf.setTitle("N Body Problem!");
        // window size
        jf.setSize(768, 768);
        jf.add(nBody);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
