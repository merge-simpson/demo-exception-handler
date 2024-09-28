package com.example.demo.auth.mapper;

import com.example.demo.auth.readmodel.MemberReadModel.MemberDetailsReadModel;
import com.example.demo.auth.readmodel.MemberReadModel.MemberSummaryReadModel;
import com.example.demo.auth.readmodel.MemberReadModel.SigningReadModel;
import com.example.demo.auth.repository.projection.MemberProjection.MemberDetailsProjection;
import com.example.demo.auth.repository.projection.MemberProjection.SigningProjection;
import com.example.demo.auth.repository.projection.MemberProjection.MemberSummaryProjection;
import org.mapstruct.Mapper;

@Mapper
public interface MemberJpaMapper {
    SigningReadModel toReadModel(SigningProjection projection);
    MemberDetailsReadModel toReadModel(MemberDetailsProjection projection);
    MemberSummaryReadModel toReadModel(MemberSummaryProjection projection);
}
