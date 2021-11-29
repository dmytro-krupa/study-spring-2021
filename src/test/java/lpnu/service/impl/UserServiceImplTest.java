package lpnu.service.impl;

import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.entity.enumeration.UserRole;
import lpnu.exception.ServiceException;
import lpnu.mapper.UserToUserDTOMapper;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Test
    public void test_getUserById_userExist() throws Exception{
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserToUserDTOMapper userMapper = Mockito.mock(UserToUserDTOMapper.class);

        final UserService userService = new UserServiceImpl(userMapper, userRepository);


        final User user = new User(1L, "name", "", "", UserRole.MANAGER);

        when(userRepository.getUserById(1)).thenReturn(user);
        when(userMapper.toDTO(any())).thenCallRealMethod();


        final UserDTO userDTO = userService.getUserById(1);

        assertNotNull(userDTO);
        assertEquals(1L, (long)userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
    }

    @Test
    public void test_getUserById_userNotExist() throws Exception{
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserToUserDTOMapper userMapper = Mockito.mock(UserToUserDTOMapper.class);

        final UserService userService = new UserServiceImpl(userMapper, userRepository);

        when(userRepository.getUserById(1)).thenThrow( new ServiceException(400, "some exception"));
        when(userMapper.toDTO(any())).thenCallRealMethod();


        try{
            final UserDTO userDTO = userService.getUserById(1);
            fail();
        } catch (ServiceException e){

        }
    }
}
