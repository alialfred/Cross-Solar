package com.crossover.techtrial.service;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * HourlyElectricityService interface for all services realted to
 * HourlyElectricity.
 *
 * @author Crossover
 *
 */
public interface HourlyElectricityService {

    HourlyElectricity save(HourlyElectricity hourlyElectricity, String serial);

    Page<HourlyElectricity> getAllHourlyElectricityByPanelId(Long panelId, Pageable pageable);
    
    List<DailyElectricity> getAllDailyElectricityFromYesterday(String serial);
}
