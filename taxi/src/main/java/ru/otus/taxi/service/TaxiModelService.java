package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.taxi.model.TaxiModel;
import ru.otus.taxi.repository.TaxiModelRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaxiModelService {

    private final TaxiModelRepository taxiModelRepository;

    public List<TaxiModel> findAll() {
        return taxiModelRepository.findAll();
    }

    public Optional<TaxiModel> findByName(String model) {
        return taxiModelRepository.findByName(model);
    }

    public TaxiModel save(TaxiModel taxiModel) {
        return taxiModelRepository.save(taxiModel);
    }

    public List<TaxiModel> saveAll(List<TaxiModel> taxiModels) {
        return taxiModelRepository.saveAll(taxiModels);
    }
}
