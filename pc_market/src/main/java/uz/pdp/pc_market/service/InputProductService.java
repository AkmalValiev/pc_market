package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.Input;
import uz.pdp.pc_market.entity.InputProduct;
import uz.pdp.pc_market.entity.Product;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.InputProductDto;
import uz.pdp.pc_market.repository.InputProductRepository;
import uz.pdp.pc_market.repository.InputRepository;
import uz.pdp.pc_market.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public List<InputProduct> getInputProducts(){
        return inputProductRepository.findAll();
    }

    public InputProduct getInputProduct(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }

    public ApiResponse addInputProduct(InputProductDto inputProductDto){

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha product topilmadi!", false);
        Product product = optionalProduct.get();

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha input topilmadi!", false);
        Input input = optionalInput.get();

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(product);
        inputProduct.setInput(input);
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("InputProduct qo'shildi!", true);
    }

    public ApiResponse editInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha inputProduct topilmadi!",false);
        InputProduct inputProduct = optionalInputProduct.get();

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha product topilmadi!", false);
        Product product = optionalProduct.get();

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha input topilmadi!", false);
        Input input = optionalInput.get();

        inputProduct.setProduct(product);
        inputProduct.setInput(input);
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("InputProduct taxrirlandi!", true);

    }

    public ApiResponse deleteInputProduct(Integer id){
        try {
            inputProductRepository.deleteById(id);
            return new ApiResponse("InputProduct o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!",false);
        }
    }

}
