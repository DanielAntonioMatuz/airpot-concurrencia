package sample.Model;

import java.util.ArrayList;

public class estadoPista {
    private static boolean estado = false;
    private static int id;
    private static long contador = 0;
    public ArrayList data;
    public ArrayList<cluster> clusterOcupados;
    public int avionesGenerados;
    public boolean accesoDespegue = true;
    private final int capacidad = 18;
    private int ocupado;



    public synchronized boolean Estado(int idData) {
        if (id == idData) {
            System.out.println(idPlane() + " | " + idData);
            this.estado = true;
        }
        return this.estado;
    }

    public static boolean getEstado() {
        return estado;
    }

    public synchronized void entra () throws InterruptedException {
        while (ocupado >= capacidad) wait();
        ocupado++;
    }

    public synchronized void sale () {
        ocupado--;
        notifyAll();
    }

    public synchronized int ocupadoConsult() {
        return ocupado;
    }

    public static synchronized int idPlane() {
        return id;
    }

    public static void setId(int id) {
        estadoPista.id = id;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public int getAvionesGenerados() {
        return avionesGenerados;
    }

    public void setAvionesGenerados(int avionesGenerados) {
        this.avionesGenerados = avionesGenerados;
    }

    public synchronized boolean isAccesoDespegue() {
        return accesoDespegue;
    }

    public synchronized void setAccesoDespegue(boolean accesoDespegue) {
        this.accesoDespegue = accesoDespegue;
    }

    public ArrayList<cluster> getClusterOcupados() {
        return clusterOcupados;
    }

    public synchronized void  setClusterOcupados(ArrayList<cluster> clusterOcupados) {
        this.clusterOcupados = clusterOcupados;
        /*for (int i = 0; i < clusterOcupados.size(); i ++) {
            System.out.println(clusterOcupados.get(i).isOcupado() + " ID: " + clusterOcupados.get(i).getIdCluster());
        }*/
    }

    public static long getContador(){
        return contador;
    }

}
