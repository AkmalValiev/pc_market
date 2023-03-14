package uz.pdp.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.Detail;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.DetailDto;
import uz.pdp.pc_market.repository.DetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    public List<Detail> getDetails(){
        return detailRepository.findAll();
    }

    public Detail getDetail(Integer id){
        Optional<Detail> optionalDetail = detailRepository.findById(id);
        return optionalDetail.orElseGet(Detail::new);
    }

    public ApiResponse addDetail(DetailDto detailDto){

        boolean exists = detailRepository.existsByName(detailDto.getName());
        if (exists)
            return new ApiResponse("Bunday nomli detail mavjud!", false);

        Detail detail = new Detail();
        detail.setName(detailDto.getName());
        detailRepository.save(detail);
        return new ApiResponse("Detail qo'shildi!",true);
    }

    public ApiResponse editDetail(Integer id, DetailDto detailDto){
        Optional<Detail> optionalDetail = detailRepository.findById(id);
        if (!optionalDetail.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha detail topilmadi!", false);
        boolean exists = detailRepository.existsByNameAndIdNot(detailDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday nomli detail mavjud!", false);
        Detail detail = optionalDetail.get();
        detail.setName(detailDto.getName());
        detailRepository.save(detail);
        return new ApiResponse("Detail taxrirlandi!", true);
    }

    public ApiResponse deleteDetail(Integer id){
        try {
            detailRepository.deleteById(id);
            return new ApiResponse("Detail o'shirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }

}
