package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.AddressDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/address")
public interface IAddressController {

    @PostMapping
    AddressDTO insert(@RequestBody AddressDTO addressDTO);

    @PutMapping(value = "/{id}")
    void update(@PathVariable Long idAddress, @RequestBody AddressDTO addressDTO);
}
