package sample.Model;

public class cluster extends Thread {

   /* private estadoPista estado;
    private int id;
    private boolean estadoActive = false;

    public cluster(estadoPista estado, int id) {
        this.estado = estado;
        this.id = id;
    }

    public cluster(int id) {
        this.id = id;
    }

    public synchronized void estadoCluster() {
        estadoActive = true;
    }

    public boolean getEstadoActive(){ return estadoActive; }


*/

    private int idCluster;
    private boolean ocupado;

    public cluster(int id, boolean ocupado) {
        this.idCluster = id;
        this.ocupado = ocupado;
    }

    public int getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}