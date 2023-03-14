package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.*;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.InputDto;
import uz.pdp.pc_market.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SupplierRepository supplierRepository;

    public List<Input> getInputs(){
        return inputRepository.findAll();
    }

    public Input getInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    public ApiResponse addInput(InputDto inputDto){
        Timestamp timestamp = Timestamp.from(Instant.now());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha currency topilmadi!", false);
        Currency currency = optionalCurrency.get();

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha supplier topilmadi!", false);
        Supplier supplier = optionalSupplier.get();

        Optional<User> optionalUser = userRepository.findById(inputDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha user topilmadi!", false);
        User user = optionalUser.get();

        List<Input> inputList = inputRepository.findAll();
        int code=0;
        if (!inputList.isEmpty()){
            int code1 = Integer.parseInt(inputList.get(inputList.size() - 1).getCode());
            code = code1+1;
        }else {
            code=1;
        }
        Input input = new Input();
        input.setDate(timestamp);
        input.setCode(String.valueOf(code));
        input.setCurrency(currency);
        input.setUser(user);
        input.setSupplier(supplier);
        input.setWarehouse(warehouse);
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new ApiResponse("Input qo'shildi!", true);
    }

    public ApiResponse editInput(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha input topilmadi!", false);
        Input input = optionalInput.get();

        Timestamp timestamp = Timestamp.from(Instant.now());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha currency topilmadi!", false);
        Currency currency = optionalCurrency.get();

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha supplier topilmadi!", false);
        Supplier supplier = optionalSupplier.get();

        Optional<User> optionalUser = userRepository.findById(inputDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha user topilmadi!", false);
        User user = optionalUser.get();

        input.setDate(timestamp);
        input.setCurrency(currency);
        input.setUser(user);
        input.setSupplier(supplier);
        input.setWarehouse(warehouse);
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new ApiResponse("Input taxrirlandi!", true);
    }

    public ApiResponse deleteInput(Integer id){
        try {
            inputRepository.deleteById(id);
            return new ApiResponse("Input o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }
}
