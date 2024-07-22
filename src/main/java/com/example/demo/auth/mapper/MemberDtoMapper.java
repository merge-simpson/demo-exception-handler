package com.example.demo.auth.mapper;

import com.example.demo.auth.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.domain.type.MemberStatus;
import org.mapstruct.Mapper;

@Mapper
public interface MemberDtoMapper {
    Member toEntity(MemberSignUpRequestDto requestBody, MemberStatus status);
}
