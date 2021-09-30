package ru.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.domain.*;

import java.util.List;

@Mapper(uses = CartMapper.class)
public interface ClientMapper {

    ClientMapper CLIENT_MAPPER =  Mappers.getMapper(ClientMapper.class);

    @InheritInverseConfiguration
    ClientDTO fromClient (Client client);


    Client toClient (ClientDTO clientDTO);

    List<ClientDTO> fromClientList (List<Client> clients);
    List<Client> toClientsList (List<ClientDTO> clientDTOs);

}
