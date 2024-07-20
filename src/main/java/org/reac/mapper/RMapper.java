package org.reac.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.reac.entity.Users;
import org.reac.model.RDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RMapper {

    Users map(RDTO rdto);

    RDTO map(Users users);
}
