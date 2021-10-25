package lpnu.mapper;

import lpnu.dto.SimpleUserDTO;
import lpnu.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SimpleUserMapper {
    public SimpleUserDTO userToSimpleUserDTO(final User user){
        final SimpleUserDTO simpleUserDTO = new SimpleUserDTO();

        simpleUserDTO.setName(user.getName());

        return simpleUserDTO;
    }
}
