package com.example.demo.auth.mapper;

import com.example.demo.auth.controller.dto.AuthDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.domain.data.MemberStatus;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper
public interface MemberDtoMapper {
    Member toEntity(MemberSignUpRequestDto requestBody, MemberStatus status, Instant createdAt);
}
