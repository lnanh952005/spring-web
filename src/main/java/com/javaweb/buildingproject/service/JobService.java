package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.JobDTO;
import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.domain.dto.SkillDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.domain.entity.JobEntity;
import com.javaweb.buildingproject.domain.entity.SkillEntity;
import com.javaweb.buildingproject.exception.custom.ExistException;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.mapper.JobMapper;
import com.javaweb.buildingproject.mapper.SkillMapper;
import com.javaweb.buildingproject.repository.CompanyRepository;
import com.javaweb.buildingproject.repository.JobRepository;
import com.javaweb.buildingproject.repository.SkillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private JobRepository jobRepository;
    private JobMapper jobMapper;
    private CompanyRepository companyRepository;
    private SkillMapper skillMapper;
    private SkillRepository skillRepository;
    public JobService(JobRepository jobRepository, JobMapper jobMapper, CompanyRepository companyRepository
            , SkillMapper skillMapper, SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.companyRepository = companyRepository;
        this.skillMapper = skillMapper;
        this.skillRepository = skillRepository;
    }

    public PaginationDTO fetchAllJobs(Pageable pageable) {
        Page<JobEntity> page = jobRepository.findAll(pageable);
        PaginationDTO paginationDTO = new PaginationDTO();
        PaginationDTO.Meta meta = new PaginationDTO.Meta();
        List<JobDTO> jobDTOList = page.getContent().stream().map(e->jobMapper.toDTO(e)).toList();
        meta.setPageNumber(pageable.getPageNumber());
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());
        paginationDTO.setMeta(meta);
        paginationDTO.setResult(jobDTOList);
        return paginationDTO;
    }

    public JobDTO fetchById(Long id){
        return jobRepository.findById(id).map(e->jobMapper.toDTO(e))
                .orElseThrow(()-> new NotFoundException("không tìm thấy job với id: "+id));
    }

    public JobDTO fetchByName(String jobName){
        JobEntity jobEntity = jobRepository.findByName(jobName)
                .orElseThrow(()->new NotFoundException("job alread exist with name: " + jobName));
        return jobMapper.toDTO(jobEntity);
    }

    public JobDTO createJob(JobDTO jobDTO){
        boolean exist = jobRepository.existsByName(jobDTO.getName());
        if(exist)
            throw new ExistException("job already exist with name: " + jobDTO.getName());
        List<SkillDTO> skillDTOList = jobDTO.getSkill().stream()
                .filter(e->skillRepository.existsById(e.getId()))
                .map(e->skillMapper.toDTO(skillRepository.findById(e.getId()).get())).toList();
        jobDTO.setSkill(skillDTOList);
        JobEntity jobEntity =  jobRepository.save(jobMapper.toEntity(jobDTO));
        return jobMapper.toDTO(jobEntity);
    }

    public JobDTO updateJob(Long id,JobDTO jobDTO){
        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("không tìm thấy job id : " + id));
        CompanyEntity companyEntity = companyRepository.findById(jobDTO.getCompany().getId()).orElse(null);
        List<SkillEntity> skillEntityList = jobDTO.getSkill().stream().
                filter(e->skillRepository.existsById(e.getId())).map(e->skillMapper.toEntity(e)).toList();
        jobEntity.setName(jobDTO.getName());
        jobEntity.setDescription(jobDTO.getDescription());
        jobEntity.setLocation(jobDTO.getLocation());
        jobEntity.setQuantity(jobDTO.getQuantity());
        jobEntity.setCompany(companyEntity == null ? jobEntity.getCompany() : companyEntity);
        jobEntity.setSalary(jobDTO.getSalary());
        jobEntity.setLevel(jobDTO.getLevel());
        jobEntity.setSkill(skillEntityList);
        jobRepository.save(jobEntity);
        return jobMapper.toDTO(jobEntity);
    }

    public void deleteJob(Long id){
        JobEntity jobEntity = jobRepository.findById(id).orElseThrow(()->new NotFoundException("job không tồn tại với id: "+id));
        jobRepository.delete(jobEntity);
    }
}
