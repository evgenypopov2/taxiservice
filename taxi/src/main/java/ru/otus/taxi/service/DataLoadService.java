package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.taxi.model.TaxiColor;
import ru.otus.taxi.model.TaxiModel;
import ru.otus.taxi.model.TaxiType;
import ru.otus.taxi.model.TaxiVendor;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class DataLoadService {

    private final TaxiModelService taxiModelService;
    private final TaxiVendorService taxiVendorService;
    private final TaxiCarService taxiCarService;

    public void loadData() {
        List<TaxiModel> taxiModels = taxiModelService.findAll();
        if (taxiModels.size() > 0) {
            return;
        }
        final List<TaxiType> taxiTypes = Arrays.asList(TaxiType.values());
        final List<TaxiColor> taxiColors = Arrays.asList(TaxiColor.values());

        TaxiVendor taxiVendor = createTaxiVendor("Hyundai");
        taxiModels.add(createTaxiModel(taxiVendor, "Solaris", TaxiType.STANDARD));
        taxiModels.add(createTaxiModel(taxiVendor, "Elantra", TaxiType.COMFORT));
        taxiModels.add(createTaxiModel(taxiVendor, "Sonata", TaxiType.BUSINESS));

        taxiVendor = createTaxiVendor("KIA");
        taxiModels.add(createTaxiModel(taxiVendor, "Rio", TaxiType.STANDARD));
        taxiModels.add(createTaxiModel(taxiVendor, "Cerato", TaxiType.COMFORT));
        taxiModels.add(createTaxiModel(taxiVendor, "Optima", TaxiType.BUSINESS));
        taxiModels.add(createTaxiModel(taxiVendor, "K900", TaxiType.PREMIUM));

        taxiVendor = createTaxiVendor("Skoda");
        taxiModels.add(createTaxiModel(taxiVendor, "Octavia", TaxiType.COMFORT));

        taxiVendor = createTaxiVendor("Volkswagen");
        taxiModels.add(createTaxiModel(taxiVendor, "Polo", TaxiType.STANDARD));
        taxiModels.add(createTaxiModel(taxiVendor, "Jetta", TaxiType.COMFORT));
        taxiModels.add(createTaxiModel(taxiVendor, "Passat", TaxiType.BUSINESS));

        taxiVendor = createTaxiVendor("Toyota");
        taxiModels.add(createTaxiModel(taxiVendor, "Corolla", TaxiType.COMFORT));
        taxiModels.add(createTaxiModel(taxiVendor, "Camry", TaxiType.BUSINESS));

        taxiVendor = createTaxiVendor("Ford");
        taxiModels.add(createTaxiModel(taxiVendor, "Focus", TaxiType.STANDARD));
        taxiModels.add(createTaxiModel(taxiVendor, "Mondeo", TaxiType.BUSINESS));

        taxiModels = taxiModelService.saveAll(taxiModels);
    }

    private TaxiVendor createTaxiVendor(String name) {
        TaxiVendor taxiVendor = new TaxiVendor();
        taxiVendor.setName(name);
        taxiVendor = taxiVendorService.save(taxiVendor);
        return taxiVendor;
    }

    private TaxiModel createTaxiModel(TaxiVendor taxiVendor, String name, TaxiType taxiType) {
        TaxiModel taxiModel = new TaxiModel();
        taxiModel.setVendor(taxiVendor);
        taxiModel.setName(name);
        taxiModel.setType(taxiType);
        return taxiModel;
    }
}
