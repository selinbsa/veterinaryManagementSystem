package dev.patika.veterinaryManagementSystem.core.config.modelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();   // DTO → Entity
    ModelMapper forResponse();  // Entity → DTO
}
