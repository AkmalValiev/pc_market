package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.Attachment;
import uz.pdp.pc_market.entity.Category;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.CategoryDto;
import uz.pdp.pc_market.repository.AttachmentRepository;
import uz.pdp.pc_market.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    public ApiResponse addCategory(CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists)
            return new ApiResponse("Bunaqa nomli category mavjud!", false);
        Category category = new Category();
        if (categoryDto.getParenCategoryId()!=null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParenCategoryId());
            if (optionalCategory.isPresent()){
                category = optionalCategory.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha category topilmadi!", false);
            }
        }
        Attachment attachment = new Attachment();
        if (categoryDto.getAttachmentId()!=null){
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(categoryDto.getAttachmentId());
            if (optionalAttachment.isPresent()){
                attachment = optionalAttachment.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha attachment topilmadi!", false);
            }
        }
        Category category1 = new Category();
        category1.setName(categoryDto.getName());
        category1.setParentCategory(category);
        category1.setAttachment(attachment);
        category1.setActive(categoryDto.isActive());
        categoryRepository.save(category1);
        return new ApiResponse("Category qo'shildi!", true);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto){

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha category topilmadi!", false);
        Category category = optionalCategory.get();
        boolean exists = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday nomli category mavjud!", false);
        Category category1 = new Category();
        if (categoryDto.getParenCategoryId()!=null) {
            Optional<Category> optionalCategoryParent = categoryRepository.findById(categoryDto.getParenCategoryId());
            if (optionalCategoryParent.isPresent()){
                category1 = optionalCategory.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha category topilmadi!", false);
            }
        }
        Attachment attachment = new Attachment();
        if (categoryDto.getAttachmentId()!=null){
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(categoryDto.getAttachmentId());
            if (optionalAttachment.isPresent()){
                attachment = optionalAttachment.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha attachment topilmadi!", false);
            }
        }
        category.setName(categoryDto.getName());
        category.setParentCategory(category1);
        category.setAttachment(attachment);
        category.setActive(categoryDto.isActive());
        categoryRepository.save(category);
        return new ApiResponse("Category taxrirlandi!", true);
    }

    public ApiResponse deleteCategory(Integer id){
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }

}
