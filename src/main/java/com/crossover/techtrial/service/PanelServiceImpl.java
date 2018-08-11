package com.crossover.techtrial.service;

import com.crossover.techtrial.dto.PanelDto;
import com.crossover.techtrial.exceptions.ApiException;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.PanelRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * PanelServiceImpl for panel related handling.
 *
 * @author Crossover
 *
 */
@Service
public class PanelServiceImpl implements PanelService {

    @Autowired
    PanelRepository panelRepository;

    /* (non-Javadoc)
     * @see com.crossover.techtrial.service.PanelService#register(com.crossover.techtrial.model.Panel)
     */
    @Override
    public void register(PanelDto panel) {
        String message = panel.validate();
        if(!StringUtils.isEmpty(message)){
            throw new ApiException(message, HttpStatus.BAD_REQUEST);
        }
        panelRepository.save(panel.getEntity());
    }

    @Override
    public PanelDto findBySerial(String serial) {
        Panel panel = panelRepository.findBySerial(serial);
        return Objects.isNull(panel) ? null : new PanelDto(panel);
    }

    @Override
    public boolean isPanelExists(Panel panel) {
        if(Objects.isNull(panel) || Objects.isNull(panel.getId())){
            return false;
        }
        return panelRepository.existsById(panel.getId());
    }
    
    
}
