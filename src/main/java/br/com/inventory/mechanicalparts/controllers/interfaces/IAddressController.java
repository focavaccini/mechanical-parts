package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.AddressDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
public interface IAddressController {

    @PostMapping
    AddressDTO insert(@RequestBody AddressDTO addressDTO);

    @PutMapping(value = "/{idAddress}")
    void update(@PathVariable Long idAddress, @RequestBody AddressDTO addressDTO);

    @GetMapping
    List<AddressDTO> getAll();

    @GetMapping(value = "/{idAddress}")
    AddressDTO getById(@PathVariable Long idAddress);
}
