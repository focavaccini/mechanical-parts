package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ProfessionalDTO;
import br.com.inventory.mechanicalparts.entities.Professional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/professional")
public interface IProfessionalController {

    @PostMapping
    Professional insert(@RequestBody ProfessionalDTO professionalDTO);

    @PutMapping(value = "/{idProfessional}")
    void update(@PathVariable("idProfessional") Long idProfessional, @RequestBody ProfessionalDTO professionalDTO);

    @GetMapping
    List<ProfessionalDTO> getAll();

    @GetMapping(value = "/{idProfessional}")
    ProfessionalDTO getById(@PathVariable("idProfessional") Long idProfessional);
}
