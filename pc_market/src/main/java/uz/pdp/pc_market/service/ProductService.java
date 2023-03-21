package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.Category;
import uz.pdp.pc_market.entity.Detail;
import uz.pdp.pc_market.entity.Product;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.ProductDto;
import uz.pdp.pc_market.repository.CategoryRepository;
import uz.pdp.pc_market.repository.DetailRepository;
import uz.pdp.pc_market.repository.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DetailRepository detailRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseGet(Product::new);
    }

    public ApiResponse addProduct(ProductDto productDto){

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha category topilmadi!", false);
        Category category = optionalCategory.get();

        Set<Detail> details = new HashSet<>();

        if (productDto.getDetailsId()!=null){
            Set<Integer> detailsId = productDto.getDetailsId();
            for (Integer integer : detailsId) {
                Optional<Detail> optionalDetail = detailRepository.findById(integer);
                if (optionalDetail.isPresent()){
                    details.add(optionalDetail.get());
                }else {
                    return new ApiResponse("Kiritilgan id bo'yicha detail topilmadi!", false);
                }
            }
        }

        Product product = new Product();
        product.setCategory(category);
        if (productDto.getDetailsId()==null){
            product.setDetails(null);
        }else {
            product.setDetails(details);
        }
        product.setDescription(productDto.getDescription());
        product.setGuaranteeYear(productDto.getGuaranteeYear());
        productRepository.save(product);
        return new ApiResponse("Product qo'shildi!", true);
    }

    public ApiResponse editProduct(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha product topilmadi!", false);
        Product product = optionalProduct.get();

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha category topilmadi!", false);
        Category category = optionalCategory.get();

        Set<Detail> details = new HashSet<>();

        if (!productDto.getDetailsId().isEmpty()){
            Set<Integer> detailsId = productDto.getDetailsId();
            for (Integer integer : detailsId) {
                Optional<Detail> optionalDetail = detailRepository.findById(integer);
                if (optionalDetail.isPresent()){
                    details.add(optionalDetail.get());
                }else {
                    return new ApiResponse("Kiritilgan id bo'yicha detail topilmadi!", false);
                }
            }
        }

        product.setCategory(category);
        product.setDetails(details);
        product.setDescription(productDto.getDescription());
        product.setGuaranteeYear(productDto.getGuaranteeYear());
        productRepository.save(product);
        return new ApiResponse("Product taxrirlandi!", true);
    }

    public ApiResponse deleteProduct(Integer id){
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Product o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }

}
