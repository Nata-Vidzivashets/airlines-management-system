package airlines.management.system.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
