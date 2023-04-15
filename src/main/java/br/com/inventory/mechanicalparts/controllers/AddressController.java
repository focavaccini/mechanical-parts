package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAddressController;
import br.com.inventory.mechanicalparts.dtos.AddressDTO;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AddressController extends AbstractController<AddressService> implements IAddressController {

    private AddressService addressService;

    public AddressDTO insert(@RequestBody AddressDTO addressDTO){
        Address address = convert(addressDTO, Address.class);
        addressService.insert(address);
        return convert(address, AddressDTO.class);
    }

    public void update(@PathVariable Long idAddress, @RequestBody AddressDTO addressDTO){
        Address address = convert(addressDTO, Address.class);
        addressService.update(idAddress, address);
    }
}
