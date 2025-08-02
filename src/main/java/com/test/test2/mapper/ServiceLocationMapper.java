package com.test.test2.mapper;

import com.test.test2.dto.ServiceLocationDTO;
import com.test.test2.model.ServiceLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceLocationMapper {

    @Mapping(target = "id", ignore = true) // ID is generated for new entities
    @Mapping(target = "customer", ignore = true) // Customer is set in service layer
    ServiceLocation toEntity(ServiceLocationDTO dto);

    ServiceLocationDTO toDto(ServiceLocation entity);

    List<ServiceLocationDTO> toDtoList(List<ServiceLocation> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateServiceLocationFromDto(ServiceLocationDTO dto, @MappingTarget ServiceLocation entity);
}
