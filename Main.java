import javax.print.DocFlavor;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    private static long multiplicar(String x, String y, int size, int base) {
        String Xi = "", Xd = "", Yi = "", Yd = "";

            if (size == 1){
                if (base == 16) {
                    BigInteger aux1 = new BigInteger(x,base);
                    BigInteger aux2 = new BigInteger(y,base);
                    return aux1.multiply(aux2).longValue();              // Caso base
                } else {
                    return Long.parseLong(x)*Long.parseLong(y);              // Caso base
                }
            } else{
                if(base == 16){
                    Xd = x.substring(size/2,size);
                    BigInteger Xderecha = new BigInteger(Xd, base);
                    Xi = x.substring(0,size/2);
                    BigInteger Xizquierda = new BigInteger(Xi, base);
                    Yi = y.substring(0,size/2);
                    BigInteger Yizquierda = new BigInteger(Yi, base);
                    Yd= y.substring(size/2,size);
                    BigInteger Yderecha = new BigInteger(Yd, base);

                    String suma1 = (Xizquierda.add(Xderecha).toString(base));
                    String suma2 = (Yizquierda.add(Yderecha).toString(base));
                    int sizeAux = Math.max(suma1.length(),suma2.length());

                    while (!isPow(sizeAux)){
                        sizeAux ++;
                    }

                    while (suma1.length()<sizeAux){
                        suma1 = '0' + suma1;
                    }

                    while (suma2.length()<sizeAux){
                        suma2 = '0' + suma2;
                    }

                    long p1 = multiplicar(Xi,Yi,size/2, base);
                    long p2 = multiplicar(suma1, suma2,sizeAux,base);
                    long p3 = multiplicar(Xd,Yd,size/2, base);

                    return (((long) Math.pow(base,size))*p1) + (((long) Math.pow(base, size/2)*(p2-p1-p3))) + p3;
                } else {
                    Xi = x.substring(0,size/2);
                    Xd = x.substring(size/2,size);
                    Yi = y.substring(0,size/2);
                    Yd= y.substring(size/2,size);
                    StringBuilder suma1 = new StringBuilder(String.valueOf(Long.parseLong(Xi) + Long.parseLong(Xd)));
                    StringBuilder suma2 = new StringBuilder(String.valueOf(Long.parseLong(Yi) + Long.parseLong(Yd)));

                    while (suma1.length()<size/2){
                        suma1.insert(0, "0");
                    }

                    while (suma2.length()<size/2){
                        suma2.insert(0, "0");
                    }

                    long p1 = multiplicar(Xi,Yi,size/2, base);
                    long p2 = multiplicar(suma1.toString(), suma2.toString(),size/2,base);
                    long p3 = multiplicar(Xd,Yd,size/2, base);

                    return (((long) Math.pow(base,size))*p1) + (((long) Math.pow(base, size/2)*(p2-p1-p3))) + p3;
                }
            }
    }

    private static String DecimalToBinary(long numero){
        StringBuilder binario = new StringBuilder();
        if (numero == 0){
            return "0";
        }
        while (numero > 0) {
            if (numero % 2 == 0) {//12 es multiplo de dos?...
                binario.insert(0, "0"); // si va cero
            } else {
                binario.insert(0, "1"); // si va uno
            }
            numero = numero / 2;//lo divido entre dos
        }
        return binario.toString();
    }

    private static boolean isPow(int x){
        return (x != 0) && ((x & (x-1)) == 0);
    }

    public static void main (String[] args)
    {
        int size = 0;
        String cadena1;
        String cadena2;
        int base;
        Scanner flujo = new Scanner(System.in);
        // Obtenemos el tamano de la cadena mas grande
        System.out.println("Ingresa el tamano maximo de uno de los numeros");
        size = Integer.parseInt(flujo.nextLine());
        System.out.println("\nIngresa la base de los números: 10.-Decimal 2.-Binario 16.-Hexadecimal");
        base = Integer.parseInt(flujo.nextLine());
        System.out.println("Ingresa el primer numero");
        cadena1 = flujo.nextLine();
        System.out.println("Ingresa el segundo numero");
        cadena2 = flujo.nextLine();


        while (!isPow(size)){
            size ++;
        }

        while (cadena1.length() < size){
            cadena1 = '0' + cadena1;
        }
        System.out.println("El primer valor es: " + cadena1);

        while (cadena2.length() < size){
            cadena2 = '0' + cadena2;
        }
        System.out.println("El segundo valor es: " + cadena2 + "\n");
        long c = multiplicar(cadena1, cadena2,size,base);
        String valorDivideVenceras = DecimalToBinary(c);
        String valorHexadecimal = Long.toHexString(c);
        System.out.println("El valor en base 10 es: " + c);
        System.out.println("El valor en base 2 es: "+ valorDivideVenceras);
        System.out.println("El valor en base 16 es: "+ valorHexadecimal);
    }
}
