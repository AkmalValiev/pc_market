package uz.pdp.pc_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pc_market.entity.Parameter;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.ParameterDto;
import uz.pdp.pc_market.service.ParameterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parameter")
public class ParameterController {

    @Autowired
    ParameterService parameterService;

    @GetMapping
    public ResponseEntity<List<Parameter>> getParameters(){
        return ResponseEntity.ok(parameterService.getParameters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parameter> getParameter(@PathVariable Integer id){
        return ResponseEntity.ok(parameterService.getParameter(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addParameter(@RequestBody ParameterDto parameterDto){
        ApiResponse apiResponse = parameterService.addParameter(parameterDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editParameter(@PathVariable Integer id, @RequestBody ParameterDto parameterDto){
        ApiResponse apiResponse = parameterService.editParameter(id, parameterDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteParameter(@PathVariable Integer id){
        ApiResponse apiResponse = parameterService.deleteParameter(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
