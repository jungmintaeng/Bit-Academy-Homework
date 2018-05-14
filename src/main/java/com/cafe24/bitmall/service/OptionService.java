package com.cafe24.bitmall.service;

import com.cafe24.bitmall.dto.OptionDto;
import com.cafe24.bitmall.repository.OptionRepository;
import com.cafe24.bitmall.repository.ProductOptionRepository;
import com.cafe24.bitmall.vo.OptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ProductOptionRepository productOptionRepository;

    public List<OptionDto> optionDtoList(Long no){
        return convertToDto(optionRepository.getList(no));
    }

    private List<OptionDto> convertToDto(List<OptionVo> optionVos){
        if(optionVos == null || optionVos.size() == 0)
            return null;
        List<OptionDto> optionDtos = new ArrayList<>();
        OptionDto dto;
        for(OptionVo vo : optionVos){
            dto = new OptionDto();
            dto.setNo(vo.getNo());
            dto.setName(vo.getName());
            dto.setParentNo(vo.getParentNo());
            dto.setOptionDtoList(convertToDto(productOptionRepository.getList(vo.getNo())));
            optionDtos.add(dto);
        }
        return optionDtos;
    }
}
