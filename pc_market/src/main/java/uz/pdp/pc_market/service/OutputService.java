package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.*;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.OutputDto;
import uz.pdp.pc_market.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;

    public List<Output> getOutputs(){
        return outputRepository.findAll();
    }

    public Output getOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }

    public ApiResponse addOutput(OutputDto outputDto){
        Timestamp timestamp = Timestamp.from(Instant.now());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<User> optionalUser = userRepository.findById(outputDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Kiritilgan id bo;yicha user topilmadi!", false);
        User user = optionalUser.get();

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha client topilmadi!", false);
        Client client = optionalClient.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha currency topilmadi!", false);
        Currency currency = optionalCurrency.get();

        List<Output> outputList = outputRepository.findAll();
        int code = 0;
        if (!outputList.isEmpty()){
            code = Integer.parseInt(outputList.get(outputList.size()-1).getCode())+1;
        }else {
            code = 1;
        }

        Output output = new Output();
        output.setUser(user);
        output.setDate(timestamp);
        output.setCurrency(currency);
        output.setCode(String.valueOf(code));
        output.setWarehouse(warehouse);
        output.setClient(client);
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new ApiResponse("Output qo'shildi!", true);
    }

    public ApiResponse editOutput(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha output topilmadi!", false);
        Output output = optionalOutput.get();

        Timestamp timestamp = Timestamp.from(Instant.now());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<User> optionalUser = userRepository.findById(outputDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Kiritilgan id bo;yicha user topilmadi!", false);
        User user = optionalUser.get();

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha client topilmadi!", false);
        Client client = optionalClient.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha currency topilmadi!", false);
        Currency currency = optionalCurrency.get();

        output.setUser(user);
        output.setDate(timestamp);
        output.setCurrency(currency);
        output.setWarehouse(warehouse);
        output.setClient(client);
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new ApiResponse("Output taxrirlandi!", true);
    }

    public ApiResponse deleteOutput(Integer id){
        try {
            outputRepository.deleteById(id);
            return new ApiResponse("Output o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }
}
