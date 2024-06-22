package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    @ParameterizedTest
    @MethodSource("provideTestData")

    void send(Map<String, String> input) {

        System.out.println(input.get("x-real-ip"));

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149")).
                thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp("172.0.32.11")).
                thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("")).
                thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
       Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl massage = new MessageSenderImpl(geoService, localizationService);

        String preferences = massage.send(input);
        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, preferences);    }

    private static Stream<Map<String, String>> provideTestData() {
        return Stream.of(
                Map.of(MessageSenderImpl.IP_ADDRESS_HEADER,"172.0.32.11"),
                Map.of(MessageSenderImpl.IP_ADDRESS_HEADER,"96.44.183.149"),
                Map.of(MessageSenderImpl.IP_ADDRESS_HEADER,"")
        );
    }
}