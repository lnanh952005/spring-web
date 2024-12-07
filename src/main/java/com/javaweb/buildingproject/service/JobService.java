package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.JobDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.domain.entity.JobEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.mapper.JobMapper;
import com.javaweb.buildingproject.repository.CompanyRepository;
import com.javaweb.buildingproject.repository.JobRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class JobService {
    private JobRepository jobRepository;
    private JobMapper jobMapper;
    private CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, JobMapper jobMapper, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.companyRepository = companyRepository;
    }

    public List<JobDTO> fetchAllJobs(){
        return jobRepository.findAll().stream().map(e->jobMapper.toDTO(e)).toList();
    }

    public JobDTO fetchById(Long id){
        return jobRepository.findById(id).map(e->jobMapper.toDTO(e))
                .orElseThrow(()-> new NotFoundException("không tìm thấy job với id: "+id));
    }

    public JobDTO fetchByName(String jobName){
        Optional<JobEntity> jobEntityOp = jobRepository.findByName(jobName);
        if(jobEntityOp.isPresent()){
            return jobMapper.toDTO(jobEntityOp.get());
        }
        throw new NotFoundException("không tìm thấy job name : " + jobName);
    }

    public JobDTO createJob(JobDTO jobDTO){
        Optional<JobEntity> jobEntityOp = jobRepository.findByName(jobDTO.getName());
        JobEntity jobEntity = jobEntityOp.isPresent() ? jobEntityOp.get() : null;
        if(jobEntity == null){
            throw new NotFoundException("job name : " + jobDTO.getName() + " đã tồn tại");
        }
        jobRepository.save(jobMapper.toEntity(jobDTO));
        return jobDTO;
    }

    public JobDTO updateJob(Long id,JobDTO jobDTO){
        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("không tìm thấy job id : " + id));
        CompanyEntity companyEntity = companyRepository.findById(jobDTO.getCompany().getId()).orElse(null);
        jobEntity.setName(jobDTO.getName());
        jobEntity.setDescription(jobDTO.getDescription());
        jobEntity.setLocation(jobDTO.getLocation());
        jobEntity.setQuantity(jobDTO.getQuantity());
        jobEntity.setCompany(companyEntity == null ? jobEntity.getCompany() : companyEntity);
        jobEntity.setSalary(jobDTO.getSalary());
        jobEntity.setLevel(jobDTO.getLevel());
        jobRepository.save(jobEntity);
        return jobDTO;
    }

    public void deleteJob(Long id){
        JobEntity jobEntity = jobRepository.findById(id).orElseThrow(()->new NotFoundException("job không tồn tại với id: "+id));
        jobEntity.setActive(false);
        jobRepository.save(jobEntity);
    }
}
