package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.Captain;
import com.sgwb.saigonwaterbus.Model.DTO.CaptainDTO;
import com.sgwb.saigonwaterbus.Model.DTO.InvoiceDTO;
import com.sgwb.saigonwaterbus.Model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    Invoice toInvoice(InvoiceDTO invoiceDTO);

    @Mapping(source = "invoice.account.id", target = "account_id")
    @Mapping(source = "invoice.account.email", target = "email")

    InvoiceDTO toiInvoiceDto(Invoice invoice);
    List<Invoice> toCaptainList(List<InvoiceDTO> invoiceDTOList);
    @Mapping(source = "invoice.account.id", target = "account_id")
    List<InvoiceDTO> toInvoiceDtoList(List<Invoice> invoiceList);
}

