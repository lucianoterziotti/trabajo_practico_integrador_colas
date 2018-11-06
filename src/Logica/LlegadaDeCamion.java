package Logica;

import java.util.LinkedList;

public class LlegadaDeCamion extends Evento
{

    private double tiempoLlegada;

    public double getProxLlegadaCamion() {
        return proxLlegadaCamion;
    }

    public void setProxLlegadaCamion(double proxLlegadaCamion) {
        this.proxLlegadaCamion = proxLlegadaCamion;
    }

    private double proxLlegadaCamion;
    private Camion camion;
    private double randomLlegada;
    private Recepcion recepcion;
    private int contadorCamiones = 1;

    public double getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(double tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public double getRandomLlegada() {
        return randomLlegada;
    }

    public void setRandomLlegada(double randomLlegada) {
        this.randomLlegada = randomLlegada;
    }

    public void sumarContadorCamiones() {this.contadorCamiones++;}

    public LlegadaDeCamion( Recepcion recepcion)
    {
        this.camion=generarCamion();
        this.recepcion=recepcion;
        contadorCamiones++;
    }

    public String getNombre()
    {
        String nombre= "Lleg Camion";
        return nombre;
    }



    public Camion generarCamion()
    {
        Camion camion = new Camion(contadorCamiones,EstadoCamion.Nuevo);
        this.randomLlegada = Math.random();
        this.calcularTiempoLlegada();
        this.calcularProxLlegada();
        return camion;
    }

    public Camion generarCamionFueraHora(){
        Camion camion = new Camion(EstadoCamion.Nuevo);
        this.randomLlegada = Math.random();
        this.calcularTiempoLlegada();
        this.calcularProxLlegada();
        return camion;
    }

    public void calcularTiempoLlegada() {
        double demora = -((0.13333)*Math.log((1-randomLlegada))*3600);
        setTiempoLlegada(demora);
    }

    public void calcularProxLlegada()
    {
        setProxLlegadaCamion(this.getTiempoLlegada()+Reloj.getInstancia().getTiempoActual());
    }

    public void ejecutar()
    {
        if (recepcion.getEstado()==EstadoRecepcion.Libre)
        {
            recepcion.setEstado(EstadoRecepcion.Ocupado);
            this.getCamion().setEstado(EstadoCamion.En_Recepcion);
            recepcion.setCamion(this.getCamion());
            this.setCamion(null);
        }
        else
        {
            this.getCamion().setEstado(EstadoCamion.En_cola_Recepcion);
            recepcion.getCola().add(this.getCamion());
            this.setCamion(null);
        }
    }


    public String getTiempoLlegada1()
    {
        return Reloj.tiempoString(getTiempoLlegada());
    }

    public String getProxFinLlegada1()
    {
        return Reloj.tiempoString(getProxLlegadaCamion());
    }
}
