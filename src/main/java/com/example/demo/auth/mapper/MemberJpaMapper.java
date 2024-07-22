package com.example.demo.auth.mapper;

import com.example.demo.auth.readmodel.MemberReadModel.MemberListViewReadModel;
import com.example.demo.auth.repository.projection.MemberProjection.MemberListViewProjection;
import org.mapstruct.Mapper;

@Mapper
public interface MemberJpaMapper {
    MemberListViewReadModel toReadModel(MemberListViewProjection projection);
}
