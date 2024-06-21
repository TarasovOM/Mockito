package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {
    Location[] locations = {
            new Location(null, null, null, 0),
            new Location("Moscow", Country.RUSSIA, "Lenina", 15),
            new Location("New York", Country.USA, " 10th Avenue", 32),
            new Location("Moscow", Country.RUSSIA, null, 0),
            new Location("New York", Country.USA, null, 0)
    };

    @ParameterizedTest
    @CsvSource({
            "127.0.0.1, 0",
            "172.0.32.11, 1",
            "96.44.183.149, 2",
            "172.0.32.55, 3",
            "96.11.147.44, 4"})
    void byIp(String ip, int exepted) {
        Assertions.assertEquals(locations[exepted], new GeoServiceImpl().byIp(ip));
    }

    @org.junit.jupiter.api.Test
    void byCoordinates() {
// Два варианта
        GeoServiceImpl service = new GeoServiceImpl();
       /* RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.byCoordinates(0,0);
        },"Not implemented");
        Assertions.assertEquals("Not implemented", thrown.getMessage());*/

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.byCoordinates(0, 0);
        });
    }
}
