package sample.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Semaphore;

import sample.Model.airport;

public class plane extends Observable implements Runnable {
    private Semaphore access;
    private Semaphore lleno;
    private String id;
    private boolean estadoMovimiento;
    private boolean estadoCarril;
    private int ejeX;
    private int ejeY;
    private estadoPista estado;
    boolean accessConfig = false;
    boolean estadoVolando;
    boolean accesoAterrizaje = false;
    boolean acoplar = false;
    public int cluster;


    public plane(Semaphore access_write, Semaphore lleno, String id) {
        this.access = access_write;
        this.lleno = lleno;
        this.id = id;
    }

    public plane(String id, int x, int y, estadoPista estado, Semaphore mutex) {
        this.id = id;
        this.ejeX = x;
        this.ejeY = y;
        this.estadoMovimiento = false;
        this.estado = estado;
        this.access = mutex;
    }

    public plane(String id, int x, int y, estadoPista estado, Semaphore mutex, boolean volando) {
        this.id = id;
        this.ejeX = x;
        this.ejeY = y;
        this.estadoMovimiento = false;
        this.estado = estado;
        this.access = mutex;
        this.estadoVolando = volando;
    }

    public plane(String id, int x, int y, estadoPista estado, Semaphore mutex, boolean volando, int cluster) {
        this.id = id;
        this.ejeX = x;
        this.ejeY = y;
        this.estadoMovimiento = false;
        this.estado = estado;
        this.access = mutex;
        this.estadoVolando = volando;
        this.cluster = cluster;
    }

    public plane(String id, int x, int y, estadoPista estado, Semaphore mutex, int cluster) {
        this.id = id;
        this.ejeX = x;
        this.ejeY = y;
        this.estadoMovimiento = false;
        this.estado = estado;
        this.access = mutex;
        this.cluster = cluster;
    }

   /* @Override
    public void run() {
        try {
            //access.acquire();


            //db.insert("dato");
            //lleno.release();
            //System.out.println(db.toString());
            curso();
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
       // access.release();
    }
*/

    @Override
    public void run() {

        while (true) {
            try {
                curso();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void curso() throws InterruptedException {
        ejeX = getEjeX();
        ejeY = getEjeY();

        if (estado.idPlane() == Integer.parseInt(this.id) && estado.isAccesoDespegue()) {
            if (ejeX >= 548 && ejeX < 2310 && ejeY >= 830 && ejeY <= 845) {
                setEjeX(airport.pista1(ejeX,ejeY));
            }

            if (ejeX >= 2309 && ejeY < 285) {
                setEjeY(airport.pista2(ejeX,ejeY));
            }

            if (ejeX >= 584 && ejeX <= 2400 && ejeY>= 245 && ejeY <= 313) {
                    setEjeX(airport.pista3(ejeX, ejeY));
            }


            if (ejeX >= 565 && ejeX <= 640  && ejeY >= 247 &&  ejeY < 460) {
                setEjeY(airport.pista4(ejeX,ejeY));
                if (!accesoAterrizaje) {
                    estado.getClusterOcupados().get(Integer.parseInt(id)).setOcupado(false);
                }
            }

            if (ejeX < 2080 &&  ejeY >= 460 && ejeY <= 480) {
                setEjeX(airport.pista5(ejeX,ejeY));
            }

            if (ejeX >= 2060 && ejeY > 441 && ejeY < 840) {
                setEjeY(airport.pista6(ejeX,ejeY));
            }

            if (ejeX >= 0 && ejeY >= 830 ) {
                setEjeX(airport.pista7(ejeX,ejeY));
                if (ejeX <= 40) {
                    setEstadoMovimiento(false);
                    estado.sale();
                    ArrayList dataAux;
                    Random random = new Random(System.currentTimeMillis());
                    int data = random.nextInt(estado.ocupadoConsult());
                    dataAux = estado.getData();
                    if (data == 1) {
                        data += 1;
                    } else if (data == 0) {
                        estado.setAccesoDespegue(false);
                    }
                    if (!dataAux.contains(data)) {
                        estado.setId(data);
                        dataAux.add(data);
                        estado.setData(dataAux);
                        accessConfig = true;
                        accesoAterrizaje = false;
                    }  else {
                        accessConfig = false;
                    }
                    System.out.println("CORE: " + data);

                    if (!accessConfig) {
                        estado.setAccesoDespegue(false);
                    }


                }

            }

            if (ejeX >= 577 && ejeX <= 2395 && ejeY >= 140 && ejeY <= 247 && estado.isAccesoDespegue()) {
                setEjeY(airport.puerta(ejeX,ejeY));
                setEstadoMovimiento(true);
            }


        }

        if (!estadoVolando) {
            Thread.sleep(40);
            setChanged();
            notifyObservers(Thread.currentThread().getName());
        }

        if (estadoVolando) {
            if (ejeX >= 0 && ejeX <= 2240 && ejeY >= 830 && !accesoAterrizaje) {
                setEjeX(airport.pista1R(ejeX, ejeY));

            }

            if (ejeX >= 2240 && ejeY >= 284 ) {
                setEjeY(ejeY-20);
                if (ejeY < 284) {
                    accesoAterrizaje = true;
                }
            }
            Thread.sleep(115);
            setChanged();
            notifyObservers(Thread.currentThread().getName());

        }



    }

    public String getId() {
        return id;
    }

    public boolean isAccesoAterrizaje() {
        return accesoAterrizaje;
    }

    public void setAccesoAterrizaje(boolean accesoAterrizaje) {
        this.accesoAterrizaje = accesoAterrizaje;
    }

    public boolean isAcoplar() {
        return acoplar;
    }

    public void setAcoplar(boolean acoplar) {
        this.acoplar = acoplar;
    }

    public boolean isEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(boolean estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public boolean isEstadoCarril() {
        return estadoCarril;
    }

    public void setEstadoCarril(boolean estadoCarril) {
        this.estadoCarril = estadoCarril;
    }

    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getEjeY() {
        return ejeY;
    }

    public boolean isEstadoVolando() {
        return estadoVolando;
    }

    public void setEstadoVolando(boolean estadoVolando) {
        this.estadoVolando = estadoVolando;
    }

    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
}
