package com.open.weather.api.openweatherapi.framework.adapter.out.persistence.repository;

import com.open.weather.api.openweatherapi.application.ports.out.persistence.WeatherRepositoryPort;
import com.open.weather.api.openweatherapi.domain.model.Weather;
import com.open.weather.api.openweatherapi.framework.adapter.out.persistence.entity.WeatherEntity;
import com.open.weather.api.openweatherapi.framework.adapter.out.persistence.mapper.WeatherDataAccessMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WeatherJpaRepositoryAdapter implements WeatherRepositoryPort {

    private final EntityManager entityManager;
    private final WeatherDataAccessMapper weatherDataAccessMapper;

    @Override
    public Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeatherEntity> cq = cb.createQuery(WeatherEntity.class);
        Root<WeatherEntity> root = cq.from(WeatherEntity.class);
        CriteriaQuery<WeatherEntity> criteria = cq.select(root);
        criteria.where(cb.equal(root.get("cityName"), city));
        TypedQuery<WeatherEntity> query = entityManager.createQuery(criteria);
        List<WeatherEntity> weathers = query.getResultList();
        if (weathers.isEmpty()) {
            return Optional.empty();
        }

        WeatherEntity findWeather = weathers.get(0);
        Weather weatherToWeatherEntity = weatherDataAccessMapper.createWeatherToWeatherEntity(findWeather);

        return Optional.of(weatherToWeatherEntity);
    }

    @Transactional
    @Override
    public Weather weatherSave(Weather weather) {
        WeatherEntity weatherEntity = weatherDataAccessMapper.createWeatherEntityToWeather(weather);
        entityManager.persist(weatherEntity);
        return weatherDataAccessMapper.createWeatherToWeatherEntity(weatherEntity);
    }
}
