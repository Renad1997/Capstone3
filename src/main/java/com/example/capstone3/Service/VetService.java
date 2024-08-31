package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Stable;
import com.example.capstone3.Model.Vet;
import com.example.capstone3.Repository.StableRepository;
import com.example.capstone3.Repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;
    private final StableRepository stableRepository;

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    public void addVet(Vet vet) {
        vetRepository.save(vet);
    }

    public void updateVet(Vet vet) {
        Vet existingVet = vetRepository.findVetById(vet.getId());

        if (existingVet == null) {
            throw new ApiException("Vet not found");
        }

        existingVet.setName(vet.getName());
        existingVet.setPhone(vet.getPhone());
        existingVet.setEmail(vet.getEmail());
        existingVet.setYear_of_experience(vet.getYear_of_experience());
        existingVet.setSpecialty(vet.getSpecialty());
        existingVet.setSalary(vet.getSalary());

        vetRepository.save(existingVet);
    }

    public void deleteVet(Integer id) {
        Vet vet = vetRepository.findVetById(id);

        if (vet == null) {
            throw new ApiException("Vet not found");
        }

        vetRepository.delete(vet);
    }
    /*Renad*/
    public List<Vet> nearLocation(Integer vet_id,Integer stable_id) {
        List<Vet> list= new ArrayList<>();
        Vet vet1=vetRepository.findVetById(vet_id);
        Stable stable1=stableRepository.findStableById(stable_id);
        if(vet1==null || stable1==null) {
            throw new ApiException("Vet not found");
        }
        if(vet1.getLocation().equalsIgnoreCase(stable1.getLocation())) {
            list.add(vet1);
        }
        return list;
    }
    /*Renad*/
    public void raiseSalary(Integer vet_id) {
        Vet vet1=vetRepository.findVetById(vet_id);
        if(vet1==null) {
            throw new ApiException("Vet not found");
        }
        if(vet1.getYear_of_experience() >= 5){
            vet1.setSalary(vet1.getSalary() * 1.30);
            vetRepository.save(vet1);
        }

    }
    /*Renad*/
    public List<Vet> getVetByLocation(String location) {
        return vetRepository.findVetByLocation(location);
    }
    /*Renad*/
    public List<Vet> getVetBySalary(double salary) {
        return vetRepository.findVetBySalary(salary);
    }
    /*Renad*/
    public List<Vet> getVetBySpecialty(String specialty) {
        return vetRepository.findVetBySpecialty(specialty);
    }
    /*Renad*/
    public List<Vet> getVetByYear_of_experience(Integer year_of_experience) {
        return vetRepository.findVetByYear_of_experience(year_of_experience);
    }
}
