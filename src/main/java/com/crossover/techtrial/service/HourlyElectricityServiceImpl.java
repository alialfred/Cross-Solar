package com.crossover.techtrial.service;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.dto.PanelDto;
import com.crossover.techtrial.exceptions.ApiException;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.repository.HourlyElectricityRepository;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * HourlyElectricityServiceImpl will handle electricity generated by a Panel.
 *
 * @author Crossover
 *
 */
@Service
public class HourlyElectricityServiceImpl implements HourlyElectricityService {

    @Autowired
    HourlyElectricityRepository hourlyElectricityRepository;
    @Autowired
    PanelService panelService;
    @Autowired
    EntityManager entityManager;

    @Override
    public HourlyElectricity save(HourlyElectricity hourlyElectricity, String serial) {
        PanelDto panelDto = panelService.findBySerial(serial);
        if (Objects.isNull(panelDto)) {
            throw new ApiException("Requested panel is not registerd.", HttpStatus.BAD_REQUEST);
        }
        hourlyElectricity.setPanel(panelDto.getEntity());
        return hourlyElectricityRepository.save(hourlyElectricity);
    }

    @Override
    public Page<HourlyElectricity> getAllHourlyElectricityByPanelId(Long panelId, Pageable pageable) {
        return hourlyElectricityRepository.findAllByPanelIdOrderByReadingAtDesc(panelId, pageable);
    }

    @Override
    public List<DailyElectricity> getAllDailyElectricityFromYesterday(String serial) {
        Query query = entityManager.createNativeQuery(readQuery("report_queries/DailyElectricityFromYesterday.sql"));
        query.setParameter("serial", serial);
        List<Object[]> result = query.getResultList();
        List<DailyElectricity> list = result.stream().map(this::toDailyElectricity).collect(Collectors.toList());
        return list;
    }

    private DailyElectricity toDailyElectricity(Object[] data) {
        DailyElectricity de = new DailyElectricity();
        de.setDate(((java.sql.Date) data[0]).toLocalDate());
        de.setSum(((BigDecimal) data[1]).doubleValue());
        de.setAverage(((BigDecimal) data[2]).doubleValue());
        de.setMin(((BigInteger) data[3]).doubleValue());
        de.setMax(((BigInteger) data[4]).doubleValue());
        return de;
    }

    /**
     *
     * @param reportName
     * @return report query @see report
     */
    protected String readQuery(String reportName) {
        byte[] b;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(reportName)) {
            b = new byte[is.available()];
            is.read(b);
        } catch (Exception exc) {
            throw new ApiException(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, exc);
        }
        return new String(b);
    }

}
