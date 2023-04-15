package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ProfessionalDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/professional")
public interface IProfessionalController {

    @PostMapping
    ProfessionalDTO insert(@RequestBody ProfessionalDTO professionalDTO);

    @PutMapping(value = "/{idProfessional}")
    void update(@PathVariable Long idProfessional, @RequestBody ProfessionalDTO professionalDTO);
}
