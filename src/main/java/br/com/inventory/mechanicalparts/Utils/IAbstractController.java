//package br.com.inventory.mechanicalparts.Util;
//
//import com.smartbr.clinica.model.dto.system.AbstractDTO;
//import com.smartbr.clinica.model.dto.system.DefaultDTO;
//import com.smartbr.clinica.pattern.constants.OperationsParam;
//import com.smartbr.clinica.pattern.constants.OperationsPath;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;
//
//import java.util.Map;
//
//@RestController
//public interface IAbstractController<T extends AbstractDTO<?>> {
//
//    String PATH = "/filter";
//
//    @GetMapping(IAbstractController.PATH)
//    @ApiOperation(value = "${generic.findAll}")
//    ResponseEntity<Page<T>> findAll(@ApiIgnore Pageable pageable,
//                                    @ApiParam(value = "${params.filters-pageable}") @RequestParam(required = false) Map<String, Object> filters);
//
//    @GetMapping(IAbstractController.PATH + "/list")
//    @ApiOperation(value = "${generic.findAllList}")
//    DefaultDTO<T> findAll(@ApiParam(value = "${params.filters-dynamic}") @RequestParam(required = false) Map<String, Object> filters);
//
//    @GetMapping(IAbstractController.PATH + OperationsPath.ID)
//    @ApiOperation(value = "${generic.findById}")
//    ResponseEntity<T> findById(@ApiParam("${generic.findById}") @PathVariable(OperationsParam.ID) Integer id);
//
//
//}
