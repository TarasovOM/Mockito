package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1", "2", "3"
    })
    void send(int value) {

        Map<String, String> headers = new HashMap<>();
        //headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        // headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).
                thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");

        MessageSenderImpl massage = new MessageSenderImpl(geoService, localizationService);

        String preferences;
        preferences = massage.send(headers);
        String expected = "Welcome";

        Assertions.assertEquals(expected, preferences);
    }
}