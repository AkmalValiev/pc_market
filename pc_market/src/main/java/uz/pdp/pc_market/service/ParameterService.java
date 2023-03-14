package uz.pdp.pc_market.service;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pc_market.entity.Detail;
import uz.pdp.pc_market.entity.Measurement;
import uz.pdp.pc_market.entity.Parameter;
import uz.pdp.pc_market.payload.ApiResponse;
import uz.pdp.pc_market.payload.ParameterDto;
import uz.pdp.pc_market.repository.DetailRepository;
import uz.pdp.pc_market.repository.MeasurementRepository;
import uz.pdp.pc_market.repository.ParameterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterService {

    @Autowired
    ParameterRepository parameterRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    DetailRepository detailRepository;

    public List<Parameter> getParameters(){
        return parameterRepository.findAll();
    }

    public Parameter getParameter(Integer id){
        Optional<Parameter> optionalParameter = parameterRepository.findById(id);
        return optionalParameter.orElseGet(Parameter::new);
    }

    public ApiResponse addParameter(ParameterDto parameterDto){
        Measurement measurement = new Measurement();
        if (parameterDto.getMeasurementId()!=null){
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(parameterDto.getMeasurementId());
            if (optionalMeasurement.isPresent()){
                measurement = optionalMeasurement.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha measurement topilmadi!", false);
            }
        }
        Detail detail = new Detail();
        if (parameterDto.getDetailId()!=null){
            Optional<Detail> optionalDetail = detailRepository.findById(parameterDto.getDetailId());
            if (optionalDetail.isPresent()){
                detail = optionalDetail.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha detail topilmadi!", false);
            }
        }

        Parameter parameter = new Parameter();
        parameter.setName(parameterDto.getName());
        parameter.setSize(parameterDto.getSize());
        parameter.setActive(parameterDto.isActive());
        parameter.setDetail(detail);
        parameter.setMeasurement(measurement);
        parameterRepository.save(parameter);
        return new ApiResponse("Parameter qo'shildi!", true);
    }

    public ApiResponse editParameter(Integer id, ParameterDto parameterDto){
        Optional<Parameter> optionalParameter = parameterRepository.findById(id);
        if (!optionalParameter.isPresent())
            return new ApiResponse("Kiritilgan id bo'yicha parameter topilmadi!", false);
        Parameter parameter = optionalParameter.get();

        Measurement measurement = new Measurement();
        if (parameterDto.getMeasurementId()!=null){
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(parameterDto.getMeasurementId());
            if (optionalMeasurement.isPresent()){
                measurement = optionalMeasurement.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha measurement topilmadi!", false);
            }
        }
        Detail detail = new Detail();
        if (parameterDto.getDetailId()!=null){
            Optional<Detail> optionalDetail = detailRepository.findById(parameterDto.getDetailId());
            if (optionalDetail.isPresent()){
                detail = optionalDetail.get();
            }else {
                return new ApiResponse("Kiritilgan id bo'yicha detail topilmadi!", false);
            }
        }
        parameter.setName(parameterDto.getName());
        parameter.setSize(parameterDto.getSize());
        parameter.setActive(parameterDto.isActive());
        parameter.setDetail(detail);
        parameter.setMeasurement(measurement);
        parameterRepository.save(parameter);
        return new ApiResponse("Parameter taxrirlandi!", true);
    }

    public ApiResponse deleteParameter(Integer id){
        try {
            parameterRepository.deleteById(id);
            return new ApiResponse("Parameter o'chirildi!", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }

}
