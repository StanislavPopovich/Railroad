package com.railroad.service.impl;

import com.railroad.dao.impl.WayGenericDaoImpl;
import com.railroad.dto.WayDto;
import com.railroad.mapper.WayDtoMapper;
import com.railroad.model.Way;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WayServiceImpl implements WayService {

    @Autowired
    private WayGenericDaoImpl wayGenericDao;

    @Autowired
    private WayDtoMapper wayDtoMapper;

    @Override
    public void save(WayDto wayDto) {
        Way way = wayDtoMapper.WayDtoToWay(wayDto);
        wayGenericDao.save(way);

    }

    @Override
    public List<Way> getAll() {
        return wayGenericDao.getAll();
    }
}
