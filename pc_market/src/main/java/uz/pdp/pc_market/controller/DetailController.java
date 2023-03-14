package uz.pdp.pc_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pc_market.entity.Detail;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.DetailDto;
import uz.pdp.pc_market.service.DetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/detail")
public class DetailController {

    @Autowired
    DetailService detailService;

    @GetMapping
    public ResponseEntity<List<Detail>> getDetails(){
        return ResponseEntity.ok(detailService.getDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail> getDetail(@PathVariable Integer id){
        return ResponseEntity.ok(detailService.getDetail(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addDetail(@RequestBody DetailDto detailDto){
        ApiResponse apiResponse = detailService.addDetail(detailDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDetail(@PathVariable Integer id, @RequestBody DetailDto detailDto){
        ApiResponse apiResponse = detailService.editDetail(id, detailDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDetail(@PathVariable Integer id){
        ApiResponse apiResponse = detailService.deleteDetail(id);
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
