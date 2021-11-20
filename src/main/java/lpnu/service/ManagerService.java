package lpnu.service;

public interface ManagerService {
    void approve(Long fuelId);
    void deactivate(Long fuelId);
    void delete(Long fuelId);
}
