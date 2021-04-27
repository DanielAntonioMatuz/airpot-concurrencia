package sample.Model;

public class airport {

    public static int X = 0;
    public static int Y = 0;

    public static int pista1(int x, int y) {
        if (x >= 548 && x < 2310 && y >= 830 && y <= 845) {
            X =  x- 10 ;
        }
        return X;
    }

    public static int pista2(int x, int y) {
        if (x >= 2240 && y <= 284) {
            Y = y - 20 ;
        }
        return Y;
    }

    public static int pista3(int x, int y) {
        if (x >= 584 && x <= 2369 && y>= 245 && y <= 313) {
            X =  x - 30 ;
        }
        return X;
    }

    public static int pista4(int x, int y) {
        if (x >= 565 && x <= 640  && y >= 247 &&  y < 460) {
            Y = y + 30 ;
        }
        return Y;
    }

    public static int pista5(int x, int y) {
        if (x < 2080 &&  y >= 460 && y <= 480) {
            X =  x + 50 ;
        }
        return X;
    }

    public static int pista6(int x, int y) {
        if (x >= 2064 && y > 441) {
            Y = y + 50 ;
        }
        return Y;
    }

    public static int pista7(int x, int y) {
        if (x >= 0  && y >= 830 ) {
            X =  x- 100 ;
        }
        return X;
    }

    public static int pista1R(int x, int y) {
        if (x >= 0 && x <= 2240  && y >= 830 ) {
            X =  x + 100 ;
        }
        return X;
    }

    public static int puerta(int x, int y) {
        if (x >= 577 && x <= 2395 && y >= 140 && y <= 247) {
            Y = y + 20 ;
        }
        return Y;
    }


}
