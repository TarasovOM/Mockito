package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    void locale(Country country) {
        boolean actualResult = new LocalizationServiceImpl().locale(country).contains("Welcome");
        Assertions.assertTrue(actualResult);
    }
}