package com.wms.domain.user.dto.response;

import com.wms.domain.user.entity.Role;
import com.wms.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String profileImage;
    private String departmentName;
    private String positionName;

    public static UserListResponseDTO toDTO(User user) {
        return UserListResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .profileImage(user.getProfileImage())
                .departmentName(user.getDepartment().getDepartment_name())
                .positionName(user.getPosition().getPosition_name())
                .build();
    }
}
