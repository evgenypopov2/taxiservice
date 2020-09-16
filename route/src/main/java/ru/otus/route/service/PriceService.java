package ru.otus.route.service;

import org.springframework.stereotype.Service;
import ru.otus.common.model.TaxiType;

@Service
public class PriceService {

    public static long STANDARD_TARIFF = 50;
    public static double COMFORT_COEFF = 1.5;
    public static double BUSINESS_COEFF = 2.5;
    public static double PREMIUM_COEFF = 4.0;

    public Long getPriceOf1kmByTaxiType(TaxiType type) {
        switch (type) {
            case STANDARD: return STANDARD_TARIFF;
            case COMFORT: return Math.round(STANDARD_TARIFF * COMFORT_COEFF);
            case BUSINESS: return Math.round(STANDARD_TARIFF * BUSINESS_COEFF);
            case PREMIUM: return Math.round(STANDARD_TARIFF * PREMIUM_COEFF);
        }
        return STANDARD_TARIFF;
    }
}
