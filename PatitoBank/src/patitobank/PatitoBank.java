package patitobank;
//importo todas, para no gastar lineas de más 
import java.util.*;

public class PatitoBank {

    //Creo una clase que tenga todos los datos necesarios de forma que pueda acceder a ellos
    static class Cuenta {

        //declaro todos los datos a los que accedere desde el switch
        private String nombre;
        private String rfc;
        private int nip;
        private double saldo;
        private long numeroCuenta;
        //array de objetos que almacenara las transacciones, monto, concepto, fecha, tipo
        private ArrayList<Transaccion> transacciones;

        //genero automaticamente el constructor de la clase inicializados los atributos
        public Cuenta(String nombre, String rfc, int nip, double saldo, long numeroCuenta) {
            this.nombre = nombre;
            this.rfc = rfc;
            this.nip = nip;
            this.saldo = saldo;
            this.numeroCuenta = numeroCuenta;
            this.transacciones = new ArrayList<Transaccion>();
        }

        public void depositar(double monto, String concepto, String fecha, String tipo) {
            saldo += monto;
            Transaccion transaccion = new Transaccion(monto, concepto, fecha, tipo);
            transacciones.add(transaccion);
        }

        public void retirar(double monto, String concepto, String fecha, String tipo) {
            saldo -= monto;
            Transaccion transaccion = new Transaccion(monto, concepto, fecha, tipo);
            transacciones.add(transaccion);
        }

        public ArrayList<Transaccion> getTransacciones() {
            return transacciones;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRfc() {
            return rfc;
        }

        public void setRfc(String rfc) {
            this.rfc = rfc;
        }

        public int getNip() {
            return nip;
        }

        public void setNip(int nip) {
            this.nip = nip;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }

        public long getNumeroCuenta() {
            return numeroCuenta;
        }

        public void setNumeroCuenta(long numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
        }

    }

    static class Transaccion {

        private double monto;
        private String concepto;
        private String fecha;
        private String tipo;

        public Transaccion(double monto, String concepto, String fecha, String tipo) {
            this.monto = monto;
            this.concepto = concepto;
            this.fecha = fecha;
            this.tipo = tipo;
        }

        public double getMonto() {
            return monto;
        }

        public void setMonto(double monto) {
            this.monto = monto;
        }

        public String getConcepto() {
            return concepto;
        }

        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }
    }

    public static void main(String[] args) {
        //este array cuentas almacenara las cuentas creadas
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int op = 0;

        //menu de movimientos que podrá realizar el usuario
        do {
            System.out.print("______________________________");
            System.out.print("\n  Bienvenido al PatitoBank");
            System.out.print("\n______________________________");
            System.out.println("\n1. Crear cuenta");
            System.out.println("2. Consulta saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Total de Ingresos y Egresos al banco");
            System.out.println("6. Historial de transacciones");
            System.out.println("7. Salir");
            System.out.print("Ingrese el movimiento a realizar: ");
            op = scanner.nextInt();
            System.out.print("______________________________\n");

            switch (op) {
                case 1:
                    //Aqui se realizara la creacion de la cuenta
                    System.out.print("Ingrese el nombre del titular de la cuenta: ");
                    String nombre = scanner.next();
                    //valida que no ingrese datos numericos
                    while (!nombre.matches("^[a-zA-ZáéíóúñÑ ]+$")) {
                        System.err.print("El nombre no puede contener numeros. Ingrese un nombre valido.");
                        nombre = scanner.next();
                    }

                    System.out.print("Ingrese el RFC del titular de la cuenta (16 Digitos): ");
                    String rfc = scanner.next();
                    //valida que el rfc sea de 16 digitos
                    while (rfc.length() != 16) {
                        System.out.println("RFC invalido. Ingrese el RFC del titular de la cuenta (16 Digitos): ");
                        rfc = scanner.next();
                    }

                    System.out.print("Ingrese el NIP de la cuenta: ");
                    int nip = scanner.nextInt();

                    double saldo = 0;
                    System.out.print("El saldo actual es: " + saldo);

                    //crea y asigna un numero de cuenta aleatorio
                    long numeroCuenta = (long) ((long) (Math.random() * 9e9) + 1e9);
                    System.out.print("\nEl numero de cuenta asignado es: " + numeroCuenta);

                    //crea instancia y  almacenan los parametros y luego lo agrega al array
                    Cuenta cuenta = new Cuenta(nombre, rfc, nip, saldo, numeroCuenta);
                    cuentas.add(cuenta);

                    System.out.println("");
                    System.out.print("\nCuenta creada con exito!");
                    System.out.print("\n______________________________\n");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("RFC: " + rfc);
                    System.out.println("Numero cuenta: " + numeroCuenta);
                    System.out.print("______________________________\n");
                    break;
                case 2:
                    //consulta de saldo
                    System.out.print("\nIngrese el numero de cuenta: ");
                    numeroCuenta = scanner.nextLong();
                    System.out.print("Ingrese el NIP: ");
                    nip = scanner.nextInt();

                    //esta variable es solo para indicar si fue encontrada
                    boolean cuentaEncontrada = false;
                    //recorre el array buscando el numero de cuenta y nip
                    //si fueran encontrados muestra el saldo de esa cuenta
                    for (Cuenta cuent : cuentas) {
                        if (cuent != null && cuent.getNumeroCuenta() == numeroCuenta && cuent.getNip() == nip) {
                            System.out.println("Saldo de la cuenta: " + cuent.getSaldo());
                            cuentaEncontrada = true;//si es encontrada cambia el valor a true
                            break;
                        }
                    }
                    //si no se encuentra muestra mensaje de error
                    if (!cuentaEncontrada) {
                        System.err.println("Cuenta no encontrada o NIP incorecto.");
                    }
                    break;
                case 3:
                    //Deposito de dinero en cuenta
                    System.out.print("______________________________\n");
                    System.out.print("Ingrese el numero de cuenta: ");
                    //creo una variable de tipo long para almacenar el deposito
                    long numeroCuentaDeposito = scanner.nextLong();

                    System.out.print("Ingrese el NIP: ");
                    int nipDeposito = scanner.nextInt();//almacena el nip
                    System.out.print("______________________________\n");
                    //esta variable es solo para indicar si fue encontrada
                    boolean cuentaEncontradaDeposito = false;

                    //recorre el array buscando el numero de cuenta y nip
                    //si fueran encontrados solicita el monto a depositar, concepto, fecha
                    for (Cuenta cuent : cuentas) {
                        if (cuent != null && cuent.getNumeroCuenta() == numeroCuentaDeposito && cuent.getNip() == nipDeposito) {
                            System.out.print("Ingrese el monto a depositar: $");
                            double montoDeposito = scanner.nextDouble();
                            System.out.print("Ingrese el concepto: ");
                            String concepto = scanner.next();
                            System.out.print("Ingrese la fecha: ");
                            String fecha = scanner.next();

                            //esta variable es solo para uso en las transacciones, ya que servira en el caso 5
                            String tipo = "INGRESO";

                            //crea instancia y  almacenan los parametros y luego lo agrega al array
                            cuent.depositar(montoDeposito, concepto, fecha, tipo);
                            System.out.println("Deposito realizado, Nuevo saldo: $" + cuent.getSaldo());
                            cuentaEncontradaDeposito = true;
                            break;
                        }
                    }
                    //si no se encuentra muestra mensaje de error
                    if (!cuentaEncontradaDeposito) {
                        System.err.println("Cuenta no encontrada o NIP incorecto.");
                    }
                    break;
                case 4:
                    //Retiro de dinero a cuenta
                    System.out.print("_________________________________\n");
                    System.out.print("Ingrese el numero de cuenta: ");
                    long numeroCuentaRetiro = scanner.nextLong();
                    System.out.print("Ingrese el NIP de la cuenta: ");
                    int nipRetiro = scanner.nextInt();
                    //creo una variable de tipo long para almacenar el deposito
                    boolean cuentaEncontradaRetiro = false;
                    //recorre el array buscando el numero de cuenta y nip
                    //si fueran encontrados solicita el monto a depositar, concepto, fecha
                    for (Cuenta cuent : cuentas) {
                        if (cuent != null && cuent.getNumeroCuenta() == numeroCuentaRetiro && cuent.getNip() == nipRetiro) {
                            System.out.print("Ingrese el monto a retirar: $");
                            double montoRetiro = scanner.nextDouble();
                            //compara el monto a retirar con el saldo, si es menor sigue con la peticion de concepto, fecha
                            //sino muestra mensaje de fondos insuficientes
                            if (montoRetiro <= cuent.getSaldo()) {
                                System.out.print("Ingrese el concepto: ");
                                String concepto = scanner.next();
                                System.out.print("Ingrese la fecha: ");
                                String fecha = scanner.next();
                                String tipo = "EGRESO";//esta variable es solo para uso en las transacciones, ya que servira en el caso 5
                                //crea instancia y  almacenan los parametros y luego lo agrega al array
                                cuent.retirar(montoRetiro, concepto, fecha, tipo);
                                System.out.println("Retiro realizado.");
                                System.out.println("Nuevo saldo: $" + cuent.getSaldo());

                            } else {
                                System.out.println("Fondos insuficientes para realizar el retiro");
                            }
                            cuentaEncontradaRetiro = true;
                            break;
                        }
                    }
                    if (!cuentaEncontradaRetiro) {
                        System.out.println("Cuenta no encontrada o NIP incorrecto");
                    }
                    break;
                case 5:
                    //Ingreso y Egresos
                    //creo variables para almacenar totales
                    double totalIngresos = 0;
                    double totalEgresos = 0;
                    String tipo;

                    //recorre el array buscando el numero de cuenta y nip
                    for (Cuenta cuent : cuentas) {
                        //recorre el array buscando si los parametros corresponden
                        for (Transaccion transaccion : cuent.getTransacciones()) {
                            tipo = transaccion.getTipo(); //obtiene el tipo de trancaccion
                            //compara si el tipo es igual a ingreso
                            if (tipo.equals("INGRESO")) {
                                //actualiza el totalingreso con la suma del monto
                                totalIngresos += transaccion.getMonto();
                                //compara si el tipo es igual a egreso
                            } else if (tipo.equals("EGRESO")) {
                                totalEgresos += transaccion.getMonto();
                            }
                        }
                    }
                    System.out.println("Total de ingresos: $" + totalIngresos);
                    System.out.println("Total de egresos: $" + totalEgresos);
                    System.out.println("Saldo total: $" + (totalIngresos - totalEgresos));
                    
                    System.out.print("\nCuenta creadas!");
                    for (Cuenta cuent : cuentas) {
                        System.out.print("\n______________________________\n");
                        System.out.println("Nombre: " + cuent.getNombre());
                        System.out.println("RFC: " + cuent.getRfc());
                        System.out.println("Numero cuenta: " + cuent.getNumeroCuenta());
                        System.out.print("______________________________\n");
                    }
                    break;
                case 6:
                    System.out.print("_________________________________\n");
                    System.out.print("Ingrese el numero de cuenta: ");
                    numeroCuenta = scanner.nextLong();
                    System.out.print("Ingrese el NIP: ");
                    nip = scanner.nextInt();

                    //esta variable es solo para indicar si fue encontrada
                    boolean cuentaT = false;
                    //recorre el array buscando el numero de cuenta y nip
                    for (Cuenta cuent : cuentas) {
                        if (cuent != null && cuent.getNumeroCuenta() == numeroCuenta && cuent.getNip() == nip) {
                            System.out.println("Historial de transacciones de la cuenta: " + cuent.getNumeroCuenta());
                            System.out.print("_________________________________\n");
                            //recorre el array buscando si los parametros corresponden
                            for (Transaccion transaccion : cuent.getTransacciones()) {
                                System.out.println("Fecha: " + transaccion.getFecha());
                                System.out.println("Concepto: " + transaccion.getConcepto());
                                System.out.println("Monto: $" + transaccion.getMonto());
                                System.out.print("_________________________________\n");
                            }
                            cuentaT = true;
                            break;
                        }
                    }
                    if (!cuentaT) {
                        System.out.println("Cuenta no encontrada o NIP incorrecto.");
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opcion no valida, Intentelo de nuevo");
                    break;
            }
        } while (op != 7);
    }
}