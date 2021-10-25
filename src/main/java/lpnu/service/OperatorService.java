package lpnu.service;

import lpnu.dto.SimpleUserDTO;

public interface OperatorService {
    SimpleUserDTO getSimpleUserById(long id);
}
