package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.domain.dto.SkillDTO;
import com.javaweb.buildingproject.domain.entity.SkillEntity;
import com.javaweb.buildingproject.exception.custom.ExistException;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.mapper.SkillMapper;
import com.javaweb.buildingproject.repository.SkillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {
    private SkillRepository skillRepository;
    private SkillMapper skillMapper;

    public SkillService(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    public PaginationDTO fetchAllSkills(Pageable pageable) {
        Page<SkillEntity> page = skillRepository.findAll(pageable);
        List<SkillDTO> skillDTOList = page.getContent().stream().map(e->skillMapper.toDTO(e)).toList();
        PaginationDTO paginationDTO = new PaginationDTO();
        PaginationDTO.Meta meta = new PaginationDTO.Meta();
        meta.setPageNumber(pageable.getPageNumber()+1);
        meta.setPageSize(pageable.getPageSize());

        meta.setTotal(page.getTotalElements());
        meta.setPages(page.getTotalPages());
        paginationDTO.setMeta(meta);
        paginationDTO.setResult(skillDTOList);
        return paginationDTO;
    }

    public SkillDTO fetchByName(String name){
        SkillEntity skillEntity = skillRepository.findByName(name)
                .orElseThrow(()->new NotFoundException("skill not found with name: "+name));
        return skillMapper.toDTO(skillEntity);
    }

    public SkillDTO createSkill(SkillDTO skillDTO) {
        if(skillRepository.existsByName(skillDTO.getName())){
            throw new ExistException("skill already exists");
        }
        SkillEntity skillEntity = skillRepository.save(skillMapper.toEntity(skillDTO));
        return skillMapper.toDTO(skillEntity);
    }

    public SkillDTO updateSkill(Long id,SkillDTO skillDTO) {
        SkillEntity skillEntity = skillRepository.findById(id).orElseThrow(()->new NotFoundException("skill not found with id: "+id));
        skillEntity.setName(skillDTO.getName());
        skillRepository.save(skillEntity);
        return skillMapper.toDTO(skillEntity);
    }

    public void deleteSkill(Long id) {
        SkillEntity skillEntity = skillRepository.findById(id).orElseThrow(()->new NotFoundException("skill not found with id: "+id));
        skillRepository.delete(skillEntity);
    }
}
