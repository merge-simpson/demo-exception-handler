package com.example.demo.members.mapper;

import com.example.demo.members.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.members.domain.Member;
import com.example.demo.members.domain.type.MemberStatus;
import org.mapstruct.Mapper;

@Mapper
public interface DtoMemberMapper {
    Member toEntity(MemberSignUpRequestDto requestBody, MemberStatus status);
}
