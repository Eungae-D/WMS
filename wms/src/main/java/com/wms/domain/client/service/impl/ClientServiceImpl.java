package com.wms.domain.client.service.impl;

import com.wms.domain.client.dto.request.ClientRequestDTO;
import com.wms.domain.client.dto.response.ClientResponseDTO;
import com.wms.domain.client.entity.Category;
import com.wms.domain.client.entity.Client;
import com.wms.domain.client.repository.ClientRepository;
import com.wms.domain.client.service.ClientService;
import com.wms.global.exception.exception.ClientException;
import com.wms.global.exception.responseCode.ClientExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    // 거래처 등록
    @Override
    @Transactional
    public void registerClient(ClientRequestDTO clientRequestDTO) {
        if(clientRepository.existsByCode(clientRequestDTO.getCode())){
            throw new ClientException(ClientExceptionResponseCode.CLIENT_CODE_DUPLICATE,"이미 존재하는 거래처코드입니다.");
        }

        Client client = clientRequestDTO.toEntity();
        clientRepository.save(client);
    }

    // 거래처 삭제
    @Override
    @Transactional
    public void deleteClient(Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND, "거래처를 찾을 수 없습니다.");
        }
        clientRepository.deleteById(clientId);
    }

    // 거래처 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getAllClients(){
        List<Client> clients = clientRepository.findAll();

        if(clients.isEmpty()){
            throw new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND, "거래처를 찾을 수 없습니다.");
        }

        return clients.stream()
                .map(ClientResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 카테고리별로 거래처 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getClientsByCategory(Category category) {
        List<Client> clients = clientRepository.findAllByCategory(category);

        if(clients.isEmpty()){
            throw new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND,"거래처를 찾을 수 없습니다.");
        }

        return clients.stream()
                .map(ClientResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 이름으로 거래처 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getClientsByName(String name) {
        List<Client> clients = clientRepository.findByName(name);

        if(clients.isEmpty()){
            throw new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND,"거래처를 찾을 수 없습니다.");
        }

        return clients.stream()
                .map(ClientResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }



}
