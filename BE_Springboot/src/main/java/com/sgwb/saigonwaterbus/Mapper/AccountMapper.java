package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.AccountDTO;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountDTO toAccountDTO(Account account);
    @Mapping(source = "id", target = "id")
    Account toAccount(AccountDTO accountDTO);
    List<AccountDTO> toAccountDtoList(List<Account> accountList);
    RegisterResponse toRegisterResponse(Account account);
}
