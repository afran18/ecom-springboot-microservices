package com.app.ecom.service;

import com.app.ecom.dto.AddressResponseDto;
import com.app.ecom.dto.UserRequestDto;
import com.app.ecom.dto.UserResponseDto;
import com.app.ecom.model.Address;
import com.app.ecom.model.UserRole;
import com.app.ecom.repository.UserRepository;
import com.app.ecom.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserResponseDto> fetchAllUsers() {
         return userRepository.findAll()
                 .stream()
                 .map(this::mapToUserResponse)
                 .collect(Collectors.toList());
    }

    public UserResponseDto addUser(UserRequestDto userRequest) {
        User user = new User();
        mapToUserRequest(user, userRequest);
        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    private void mapToUserRequest(User user, UserRequestDto userRequest) {
        if (userRequest.getFirstName() != null) {
            user.setFirstName(userRequest.getFirstName());
        }
        if (userRequest.getLastName() != null) {
            user.setLastName(userRequest.getLastName());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPhone() != null) {
            user.setPhone(userRequest.getPhone());
        }

        if (user.getRole() == null) {
            user.setRole(UserRole.CUSTOMER);
        }

        if (userRequest.getAddress() != null) {
            Address address = (user.getAddress() != null) ? user.getAddress() : new Address();

            if (userRequest.getAddress().getStreet() != null) {
                address.setStreet(userRequest.getAddress().getStreet());
            }
            if (userRequest.getAddress().getCity() != null) {
                address.setCity(userRequest.getAddress().getCity());
            }
            if (userRequest.getAddress().getState() != null) {
                address.setState(userRequest.getAddress().getState());
            }
            if (userRequest.getAddress().getCountry() != null) {
                address.setCountry(userRequest.getAddress().getCountry());
            }
            if (userRequest.getAddress().getZipcode() != null) {
                address.setZipcode(userRequest.getAddress().getZipcode());
            }

            user.setAddress(address);
        }
    }

    public Optional<UserResponseDto> fetchUserById(Long id) {
/*        return userList.stream()
**              .filter(user -> user.getId().equals(id))
**              .findFirst();
*/
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public boolean updateUser(Long id, UserRequestDto updatedUserRequest) {
//        return userList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .map(existingUser ->  {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    return true;
//                }).orElse(false);
        return userRepository.findById(id)
                .map(existingUser -> {
                    mapToUserRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);

                    return true;
                }).orElse(false);
    }

    private UserResponseDto mapToUserResponse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(String.valueOf(user.getId()));
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setRole(user.getRole());

        if(user.getAddress() != null) {
            AddressResponseDto addressResponseDto = new AddressResponseDto();
            addressResponseDto.setStreet(user.getAddress().getStreet());
            addressResponseDto.setCity(user.getAddress().getCity());
            addressResponseDto.setState(user.getAddress().getState());
            addressResponseDto.setCountry(user.getAddress().getCountry());
            addressResponseDto.setZipcode(user.getAddress().getZipcode());
            userResponseDto.setAddressResponse(addressResponseDto);
        }

        return userResponseDto;
    }
}
