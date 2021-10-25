package lpnu.service.impl;

import lpnu.dto.SimpleUserDTO;
import lpnu.mapper.SimpleUserMapper;
import lpnu.repository.UserRepository;
import lpnu.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpleUserMapper simpleUserMapper;

    @Override
    public SimpleUserDTO getSimpleUserById(final long id) {
        return simpleUserMapper.userToSimpleUserDTO(userRepository.getUserById(id));
    }
}
