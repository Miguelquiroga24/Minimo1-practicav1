package edu.upc.dsa;
import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Test de JUnit para el componente ManagerImpl.
 *
 * Este test implementa cuatro operaciones:
 * 1. Añadir un dron.
 * 2. Añadir un piloto.
 * 3. Guardar un dron en el almacén y repararlo.
 * 4. Añadir una reserva para un plan de vuelo.
 */
public class ManagerImplTest {

    private Manager manager;

    /**
     * Método que se ejecuta antes de cada test.
     * Obtiene la instancia única de ManagerImpl (Singleton).
     */
    @Before
    public void setUp() {
        manager = ManagerImpl.getInstance();
    }

    /**
     * Test 1: Añadir un dron.
     * Verifica que, al añadir un dron, este aparece en el listado de drones ordenados.
     */
    @Test
    public void testAnadirDron() {
        Dron dron = manager.añadirDron("D1", "Alpha", "Fab1", "Mod1");
        List<Dron> drones = manager.listarDronesPorHorasDeVuelo();
        assertTrue("El dron debería estar en la lista", drones.contains(dron));
    }

    /**
     * Test 2: Añadir un piloto.
     * Verifica que, al añadir un piloto, este aparece en el listado de pilotos ordenados.
     */
    @Test
    public void testAnadirPiloto() {
        Piloto piloto = manager.añadirPiloto("P1", "Juan", "Perez");
        List<Piloto> pilotos = manager.listarPilotosPorHorasDeVuelo();
        assertTrue("El piloto debería estar en la lista", pilotos.contains(piloto));
    }

    /**
     * Test 3: Guardar un dron en el almacén y repararlo.
     * Se añade un dron, se guarda en mantenimiento y luego se repara.
     * Se comprueba que el estado de mantenimiento cambia correctamente.
     */
    @Test
    public void testGuardarYRepararDron() {
        Dron dron = manager.añadirDron("D2", "Beta", "Fab2", "Mod2");
        manager.guardarDronEnAlmacen("D2");
        assertTrue("El dron debe estar en mantenimiento", dron.isMantenimiento());
        Dron reparado = manager.repararDronEnAlmacen();
        assertNotNull("Se debe haber reparado un dron", reparado);
        assertFalse("El dron no debe estar en mantenimiento", reparado.isMantenimiento());
    }

    /**
     * Test 4: Añadir una reserva para un plan de vuelo.
     * Se añade un dron y un piloto, se crea una reserva y se verifica que
     * el piloto tiene al menos una reserva asignada.
     */
    @Test
    public void testAñadirReservaPlanVuelo() {
        // Añadimos un dron y un piloto para la reserva
        Dron dron = manager.añadirDron("D3", "Gamma", "Fab3", "Mod3");
        Piloto piloto = manager.añadirPiloto("P2", "Ana", "Lopez");
        Date fecha = new Date(); // Fecha actual
        // Añadimos la reserva: duración de 2 horas, posiciones de ejemplo
        manager.añadirReservaPlanVuelo("D3", fecha, 2, "40.0,-3.0", "41.0,-2.0", "P2");
        // Obtenemos las reservas asignadas al piloto "P2"
        List<Reserva> reservas = manager.listarReservasPorPiloto("P2");
        assertFalse("Debe haber al menos una reserva para el piloto", reservas.isEmpty());
    }
}
