package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.Output;
import uz.pdp.pc_market.entity.OutputProduct;
import uz.pdp.pc_market.entity.Product;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.OutputProductDto;
import uz.pdp.pc_market.repository.OutputProductRepository;
import uz.pdp.pc_market.repository.OutputRepository;
import uz.pdp.pc_market.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    public List<OutputProduct> getOutputProducts(){
        return outputProductRepository.findAll();
    }

    public OutputProduct getOutputProduct(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElseGet(OutputProduct::new);
    }

    public ApiResponse addOutputProduct(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha product topilmadi!", false);
        Product product = optionalProduct.get();

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha output topilmadi!", false);
        Output output = optionalOutput.get();

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(product);
        outputProduct.setOutput(output);
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new ApiResponse("OutputProduct qo'shildi!", true);
    }

    public ApiResponse editOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha outputProduct topilmadi!", false);
        OutputProduct outputProduct = optionalOutputProduct.get();

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha product topilmadi!", false);
        Product product = optionalProduct.get();

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha output topilmadi!", false);
        Output output = optionalOutput.get();

        outputProduct.setProduct(product);
        outputProduct.setOutput(output);
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new ApiResponse("OutputProduct taxrirlandi!", true);
    }

    public ApiResponse deleteOutputProduct(Integer id){
        try {
            outputProductRepository.deleteById(id);
            return new ApiResponse("OutputProduct o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }

}
